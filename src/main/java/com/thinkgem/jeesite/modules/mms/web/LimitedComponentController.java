/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
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
import com.thinkgem.jeesite.modules.mms.entity.LimitedComponent;
import com.thinkgem.jeesite.modules.mms.service.LimitedComponentService;

import java.io.IOException;
import java.util.List;

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

	/**
	 * 导入模板下载
	 * @param response 响应
	 * @param redirectAttributes 重定向
	 * @return
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "限用物质-防腐剂-防嗮剂导入模板.xlsx";
			List<LimitedComponent> list = Lists.newArrayList();
			list.add(new LimitedComponent());
			new ExportExcel("用物质-防腐剂-防嗮剂", LimitedComponent.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/mms/limitedComponent/?repage";
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
		List<LimitedComponent> list = ei.getDataList(LimitedComponent.class);

		for (LimitedComponent limitedComponent : list){
			limitedComponentService.save(limitedComponent);
		}
		addMessage(redirectAttributes, "已成功导入  限用物质-防腐剂-防嗮剂数据");
		return "redirect:"+Global.getAdminPath()+"/mms/limitedComponent/?repage";
	}
}