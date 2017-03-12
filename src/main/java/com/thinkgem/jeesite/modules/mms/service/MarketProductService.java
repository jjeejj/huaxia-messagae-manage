/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.MarketProduct;
import com.thinkgem.jeesite.modules.mms.dao.MarketProductDao;

/**
 * 市场产品Service
 * @author jiang
 * @version 2017-03-12
 */
@Service
@Transactional(readOnly = true)
public class MarketProductService extends CrudService<MarketProductDao, MarketProduct> {

	public MarketProduct get(String id) {
		return super.get(id);
	}
	
	public List<MarketProduct> findList(MarketProduct marketProduct) {
		return super.findList(marketProduct);
	}
	
	public Page<MarketProduct> findPage(Page<MarketProduct> page, MarketProduct marketProduct) {
		return super.findPage(page, marketProduct);
	}
	
	@Transactional(readOnly = false)
	public void save(MarketProduct marketProduct) {
		super.save(marketProduct);
	}
	
	@Transactional(readOnly = false)
	public void delete(MarketProduct marketProduct) {
		super.delete(marketProduct);
	}
	
}