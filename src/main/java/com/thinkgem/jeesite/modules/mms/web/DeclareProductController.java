/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.entity.MarketProduct;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;
import com.thinkgem.jeesite.modules.mms.service.MarketProductService;
import com.thinkgem.jeesite.modules.mms.service.ProductService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

import java.util.Date;

/**
 * 申报产品Controller
 *
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/declareProduct")
public class DeclareProductController extends BaseController {

    @Autowired
    private DeclareProductService declareProductService;
    @Autowired
    private MarketProductService marketProductService;

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public DeclareProduct get(@RequestParam(required = false) String id) {
        DeclareProduct entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = declareProductService.get(id);
        }
        if (entity == null) {
            entity = new DeclareProduct();
        }
        return entity;
    }

    @RequiresPermissions("mms:declareProduct:view")
    @RequestMapping(value = {"list", ""})
    public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<DeclareProduct> page = declareProductService.findPage(new Page<DeclareProduct>(request, response), declareProduct);
        Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
        model.addAttribute("page", page);
        return "modules/mms/declareProductList";
    }

    @RequiresPermissions("mms:declareProduct:view")
    @RequestMapping(value = "form")
    public String form(DeclareProduct declareProduct, Model model) {

        MarketProduct marketProduct = new MarketProduct();
        //把对应的产品中文名称，英文名称，和产品编号显示出来
        if(StringUtils.isNoneBlank(declareProduct.getId())){
            String declareProductId = declareProduct.getId();//申报表id
            Product product = productService.getByDeclareProductId(declareProductId); //产品信息

            marketProduct = marketProductService.get(product.getMarketProductId());
        }
        model.addAttribute("marketProduct", marketProduct);
        model.addAttribute("declareProduct", declareProduct);
        return "modules/mms/declareProductForm";
    }

    @RequiresPermissions("mms:declareProduct:edit")
    @RequestMapping(value = "save")
    public String save(DeclareProduct declareProduct, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, declareProduct)) {
            return form(declareProduct, model);
        }

        //获取当前登录用户.处理人ID
        User user = UserUtils.getUser();
        declareProduct.setProductHandlePersonId(user.getId());
        //查找对应的产品汇总及记录
        Product product = productService.getByDeclareProductId(declareProduct.getId());
        if (product != null) {
            //保存时判断产品状态
            /**
             * 终止申报 -- 7----->	备注有“终止申报”字段
             * 不予批准 ------6--------->	立项时间、来样时间、上报、下意见、批件五项有日期数据且备注有“不予批准”字段
             * 取得批件	--------5---------->立项时间、来样时间、上报、下意见、批件五项有日期数据
             * 完善资料	-----4--------> 立项时间、来样时间、上报、下意见四项有日期数据
             * 申报	---------3-------->立项时间、来样时间、上报三项有日期数据
             *
             *  初审	--仅立项时间一项有日期数据								20%
             送检==	立项时间和来样时间两项有日期数据								40%
             申报---	立项时间、来样时间、申报时间三项有日期数据								60%
             完善资料---	下意见时间项有日期数据								80%	橙色标记
             取得批件---	取得批件时间项有日期数据								100%	绿色标记  --最优先判断
             不予批准---	产品备注有“不予批准”字段								100%	红色标记
             终止申报---	产品备注有“终止申报”字段								100%	灰色标记
             */

            Date reportTime = declareProduct.getReportTime();//上报时间
            Date nextOpinionTime = declareProduct.getNextOpinionTime(); //下意见时间
            Date documentTime = declareProduct.getDocumentTime();//批件时间
            String productStatusRemark = declareProduct.getProductStatusRemark();//备注

            if (productStatusRemark.equals(MmsConstant.PRODUCT_STATUS_REMARK_3)) { //这个优先,//3:终止申报
                product.setProductStatus(MmsConstant.PRODUCT_STATUS_7); //3:终止申报
                product.setProductProcess(MmsConstant.PRODUCT_PROCESS_7);
            } else if (productStatusRemark.equals(MmsConstant.PRODUCT_STATUS_REMARK_2)) { //2:不予批准
                product.setProductStatus(MmsConstant.PRODUCT_STATUS_6);
                product.setProductProcess(MmsConstant.PRODUCT_PROCESS_7); //100
            } else if (productStatusRemark.equals(MmsConstant.PRODUCT_STATUS_REMARK_1)) { //1:通过审批
                product.setProductStatus(MmsConstant.PRODUCT_STATUS_5);
                product.setProductProcess(MmsConstant.PRODUCT_PROCESS_7); //100
            } else {
//				if(reportTime!=null){ //上报时间
//					product.setProductStatus(MmsConstant.PRODUCT_STATUS_3);
//					product.setProductProcess(MmsConstant.PRODUCT_PROCESS_3); //60
//					if(nextOpinionTime!=null){ //下意见时间
//						product.setProductStatus(MmsConstant.PRODUCT_STATUS_4);
//						product.setProductProcess(MmsConstant.PRODUCT_PROCESS_4); //80
//						if(documentTime !=null){ //批件时间
//							product.setProductStatus(MmsConstant.PRODUCT_STATUS_5);
//							product.setProductProcess(MmsConstant.PRODUCT_PROCESS_7); //100
//						}
//					}
//				}

                if (documentTime != null) { //批件时间
                    product.setProductStatus(MmsConstant.PRODUCT_STATUS_5);
                    product.setProductProcess(MmsConstant.PRODUCT_PROCESS_7); //100
                } else if (nextOpinionTime != null) {//下意见时间
                    product.setProductStatus(MmsConstant.PRODUCT_STATUS_4);
                    product.setProductProcess(MmsConstant.PRODUCT_PROCESS_4); //80
                } else if (reportTime != null) {//申报时间
                    product.setProductStatus(MmsConstant.PRODUCT_STATUS_3);
                    product.setProductProcess(MmsConstant.PRODUCT_PROCESS_3); //60
                }
            }
            productService.save(product);

            //送检总数的处理
            String administrativeLicenseInspectionNumber = declareProduct.getAdministrativeLicenseInspectionNumber();//行政许可送检数量
            if (StringUtils.isEmpty(administrativeLicenseInspectionNumber)) {
                administrativeLicenseInspectionNumber = "0";
            }
            String sendBodyNumber = declareProduct.getSendBodyNumber();//人体检验数量
            if (StringUtils.isEmpty(sendBodyNumber)) {
                sendBodyNumber = "0";
            }
            String sendRiskTestNumber = declareProduct.getSendRiskTestNumber();//风险检验数量
            if (StringUtils.isEmpty(sendRiskTestNumber)) {
                sendRiskTestNumber = "0";
            }
            declareProduct.setTotalSubmission(String.valueOf(Integer.valueOf(administrativeLicenseInspectionNumber) + Integer.valueOf(sendBodyNumber)
                    + Integer.valueOf(sendRiskTestNumber)));//送检总数

            declareProductService.save(declareProduct);
            addMessage(redirectAttributes, "保存申报产品成功");
        } else {
            addMessage(redirectAttributes, "产品信息有错误");
        }


        return "redirect:" + Global.getAdminPath() + "/mms/declareProduct/?repage";
    }

    @RequiresPermissions("mms:declareProduct:edit")
    @RequestMapping(value = "delete")
    public String delete(DeclareProduct declareProduct, RedirectAttributes redirectAttributes) {
        declareProductService.delete(declareProduct);
        addMessage(redirectAttributes, "删除申报产品成功");
        return "redirect:" + Global.getAdminPath() + "/mms/declareProduct/?repage";
    }

}