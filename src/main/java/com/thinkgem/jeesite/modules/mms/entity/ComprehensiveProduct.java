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
	private String totalNumberOfSamples;		// 送检总数
	private Date submissionTime;		// 送检时间
	
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
	
	@Length(min=0, max=10, message="送检总数长度必须介于 0 和 10 之间")
	public String getTotalNumberOfSamples() {
		return totalNumberOfSamples;
	}

	public void setTotalNumberOfSamples(String totalNumberOfSamples) {
		this.totalNumberOfSamples = totalNumberOfSamples;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}
	
}