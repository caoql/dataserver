package com.gaode.xtd.admin.mapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gaode.xtd.admin.domain.dto.OperationDTO;
import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.admin.domain.query.OperationParam;

public interface OperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(OperationPO record);

    OperationPO selectByPrimaryKey(Integer id);
    
    OperationDTO selectOperAndParamByKey(Integer id);
    
    List<OperationDTO> queryDtos(Map<String, Object> map);

    int updateByPrimaryKeySelective(OperationPO record);
    
    List<OperationPO> queryList(OperationParam param);
    
    // 执行调用SQL
    List<LinkedHashMap<String, Object>> executeSql(@Param("sql")String sql, @Param("param")Map<String, Object> map);
    
    // 执行储存过程
    List<LinkedHashMap<String, Object>> callProcedure(@Param("procedure")String procedure, @Param("param")Map<String, Object> map);
}