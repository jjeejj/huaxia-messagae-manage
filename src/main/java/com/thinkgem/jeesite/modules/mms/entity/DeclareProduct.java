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
	private Date inspectionReportTime;		// 取送检报告时间
	private String sendBodyTime;		// 送人体时间
	private Date bodyReportTime;		// 取送人体报告时间
	private Date reportTime;		// 上报时间
	private Date acceptanceTime;		// 受理时间
	private Date documentTime;		// 批件时间
	private Date nextOpinionTime;		// 下意见时间
	private Date replyOpinion;		// 回复意见
	private String productHandlePersonId;		// 产品处理人id

	private MarketProduct marketProduct;		// 市场产品
	
	public DeclareProduct() {
		super();
	}

	public DeclareProduct(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInspectionReportTime() {
		return inspectionReportTime;
	}

	public void setInspectionReportTime(Date inspectionReportTime) {
		this.inspectionReportTime = inspectionReportTime;
	}
	
	@Length(min=0, max=10, message="送人体时间长度必须介于 0 和 10 之间")
	public String getSendBodyTime() {
		return sendBodyTime;
	}

	public void setSendBodyTime(String sendBodyTime) {
		this.sendBodyTime = sendBodyTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBodyReportTime() {
		return bodyReportTime;
	}

	public void setBodyReportTime(Date bodyReportTime) {
		this.bodyReportTime = bodyReportTime;
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
}