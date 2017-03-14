/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.dao.ProductDao;

/**
 * 产品Service
 * @author jiang
 * @version 2017-03-12
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {

	@Autowired
	private ProductDao productDao;

	public Product get(String id) {
		return super.get(id);
	}
	
	public List<Product> findList(Product product) {
		return super.findList(product);
	}
	
	public Page<Product> findPage(Page<Product> page, Product product) {
		return super.findPage(page, product);
	}
	
	@Transactional(readOnly = false)
	public void save(Product product) {
		super.save(product);
	}
	
	@Transactional(readOnly = false)
	public void delete(Product product) {
		super.delete(product);
	}

	/**
	 * 根据市场产品id 查找对应的记录
	 * @param marketProductId
	 * @return
	 */
	public Product getByMarketProductId(String marketProductId) {
		return productDao.getByMarketProductId(marketProductId);
	}


	/**
	 * 根据综合产品id 查找对应的记录
	 * @param comprehensiveProductId
	 * @return
	 */
	public Product getByComprehensiveProductId(String comprehensiveProductId) {
		return productDao.getByComprehensiveProductId(comprehensiveProductId);
	}

	/**
	 * 根据申报产品id 查找对应的记录
	 * @param declareProductId
	 * @return
	 */
	public Product getByDeclareProductId(String declareProductId) {
		return productDao.getByDeclareProductId(declareProductId);
	}
	
}