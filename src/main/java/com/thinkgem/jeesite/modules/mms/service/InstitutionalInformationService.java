/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.InstitutionalInformation;
import com.thinkgem.jeesite.modules.mms.dao.InstitutionalInformationDao;

/**
 * 机构信息Service
 * @author jiang
 * @version 2017-04-20
 */
@Service
@Transactional(readOnly = true)
public class InstitutionalInformationService extends CrudService<InstitutionalInformationDao, InstitutionalInformation> {

	public InstitutionalInformation get(String id) {
		return super.get(id);
	}
	
	public List<InstitutionalInformation> findList(InstitutionalInformation institutionalInformation) {
		return super.findList(institutionalInformation);
	}
	
	public Page<InstitutionalInformation> findPage(Page<InstitutionalInformation> page, InstitutionalInformation institutionalInformation) {
		return super.findPage(page, institutionalInformation);
	}
	
	@Transactional(readOnly = false)
	public void save(InstitutionalInformation institutionalInformation) {
		super.save(institutionalInformation);
	}
	
	@Transactional(readOnly = false)
	public void delete(InstitutionalInformation institutionalInformation) {
		super.delete(institutionalInformation);
	}
	
}