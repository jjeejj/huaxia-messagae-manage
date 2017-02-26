SET SESSION FOREIGN_KEY_CHECKS=0;

/* Create Tables */
/* 该只是查询看没有业务逻辑 */
DROP TABLE IF EXISTS mms_risk_material_assessment;
CREATE TABLE mms_risk_material_assessment
(
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	risk_material_name varchar(255) NOT NULL COMMENT '风险物质名称',
	source varchar(255) COMMENT '主要来源',
	safety_threshold varchar(50) COMMENT '安全阈值',
	evaluation_basis varchar(255) COMMENT '评估依据',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '风险物质评估信息';

DROP TABLE IF EXISTS mms_formula;
CREATE TABLE mms_formula
(
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	formula_name varchar(255) NOT NULL COMMENT '配方名称',
	raw_material_content_total varchar(10) COMMENT '总原料含量（%）',
	actual_component_content_total varchar(10) COMMENT '总实际成份含量（%）',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '配方信息';

DROP TABLE IF EXISTS mms_formula_details;
CREATE TABLE mms_formula_details
(
	id varchar(64) NOT NULL COMMENT '编号',
	formula_id varchar(64) NOT NULL COMMENT '配方id',
	sequence varchar(64) NOT NULL COMMENT '序号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	inic_name varchar(100) COMMENT 'INCI名',
	raw_material_content varchar(10) COMMENT '原料含量（%）',
	compound_percentage varchar(10) COMMENT '复配百分比（%）',
	actual_component_content varchar(10) COMMENT '实际成份含量（%）',
	purpose_of_use varchar(100) COMMENT '使用目的',
	risk_material varchar(100) COMMENT '风险物质',
  component_type char(1) COMMENT '成分类型(1:正常，2：禁用，3：限用)',
  actual_component_content_status char(1) COMMENT '对于限用成分的实际成份含量状态(1:符合标准，2：不符合)',
  name_or_inic_status char(1) COMMENT '标准中文名称或INCI名的状态(0:正常,1:标准中文名称和INCI名不一致,2：标准中文名称未出现,3：INCI名未出现,4:标准中文名称和INCI名都未出现)',
  plant_component char(1) COMMENT '是否是植物成分(1:是,2:不是)',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '配方详情信息';

DROP TABLE IF EXISTS cms_name_to_risk_material;
CREATE TABLE mms_name_to_risk_material
(
	id varchar(64) NOT NULL COMMENT '编号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	risk_material varchar(100) COMMENT '风险物质',
	transform_level char(1) COMMENT '转换查询级别(1:精确转换，2：模糊转换)',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '标准中文名对应的风险物质';

DROP TABLE IF EXISTS mms_forbidden_component;
CREATE TABLE mms_forbidden_component
(
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	inic_name varchar(100) COMMENT 'INCI名',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '化妆品安全技术规范的禁用成分';

/* 已使用原料目录*/




