package com.gaode.xtd.admin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.common.config.DynamicDataSource;
import com.gaode.xtd.common.info.ResponseInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/applicationContext-dao.xml",
		"classpath:spring/applicationContext-common.xml" })
public class OperationServiceTest {

	@Autowired
	private OperationService operationService;
	
	// 注入动态数据源来做测试
	@Autowired
	private DynamicDataSource dds;
	
	@Test
	public void testSearch() {
		// 可以自己在程序执行的时候再去动态加载一次数据源,但是每次执行接口都要调用，性能较低
	/*	System.out.println("》》》》》》》》》》》》》》》再去动态加载一次数据源");
		dds.setTargetDataSources(null);*/
		OperationParam param = new OperationParam();
		param.setOperName("tt");
		ResponseInfo info = operationService.search(param);
		System.out.println(info.data);
	}

}
