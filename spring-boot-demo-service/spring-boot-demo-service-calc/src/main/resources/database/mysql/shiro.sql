
/*TABLE STRUCTURE FOR TABLE SYS_USER' */

DROP TABLE IF EXISTS SYS_USER;

CREATE TABLE SYS_USER (
    UID              BIGINT(20)   NOT NULL,
    USERNAME         VARCHAR(100) DEFAULT NULL COMMENT '用户昵称',
    PASSWARD         VARCHAR(100) DEFAULT NULL COMMENT '密码',
    EMAIL            VARCHAR(100) DEFAULT NULL COMMENT '邮箱|登录帐号',
    STATUS           BIGINT(1)    DEFAULT '1' COMMENT '1:有效，0:禁止登录',
    LAST_LOGIN_TIME  DATETIME     DEFAULT NULL COMMENT '最后登录时间',
    CREATE_TIME      DATETIME     DEFAULT NULL COMMENT '创建时间',
    UPDATE_TIME      DATETIME     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (UID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*TABLE STRUCTURE FOR TABLE SYS_ROLE' */

DROP TABLE IF EXISTS SYS_ROLE;

CREATE TABLE SYS_ROLE (
    RID              BIGINT(20)   NOT NULL,
    ROLE_NAME        VARCHAR(100) DEFAULT NULL COMMENT '角色名称',
    ROLE_TYPE        VARCHAR(100) DEFAULT NULL COMMENT '角色类型',
    CREATE_TIME      DATETIME     DEFAULT NULL COMMENT '创建时间',
    UPDATE_TIME      DATETIME     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (RID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*TABLE STRUCTURE FOR TABLE SYS_PERMISSION' */

DROP TABLE IF EXISTS SYS_PERMISSION;

CREATE TABLE SYS_PERMISSION (
    PID             BIGINT(20)   NOT NULL,
    PERM_URL        VARCHAR(500) DEFAULT NULL COMMENT '权限地址',
    PERM_DESC       VARCHAR(500) DEFAULT NULL COMMENT '权限描述',
    CREATE_TIME     DATETIME     DEFAULT NULL COMMENT '创建时间',
    UPDATE_TIME     DATETIME     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (PID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


/*TABLE STRUCTURE FOR TABLE SYS_USER_ROLE' */

DROP TABLE IF EXISTS SYS_USER_ROLE;

CREATE TABLE SYS_USER_ROLE (
    UID             BIGINT(20)   DEFAULT NULL COMMENT '用户ID',
    RID             BIGINT(20)   DEFAULT NULL COMMENT '角色ID',
    CREATE_TIME     DATETIME     DEFAULT NULL COMMENT '创建时间',
    UPDATE_TIME     DATETIME     DEFAULT NULL COMMENT '更新时间'
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

/*TABLE STRUCTURE FOR TABLE SYS_ROLE_PERMISSION' */

DROP TABLE IF EXISTS SYS_ROLE_PERMISSION;

CREATE TABLE SYS_ROLE_PERMISSION (
    RID             BIGINT(20)   DEFAULT NULL COMMENT '角色ID',
    PID             BIGINT(20)   DEFAULT NULL COMMENT '权限ID',
    CREATE_TIME     DATETIME     DEFAULT NULL COMMENT '创建时间',
    UPDATE_TIME     DATETIME     DEFAULT NULL COMMENT '更新时间'
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

