package com.gaode.xtd.admin.mapper;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * OperationMapper 的单元测试
 * @author andyc
 * 2018-2-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml" })
public class OperationMapperTest {
	
	@Autowired
	private OperationMapper mapper;

	@Test
	public void testExecuteSql() {
	}

	@Test
	public void testCallProcedure() {
		//String procedure = "call product()";
		//String procedure = "call product2(#{param.age,mode=IN},#{param.username,mode=IN})";
		String procedure = "call test(#{param.username,mode=IN},#{param.age,mode=IN},#{param.pro_out,jdbcType=INTEGER,mode=OUT})";
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("age", 41);
		map.put("username", "CAOQL");
		map.put("pro_out", null);
		List<LinkedHashMap<String, Object>>  result = mapper.callProcedure(procedure, map);
		System.out.println(result);
		//存储过程的返回值不用新的map接受，值回传在参数里面的map里面
		System.out.println(map.get("pro_out"));
	}

}
