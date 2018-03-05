package com.gaode.xtd.admin.domain.po;

import java.io.Serializable;
import java.util.Date;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * datasourceconfig 表对应的PO
 * @author andyc
 * 2018-3-5
 */
public class DatasourceconfigPO implements Serializable {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = -7060862168131699788L;

	private Integer id;

    private String name;

    private String jdbcDriver;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    private String isUse;

    private Date createTime;

    @Override
	public String toString() {
		return ObjReflect.toString(this);
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver == null ? null : jdbcDriver.trim();
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl == null ? null : jdbcUrl.trim();
    }

    public String getJdbcUsername() {
        return jdbcUsername;
    }

    public void setJdbcUsername(String jdbcUsername) {
        this.jdbcUsername = jdbcUsername == null ? null : jdbcUsername.trim();
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword == null ? null : jdbcPassword.trim();
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}