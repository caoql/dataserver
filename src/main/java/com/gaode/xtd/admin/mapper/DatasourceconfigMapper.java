package com.gaode.xtd.admin.mapper;

import java.util.List;
import java.util.Map;

import com.gaode.xtd.admin.domain.po.DatasourceconfigPO;
import com.gaode.xtd.admin.domain.query.DatasourceconfigParam;

public interface DatasourceconfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(DatasourceconfigPO record);

    DatasourceconfigPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DatasourceconfigPO record);

    List<DatasourceconfigPO> listAll(Map<String, Object> paramMap);

	List<DatasourceconfigPO> list(DatasourceconfigParam param);
}