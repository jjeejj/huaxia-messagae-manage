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
import com.thinkgem.jeesite.modules.mms.entity.NameToRiskMaterial;
import com.thinkgem.jeesite.modules.mms.service.NameToRiskMaterialService;

/**
 * 标准中文名对应的风险物质Controller
 * @author jiang
 * @version 2017-02-26
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/nameToRiskMaterial")
public class NameToRiskMaterialController extends BaseController {

	@Autowired
	private NameToRiskMaterialService nameToRiskMaterialService;
	
	@ModelAttribute
	public NameToRiskMaterial get(@RequestParam(required=false) String id) {
		NameToRiskMaterial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nameToRiskMaterialService.get(id);
		}
		if (entity == null){
			entity = new NameToRiskMaterial();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:nameToRiskMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(NameToRiskMaterial nameToRiskMaterial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NameToRiskMaterial> page = nameToRiskMaterialService.findPage(new Page<NameToRiskMaterial>(request, response), nameToRiskMaterial); 
		model.addAttribute("page", page);
		return "modules/mms/nameToRiskMaterialList";
	}

	@RequiresPermissions("mms:nameToRiskMaterial:view")
	@RequestMapping(value = "form")
	public String form(NameToRiskMaterial nameToRiskMaterial, Model model) {
		model.addAttribute("nameToRiskMaterial", nameToRiskMaterial);
		return "modules/mms/nameToRiskMaterialForm";
	}

	@RequiresPermissions("mms:nameToRiskMaterial:edit")
	@RequestMapping(value = "save")
	public String save(NameToRiskMaterial nameToRiskMaterial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nameToRiskMaterial)){
			return form(nameToRiskMaterial, model);
		}
		nameToRiskMaterialService.save(nameToRiskMaterial);
		addMessage(redirectAttributes, "保存标准中文名对应的风险物质成功");
		return "redirect:"+Global.getAdminPath()+"/mms/nameToRiskMaterial/?repage";
	}
	
	@RequiresPermissions("mms:nameToRiskMaterial:edit")
	@RequestMapping(value = "delete")
	public String delete(NameToRiskMaterial nameToRiskMaterial, RedirectAttributes redirectAttributes) {
		nameToRiskMaterialService.delete(nameToRiskMaterial);
		addMessage(redirectAttributes, "删除标准中文名对应的风险物质成功");
		return "redirect:"+Global.getAdminPath()+"/mms/nameToRiskMaterial/?repage";
	}

}