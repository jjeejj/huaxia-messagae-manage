/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.vo.ProductStatusVo;

import java.util.List;

/**
 * 产品DAO接口
 * @author jiang
 * @version 2017-03-12
 */
@MyBatisDao
public interface ProductDao extends CrudDao<Product> {

    /**
     * 根据市场产品id 查找对应的记录
     * @param marketProductId
     * @return
     */
    Product getByMarketProductId(String marketProductId);

    /**
     * 根据综合产品id 查找对应的记录
     * @param comprehensiveProductId
     * @return
     */
    Product getByComprehensiveProductId(String comprehensiveProductId);

    /**
     * 根据申报产品id 查找对应的记录
     * @param declareProductId
     * @return
     */
    Product getByDeclareProductId(String declareProductId);

    /**
     * 根据 产品负责人进行分组,统计每个状态的数量
     * @return
     */
    List<ProductStatusVo> selectByProductLeader (ProductStatusVo productStatusVo);
}