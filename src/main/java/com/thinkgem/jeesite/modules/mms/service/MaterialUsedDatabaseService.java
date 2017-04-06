/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.MaterialUsedDatabase;
import com.thinkgem.jeesite.modules.mms.dao.MaterialUsedDatabaseDao;

/**
 * 原料使用数据库Service
 * @author jiang
 * @version 2017-04-06
 */
@Service
@Transactional(readOnly = true)
public class MaterialUsedDatabaseService extends CrudService<MaterialUsedDatabaseDao, MaterialUsedDatabase> {

	public MaterialUsedDatabase get(String id) {
		return super.get(id);
	}
	
	public List<MaterialUsedDatabase> findList(MaterialUsedDatabase materialUsedDatabase) {
		return super.findList(materialUsedDatabase);
	}
	
	public Page<MaterialUsedDatabase> findPage(Page<MaterialUsedDatabase> page, MaterialUsedDatabase materialUsedDatabase) {
		return super.findPage(page, materialUsedDatabase);
	}
	
	@Transactional(readOnly = false)
	public void save(MaterialUsedDatabase materialUsedDatabase) {
		super.save(materialUsedDatabase);
	}
	
	@Transactional(readOnly = false)
	public void delete(MaterialUsedDatabase materialUsedDatabase) {
		super.delete(materialUsedDatabase);
	}
	
}