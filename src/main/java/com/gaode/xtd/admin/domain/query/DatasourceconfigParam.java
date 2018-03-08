package com.gaode.xtd.admin.domain.query;

import java.io.Serializable;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * 数据源列表查询对应的参数
 * @author andyc
 * 2018-3-8
 */
public class DatasourceconfigParam implements Serializable {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 872207923970560307L;

    private String name;

    private String jdbcDriver;

    private String jdbcUrl;

    private String jdbcUsername;

    private String jdbcPassword;

    private String isUse;

    @Override
	public String toString() {
		return ObjReflect.toString(this);
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
}