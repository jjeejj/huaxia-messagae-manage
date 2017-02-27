/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.entity.FormulaDetails;
import com.thinkgem.jeesite.modules.mms.service.FormulaDetailsService;
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
import com.thinkgem.jeesite.modules.mms.entity.Formula;
import com.thinkgem.jeesite.modules.mms.service.FormulaService;

import java.util.List;

/**
 * 配方信息Controller
 * @author jiang
 * @version 2017-02-27
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/formula")
public class FormulaController extends BaseController {

	@Autowired
	private FormulaService formulaService;

	@Autowired
	private FormulaDetailsService formulaDetailsService;

	@ModelAttribute
	public Formula get(@RequestParam(required=false) String id) {
		Formula entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = formulaService.get(id);
		}
		if (entity == null){
			entity = new Formula();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:formula:view")
	@RequestMapping(value = {"list", ""})
	public String list(Formula formula, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Formula> page = formulaService.findPage(new Page<Formula>(request, response), formula); 
		model.addAttribute("page", page);
		return "modules/mms/formulaList";
	}

	@RequiresPermissions("mms:formula:view")
	@RequestMapping(value = "form")
	public String form(Formula formula, Model model) {
		model.addAttribute("formula", formula);
		return "modules/mms/formulaForm";
	}

	@RequiresPermissions("mms:formula:edit")
	@RequestMapping(value = "save")
	public String save(Formula formula, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, formula)){
			return form(formula, model);
		}
		formulaService.save(formula);
		addMessage(redirectAttributes, "保存配方信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";
	}
	
	@RequiresPermissions("mms:formula:edit")
	@RequestMapping(value = "delete")
	public String delete(Formula formula, RedirectAttributes redirectAttributes) {

		String formulaId = formula.getId();//配方Id
		//根据该配方Id 查找是否有对应的配方详情信息，如果有不让删除

		List<FormulaDetails> formulaDetailsList  =formulaDetailsService.selectAllByFormulaId(formulaId);
		if(formulaDetailsList !=null && formulaDetailsList.size() > 0){
			addMessage(redirectAttributes, "该配方有详细信息,不可以删除");
		}else{
			formulaService.delete(formula);
			addMessage(redirectAttributes, "删除配方信息成功");
		}
		return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";
	}

	/**
	 * 筛选功能
	 */

}