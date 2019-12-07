孟凡龙 5-21 上午10:41:22
create table fportal_t_message
(
   id_                VARCHAR2(64),
   rev_               NUMBER(1)            default 1,
   app_id_            VARCHAR2(64),
   name_              VARCHAR2(255),
   type_              NUMBER(1),
   content_           CLOB,
   create_user_id_    VARCHAR2(64),
   is_view_           NUMBER(1)            default 0,
   view_user_id_      VARCHAR2(64),
   create_time_       TIMESTAMP(6)         default SYSDATE,
   candidate_user_ids_ CLOB,
   candidate_group_ids_ CLOB
);

comment on table fportal_t_message is
'门户消息表：登录后在门户页面采用弹出或者其他方式展现信息，消息展现需有权限控制，不是所有人都能看到。 消息点击查看后不再提醒，如果未点击，只提醒一段时间，具体时间段可以按天设置。消息的来源是业务系统。';

comment on column fportal_t_message.is_view_ is
'未看0、已看1';

孟凡龙 5-21 上午10:41:30
消息表创建脚本

孟凡龙 5-23 下午05:23:33
https://gitee.com/zhengea/springboot-ueditor.git

孟凡龙 5-23 下午05:23:49
http://localhost:8080/ueditor/index.html

孟凡龙 5-23 下午05:28:06
https://blog.csdn.net/qq_38091831/article/details/82936771

孟凡龙 5-24 上午11:31:37
drop table hq_portal_notic cascade constraints;

/*==============================================================*/
/* Table: hq_portal_notic                                     */
/*==============================================================*/
create table hq_portal_notic
(
   id                 VARCHAR2(64),
   title              VARCHAR2(255),
   createuserid       VARCHAR2(64),
   province           VARCHAR2(64),
   appid              VARCHAR2(64),
   fileserverid       VARCHAR2(64),
   type               VARCHAR2(64),
   subtype            VARCHAR2(64),
   createtime         TIMESTAMP(6),
   updatetime         TIMESTAMP(6),
   publishtime        TIMESTAMP(6),
   state              CHAR(2),
   publishlocation    CHAR(6),
   content            CLOB,
   orderid            NUMBER,
   views              NUMBER,
   downloads          NUMBER,
   remark             VARCHAR2(1000)
);

comment on table hq_portal_notic is
'通知公告、帮助文档表';

comment on column hq_portal_notic.province is
'创建用户区划';

comment on column hq_portal_notic.fileserverid is
'关联文件服务器ID';

comment on column hq_portal_notic.type is
'类型';

comment on column hq_portal_notic.subtype is
'子类型';

comment on column hq_portal_notic.publishtime is
'发布时间';

comment on column hq_portal_notic.state is
'0：定时发布，1：已发布，2：已下架，3：已删除，4：草稿';

comment on column hq_portal_notic.publishlocation is
'1、登录页 2、首页 3、业务系统';

孟凡龙 5-24 上午11:32:24
================================

孟凡龙 5-24 上午11:32:25
drop table hq_protal_bytearray cascade constraints;

/*==============================================================*/
/* Table: hq_protal_bytearray                                 */
/*==============================================================*/
create table hq_protal_bytearray
(
   id                 VARCHAR2(64),
   bytes              BLOB,
   belongid           VARCHAR2(64),
   belongtype         VARCHAR2(64),
   time               TIMESTAMP(6)
);

comment on table hq_protal_bytearray is
'资源表';