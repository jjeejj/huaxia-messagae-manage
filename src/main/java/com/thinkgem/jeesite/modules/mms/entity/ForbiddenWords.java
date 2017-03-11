/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 禁用语词汇Entity
 * @author jiang
 * @version 2017-03-11
 */
public class ForbiddenWords extends DataEntity<ForbiddenWords> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String forbiddenName;		// 禁用名称
	private String forbiddenExplain;		// 禁用说明
	
	public ForbiddenWords() {
		super();
	}

	public ForbiddenWords(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=64, message="禁用名称长度必须介于 1 和 64 之间")
	public String getForbiddenName() {
		return forbiddenName;
	}

	public void setForbiddenName(String forbiddenName) {
		this.forbiddenName = forbiddenName;
	}
	
	public String getForbiddenExplain() {
		return forbiddenExplain;
	}

	public void setForbiddenExplain(String forbiddenExplain) {
		this.forbiddenExplain = forbiddenExplain;
	}
	
}