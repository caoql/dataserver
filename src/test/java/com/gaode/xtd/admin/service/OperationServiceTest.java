package com.gaode.xtd.admin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.common.info.ResponseInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/applicationContext-dao.xml",
		"classpath:spring/applicationContext-common.xml" })
public class OperationServiceTest {

	@Autowired
	private OperationService operationService;
	
	@Test
	public void testQueryList() {
		OperationParam param = new OperationParam();
		param.setOperName("tt");
		ResponseInfo info = operationService.queryList(param);
		System.out.println(info.data);
	}

}
