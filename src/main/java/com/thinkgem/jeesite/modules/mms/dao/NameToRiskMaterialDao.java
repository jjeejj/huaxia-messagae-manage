/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.NameToRiskMaterial;

import java.util.List;

/**
 * 标准中文名对应的风险物质DAO接口
 * @author jiang
 * @version 2017-02-26
 */
@MyBatisDao
public interface NameToRiskMaterialDao extends CrudDao<NameToRiskMaterial> {

    /**
     * 查询所有模糊转换的信息，并按照优先级别进行排序
     * @return List<NameToRiskMaterial>
     */
    List<NameToRiskMaterial> selectAllLevelOther();
}