/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 风险物质评估信息Entity
 * @author jiang
 * @version 2017-02-26
 */
public class RiskMaterialAssessment extends DataEntity<RiskMaterialAssessment> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String riskMaterialName;		// 风险物质名称
	private String source;		// 主要来源
	private String safetyThreshold;		// 安全阈值
	private String evaluationBasis;		// 评估依据
	
	public RiskMaterialAssessment() {
		super();
	}

	public RiskMaterialAssessment(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=255, message="风险物质名称长度必须介于 1 和 255 之间")
	public String getRiskMaterialName() {
		return riskMaterialName;
	}

	public void setRiskMaterialName(String riskMaterialName) {
		this.riskMaterialName = riskMaterialName;
	}
	
	@Length(min=0, max=255, message="主要来源长度必须介于 0 和 255 之间")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@Length(min=0, max=50, message="安全阈值长度必须介于 0 和 50 之间")
	public String getSafetyThreshold() {
		return safetyThreshold;
	}

	public void setSafetyThreshold(String safetyThreshold) {
		this.safetyThreshold = safetyThreshold;
	}
	
	@Length(min=0, max=255, message="评估依据长度必须介于 0 和 255 之间")
	public String getEvaluationBasis() {
		return evaluationBasis;
	}

	public void setEvaluationBasis(String evaluationBasis) {
		this.evaluationBasis = evaluationBasis;
	}
	
}