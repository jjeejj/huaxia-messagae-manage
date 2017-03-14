/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.entity.ComprehensiveProduct;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.entity.MarketProduct;
import com.thinkgem.jeesite.modules.mms.service.ComprehensiveProductService;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;
import com.thinkgem.jeesite.modules.mms.service.MarketProductService;
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
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.ProductService;

/**
 * 产品Controller
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private MarketProductService marketProductService;

	@Autowired
	private ComprehensiveProductService comprehensiveProductService;

	@Autowired
	private DeclareProductService declareProductService;
	
	@ModelAttribute
	public Product get(@RequestParam(required=false) String id) {
		Product entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productService.get(id);
		}
		if (entity == null){
			entity = new Product();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product); 
		model.addAttribute("page", page);
		return "modules/mms/productList";
	}

	@RequiresPermissions("mms:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {
		//根据每个部门的产品id ，查询出具体的信息

		String marketProductId = product.getMarketProductId();//市场产品id
		String comprehensiveProductId = product.getComprehensiveProductId();//综合产品id
		String declareProductId = product.getDeclareProductId();//申报产品id

		MarketProduct marketProduct = marketProductService.get(marketProductId);
		ComprehensiveProduct comprehensiveProduct = comprehensiveProductService.get(comprehensiveProductId);
		DeclareProduct declareProduct = declareProductService.get(declareProductId);



		model.addAttribute("product", product);
		model.addAttribute("marketProduct", marketProduct);
		model.addAttribute("comprehensiveProduct", comprehensiveProduct);
		model.addAttribute("declareProduct", declareProduct);


		return "modules/mms/productForm";
	}

	@RequiresPermissions("mms:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, product)){
			return form(product, model);
		}
		productService.save(product);
		addMessage(redirectAttributes, "保存产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/product/?repage";
	}
	
	@RequiresPermissions("mms:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Product product, RedirectAttributes redirectAttributes) {
		productService.delete(product);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/product/?repage";
	}

}