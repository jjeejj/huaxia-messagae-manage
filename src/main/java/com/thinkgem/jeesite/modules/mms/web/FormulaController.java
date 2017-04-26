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
import com.thinkgem.jeesite.modules.mms.utils.MmsUtils;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 配方信息Controller
 *
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

    @Autowired
    private MaterialUsedDatabaseService materialUsedDatabaseService;

    @Autowired
    private IncinameConvertChinesenameService incinameConvertChinesenameService;


    @ModelAttribute
    public Formula get(@RequestParam(required = false) String id) {
        Formula entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = formulaService.get(id);
        }
        if (entity == null) {
            entity = new Formula();
        }
        return entity;
    }

    @RequiresPermissions("mms:formula:view")
    @RequestMapping(value = {"list", ""})
    public String list(Formula formula, HttpServletRequest request, HttpServletResponse response, Model model) {
        //根据产品编号，查找对应的产品信息
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
        if (!beanValidator(model, formula)) {
            return form(formula, model);
        }
        formulaService.save(formula);
        addMessage(redirectAttributes, "保存配方信息成功");
        return "redirect:" + Global.getAdminPath() + "/mms/formula/?repage";
    }

    @RequiresPermissions("mms:formula:edit")
    @RequestMapping(value = "delete")
    public String delete(Formula formula, RedirectAttributes redirectAttributes) {

        String formulaId = formula.getId();//配方Id
        //根据该配方Id 查找是否有对应的配方详情信息，如果有不让删除

        List<FormulaDetails> formulaDetailsList = formulaDetailsService.selectAllByFormulaId(formulaId);
        if (formulaDetailsList != null && formulaDetailsList.size() > 0) {
            //现在让删除
            for (FormulaDetails formulaDetails : formulaDetailsList) {
                formulaDetailsService.delete(formulaDetails);
            }
        }
        formulaService.delete(formula);
        addMessage(redirectAttributes, "删除配方信息成功");
        return "redirect:" + Global.getAdminPath() + "/mms/formula/?repage";
    }

    /**
     * 根据配方 id 找到具体的配方信息
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "formulaDetailById")
    public String formulaDetailById(HttpServletRequest request, HttpServletResponse response, Model model) {

        String formulaId = request.getParameter("formulaId");//配方ID
        //查询
        List<FormulaDetails> formulaDetailsList = formulaDetailsService.selectAllByFormulaId(formulaId);

        //配方的总信息也查询出来显示
        Formula formula = formulaService.get(formulaId);

        model.addAttribute("formulaDetailsList", formulaDetailsList);

        model.addAttribute("formula", formula);
        return "modules/mms/formulaDetailsListByFormulaId";
    }

    /**
     * 根据导入的配方产品编号 找到具体的配方信息
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "formulaDetailByproductNumber")
    public String formulaDetailByproductNumber(HttpServletRequest request, HttpServletResponse response, Model model) {

        String productNumber = request.getParameter("productNumber");//配方产品编号

        List<FormulaDetails> formulaDetailsList = new ArrayList<FormulaDetails>();//配方信息
        if (StringUtils.isNoneEmpty(productNumber)) {

            Formula formula = new Formula();
            //查询配方id
            formula.setProductNumber(productNumber);

            List<Formula> formulaList = formulaService.findList(formula);
            if (formulaList != null && formulaList.size() > 0) {
                String formulaId = formulaList.get(0).getId();
                //查询
                formulaDetailsList = formulaDetailsService.selectAllByFormulaId(formulaId);
            }
        }

        model.addAttribute("formulaDetailsList", formulaDetailsList);
        return "modules/mms/formulaDetailsListByFormulaId";
    }


    /**
     * 导入的时候直接进行筛选
     * 筛选功能
     *
     * @param formula 配方
     * @return String
     */
//	@RequestMapping(value = "filter")
    public void filter(Formula formula) {
        //根据配方id 获取所有的配方详细信息

        String formulaId = formula.getId();

        List<FormulaDetails> formulaDetailsList = formulaDetailsService.selectAllByFormulaId(formulaId);
        if (formulaDetailsList != null && formulaDetailsList.size() > 0) {
//			addMessage(redirectAttributes, "该配方没有详情信息");
//			return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";

            String standardChineseName = StringUtils.EMPTY; //标准中文名
            String inicName = StringUtils.EMPTY; //INCI名
            //float actualComponentContent = 0L; //实际成份含量
            // 创建 Pattern 对象
            Pattern r1 = Pattern.compile(MmsConstant.plantComponentRegex);

            for (FormulaDetails formulaDetails : formulaDetailsList) {
                standardChineseName = formulaDetails.getStandardChineseName();


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
                if ((rawMaterialListChineseList == null || rawMaterialListChineseList.size() == 0.) && (rawMaterialListInicList == null || rawMaterialListInicList.size() == 0.)) { //都不存在
                    formulaDetails.setNameOrInicStatus(MmsConstant.NAME_OR_INIC_STATUS_All_NOT_FIND);
                } else if ((rawMaterialListChineseList != null && rawMaterialListChineseList.size() > 0.) && (rawMaterialListInicList == null || rawMaterialListInicList.size() == 0.)) { //INCI名未出现
                    formulaDetails.setNameOrInicStatus(MmsConstant.INIC_STATUS_NOT_FIND);
                } else if ((rawMaterialListChineseList == null || rawMaterialListChineseList.size() == 0.) && (rawMaterialListInicList != null && rawMaterialListInicList.size() > 0.)) { //标准中文名称未出现
                    formulaDetails.setNameOrInicStatus(MmsConstant.NAME_STATUS_NOT_FIND);
                } else {
                    //都出现了，判断是否一致
                    RawMaterialList rawMaterialList = new RawMaterialList();
                    rawMaterialList.setInicName(inicName);
                    rawMaterialList.setStandardChineseName(standardChineseName);
                    List<RawMaterialList> rawMaterialListList = rawMaterialListService.findList(rawMaterialList);
                    if (rawMaterialListList != null && rawMaterialListList.size() > 0) { //匹配
                        formulaDetails.setNameOrInicStatus(MmsConstant.NAME_OR_INIC_STATUS_NORMAL);
                    } else {
                        formulaDetails.setNameOrInicStatus(MmsConstant.NAME_OR_INIC_STATUS_NOT_MATCH); //不匹配
                    }
                }

                /**
                 * 禁用限用物质的逻辑
                 * 1：如果是禁用物质或者新原料就不用在判断是否是限用物质
                 * 2：限用物质 1：限用成分，2：防腐剂，3：防晒剂，原料类型 为单一原料根据使用目的进行判断，复配原料：根据标准中文名称进行判断
                 *
                 */

                //根据标准中文名，判断是否是禁用物质
                ForbiddenComponent forbiddenComponent = new ForbiddenComponent();
                forbiddenComponent.setStandardChineseName(standardChineseName);

                List<ForbiddenComponent> forbiddenComponentList = forbiddenComponentService.findList(forbiddenComponent);
                if (forbiddenComponentList != null && forbiddenComponentList.size() > 0) { //是禁用物质
                    formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_FORBIDDEN);
                } else {
                    //判断是否是限用物质
                    //新原料不用判断了
                    if (!formulaDetails.getNameOrInicStatus().equals(MmsConstant.NAME_OR_INIC_STATUS_All_NOT_FIND)) {
                        formulaDetails = filterLimitComponent(formulaDetails);
                    }
//                    LimitedComponent limitedComponent = new LimitedComponent();
//                    limitedComponent.setStandardChineseName(standardChineseName);
//                    List<LimitedComponent> limitedComponentList = limitedComponentService.findList(limitedComponent);
//                    if (limitedComponentList != null && limitedComponentList.size() > 0) {
//                        formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_LIMITED); //限用成分
//                        //成分含量是否符合标准
//                        limitedComponent = limitedComponentList.get(0);
//                        if (Float.parseFloat(formulaDetails.getActualComponentContent()) > Float.parseFloat(limitedComponent.getMaxAllowConcentretion())) { //大于标准含量
//                            formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NO_NORMAL);//不符合标准
//                        } else {
//                            formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL);//符合标准
//                        }
//                    } else {
//                        //正常成分
//                        formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_NORMAL); //正常成分
//                    }
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
                Matcher m1 = r1.matcher(standardChineseName);
                if (m1.find()) { //含有，植物油
                    formulaDetails.setPlantComponent(MmsConstant.PLANT_COMPONENT_YES);
                } else {
                    formulaDetails.setPlantComponent(MmsConstant.PLANT_COMPONENT_NO);
                }
                //保存筛选后的信息
                formulaDetailsService.save(formulaDetails);
            }
        }

//		addMessage(redirectAttributes, "筛选配方信息成功");
//		return "redirect:"+Global.getAdminPath()+"/mms/formula/?repage";
    }

    /**
     * 限用成分的逻辑
     */
    public FormulaDetails filterLimitComponent(FormulaDetails formulaDetails) {
        String purposeOfUse = formulaDetails.getPurposeOfUse();//使用目的

        String materialType = formulaDetails.getMaterialType();//原料类型（* 0：单一原料，* 1：复配原料）

        String standardChineseName = formulaDetails.getStandardChineseName();//标准中文名称

        List<LimitedComponent> limitedComponentList = new ArrayList<LimitedComponent>();

        //先处理着色剂 的限用成分
        Pattern r1 = Pattern.compile(MmsConstant.colorantRegex);
        Matcher m1 = r1.matcher(standardChineseName);

        if (m1.find()) { //匹配了

        } else {

            if (materialType.equals(MmsConstant.MATERIAL_TYPE_1)) {//复配原料.直接查不根据使用目的
                limitedComponentList = limitedComponentService.findList(new LimitedComponent());
                boolean isHandle = false;
                for (LimitedComponent limitedComponent : limitedComponentList) {
                    List<String> queryChineseNameList = Arrays.asList(limitedComponent.getQueryChineseName().split("，|,")); //查询用的中文名称（可能有多个以，分割）
                    for (String queryChineseName : queryChineseNameList) {
                        if (standardChineseName.contains(queryChineseName)) {
                            formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_LIMITED);//限用成分
                            formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL);//符合标准
                            isHandle = true;
                            break;
                        }
                    }
                }
                if (!isHandle) {
                    formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_NORMAL);//正常成分
                    isHandle = true;
                }
            } else if (materialType.equals(MmsConstant.MATERIAL_TYPE_0)) { //单一原料 ,根据使用目的进行判断

                LimitedComponent limitedComponent = new LimitedComponent();
                //使用目的是否有值
                if (StringUtils.isNoneBlank(purposeOfUse)) {
                    //使用目的转换---消息转数字类型
                    String purposeOfUseNum = MmsUtils.purposeOfUseTypeNum2Message("", purposeOfUse);
                    limitedComponent.setType(purposeOfUseNum);
                } else { //现在就是一般的限用成分查询
                    limitedComponent.setType(MmsConstant.PURPOSE_OF_USE_1);
                }

                limitedComponentList = limitedComponentService.findList(limitedComponent);
                boolean isHandle = false;
                for (LimitedComponent limitedComponentTemp : limitedComponentList) {
                    List<String> queryChineseNameList = Arrays.asList(limitedComponentTemp.getQueryChineseName().split("，|,")); //查询用的中文名称（可能有多个以，分割）
                    for (String queryChineseName : queryChineseNameList) {
                        if (standardChineseName.contains(queryChineseName)) {
                            formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_LIMITED);//限用成分
                            formulaDetails.setLimitedRemarks(limitedComponentTemp.getLimitedRemarks());
                            //比较成分含量---默认是符合要求的。队友没有浓度的是复合要求的
                            if (StringUtils.isNoneBlank(limitedComponentTemp.getMaxAllowConcentretion())) { //成分有限制的浓度
                                if (Float.parseFloat(formulaDetails.getActualComponentContent()) > Float.parseFloat(limitedComponentTemp.getMaxAllowConcentretion())) { //大于标准含量
                                    formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NO_NORMAL);//不符合标准
                                } else {
                                    formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL);//符合标准
                                }
                            } else {
                                formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL);//符合标准
                            }
                            isHandle = true;
                            break;
                        }
                    }
                }

                //如果现在都没有
                if (!isHandle) {
                    //到找不到，在全部里面查询
                    limitedComponentList = limitedComponentService.findList(new LimitedComponent());
                    for (LimitedComponent limitedComponentTemp : limitedComponentList) {
                        List<String> queryChineseNameList = Arrays.asList(limitedComponentTemp.getQueryChineseName().split("，|,")); //查询用的中文名称（可能有多个以，分割）
                        for (String queryChineseName : queryChineseNameList) {
                            if (standardChineseName.contains(queryChineseName)) {
                                formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_LIMITED);//限用成分
                                formulaDetails.setActualComponentContent(MmsConstant.ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL);//符合标准
                                isHandle = true;
                                break;
                            }
                        }
                    }
                }
                //都没有。正常成分
                if (!isHandle) {
                    formulaDetails.setComponentType(MmsConstant.COMPONENT_TYPE_NORMAL);//正常成分
                    isHandle = true;
                }
            }
        }

        return formulaDetails;
    }


    /**
     * 导入模板下载
     *
     * @param response           响应
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
     *
     * @param file               导入的文件
     * @param redirectAttributes 重定向
     * @return
     */
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            ImportExcel ei = new ImportExcel(file, 1, 0);
            try {
                List<ExportFormulaVo> exportFormulaVoList = ei.getDataList(ExportFormulaVo.class);
                String standardChineseName = StringUtils.EMPTY; //标准中文名
                String inicName = StringUtils.EMPTY; //inci名称
                String riskMaterial = StringUtils.EMPTY; //风险物质
                String rawMaterialContent = StringUtils.EMPTY; //原料含量（%）
                double rawMaterialContentTotal = 0L; //总原料含量（%）
                BigDecimal rawMaterialContentTotalBig = new BigDecimal("0"); //总原料含量（%）精度表示
                String compoundPercentage = StringUtils.EMPTY; //复配百分比（%）
                String actualComponentContent = StringUtils.EMPTY; //实际成份含量（%）
                double actualComponentContentTotal = 0L; //总实际成份含量（%）
                BigDecimal actualComponentContentTotalBig = new BigDecimal("0"); //总实际成份含量（%）精度表示
                String purposeOfUse = StringUtils.EMPTY; //使用目的
                String sequence = StringUtils.EMPTY; //序号


                //配方信息---一次导入只有一条
                Formula formula = new Formula();
                //先不保存配方详情信息
                List<FormulaDetails> formulaDetailsList = new ArrayList<FormulaDetails>();
                for (int i = 0; i < exportFormulaVoList.size(); i++) {
                    ExportFormulaVo exportFormulaVo = exportFormulaVoList.get(i);
                    //配方信息保存
                    if (StringUtils.isNoneEmpty(exportFormulaVo.getFormulaName())) { //配方名称
                        formula.setFormulaName(exportFormulaVo.getFormulaName());
                    }

//					if(StringUtils.isNoneEmpty(exportFormulaVo.getFormulaSequence())){ //配方序号
//						formula.setSequence(exportFormulaVo.getFormulaSequence());
//					}
                    //产品编号
                    if (StringUtils.isNoneEmpty(exportFormulaVo.getProductNumber())) {
                        formula.setProductNumber(exportFormulaVo.getProductNumber());
                    }
//					if(exportFormulaVo.getRemarks() !=null && !exportFormulaVo.getRemarks().equals("")){
//						formula.setRemarks(exportFormulaVo.getRemarks());
//					} //配方备注

                    //配方详情
                    FormulaDetails formulaDetails = new FormulaDetails();

                    //第一步，标准中文名 与 inci名称 相互转换，如果有一项没有值的情况下

                    standardChineseName = exportFormulaVo.getFormulaDetailsStandardChineseName();
                    inicName = exportFormulaVo.getFormulaDetailsInicName();

                    IncinameConvertChinesename incinameConvertChinesename = new IncinameConvertChinesename();
                    List<IncinameConvertChinesename> incinameConvertChinesenameList = new ArrayList<IncinameConvertChinesename>();

                    //标准中文名有，inci名称没有，去查找对应的数据.  标准中文名 ------> inci名
                    if (StringUtils.isNoneEmpty(standardChineseName) && StringUtils.isEmpty(inicName)) {
                        incinameConvertChinesename.setStandardChineseName(standardChineseName);
                        incinameConvertChinesenameList = incinameConvertChinesenameService.findList(incinameConvertChinesename);
                        if (incinameConvertChinesenameList != null && incinameConvertChinesenameList.size() > 0) {
                            inicName = incinameConvertChinesenameList.get(0).getInciName();
                        }
                    }
                    //标准中文名没有，inci名称有，去查找对应的数据.  标准中文名 <------ inci名
                    if (StringUtils.isNoneEmpty(inicName) && StringUtils.isEmpty(standardChineseName)) {
                        incinameConvertChinesename.setInciName(inicName);
                        incinameConvertChinesenameList = incinameConvertChinesenameService.findList(incinameConvertChinesename);
                        if (incinameConvertChinesenameList != null && incinameConvertChinesenameList.size() > 0) {
                            standardChineseName = incinameConvertChinesenameList.get(0).getStandardChineseName();
                        }
                    }
                    //有标准中文名称，才进行风险物质的匹配
                    if (StringUtils.isNoneEmpty(standardChineseName)) {
                        riskMaterial = this.nameToRiskMaterial(standardChineseName); //对应的风险物质
                    }
                    formulaDetails.setStandardChineseName(standardChineseName);
                    formulaDetails.setInicName(inicName);
                    formulaDetails.setRiskMaterial(riskMaterial);
                    rawMaterialContent = exportFormulaVo.getFormulaDetailsRawMaterialContent(); //原料含量（%）,某一项可能没有向上找,由于单元格合并的

//                    rawMaterialContentTotal += (rawMaterialContent == null || rawMaterialContent.equals("")) ? 0L : Double.parseDouble(rawMaterialContent);//总原料含量,需要相加在以一起的
                    rawMaterialContentTotalBig = rawMaterialContentTotalBig.add((rawMaterialContent == null || rawMaterialContent.equals("")) ? new BigDecimal("0") : new BigDecimal(rawMaterialContent));//总原料含量,需要相加在以一起的
                    if (StringUtils.isEmpty(rawMaterialContent)) { //某一项可能没有向上找,由于单元格合并的
                        for (int j = i - 1; j >= 0; j--) {
                            boolean isRawMaterialContentok = false;
                            ExportFormulaVo exportFormulaVoRawMaterialContent = exportFormulaVoList.get(j);
                            if (exportFormulaVoRawMaterialContent.getFormulaDetailsRawMaterialContent() != null && !exportFormulaVoRawMaterialContent.getFormulaDetailsRawMaterialContent().equals("")) {
                                rawMaterialContent = exportFormulaVoRawMaterialContent.getFormulaDetailsRawMaterialContent();
                                isRawMaterialContentok = true;
                                break;
                            }
                            if (!isRawMaterialContentok) {
                                rawMaterialContent = "0";
                            }
                        }
                    }
                    compoundPercentage = exportFormulaVo.getFormulaDetailsCompoundPercentage();//复配百分比（%）
//                    actualComponentContent = String.valueOf((Double.parseDouble(rawMaterialContent) * Double.parseDouble(compoundPercentage)) / 100); //实际成分含量
                    actualComponentContent = String.valueOf(new BigDecimal(rawMaterialContent).multiply(new BigDecimal(compoundPercentage)).divide(new BigDecimal("100"))); //实际成分含量

//                    actualComponentContentTotal += Double.parseDouble(actualComponentContent);//总实际成分含量
                    actualComponentContentTotalBig = actualComponentContentTotalBig.add(new BigDecimal(actualComponentContent));//总实际成分含量
                    formulaDetails.setActualComponentContent(actualComponentContent);
                    formulaDetails.setRawMaterialContent(rawMaterialContent);
                    formulaDetails.setCompoundPercentage(compoundPercentage);

                    /**
                     * 对于复合原料，只显示第一个的使用目的和序号
                     */
                    //使用目的
                    purposeOfUse = exportFormulaVo.getFormulaDetailsPurposeOfUse();
//					if(StringUtils.isEmpty(purposeOfUse)){
//						for(int j = i -1; j>=0;j--){
//							ExportFormulaVo exportFormulaVoPurposeOfUse = exportFormulaVoList.get(j);
//							if(StringUtils.isNoneEmpty(exportFormulaVoPurposeOfUse.getFormulaDetailsPurposeOfUse())){
//								purposeOfUse = exportFormulaVoPurposeOfUse.getFormulaDetailsPurposeOfUse();
//								break;
//							}
//						}
//					}
                    formulaDetails.setPurposeOfUse(purposeOfUse);
                    //配方详情的每一个序号
                    //只显示第一个序号
                    sequence = exportFormulaVo.getFormulaDetailsSequence();
//					if(StringUtils.isEmpty(sequence)){
//						for(int j = i -1; j>=0;j--){
//							ExportFormulaVo exportFormulaVoSequence = exportFormulaVoList.get(j);
//							if(StringUtils.isNoneEmpty(exportFormulaVoSequence.getFormulaDetailsSequence())){
//								sequence = exportFormulaVoSequence.getFormulaDetailsSequence();
//								break;
//							}
//						}
//					}
                    String materialType = StringUtils.EMPTY; //原料类型（0：单一原料，1：复配原料）
                    //判断是否是复配原料
                    if (StringUtils.isEmpty(sequence)) {
                        materialType = MmsConstant.MATERIAL_TYPE_1;
                    } else {
                        //判断下一个序号是否有值，如果下一个没有值么就是复配原料
                        if ((i + 1) == exportFormulaVoList.size()) { //最后一项
                            materialType = MmsConstant.MATERIAL_TYPE_0;
                        } else {
                            String nextSequence = exportFormulaVoList.get(i + 1).getFormulaDetailsSequence();
                            if (StringUtils.isEmpty(nextSequence)) {
                                materialType = MmsConstant.MATERIAL_TYPE_1;
                            } else {
                                materialType = MmsConstant.MATERIAL_TYPE_0;
                            }
                        }
                    }
                    formulaDetails.setMaterialType(materialType);
                    formulaDetails.setSequence(sequence);
                    formulaDetails.setExcelLineNumber(String.valueOf(i));//从第0行开始
                    formulaDetailsList.add(formulaDetails);
                }
//                formula.setActualComponentContentTotal(String.valueOf(actualComponentContentTotal));
                formula.setActualComponentContentTotal(String.valueOf(actualComponentContentTotalBig));
//                formula.setRawMaterialContentTotal(String.valueOf(rawMaterialContentTotal));
                formula.setRawMaterialContentTotal(String.valueOf(rawMaterialContentTotalBig));
                formulaService.save(formula);

                //取得配方id保存信息
                for (FormulaDetails formulaDetails : formulaDetailsList) {
                    formulaDetails.setFormulaId(formula.getId());
                    formulaDetailsService.save(formulaDetails);
                }
                //更新原料使用数据库，的数据信息
                this.handleMaterialUsedDatabase();
                //筛选导入的配方信息
                this.filter(formula);

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
     *
     * @param standardChineseName 标准中文名
     * @return
     */
    private String nameToRiskMaterial(String standardChineseName) {

        String riskMaterial = StringUtils.EMPTY;
        Boolean isOk = false;
        //先查询精确匹配的风险物质
        NameToRiskMaterial nameToRiskMaterial = new NameToRiskMaterial();
        //根据字典表进行查找精确匹配对应的等级
//		String transformLevel = DictUtils.getDictValue("精确转换","transform_level","");
        nameToRiskMaterial.setTransformLevel("1");
        List<NameToRiskMaterial> nameToRiskMaterialListLevelOne = nameToRiskMaterialService.findList(nameToRiskMaterial);
        if (nameToRiskMaterialListLevelOne != null && nameToRiskMaterialListLevelOne.size() > 0) {
            for (NameToRiskMaterial nameToRiskMaterial1 : nameToRiskMaterialListLevelOne) {
                if (standardChineseName.equals(nameToRiskMaterial1.getStandardChineseName())) {
                    riskMaterial = nameToRiskMaterial1.getRiskMaterial();
                    isOk = true;
                    break;
                }
            }
        }

        if (!isOk) { //没有匹配到，开始模糊匹配
            //查询所有模糊转换的信息，并按照优先级别进行排序，数字越高优先级越大
            List<NameToRiskMaterial> nameToRiskMaterialListLevelOther = nameToRiskMaterialService.selectAllLevelOther();
            if (nameToRiskMaterialListLevelOther != null && nameToRiskMaterialListLevelOther.size() > 0) {
                for (NameToRiskMaterial nameToRiskMaterialOther : nameToRiskMaterialListLevelOther) {
                    if (standardChineseName.contains(nameToRiskMaterialOther.getStandardChineseName())) {
                        riskMaterial = nameToRiskMaterialOther.getRiskMaterial();
                        isOk = true;
                        break;
                    }
                }
            }
        }
        return riskMaterial;
    }

    /**
     * 配方导入 ，重新统计原料使用数据库
     * 之前的时间进行逻辑删除
     */
    private void handleMaterialUsedDatabase() {
        //删除之前的统计信息，逻辑删除
        List<MaterialUsedDatabase> materialUsedDatabaseList = materialUsedDatabaseService.findList(new MaterialUsedDatabase());

        for (MaterialUsedDatabase materialUsedDatabase : materialUsedDatabaseList) {
            materialUsedDatabaseService.delete(materialUsedDatabase);
        }
        //统计新的数据
        List<MaterialUsedDatabase> materialUsedDatabases = formulaDetailsService.selectGroupStandardChineseName();
        for (MaterialUsedDatabase materialUsedDatabase : materialUsedDatabases) {
            materialUsedDatabaseService.save(materialUsedDatabase);
        }
    }

}