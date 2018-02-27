package com.gaode.xtd.admin.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gaode.xtd.admin.domain.po.UserPO;
import com.gaode.xtd.admin.domain.query.UserParam;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext-dao.xml" })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {
	
	private static Integer id;
	
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testZDeleteByPrimaryKey() {
		int result = userMapper.deleteByPrimaryKey(id == null ? 0 : id);
		
		Assert.assertEquals(1, result);
		System.out.println("删除数据记录的ID是 : " + id);
	}

	@Test
	public void testInsertSelective() {
		UserPO record = new UserPO();
		record.setUsername("曹启龙");
		record.setAge(27);
		
		int result = userMapper.insertSelective(record);
		Assert.assertEquals(1, result);
		id = record.getId();
		System.out.println("插入数据记录的ID是: " + record.getId());
	}

	@Test
	public void testSelectByPrimaryKey() {
		UserPO result = userMapper.selectByPrimaryKey(id == null ? 0 : id);
		
		Assert.assertEquals(id, result.getId());
		System.out.println("查询数据记录是 : " + result);
	}

	@Test
	public void testQueryList() {
		UserParam param = new UserParam();
		param.setAge("27");
		List<UserPO> result = userMapper.queryList(param);
		
		if (result != null) {
			System.out.println("查询数据记录总数是 : " + result.size());
			for (UserPO u: result) {
				System.out.println("查询数据记录是 : " + u);
			}
		}
	}
	
	@Test
	public void testUpdateByPrimaryKeySelective() {
		UserPO record = new UserPO();
		record.setId(id == null ? 0 : id);
		record.setUsername("曹启龙");
		record.setAge(27);
		
		int result = userMapper.updateByPrimaryKeySelective(record);
		Assert.assertEquals(1, result);
		System.out.println("更新的数据记录ID是: " + id);
	}
}
