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
import com.thinkgem.jeesite.modules.mms.entity.NameToRiskMaterial;
import com.thinkgem.jeesite.modules.mms.dao.NameToRiskMaterialDao;

/**
 * 标准中文名对应的风险物质Service
 * @author jiang
 * @version 2017-02-26
 */
@Service
@Transactional(readOnly = true)
public class NameToRiskMaterialService extends CrudService<NameToRiskMaterialDao, NameToRiskMaterial> {

	@Autowired
	private NameToRiskMaterialDao nameToRiskMaterialDao;

	public NameToRiskMaterial get(String id) {
		return super.get(id);
	}
	
	public List<NameToRiskMaterial> findList(NameToRiskMaterial nameToRiskMaterial) {
		return super.findList(nameToRiskMaterial);
	}
	
	public Page<NameToRiskMaterial> findPage(Page<NameToRiskMaterial> page, NameToRiskMaterial nameToRiskMaterial) {
		return super.findPage(page, nameToRiskMaterial);
	}
	
	@Transactional(readOnly = false)
	public void save(NameToRiskMaterial nameToRiskMaterial) {
		super.save(nameToRiskMaterial);
	}
	
	@Transactional(readOnly = false)
	public void delete(NameToRiskMaterial nameToRiskMaterial) {
		super.delete(nameToRiskMaterial);
	}
	/**
	 * 查询所有模糊转换的信息，并按照优先级别进行排序
	 * @return List<NameToRiskMaterial>
	 */
	public  List<NameToRiskMaterial> selectAllLevelOther(){
		return nameToRiskMaterialDao.selectAllLevelOther();
	}
	
}