/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.ProductFlowNumber;
import com.thinkgem.jeesite.modules.mms.dao.ProductFlowNumberDao;

/**
 * 产品流水编号Service
 * @author jiang
 * @version 2017-03-28
 */
@Service
@Transactional(readOnly = true)
public class ProductFlowNumberService extends CrudService<ProductFlowNumberDao, ProductFlowNumber> {

	public ProductFlowNumber get(String id) {
		return super.get(id);
	}
	
	public List<ProductFlowNumber> findList(ProductFlowNumber productFlowNumber) {
		return super.findList(productFlowNumber);
	}
	
	public Page<ProductFlowNumber> findPage(Page<ProductFlowNumber> page, ProductFlowNumber productFlowNumber) {
		return super.findPage(page, productFlowNumber);
	}
	
	@Transactional(readOnly = false)
	public void save(ProductFlowNumber productFlowNumber) {
		super.save(productFlowNumber);
	}
	
	@Transactional(readOnly = false)
	public void delete(ProductFlowNumber productFlowNumber) {
		super.delete(productFlowNumber);
	}
	
}