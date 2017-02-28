/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.RawMaterialList;
import com.thinkgem.jeesite.modules.mms.dao.RawMaterialListDao;

/**
 * 已使用原料目录Service
 * @author jiang
 * @version 2017-02-28
 */
@Service
@Transactional(readOnly = true)
public class RawMaterialListService extends CrudService<RawMaterialListDao, RawMaterialList> {

	public RawMaterialList get(String id) {
		return super.get(id);
	}
	
	public List<RawMaterialList> findList(RawMaterialList rawMaterialList) {
		return super.findList(rawMaterialList);
	}
	
	public Page<RawMaterialList> findPage(Page<RawMaterialList> page, RawMaterialList rawMaterialList) {
		return super.findPage(page, rawMaterialList);
	}
	
	@Transactional(readOnly = false)
	public void save(RawMaterialList rawMaterialList) {
		super.save(rawMaterialList);
	}
	
	@Transactional(readOnly = false)
	public void delete(RawMaterialList rawMaterialList) {
		super.delete(rawMaterialList);
	}
	
}