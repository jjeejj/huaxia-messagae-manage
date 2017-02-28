/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.*;
import com.thinkgem.jeesite.modules.mms.service.*;
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

	@Autowired
	private ForbiddenComponentService forbiddenComponentService;

	@Autowired
	private RawMaterialListService rawMaterialListService;

	@Autowired
	private LimitedComponentService limitedComponentService;

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
	 * @param formula 配方
	 * @param redirectAttributes 重定向
	 * @return String
	 */
	@RequestMapping(value = "filter")
	public String filter(Formula formula, RedirectAttributes redirectAttributes) {
		//根据配方id 获取所有的配方详细信息

		String formulaId = formula.getId();

		List<FormulaDetails> formulaDetailsList  =formulaDetailsService.selectAllByFormulaId(formulaId);
		if(formulaDetailsList == null || formulaDetailsList.size() == 0){
			addMessage(redirectAttributes, "该配方没有详情信息");
			return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";
		}
		String standardChineseName = StringUtils.EMPTY; //标准中文名
		String inicName = StringUtils.EMPTY; //INCI名
//		float actualComponentContent = 0L; //实际成份含量
		for(FormulaDetails formulaDetails :formulaDetailsList ){
			standardChineseName = formulaDetails.getStandardChineseName();
			//根据标准中文名，判断是否是禁用物质
			ForbiddenComponent  forbiddenComponent = new ForbiddenComponent();
			forbiddenComponent.setStandardChineseName(standardChineseName);

			List<ForbiddenComponent> forbiddenComponentList = forbiddenComponentService.findList(forbiddenComponent);
			if( forbiddenComponentList != null && forbiddenComponentList.size() > 0){ //是禁用物质
				formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_FORBIDDEN);
			}else{
				//判断是否是限用物质
				LimitedComponent limitedComponent = new LimitedComponent();
				limitedComponent.setStandardChineseName(standardChineseName);
				List<LimitedComponent> limitedComponentList = limitedComponentService.findList(limitedComponent);
				if(limitedComponentList !=null && limitedComponentList.size() >0){
					formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_FORBIDDEN); //限用成分
					//成分含量是否符合标准
					limitedComponent = limitedComponentList.get(0);
					if(Float.parseFloat(formulaDetails.getActualComponentContent()) > Float.parseFloat(limitedComponent.getMaxAllowConcentretion())){ //大于标准含量
						formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NO_NORMAL);//不符合标准
					}else{
						formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL);//符合标准
					}
				}else {
					//正常成分
					formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_NORMAL); //正常成分
				}
			}

			/**
			 * 根据已使用原料目录，进行
			 * 标准中文名称或INCI名的状态(
			 * 0:正常,
			 * 1:标准中文名称和INCI名不一致,
			 * 2：标准中文名称未出现,
			 * 3：INCI名未出现,
			 * 4:标准中文名称和INCI名都未出现)
			 *
			 */
			RawMaterialList rawMaterialListChineseName = new RawMaterialList();
			rawMaterialListChineseName.setStandardChineseName(standardChineseName);
			RawMaterialList rawMaterialListInicName = new RawMaterialList();
			rawMaterialListInicName.setInicName(inicName);

			//查询标准中文名是否存在
			List<RawMaterialList> rawMaterialListChineseList = rawMaterialListService.findList(rawMaterialListChineseName);
			//查询INCI是否存在
			List<RawMaterialList> rawMaterialListInicList = rawMaterialListService.findList(rawMaterialListInicName);
			if((rawMaterialListChineseList ==null || rawMaterialListChineseList.size() == 0.) &&(rawMaterialListInicList ==null || rawMaterialListInicList.size() == 0.)){ //都不存在
				formulaDetails.setNameOrInicStatus(MmsConstant.NAME_OR_INIC_STATUS_All_NOT_FIND);
			}else if((rawMaterialListChineseList !=null && rawMaterialListChineseList.size() > 0.) && (rawMaterialListInicList ==null || rawMaterialListInicList.size() == 0.)){ //INCI名未出现
				formulaDetails.setNameOrInicStatus(MmsConstant.INIC_STATUS_NOT_FIND);
			}else if((rawMaterialListChineseList ==null || rawMaterialListChineseList.size() == 0.) && (rawMaterialListInicList !=null && rawMaterialListInicList.size() > 0.)){ //标准中文名称未出现
				formulaDetails.setNameOrInicStatus(MmsConstant.NAME_STATUS_NOT_FIND);
			}else{
				//都出现了，判断是否一致
				RawMaterialList rawMaterialList = new RawMaterialList();
				rawMaterialList.setInicName(inicName);
				rawMaterialList.setStandardChineseName(standardChineseName);
				List<RawMaterialList> rawMaterialListList = rawMaterialListService.findList(rawMaterialList);
				if(rawMaterialListList!=null && rawMaterialListList.size() >0 ){ //匹配
					formulaDetails.setNameOrInicStatus(MmsConstant.NAME_OR_INIC_STATUS_NORMAL);
				}else{
					formulaDetails.setNameOrInicStatus(MmsConstant.NAME_OR_INIC_STATUS_NOT_MATCH); //不匹配
				}
			}

			/**
			 * 进行是否是植物成分判断
			 * 是否是植物成分(1:是,2:不是)
			 * 原则
			 * 导入配方成分名称含“（字母）”并直接连接“提取”、“油”、“水”、“汁”、“粉”字段
			 * 或 含“（字母）”和“/”字段
			 *
			 * Java 正则表达式
			 */



			//保存筛选后的信息
			formulaDetailsService.save(formulaDetails);
		}

		addMessage(redirectAttributes, "筛选配方信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";
	}


}