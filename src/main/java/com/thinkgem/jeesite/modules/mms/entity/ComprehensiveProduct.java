/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 综合产品Entity
 * @author jiang
 * @version 2017-03-28
 */
public class ComprehensiveProduct extends DataEntity<ComprehensiveProduct> {
	
	private static final long serialVersionUID = 1L;
	private Date arrivalTime;		// 来款时间
	private Date sampleTime;		// 来样时间
	private String sampleQuantity;		// 样品数量
	private String administrativeLicenseInspectionNo;		// 行政许可检验受理编号
	private Date administrativeLicenseInspectionReportTime;		// 行政许可检验取报告时间
	private String humanTestAcceptanceNo;		// 人体检验受理编号
	private Date humanTestAcceptanceReportTime;		// 人体检验取报告时间
	private String riskTestAcceptanceNo;		// 风险检验受理编号
	private Date riskTestAcceptanceReportTime;		// 风险检验取报告时间
	private String productHandlePersonId;		// 产品处理人id
	
	public ComprehensiveProduct() {
		super();
	}

	public ComprehensiveProduct(String id){
		super(id);
	}

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSampleTime() {
		return sampleTime;
	}

	public void setSampleTime(Date sampleTime) {
		this.sampleTime = sampleTime;
	}
	
	@Length(min=0, max=10, message="样品数量长度必须介于 0 和 10 之间")
	public String getSampleQuantity() {
		return sampleQuantity;
	}

	public void setSampleQuantity(String sampleQuantity) {
		this.sampleQuantity = sampleQuantity;
	}
	
	@Length(min=0, max=64, message="行政许可检验受理编号长度必须介于 0 和 64 之间")
	public String getAdministrativeLicenseInspectionNo() {
		return administrativeLicenseInspectionNo;
	}

	public void setAdministrativeLicenseInspectionNo(String administrativeLicenseInspectionNo) {
		this.administrativeLicenseInspectionNo = administrativeLicenseInspectionNo;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAdministrativeLicenseInspectionReportTime() {
		return administrativeLicenseInspectionReportTime;
	}

	public void setAdministrativeLicenseInspectionReportTime(Date administrativeLicenseInspectionReportTime) {
		this.administrativeLicenseInspectionReportTime = administrativeLicenseInspectionReportTime;
	}
	
	@Length(min=0, max=64, message="人体检验受理编号长度必须介于 0 和 64 之间")
	public String getHumanTestAcceptanceNo() {
		return humanTestAcceptanceNo;
	}

	public void setHumanTestAcceptanceNo(String humanTestAcceptanceNo) {
		this.humanTestAcceptanceNo = humanTestAcceptanceNo;
	}
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHumanTestAcceptanceReportTime() {
		return humanTestAcceptanceReportTime;
	}

	public void setHumanTestAcceptanceReportTime(Date humanTestAcceptanceReportTime) {
		this.humanTestAcceptanceReportTime = humanTestAcceptanceReportTime;
	}
	
	@Length(min=0, max=64, message="风险检验受理编号长度必须介于 0 和 64 之间")
	public String getRiskTestAcceptanceNo() {
		return riskTestAcceptanceNo;
	}

	public void setRiskTestAcceptanceNo(String riskTestAcceptanceNo) {
		this.riskTestAcceptanceNo = riskTestAcceptanceNo;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRiskTestAcceptanceReportTime() {
		return riskTestAcceptanceReportTime;
	}

	public void setRiskTestAcceptanceReportTime(Date riskTestAcceptanceReportTime) {
		this.riskTestAcceptanceReportTime = riskTestAcceptanceReportTime;
	}
	
	@Length(min=1, max=64, message="产品处理人id长度必须介于 1 和 64 之间")
	public String getProductHandlePersonId() {
		return productHandlePersonId;
	}

	public void setProductHandlePersonId(String productHandlePersonId) {
		this.productHandlePersonId = productHandlePersonId;
	}
	
}