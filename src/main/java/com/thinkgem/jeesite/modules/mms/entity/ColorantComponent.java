/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 着色剂Entity
 * @author jiang
 * @version 2017-04-26
 */
public class ColorantComponent extends DataEntity<ColorantComponent> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String colorIndex;		// 着色剂索引号
	private String colorGenericName;		// 着色剂索引通用名
	private String limitedRemarks;		// 限用成分备注信息（备注项显示内容）
	
	public ColorantComponent() {
		super();
	}

	public ColorantComponent(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=64, message="着色剂索引号长度必须介于 1 和 64 之间")
	public String getColorIndex() {
		return colorIndex;
	}

	public void setColorIndex(String colorIndex) {
		this.colorIndex = colorIndex;
	}
	
	@Length(min=1, max=64, message="着色剂索引通用名长度必须介于 1 和 64 之间")
	public String getColorGenericName() {
		return colorGenericName;
	}

	public void setColorGenericName(String colorGenericName) {
		this.colorGenericName = colorGenericName;
	}
	
	@Length(min=0, max=255, message="限用成分备注信息（备注项显示内容）长度必须介于 0 和 255 之间")
	public String getLimitedRemarks() {
		return limitedRemarks;
	}

	public void setLimitedRemarks(String limitedRemarks) {
		this.limitedRemarks = limitedRemarks;
	}
	
}