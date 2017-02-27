/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配方详情信息Entity
 * @author jiang
 * @version 2017-02-27
 */
public class FormulaDetails extends DataEntity<FormulaDetails> {
	
	private static final long serialVersionUID = 1L;
	private String formulaId;		// 配方id
	private String sequence;		// 序号
	private String standardChineseName;		// 标准中文名称
	private String inicName;		// INCI名
	private String rawMaterialContent;		// 原料含量（%）
	private String compoundPercentage;		// 复配百分比（%）
	private String actualComponentContent;		// 实际成份含量（%）
	private String purposeOfUse;		// 使用目的
	private String riskMaterial;		// 风险物质
	private String componentType;		// 成分类型
	private String actualComponentContentStatus;		// 对于限用成分的实际成份含量状态
	private String nameOrInicStatus;		// 标准中文名称或INCI名的状态
	private String plantComponent;		// 是否是植物成分
	
	public FormulaDetails() {
		super();
	}

	public FormulaDetails(String id){
		super(id);
	}

	@Length(min=1, max=64, message="配方id长度必须介于 1 和 64 之间")
	public String getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(String formulaId) {
		this.formulaId = formulaId;
	}
	
	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=100, message="标准中文名称长度必须介于 1 和 100 之间")
	public String getStandardChineseName() {
		return standardChineseName;
	}

	public void setStandardChineseName(String standardChineseName) {
		this.standardChineseName = standardChineseName;
	}
	
	@Length(min=0, max=100, message="INCI名长度必须介于 0 和 100 之间")
	public String getInicName() {
		return inicName;
	}

	public void setInicName(String inicName) {
		this.inicName = inicName;
	}
	
	@Length(min=0, max=10, message="原料含量（%）长度必须介于 0 和 10 之间")
	public String getRawMaterialContent() {
		return rawMaterialContent;
	}

	public void setRawMaterialContent(String rawMaterialContent) {
		this.rawMaterialContent = rawMaterialContent;
	}
	
	@Length(min=0, max=10, message="复配百分比（%）长度必须介于 0 和 10 之间")
	public String getCompoundPercentage() {
		return compoundPercentage;
	}

	public void setCompoundPercentage(String compoundPercentage) {
		this.compoundPercentage = compoundPercentage;
	}
	
	@Length(min=0, max=10, message="实际成份含量（%）长度必须介于 0 和 10 之间")
	public String getActualComponentContent() {
		return actualComponentContent;
	}

	public void setActualComponentContent(String actualComponentContent) {
		this.actualComponentContent = actualComponentContent;
	}
	
	@Length(min=0, max=100, message="使用目的长度必须介于 0 和 100 之间")
	public String getPurposeOfUse() {
		return purposeOfUse;
	}

	public void setPurposeOfUse(String purposeOfUse) {
		this.purposeOfUse = purposeOfUse;
	}
	
	@Length(min=0, max=100, message="风险物质长度必须介于 0 和 100 之间")
	public String getRiskMaterial() {
		return riskMaterial;
	}

	public void setRiskMaterial(String riskMaterial) {
		this.riskMaterial = riskMaterial;
	}
	
	@Length(min=0, max=1, message="成分类型长度必须介于 0 和 1 之间")
	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}
	
	@Length(min=0, max=1, message="对于限用成分的实际成份含量状态长度必须介于 0 和 1 之间")
	public String getActualComponentContentStatus() {
		return actualComponentContentStatus;
	}

	public void setActualComponentContentStatus(String actualComponentContentStatus) {
		this.actualComponentContentStatus = actualComponentContentStatus;
	}
	
	@Length(min=0, max=1, message="标准中文名称或INCI名的状态长度必须介于 0 和 1 之间")
	public String getNameOrInicStatus() {
		return nameOrInicStatus;
	}

	public void setNameOrInicStatus(String nameOrInicStatus) {
		this.nameOrInicStatus = nameOrInicStatus;
	}
	
	@Length(min=0, max=1, message="是否是植物成分长度必须介于 0 和 1 之间")
	public String getPlantComponent() {
		return plantComponent;
	}

	public void setPlantComponent(String plantComponent) {
		this.plantComponent = plantComponent;
	}
	
}