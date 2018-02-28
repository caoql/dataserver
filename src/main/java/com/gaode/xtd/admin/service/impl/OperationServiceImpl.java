package com.gaode.xtd.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.domain.vo.OperationVO;
import com.gaode.xtd.admin.mapper.OperationMapper;
import com.gaode.xtd.admin.service.OperationService;
import com.gaode.xtd.common.SystemConstant;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.exception.ServiceException;
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

	/**
	 * 新增
	 */
	@Override
	public ResponseInfo insertSelective(OperationVO vo) {
		if (vo == null) {
			throw new ServiceException("新增的内容不能为空");
		}
		if (StringUtils.isBlank(vo.getOperName())) {
			throw new ServiceException("新增内容的<操作名称>不能为空");
		}
		// 检查operName是否重复
		OperationParam param = new OperationParam();
		param.setOperName(vo.getOperName());
		List<OperationPO> list = operationMapper.queryList(param);
		if (list != null && list.size() > 0) {
			throw new ServiceException("文本名字(oper_name):" + vo.getOperName() + "已经存在，不能重复");
		}
		ResponseInfo info = new ResponseInfo();
		OperationPO record = vo.toPO();
		// 跟踪信息
		record.setCreateTime(new Date());
		
		int result = operationMapper.insertSelective(record);
		if (result != 1) {
			info.code = ErrorCodeEnum.DB_INSERT_ERROR.getCode();
			info.msg = ErrorCodeEnum.DB_INSERT_ERROR.getMsg();
		}
		return info;
	}

	/**
	 * 修改
	 */
	@Override
	public ResponseInfo updateSelective(OperationVO vo) {
		if (vo == null) {
			throw new ServiceException("修改的内容不能为空");
		}
		if (vo.getId() == null) {
			throw new ServiceException("修改内容的ID不能为空");
		}
		if (StringUtils.isBlank(vo.getOperName())) {
			throw new ServiceException("修改内容的<操作名称>不能为空");
		}
		// 还要校验operName是否重复
		OperationParam param = new OperationParam();
		param.setOperName(vo.getOperName());
		List<OperationPO> list = operationMapper.queryList(param);
		if (list != null && list.size() > 0) {
			for (OperationPO po : list) {
				// 查询出所有这个operName名字的，只要他们的ID有一个和编辑数据的ID不同，就认为已经存在这个operName
				if (!Objects.equals(po.getId(), vo.getId())) {
					throw new ServiceException("文本名字(oper_name):" + vo.getOperName() + "已经存在，不能重复");
				}
			}
		}
		ResponseInfo info = new ResponseInfo();
		OperationPO record = vo.toPO();
		// 跟踪信息
		record.setUpdateTime(new Date());
		
		int result = operationMapper.updateByPrimaryKeySelective(record);
		if (result != 1) {
			info.code = ErrorCodeEnum.DB_UPDATE_ERROR.getCode();
			info.msg = ErrorCodeEnum.DB_UPDATE_ERROR.getMsg();
		}
		return info;
	}

	/**
	 * 删除
	 */
	@Override
	public ResponseInfo deleteByPrimaryKey(Integer id) {
		if (id == null) {
			throw new ServiceException("删除ID不能为空");
		}
		OperationPO po = operationMapper.selectByPrimaryKey(id);
		if (po == null) {
			throw new ServiceException("ID为:" + id + "的数据记录不存在,不能删除");
		}
		ResponseInfo info = new ResponseInfo();
		int result = operationMapper.deleteByPrimaryKey(id);
		if (result != 1) {
			info.code = ErrorCodeEnum.DB_DELETE_ERROR.getCode();
			info.msg = ErrorCodeEnum.DB_DELETE_ERROR.getMsg();
		}
		return info;
	}

}
