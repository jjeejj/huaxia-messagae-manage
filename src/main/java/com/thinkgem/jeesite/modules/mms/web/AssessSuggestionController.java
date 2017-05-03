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
import com.thinkgem.jeesite.modules.mms.entity.AssessSuggestion;
import com.thinkgem.jeesite.modules.mms.service.AssessSuggestionService;

/**
 * 审评意见Controller
 * @author jiang
 * @version 2017-03-07
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/assessSuggestion")
public class AssessSuggestionController extends BaseController {

	@Autowired
	private AssessSuggestionService assessSuggestionService;
	
	@ModelAttribute
	public AssessSuggestion get(@RequestParam(required=false) String id) {
		AssessSuggestion entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = assessSuggestionService.get(id);
		}
		if (entity == null){
			entity = new AssessSuggestion();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:assessSuggestion:view")
	@RequestMapping(value = {"list", ""})
	public String list(AssessSuggestion assessSuggestion, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<AssessSuggestion> page = assessSuggestionService.findPage(new Page<AssessSuggestion>(request, response), assessSuggestion); 
		model.addAttribute("page", page);
		return "modules/mms/assessSuggestionList";
	}

	@RequiresPermissions("mms:assessSuggestion:view")
	@RequestMapping(value = "form")
	public String form(AssessSuggestion assessSuggestion, Model model) {
		model.addAttribute("assessSuggestion", assessSuggestion);
		return "modules/mms/assessSuggestionForm";
	}

	@RequiresPermissions("mms:assessSuggestion:edit")
	@RequestMapping(value = "save")
	public String save(AssessSuggestion assessSuggestion, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, assessSuggestion)){
			return form(assessSuggestion, model);
		}
		//序号的流水排序
		//如果是新记录，就生成新的流水序号 后面的比前面的 加 1
		if(assessSuggestion.getIsNewRecord()){
			//查询最大的流水号
			String bigSequence = assessSuggestionService.getBigSequence();
			//赋值下一个序列号
			assessSuggestion.setSequence(String.valueOf(Integer.valueOf(bigSequence) + 1));
		}
		assessSuggestionService.save(assessSuggestion);
		addMessage(redirectAttributes, "保存审评意见成功");
		return "redirect:"+Global.getAdminPath()+"/mms/assessSuggestion/?repage";
	}
	
	@RequiresPermissions("mms:assessSuggestion:edit")
	@RequestMapping(value = "delete")
	public String delete(AssessSuggestion assessSuggestion, RedirectAttributes redirectAttributes) {
		assessSuggestionService.delete(assessSuggestion);
		addMessage(redirectAttributes, "删除审评意见成功");
		return "redirect:"+Global.getAdminPath()+"/mms/assessSuggestion/?repage";
	}

}