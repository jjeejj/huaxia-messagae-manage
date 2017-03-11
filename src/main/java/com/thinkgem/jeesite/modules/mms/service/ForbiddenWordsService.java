/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.ForbiddenWords;
import com.thinkgem.jeesite.modules.mms.dao.ForbiddenWordsDao;

/**
 * 禁用语词汇Service
 * @author jiang
 * @version 2017-03-11
 */
@Service
@Transactional(readOnly = true)
public class ForbiddenWordsService extends CrudService<ForbiddenWordsDao, ForbiddenWords> {

	public ForbiddenWords get(String id) {
		return super.get(id);
	}
	
	public List<ForbiddenWords> findList(ForbiddenWords forbiddenWords) {
		return super.findList(forbiddenWords);
	}
	
	public Page<ForbiddenWords> findPage(Page<ForbiddenWords> page, ForbiddenWords forbiddenWords) {
		return super.findPage(page, forbiddenWords);
	}
	
	@Transactional(readOnly = false)
	public void save(ForbiddenWords forbiddenWords) {
		super.save(forbiddenWords);
	}
	
	@Transactional(readOnly = false)
	public void delete(ForbiddenWords forbiddenWords) {
		super.delete(forbiddenWords);
	}
	
}