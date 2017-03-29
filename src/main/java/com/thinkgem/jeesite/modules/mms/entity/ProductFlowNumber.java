/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品流水编号Entity
 * @author jiang
 * @version 2017-03-28
 */
public class ProductFlowNumber extends DataEntity<ProductFlowNumber> {
	
	private static final long serialVersionUID = 1L;
	private String productId;		// 对应的产品ID
	private String flowNumber;		// 流水编号
	private String flowYear;		// 流水年
	
	public ProductFlowNumber() {
		super();
	}

	public ProductFlowNumber(String id){
		super(id);
	}

	@Length(min=1, max=64, message="对应的产品ID长度必须介于 1 和 64 之间")
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Length(min=1, max=32, message="流水编号长度必须介于 1 和 32 之间")
	public String getFlowNumber() {
		return flowNumber;
	}

	public void setFlowNumber(String flowNumber) {
		this.flowNumber = flowNumber;
	}
	
	@Length(min=1, max=32, message="流水年长度必须介于 1 和 32 之间")
	public String getFlowYear() {
		return flowYear;
	}

	public void setFlowYear(String flowYear) {
		this.flowYear = flowYear;
	}
	
}