/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.mms.entity.ColorantComponent;
import com.thinkgem.jeesite.modules.mms.dao.ColorantComponentDao;

/**
 * 着色剂Service
 * @author jiang
 * @version 2017-04-26
 */
@Service
@Transactional(readOnly = true)
public class ColorantComponentService extends CrudService<ColorantComponentDao, ColorantComponent> {

	@Autowired
	private ColorantComponentDao colorantComponentDao;

	public ColorantComponent get(String id) {
		return super.get(id);
	}
	
	public List<ColorantComponent> findList(ColorantComponent colorantComponent) {
		return super.findList(colorantComponent);
	}
	
	public Page<ColorantComponent> findPage(Page<ColorantComponent> page, ColorantComponent colorantComponent) {
		return super.findPage(page, colorantComponent);
	}
	
	@Transactional(readOnly = false)
	public void save(ColorantComponent colorantComponent) {
		super.save(colorantComponent);
	}
	
	@Transactional(readOnly = false)
	public void delete(ColorantComponent colorantComponent) {
		super.delete(colorantComponent);
	}

	/**
	 * 根据条件判断，记录是否存在.并返回该条记录
	 * @param number 5  位的数字
	 * @return
	 */
	public ColorantComponent selectByFiveNumber(String number){

		return colorantComponentDao.selectByFiveNumber(number);
	}
}