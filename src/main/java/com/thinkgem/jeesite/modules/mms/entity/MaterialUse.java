/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 原料使用数据库Entity
 * @author jiang
 * @version 2017-03-27
 */
public class MaterialUse extends DataEntity<MaterialUse> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 原料序号（导入配方详情的每一项的序号）
	private String standardChineseName;		// 标准中文名称
	private String minActualComponentContent;		// 最小实际成份含量（%）
	private String maxActualComponentContent;		// 最大实际成份含量（%）
	private String purposeOfUse;		// 使用目的
	private String riskMaterial;		// 风险物质
	
	public MaterialUse() {
		super();
	}

	public MaterialUse(String id){
		super(id);
	}

	@Length(min=1, max=64, message="原料序号（导入配方详情的每一项的序号）长度必须介于 1 和 64 之间")
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
	
	@Length(min=0, max=32, message="最小实际成份含量（%）长度必须介于 0 和 32 之间")
	public String getMinActualComponentContent() {
		return minActualComponentContent;
	}

	public void setMinActualComponentContent(String minActualComponentContent) {
		this.minActualComponentContent = minActualComponentContent;
	}
	
	@Length(min=0, max=32, message="最大实际成份含量（%）长度必须介于 0 和 32 之间")
	public String getMaxActualComponentContent() {
		return maxActualComponentContent;
	}

	public void setMaxActualComponentContent(String maxActualComponentContent) {
		this.maxActualComponentContent = maxActualComponentContent;
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
	
}