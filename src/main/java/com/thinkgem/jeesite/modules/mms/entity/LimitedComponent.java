/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.Length(min=0, max=10, message="适用及;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 化妆品安全技术规范的限用成分Entity
 * @author jiang
 * @version 2017-02-28
 */
public class LimitedComponent extends DataEntity<LimitedComponent> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String standardChineseName;		// 标准中文名称
	private String inicName;		// INIC名
	private String englishName;		// 英文名称
	private String useRange;		// 适用及(或)使用范围
	private String maxAllowConcentretion;		// 最大允许浓度
	
	public LimitedComponent() {
		super();
	}

	public LimitedComponent(String id){
		super(id);
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
	
	@Length(min=0, max=100, message="INIC名长度必须介于 0 和 100 之间")
	public String getInicName() {
		return inicName;
	}

	public void setInicName(String inicName) {
		this.inicName = inicName;
	}
	
	@Length(min=0, max=100, message="英文名称长度必须介于 0 和 100 之间")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Length(min=0, max=10, message="适用及(或)使用范围长度必须介于 0 和 10 之间")
	public String getUseRange() {
		return useRange;
	}

	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	
	@Length(min=0, max=10, message="最大允许浓度长度必须介于 0 和 10 之间")
	public String getMaxAllowConcentretion() {
		return maxAllowConcentretion;
	}

	public void setMaxAllowConcentretion(String maxAllowConcentretion) {
		this.maxAllowConcentretion = maxAllowConcentretion;
	}
	
}