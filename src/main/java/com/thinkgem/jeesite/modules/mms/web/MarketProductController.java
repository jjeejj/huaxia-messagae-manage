/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.*;
import com.thinkgem.jeesite.modules.mms.service.*;
import com.thinkgem.jeesite.modules.mms.utils.MmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
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

import java.util.List;

/**
 * 市场产品Controller
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/marketProduct")
public class MarketProductController extends BaseController {

	@Autowired
	private MarketProductService marketProductService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ComprehensiveProductService comprehensiveProductService;

	@Autowired
	private DeclareProductService declareProductService;

	@Autowired
	private ProductFlowNumberService productFlowNumberService;
	
	@ModelAttribute
	public MarketProduct get(@RequestParam(required=false) String id) {
		MarketProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = marketProductService.get(id);
		}
		if (entity == null){
			entity = new MarketProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:marketProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
		model.addAttribute("page", page);
		return "modules/mms/marketProductList";
	}

	@RequiresPermissions("mms:marketProduct:view")
	@RequestMapping(value = "form")
	public String form(MarketProduct marketProduct, Model model) {
		model.addAttribute("marketProduct", marketProduct);

		return "modules/mms/marketProductForm";
	}

	@RequiresPermissions("mms:marketProduct:edit")
	@RequestMapping(value = "save")
	public String save(MarketProduct marketProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, marketProduct)){
			return form(marketProduct, model);
		}

		//获取当前登录用户.处理人ID
		User user  =UserUtils.getUser();
		marketProduct.setProductHandlePersonId(user.getId());

		marketProductService.save(marketProduct);

		//新建的产品，关联到产品汇总信息 中
		Product product = productService.getByMarketProductId(marketProduct.getId()); //产品信息
		Product	productSave = new Product();
		if(product == null){ //新建产品，第一次保存

			//自动创建综合产品记录和申报产品记录
			ComprehensiveProduct comprehensiveProduct = new ComprehensiveProduct();
			DeclareProduct declareProduct = new DeclareProduct();
			comprehensiveProductService.save(comprehensiveProduct);
			declareProductService.save(declareProduct);

			//产品状态判断
			if(marketProduct.getProjectTime() != null){ //立项时间有 就是初审状态，如果是新建，该时间是必填的
				productSave.setProductStatus(MmsConstant.PRODUCT_STATUS_1);
				productSave.setProductProcess(MmsConstant.PRODUCT_PROCESS_1); //产品进度
			}
			productSave.setMarketProductId(marketProduct.getId());
			productSave.setComprehensiveProductId(comprehensiveProduct.getId());
			productSave.setDeclareProductId(declareProduct.getId());
			productService.save(productSave);

			//如果是新建产品，生成处理产品编号，规则：在输入的产品编号的首位添加 四位年份+四位流水，20170001
			String productNumber = StringUtils.EMPTY;//产品编号
			String flowNumber = StringUtils.EMPTY;//流水号
			//流水编号记录
			ProductFlowNumber productFlowNumber = new ProductFlowNumber();
			//根据当前年 查找对应的最大的流水编号
			String nowYear = DateUtils.getYear();
			String bigNumber = productFlowNumberService.selectBigNumberByYear(nowYear);
			if(StringUtils.isEmpty(bigNumber)){ //该年没有记录，重0001 开始
				flowNumber = "0001";
				productNumber = nowYear + flowNumber;
			}else{
				flowNumber = MmsUtils.handleSerialNumber(bigNumber);
				productNumber = nowYear + flowNumber;
			}

			//保存该流水记录
			productFlowNumber.setFlowNumber(flowNumber);
			productFlowNumber.setFlowYear(nowYear);
			productFlowNumber.setProductId(productSave.getId());
			productFlowNumberService.save(productFlowNumber);

			//更新产品编号
			marketProduct.setProductNumber(productNumber+marketProduct.getProductNumber());
			marketProductService.save(marketProduct);

		}

		//更新产品负责人---保存代汇总产品表中
		if(StringUtils.isNoneEmpty(marketProduct.getProductLeader())){
			product.setProductLeader(marketProduct.getProductLeader());
			productService.save(product);
		}

		addMessage(redirectAttributes, "保存市场产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/marketProduct/?repage";
	}
	
	@RequiresPermissions("mms:marketProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(MarketProduct marketProduct, RedirectAttributes redirectAttributes) {
		//市场部 删除产品 则每个部门都要删除的，有关该产品的所有记录

		//找到该产品总记录
		Product product = productService.getByMarketProductId(marketProduct.getId());

		String comprehensiveProductId = product.getComprehensiveProductId();//综合产品id
		if(StringUtils.isNoneEmpty(comprehensiveProductId)){ //有综合产品记录
			comprehensiveProductService.delete(comprehensiveProductService.get(comprehensiveProductId));
		}
		String declareProductId = product.getDeclareProductId();//综合产品id
		if(StringUtils.isNoneEmpty(declareProductId)){ //综合产品记录
			declareProductService.delete(declareProductService.get(declareProductId));
		}
		productService.delete(product);
		marketProductService.delete(marketProduct);
		addMessage(redirectAttributes, "删除市场产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/marketProduct/?repage";
	}

}