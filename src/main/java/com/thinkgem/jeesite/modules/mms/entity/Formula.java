/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 配方信息Entity
 * @author jiang
 * @version 2017-02-27
 */
public class Formula extends DataEntity<Formula> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String productNumber;		// 归属产品编号
	private String formulaName;		// 配方名称
	private String rawMaterialContentTotal;		// 总原料含量（%）
	private String actualComponentContentTotal;		// 总实际成份含量（%）
	
	public Formula() {
		super();
	}

	public Formula(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=255, message="配方名称长度必须介于 1 和 255 之间")
	public String getFormulaName() {
		return formulaName;
	}

	public void setFormulaName(String formulaName) {
		this.formulaName = formulaName;
	}
	
	@Length(min=0, max=10, message="总原料含量（%）长度必须介于 0 和 10 之间")
	public String getRawMaterialContentTotal() {
		return rawMaterialContentTotal;
	}

	public void setRawMaterialContentTotal(String rawMaterialContentTotal) {
		this.rawMaterialContentTotal = rawMaterialContentTotal;
	}
	
	@Length(min=0, max=10, message="总实际成份含量（%）长度必须介于 0 和 10 之间")
	public String getActualComponentContentTotal() {
		return actualComponentContentTotal;
	}

	public void setActualComponentContentTotal(String actualComponentContentTotal) {
		this.actualComponentContentTotal = actualComponentContentTotal;
	}

	public String getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
}