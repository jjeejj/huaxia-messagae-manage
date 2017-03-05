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
import com.thinkgem.jeesite.modules.mms.entity.RiskMaterialAssessment;
import com.thinkgem.jeesite.modules.mms.service.RiskMaterialAssessmentService;

import java.io.IOException;
import java.util.List;

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

	/**
	 * 导入模板下载
	 * @param response 响应
	 * @param redirectAttributes 重定向
	 * @return
	 */
	@RequestMapping(value = "import/template")
	public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "风险物质评估数据导入模板.xlsx";
			List<RiskMaterialAssessment> list = Lists.newArrayList();
			list.add(new RiskMaterialAssessment());
			new ExportExcel("风险物质评估数据", RiskMaterialAssessment.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/mms/riskMaterialAssessment/?repage";
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
		List<RiskMaterialAssessment> list = ei.getDataList(RiskMaterialAssessment.class);
		//TODO 有重复的怎么办？？？
		int num = 0;
		for(RiskMaterialAssessment  riskMaterialAssessment : list){
			if(StringUtils.isEmpty(riskMaterialAssessment.getSequence())){ //如果序号为空，忽略继续往下循环
				num++;
				continue;
			}
			riskMaterialAssessment.setRemarks(riskMaterialAssessment.getRemark());//备注
			riskMaterialAssessmentService.save(riskMaterialAssessment);
		}
		if(num > 0){
			addMessage(redirectAttributes, "已成功导入  风险物质评估数据. "+"没有序号的数据条数为: "+num);
		}else{
			addMessage(redirectAttributes, "已成功导入  风险物质评估数据");
		}
		return "redirect:"+Global.getAdminPath()+"/mms/riskMaterialAssessment/?repage";
	}

}