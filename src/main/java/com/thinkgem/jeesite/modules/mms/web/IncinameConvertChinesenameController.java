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
import com.thinkgem.jeesite.modules.mms.entity.IncinameConvertChinesename;
import com.thinkgem.jeesite.modules.mms.service.IncinameConvertChinesenameService;

/**
 * inci名与标准中文名相互转换Controller
 * @author jiang
 * @version 2017-04-05
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/incinameConvertChinesename")
public class IncinameConvertChinesenameController extends BaseController {

	@Autowired
	private IncinameConvertChinesenameService incinameConvertChinesenameService;
	
	@ModelAttribute
	public IncinameConvertChinesename get(@RequestParam(required=false) String id) {
		IncinameConvertChinesename entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = incinameConvertChinesenameService.get(id);
		}
		if (entity == null){
			entity = new IncinameConvertChinesename();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:incinameConvertChinesename:view")
	@RequestMapping(value = {"list", ""})
	public String list(IncinameConvertChinesename incinameConvertChinesename, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<IncinameConvertChinesename> page = incinameConvertChinesenameService.findPage(new Page<IncinameConvertChinesename>(request, response), incinameConvertChinesename); 
		model.addAttribute("page", page);
		return "modules/mms/incinameConvertChinesenameList";
	}

	@RequiresPermissions("mms:incinameConvertChinesename:view")
	@RequestMapping(value = "form")
	public String form(IncinameConvertChinesename incinameConvertChinesename, Model model) {
		model.addAttribute("incinameConvertChinesename", incinameConvertChinesename);
		return "modules/mms/incinameConvertChinesenameForm";
	}

	@RequiresPermissions("mms:incinameConvertChinesename:edit")
	@RequestMapping(value = "save")
	public String save(IncinameConvertChinesename incinameConvertChinesename, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, incinameConvertChinesename)){
			return form(incinameConvertChinesename, model);
		}
		incinameConvertChinesenameService.save(incinameConvertChinesename);
		addMessage(redirectAttributes, "保存inci名与标准中文名相互转换成功");
		return "redirect:"+Global.getAdminPath()+"/mms/incinameConvertChinesename/?repage";
	}
	
	@RequiresPermissions("mms:incinameConvertChinesename:edit")
	@RequestMapping(value = "delete")
	public String delete(IncinameConvertChinesename incinameConvertChinesename, RedirectAttributes redirectAttributes) {
		incinameConvertChinesenameService.delete(incinameConvertChinesename);
		addMessage(redirectAttributes, "删除inci名与标准中文名相互转换成功");
		return "redirect:"+Global.getAdminPath()+"/mms/incinameConvertChinesename/?repage";
	}

}