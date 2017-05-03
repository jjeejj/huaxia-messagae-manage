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
import com.thinkgem.jeesite.modules.mms.entity.AssessSuggestion;
import com.thinkgem.jeesite.modules.mms.dao.AssessSuggestionDao;

/**
 * 审评意见Service
 * @author jiang
 * @version 2017-03-07
 */
@Service
@Transactional(readOnly = true)
public class AssessSuggestionService extends CrudService<AssessSuggestionDao, AssessSuggestion> {

	@Autowired
	private AssessSuggestionDao assessSuggestionDao;

	public AssessSuggestion get(String id) {
		return super.get(id);
	}
	
	public List<AssessSuggestion> findList(AssessSuggestion assessSuggestion) {
		return super.findList(assessSuggestion);
	}
	
	public Page<AssessSuggestion> findPage(Page<AssessSuggestion> page, AssessSuggestion assessSuggestion) {
		return super.findPage(page, assessSuggestion);
	}
	
	@Transactional(readOnly = false)
	public void save(AssessSuggestion assessSuggestion) {
		super.save(assessSuggestion);
	}
	
	@Transactional(readOnly = false)
	public void delete(AssessSuggestion assessSuggestion) {
		super.delete(assessSuggestion);
	}

	/**
	 * 获取最大的序号
	 * @return String
	 */

	public String getBigSequence(){
		return assessSuggestionDao.getBigSequence();
	}
	
}