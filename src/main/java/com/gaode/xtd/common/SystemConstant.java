package com.gaode.xtd.common;

/**
 * 系统的常量定义
 * @author andyc 
 * 2018-2-26
 */
public final class SystemConstant {
	// 存储文本类型,A:sql,B:存储过程
	public static final String OPER_TYPE_A = "A";
	public static final String OPER_TYPE_B = "B";
	
	// 是否的通用定义
	public static final String IS_YES = "1";
	public static final String IS_NOT = "0";
	
	// 存储过程参数方向
	public static final String P_DIRECTION_IN = "IN";
	public static final String P_DIRECTION_OUT = "OUT";
	
	// 模式
	public static final String MODEL_ADD = "ADD";
	public static final String MODEL_UPDATE = "UPDATE";
	
	// 默认数据源-和applicationContext-dao.xml中的默认数据源关联的bean的ID一致
	public static final String DEFAULT_DS = "defaultDataSource";
}
