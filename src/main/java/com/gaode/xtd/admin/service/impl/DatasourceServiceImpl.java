package com.gaode.xtd.admin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaode.xtd.admin.domain.po.DatasourceconfigPO;
import com.gaode.xtd.admin.domain.query.DatasourceconfigParam;
import com.gaode.xtd.admin.domain.vo.DatasourceconfigVO;
import com.gaode.xtd.admin.mapper.DatasourceconfigMapper;
import com.gaode.xtd.admin.service.DatasourceService;
import com.gaode.xtd.common.SystemConstant;
import com.gaode.xtd.common.config.DataSourceContextHolder;
import com.gaode.xtd.common.config.DynamicDataSource;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.exception.ServiceException;
import com.gaode.xtd.common.info.ResponseInfo;

/**
 * 数据源Service
 * @author andyc
 * 2018-3-8
 */
@Service
public class DatasourceServiceImpl implements DatasourceService {

	// 注入数据源Mapper
	@Autowired
	private DatasourceconfigMapper datasourceconfigMapper;
	
	// 注入动态数据源来实时更新数据源
	@Autowired
	private DynamicDataSource dds;
		
	// 新增
	@Override
	@Transactional
	public ResponseInfo insertSelective(DatasourceconfigVO vo) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		// 校验
		validate(vo, SystemConstant.MODEL_ADD);
		ResponseInfo info = new ResponseInfo();
		DatasourceconfigPO record = vo.toPO();
		// 跟踪信息
		record.setCreateTime(new Date());
		int result = datasourceconfigMapper.insertSelective(record);
		if (result != 1) {
			throw new ServiceException(ErrorCodeEnum.DB_INSERT_ERROR);
		}
		// 系统的动态数据源更新
		dds.setTargetDataSources(null);
		return info;
	}

	// 编辑
	@Override
	@Transactional
	public ResponseInfo updateSelective(DatasourceconfigVO vo) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		// 校验
		validate(vo, SystemConstant.MODEL_UPDATE);
		ResponseInfo info = new ResponseInfo();
		DatasourceconfigPO record = vo.toPO();
		int result = datasourceconfigMapper.updateByPrimaryKeySelective(record);
		if (result != 1) {
			throw new ServiceException(ErrorCodeEnum.DB_UPDATE_ERROR);
		}
		// 系统的动态数据源更新
		dds.setTargetDataSources(null);
		return info;
	}

	// 查看
	@Override
	public ResponseInfo selectByPrimaryKey(Integer id) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		if (id == null) {
			throw new ServiceException("查看ID不能为空");
		}
		ResponseInfo info = new ResponseInfo();
		info.data = datasourceconfigMapper.selectByPrimaryKey(id);
		return info;
	}

	// 列表数据展示
	@Override
	public ResponseInfo list(DatasourceconfigParam param) {
		// 切换数据源进行执行-默认数据源
		DataSourceContextHolder.setDBType(SystemConstant.DEFAULT_DS); 
		ResponseInfo info = new ResponseInfo();
		info.data = datasourceconfigMapper.list(param);
		return info;
	}

	// 新增和修改校验
	private void validate(DatasourceconfigVO vo, String model) {
		if (vo == null) {
			throw new ServiceException("数据源配置数据不能为空");
		}
		if (SystemConstant.MODEL_UPDATE.equals(model) && vo.getId() == null) {
			throw new ServiceException("编辑<ID>不能为空");
		}
		// 检查数据源名称是否存在
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("name", vo.getName());
		List<DatasourceconfigPO> list = datasourceconfigMapper.listAll(paramMap);
		if (list != null && list.size() > 0) {
			if (SystemConstant.MODEL_ADD.equals(model)) {
				throw new ServiceException("<数据源名称>:" + vo.getName() + "已经存在，不能重复");
			} else if (SystemConstant.MODEL_UPDATE.equals(model)) {
				for (DatasourceconfigPO po : list) {
					// 查询出所有这个name名字的，只要他们的ID有一个和编辑数据的ID不同，就认为已经存在这个name
					if (!Objects.equals(po.getId(), vo.getId())) {
						throw new ServiceException("<数据源名称>:" + vo.getName() + "已经存在，不能重复");
					}
				}
			}
		}
	}
}
