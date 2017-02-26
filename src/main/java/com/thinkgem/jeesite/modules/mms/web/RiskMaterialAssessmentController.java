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
import com.thinkgem.jeesite.modules.mms.entity.RiskMaterialAssessment;
import com.thinkgem.jeesite.modules.mms.service.RiskMaterialAssessmentService;

/**
 * 风险物质评估信息Controller
 * @author jiang
 * @version 2017-02-26
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/riskMaterialAssessment")
public class RiskMaterialAssessmentController extends BaseController {

	@Autowired
	private RiskMaterialAssessmentService riskMaterialAssessmentService;
	
	@ModelAttribute
	public RiskMaterialAssessment get(@RequestParam(required=false) String id) {
		RiskMaterialAssessment entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = riskMaterialAssessmentService.get(id);
		}
		if (entity == null){
			entity = new RiskMaterialAssessment();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:riskMaterialAssessment:view")
	@RequestMapping(value = {"list", ""})
	public String list(RiskMaterialAssessment riskMaterialAssessment, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RiskMaterialAssessment> page = riskMaterialAssessmentService.findPage(new Page<RiskMaterialAssessment>(request, response), riskMaterialAssessment); 
		model.addAttribute("page", page);
		return "modules/mms/riskMaterialAssessmentList";
	}

	@RequiresPermissions("mms:riskMaterialAssessment:view")
	@RequestMapping(value = "form")
	public String form(RiskMaterialAssessment riskMaterialAssessment, Model model) {
		model.addAttribute("riskMaterialAssessment", riskMaterialAssessment);
		return "modules/mms/riskMaterialAssessmentForm";
	}

	@RequiresPermissions("mms:riskMaterialAssessment:edit")
	@RequestMapping(value = "save")
	public String save(RiskMaterialAssessment riskMaterialAssessment, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, riskMaterialAssessment)){
			return form(riskMaterialAssessment, model);
		}
		riskMaterialAssessmentService.save(riskMaterialAssessment);
		addMessage(redirectAttributes, "保存风险物质评估信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/riskMaterialAssessment/?repage";
	}
	
	@RequiresPermissions("mms:riskMaterialAssessment:edit")
	@RequestMapping(value = "delete")
	public String delete(RiskMaterialAssessment riskMaterialAssessment, RedirectAttributes redirectAttributes) {
		riskMaterialAssessmentService.delete(riskMaterialAssessment);
		addMessage(redirectAttributes, "删除风险物质评估信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/riskMaterialAssessment/?repage";
	}

}