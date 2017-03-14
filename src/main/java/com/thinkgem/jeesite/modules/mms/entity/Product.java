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

	private MarketProduct marketProduct; //市场产品
	private ComprehensiveProduct comprehensiveProduct; //综合产品
	private DeclareProduct declareProduct; //申报产品

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
}