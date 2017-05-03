/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.AssessSuggestion;

/**
 * 审评意见DAO接口
 * @author jiang
 * @version 2017-03-07
 */
@MyBatisDao
public interface AssessSuggestionDao extends CrudDao<AssessSuggestion> {

    /**
     * 获取最大的序号
     * @return String
     */
    String getBigSequence();
	
}