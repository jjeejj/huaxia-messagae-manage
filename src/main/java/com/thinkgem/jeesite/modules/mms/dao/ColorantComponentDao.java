/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.ColorantComponent;

/**
 * 着色剂DAO接口
 * @author jiang
 * @version 2017-04-26
 */
@MyBatisDao
public interface ColorantComponentDao extends CrudDao<ColorantComponent> {

    /**
     * 根据条件判断，记录是否存在.并返回该条记录
     * @param number 5  位的数字
     * @return
     */
    ColorantComponent selectByFiveNumber(String number);
	
}