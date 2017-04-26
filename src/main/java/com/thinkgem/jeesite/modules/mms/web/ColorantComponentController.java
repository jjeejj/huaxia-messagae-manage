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
import com.thinkgem.jeesite.modules.mms.entity.ColorantComponent;
import com.thinkgem.jeesite.modules.mms.service.ColorantComponentService;

/**
 * 着色剂Controller
 * @author jiang
 * @version 2017-04-26
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/colorantComponent")
public class ColorantComponentController extends BaseController {

	@Autowired
	private ColorantComponentService colorantComponentService;
	
	@ModelAttribute
	public ColorantComponent get(@RequestParam(required=false) String id) {
		ColorantComponent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = colorantComponentService.get(id);
		}
		if (entity == null){
			entity = new ColorantComponent();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:colorantComponent:view")
	@RequestMapping(value = {"list", ""})
	public String list(ColorantComponent colorantComponent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ColorantComponent> page = colorantComponentService.findPage(new Page<ColorantComponent>(request, response), colorantComponent); 
		model.addAttribute("page", page);
		return "modules/mms/colorantComponentList";
	}

	@RequiresPermissions("mms:colorantComponent:view")
	@RequestMapping(value = "form")
	public String form(ColorantComponent colorantComponent, Model model) {
		model.addAttribute("colorantComponent", colorantComponent);
		return "modules/mms/colorantComponentForm";
	}

	@RequiresPermissions("mms:colorantComponent:edit")
	@RequestMapping(value = "save")
	public String save(ColorantComponent colorantComponent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, colorantComponent)){
			return form(colorantComponent, model);
		}
		colorantComponentService.save(colorantComponent);
		addMessage(redirectAttributes, "保存着色剂成功");
		return "redirect:"+Global.getAdminPath()+"/mms/colorantComponent/?repage";
	}
	
	@RequiresPermissions("mms:colorantComponent:edit")
	@RequestMapping(value = "delete")
	public String delete(ColorantComponent colorantComponent, RedirectAttributes redirectAttributes) {
		colorantComponentService.delete(colorantComponent);
		addMessage(redirectAttributes, "删除着色剂成功");
		return "redirect:"+Global.getAdminPath()+"/mms/colorantComponent/?repage";
	}

}