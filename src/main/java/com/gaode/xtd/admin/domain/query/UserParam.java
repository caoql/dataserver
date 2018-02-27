package com.gaode.xtd.admin.domain.query;

import java.io.Serializable;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * User 查询的数据
 * @author andyc
 * 2018-2-16
 */
public class UserParam implements Serializable {
	
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 4876306937475742881L;

	private String id;

    private String username;

    private String age;

	@Override
	public String toString() {
		return ObjReflect.toString(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age == null ? null : age.trim();
	}
}