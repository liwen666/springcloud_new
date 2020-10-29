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
   id                   bigint(20) not null,
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
ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*==============================================================*/
/* Table: meta_category                                         */
/*==============================================================*/
create table meta_category
(
   category_id          int(11) not null,
   content_code         varchar(50) default NULL,
   create_person        varchar(50) default NULL,
   create_time          datetime default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   category_type        varchar(20) default NULL,
   name                 varchar(100) default NULL,
   parent_id            int(11) default NULL,
   used                 bit(1) default NULL,
   primary key (category_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_data_object                                      */
/*==============================================================*/
create table meta_data_object
(
   resource_version_id  int(11) not null,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   field_ids            varchar(8000) default NULL,
   resource_id          int(11) default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   version_code         varchar(255) default NULL,
   version_state        varchar(20) default NULL,
   params_json          longtext,
   used                 bit(1) default NULL,
   ddl_sql              longtext,
   primary key (resource_version_id),
   key idx_resource_id (resource_id),
   key idx_version_state (version_state),
   key meta_data_object_content_code_IDX (content_code, resource_id, version_code),
   key idx_meta_data_object_resource_id (resource_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_data_object_info                                 */
/*==============================================================*/
create table meta_data_object_info
(
   resource_id          int(11) not null,
   code                 varchar(255) default NULL,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   description          varchar(200) default NULL,
   name                 varchar(100) default NULL,
   resource_state       varchar(255) default NULL,
   resource_type        varchar(20) default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   data_source_id       int(11) default NULL,
   config_props         varchar(2000) comment '存储表的额外信息，比如kafka的topic、groupId、读取模式;三方接口认为一张表，可能是传递参数这些数据',
   primary key (resource_id),
   key data_source_id (data_source_id),
   key meta_data_object_info_resource_id_IDX (resource_id, name),
   key idx_meta_data_object_info_code (code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_data_source_info                                 */
/*==============================================================*/
create table meta_data_source_info
(
   data_source_id       int(11) not null,
   content_code         varchar(50) default NULL,
   create_person        varchar(50) default NULL,
   create_time          datetime default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   db_config_json       varchar(255) default NULL,
   db_type              varchar(20) default NULL,
   description          varchar(200) default NULL,
   source_code          varchar(255) default NULL,
   source_name          varchar(100) default NULL,
   cron_expression      varchar(100),
   primary key (data_source_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_function                                         */
/*==============================================================*/
create table meta_function
(
   function_id          int(11) not null,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   resource_id          int(11) default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   version_code         varchar(255) default NULL,
   version_state        varchar(20) default NULL,
   function_content     longtext,
   primary key (function_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_function_info                                    */
/*==============================================================*/
create table meta_function_info
(
   resource_id          int(11) not null,
   code                 varchar(255) default NULL,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   example              varchar(1000) default NULL,
   description          varchar(1000) default NULL,
   name                 varchar(100) default NULL,
   resource_state       varchar(255) default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   language             varchar(20) default NULL,
   function_type        varchar(20) default NULL,
   param_fields         longtext comment '包含{形参和参数类型}的JSON数组',
   return_value_type    varchar(20) default NULL,
   primary key (resource_id),
   key idx_meta_function_info_name (name),
   key idx_meta_function_info_code (code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_job_info                                         */
/*==============================================================*/
create table meta_job_info
(
   resource_id          int(11) not null,
   name                 varchar(100) default NULL,
   code                 varchar(255) default NULL,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   description          varchar(200) default NULL,
   job_type             varchar(255) default NULL,
   job_state            varchar(255) default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   source_ids           varchar(255) default NULL,
   target_id            int(11) default NULL,
   zpl_catagre_id       varchar(30),
   zpl_notebook_id      varchar(30),
   primary key (resource_id),
   key data_source_id (target_id),
   key meta_data_object_info_resource_id_IDX (resource_id, name),
   key idx_meta_data_object_info_code (code)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_job_object                                       */
/*==============================================================*/
create table meta_job_object
(
   data_object_id       int(11) not null,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   resource_id          int(11) default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   version_code         varchar(255) default NULL,
   version_state        varchar(20) default NULL,
   sql_content          longtext,
   param_json           varchar(100),
   primary key (data_object_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_object_field                                     */
/*==============================================================*/
create table meta_object_field
(
   object_field_id      int(11) not null,
   content_code         varchar(50) default NULL,
   create_person        varchar(50) default NULL,
   create_time          datetime default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   default_value        varchar(200) default NULL,
   description          varchar(500) default NULL,
   field_code           varchar(500) default NULL,
   field_format         varchar(200) default NULL,
   field_length         int(11) default NULL,
   field_name           varchar(100) default NULL,
   field_type           varchar(20) default NULL,
   is_key               bit(1) default NULL,
   object_type          varchar(20) default NULL,
   scale_length         int(11) default NULL,
   primary key (object_field_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table: meta_relation_info                                    */
/*==============================================================*/
create table meta_relation_info
(
   source_id            int(11) not null,
   source_type          varchar(50) default NULL,
   target_id            int(11) not null default NULL,
   target_type          varchar(50) default NULL,
   comment              varchar(500) default NULL,
   primary key (source_id, target_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table meta_relation_info comment '字段和字段的关系，每个表和分类的关系，字段和表的关系';

/*==============================================================*/
/* Table: meta_work_info                                        */
/*==============================================================*/
create table meta_work_info
(
   resource_id          int(11) not null default NULL,
   content_code         varchar(50) default NULL,
   create_time          datetime default NULL,
   update_person        varchar(50) default NULL,
   update_time          datetime default NULL,
   work_state           varchar(20) default NULL,
   zpl_notebook_id      varchar(30),
   primary key (resource_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;

