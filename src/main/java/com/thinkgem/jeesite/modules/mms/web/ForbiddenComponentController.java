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
import com.thinkgem.jeesite.modules.mms.entity.ForbiddenComponent;
import com.thinkgem.jeesite.modules.mms.service.ForbiddenComponentService;

/**
 * 化妆品安全技术规范的禁用成分Controller
 * @author jiang
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/forbiddenComponent")
public class ForbiddenComponentController extends BaseController {

	@Autowired
	private ForbiddenComponentService forbiddenComponentService;
	
	@ModelAttribute
	public ForbiddenComponent get(@RequestParam(required=false) String id) {
		ForbiddenComponent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = forbiddenComponentService.get(id);
		}
		if (entity == null){
			entity = new ForbiddenComponent();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:forbiddenComponent:view")
	@RequestMapping(value = {"list", ""})
	public String list(ForbiddenComponent forbiddenComponent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ForbiddenComponent> page = forbiddenComponentService.findPage(new Page<ForbiddenComponent>(request, response), forbiddenComponent); 
		model.addAttribute("page", page);
		return "modules/mms/forbiddenComponentList";
	}

	@RequiresPermissions("mms:forbiddenComponent:view")
	@RequestMapping(value = "form")
	public String form(ForbiddenComponent forbiddenComponent, Model model) {
		model.addAttribute("forbiddenComponent", forbiddenComponent);
		return "modules/mms/forbiddenComponentForm";
	}

	@RequiresPermissions("mms:forbiddenComponent:edit")
	@RequestMapping(value = "save")
	public String save(ForbiddenComponent forbiddenComponent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, forbiddenComponent)){
			return form(forbiddenComponent, model);
		}
		forbiddenComponentService.save(forbiddenComponent);
		addMessage(redirectAttributes, "保存化妆品安全技术规范的禁用成分成功");
		return "redirect:"+Global.getAdminPath()+"/mms/forbiddenComponent/?repage";
	}
	
	@RequiresPermissions("mms:forbiddenComponent:edit")
	@RequestMapping(value = "delete")
	public String delete(ForbiddenComponent forbiddenComponent, RedirectAttributes redirectAttributes) {
		forbiddenComponentService.delete(forbiddenComponent);
		addMessage(redirectAttributes, "删除化妆品安全技术规范的禁用成分成功");
		return "redirect:"+Global.getAdminPath()+"/mms/forbiddenComponent/?repage";
	}

}