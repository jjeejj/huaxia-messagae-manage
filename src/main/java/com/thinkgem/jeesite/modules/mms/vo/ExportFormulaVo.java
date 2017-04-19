package com.thinkgem.jeesite.modules.mms.vo;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 导入的配方的基本信息
 * Created by jjeejj on 2017/2/28.
 */
public class ExportFormulaVo extends DataEntity {

    private String formulaName; //配方名称
    private String formulaSequence; //配方序号
    private String productNumber;		// 产品编号
    private String formulaDetailsSequence; //配方详情序号
    private String formulaDetailsStandardChineseName; //配方详情标准中文名称
    private String formulaDetailsInicName; //配方详情INCI名
    private String formulaDetailsRawMaterialContent; //配方详情原料含量（%）
    private String formulaDetailsCompoundPercentage; //配方详情复配百分比（%）
    private String formulaDetailsPurposeOfUse; //配方详情使用目的
    private String formulaRemarks; //配方备注信息

    @ExcelField(title = "配方名称",sort = 100)
    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

//    @ExcelField(title = "配方序号",sort = 110)
    public String getFormulaSequence() {
        return formulaSequence;
    }

    public void setFormulaSequence(String formulaSequence) {
        this.formulaSequence = formulaSequence;
    }

    @ExcelField(title = "配方详情序号",sort = 30)
    public String getFormulaDetailsSequence() {
        return formulaDetailsSequence;
    }

    public void setFormulaDetailsSequence(String formulaDetailsSequence) {
        this.formulaDetailsSequence = formulaDetailsSequence;
    }
    @ExcelField(title = "配方详情标准中文名称",sort = 40)
    public String getFormulaDetailsStandardChineseName() {
        return formulaDetailsStandardChineseName;
    }

    public void setFormulaDetailsStandardChineseName(String formulaDetailsStandardChineseName) {
        this.formulaDetailsStandardChineseName = formulaDetailsStandardChineseName;
    }

    @ExcelField(title = "配方详情INCI名",sort = 50)
    public String getFormulaDetailsInicName() {
        return formulaDetailsInicName;
    }

    public void setFormulaDetailsInicName(String formulaDetailsInicName) {
        this.formulaDetailsInicName = formulaDetailsInicName;
    }

    @ExcelField(title = "配方详情原料含量（%）",sort = 60)
    public String getFormulaDetailsRawMaterialContent() {
        return formulaDetailsRawMaterialContent;
    }

    public void setFormulaDetailsRawMaterialContent(String formulaDetailsRawMaterialContent) {
        this.formulaDetailsRawMaterialContent = formulaDetailsRawMaterialContent;
    }

    @ExcelField(title = "配方详情复配百分比（%）",sort = 70)
    public String getFormulaDetailsCompoundPercentage() {
        return formulaDetailsCompoundPercentage;
    }

    public void setFormulaDetailsCompoundPercentage(String formulaDetailsCompoundPercentage) {
        this.formulaDetailsCompoundPercentage = formulaDetailsCompoundPercentage;
    }

    @ExcelField(title = "配方详情使用目的",sort = 80)
    public String getFormulaDetailsPurposeOfUse() {
        return formulaDetailsPurposeOfUse;
    }

    public void setFormulaDetailsPurposeOfUse(String formulaDetailsPurposeOfUse) {
        this.formulaDetailsPurposeOfUse = formulaDetailsPurposeOfUse;
    }
    @ExcelField(title = "配方备注信息",sort = 90)
    public String getFormulaRemarks() {
        return formulaRemarks;
    }


    public void setFormulaRemarks(String formulaRemarks) {
        this.formulaRemarks = formulaRemarks;
    }

    @ExcelField(title = "产品编号",sort = 120)
    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }
}
