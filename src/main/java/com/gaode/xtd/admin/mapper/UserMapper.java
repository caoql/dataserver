package com.gaode.xtd.admin.mapper;

import java.util.List;

import com.gaode.xtd.admin.domain.po.UserPO;
import com.gaode.xtd.admin.domain.query.UserParam;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPO record);
    
    List<UserPO> queryList(UserParam param);
} 