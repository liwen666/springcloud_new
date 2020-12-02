/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/10/29 13:37:21                          */
/*==============================================================*/


drop table if exists mc_user;

drop table if exists meta_category;

drop table if exists meta_data_object;

drop table if exists meta_data_object_info;

drop table if exists meta_data_source_info;

drop table if exists meta_function;

drop table if exists meta_function_info;

drop table if exists meta_job_info;

drop table if exists meta_job_object;

drop table if exists meta_object_field;

drop table if exists meta_relation_info;

drop table if exists meta_work_info;

/*==============================================================*/
/* Table: mc_user                                               */
/*==============================================================*/
create table mc_user
(
   id                   char(20) not null,
   create_by            varchar(100) character set utf8,
   create_time          datetime default NULL,
   modified_by          varchar(100) character set utf8,
   modified_time        datetime default NULL,
   doman_type           varchar(20) character set utf8,
   email                varchar(500) character set utf8,
   enabled              bit(1) default NULL,
   forbidden_date       datetime default NULL,
   last_password_rest_date datetime default NULL,
   last_password_reset_type int(11) default NULL,
   mobile               varchar(50) character set utf8,
   nick_name            varchar(500) character set utf8,
   org_name             longtext character set utf8,
   password             varchar(100) character set utf8,
   user_name            varchar(200) character set utf8,
   primary key (id),
   unique key UK_jann1p0scjpjpfgp7ycsaoexf (user_name)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT = '用户信息表';

/*==============================================================*/
/* Table: meta_category                                         */
/*==============================================================*/
create table meta_category
(
   category_id          char(20) not null comment '分类ID',
   content_code         varchar(50) default NULL comment '租户号',
   create_person        varchar(50) default NULL comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   update_person        varchar(50) default NULL comment '更新人',
   update_time          datetime default NULL comment '更新时间',
   category_type        varchar(20) default NULL comment '分类类型',
   name                 varchar(100) default NULL comment '分类名称',
   parent_id            char(20) default NULL comment '分类父ID',
   used                 bit(1) default NULL comment '是否启动',
   primary key (category_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '分类信息' ;

/*==============================================================*/
/* Table: meta_data_object                                      */
/*==============================================================*/
create table meta_data_object
(
   resource_version_id  char(20) not null comment '表版本ID',
   content_code         varchar(50) default NULL comment '租户号' ,
   create_time          datetime default NULL comment '创建时间',
   create_person        varchar(50) default NULL comment '创建人',
   field_ids            varchar(8000) default NULL comment '引用ID数组',
   resource_id          char(20) default NULL comment '表信息infoID',
   update_person        varchar(50) default NULL comment '更新人',
   update_time          datetime default NULL comment '更新时间',
   version_code         varchar(255) default NULL comment '版本号',
   version_state        varchar(20) default NULL comment '版本状态',
   params_json          longtext comment 'ddl 语句参数 是一个json数组',
   used                 bit(1) default NULL,
   ddl_sql              longtext comment 'ddl 语句内容',
   primary key (resource_version_id),
   key idx_resource_id (resource_id),
   key idx_version_state (version_state),
   key meta_data_object_content_code_IDX (content_code, resource_id, version_code),
   key idx_meta_data_object_resource_id (resource_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '表版本信息';

/*==============================================================*/
/* Table: meta_data_object_info                                 */
/*==============================================================*/
create table meta_data_object_info
(
   resource_id          char(20) not null comment '表信息ID',
   code                 varchar(255) default NULL comment '表的唯一标识',
   content_code         varchar(50) default NULL comment '租户号',
   create_person        varchar(50) default NULL comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   description          varchar(200) default NULL comment '描述信息',
   name                 varchar(100) default NULL comment '表名称',
   resource_state       varchar(255) default NULL comment '表状态',
   resource_type        varchar(20) default NULL comment '表类型',
   update_person        varchar(50) default NULL comment '更新人',
   update_time          datetime default NULL comment '更新时间',
   data_source_id       char(20) default NULL comment '表所属数据源ID',
   config_props         varchar(2000) comment '例如kafka表等的配置信息' ,
   zpl_job_id           varchar(300) comment 'zpl段落ID',
   primary key (resource_id),
   key data_source_id (data_source_id),
   key meta_data_object_info_resource_id_IDX (resource_id, name),
   key idx_meta_data_object_info_code (code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '表详情';

/*==============================================================*/
/* Table: meta_data_source_info                                 */
/*==============================================================*/
create table meta_data_source_info
(
   data_source_id       char(20) not null comment '数据源ID',
   content_code         varchar(50) default NULL comment '租户号',
   create_person        varchar(50) default NULL comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   update_person        varchar(50) default NULL comment '更新人',
   update_time          datetime default NULL comment '更新时间',
   db_config_json       varchar(255) default NULL comment '连接器的配置信息',
   db_type              varchar(20) default NULL comment '数据源类型',
   description          varchar(200) default NULL comment '描述信息',
   source_code          varchar(255) default NULL comment '数据源标识',
   source_name          varchar(100) default NULL comment '数据源名称',
   db_name              varchar(100) default NULL comment '数据库名称',
   cron_expression      varchar(100) comment '定时器表达式',
   zpl_job_id           varchar(300) comment 'zpl段落ID',
   primary key (data_source_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '数据源信息';

/*==============================================================*/
/* Table: meta_function                                         */
/*==============================================================*/
drop table if exists meta_function;
create table if not exists meta_function
(
   function_id          char(20) not null comment '函数版本ID',
   content_code         varchar(50) default NULL comment '租户号',
   resource_id          char(20) default NULL comment '函数信息ID',
   version_code         int(20) default NULL comment '版本号',
   version_state        varchar(20) default NULL comment '版本状态',
   param_fields         varchar(2000) default NULL comment '函数参数',
   param_field_types    varchar(2000) default NULL comment '函数参数类型',
   return_value_type    varchar(20) default NULL comment '函数返回值类型',
   scripts              varchar(2000) default NULL comment '函数脚本内容',
   language             varchar(2000) default NULL comment '函数语言',
   create_person        varchar(50) default NULL comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   update_person        varchar(50) default NULL comment '修改人',
   update_time          datetime default NULL comment '修改时间',
   primary key (function_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '函数版本';

/*==============================================================*/
/* Table: meta_function_info                                    */
/*==============================================================*/
drop table if exists meta_function_info;
create table if not exists meta_function_info
(
   resource_id          char(20) not null comment '函数信息ID',
   action               varchar(255) default NULL comment '函数作用',
   name                 varchar(100) default NULL comment '函数名称',
   content_code         varchar(50) default NULL comment '租户号',
   example              varchar(1000) default NULL comment '示例',
   description          varchar(1000) default NULL comment '描述信息',
   language             varchar(20) default NULL comment '函数语言类型',
   function_type        varchar(20) default NULL comment '函数类型',
   used                 int(4) default NULL comment '函数使用与否',
   buildin              int(4) default '0' comment '函数是否内置',
   zpl_function_id      varchar(255) default NULL comment 'function对应zeppelin的段落id',
   create_person        varchar(50) default NULL comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   update_person        varchar(50) default NULL comment '修改人',
   update_time          datetime default NULL comment '修改时间',
   primary key (resource_id) ,
   key idx_meta_function_info_name (name)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '函数信息';

/*==============================================================*/
/* Table: meta_job_info                                         */
/*==============================================================*/
create table meta_job_info
(
   resource_id          char(20) not null comment 'jobID',
   name                 varchar(100) default NULL comment 'job名称',
   code                 varchar(255) default NULL comment 'job标识',
   content_code         varchar(50) default NULL comment '租户号',
   create_time          datetime default NULL comment '创建时间',
   description          varchar(200) default NULL comment '描述信息',
   job_type             varchar(255) default NULL comment 'job类型',
   job_state            varchar(255) default NULL comment 'job状态',
   create_person        varchar(50) default NULL comment '创建人',
   update_person        varchar(50) default NULL comment '修改人',
   update_time          datetime default NULL comment '修改时间',
   source_ids           varchar(255) default NULL comment '加工数据源表ID',
   target_id            char(20) default NULL comment '加工数据目标表ID',
   zpl_catagre_id       varchar(30) comment 'zpl分类ID',
   zpl_notebook_id      varchar(30) comment 'zpl noteBookId',
   zpl_job_id      varchar(300) comment 'zpl noteBookId',
   primary key (resource_id),
   key data_source_id (target_id),
   key meta_data_object_info_resource_id_IDX (resource_id, name),
   key idx_meta_data_object_info_code (code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'job 信息';

/*==============================================================*/
/* Table: meta_job_object                                       */
/*==============================================================*/
create table meta_job_object
(
   job_object_id       char(20) not null comment 'job 版本ID',
   content_code         varchar(50) default NULL comment '租户号',
   create_time          datetime default NULL comment '创建时间',
   resource_id          char(20) default NULL comment 'job 信息ID',
   create_person        varchar(50) default NULL comment '创建人',
   update_person        varchar(50) default NULL comment '修改人',
   update_time          datetime default NULL comment '修改时间',
   version_code         varchar(255) default NULL comment '版本号',
   version_state        varchar(20) default NULL comment '版本状态',
   sql_content          longtext comment 'job 详细内容',
   param_json           varchar(100) comment 'job 参数信息 JSON 数组',
   primary key (job_object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = 'job 版本';

/*==============================================================*/
/* Table: meta_object_field                                     */
/*==============================================================*/
create table meta_object_field
(
   object_field_id      char(20) not null comment '字段ID',
   content_code         varchar(50) default NULL comment '租户号',
   create_person        varchar(50) default NULL comment '创建人',
   create_time          datetime default NULL comment '创建时间',
   update_person        varchar(50) default NULL comment '修改人',
   update_time          datetime default NULL comment '修改时间',
   default_value        varchar(200) default NULL comment '默认值',
   description          varchar(500) default NULL comment '描述信息',
   field_code           varchar(500) default NULL comment '字段标识',
   field_format         varchar(200) default NULL comment '',
   field_length         int(11) default NULL comment '字段长度',
   field_name           varchar(100) default NULL comment '字段名称',
   field_type           varchar(20) default NULL comment '字段类型',
   is_key               bit(1) default NULL comment '是否是主键',
   object_type          varchar(20) default NULL comment '字段类型',
   scale_length         int(11) default NULL comment '',
   primary key (object_field_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '字段信息';

/*==============================================================*/
/* Table: meta_relation_info                                    */
/*==============================================================*/
create table meta_relation_info
(
   source_id            char(20) not null comment '源表ID',
   source_type          varchar(50) default NULL comment '源表类型',
   target_id            char(20) not null comment '目标表ID',
   target_type          varchar(50) default NULL comment '目标表类型',
   comment              varchar(500) default NULL comment '源表到目标表逻辑关系'
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '对象之间的关联关系 ';


/*==============================================================*/
/* Table: meta_work_info                                        */
/*==============================================================*/
drop table if exists meta_work_info;
create table if not exists meta_work_info
(
   resource_id          char(20) not null comment 'work ID',
   content_code         varchar(50) default NULL comment '租户号',
   create_time          datetime default NULL comment '创建时间',
   create_person        varchar(50) default NULL comment '创建人',
   update_person        varchar(50) default NULL comment '修改人',
   update_time          datetime default NULL comment '修改时间',
   work_state           varchar(20) default NULL comment 'work 状态 ',
   zpl_notebook_id      varchar(30) comment 'work 对应的zpl的ID',
   note_path      varchar(300) comment '对应的work名',
   primary key (resource_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;





CREATE TABLE IF NOT EXISTS  `table_entity_conversion_rule` (
  `id` int(11) NOT NULL,
  `content_code` varchar(50) DEFAULT NULL,
  `create_person` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_person` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `data_conversion_model` varchar(255) DEFAULT NULL,
  `entity_key` varchar(255) DEFAULT NULL,
  `entity_value` varchar(255) DEFAULT NULL,
  `table_code` varchar(255) DEFAULT NULL,
  `table_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_resource_id` (`table_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for table_code_relation
-- ----------------------------
CREATE TABLE IF NOT EXISTS `table_code_relation`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `filter_handle_bean` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `primary_code_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `primary_table_china_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `primary_table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `slave_code_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `slave_table_china_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `slave_table_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;



CREATE TABLE IF NOT EXISTS `table_mark_init`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `table_china_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;



CREATE TABLE IF NOT EXISTS  `table_param_config`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `key_column` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id_column` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_describe` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `version_column` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;



CREATE TABLE IF NOT EXISTS  `table_history_data`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `data` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `history_data_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `primary_key_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `primary_table_china_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


CREATE TABLE  IF NOT EXISTS  `table_import_sort`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `flag` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `table_code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;



CREATE TABLE  IF NOT EXISTS `table_code_config`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `columns` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `handle_bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ignore_column_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ignore_column_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_code_china_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `where_sql_columns` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


CREATE TABLE IF NOT EXISTS `table_conversion_key`  (
  `id` int(11) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `used` bit(1) NULL DEFAULT NULL,
  `conversion_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `handle_bean_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `is_json_object` bit(1) NULL DEFAULT NULL,
  `is_multiple_relation` bit(1) NULL DEFAULT NULL,
  `table_code_china_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----
-- ----
-- 租户信息
-- ----
DROP TABLE IF EXISTS `meta_tenant`;
CREATE TABLE IF NOT EXISTS `meta_tenant`  (
  `tenant_id` char(20) NOT NULL,
  `create_person` varchar(255) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_person` varchar(255) NULL DEFAULT NULL,
  `tenant_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenant_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tenant_description` varchar(2000) NULL DEFAULT NULL,
  PRIMARY KEY (`tenant_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- 导入导出配置


-- 配置code
INSERT INTO `table_code_config`(`id`, `create_time`, `used`, `columns`, `handle_bean_name`, `ignore_column_name`, `ignore_column_value`, `table_code_china_name`, `table_code_name`, `where_sql_columns`) VALUES (1, '2020-08-27 15:07:55', b'1', 'name', 'anyDataHubHandler', NULL, NULL, 'job信息表', 'meta_job_info', 'content_code');
INSERT INTO `table_code_config`(`id`, `create_time`, `used`, `columns`, `handle_bean_name`, `ignore_column_name`, `ignore_column_value`, `table_code_china_name`, `table_code_name`, `where_sql_columns`) VALUES (2, '2020-08-27 15:07:55', b'1', 'resource_id,version_code', 'anyDataHubHandler', NULL, NULL, 'job版本表', 'meta_job_object', 'content_code');

-- 配置关联导出

INSERT INTO `table_code_relation`(`id`, `create_time`, `used`, `filter_handle_bean`, `primary_code_key`, `primary_table_china_name`, `primary_table_name`, `slave_code_key`, `slave_table_china_name`, `slave_table_name`) VALUES (1, NULL, b'1', 'anyDataHubHandler', 'resource_id', 'job信息表', 'meta_job_info', 'resource_id', '对象信息表', 'meta_job_object');


CREATE TABLE  if not exists `hibernate_sequence`  (
  `next_val` bigint(20) NULL DEFAULT 0
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `table_import_sort`(`id`, `create_time`, `used`, `flag`, `order_id`, `table_code_name`) VALUES (100, NULL, b'1', NULL, 1, 'meta_category');
INSERT INTO `table_import_sort`(`id`, `create_time`, `used`, `flag`, `order_id`, `table_code_name`) VALUES (200, NULL, b'1', NULL, 1, 'meta_function_info');
INSERT INTO `table_import_sort`(`id`, `create_time`, `used`, `flag`, `order_id`, `table_code_name`) VALUES (300, NULL, b'1', NULL, 1, 'meta_function');
INSERT INTO `table_import_sort`(`id`, `create_time`, `used`, `flag`, `order_id`, `table_code_name`) VALUES (400, NULL, b'1', NULL, 1, 'meta_job_info');
INSERT INTO `table_import_sort`(`id`, `create_time`, `used`, `flag`, `order_id`, `table_code_name`) VALUES (500, NULL, b'1', NULL, 1, 'meta_job_object');
INSERT INTO `table_import_sort`(`id`, `create_time`, `used`, `flag`, `order_id`, `table_code_name`) VALUES (600, NULL, b'1', NULL, 1, 'meta_work_info');

-- 配置转换KEY
INSERT INTO `table_conversion_key`(`id`, `create_time`, `used`, `conversion_key`, `handle_bean_name`, `is_json_object`, `is_multiple_relation`, `table_code_china_name`, `table_code_name`) VALUES (1, '2020-09-02 14:23:27', b'1', 'meta_job_info@resource_id', 'anyDataHubHandler', b'0', b'0', 'job版本表', 'meta_job_object');

INSERT INTO `table_param_config`(`id`, `create_time`, `used`, `key_column`, `resource_id_column`, `table_code_name`, `table_describe`, `version_column`) VALUES (1621, NULL, b'1', 'next_val', NULL, 'key_sequence', 'hibernate_sequence', NULL);
