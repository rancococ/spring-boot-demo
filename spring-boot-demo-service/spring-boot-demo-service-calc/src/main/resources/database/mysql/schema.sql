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

