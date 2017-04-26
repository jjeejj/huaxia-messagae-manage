/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.Length(min=0, max=10, message="适用及;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 化妆品安全技术规范的限用成分Entity
 * @author jiang
 * @version 2017-02-28
 */
public class LimitedComponent extends DataEntity<LimitedComponent> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String standardChineseName;		// 标准中文名称
	private String queryChineseName;		// 查询用的中文名称（可能有多个以，分割）
	private String inicName;		// INIC名
	private String englishName;		// 英文名称
	private String useRange;		// 适用及(或)使用范围（使用范围和限制条件）
	private String maxAllowConcentretion;		// 最大允许浓度（%）
	private String otherRestrictionsRequirements;		// 其他限制和要求
	private String labelMarkedConditionsPrecautions;		// 标签上必须标印的使用条件和注意事项
	private String type;		// 类型(1：限用成分，2：防腐剂，3：防晒剂)

	private String limitedRemarks;		// 限用成分备注信息（备注项显示内容）

	public LimitedComponent() {
		super();
	}

	public LimitedComponent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	@ExcelField(title = "序号",sort = 10)
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
//	@Length(min=1, max=100, message="标准中文名称长度必须介于 1 和 100 之间")
	@ExcelField(title = "标准中文名称",sort = 20)
	public String getStandardChineseName() {
		return standardChineseName;
	}

	public void setStandardChineseName(String standardChineseName) {
		this.standardChineseName = standardChineseName;
	}
	
//	@Length(min=0, max=100, message="INIC名长度必须介于 0 和 100 之间")
	@ExcelField(title = "INIC名",sort = 40)
	public String getInicName() {
		return inicName;
	}

	public void setInicName(String inicName) {
		this.inicName = inicName;
	}
	
//	@Length(min=0, max=100, message="英文名称长度必须介于 0 和 100 之间")
	@ExcelField(title = "英文名称",sort = 30)
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	@Length(min=0, max=100, message="适用及(或)使用范围长度必须介于 0 和 100 之间")
//	@ExcelField(title = "适用及(或)使用范围",sort = 50)
	public String getUseRange() {
		return useRange;
	}

	public void setUseRange(String useRange) {
		this.useRange = useRange;
	}
	
	@Length(min=0, max=10, message="最大允许浓度长度必须介于 0 和 10 之间")
	@ExcelField(title = "最大允许浓度(%)",sort = 60)
	public String getMaxAllowConcentretion() {
		return maxAllowConcentretion;
	}

	public void setMaxAllowConcentretion(String maxAllowConcentretion) {
		this.maxAllowConcentretion = maxAllowConcentretion;
	}

//	@ExcelField(title = "其他限制和要求",sort = 70)
	public String getOtherRestrictionsRequirements() {
		return otherRestrictionsRequirements;
	}

	public void setOtherRestrictionsRequirements(String otherRestrictionsRequirements) {
		this.otherRestrictionsRequirements = otherRestrictionsRequirements;
	}

//	@ExcelField(title = "标签上必须标印的使用条件和注意事项",sort = 80)
	public String getLabelMarkedConditionsPrecautions() {
		return labelMarkedConditionsPrecautions;
	}

	public void setLabelMarkedConditionsPrecautions(String labelMarkedConditionsPrecautions) {
		this.labelMarkedConditionsPrecautions = labelMarkedConditionsPrecautions;
	}

	@ExcelField(title = "类型(1：限用成分，2：防腐剂，3：防晒剂)",sort = 90)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@ExcelField(title = "查询用的中文名称（可能有多个以，分割）",sort = 25)
	public String getQueryChineseName() {
		return queryChineseName;
	}

	public void setQueryChineseName(String queryChineseName) {
		this.queryChineseName = queryChineseName;
	}

	@ExcelField(title = "备注项显示内容",sort = 85)
	public String getLimitedRemarks() {
		return limitedRemarks;
	}

	public void setLimitedRemarks(String limitedRemarks) {
		this.limitedRemarks = limitedRemarks;
	}
}