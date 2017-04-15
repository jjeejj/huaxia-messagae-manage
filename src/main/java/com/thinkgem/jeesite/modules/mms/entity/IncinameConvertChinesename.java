/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * inci名与标准中文名相互转换Entity
 * @author jiang
 * @version 2017-04-05
 */
public class IncinameConvertChinesename extends DataEntity<IncinameConvertChinesename> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String standardChineseName;		// 标准中文名称
	private String inciName;		// INCI名
	private String casNumber;		// CAS号
	
	public IncinameConvertChinesename() {
		super();
	}

	public IncinameConvertChinesename(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标准中文名称长度必须介于 1 和 200 之间")
	@ExcelField(title = "标准中文名称",sort = 20)
	public String getStandardChineseName() {
		return standardChineseName;
	}

	public void setStandardChineseName(String standardChineseName) {
		this.standardChineseName = standardChineseName;
	}
	
	@Length(min=0, max=200, message="INCI名长度必须介于 0 和 200 之间")
	@ExcelField(title = "INCI名",sort = 10)
	public String getInciName() {
		return inciName;
	}

	public void setInciName(String inciName) {
		this.inciName = inciName;
	}
	
	@Length(min=0, max=200, message="CAS号长度必须介于 0 和 200 之间")
	@ExcelField(title = "CAS号",sort = 30)
	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}

	@ExcelField(title = "序号",sort = 5)
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}