/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 政策法规数据库Entity
 * @author jiang
 * @version 2017-03-29
 */
public class PoliciesRegulations extends DataEntity<PoliciesRegulations> {
	
	private static final long serialVersionUID = 1L;
	private String sequence;		// 序号
	private String fileName;		// 文件名称
	private String sourceHref;		// 文件原始链接
	private String documentNumber;		// 文号
	private String fileType;		// 文件分类（1：法规、2：标准、3：技术资料）
	private String fileTimeliness;		// 文件时效性（1：现行、2：征求意见、3：历史文件）
	
	public PoliciesRegulations() {
		super();
	}

	public PoliciesRegulations(String id){
		super(id);
	}

	@Length(min=1, max=64, message="序号长度必须介于 1 和 64 之间")
	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=1, max=64, message="文件名称长度必须介于 1 和 64 之间")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Length(min=0, max=64, message="文件原始链接长度必须介于 0 和 64 之间")
	public String getSourceHref() {
		return sourceHref;
	}

	public void setSourceHref(String sourceHref) {
		this.sourceHref = sourceHref;
	}
	
	@Length(min=1, max=32, message="文号长度必须介于 1 和 32 之间")
	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
	
	@Length(min=1, max=1, message="文件分类（1：法规、2：标准、3：技术资料）长度必须介于 1 和 1 之间")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	@Length(min=0, max=1, message="文件时效性（1：现行、2：征求意见、3：历史文件）长度必须介于 0 和 1 之间")
	public String getFileTimeliness() {
		return fileTimeliness;
	}

	public void setFileTimeliness(String fileTimeliness) {
		this.fileTimeliness = fileTimeliness;
	}
	
}