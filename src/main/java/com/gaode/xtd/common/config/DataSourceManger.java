package com.gaode.xtd.common.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

/**
 * 数据源管理
 * @author andyc
 * 2018-3-5
 */
public class DataSourceManger {
	private static final Logger log = Logger.getLogger(DataSourceManger.class);
	private static String url = "jdbc:mysql://127.0.0.1:3306/dataserver?useUnicode=true&amp;characterEncoding=utf-8";
	private static String username = "root";
	private static String password = "root";
	
	// 查询数据库获取数据源配置
	public static Map<Object, Object> getDatasourcesByJDBC() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			// 1.加载驱动程序
			Class.forName("com.mysql.jdbc.Driver");
			// 2.获得数据库链接
			conn = DriverManager.getConnection(url, username, password);
			// 3.通过数据库的连接操作数据库，实现增删改查（使用Statement类）
			st = conn.createStatement();
			String sql = "select * from datasourceconfig where is_use = '1'";
			rs = st.executeQuery(sql);
			// 4.处理数据库的返回结果(使用ResultSet类)
			while (rs.next()) {
				BasicDataSource dataSource = new BasicDataSource();
				dataSource.setDriverClassName(rs.getString("jdbc_driver"));
				dataSource.setUrl(rs.getString("jdbc_url"));
				dataSource.setUsername(rs.getString("jdbc_username"));
				dataSource.setPassword(rs.getString("jdbc_password"));
				map.put(rs.getString("name"), dataSource);
				log.info("数据源名称：" + rs.getString("name") + "-驱动：" + rs.getString("jdbc_driver") 
						+"-url:"+ rs.getString("jdbc_url") + "username:-"+ rs.getString("jdbc_username") + "-password:" + rs.getString("jdbc_password"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 5.关闭资源
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
