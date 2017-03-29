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
import com.thinkgem.jeesite.modules.mms.entity.MaterialUse;
import com.thinkgem.jeesite.modules.mms.service.MaterialUseService;

/**
 * 原料使用数据库Controller
 * @author jiang
 * @version 2017-03-27
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/materialUse")
public class MaterialUseController extends BaseController {

	@Autowired
	private MaterialUseService materialUseService;
	
	@ModelAttribute
	public MaterialUse get(@RequestParam(required=false) String id) {
		MaterialUse entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialUseService.get(id);
		}
		if (entity == null){
			entity = new MaterialUse();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:materialUse:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialUse materialUse, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaterialUse> page = materialUseService.findPage(new Page<MaterialUse>(request, response), materialUse); 
		model.addAttribute("page", page);
		return "modules/mms/materialUseList";
	}

	@RequiresPermissions("mms:materialUse:view")
	@RequestMapping(value = "form")
	public String form(MaterialUse materialUse, Model model) {
		model.addAttribute("materialUse", materialUse);
		return "modules/mms/materialUseForm";
	}

	@RequiresPermissions("mms:materialUse:edit")
	@RequestMapping(value = "save")
	public String save(MaterialUse materialUse, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, materialUse)){
			return form(materialUse, model);
		}
		materialUseService.save(materialUse);
		addMessage(redirectAttributes, "保存原料使用数据库成功");
		return "redirect:"+Global.getAdminPath()+"/mms/materialUse/?repage";
	}
	
	@RequiresPermissions("mms:materialUse:edit")
	@RequestMapping(value = "delete")
	public String delete(MaterialUse materialUse, RedirectAttributes redirectAttributes) {
		materialUseService.delete(materialUse);
		addMessage(redirectAttributes, "删除原料使用数据库成功");
		return "redirect:"+Global.getAdminPath()+"/mms/materialUse/?repage";
	}

}