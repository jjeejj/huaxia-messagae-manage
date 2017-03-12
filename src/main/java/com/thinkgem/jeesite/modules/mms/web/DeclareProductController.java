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
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;

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
		declareProductService.save(declareProduct);
		addMessage(redirectAttributes, "保存申报产品成功");
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