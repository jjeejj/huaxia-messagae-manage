/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * inci名与标准中文名相互转换Entity
 * @author jiang
 * @version 2017-04-05
 */
public class IncinameConvertChinesename extends DataEntity<IncinameConvertChinesename> {
	
	private static final long serialVersionUID = 1L;
	private String standardChineseName;		// 标准中文名称
	private String inciName;		// INCI名
	private String casNumber;		// CAS号
	
	public IncinameConvertChinesename() {
		super();
	}

	public IncinameConvertChinesename(String id){
		super(id);
	}

	@Length(min=1, max=100, message="标准中文名称长度必须介于 1 和 100 之间")
	public String getStandardChineseName() {
		return standardChineseName;
	}

	public void setStandardChineseName(String standardChineseName) {
		this.standardChineseName = standardChineseName;
	}
	
	@Length(min=0, max=100, message="INCI名长度必须介于 0 和 100 之间")
	public String getInciName() {
		return inciName;
	}

	public void setInciName(String inciName) {
		this.inciName = inciName;
	}
	
	@Length(min=0, max=64, message="CAS号长度必须介于 0 和 64 之间")
	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}
	
}