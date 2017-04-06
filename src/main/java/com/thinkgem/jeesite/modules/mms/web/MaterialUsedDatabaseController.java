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
import com.thinkgem.jeesite.modules.mms.entity.MaterialUsedDatabase;
import com.thinkgem.jeesite.modules.mms.service.MaterialUsedDatabaseService;

/**
 * 原料使用数据库Controller
 * @author jiang
 * @version 2017-04-06
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/materialUsedDatabase")
public class MaterialUsedDatabaseController extends BaseController {

	@Autowired
	private MaterialUsedDatabaseService materialUsedDatabaseService;
	
	@ModelAttribute
	public MaterialUsedDatabase get(@RequestParam(required=false) String id) {
		MaterialUsedDatabase entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = materialUsedDatabaseService.get(id);
		}
		if (entity == null){
			entity = new MaterialUsedDatabase();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:materialUsedDatabase:view")
	@RequestMapping(value = {"list", ""})
	public String list(MaterialUsedDatabase materialUsedDatabase, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<MaterialUsedDatabase> page = materialUsedDatabaseService.findPage(new Page<MaterialUsedDatabase>(request, response), materialUsedDatabase); 
		model.addAttribute("page", page);
		return "modules/mms/materialUsedDatabaseList";
	}

	@RequiresPermissions("mms:materialUsedDatabase:view")
	@RequestMapping(value = "form")
	public String form(MaterialUsedDatabase materialUsedDatabase, Model model) {
		model.addAttribute("materialUsedDatabase", materialUsedDatabase);
		return "modules/mms/materialUsedDatabaseForm";
	}

	@RequiresPermissions("mms:materialUsedDatabase:edit")
	@RequestMapping(value = "save")
	public String save(MaterialUsedDatabase materialUsedDatabase, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, materialUsedDatabase)){
			return form(materialUsedDatabase, model);
		}
		materialUsedDatabaseService.save(materialUsedDatabase);
		addMessage(redirectAttributes, "保存原料使用数据库成功");
		return "redirect:"+Global.getAdminPath()+"/mms/materialUsedDatabase/?repage";
	}
	
	@RequiresPermissions("mms:materialUsedDatabase:edit")
	@RequestMapping(value = "delete")
	public String delete(MaterialUsedDatabase materialUsedDatabase, RedirectAttributes redirectAttributes) {
		materialUsedDatabaseService.delete(materialUsedDatabase);
		addMessage(redirectAttributes, "删除原料使用数据库成功");
		return "redirect:"+Global.getAdminPath()+"/mms/materialUsedDatabase/?repage";
	}

}