/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.ForbiddenComponent;
import com.thinkgem.jeesite.modules.mms.dao.ForbiddenComponentDao;

/**
 * 化妆品安全技术规范的禁用成分Service
 * @author jiang
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class ForbiddenComponentService extends CrudService<ForbiddenComponentDao, ForbiddenComponent> {

	public ForbiddenComponent get(String id) {
		return super.get(id);
	}
	
	public List<ForbiddenComponent> findList(ForbiddenComponent forbiddenComponent) {
		return super.findList(forbiddenComponent);
	}
	
	public Page<ForbiddenComponent> findPage(Page<ForbiddenComponent> page, ForbiddenComponent forbiddenComponent) {
		return super.findPage(page, forbiddenComponent);
	}
	
	@Transactional(readOnly = false)
	public void save(ForbiddenComponent forbiddenComponent) {
		super.save(forbiddenComponent);
	}
	
	@Transactional(readOnly = false)
	public void delete(ForbiddenComponent forbiddenComponent) {
		super.delete(forbiddenComponent);
	}
	
}