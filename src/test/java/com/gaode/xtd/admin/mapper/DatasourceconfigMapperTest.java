package com.gaode.xtd.admin.mapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaode.xtd.admin.domain.po.DatasourceconfigPO;
import com.gaode.xtd.common.SystemConstant;
import com.gaode.xtd.common.config.DataSourceContextHolder;
import com.gaode.xtd.common.config.DataSourceManger;

/**
 * DatasourceconfigMapper 的单元测试
 * @author andyc
 * 2018-3-5
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml" })
public class DatasourceconfigMapperTest {

	@Autowired
	private DatasourceconfigMapper mapper;
	
	@Test
	public void testDeleteByPrimaryKey() {
	}

	@Test
	public void testInsertSelective() {
		DatasourceconfigPO record = new DatasourceconfigPO();
		record.setCreateTime(new Date());
		record.setIsUse(SystemConstant.IS_YES);
		record.setName("datasource1");
		record.setJdbcDriver("com.mysql.jdbc.Driver");
		record.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/base");
		record.setJdbcUsername("root");
		record.setJdbcPassword("root");
		int result = mapper.insertSelective(record);
		Assert.assertEquals(1, result);
		System.out.println("返回的助主键是: " + record.getId());
	}

	@Test
	public void testSelectByPrimaryKey() {
		DatasourceconfigPO po = mapper.selectByPrimaryKey(1);
		System.out.println(po);
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
	}
	
	@Test
	public void testDatasource() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("E:\\temp_wk\\dataserver\\src\\main\\resources\\jdbc.properties"));
		} catch (IOException e) {
			System.out.println("文件找不到111！");
			e.printStackTrace();
		}
		/*DataSourceManger ds = new DataSourceManger();
		ds.setDatasources();
		DataSourceContextHolder.setDBType("datasource1");*/
		DataSourceContextHolder.setDBType("datasource1");
		DatasourceconfigPO po = mapper.selectByPrimaryKey(2);
		if (po != null) {
			prop.setProperty(po.getName() + "." + "driver", po.getJdbcDriver());
			prop.setProperty(po.getName() + "." + "url", po.getJdbcUrl());
			prop.setProperty(po.getName() + "." + "username", po.getJdbcUsername());
			prop.setProperty(po.getName() + "." + "password", po.getJdbcPassword());
		}
		Set<String> set = prop.stringPropertyNames();
		for (String s : set) {
			System.out.println(s + "=" + prop.getProperty(s));
		}
	}

}
