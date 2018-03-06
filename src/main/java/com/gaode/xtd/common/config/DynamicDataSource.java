package com.gaode.xtd.common.config;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源配置
 * 
 * @author andyc 2018-3-5
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	private static final Logger log = Logger.getLogger(DataSourceContextHolder.class);
	
	@Override
	protected Object determineCurrentLookupKey() {
		log.debug("数据源为{}: " + DataSourceContextHolder.getDBType());
		return DataSourceContextHolder.getDBType();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		// 自己设置多数据源
		Map<Object, Object> map = DataSourceManger.getDatasourcesByJDBC();
		super.setTargetDataSources(map);
	}
	
}
