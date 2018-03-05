package com.gaode.xtd.common.config;

import org.apache.log4j.Logger;

/**
 * 保存当前线程使用的数据源名
 * @author andyc
 * 2018-3-5
 */
public class DataSourceContextHolder {
	private static final Logger log = Logger.getLogger(DataSourceContextHolder.class);
	
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();      
	 
	// 设置数据源名
    public static void setDBType(String dbType) {   
    	log.debug("切换到{}数据源: "+dbType);
        contextHolder.set(dbType);      
    }      
  
    // 获取数据源名
    public static String getDBType() {      
        return ((String) contextHolder.get());      
    }      
  
    // 清除数据源名
    public static void clearDBType() {      
        contextHolder.remove();      
    }      
}
