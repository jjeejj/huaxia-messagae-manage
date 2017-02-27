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
import com.thinkgem.jeesite.modules.mms.entity.FormulaDetails;
import com.thinkgem.jeesite.modules.mms.service.FormulaDetailsService;

/**
 * 配方详情信息Controller
 * @author jiang
 * @version 2017-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/formulaDetails")
public class FormulaDetailsController extends BaseController {

	@Autowired
	private FormulaDetailsService formulaDetailsService;
	
	@ModelAttribute
	public FormulaDetails get(@RequestParam(required=false) String id) {
		FormulaDetails entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = formulaDetailsService.get(id);
		}
		if (entity == null){
			entity = new FormulaDetails();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:formulaDetails:view")
	@RequestMapping(value = {"list", ""})
	public String list(FormulaDetails formulaDetails, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<FormulaDetails> page = formulaDetailsService.findPage(new Page<FormulaDetails>(request, response), formulaDetails); 
		model.addAttribute("page", page);
		return "modules/mms/formulaDetailsList";
	}

	@RequiresPermissions("mms:formulaDetails:view")
	@RequestMapping(value = "form")
	public String form(FormulaDetails formulaDetails, Model model) {
		model.addAttribute("formulaDetails", formulaDetails);
		return "modules/mms/formulaDetailsForm";
	}

	@RequiresPermissions("mms:formulaDetails:edit")
	@RequestMapping(value = "save")
	public String save(FormulaDetails formulaDetails, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, formulaDetails)){
			return form(formulaDetails, model);
		}
		formulaDetailsService.save(formulaDetails);
		addMessage(redirectAttributes, "保存配方详情信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/formulaDetails/?repage";
	}
	
	@RequiresPermissions("mms:formulaDetails:edit")
	@RequestMapping(value = "delete")
	public String delete(FormulaDetails formulaDetails, RedirectAttributes redirectAttributes) {
		formulaDetailsService.delete(formulaDetails);
		addMessage(redirectAttributes, "删除配方详情信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/formulaDetails/?repage";
	}

}