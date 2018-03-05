package com.gaode.xtd.admin.mapper;

import java.util.Map;

import com.gaode.xtd.admin.domain.po.QueryparameterPO;

public interface QueryparameterMapper {
    int deleteByPrimaryKey(Integer pid);

    int insertSelective(QueryparameterPO record);

    QueryparameterPO selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(QueryparameterPO record);

	int deleteByMap(Map<String, Object> map);
}