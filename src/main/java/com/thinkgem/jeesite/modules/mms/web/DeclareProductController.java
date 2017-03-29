/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;
import com.thinkgem.jeesite.modules.mms.service.ProductService;
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
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/declareProduct")
public class DeclareProductController extends BaseController {

	@Autowired
	private DeclareProductService declareProductService;

	@Autowired
	private ProductService productService;
	
	@ModelAttribute
	public DeclareProduct get(@RequestParam(required=false) String id) {
		DeclareProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = declareProductService.get(id);
		}
		if (entity == null){
			entity = new DeclareProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:declareProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(DeclareProduct declareProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DeclareProduct> page = declareProductService.findPage(new Page<DeclareProduct>(request, response), declareProduct); 
		model.addAttribute("page", page);
		return "modules/mms/declareProductList";
	}

	@RequiresPermissions("mms:declareProduct:view")
	@RequestMapping(value = "form")
	public String form(DeclareProduct declareProduct, Model model) {
		model.addAttribute("declareProduct", declareProduct);
		return "modules/mms/declareProductForm";
	}

	@RequiresPermissions("mms:declareProduct:edit")
	@RequestMapping(value = "save")
	public String save(DeclareProduct declareProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, declareProduct)){
			return form(declareProduct, model);
		}

		//查找对应的产品汇总及记录
		Product product = productService.getByDeclareProductId(declareProduct.getId());
		if(product != null){
			//保存时判断产品状态
			/**
			 * 终止申报 -- 7----->	备注有“终止申报”字段
			 * 不予批准 ------6--------->	立项时间、来样时间、上报、下意见、批件五项有日期数据且备注有“不予批准”字段
			 * 取得批件	--------5---------->立项时间、来样时间、上报、下意见、批件五项有日期数据
			 * 完善资料	-----4--------> 立项时间、来样时间、上报、下意见四项有日期数据
			 * 申报	---------3-------->立项时间、来样时间、上报三项有日期数据
			 */

			Date reportTime = declareProduct.getReportTime();//上报时间
			Date nextOpinionTime = declareProduct.getNextOpinionTime(); //下意见时间
			Date documentTime = declareProduct.getDocumentTime();//批件时间
			String remarks =  declareProduct.getRemarks();//备注

			if(remarks.contains("终止申报")){ //这个优先
				product.setProductStatus(MmsConstant.PRODUCT_STATUS_7);
			}else{
				if(reportTime!=null){ //上报时间
					product.setProductStatus(MmsConstant.PRODUCT_STATUS_3);

					if(nextOpinionTime!=null){ //下意见时间
						product.setProductStatus(MmsConstant.PRODUCT_STATUS_4);

						if(documentTime !=null){ //批件时间
							product.setProductStatus(MmsConstant.PRODUCT_STATUS_5);

							if(remarks.contains("不予批准")){
								product.setProductStatus(MmsConstant.PRODUCT_STATUS_6);
							}
						}
					}
				}
			}
			productService.save(product);

			declareProductService.save(declareProduct);
			addMessage(redirectAttributes, "保存申报产品成功");
		}else{
			addMessage(redirectAttributes, "产品信息有错误");
		}


		return "redirect:"+Global.getAdminPath()+"/mms/declareProduct/?repage";
	}
	
	@RequiresPermissions("mms:declareProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(DeclareProduct declareProduct, RedirectAttributes redirectAttributes) {
		declareProductService.delete(declareProduct);
		addMessage(redirectAttributes, "删除申报产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/declareProduct/?repage";
	}

}