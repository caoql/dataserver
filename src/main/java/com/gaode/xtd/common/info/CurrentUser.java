package com.gaode.xtd.common.info;

import java.io.Serializable;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * 当前的用户信息
 * @author andyc
 * 2018-2-28
 */
public class CurrentUser implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -3310605738519429313L;
	// 当前用户名
	private String username;
	
	@Override
	public String toString() {
		return ObjReflect.toString(this);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
