/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.mms.entity.RiskMaterialAssessment;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mms.entity.ForbiddenComponent;
import com.thinkgem.jeesite.modules.mms.service.ForbiddenComponentService;

import java.io.IOException;
import java.util.List;

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
//		if(StringUtils.isNoneEmpty(forbiddenComponent.getStandardChineseName())){ //特殊字符处理
//			forbiddenComponent.setStandardChineseName(forbiddenComponent.getStandardChineseName().replaceAll("&rsquo;","\'"));
//		}
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

	/**
	 * 导入模板下载
	 * @param response 响应
	 * @param redirectAttributes 重定向
	 * @return
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "禁用物质数据导入模板.xlsx";
			List<ForbiddenComponent> list = Lists.newArrayList();
			list.add(new ForbiddenComponent());
			new ExportExcel("禁用物质数据", ForbiddenComponent.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/mms/forbiddenComponent/?repage";
	}

	/**
	 * 处理导入的文件数据
	 * @param file 导入的文件
	 * @param redirectAttributes 重定向
	 * @return
	 */
	@RequestMapping(value = "import",method = RequestMethod.POST)
	public String importFile (MultipartFile file, RedirectAttributes redirectAttributes) throws IOException, InvalidFormatException, IllegalAccessException, InstantiationException {
		ImportExcel ei = new ImportExcel(file, 1, 0);
		List<ForbiddenComponent> list = ei.getDataList(ForbiddenComponent.class);
		//TODO 有重复的怎么办？？？
//		int num = 0;
		for(ForbiddenComponent  forbiddenComponent : list){
			if(StringUtils.isEmpty(forbiddenComponent.getStandardChineseName())){ //如果序号为空，忽略继续往下循环
//				num++;
				continue;
			}
			forbiddenComponent.setRemarks(forbiddenComponent.getRemark());//备注
			forbiddenComponentService.save(forbiddenComponent);
		}
		addMessage(redirectAttributes, "已成功导入  禁用物质数据");
		return "redirect:"+Global.getAdminPath()+"/mms/forbiddenComponent/?repage";
	}

}