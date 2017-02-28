package com.thinkgem.jeesite.modules.mms.vo;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 导入的配方的基本信息
 * Created by jjeejj on 2017/2/28.
 */
public class ExportFormulaVo extends DataEntity {

    private String formulaName; //配方名称
    private String formulaSequence; //配方序号
    private String formulaDetailsSequence; //配方详情序号
    private String formulaDetailsStandardChineseName; //配方详情标准中文名称
    private String formulaDetailsInicName; //配方详情INCI名
    private String formulaDetailsRawMaterialContent; //配方详情原料含量（%）
    private String formulaDetailsCompoundPercentage; //配方详情复配百分比（%）
    private String formulaDetailsPurposeOfUse; //配方详情使用目的

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormulaSequence() {
        return formulaSequence;
    }

    public void setFormulaSequence(String formulaSequence) {
        this.formulaSequence = formulaSequence;
    }

    public String getFormulaDetailsSequence() {
        return formulaDetailsSequence;
    }

    public void setFormulaDetailsSequence(String formulaDetailsSequence) {
        this.formulaDetailsSequence = formulaDetailsSequence;
    }

    public String getFormulaDetailsStandardChineseName() {
        return formulaDetailsStandardChineseName;
    }

    public void setFormulaDetailsStandardChineseName(String formulaDetailsStandardChineseName) {
        this.formulaDetailsStandardChineseName = formulaDetailsStandardChineseName;
    }

    public String getFormulaDetailsInicName() {
        return formulaDetailsInicName;
    }

    public void setFormulaDetailsInicName(String formulaDetailsInicName) {
        this.formulaDetailsInicName = formulaDetailsInicName;
    }

    public String getFormulaDetailsRawMaterialContent() {
        return formulaDetailsRawMaterialContent;
    }

    public void setFormulaDetailsRawMaterialContent(String formulaDetailsRawMaterialContent) {
        this.formulaDetailsRawMaterialContent = formulaDetailsRawMaterialContent;
    }

    public String getFormulaDetailsCompoundPercentage() {
        return formulaDetailsCompoundPercentage;
    }

    public void setFormulaDetailsCompoundPercentage(String formulaDetailsCompoundPercentage) {
        this.formulaDetailsCompoundPercentage = formulaDetailsCompoundPercentage;
    }

    public String getFormulaDetailsPurposeOfUse() {
        return formulaDetailsPurposeOfUse;
    }

    public void setFormulaDetailsPurposeOfUse(String formulaDetailsPurposeOfUse) {
        this.formulaDetailsPurposeOfUse = formulaDetailsPurposeOfUse;
    }
}
