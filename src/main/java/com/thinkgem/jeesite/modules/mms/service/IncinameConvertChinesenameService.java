/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.IncinameConvertChinesename;
import com.thinkgem.jeesite.modules.mms.dao.IncinameConvertChinesenameDao;

/**
 * inci名与标准中文名相互转换Service
 * @author jiang
 * @version 2017-04-05
 */
@Service
@Transactional(readOnly = true)
public class IncinameConvertChinesenameService extends CrudService<IncinameConvertChinesenameDao, IncinameConvertChinesename> {

	public IncinameConvertChinesename get(String id) {
		return super.get(id);
	}
	
	public List<IncinameConvertChinesename> findList(IncinameConvertChinesename incinameConvertChinesename) {
		return super.findList(incinameConvertChinesename);
	}
	
	public Page<IncinameConvertChinesename> findPage(Page<IncinameConvertChinesename> page, IncinameConvertChinesename incinameConvertChinesename) {
		return super.findPage(page, incinameConvertChinesename);
	}
	
	@Transactional(readOnly = false)
	public void save(IncinameConvertChinesename incinameConvertChinesename) {
		super.save(incinameConvertChinesename);
	}
	
	@Transactional(readOnly = false)
	public void delete(IncinameConvertChinesename incinameConvertChinesename) {
		super.delete(incinameConvertChinesename);
	}
	
}