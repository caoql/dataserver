package com.gaode.xtd.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaode.xtd.admin.domain.po.OperationPO;
import com.gaode.xtd.admin.domain.po.QueryparameterPO;
import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.domain.vo.OperationVO;
import com.gaode.xtd.admin.domain.vo.QueryparameterVO;
import com.gaode.xtd.admin.mapper.DatasourceconfigMapper;
import com.gaode.xtd.admin.mapper.OperationMapper;
import com.gaode.xtd.admin.mapper.QueryparameterMapper;
import com.gaode.xtd.admin.service.OperationService;
import com.gaode.xtd.common.SystemConstant;
import com.gaode.xtd.common.config.DataSourceContextHolder;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.exception.ServiceException;
import com.gaode.xtd.common.info.ResponseInfo;

/**
 * 操作Service
 * @author andyc
 * 2018-2-26
 * @modify 2018-3-6
 */
@Service
public class OperationServiceImpl implements OperationService {

	// 注入操作Mapper
	@Autowired
	private OperationMapper operationMapper;
	
	// 注入参数Mapper
	@Autowired
	private QueryparameterMapper queryparameterMapper;
	
	// 注入数据源配置Mapper
	@Autowired
	private DatasourceconfigMapper datasourceconfigMapper;
	
	// 数据列表展示
	@Override
	public ResponseInfo list(OperationParam param) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		ResponseInfo info = new ResponseInfo();
		List<OperationPO> result = operationMapper.queryList(param);
		info.data = result;
		return info;
	}
	
	// 超级接口-数据过滤查询,不能加事务，加锁，否则数据源切换不过去
	@Override
	public ResponseInfo search(OperationParam param) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS);
		// 校验重要信息operName字段
		if (param ==  null || StringUtils.isBlank(param.getOperName())) {
			throw new ServiceException(ErrorCodeEnum.CALL_VALIDATE_ERROR);
		}
		ResponseInfo info = new ResponseInfo();
		List<OperationPO> result = operationMapper.queryList(param);
		if (result != null && result.size() > 0) {
			// 判断查询出来的第一条记录数据，如果有多条的话
			OperationPO po = result.get(0);
			if (po != null && StringUtils.isNotBlank(po.getText())) {
				Map<String, Object> paramMap = param.getQuery();
				// 传入的查询参数不返回
				Map<String, Object> temp = new HashMap<String, Object>();
				if (paramMap != null) {
					for (Entry<String, Object> entry: paramMap.entrySet()) {
						String key = entry.getKey();
						Object value = entry.getValue();
						temp.put(key, value);
					}
				}
				// 切换数据源进行执行
				if (!SystemConstant.DEFAULT_DS.equals(po.getDatasourceName())) {
					DataSourceContextHolder.setDBType(po.getDatasourceName()); 
				}
				// 1.SQL
				if (SystemConstant.OPER_TYPE_A.equals(po.getOperType())) {
					info.data = operationMapper.executeSql(po.getText(), paramMap);
				} else if (SystemConstant.OPER_TYPE_B.equals(po.getOperType())) {// 2.储存过程
					// 看看存储过程调用是否有返回结果,存储过程的返回值不用新的map接受，值回传在参数里面的map里面
					info.data = operationMapper.callProcedure(po.getText(), paramMap);
					if (SystemConstant.IS_YES.equals(po.getIsReturn())) {
						for (Entry<String, Object> entry: temp.entrySet()) {
							String key = entry.getKey();
							Object value = entry.getValue();
							// 按key-value的方式去删除，可以避免存储过程INOUT的问题
							paramMap.remove(key, value);
						}
					} 
					info.out = paramMap;
				} else {// 3.其他带扩展...
					
				}
			}
		}
		return info;
	}

	/**
	 * 新增
	 */
	@Override
	@Transactional
	public ResponseInfo insertSelective(OperationVO vo) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		validate(vo, SystemConstant.MODEL_ADD);
		ResponseInfo info = new ResponseInfo();
		OperationPO record = vo.toPO();
		// 跟踪信息
		record.setCreateTime(new Date());
		int result = operationMapper.insertSelective(record);
		if (result != 1) {
			throw new ServiceException(ErrorCodeEnum.DB_INSERT_ERROR);
		}
		
		List<QueryparameterPO> polist = vo.toQueryparameterPO();
		for (QueryparameterPO queryPO: polist) {
			queryPO.setMid(record.getId());// 关联操作表的id
			int r = queryparameterMapper.insertSelective(queryPO);
			if (r != 1) {
				throw new ServiceException(ErrorCodeEnum.DB_INSERT_ERROR);
			}
		}
		
		return info;
	}

	// 新增和修改校验
	private void validate(OperationVO vo,String model) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		if (vo == null) {
			throw new ServiceException("数据服务接口配置不能为空");
		}
		if (SystemConstant.MODEL_UPDATE.equals(model) && vo.getId() == null) {
			throw new ServiceException("ID不能为空");
		}
		if (StringUtils.isBlank(vo.getOperName())) {
			throw new ServiceException("<操作名称>不能为空");
		}
		// 检查operName是否重复
		OperationParam param = new OperationParam();
		param.setOperName(vo.getOperName());
		List<OperationPO> list = operationMapper.queryList(param);
		if (list != null && list.size() > 0) {
			if (SystemConstant.MODEL_ADD.equals(model)) {
				throw new ServiceException("<操作名称>(oper_name):" + vo.getOperName() + "已经存在，不能重复");
			} else if (SystemConstant.MODEL_UPDATE.equals(model)) {
				for (OperationPO po : list) {
					// 查询出所有这个operName名字的，只要他们的ID有一个和编辑数据的ID不同，就认为已经存在这个operName
					if (!Objects.equals(po.getId(), vo.getId())) {
						throw new ServiceException("<操作名称>(oper_name):" + vo.getOperName() + "已经存在，不能重复");
					}
				}
			}
		}
		String operType = vo.getOperType();
		if (StringUtils.isBlank(operType)) {
			throw new ServiceException("<语句类型>不能为空");
		}
		if (StringUtils.isBlank(vo.getSqltemplate())) {
			throw new ServiceException("<模板>不能为空");
		}
		if (vo.getDatasourceId() == null || StringUtils.isBlank(vo.getDatasourceName())) {
			throw new ServiceException("<数据源>不能为空");
		}
		String sqltemplate = vo.getSqltemplate();
		// 默认没有返回值
		String isReturn = SystemConstant.IS_NOT;
		List<QueryparameterVO> plist = vo.getQueryParamList();
		if (plist != null) {
			for (QueryparameterVO parameterVO: plist) {
				// 参数名称
				String pname = parameterVO.getPname();
				if (StringUtils.isBlank(pname)) {
					throw new ServiceException("<参数名称>不能为空");
				}
				// SQL语句
				if (SystemConstant.OPER_TYPE_A.equals(operType)) {
					pname = "#{param." + pname.substring(1) + "}";
				} else if (SystemConstant.OPER_TYPE_B.equals(operType)) {// 储存过程
					// 参数类型
					String ptype =parameterVO.getPtype();
					// 输入/输出
					String pdirection = parameterVO.getPdirection();
					if (StringUtils.isBlank(ptype)) {
						throw new ServiceException(pname + "的<参数类型>不能为空");
					}
					if (StringUtils.isBlank(pdirection)) {
						throw new ServiceException(pname + "的<输入/输出>不能为空");
					}
					if (SystemConstant.P_DIRECTION_OUT.equals(pdirection)) {// 存储过程是否有返回值
						isReturn = SystemConstant.IS_YES;
					}
					pname = "#{param." + pname.substring(1) + ",jdbcType=" + ptype + ",mode=" + pdirection + "}";
				}
				sqltemplate = sqltemplate.replace(parameterVO.getPname(), pname);
			}
		}
		vo.setIsReturn(isReturn);
		vo.setText(sqltemplate);
	}
	
	/**
	 * 修改
	 */
	@Override
	@Transactional
	public ResponseInfo updateSelective(OperationVO vo) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		// 校验
		validate(vo, SystemConstant.MODEL_UPDATE);
		ResponseInfo info = new ResponseInfo();
		OperationPO record = vo.toPO();
		// 跟踪信息
		record.setUpdateTime(new Date());
		int result = operationMapper.updateByPrimaryKeySelective(record);
		if (result != 1) {
			throw new ServiceException(ErrorCodeEnum.DB_UPDATE_ERROR);
		}
		
		// 参数配置先删后插入
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", record.getId());
		queryparameterMapper.deleteByMap(map);
		List<QueryparameterPO> polist = vo.toQueryparameterPO();
		for (QueryparameterPO queryPO: polist) {
			queryPO.setMid(record.getId());// 关联操作表的id
			int r = queryparameterMapper.insertSelective(queryPO);
			if (r != 1) {
				throw new ServiceException(ErrorCodeEnum.DB_INSERT_ERROR);
			}
		}
		
		return info;
	}

	/**
	 * 删除
	 */
	@Override
	@Transactional
	public ResponseInfo deleteByPrimaryKey(Integer id) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		if (id == null) {
			throw new ServiceException("删除ID不能为空");
		}
		// 先删除关联表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mid", id);
		queryparameterMapper.deleteByMap(map);
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

	/**
	 * 查看
	 */
	@Override
	public ResponseInfo selectByPrimaryKey(Integer id) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		if (id == null) {
			throw new ServiceException("查看ID不能为空");
		}
		ResponseInfo info = new ResponseInfo();
		info.data = operationMapper.selectOperAndParamByKey(id);
		return info;
	}

}
