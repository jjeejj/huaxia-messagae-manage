/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 申报产品Entity
 * @author jiang
 * @version 2017-03-28
 */
public class DeclareProduct extends DataEntity<DeclareProduct> {
	
	private static final long serialVersionUID = 1L;
	private String totalSubmission;		// 送检总数
	private Date administrativeLicenseInspectionTime;		// 行政许可送检时间
	private String administrativeLicenseInspectionOrganization;		// 行政许可检验机构
	private String administrativeLicenseInspectionProject;		// 行政许可检验项目
	private String administrativeLicenseInspectionNumber;		// 行政许可送检数量
	private Date sendBodyTime;		// 人体检验送检时间
	private String sendBodyOrganization;		// 人体检验送检机构
	private String sendBodyProject;		// 人体检验项目
	private String sendBodyNumber;		// 人体检验数量
	private Date sendRiskTestTime;		// 风险检验时间
	private String sendRiskTestOrganization;		// 风险检验机构
	private String sendRiskTestProject;		// 风险检验项目
	private String sendRiskTestNumber;		// 风险检验数量
	private Date reportTime;		// 上报时间／申报时间
	private Date acceptanceTime;		// 受理时间
	private String acceptanceNumber;		// 受理编号
	private Date documentTime;		// 取得批件时间
	private String documentNumber;		// 批件编号
	private String productStatusRemark;		// 产品状态备注（1：通过审批，2：不予批准3：终止申报;）
	private Date nextOpinionTime;		// 下意见时间
	private String opinionContent;		// 意见内容
	private Date replyOpinion;		// 回复意见时间
	private String otherDescription;		// 其他说明
	private String colorCharacter;		// 颜色性状
	private String sampleMarking;		// 样品标记（生产日期或批号）
	private String dateOfExpiry;		// 保质期或限期使用日期（包装标注的保质期或限期使用日期）
	private String technologyDateOfExpiry;		// 保质期（技术要求中显示的保质期）
	private String smell;		// 气味
	private String specifications;		// 规格
	private String productHandlePersonId;		// 产品处理人id
	private String statusRemarks;		// 状态备注(现该字段没有作用)
	
	public DeclareProduct() {
		super();
	}

	public DeclareProduct(String id){
		super(id);
	}

	@Length(min=0, max=64, message="送检总数长度必须介于 0 和 64 之间")
	public String getTotalSubmission() {
		return totalSubmission;
	}

	public void setTotalSubmission(String totalSubmission) {
		this.totalSubmission = totalSubmission;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAdministrativeLicenseInspectionTime() {
		return administrativeLicenseInspectionTime;
	}

	public void setAdministrativeLicenseInspectionTime(Date administrativeLicenseInspectionTime) {
		this.administrativeLicenseInspectionTime = administrativeLicenseInspectionTime;
	}
	
	@Length(min=0, max=64, message="行政许可检验机构长度必须介于 0 和 64 之间")
	public String getAdministrativeLicenseInspectionOrganization() {
		return administrativeLicenseInspectionOrganization;
	}

	public void setAdministrativeLicenseInspectionOrganization(String administrativeLicenseInspectionOrganization) {
		this.administrativeLicenseInspectionOrganization = administrativeLicenseInspectionOrganization;
	}
	
	@Length(min=0, max=64, message="行政许可检验项目长度必须介于 0 和 64 之间")
	public String getAdministrativeLicenseInspectionProject() {
		return administrativeLicenseInspectionProject;
	}

	public void setAdministrativeLicenseInspectionProject(String administrativeLicenseInspectionProject) {
		this.administrativeLicenseInspectionProject = administrativeLicenseInspectionProject;
	}
	
	@Length(min=0, max=64, message="行政许可送检数量长度必须介于 0 和 64 之间")
	public String getAdministrativeLicenseInspectionNumber() {
		return administrativeLicenseInspectionNumber;
	}

	public void setAdministrativeLicenseInspectionNumber(String administrativeLicenseInspectionNumber) {
		this.administrativeLicenseInspectionNumber = administrativeLicenseInspectionNumber;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendBodyTime() {
		return sendBodyTime;
	}

	public void setSendBodyTime(Date sendBodyTime) {
		this.sendBodyTime = sendBodyTime;
	}
	
	@Length(min=0, max=64, message="人体检验送检机构长度必须介于 0 和 64 之间")
	public String getSendBodyOrganization() {
		return sendBodyOrganization;
	}

	public void setSendBodyOrganization(String sendBodyOrganization) {
		this.sendBodyOrganization = sendBodyOrganization;
	}
	
	@Length(min=0, max=64, message="人体检验项目长度必须介于 0 和 64 之间")
	public String getSendBodyProject() {
		return sendBodyProject;
	}

	public void setSendBodyProject(String sendBodyProject) {
		this.sendBodyProject = sendBodyProject;
	}
	
	@Length(min=0, max=64, message="人体检验数量长度必须介于 0 和 64 之间")
	public String getSendBodyNumber() {
		return sendBodyNumber;
	}

	public void setSendBodyNumber(String sendBodyNumber) {
		this.sendBodyNumber = sendBodyNumber;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSendRiskTestTime() {
		return sendRiskTestTime;
	}

	public void setSendRiskTestTime(Date sendRiskTestTime) {
		this.sendRiskTestTime = sendRiskTestTime;
	}
	
	@Length(min=0, max=64, message="风险检验机构长度必须介于 0 和 64 之间")
	public String getSendRiskTestOrganization() {
		return sendRiskTestOrganization;
	}

	public void setSendRiskTestOrganization(String sendRiskTestOrganization) {
		this.sendRiskTestOrganization = sendRiskTestOrganization;
	}
	
	@Length(min=0, max=64, message="风险检验项目长度必须介于 0 和 64 之间")
	public String getSendRiskTestProject() {
		return sendRiskTestProject;
	}

	public void setSendRiskTestProject(String sendRiskTestProject) {
		this.sendRiskTestProject = sendRiskTestProject;
	}
	
	@Length(min=0, max=64, message="风险检验数量长度必须介于 0 和 64 之间")
	public String getSendRiskTestNumber() {
		return sendRiskTestNumber;
	}

	public void setSendRiskTestNumber(String sendRiskTestNumber) {
		this.sendRiskTestNumber = sendRiskTestNumber;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcceptanceTime() {
		return acceptanceTime;
	}

	public void setAcceptanceTime(Date acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	
	@Length(min=0, max=64, message="受理编号长度必须介于 0 和 64 之间")
	public String getAcceptanceNumber() {
		return acceptanceNumber;
	}

	public void setAcceptanceNumber(String acceptanceNumber) {
		this.acceptanceNumber = acceptanceNumber;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDocumentTime() {
		return documentTime;
	}

	public void setDocumentTime(Date documentTime) {
		this.documentTime = documentTime;
	}
	
	@Length(min=0, max=64, message="批件编号长度必须介于 0 和 64 之间")
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@Length(min=0, max=1, message="产品状态备注（1：通过审批，2：不予批准3：终止申报&rdquo;）长度必须介于 0 和 1 之间")
	public String getProductStatusRemark() {
		return productStatusRemark;
	}

	public void setProductStatusRemark(String productStatusRemark) {
		this.productStatusRemark = productStatusRemark;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getNextOpinionTime() {
		return nextOpinionTime;
	}

	public void setNextOpinionTime(Date nextOpinionTime) {
		this.nextOpinionTime = nextOpinionTime;
	}
	
	@Length(min=0, max=255, message="意见内容长度必须介于 0 和 255 之间")
	public String getOpinionContent() {
		return opinionContent;
	}

	public void setOpinionContent(String opinionContent) {
		this.opinionContent = opinionContent;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReplyOpinion() {
		return replyOpinion;
	}

	public void setReplyOpinion(Date replyOpinion) {
		this.replyOpinion = replyOpinion;
	}
	
	@Length(min=0, max=64, message="其他说明长度必须介于 0 和 64 之间")
	public String getOtherDescription() {
		return otherDescription;
	}

	public void setOtherDescription(String otherDescription) {
		this.otherDescription = otherDescription;
	}
	
	@Length(min=0, max=64, message="颜色性状长度必须介于 0 和 64 之间")
	public String getColorCharacter() {
		return colorCharacter;
	}

	public void setColorCharacter(String colorCharacter) {
		this.colorCharacter = colorCharacter;
	}
	
	@Length(min=0, max=64, message="样品标记（生产日期或批号）长度必须介于 0 和 64 之间")
	public String getSampleMarking() {
		return sampleMarking;
	}

	public void setSampleMarking(String sampleMarking) {
		this.sampleMarking = sampleMarking;
	}
	
	@Length(min=0, max=32, message="保质期或限期使用日期（包装标注的保质期或限期使用日期）长度必须介于 0 和 32 之间")
	public String getDateOfExpiry() {
		return dateOfExpiry;
	}

	public void setDateOfExpiry(String dateOfExpiry) {
		this.dateOfExpiry = dateOfExpiry;
	}
	
	@Length(min=0, max=32, message="保质期（技术要求中显示的保质期）长度必须介于 0 和 32 之间")
	public String getTechnologyDateOfExpiry() {
		return technologyDateOfExpiry;
	}

	public void setTechnologyDateOfExpiry(String technologyDateOfExpiry) {
		this.technologyDateOfExpiry = technologyDateOfExpiry;
	}
	
	@Length(min=0, max=32, message="气味长度必须介于 0 和 32 之间")
	public String getSmell() {
		return smell;
	}

	public void setSmell(String smell) {
		this.smell = smell;
	}
	
	@Length(min=0, max=32, message="规格长度必须介于 0 和 32 之间")
	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	
	@Length(min=1, max=64, message="产品处理人id长度必须介于 1 和 64 之间")
	public String getProductHandlePersonId() {
		return productHandlePersonId;
	}

	public void setProductHandlePersonId(String productHandlePersonId) {
		this.productHandlePersonId = productHandlePersonId;
	}
	
	@Length(min=0, max=255, message="状态备注长度必须介于 0 和 255 之间")
	public String getStatusRemarks() {
		return statusRemarks;
	}

	public void setStatusRemarks(String statusRemarks) {
		this.statusRemarks = statusRemarks;
	}
	
}