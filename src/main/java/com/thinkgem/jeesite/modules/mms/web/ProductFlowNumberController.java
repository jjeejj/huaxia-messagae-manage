/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.thinkgem.jeesite.modules.mms.entity.ProductFlowNumber;
import com.thinkgem.jeesite.modules.mms.service.ProductFlowNumberService;

/**
 * 产品流水编号Controller
 * @author jiang
 * @version 2017-03-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/productFlowNumber")
public class ProductFlowNumberController extends BaseController {

	@Autowired
	private ProductFlowNumberService productFlowNumberService;
	
	@ModelAttribute
	public ProductFlowNumber get(@RequestParam(required=false) String id) {
		ProductFlowNumber entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productFlowNumberService.get(id);
		}
		if (entity == null){
			entity = new ProductFlowNumber();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:productFlowNumber:view")
	@RequestMapping(value = {"list", ""})
	public String list(ProductFlowNumber productFlowNumber, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ProductFlowNumber> page = productFlowNumberService.findPage(new Page<ProductFlowNumber>(request, response), productFlowNumber); 
		model.addAttribute("page", page);
		return "modules/mms/productFlowNumberList";
	}

	@RequiresPermissions("mms:productFlowNumber:view")
	@RequestMapping(value = "form")
	public String form(ProductFlowNumber productFlowNumber, Model model) {
		model.addAttribute("productFlowNumber", productFlowNumber);
		return "modules/mms/productFlowNumberForm";
	}

	@RequiresPermissions("mms:productFlowNumber:edit")
	@RequestMapping(value = "save")
	public String save(ProductFlowNumber productFlowNumber, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, productFlowNumber)){
			return form(productFlowNumber, model);
		}
		productFlowNumberService.save(productFlowNumber);
		addMessage(redirectAttributes, "保存产品流水编号成功");
		return "redirect:"+Global.getAdminPath()+"/mms/productFlowNumber/?repage";
	}
	
	@RequiresPermissions("mms:productFlowNumber:edit")
	@RequestMapping(value = "delete")
	public String delete(ProductFlowNumber productFlowNumber, RedirectAttributes redirectAttributes) {
		productFlowNumberService.delete(productFlowNumber);
		addMessage(redirectAttributes, "删除产品流水编号成功");
		return "redirect:"+Global.getAdminPath()+"/mms/productFlowNumber/?repage";
	}

}