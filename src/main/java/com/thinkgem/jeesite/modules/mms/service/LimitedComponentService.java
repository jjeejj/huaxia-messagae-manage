/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.LimitedComponent;
import com.thinkgem.jeesite.modules.mms.dao.LimitedComponentDao;

/**
 * 化妆品安全技术规范的限用成分Service
 * @author jiang
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class LimitedComponentService extends CrudService<LimitedComponentDao, LimitedComponent> {

	public LimitedComponent get(String id) {
		return super.get(id);
	}
	
	public List<LimitedComponent> findList(LimitedComponent limitedComponent) {
		return super.findList(limitedComponent);
	}
	
	public Page<LimitedComponent> findPage(Page<LimitedComponent> page, LimitedComponent limitedComponent) {
		return super.findPage(page, limitedComponent);
	}
	
	@Transactional(readOnly = false)
	public void save(LimitedComponent limitedComponent) {
		super.save(limitedComponent);
	}
	
	@Transactional(readOnly = false)
	public void delete(LimitedComponent limitedComponent) {
		super.delete(limitedComponent);
	}
	
}