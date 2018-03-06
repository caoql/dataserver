package com.gaode.xtd.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

import com.gaode.xtd.common.SystemConstant;

/**
 * 数据源管理
 * @author andyc
 * 2018-3-5
 */
public class DataSourceManger {
	private static final Logger log = Logger.getLogger(DataSourceManger.class);
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		// java类路径路径
		InputStream in = DataSourceManger.class.getResourceAsStream("/jdbc.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			driver = prop.getProperty("jdbc.driver");
			url = prop.getProperty("jdbc.url");
			username = prop.getProperty("jdbc.username");
			password = prop.getProperty("jdbc.password");
		} catch (IOException e) {
			log.error("加载配置文件jdbc.properties出错了");
			e.printStackTrace();
		}
	}
	
	// 查询数据库获取数据源配置
	public static Map<Object, Object> getDatasourcesByJDBC() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			conn = getConn();
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
						+"-url:"+ rs.getString("jdbc_url") + "-username:"+ rs.getString("jdbc_username") + "-password:" + rs.getString("jdbc_password"));
			}
		} catch (SQLException e) {
			log.error("获取多数据源出错了", e);
			e.printStackTrace();
		} catch(Exception e) {
			log.error("获取多数据源出错了", e);
			e.printStackTrace();
		}
		// 5.关闭资源
		close(conn, st, rs);
		// 确保默认数据库存在
		if (!map.containsKey(SystemConstant.DEFAULT_DS)) {
			BasicDataSource dafaultDataSource = new BasicDataSource();
			dafaultDataSource.setDriverClassName(driver);
			dafaultDataSource.setUrl(url);
			dafaultDataSource.setUsername(username);
			dafaultDataSource.setPassword(password);
			map.put(SystemConstant.DEFAULT_DS, dafaultDataSource);
			log.info("<默认添加的数据源名称>：" + SystemConstant.DEFAULT_DS + "-驱动：" + driver 
					+"-url:"+ url + "-username:"+ username + "-password:" + password);
		}
		return map;
	}
	
	// 获取数据库链接
	private static Connection getConn() {
		Connection conn = null;
		try {
			// 1.加载驱动程序
			Class.forName(driver);
			// 2.获得数据库链接
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			log.error("加载配置文件jdbc.properties出错了", e);
			e.printStackTrace();
		} catch (SQLException e) {
			log.error("多数据源获取数据连接出错了", e);
			e.printStackTrace();
		}
		return conn;
	}

	// 关闭资源
	private static void close(Connection conn, Statement st, ResultSet rs) {
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
	}
	
}
