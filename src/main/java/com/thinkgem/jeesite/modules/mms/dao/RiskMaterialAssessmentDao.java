/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.RiskMaterialAssessment;

/**
 * 风险物质评估信息DAO接口
 * @author jiang
 * @version 2017-02-26
 */
@MyBatisDao
public interface RiskMaterialAssessmentDao extends CrudDao<RiskMaterialAssessment> {


    /**
     * 获取最大的序号
     * @return String
     */
    String getBigSequence();
}