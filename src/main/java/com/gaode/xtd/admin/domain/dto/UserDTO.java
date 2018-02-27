package com.gaode.xtd.admin.domain.dto;

import java.io.Serializable;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * User 传输的数据
 * @author andyc
 * 2018-2-16
 */
public class UserDTO implements Serializable {
	
    /**
	 * 序列号
	 */
	private static final long serialVersionUID = 953819764600735190L;

	private Integer id;

    private String username;

    private Integer age;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}