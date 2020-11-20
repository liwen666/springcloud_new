create table interface_analysis_log
(
   id       bigint(20) not null comment '数据源ID',
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
   cron_expression      varchar(100) comment '定时器表达式',
   primary key (data_source_id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT = '数据源信息';
