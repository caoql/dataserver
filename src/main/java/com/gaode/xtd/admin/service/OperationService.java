package com.gaode.xtd.admin.service;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.domain.vo.OperationVO;
import com.gaode.xtd.common.info.ResponseInfo;

public interface OperationService {
	public ResponseInfo queryList(OperationParam param);
	
	public ResponseInfo insertSelective(OperationVO vo);
	
	public ResponseInfo updateSelective(OperationVO vo);
	
	public ResponseInfo deleteByPrimaryKey(Integer id);

	public ResponseInfo list(OperationParam param);
	
	public ResponseInfo selectByPrimaryKey(Integer id);
}
