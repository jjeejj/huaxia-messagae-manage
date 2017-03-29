/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.dao.DeclareProductDao;

/**
 * 申报产品Service
 * @author jiang
 * @version 2017-03-28
 */
@Service
@Transactional(readOnly = true)
public class DeclareProductService extends CrudService<DeclareProductDao, DeclareProduct> {

	public DeclareProduct get(String id) {
		return super.get(id);
	}
	
	public List<DeclareProduct> findList(DeclareProduct declareProduct) {
		return super.findList(declareProduct);
	}
	
	public Page<DeclareProduct> findPage(Page<DeclareProduct> page, DeclareProduct declareProduct) {
		return super.findPage(page, declareProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(DeclareProduct declareProduct) {
		super.save(declareProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(DeclareProduct declareProduct) {
		super.delete(declareProduct);
	}
	
}