/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.ProductFlowNumber;

/**
 * 产品流水编号DAO接口
 * @author jiang
 * @version 2017-03-28
 */
@MyBatisDao
public interface ProductFlowNumberDao extends CrudDao<ProductFlowNumber> {

    /**
     * 根据年选出该年最大的流水账号数字
     * @param year 查询年份
     * @return  最大的流水账号数字
     */
    String selectBigNumberByYear(String year);
	
}