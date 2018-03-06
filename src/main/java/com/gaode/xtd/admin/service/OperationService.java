package com.gaode.xtd.admin.service;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.domain.vo.OperationVO;
import com.gaode.xtd.common.info.ResponseInfo;

public interface OperationService {
	public ResponseInfo insertSelective(OperationVO vo);
	
	public ResponseInfo updateSelective(OperationVO vo);
	
	public ResponseInfo deleteByPrimaryKey(Integer id);

	public ResponseInfo selectByPrimaryKey(Integer id);
	
	// 超级接口-数据过滤查询
	public ResponseInfo search(OperationParam param);
	
	// 数据列表展示
	public ResponseInfo list(OperationParam param);
}
