/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.Length(min=0, max=1, message="转换查询级别");

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 标准中文名对应的风险物质Entity
 * @author jiang
 * @version 2017-02-26
 */
public class NameToRiskMaterial extends DataEntity<NameToRiskMaterial> {
	
	private static final long serialVersionUID = 1L;
	private String standardChineseName;		// 标准中文名称
	private String riskMaterial;		// 风险物质
	private String transformLevel;		// 转换查询级别(1:精确转换，2：模糊转换)
	
	public NameToRiskMaterial() {
		super();
	}

	public NameToRiskMaterial(String id){
		super(id);
	}

	@Length(min=1, max=100, message="标准中文名称长度必须介于 1 和 100 之间")
	public String getStandardChineseName() {
		return standardChineseName;
	}

	public void setStandardChineseName(String standardChineseName) {
		this.standardChineseName = standardChineseName;
	}
	
	@Length(min=0, max=100, message="风险物质长度必须介于 0 和 100 之间")
	public String getRiskMaterial() {
		return riskMaterial;
	}

	public void setRiskMaterial(String riskMaterial) {
		this.riskMaterial = riskMaterial;
	}
	
	@Length(min=0, max=1, message="转换查询级别(1:精确转换，2：模糊转换)长度必须介于 0 和 1 之间")
	public String getTransformLevel() {
		return transformLevel;
	}

	public void setTransformLevel(String transformLevel) {
		this.transformLevel = transformLevel;
	}
	
}