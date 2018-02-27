package com.gaode.xtd.admin.service;

import com.gaode.xtd.admin.domain.query.UserParam;
import com.gaode.xtd.common.info.ResponseInfo;

public interface UserService {
	
	public ResponseInfo queryList(UserParam param);
}
