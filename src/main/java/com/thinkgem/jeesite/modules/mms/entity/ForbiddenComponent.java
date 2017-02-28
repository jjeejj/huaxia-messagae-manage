/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.Length(min=0, max=100, message="原植(动)物拉丁文学名或植;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 化妆品安全技术规范的禁用成分Entity
 * @author jiang
 * @version 2017-02-28
 */
public class ForbiddenComponent extends DataEntity<ForbiddenComponent> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String standardChineseName;		// 标准中文名称
	private String inicName;		// 原植(动)物拉丁文学名或植(动)物英文名
	
	public ForbiddenComponent() {
		super();
	}

	public ForbiddenComponent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=100, message="标准中文名称长度必须介于 1 和 100 之间")
	public String getStandardChineseName() {
		return standardChineseName;
	}

	public void setStandardChineseName(String standardChineseName) {
		this.standardChineseName = standardChineseName;
	}
	
	@Length(min=0, max=100, message="原植(动)物拉丁文学名或植(动)物英文名长度必须介于 0 和 100 之间")
	public String getInicName() {
		return inicName;
	}

	public void setInicName(String inicName) {
		this.inicName = inicName;
	}
	
}