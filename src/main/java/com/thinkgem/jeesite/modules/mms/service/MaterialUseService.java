/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.MaterialUse;
import com.thinkgem.jeesite.modules.mms.dao.MaterialUseDao;

/**
 * 原料使用数据库Service
 * @author jiang
 * @version 2017-03-27
 */
@Service
@Transactional(readOnly = true)
public class MaterialUseService extends CrudService<MaterialUseDao, MaterialUse> {

	public MaterialUse get(String id) {
		return super.get(id);
	}
	
	public List<MaterialUse> findList(MaterialUse materialUse) {
		return super.findList(materialUse);
	}
	
	public Page<MaterialUse> findPage(Page<MaterialUse> page, MaterialUse materialUse) {
		return super.findPage(page, materialUse);
	}
	
	@Transactional(readOnly = false)
	public void save(MaterialUse materialUse) {
		super.save(materialUse);
	}
	
	@Transactional(readOnly = false)
	public void delete(MaterialUse materialUse) {
		super.delete(materialUse);
	}
	
}