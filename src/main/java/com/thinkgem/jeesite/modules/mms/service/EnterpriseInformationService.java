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
import com.thinkgem.jeesite.modules.mms.entity.EnterpriseInformation;
import com.thinkgem.jeesite.modules.mms.dao.EnterpriseInformationDao;

/**
 * 企业信息Service
 * @author jiang
 * @version 2017-04-22
 */
@Service
@Transactional(readOnly = true)
public class EnterpriseInformationService extends CrudService<EnterpriseInformationDao, EnterpriseInformation> {

	@Autowired
	private EnterpriseInformationDao enterpriseInformationDao;

	public EnterpriseInformation get(String id) {
		return super.get(id);
	}
	
	public List<EnterpriseInformation> findList(EnterpriseInformation enterpriseInformation) {
		return super.findList(enterpriseInformation);
	}
	
	public Page<EnterpriseInformation> findPage(Page<EnterpriseInformation> page, EnterpriseInformation enterpriseInformation) {
		return super.findPage(page, enterpriseInformation);
	}
	
	@Transactional(readOnly = false)
	public void save(EnterpriseInformation enterpriseInformation) {
		super.save(enterpriseInformation);
	}
	
	@Transactional(readOnly = false)
	public void delete(EnterpriseInformation enterpriseInformation) {
		super.delete(enterpriseInformation);
	}

	/**
	 * 根据企业名称进行查询
	 * @param enterpriseNam 企业名称
	 * @return
	 */
	public List<EnterpriseInformation> findListByName(String enterpriseNam){
		return enterpriseInformationDao.findListByName(enterpriseNam);
	}
	
}