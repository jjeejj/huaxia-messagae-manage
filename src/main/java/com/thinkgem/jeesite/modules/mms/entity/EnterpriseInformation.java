/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 企业信息Entity
 * @author jiang
 * @version 2017-04-22
 */
public class EnterpriseInformation extends DataEntity<EnterpriseInformation> {
	
	private static final long serialVersionUID = 1L;
	private String enterpriseName;		// 企业名称
	private String enterpriseAddress;		// 企业地址
	private String enterpriseType;		// 企业类型（1：申请企业、2：实际生产企、3：在华责任单位）
	private String enterprisePhone;		// 企业电话
	private String enterpriseContacts;		// 企业联系人
	private String nterpriseFax;		// 企业传真
	private String rnterpriseZipCode;		// 企业邮编
	
	public EnterpriseInformation() {
		super();
	}

	public EnterpriseInformation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="企业名称长度必须介于 0 和 64 之间")
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	
	@Length(min=0, max=100, message="企业地址长度必须介于 0 和 100 之间")
	public String getEnterpriseAddress() {
		return enterpriseAddress;
	}

	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}
	
	@Length(min=1, max=1, message="企业类型长度必须介于 1 和 1 之间")
	public String getEnterpriseType() {
		return enterpriseType;
	}

	public void setEnterpriseType(String enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	
	@Length(min=0, max=32, message="企业电话长度必须介于 0 和 32 之间")
	public String getEnterprisePhone() {
		return enterprisePhone;
	}

	public void setEnterprisePhone(String enterprisePhone) {
		this.enterprisePhone = enterprisePhone;
	}
	
	@Length(min=0, max=100, message="企业联系人长度必须介于 0 和 100 之间")
	public String getEnterpriseContacts() {
		return enterpriseContacts;
	}

	public void setEnterpriseContacts(String enterpriseContacts) {
		this.enterpriseContacts = enterpriseContacts;
	}
	
	@Length(min=0, max=64, message="企业传真长度必须介于 0 和 64 之间")
	public String getNterpriseFax() {
		return nterpriseFax;
	}

	public void setNterpriseFax(String nterpriseFax) {
		this.nterpriseFax = nterpriseFax;
	}
	
	@Length(min=0, max=64, message="企业邮编长度必须介于 0 和 64 之间")
	public String getRnterpriseZipCode() {
		return rnterpriseZipCode;
	}

	public void setRnterpriseZipCode(String rnterpriseZipCode) {
		this.rnterpriseZipCode = rnterpriseZipCode;
	}
	
}