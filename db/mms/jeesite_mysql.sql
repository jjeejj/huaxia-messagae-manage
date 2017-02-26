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
	create_by varchar(64) COMMENT '创建者',
	create_date datetime COMMENT '创建时间',
	update_by varchar(64) COMMENT '更新者',
	update_date datetime COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '配方详情信息';

DROP TABLE IF EXISTS cms_name_to_risk_material;
CREATE TABLE cms_name_to_risk_material
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

DROP TABLE IF EXISTS cms_forbidden_component;
CREATE TABLE cms_forbidden_component
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


/* Create Indexes */

CREATE INDEX cms_article_create_by ON cms_article (create_by ASC);
CREATE INDEX cms_article_title ON cms_article (title ASC);
CREATE INDEX cms_article_keywords ON cms_article (keywords ASC);
CREATE INDEX cms_article_del_flag ON cms_article (del_flag ASC);
CREATE INDEX cms_article_weight ON cms_article (weight ASC);
CREATE INDEX cms_article_update_date ON cms_article (update_date ASC);
CREATE INDEX cms_article_category_id ON cms_article (category_id ASC);
CREATE INDEX cms_category_parent_id ON cms_category (parent_id ASC);
/*CREATE INDEX cms_category_parent_ids ON cms_category (parent_ids ASC);*/
CREATE INDEX cms_category_module ON cms_category (module ASC);
CREATE INDEX cms_category_name ON cms_category (name ASC);
CREATE INDEX cms_category_sort ON cms_category (sort ASC);
CREATE INDEX cms_category_del_flag ON cms_category (del_flag ASC);
CREATE INDEX cms_category_office_id ON cms_category (office_id ASC);
CREATE INDEX cms_category_site_id ON cms_category (site_id ASC);
CREATE INDEX cms_comment_category_id ON cms_comment (category_id ASC);
CREATE INDEX cms_comment_content_id ON cms_comment (content_id ASC);
CREATE INDEX cms_comment_status ON cms_comment (del_flag ASC);
CREATE INDEX cms_guestbook_del_flag ON cms_guestbook (del_flag ASC);
CREATE INDEX cms_link_category_id ON cms_link (category_id ASC);
CREATE INDEX cms_link_title ON cms_link (title ASC);
CREATE INDEX cms_link_del_flag ON cms_link (del_flag ASC);
CREATE INDEX cms_link_weight ON cms_link (weight ASC);
CREATE INDEX cms_link_create_by ON cms_link (create_by ASC);
CREATE INDEX cms_link_update_date ON cms_link (update_date ASC);
CREATE INDEX cms_site_del_flag ON cms_site (del_flag ASC);



