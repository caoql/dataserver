package com.gaode.xtd.common.config;

import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
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
		Map<Object, Object> map = DataSourceManger.getDatasourcesByJDBC();
		if (!map.containsKey("dafaultDataSource")) {
			BasicDataSource dafaultDataSource = new BasicDataSource();
			dafaultDataSource.setDriverClassName("com.mysql.jdbc.Driver");
			dafaultDataSource.setUrl("jdbc:mysql://127.0.0.1:3306/dataserver");
			dafaultDataSource.setUsername("root");
			dafaultDataSource.setPassword("root");
			map.put("dafaultDataSource", dafaultDataSource);
		}
		super.setTargetDataSources(map);
	}
}
