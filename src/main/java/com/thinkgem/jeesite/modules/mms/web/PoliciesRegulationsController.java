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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mms.entity.PoliciesRegulations;
import com.thinkgem.jeesite.modules.mms.service.PoliciesRegulationsService;

/**
 * 政策法规数据库Controller
 * @author jiang
 * @version 2017-03-29
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/policiesRegulations")
public class PoliciesRegulationsController extends BaseController {

	@Autowired
	private PoliciesRegulationsService policiesRegulationsService;
	
	@ModelAttribute
	public PoliciesRegulations get(@RequestParam(required=false) String id) {
		PoliciesRegulations entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = policiesRegulationsService.get(id);
		}
		if (entity == null){
			entity = new PoliciesRegulations();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:policiesRegulations:view")
	@RequestMapping(value = {"list", ""})
	public String list(PoliciesRegulations policiesRegulations, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PoliciesRegulations> page = policiesRegulationsService.findPage(new Page<PoliciesRegulations>(request, response), policiesRegulations); 
		model.addAttribute("page", page);
		return "modules/mms/policiesRegulationsList";
	}

	@RequiresPermissions("mms:policiesRegulations:view")
	@RequestMapping(value = "form")
	public String form(PoliciesRegulations policiesRegulations, Model model) {
		model.addAttribute("policiesRegulations", policiesRegulations);
		return "modules/mms/policiesRegulationsForm";
	}

	@RequiresPermissions("mms:policiesRegulations:edit")
	@RequestMapping(value = "save")
	public String save(PoliciesRegulations policiesRegulations, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, policiesRegulations)){
			return form(policiesRegulations, model);
		}
		policiesRegulationsService.save(policiesRegulations);
		addMessage(redirectAttributes, "保存政策法规数据库成功");
		return "redirect:"+Global.getAdminPath()+"/mms/policiesRegulations/?repage";
	}
	
	@RequiresPermissions("mms:policiesRegulations:edit")
	@RequestMapping(value = "delete")
	public String delete(PoliciesRegulations policiesRegulations, RedirectAttributes redirectAttributes) {
		policiesRegulationsService.delete(policiesRegulations);
		addMessage(redirectAttributes, "删除政策法规数据库成功");
		return "redirect:"+Global.getAdminPath()+"/mms/policiesRegulations/?repage";
	}

}