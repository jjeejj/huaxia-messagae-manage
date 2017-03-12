/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 市场产品Entity
 * @author jiang
 * @version 2017-03-12
 */
public class MarketProduct extends DataEntity<MarketProduct> {
	
	private static final long serialVersionUID = 1L;
	private String productNumber;		// 产品编号
	private String englishName;		// 英文名称
	private String chineseName;		// 中文名称
	private String productType;		// 类别
	private String workMatters;		// 工作事项
	private String productLeader;		// 产品负责人
	private String projectLeader;		// 项目负责人
	private String enterpriseApplication;		// 申请企业
	private String actualProductionEnterprise;		// 实际生产企业
	private String responsibleUnitInChina;		// 在华责任单位
	private Date projectTime;		// 立项时间
	private Date contractSigningTime;		// 合同签订时间

	private String productHandlePersonId;		// 产品处理人id
	private String productNextHandlePersonId;		// 产品下一环节处理人id

	public MarketProduct() {
		super();
	}

	public MarketProduct(String id){
		super(id);
	}

	@Length(min=1, max=64, message="产品编号长度必须介于 1 和 64 之间")
	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	
	@Length(min=1, max=100, message="中文名称长度必须介于 1 和 100 之间")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Length(min=1, max=100, message="英文名称长度必须介于 1 和 100 之间")
	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	@Length(min=0, max=1, message="类别长度必须介于 0 和 1 之间")
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
//	@Length(min=1, max=100, message="工作事项长度必须介于 1 和 100 之间")
	public String getWorkMatters() {
		return workMatters;
	}

	public void setWorkMatters(String workMatters) {
		this.workMatters = workMatters;
	}
	
	@Length(min=1, max=64, message="产品负责人长度必须介于 1 和 64 之间")
	public String getProductLeader() {
		return productLeader;
	}

	public void setProductLeader(String productLeader) {
		this.productLeader = productLeader;
	}
	
	@Length(min=1, max=64, message="项目负责人长度必须介于 1 和 64 之间")
	public String getProjectLeader() {
		return projectLeader;
	}

	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;
	}
	
	@Length(min=1, max=64, message="申请企业长度必须介于 1 和 64 之间")
	public String getEnterpriseApplication() {
		return enterpriseApplication;
	}

	public void setEnterpriseApplication(String enterpriseApplication) {
		this.enterpriseApplication = enterpriseApplication;
	}
	
	@Length(min=0, max=64, message="实际生产企业长度必须介于 0 和 64 之间")
	public String getActualProductionEnterprise() {
		return actualProductionEnterprise;
	}

	public void setActualProductionEnterprise(String actualProductionEnterprise) {
		this.actualProductionEnterprise = actualProductionEnterprise;
	}
	
	@Length(min=0, max=64, message="在华责任单位长度必须介于 0 和 64 之间")
	public String getResponsibleUnitInChina() {
		return responsibleUnitInChina;
	}

	public void setResponsibleUnitInChina(String responsibleUnitInChina) {
		this.responsibleUnitInChina = responsibleUnitInChina;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getProjectTime() {
		return projectTime;
	}

	public void setProjectTime(Date projectTime) {
		this.projectTime = projectTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getContractSigningTime() {
		return contractSigningTime;
	}

	public void setContractSigningTime(Date contractSigningTime) {
		this.contractSigningTime = contractSigningTime;
	}

	public String getProductHandlePersonId() {
		return productHandlePersonId;
	}

	public void setProductHandlePersonId(String productHandlePersonId) {
		this.productHandlePersonId = productHandlePersonId;
	}

	public String getProductNextHandlePersonId() {
		return productNextHandlePersonId;
	}

	public void setProductNextHandlePersonId(String productNextHandlePersonId) {
		this.productNextHandlePersonId = productNextHandlePersonId;
	}
}