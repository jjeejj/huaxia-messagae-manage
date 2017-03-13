/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.ComprehensiveProduct;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.ComprehensiveProductService;
import com.thinkgem.jeesite.modules.mms.service.ProductService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
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
import com.thinkgem.jeesite.modules.mms.entity.MarketProduct;
import com.thinkgem.jeesite.modules.mms.service.MarketProductService;

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
	private SystemService systemService;

	@Autowired
	private ComprehensiveProductService comprehensiveProductService;
	
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
	public String list(MarketProduct marketProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MarketProduct> page = marketProductService.findPage(new Page<MarketProduct>(request, response), marketProduct); 
		model.addAttribute("page", page);
		return "modules/mms/marketProductList";
	}

	@RequiresPermissions("mms:marketProduct:view")
	@RequestMapping(value = "form")
	public String form(MarketProduct marketProduct, Model model) {
		model.addAttribute("marketProduct", marketProduct);

		//查询下发综合部的所有人员
		List<User> userList =systemService.findUserByOfficeId("3");

		model.addAttribute("userList", userList);
		return "modules/mms/marketProductForm";
	}

	@RequiresPermissions("mms:marketProduct:edit")
	@RequestMapping(value = "save")
	public String save(MarketProduct marketProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, marketProduct)){
			return form(marketProduct, model);
		}

		//获取当前登录用户
		User user  =UserUtils.getUser();
		marketProduct.setProductHandlePersonId(user.getId());
		marketProductService.save(marketProduct);

		//新建的产品，关联到产品汇总信息 中
		Product product = productService.getByMarketProductId(marketProduct.getId());
		Product	productSave = new Product();
		if(product == null){ //第一次
			if(StringUtils.isNoneEmpty(marketProduct.getProductNextHandlePersonId())) { // 有分配人
				ComprehensiveProduct comprehensiveProduct = new ComprehensiveProduct();
				comprehensiveProduct.setProductHandlePersonId(marketProduct.getProductNextHandlePersonId());

				comprehensiveProductService.save(comprehensiveProduct);
				productSave.setComprehensiveProductId(comprehensiveProduct.getId());
			}
			//产品状态判断
			if(marketProduct.getProjectTime() != null){ //立项时间有 就是初审状态
				productSave.setProductStatus(MmsConstant.PRODUCT_STATUS_1);
			}
			productSave.setMarketProductId(marketProduct.getId());
			productService.save(productSave);
		}else{
			if(StringUtils.isEmpty(product.getComprehensiveProductId())){ //是否有对应的综合产品id ，如果有 就已经分配 ，没有及时没有分配
				if(StringUtils.isNoneEmpty(marketProduct.getProductNextHandlePersonId())){ // 有分配人
					ComprehensiveProduct comprehensiveProduct = new ComprehensiveProduct();
					comprehensiveProduct.setProductHandlePersonId(marketProduct.getProductNextHandlePersonId());

					comprehensiveProductService.save(comprehensiveProduct);

					product.setComprehensiveProductId(comprehensiveProduct.getId());
					productService.save(product);
				}
			}
		}
		addMessage(redirectAttributes, "保存市场产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/marketProduct/?repage";
	}
	
	@RequiresPermissions("mms:marketProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(MarketProduct marketProduct, RedirectAttributes redirectAttributes) {
		marketProductService.delete(marketProduct);
		addMessage(redirectAttributes, "删除市场产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/marketProduct/?repage";
	}

}