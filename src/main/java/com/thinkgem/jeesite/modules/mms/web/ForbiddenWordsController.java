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
import com.thinkgem.jeesite.modules.mms.entity.ForbiddenWords;
import com.thinkgem.jeesite.modules.mms.service.ForbiddenWordsService;

import java.util.List;

/**
 * 禁用语词汇Controller
 * @author jiang
 * @version 2017-03-11
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/forbiddenWords")
public class ForbiddenWordsController extends BaseController {

	@Autowired
	private ForbiddenWordsService forbiddenWordsService;
	
	@ModelAttribute
	public ForbiddenWords get(@RequestParam(required=false) String id) {
		ForbiddenWords entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = forbiddenWordsService.get(id);
		}
		if (entity == null){
			entity = new ForbiddenWords();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:forbiddenWords:view")
	@RequestMapping(value = {"list", ""})
	public String list(ForbiddenWords forbiddenWords, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ForbiddenWords> page = forbiddenWordsService.findPage(new Page<ForbiddenWords>(request, response), forbiddenWords); 
		model.addAttribute("page", page);
		return "modules/mms/forbiddenWordsList";
	}

	@RequiresPermissions("mms:forbiddenWords:view")
	@RequestMapping(value = "form")
	public String form(ForbiddenWords forbiddenWords, Model model) {
		model.addAttribute("forbiddenWords", forbiddenWords);
		return "modules/mms/forbiddenWordsForm";
	}

	@RequiresPermissions("mms:forbiddenWords:edit")
	@RequestMapping(value = "save")
	public String save(ForbiddenWords forbiddenWords, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, forbiddenWords)){
			return form(forbiddenWords, model);
		}
		forbiddenWordsService.save(forbiddenWords);
		addMessage(redirectAttributes, "保存禁用语词汇成功");
		return "redirect:"+Global.getAdminPath()+"/mms/forbiddenWords/?repage";
	}
	
	@RequiresPermissions("mms:forbiddenWords:edit")
	@RequestMapping(value = "delete")
	public String delete(ForbiddenWords forbiddenWords, RedirectAttributes redirectAttributes) {
		forbiddenWordsService.delete(forbiddenWords);
		addMessage(redirectAttributes, "删除禁用语词汇成功");
		return "redirect:"+Global.getAdminPath()+"/mms/forbiddenWords/?repage";
	}

	/**
	 * 进入筛选页面
	 * @return
	 */
	@RequestMapping(value = "filterPage")
	public String filterPage() {
		return "modules/mms/forbiddenWordsFilterPage";
	}


	@RequestMapping(value = "filter",method = RequestMethod.GET)
	@ResponseBody
	public String filter(HttpServletRequest request, HttpServletResponse response, Model model) {

		String inputValue = request.getParameter("inputValue");

		//查询所有的禁用词语，进行筛选

		String filterValue = inputValue;

		List<ForbiddenWords> forbiddenWordsList = forbiddenWordsService.findList(new ForbiddenWords());

		if(forbiddenWordsList !=null && forbiddenWordsList.size() >0){
			for (ForbiddenWords forbiddenWords : forbiddenWordsList){ //循环禁用词汇进行替换
				if(inputValue.contains(forbiddenWords.getForbiddenName())){
					filterValue = filterValue.replaceAll(forbiddenWords.getForbiddenName(),"<b style='background-color:yellow'>"+forbiddenWords.getForbiddenName()+"</b>");
				}
			}
		}
		return filterValue;
	}

}