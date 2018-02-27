package com.gaode.xtd.admin.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.mapper.OperationMapper;
import com.gaode.xtd.admin.service.OperationService;
import com.gaode.xtd.common.SystemConstant;
import com.gaode.xtd.common.info.ResponseInfo;

@Service
public class OperationServiceImpl implements OperationService {

	@Autowired
	private OperationMapper operationMapper;
	
	public ResponseInfo queryList(OperationParam param) {
		ResponseInfo info = new ResponseInfo();
		List<OperationPO> result = operationMapper.queryList(param);
		if (result != null && result.size() > 0) {
			OperationPO po = result.get(0);
			// 判断查询出来的第一条记录数据，如果有多条的话
			if (po != null && StringUtils.isNotBlank(po.getText())) {
				Map<String, Object> paramMap = param == null ? null: param.getQuery();
				// 传入的查询参数不返回
				Map<String, Object> temp = new HashMap<String, Object>();
				if (paramMap != null) {
					for (Entry<String, Object> entry: paramMap.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						temp.put(key, value);
					}
				}
				// 1.SQL
				if (SystemConstant.OPER_TYPE_A.equals(po.getOperType())) {
					info.data = operationMapper.executeSql(po.getText(), paramMap);
				} else if (SystemConstant.OPER_TYPE_B.equals(po.getOperType())) {// 2.储存过程
					// 看看存储过程调用是否有返回结果,存储过程的返回值不用新的map接受，值回传在参数里面的map里面
					List<LinkedHashMap<String, Object>> list = operationMapper.callProcedure(po.getText(), paramMap);
					Map<String, Object> resultData = new HashMap<String, Object>();
					resultData.put("data", list);
					if (SystemConstant.IS_YES.equals(po.getIsReturn())) {
						for (Entry<String, Object> entry: temp.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							// 按key-value的方式去删除，可以避免存储过程INOUT的问题
							paramMap.remove(key, value);
						}
						resultData.put("prout", paramMap);
					} 
					info.data = resultData;
				} else {// 3.其他带扩展
					
				}
			}
		}
		return info;
	}

}
