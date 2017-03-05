/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.*;
import com.thinkgem.jeesite.modules.mms.service.*;
import com.thinkgem.jeesite.modules.mms.vo.ExportFormulaVo;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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


	@Autowired
	private NameToRiskMaterialService nameToRiskMaterialService;


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
	 * 根据配方 id 找到具体的配方信息
	 * @param formula
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "formulaDetailById")
	public String formulaDetailById(Formula formula, RedirectAttributes redirectAttributes) {

		formulaService.save(formula);
		return "modules/mms/formulaDetailsList";
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
		// 创建 Pattern 对象
		Pattern r1 = Pattern.compile(MmsConstant.plantComponentRegex);
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
					formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_LIMITED); //限用成分
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
			Matcher m1= r1.matcher(standardChineseName);
			if(m1.find()){ //含有，植物油
				formulaDetails.setPlantComponent(MmsConstant.PLANT_COMPONENT_YES);
			}else{
				formulaDetails.setPlantComponent(MmsConstant.PLANT_COMPONENT_NO);
			}
			//保存筛选后的信息
			formulaDetailsService.save(formulaDetails);
		}

		addMessage(redirectAttributes, "筛选配方信息成功");
		return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";
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
			String fileName = "配方数据导入模板.xlsx";
			List<ExportFormulaVo> list = Lists.newArrayList();
			list.add(new ExportFormulaVo());
			new ExportExcel("配方数据", ExportFormulaVo.class, 2).setDataList(list).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + Global.getAdminPath() + "/mms/formula/?repage";
	}

	/**
	 * 处理导入的文件数据
	 * @param file 导入的文件
	 * @param redirectAttributes 重定向
	 * @return
	 */
	@RequestMapping(value = "import",method = RequestMethod.POST)
	public String importFile (MultipartFile file, RedirectAttributes redirectAttributes){

		try {
			ImportExcel ei = new ImportExcel(file, 1, 0);
			try {
				List<ExportFormulaVo> exportFormulaVoList = ei.getDataList(ExportFormulaVo.class);
				String standardChineseName = StringUtils.EMPTY; //标准中文名
				String riskMaterial = StringUtils.EMPTY; //风险物质
				String rawMaterialContent = StringUtils.EMPTY; //原料含量（%）
				float rawMaterialContentTotal = 0L; //总原料含量（%）
				String compoundPercentage = StringUtils.EMPTY; //复配百分比（%）
				String actualComponentContent = StringUtils.EMPTY; //实际成份含量（%）
				float actualComponentContentTotal = 0L; //总实际成份含量（%）
				String purposeOfUse = StringUtils.EMPTY; //使用目的
				String sequence = StringUtils.EMPTY; //序号

				//配方信息---一次导入只有一条
				Formula formula = new Formula();
				//先不保存配方详情信息
				List<FormulaDetails> formulaDetailsList = new ArrayList<FormulaDetails>();
				for(int i = 0; i<exportFormulaVoList.size();i++ ){
					ExportFormulaVo exportFormulaVo = exportFormulaVoList.get(i);
					//配方信息保存
					if(exportFormulaVo.getFormulaName() !=null && !exportFormulaVo.getFormulaName().equals("")){ //配方名称
						formula.setFormulaName(exportFormulaVo.getFormulaName());
					}
					if(exportFormulaVo.getFormulaSequence() !=null && !exportFormulaVo.getFormulaSequence().equals("")){
						formula.setSequence(exportFormulaVo.getFormulaSequence());
					} //配方序号
					if(exportFormulaVo.getRemarks() !=null && !exportFormulaVo.getRemarks().equals("")){
						formula.setRemarks(exportFormulaVo.getRemarks());
					} //配方备注

					//配方详情
					FormulaDetails formulaDetails = new FormulaDetails();

					standardChineseName = exportFormulaVo.getFormulaDetailsStandardChineseName();
					riskMaterial = this.nameToRiskMaterial(standardChineseName); //对应的风险物质
					formulaDetails.setStandardChineseName(standardChineseName);
					formulaDetails.setInicName(exportFormulaVo.getFormulaDetailsInicName());
					formulaDetails.setRiskMaterial(riskMaterial);
					rawMaterialContent =exportFormulaVo.getFormulaDetailsRawMaterialContent(); //原料含量（%）,某一项可能没有向上找
					rawMaterialContentTotal += (rawMaterialContent == null || rawMaterialContent.equals(""))? 0L : Float.parseFloat(rawMaterialContent);//总原料含量
					if(rawMaterialContent == null || rawMaterialContent.equals("")){
						for(int j = i -1; j>=0;j--){
							boolean isRawMaterialContentok = false;
							ExportFormulaVo exportFormulaVoRawMaterialContent = exportFormulaVoList.get(j);
							if(exportFormulaVoRawMaterialContent.getFormulaDetailsRawMaterialContent()!=null && !exportFormulaVoRawMaterialContent.getFormulaDetailsRawMaterialContent().equals("")){
								rawMaterialContent = exportFormulaVoRawMaterialContent.getFormulaDetailsRawMaterialContent();
								isRawMaterialContentok = true;
								break;
							}
							if(!isRawMaterialContentok){
								rawMaterialContent = "0";
							}
						}
					}
					compoundPercentage = exportFormulaVo.getFormulaDetailsCompoundPercentage();//复配百分比（%）
					actualComponentContent = String.valueOf((Float.parseFloat(rawMaterialContent) *  Float.parseFloat(compoundPercentage))/100); //实际成分含量

					actualComponentContentTotal += Float.parseFloat(actualComponentContent);//总实际成分含量
					formulaDetails.setActualComponentContent(actualComponentContent);
					formulaDetails.setRawMaterialContent(rawMaterialContent);
					formulaDetails.setCompoundPercentage(compoundPercentage);

					//使用目的
					purposeOfUse = exportFormulaVo.getFormulaDetailsPurposeOfUse();
					if(purposeOfUse == null || purposeOfUse.equals("")){
						for(int j = i -1; j>=0;j--){
							ExportFormulaVo exportFormulaVoPurposeOfUse = exportFormulaVoList.get(j);
							if(exportFormulaVoPurposeOfUse.getFormulaDetailsPurposeOfUse()!=null && !exportFormulaVoPurposeOfUse.getFormulaDetailsPurposeOfUse().equals("")){
								purposeOfUse = exportFormulaVoPurposeOfUse.getFormulaDetailsPurposeOfUse();
								break;
							}
						}
					}
					formulaDetails.setPurposeOfUse(purposeOfUse);
					//配方详情的每一个序号
					sequence = exportFormulaVo.getFormulaSequence();
					if(sequence == null || sequence.equals("")){
						for(int j = i -1; j>=0;j--){
							ExportFormulaVo exportFormulaVoSequence = exportFormulaVoList.get(j);
							if(exportFormulaVoSequence.getFormulaSequence()!=null && !exportFormulaVoSequence.getFormulaSequence().equals("")){
								sequence = exportFormulaVoSequence.getFormulaSequence();
								break;
							}
						}
					}
					formulaDetails.setSequence(sequence);
					formulaDetailsList.add(formulaDetails);

				}
				formula.setActualComponentContentTotal(String.valueOf(actualComponentContentTotal));
				formula.setRawMaterialContentTotal(String.valueOf(rawMaterialContentTotal));
				formulaService.save(formula);

				//取得配方id保存信息
				for (FormulaDetails formulaDetails :formulaDetailsList ){
					formulaDetails.setFormulaId(formula.getId());
					formulaDetailsService.save(formulaDetails);
				}

			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "导入配方信息成功");
		return "redirect:" + Global.getAdminPath() + "/mms/formula/?repage";
	}

	/**
	 * 根据标准中文名查找对应的风险物质
	 * 先进行精确查询，在进行模糊查询，模糊查询级别越高，优先级越高
	 * @param standardChineseName 标准中文名
	 * @return
	 */
	private String nameToRiskMaterial(String standardChineseName){

		String riskMaterial = StringUtils.EMPTY;
		Boolean isOk = false;
		//先查询精确匹配的风险物质
		NameToRiskMaterial nameToRiskMaterial = new NameToRiskMaterial();
		//根据字典表进行查找精确匹配对应的等级
//		String transformLevel = DictUtils.getDictValue("精确转换","transform_level","");
		nameToRiskMaterial.setTransformLevel("1");
		List<NameToRiskMaterial>  nameToRiskMaterialListLevelOne = nameToRiskMaterialService.findList(nameToRiskMaterial);
		if(nameToRiskMaterialListLevelOne !=null && nameToRiskMaterialListLevelOne.size() > 0){
			for (NameToRiskMaterial nameToRiskMaterial1 : nameToRiskMaterialListLevelOne){
				if(standardChineseName.equals(nameToRiskMaterial1.getStandardChineseName())){
					riskMaterial = nameToRiskMaterial1.getRiskMaterial();
					isOk = true;
					break;
				}
			}
		}

		if(!isOk){ //没有匹配到，开始模糊匹配
			//查询所有模糊转换的信息，并按照优先级别进行排序，数字越高优先级越大
			List<NameToRiskMaterial> nameToRiskMaterialListLevelOther = nameToRiskMaterialService.selectAllLevelOther();
			if(nameToRiskMaterialListLevelOther !=null && nameToRiskMaterialListLevelOther.size() > 0 ){
				for(NameToRiskMaterial nameToRiskMaterialOther : nameToRiskMaterialListLevelOther){
					if(standardChineseName.contains(nameToRiskMaterialOther.getStandardChineseName())){
						riskMaterial = nameToRiskMaterialOther.getRiskMaterial();
						isOk = true;
						break;
					}
				}
			}
		}
		return riskMaterial;
	}

}