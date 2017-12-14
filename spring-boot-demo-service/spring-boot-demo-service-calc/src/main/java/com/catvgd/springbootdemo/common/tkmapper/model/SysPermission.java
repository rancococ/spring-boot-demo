package com.catvgd.springbootdemo.common.tkmapper.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission implements Serializable {
    @Id
    @Column(name = "PID")
    private Long pid;

    /**
     * 权限地址
     */
    @Column(name = "PERM_URL")
    private String permUrl;

    /**
     * 权限描述
     */
    @Column(name = "PERM_DESC")
    private String permDesc;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    /**
     * @return PID
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * 获取权限地址
     *
     * @return PERM_URL - 权限地址
     */
    public String getPermUrl() {
        return permUrl;
    }

    /**
     * 设置权限地址
     *
     * @param permUrl 权限地址
     */
    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl;
    }

    /**
     * 获取权限描述
     *
     * @return PERM_DESC - 权限描述
     */
    public String getPermDesc() {
        return permDesc;
    }

    /**
     * 设置权限描述
     *
     * @param permDesc 权限描述
     */
    public void setPermDesc(String permDesc) {
        this.permDesc = permDesc;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}