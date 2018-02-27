package com.gaode.xtd.admin.service;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.common.info.ResponseInfo;

public interface OperationService {
	public ResponseInfo queryList(OperationParam param);
}
