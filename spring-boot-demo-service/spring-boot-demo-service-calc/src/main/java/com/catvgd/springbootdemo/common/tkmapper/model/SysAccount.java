package com.catvgd.springbootdemo.common.tkmapper.model;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "sys_account")
public class SysAccount implements Serializable {
    /**
     * 账号
     */
    @Id
    @Column(name = "ACCOUNT")
    private String account;

    /**
     * 用户
     */
    @Id
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    private static final long serialVersionUID = 1L;

    /**
     * 获取账号
     *
     * @return ACCOUNT - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户
     *
     * @return USER_ID - 用户
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户
     *
     * @param userId 用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
}