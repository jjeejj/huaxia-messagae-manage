/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 机构信息Entity
 * @author jiang
 * @version 2017-04-20
 */
public class InstitutionalInformation extends DataEntity<InstitutionalInformation> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String institutionalName;		// 机构名称
	private String institutionalType;		// 机构名称分类（1：风险检验机构、2：人体检验机构、3：行政许可检验机构）
	
	public InstitutionalInformation() {
		super();
	}

	public InstitutionalInformation(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=64, message="机构名称长度必须介于 1 和 64 之间")
	public String getInstitutionalName() {
		return institutionalName;
	}

	public void setInstitutionalName(String institutionalName) {
		this.institutionalName = institutionalName;
	}
	
	@Length(min=1, max=1, message="机构名称分类（1：风险检验机构、2：人体检验机构、3：行政许可检验机构）长度必须介于 1 和 1 之间")
	public String getInstitutionalType() {
		return institutionalType;
	}

	public void setInstitutionalType(String institutionalType) {
		this.institutionalType = institutionalType;
	}
	
}