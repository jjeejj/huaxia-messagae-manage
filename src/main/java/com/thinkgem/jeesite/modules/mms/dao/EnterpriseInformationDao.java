/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.EnterpriseInformation;

import java.util.List;

/**
 * 企业信息DAO接口
 * @author jiang
 * @version 2017-04-22
 */
@MyBatisDao
public interface EnterpriseInformationDao extends CrudDao<EnterpriseInformation> {

    //根据企业名称进行查询
	List<EnterpriseInformation> findListByName(String enterpriseName);
}