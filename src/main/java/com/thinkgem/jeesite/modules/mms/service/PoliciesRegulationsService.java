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
import com.thinkgem.jeesite.modules.mms.entity.PoliciesRegulations;
import com.thinkgem.jeesite.modules.mms.dao.PoliciesRegulationsDao;

/**
 * 政策法规数据库Service
 * @author jiang
 * @version 2017-03-29
 */
@Service
@Transactional(readOnly = true)
public class PoliciesRegulationsService extends CrudService<PoliciesRegulationsDao, PoliciesRegulations> {

	@Autowired
	private PoliciesRegulationsDao policiesRegulationsDao;

	public PoliciesRegulations get(String id) {
		return super.get(id);
	}
	
	public List<PoliciesRegulations> findList(PoliciesRegulations policiesRegulations) {
		return super.findList(policiesRegulations);
	}
	
	public Page<PoliciesRegulations> findPage(Page<PoliciesRegulations> page, PoliciesRegulations policiesRegulations) {
		return super.findPage(page, policiesRegulations);
	}
	
	@Transactional(readOnly = false)
	public void save(PoliciesRegulations policiesRegulations) {
		super.save(policiesRegulations);
	}
	
	@Transactional(readOnly = false)
	public void delete(PoliciesRegulations policiesRegulations) {
		super.delete(policiesRegulations);
	}

	/**
	 * 获取最大的序号
	 * @return String
	 */

	public String getBigSequence(){
		return policiesRegulationsDao.getBigSequence();
	}
}