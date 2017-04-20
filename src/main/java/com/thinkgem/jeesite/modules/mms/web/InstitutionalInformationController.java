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
import com.thinkgem.jeesite.modules.mms.entity.InstitutionalInformation;
import com.thinkgem.jeesite.modules.mms.service.InstitutionalInformationService;

/**
 * 机构信息Controller
 * @author jiang
 * @version 2017-04-20
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/institutionalInformation")
public class InstitutionalInformationController extends BaseController {

	@Autowired
	private InstitutionalInformationService institutionalInformationService;
	
	@ModelAttribute
	public InstitutionalInformation get(@RequestParam(required=false) String id) {
		InstitutionalInformation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = institutionalInformationService.get(id);
		}
		if (entity == null){
			entity = new InstitutionalInformation();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:institutionalInformation:view")
	@RequestMapping(value = {"list", ""})
	public String list(InstitutionalInformation institutionalInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<InstitutionalInformation> page = institutionalInformationService.findPage(new Page<InstitutionalInformation>(request, response), institutionalInformation); 
		model.addAttribute("page", page);
		return "modules/mms/institutionalInformationList";
	}

	@RequiresPermissions("mms:institutionalInformation:view")
	@RequestMapping(value = "form")
	public String form(InstitutionalInformation institutionalInformation, Model model) {
		model.addAttribute("institutionalInformation", institutionalInformation);
		return "modules/mms/institutionalInformationForm";
	}

	@RequiresPermissions("mms:institutionalInformation:edit")
	@RequestMapping(value = "save")
	public String save(InstitutionalInformation institutionalInformation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, institutionalInformation)){
			return form(institutionalInformation, model);
		}
		institutionalInformationService.save(institutionalInformation);
		addMessage(redirectAttributes, "保存机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/institutionalInformation/?repage";
	}
	
	@RequiresPermissions("mms:institutionalInformation:edit")
	@RequestMapping(value = "delete")
	public String delete(InstitutionalInformation institutionalInformation, RedirectAttributes redirectAttributes) {
		institutionalInformationService.delete(institutionalInformation);
		addMessage(redirectAttributes, "删除机构信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/institutionalInformation/?repage";
	}

}