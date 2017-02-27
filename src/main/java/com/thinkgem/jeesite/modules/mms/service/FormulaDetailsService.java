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
import com.thinkgem.jeesite.modules.mms.entity.FormulaDetails;
import com.thinkgem.jeesite.modules.mms.dao.FormulaDetailsDao;

/**
 * 配方详情信息Service
 * @author jiang
 * @version 2017-02-27
 */
@Service
@Transactional(readOnly = true)
public class FormulaDetailsService extends CrudService<FormulaDetailsDao, FormulaDetails> {

	@Autowired
	private FormulaDetailsDao formulaDetailsDao;

	public FormulaDetails get(String id) {
		return super.get(id);
	}
	
	public List<FormulaDetails> findList(FormulaDetails formulaDetails) {
		return super.findList(formulaDetails);
	}
	
	public Page<FormulaDetails> findPage(Page<FormulaDetails> page, FormulaDetails formulaDetails) {
		return super.findPage(page, formulaDetails);
	}
	
	@Transactional(readOnly = false)
	public void save(FormulaDetails formulaDetails) {
		super.save(formulaDetails);
	}
	
	@Transactional(readOnly = false)
	public void delete(FormulaDetails formulaDetails) {
		super.delete(formulaDetails);
	}

	/**
	 * 根据配方id  获取所有的配方信息
	 * @param formulaId  配方id
	 * @return List<FormulaDetails>
	 */
	public List<FormulaDetails> selectAllByFormulaId(String formulaId){
		return formulaDetailsDao.selectAllByFormulaId(formulaId);
	}
}