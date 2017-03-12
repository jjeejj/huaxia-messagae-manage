/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.ComprehensiveProduct;
import com.thinkgem.jeesite.modules.mms.dao.ComprehensiveProductDao;

/**
 * 综合产品Service
 * @author jiang
 * @version 2017-03-12
 */
@Service
@Transactional(readOnly = true)
public class ComprehensiveProductService extends CrudService<ComprehensiveProductDao, ComprehensiveProduct> {

	public ComprehensiveProduct get(String id) {
		return super.get(id);
	}
	
	public List<ComprehensiveProduct> findList(ComprehensiveProduct comprehensiveProduct) {
		return super.findList(comprehensiveProduct);
	}
	
	public Page<ComprehensiveProduct> findPage(Page<ComprehensiveProduct> page, ComprehensiveProduct comprehensiveProduct) {
		return super.findPage(page, comprehensiveProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(ComprehensiveProduct comprehensiveProduct) {
		super.save(comprehensiveProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(ComprehensiveProduct comprehensiveProduct) {
		super.delete(comprehensiveProduct);
	}
	
}