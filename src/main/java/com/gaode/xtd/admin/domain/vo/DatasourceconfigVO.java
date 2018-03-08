package com.gaode.xtd.admin.domain.vo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gaode.xtd.admin.domain.po.DatasourceconfigPO;
import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * datasourceconfig 表对应的PO
 * @author andyc
 * 2018-3-5
 */
public class DatasourceconfigVO implements Serializable {
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 4701493369675567616L;

	private Integer id;

	// 数据源名称
	@NotNull(message="数据源名称不能为空")
	@Size(max=40, message="数据源名称最多40个字符")
    private String name;

	// 数据库驱动
	@NotNull(message="驱动不能为空")
	@Size(max=60, message="驱动最多60个字符")
    private String jdbcDriver;

	// 连接URL
	@NotNull(message="URL不能为空")
	@Size(max=60, message="URL最多60个字符")
    private String jdbcUrl;

	// 用户名
	@Size(max=60, message="用户名最多60个字符")
    private String jdbcUsername;

    // 密码
	@Size(max=60, message="密码最多60个字符")
    private String jdbcPassword;

    // 是否启用
    private String isUse;

    /**
     * vo转换成PO
     * @return
     */
    public DatasourceconfigPO toPO() {
    	DatasourceconfigPO po = new DatasourceconfigPO();
    	po.setId(id);
    	po.setName(name);
    	po.setJdbcDriver(jdbcDriver);
    	po.setJdbcUrl(jdbcUrl);
    	po.setJdbcUsername(jdbcUsername);
    	po.setJdbcPassword(jdbcPassword);
    	po.setIsUse(isUse);
    	return po;
    }
   
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
}