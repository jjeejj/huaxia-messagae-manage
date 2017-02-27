/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.Formula;
import com.thinkgem.jeesite.modules.mms.dao.FormulaDao;

/**
 * 配方信息Service
 * @author jiang
 * @version 2017-02-27
 */
@Service
@Transactional(readOnly = true)
public class FormulaService extends CrudService<FormulaDao, Formula> {

	public Formula get(String id) {
		return super.get(id);
	}
	
	public List<Formula> findList(Formula formula) {
		return super.findList(formula);
	}
	
	public Page<Formula> findPage(Page<Formula> page, Formula formula) {
		return super.findPage(page, formula);
	}
	
	@Transactional(readOnly = false)
	public void save(Formula formula) {
		super.save(formula);
	}
	
	@Transactional(readOnly = false)
	public void delete(Formula formula) {
		super.delete(formula);
	}
	
}