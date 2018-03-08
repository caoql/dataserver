package com.gaode.xtd.admin.service;

import com.gaode.xtd.admin.domain.query.DatasourceconfigParam;
import com.gaode.xtd.admin.domain.vo.DatasourceconfigVO;
import com.gaode.xtd.common.info.ResponseInfo;

public interface DatasourceService {

	ResponseInfo insertSelective(DatasourceconfigVO vo);

	ResponseInfo updateSelective(DatasourceconfigVO vo);

	ResponseInfo selectByPrimaryKey(Integer id);

	ResponseInfo list(DatasourceconfigParam param);

}
