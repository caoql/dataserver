package com.gaode.xtd.admin.domain.query;

import java.io.Serializable;
import java.util.Map;

import com.gaode.xtd.common.reflect.ObjReflect;

/**
 * operation 查询数据封装
 * @author 
 * andyc 2018-2-16
 */
public class OperationParam implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 3312204907463566518L;
	
	// 文本名字
	private String operName;

	// 查询用户表的一些跟踪条件封装
	private Map<String, Object> query;
	
	@Override
	public String toString() {
		return ObjReflect.toString(this);
	}

	public String getOperName() {
		return operName;
	}

	public void setOperName(String operName) {
		this.operName = operName == null ? null : operName.trim();
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}
}