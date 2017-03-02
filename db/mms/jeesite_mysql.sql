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
	raw_material_content_total varchar(64) COMMENT '总原料含量（%）',
	actual_component_content_total varchar(64) COMMENT '总实际成份含量（%）',
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
  actual_component_content_status char(1) COMMENT '对于限用成分的实际成份含量状态(1:符合标准，2：不符合标准)',
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

DROP TABLE IF EXISTS mms_name_to_risk_material;
CREATE TABLE mms_name_to_risk_material
(
	id varchar(64) NOT NULL COMMENT '编号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	risk_material varchar(100) COMMENT '风险物质',
	transform_level char(1) COMMENT '转换级别(1:精确转换，2-100：模糊转换级别，数字越大级别越高)',
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
	inic_name varchar(100) COMMENT '原植(动)物拉丁文学名或植(动)物英文名 ',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '化妆品安全技术规范的禁用成分';

/*化妆品安全技术规范的限用成分*/
DROP TABLE IF EXISTS mms_limited_component;
CREATE TABLE mms_limited_component
(
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	inic_name varchar(100) COMMENT 'INIC名 ',
	english_name varchar(100) COMMENT '英文名称 ',
	use_range varchar(10) COMMENT '适用及(或)使用范围 ',
	max_allow_concentretion varchar(10) COMMENT '最大允许浓度',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '化妆品安全技术规范的限用成分';

/* 已使用原料目录*/
DROP TABLE IF EXISTS mms_raw_material_list;
CREATE TABLE mms_raw_material_list
(
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	inic_name varchar(100) COMMENT 'INCI名/英文名称',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '已使用原料目录';

/* 产品*/

DROP TABLE IF EXISTS mms_product;
CREATE TABLE mms_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	product_id varchar(64) NOT NULL COMMENT '产品编号',
	english_name varchar(100) NOT NULL COMMENT '中文名称',
	chinese_name varchar(100) NOT NULL COMMENT '英文名称',
	type char(1) COMMENT '类别',
	work_matters varchar(100) NOT NULL COMMENT '工作事项',
	product_leader varchar(64) NOT NULL COMMENT '产品负责人',
	project_leader varchar(64) NOT NULL COMMENT '项目负责人',
	enterprise_application varchar(64) NOT NULL COMMENT '申请企业',
	actual_production_enterprise varchar(64)  COMMENT '实际生产企业',
	responsible_unit_in_china varchar(64)  COMMENT '在华责任单位',
	project_time datetime  COMMENT '立项时间',
	contract_signing_time datetime  COMMENT '合同签订时间',
	sample_time datetime  COMMENT '来样时间',
	sample_quantity varchar(10)  COMMENT '样品数量',
	total_number_of_samples varchar(10)  COMMENT '送检总数',
	submission_time datetime  COMMENT '送检时间',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '产品';



