/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.FormulaDetails;
import com.thinkgem.jeesite.modules.mms.entity.MaterialUsedDatabase;

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

    /**
     * 根据标准中文名称进行分组，
     * 获取 原料的使用实际成分含量的最大值和最小值，
     * 使用目的和风险物质
     */
    List<MaterialUsedDatabase> selectGroupStandardChineseName();
}