/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品Entity
 * @author jiang
 * @version 2017-03-12
 */
public class Product extends DataEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String marketProductId;		// 市场产品id
	private String comprehensiveProductId;		// 综合产品id
	private String declareProductId;		// 申报产品id
	private String productStatus;		// 产品状态
	private String productProcess;		// 产品进度
	private String productLeader;		// 产品负责人

	private MarketProduct marketProduct; //市场产品
	private ComprehensiveProduct comprehensiveProduct; //综合产品
	private DeclareProduct declareProduct; //申报产品


	private String startDate;//开始日期
	private String endDate; //结束日期

	private String dateLimitContent;//办理时限说明

	public Product() {
		super();
	}

	public Product(String id){
		super(id);
	}

	@Length(min=1, max=64, message="市场产品id长度必须介于 1 和 64 之间")
	public String getMarketProductId() {
		return marketProductId;
	}

	public void setMarketProductId(String marketProductId) {
		this.marketProductId = marketProductId;
	}
	
	@Length(min=0, max=64, message="综合产品id长度必须介于 0 和 64 之间")
	public String getComprehensiveProductId() {
		return comprehensiveProductId;
	}

	public void setComprehensiveProductId(String comprehensiveProductId) {
		this.comprehensiveProductId = comprehensiveProductId;
	}
	
	@Length(min=0, max=64, message="申报产品id长度必须介于 0 和 64 之间")
	public String getDeclareProductId() {
		return declareProductId;
	}

	public void setDeclareProductId(String declareProductId) {
		this.declareProductId = declareProductId;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public MarketProduct getMarketProduct() {
		return marketProduct;
	}

	public void setMarketProduct(MarketProduct marketProduct) {
		this.marketProduct = marketProduct;
	}

	public ComprehensiveProduct getComprehensiveProduct() {
		return comprehensiveProduct;
	}

	public void setComprehensiveProduct(ComprehensiveProduct comprehensiveProduct) {
		this.comprehensiveProduct = comprehensiveProduct;
	}

	public DeclareProduct getDeclareProduct() {
		return declareProduct;
	}

	public void setDeclareProduct(DeclareProduct declareProduct) {
		this.declareProduct = declareProduct;
	}

	public String getProductProcess() {
		return productProcess;
	}

	public void setProductProcess(String productProcess) {
		this.productProcess = productProcess;
	}

	public String getProductLeader() {
		return productLeader;
	}

	public void setProductLeader(String productLeader) {
		this.productLeader = productLeader;
	}


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDateLimitContent() {
		return dateLimitContent;
	}

	public void setDateLimitContent(String dateLimitContent) {
		this.dateLimitContent = dateLimitContent;
	}
}