/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.mms.vo.ExportFormulaVo;
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
import com.thinkgem.jeesite.modules.mms.entity.NameToRiskMaterial;
import com.thinkgem.jeesite.modules.mms.service.NameToRiskMaterialService;

import java.io.IOException;
import java.util.List;

/**
 * 标准中文名对应的风险物质Controller
 * @author jiang
 * @version 2017-02-26
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/nameToRiskMaterial")
public class NameToRiskMaterialController extends BaseController {

	@Autowired
	private NameToRiskMaterialService nameToRiskMaterialService;
	
	@ModelAttribute
	public NameToRiskMaterial get(@RequestParam(required=false) String id) {
		NameToRiskMaterial entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = nameToRiskMaterialService.get(id);
		}
		if (entity == null){
			entity = new NameToRiskMaterial();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:nameToRiskMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(NameToRiskMaterial nameToRiskMaterial, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<NameToRiskMaterial> page = nameToRiskMaterialService.findPage(new Page<NameToRiskMaterial>(request, response), nameToRiskMaterial); 
		model.addAttribute("page", page);
		return "modules/mms/nameToRiskMaterialList";
	}

	@RequiresPermissions("mms:nameToRiskMaterial:view")
	@RequestMapping(value = "form")
	public String form(NameToRiskMaterial nameToRiskMaterial, Model model) {
		model.addAttribute("nameToRiskMaterial", nameToRiskMaterial);
		return "modules/mms/nameToRiskMaterialForm";
	}

	@RequiresPermissions("mms:nameToRiskMaterial:edit")
	@RequestMapping(value = "save")
	public String save(NameToRiskMaterial nameToRiskMaterial, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, nameToRiskMaterial)){
			return form(nameToRiskMaterial, model);
		}
		nameToRiskMaterialService.save(nameToRiskMaterial);
		addMessage(redirectAttributes, "保存标准中文名对应的风险物质成功");
		return "redirect:"+Global.getAdminPath()+"/mms/nameToRiskMaterial/?repage";
	}
	
	@RequiresPermissions("mms:nameToRiskMaterial:edit")
	@RequestMapping(value = "delete")
	public String delete(NameToRiskMaterial nameToRiskMaterial, RedirectAttributes redirectAttributes) {
		nameToRiskMaterialService.delete(nameToRiskMaterial);
		addMessage(redirectAttributes, "删除标准中文名对应的风险物质成功");
		return "redirect:"+Global.getAdminPath()+"/mms/nameToRiskMaterial/?repage";
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
			String fileName = "标准中文名对应的风险物质数据导入模板.xlsx";
			List<NameToRiskMaterial> list = Lists.newArrayList();
			list.add(new NameToRiskMaterial());
			new ExportExcel("标准中文名对应的风险物质数据", NameToRiskMaterial.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/mms/nameToRiskMaterial/?repage";
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
		List<NameToRiskMaterial> list = ei.getDataList(NameToRiskMaterial.class);
		//TODO 风险物质，有重复的怎么办？？？
		for(NameToRiskMaterial  nameToRiskMaterial : list){
			if(StringUtils.isEmpty(nameToRiskMaterial.getStandardChineseName())){ //没有标准中文名的记录，忽略防止空白单元行
				continue;
			}
			nameToRiskMaterialService.save(nameToRiskMaterial);
		}
		addMessage(redirectAttributes, "已成功导入  标准中文名对应的风险物质数据");

		return "redirect:"+Global.getAdminPath()+"/mms/nameToRiskMaterial/?repage";
	}

}