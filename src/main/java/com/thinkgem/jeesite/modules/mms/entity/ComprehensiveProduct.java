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
 * @version 2017-03-12
 */
public class ComprehensiveProduct extends DataEntity<ComprehensiveProduct> {
	
	private static final long serialVersionUID = 1L;
	private Date sampleTime;		// 来样时间
	private String sampleQuantity;		// 样品数量
	private String administrativeLicenseInspection;//行政许可检验数据
	private String administrativeLicenseInspectionNumber;//行政许可检验编号
	private Date administrativeLicenseInspectionReportTime;		// 行政许可检验取报告时间

	private String humanTestAcceptanceNumber;//人体检验受理编号
	private Date humanTestAcceptanceReportTime;		// 人体检验取报告时间

	private String riskTestAcceptanceNumber;//风险检验受理编号
	private Date riskTestAcceptanceReportTime;		// 风险检验取报告时间

	private String productHandlePersonId;		// 产品处理人id
	private String productNextHandlePersonId;		// 产品下一环节处理人id

	private MarketProduct marketProduct;		// 市场产品
	
	public ComprehensiveProduct() {
		super();
	}

	public ComprehensiveProduct(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

	public MarketProduct getMarketProduct() {
		return marketProduct;
	}

	public void setMarketProduct(MarketProduct marketProduct) {
		this.marketProduct = marketProduct;
	}

	public String getAdministrativeLicenseInspection() {
		return administrativeLicenseInspection;
	}

	public void setAdministrativeLicenseInspection(String administrativeLicenseInspection) {
		this.administrativeLicenseInspection = administrativeLicenseInspection;
	}

	public String getAdministrativeLicenseInspectionNumber() {
		return administrativeLicenseInspectionNumber;
	}

	public void setAdministrativeLicenseInspectionNumber(String administrativeLicenseInspectionNumber) {
		this.administrativeLicenseInspectionNumber = administrativeLicenseInspectionNumber;
	}

	public Date getAdministrativeLicenseInspectionReportTime() {
		return administrativeLicenseInspectionReportTime;
	}

	public void setAdministrativeLicenseInspectionReportTime(Date administrativeLicenseInspectionReportTime) {
		this.administrativeLicenseInspectionReportTime = administrativeLicenseInspectionReportTime;
	}

	public String getHumanTestAcceptanceNumber() {
		return humanTestAcceptanceNumber;
	}

	public void setHumanTestAcceptanceNumber(String humanTestAcceptanceNumber) {
		this.humanTestAcceptanceNumber = humanTestAcceptanceNumber;
	}

	public Date getHumanTestAcceptanceReportTime() {
		return humanTestAcceptanceReportTime;
	}

	public void setHumanTestAcceptanceReportTime(Date humanTestAcceptanceReportTime) {
		this.humanTestAcceptanceReportTime = humanTestAcceptanceReportTime;
	}

	public String getRiskTestAcceptanceNumber() {
		return riskTestAcceptanceNumber;
	}

	public void setRiskTestAcceptanceNumber(String riskTestAcceptanceNumber) {
		this.riskTestAcceptanceNumber = riskTestAcceptanceNumber;
	}

	public Date getRiskTestAcceptanceReportTime() {
		return riskTestAcceptanceReportTime;
	}

	public void setRiskTestAcceptanceReportTime(Date riskTestAcceptanceReportTime) {
		this.riskTestAcceptanceReportTime = riskTestAcceptanceReportTime;
	}
}