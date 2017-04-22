/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
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
import com.thinkgem.jeesite.modules.mms.entity.EnterpriseInformation;
import com.thinkgem.jeesite.modules.mms.service.EnterpriseInformationService;

/**
 * 企业信息Controller
 * @author jiang
 * @version 2017-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/enterpriseInformation")
public class EnterpriseInformationController extends BaseController {

	@Autowired
	private EnterpriseInformationService enterpriseInformationService;
	
	@ModelAttribute
	public EnterpriseInformation get(@RequestParam(required=false) String id) {
		EnterpriseInformation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = enterpriseInformationService.get(id);
		}
		if (entity == null){
			entity = new EnterpriseInformation();
		}
		return entity;
	}
	
//	@RequiresPermissions("mms:enterpriseInformation:view")
	@RequestMapping(value = {"list", ""})
	public String list(EnterpriseInformation enterpriseInformation, HttpServletRequest request, HttpServletResponse response, Model model) {
		//获取要查询的企业类型
		String enterpriseType = StringUtils.isNoneEmpty(enterpriseInformation.getEnterpriseType())? enterpriseInformation.getEnterpriseType(): request.getParameter("enterpriseType");
		if(StringUtils.isNoneEmpty(enterpriseType)){
//			enterpriseType = MmsConstant.APPLY_ENTERPRISE_TYPE ; //默认是申请企业
			enterpriseInformation.setEnterpriseType(enterpriseType);
		}
		Page<EnterpriseInformation> page = enterpriseInformationService.findPage(new Page<EnterpriseInformation>(request, response), enterpriseInformation); 
		model.addAttribute("page", page);
		model.addAttribute("enterpriseType", enterpriseType);

		if(enterpriseType.equals(MmsConstant.APPLY_ENTERPRISE_TYPE)) {
			return "modules/mms/applyEnterpriseInformationList";
		}
		if(enterpriseType.equals(MmsConstant.ACTUAL_ENTERPRISE_TYPE)) {
			return "modules/mms/actualEnterpriseInformationList";
		}
		if(enterpriseType.equals(MmsConstant.CHINA_ENTERPRISE_TYPE)) {
			return "modules/mms/chinaEnterpriseInformationList";
		}

		return "modules/mms/enterpriseInformationList";
	}

//	@RequiresPermissions("mms:enterpriseInformation:view")
	@RequestMapping(value = "form")
	public String form(EnterpriseInformation enterpriseInformation, Model model) {
		model.addAttribute("enterpriseInformation", enterpriseInformation);
		return "modules/mms/enterpriseInformationForm";
	}

//	@RequiresPermissions("mms:enterpriseInformation:edit")
	@RequestMapping(value = "save")
	public String save(EnterpriseInformation enterpriseInformation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, enterpriseInformation)){
			return form(enterpriseInformation, model);
		}
		enterpriseInformationService.save(enterpriseInformation);
		addMessage(redirectAttributes, "保存企业信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/enterpriseInformation/?repage";
	}
	
//	@RequiresPermissions("mms:enterpriseInformation:edit")
	@RequestMapping(value = "delete")
	public String delete(EnterpriseInformation enterpriseInformation, RedirectAttributes redirectAttributes) {
		enterpriseInformationService.delete(enterpriseInformation);
		addMessage(redirectAttributes, "删除企业信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/enterpriseInformation/?repage";
	}

}