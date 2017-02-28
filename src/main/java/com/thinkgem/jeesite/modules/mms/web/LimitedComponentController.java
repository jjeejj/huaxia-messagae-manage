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
import com.thinkgem.jeesite.modules.mms.entity.LimitedComponent;
import com.thinkgem.jeesite.modules.mms.service.LimitedComponentService;

/**
 * 化妆品安全技术规范的限用成分Controller
 * @author jiang
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/limitedComponent")
public class LimitedComponentController extends BaseController {

	@Autowired
	private LimitedComponentService limitedComponentService;
	
	@ModelAttribute
	public LimitedComponent get(@RequestParam(required=false) String id) {
		LimitedComponent entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = limitedComponentService.get(id);
		}
		if (entity == null){
			entity = new LimitedComponent();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:limitedComponent:view")
	@RequestMapping(value = {"list", ""})
	public String list(LimitedComponent limitedComponent, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<LimitedComponent> page = limitedComponentService.findPage(new Page<LimitedComponent>(request, response), limitedComponent); 
		model.addAttribute("page", page);
		return "modules/mms/limitedComponentList";
	}

	@RequiresPermissions("mms:limitedComponent:view")
	@RequestMapping(value = "form")
	public String form(LimitedComponent limitedComponent, Model model) {
		model.addAttribute("limitedComponent", limitedComponent);
		return "modules/mms/limitedComponentForm";
	}

	@RequiresPermissions("mms:limitedComponent:edit")
	@RequestMapping(value = "save")
	public String save(LimitedComponent limitedComponent, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, limitedComponent)){
			return form(limitedComponent, model);
		}
		limitedComponentService.save(limitedComponent);
		addMessage(redirectAttributes, "保存化妆品安全技术规范的限用成分成功");
		return "redirect:"+Global.getAdminPath()+"/mms/limitedComponent/?repage";
	}
	
	@RequiresPermissions("mms:limitedComponent:edit")
	@RequestMapping(value = "delete")
	public String delete(LimitedComponent limitedComponent, RedirectAttributes redirectAttributes) {
		limitedComponentService.delete(limitedComponent);
		addMessage(redirectAttributes, "删除化妆品安全技术规范的限用成分成功");
		return "redirect:"+Global.getAdminPath()+"/mms/limitedComponent/?repage";
	}

}