/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.RiskMaterialAssessment;
import com.thinkgem.jeesite.modules.mms.dao.RiskMaterialAssessmentDao;

/**
 * 风险物质评估信息Service
 * @author jiang
 * @version 2017-02-26
 */
@Service
@Transactional(readOnly = true)
public class RiskMaterialAssessmentService extends CrudService<RiskMaterialAssessmentDao, RiskMaterialAssessment> {

	public RiskMaterialAssessment get(String id) {
		return super.get(id);
	}
	
	public List<RiskMaterialAssessment> findList(RiskMaterialAssessment riskMaterialAssessment) {
		return super.findList(riskMaterialAssessment);
	}
	
	public Page<RiskMaterialAssessment> findPage(Page<RiskMaterialAssessment> page, RiskMaterialAssessment riskMaterialAssessment) {
		return super.findPage(page, riskMaterialAssessment);
	}
	
	@Transactional(readOnly = false)
	public void save(RiskMaterialAssessment riskMaterialAssessment) {
		super.save(riskMaterialAssessment);
	}
	
	@Transactional(readOnly = false)
	public void delete(RiskMaterialAssessment riskMaterialAssessment) {
		super.delete(riskMaterialAssessment);
	}
	
}