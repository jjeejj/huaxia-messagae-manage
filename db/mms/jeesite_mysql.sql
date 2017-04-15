SET SESSION FOREIGN_KEY_CHECKS=0;

/* Create Tables */
-- inci 名与标准中文名相互转换
DROP TABLE IF EXISTS mms_inciname_convert_chinesename;
CREATE TABLE mms_inciname_convert_chinesename
(
  id varchar(64) NOT NULL COMMENT '编号',
  sequence varchar(64) NOT NULL COMMENT '序号',
  standard_chinese_name varchar(200) NOT NULL COMMENT '标准中文名称',
	inci_name varchar(200) COMMENT 'INCI名',
	cas_number varchar(200) COMMENT 'CAS号',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = 'inci名与标准中文名相互转换';

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
	product_number varchar(64) COMMENT '归属产品编号',
	sequence varchar(64)  COMMENT '序号',
	formula_name varchar(255) COMMENT '配方名称',
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
	actual_component_content varchar(32) COMMENT '实际成份含量（%）',
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
	inic_name varchar(1000) COMMENT '原植(动)物拉丁文学名或植(动)物英文名 ',
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

-- 化妆品准用成分，也就是特殊类别的先用成分
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
	standard_chinese_name varchar(200) NOT NULL COMMENT '标准中文名称',
	inic_name varchar(200) COMMENT 'INCI名/英文名称',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '已使用原料目录';

-- 原料使用数据库，即对配方的给个原料进行统计分析，包括该原料的使用实际成分含量的最大值和最小值，使用目的和风向物质
DROP TABLE IF EXISTS mms_material_used_database;
CREATE TABLE mms_material_used_database
(
	id varchar(64) NOT NULL COMMENT '编号',
-- 	sequence varchar(64) NOT NULL AUTO_INCREMENT COMMENT '序号',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称(即原料名称)',
	min_actual_component_content varchar(32) COMMENT '最小实际成份含量（%）',
	max_actual_component_content varchar(32) COMMENT '最大实际成份含量（%）',
  purposes_of_use text COMMENT '所有使用目的',
  risk_materials text COMMENT '所有风险物质',
  create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '原料使用数据库';

/* 产品汇总信息 */
DROP TABLE IF EXISTS mms_product;
CREATE TABLE mms_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	market_product_id varchar(64) NOT NULL COMMENT '市场产品id',
	comprehensive_product_id varchar(64)  COMMENT '综合产品id',
	declare_product_id varchar(64)  COMMENT '申报产品id',
	product_status char(1)  COMMENT '产品状态',
	product_leader varchar(64)  COMMENT '产品负责人',
	product_process char(10)  COMMENT '产品进度（%）',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '产品';

DROP TABLE IF EXISTS mms_market_product;
CREATE TABLE mms_market_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	product_number varchar(64) NOT NULL COMMENT '产品编号',
	english_name varchar(100) COMMENT '英文名称',
	chinese_name varchar(100) NOT NULL COMMENT '中文名称',
	country_of_origin varchar(64)COMMENT '原产国',
	product_type char(1) COMMENT '类别(1：非特，2：特殊，3：延续，4：变更，5：其他)',
	work_matters varchar(100)  COMMENT '工作事项',
	product_leader varchar(64) NOT NULL COMMENT '产品负责人',
	project_leader varchar(64) NOT NULL COMMENT '项目负责人',
	enterprise_application varchar(64) COMMENT '申请企业',
	enterprise_application_address varchar(100) COMMENT '申请企业地址',
	enterprise_application_phone varchar(32) COMMENT '申请企业电话',
	enterprise_application_contacts varchar(100)  COMMENT '申请企业联系人',
	actual_production_enterprise varchar(64)  COMMENT '实际生产企业',
	actual_production_enterprise_address varchar(64)  COMMENT '实际生产企业地址',
	responsible_unit_in_china varchar(64)  COMMENT '在华责任单位',
	responsible_unit_in_china_address varchar(64)  COMMENT '在华责任单位地址',
	responsible_unit_in_china_phone varchar(64)  COMMENT '在华责任单位电话',
	responsible_unit_in_china_fax varchar(64)  COMMENT '在华责任单位传真',
	responsible_unit_in_china_zip_code varchar(64)  COMMENT '在华责任单位邮编',
	project_time datetime  COMMENT '立项时间',
	contract_number varchar(64)  COMMENT '合同编号',
	contract_signing_time datetime  COMMENT '合同签订时间',
	arrival_company datetime  COMMENT '来款单位',
	product_handle_person_id varchar(64) NOT NULL COMMENT '产品处理人id',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '市场产品';

/*  综合产品 */
DROP TABLE IF EXISTS mms_comprehensive_product;
CREATE TABLE mms_comprehensive_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	arrival_time datetime  COMMENT '来款时间',
	sample_time datetime  COMMENT '来样时间',
	sample_quantity varchar(10)  COMMENT '样品数量',
	administrative_license_inspection_no  varchar(64)  COMMENT '行政许可检验受理编号',
	administrative_license_inspection_report_time datetime  COMMENT '行政许可检验取报告时间',
	human_test_acceptance_no  varchar(64)  COMMENT '人体检验受理编号',
	human_test_acceptance_report_time datetime  COMMENT '人体检验取报告时间',
	risk_test_acceptance_no  varchar(64)  COMMENT '风险检验受理编号',
	risk_test_acceptance_report_time datetime  COMMENT '风险检验取报告时间',
	product_handle_person_id varchar(64) COMMENT '产品处理人id',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '综合产品';

/*  申报产品 */
DROP TABLE IF EXISTS mms_declare_product;
CREATE TABLE mms_declare_product
(
	id varchar(64) NOT NULL COMMENT '编号',
	total_submission varchar(64)  COMMENT '送检总数',
	administrative_license_inspection_time  datetime  COMMENT '行政许可送检时间',
	administrative_license_inspection_organization varchar(64)  COMMENT '行政许可检验机构',
	administrative_license_inspection_project varchar(64)  COMMENT '行政许可检验项目',
	administrative_license_inspection_number varchar(64)  COMMENT '行政许可送检数量',
	send_body_time datetime  COMMENT '人体检验送检时间',
	send_body_organization  varchar(64)   COMMENT '人体检验送检机构',
	send_body_project  varchar(64)   COMMENT '人体检验项目',
	send_body_number varchar(64)  COMMENT '人体检验数量',
	send_risk_test_time datetime  COMMENT '风险检验时间',
	send_risk_test_organization  varchar(64)   COMMENT '风险检验机构',
	send_risk_test_project  varchar(64)   COMMENT '风险检验项目',
	send_risk_test_number varchar(64)  COMMENT '风险检验数量',
	report_time datetime  COMMENT '上报时间／申报时间',
	acceptance_time datetime  COMMENT '受理时间',
	acceptance_number varchar(64)  COMMENT '受理编号',
	document_time datetime  COMMENT '取得批件时间',
	document_number varchar(64)  COMMENT '批件编号',
	product_status_remark char(1) COMMENT '产品状态备注（1：通过审批，2：不予批准3：终止申报”）',
	next_opinion_time datetime  COMMENT '下意见时间',
	opinion_content varchar(255) COMMENT '意见内容',
	reply_opinion datetime  COMMENT '回复意见时间',
  other_description varchar(64)  COMMENT '其他说明',
  color_character varchar(64)  COMMENT '颜色性状',
  sample_marking  varchar(64)  COMMENT '样品标记（生产日期或批号）',
  date_of_expiry varchar(32)  COMMENT '保质期或限期使用日期（包装标注的保质期或限期使用日期）',
  technology_date_of_expiry varchar(32)  COMMENT '保质期（技术要求中显示的保质期）',
  smell varchar(32) COMMENT '气味',
  specifications varchar(32) COMMENT '规格',
	product_handle_person_id varchar(64) COMMENT '产品处理人id',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	status_remarks varchar(255) COMMENT '状态备注(目前该字段没有作用)',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '申报产品';

-- 产品流水编号
DROP TABLE IF EXISTS mms_product_flow_number;
CREATE TABLE mms_product_flow_number(
  id varchar(64) NOT NULL COMMENT '编号',
  product_id varchar(64) NOT NULL COMMENT '对应的产品ID',
	flow_number varchar(32) NOT NULL COMMENT '流水编号',
	flow_year varchar(32) NOT NULL COMMENT '流水年',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '产品流水编号';

-- 审评意见数据库
DROP TABLE IF EXISTS mms_assess_suggestion;
CREATE TABLE mms_assess_suggestion (
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	suggestion_type char(1) NOT NULL COMMENT '意见类别',
	main_content text  NULL COMMENT '主要内容',
	issuance_date datetime COMMENT '出具日期',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '审评意见';

-- Forbidden words 禁用语词汇数据库
DROP TABLE IF EXISTS mms_forbidden_words;
CREATE TABLE mms_forbidden_words (
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	forbidden_name char(64) NOT NULL COMMENT '禁用名称',
	forbidden_explain  text  NULL COMMENT '禁用说明',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '禁用语词汇';

-- Material use 原料使用数据库
DROP TABLE IF EXISTS mms_material_use;
CREATE TABLE mms_material_use (
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '原料序号（导入配方详情的每一项的序号）',
	standard_chinese_name varchar(100) NOT NULL COMMENT '标准中文名称',
	min_actual_component_content varchar(32) COMMENT '最小实际成份含量（%）',
	max_actual_component_content varchar(32) COMMENT '最大实际成份含量（%）',
	purpose_of_use varchar(100) COMMENT '使用目的',
	risk_material varchar(100) COMMENT '风险物质',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '原料使用';

-- 政策法规数据库
DROP TABLE IF EXISTS mms_policies_regulations;
CREATE TABLE mms_policies_regulations (
	id varchar(64) NOT NULL COMMENT '编号',
	sequence varchar(64) NOT NULL COMMENT '序号',
	file_name varchar(64) NOT NULL COMMENT '文件名称',
	source_href varchar(64)  COMMENT '文件原始链接',
	upload_address varchar(1000)  COMMENT '文件上传地址',
	document_number varchar(32) NOT NULL COMMENT '文号',
	file_type char(1) NOT NULL COMMENT '政策法规件分类（1：法规、2：标准、3：技术资料）',
	file_timeliness char(1) COMMENT '政策法规文件时效性（1：现行、2：征求意见、3：历史文件）',
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '政策法规数据库';



