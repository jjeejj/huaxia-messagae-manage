/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 申报产品Entity
 * @author jiang
 * @version 2017-03-12
 */
public class DeclareProduct extends DataEntity<DeclareProduct> {
	
	private static final long serialVersionUID = 1L;
	private String totalSubmission;		// 送检总数(这个是算出来的)

	private Date administrativeLicenseInspectionTime;		// 行政许可检送检时间
	private String administrativeLicenseInspectionOrganization;		//行政许可检验机构
	private String administrativeLicenseInspectionProject;		//行政许可检验项目
	private String administrativeLicenseInspectionNumber;		//行政许可送检数量

	private Date sendBodyTime;		// 送人体检验时间
	private String sendBodyOrganization;		// 送人体检验机构
	private String sendBodyProject;		// 送人体检验项目
	private String sendBodyNumber;		// 送人体报告数量

	private Date sendRistTestTime;		// 送风险检验时间
	private String sendRistTestOrganization;		// 送风险检验机构
	private String sendRistTestProject;		// 送风险检验项目
	private String sendRistTestNumber;		// 送风险报告数量

	private Date reportTime;		// 上报时间
	private Date acceptanceTime;		// 受理时间
	private String acceptanceNumber;		// 受理编号
	private Date documentTime;		// 批件时间
	private String documentNumber;		// 批件编号
	private Date nextOpinionTime;		// 下意见时间
	private String opinionContent;		// 下意见内容
	private Date replyOpinion;		// 回复意见时间
	private String productHandlePersonId;		// 产品处理人id

	private String colorCharacter;		// 颜色性状
	private String sampleMarking;		// 样品标记（生产日期或批号）
	private Date dateOfExpiry;		// 保质期或限期使用日期
	private String specifications;		// 规格


		private MarketProduct marketProduct;		// 市场产品
	
	public DeclareProduct() {
		super();
	}

	public DeclareProduct(String id){
		super(id);
	}
	
	@Length(min=0, max=10, message="送人体时间长度必须介于 0 和 10 之间")
	public Date getSendBodyTime() {
		return sendBodyTime;
	}

	public void setSendBodyTime(Date sendBodyTime) {
		this.sendBodyTime = sendBodyTime;
	}

	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDocumentTime() {
		return documentTime;
	}

	public void setDocumentTime(Date documentTime) {
		this.documentTime = documentTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNextOpinionTime() {
		return nextOpinionTime;
	}

	public void setNextOpinionTime(Date nextOpinionTime) {
		this.nextOpinionTime = nextOpinionTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReplyOpinion() {
		return replyOpinion;
	}

	public void setReplyOpinion(Date replyOpinion) {
		this.replyOpinion = replyOpinion;
	}

	public String getProductHandlePersonId() {
		return productHandlePersonId;
	}

	public void setProductHandlePersonId(String productHandlePersonId) {
		this.productHandlePersonId = productHandlePersonId;
	}

	public MarketProduct getMarketProduct() {
		return marketProduct;
	}

	public void setMarketProduct(MarketProduct marketProduct) {
		this.marketProduct = marketProduct;
	}

	public String getTotalSubmission() {
		return totalSubmission;
	}

	public void setTotalSubmission(String totalSubmission) {
		this.totalSubmission = totalSubmission;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAdministrativeLicenseInspectionTime() {
		return administrativeLicenseInspectionTime;
	}

	public void setAdministrativeLicenseInspectionTime(Date administrativeLicenseInspectionTime) {
		this.administrativeLicenseInspectionTime = administrativeLicenseInspectionTime;
	}

	public String getAdministrativeLicenseInspectionOrganization() {
		return administrativeLicenseInspectionOrganization;
	}

	public void setAdministrativeLicenseInspectionOrganization(String administrativeLicenseInspectionOrganization) {
		this.administrativeLicenseInspectionOrganization = administrativeLicenseInspectionOrganization;
	}

	public String getAdministrativeLicenseInspectionProject() {
		return administrativeLicenseInspectionProject;
	}

	public void setAdministrativeLicenseInspectionProject(String administrativeLicenseInspectionProject) {
		this.administrativeLicenseInspectionProject = administrativeLicenseInspectionProject;
	}

	public String getAdministrativeLicenseInspectionNumber() {
		return administrativeLicenseInspectionNumber;
	}

	public void setAdministrativeLicenseInspectionNumber(String administrativeLicenseInspectionNumber) {
		this.administrativeLicenseInspectionNumber = administrativeLicenseInspectionNumber;
	}

	public String getSendBodyOrganization() {
		return sendBodyOrganization;
	}

	public void setSendBodyOrganization(String sendBodyOrganization) {
		this.sendBodyOrganization = sendBodyOrganization;
	}

	public String getSendBodyProject() {
		return sendBodyProject;
	}

	public void setSendBodyProject(String sendBodyProject) {
		this.sendBodyProject = sendBodyProject;
	}

	public String getSendBodyNumber() {
		return sendBodyNumber;
	}

	public void setSendBodyNumber(String sendBodyNumber) {
		this.sendBodyNumber = sendBodyNumber;
	}

	public Date getSendRistTestTime() {
		return sendRistTestTime;
	}

	public void setSendRistTestTime(Date sendRistTestTime) {
		this.sendRistTestTime = sendRistTestTime;
	}

	public String getSendRistTestOrganization() {
		return sendRistTestOrganization;
	}

	public void setSendRistTestOrganization(String sendRistTestOrganization) {
		this.sendRistTestOrganization = sendRistTestOrganization;
	}

	public String getSendRistTestProject() {
		return sendRistTestProject;
	}

	public void setSendRistTestProject(String sendRistTestProject) {
		this.sendRistTestProject = sendRistTestProject;
	}

	public String getSendRistTestNumber() {
		return sendRistTestNumber;
	}

	public void setSendRistTestNumber(String sendRistTestNumber) {
		this.sendRistTestNumber = sendRistTestNumber;
	}

	public String getAcceptanceNumber() {
		return acceptanceNumber;
	}

	public void setAcceptanceNumber(String acceptanceNumber) {
		this.acceptanceNumber = acceptanceNumber;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
	}

	public String getColorCharacter() {
		return colorCharacter;
	}

	public void setColorCharacter(String colorCharacter) {
		this.colorCharacter = colorCharacter;
	}

	public String getSampleMarking() {
		return sampleMarking;
	}

	public void setSampleMarking(String sampleMarking) {
		this.sampleMarking = sampleMarking;
	}

	public Date getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(Date dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
}