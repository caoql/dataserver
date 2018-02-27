package com.gaode.xtd.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaode.xtd.admin.domain.query.UserParam;
import com.gaode.xtd.admin.mapper.UserMapper;
import com.gaode.xtd.admin.service.UserService;
import com.gaode.xtd.common.info.ResponseInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	public ResponseInfo queryList(UserParam param) {
		ResponseInfo info = new ResponseInfo();
		info.data = userMapper.queryList(param);
		return info;
	}

}
