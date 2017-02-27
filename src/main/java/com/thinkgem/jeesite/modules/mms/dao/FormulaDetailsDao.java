/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.FormulaDetails;

import java.util.List;

/**
 * 配方详情信息DAO接口
 * @author jiang
 * @version 2017-02-27
 */
@MyBatisDao
public interface FormulaDetailsDao extends CrudDao<FormulaDetails> {

    /**
     * 根据配方id  获取所有的配方信息
     * @param formulaId  配方id
     * @return List<FormulaDetails>
     */
    List<FormulaDetails> selectAllByFormulaId(String formulaId);
}