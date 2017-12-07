/*==============================================================*/
/* Table: SYS_ACCOUNT                                           */
/*==============================================================*/
drop table if exists SYS_ACCOUNT;
create table SYS_ACCOUNT
(
  ACCOUNT              varchar(128) not null comment '账号',
  PASSWORD             varchar(128) not null comment '密码',
  USER_ID              varchar(128) not null comment '用户',
  primary key (ACCOUNT, USER_ID)
);
alter table SYS_ACCOUNT comment '登录账户';

/*==============================================================*/
/* Table: SYS_USER                                              */
/*==============================================================*/
drop table if exists SYS_USER;
create table SYS_USER
(
  USER_ID              varchar(128) not null comment '用户编号',
  USER_NAME            varchar(128) not null comment '用户账号',
  REAL_NAME            varchar(256) comment '真实姓名',
  REMARK               varchar(512) comment '备注说明',
  primary key (USER_ID)
);
alter table SYS_USER comment '系统用户';
