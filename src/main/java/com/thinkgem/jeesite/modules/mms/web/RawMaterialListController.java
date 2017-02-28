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
import com.thinkgem.jeesite.modules.mms.entity.RawMaterialList;
import com.thinkgem.jeesite.modules.mms.service.RawMaterialListService;

/**
 * 已使用原料目录Controller
 * @author jiang
 * @version 2017-02-28
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/rawMaterialList")
public class RawMaterialListController extends BaseController {

	@Autowired
	private RawMaterialListService rawMaterialListService;
	
	@ModelAttribute
	public RawMaterialList get(@RequestParam(required=false) String id) {
		RawMaterialList entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = rawMaterialListService.get(id);
		}
		if (entity == null){
			entity = new RawMaterialList();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:rawMaterialList:view")
	@RequestMapping(value = {"list", ""})
	public String list(RawMaterialList rawMaterialList, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<RawMaterialList> page = rawMaterialListService.findPage(new Page<RawMaterialList>(request, response), rawMaterialList); 
		model.addAttribute("page", page);
		return "modules/mms/rawMaterialListList";
	}

	@RequiresPermissions("mms:rawMaterialList:view")
	@RequestMapping(value = "form")
	public String form(RawMaterialList rawMaterialList, Model model) {
		model.addAttribute("rawMaterialList", rawMaterialList);
		return "modules/mms/rawMaterialListForm";
	}

	@RequiresPermissions("mms:rawMaterialList:edit")
	@RequestMapping(value = "save")
	public String save(RawMaterialList rawMaterialList, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, rawMaterialList)){
			return form(rawMaterialList, model);
		}
		rawMaterialListService.save(rawMaterialList);
		addMessage(redirectAttributes, "保存已使用原料目录成功");
		return "redirect:"+Global.getAdminPath()+"/mms/rawMaterialList/?repage";
	}
	
	@RequiresPermissions("mms:rawMaterialList:edit")
	@RequestMapping(value = "delete")
	public String delete(RawMaterialList rawMaterialList, RedirectAttributes redirectAttributes) {
		rawMaterialListService.delete(rawMaterialList);
		addMessage(redirectAttributes, "删除已使用原料目录成功");
		return "redirect:"+Global.getAdminPath()+"/mms/rawMaterialList/?repage";
	}

}