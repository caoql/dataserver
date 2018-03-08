package com.gaode.xtd.admin.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaode.xtd.admin.domain.query.DatasourceconfigParam;
import com.gaode.xtd.admin.domain.vo.DatasourceconfigVO;
import com.gaode.xtd.admin.service.DatasourceService;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.info.ResponseInfo;

/**
 * 数据源控制器
 * @author andyc
 * 2018-3-8
 */
@RestController
@RequestMapping("/admin/datasource")
public class DatasourceController {
	// 日志记录器
	private Logger log = Logger.getLogger(getClass());
	
	// 注入数据源Service
	@Autowired
	private DatasourceService datasourceService;
	
	// 数据列表展示
	@PostMapping("/list")
	public ResponseInfo list(DatasourceconfigParam param) {
		log.debug("查询参数为: " + param);
		return datasourceService.list(param);
	}
		
	// 新增
	@PostMapping("/add")
	public ResponseInfo add(@Validated @RequestBody DatasourceconfigVO vo, BindingResult result){
		ResponseInfo info = null;
		log.debug("新增的内容为: " + vo);
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				log.error("数据源新增接口：" + error.getDefaultMessage());
				sb.append(error.getDefaultMessage() + ",");
			}
			info = new ResponseInfo();
			info.code = ErrorCodeEnum.CALL_VALIDATE_ERROR.getCode();
			info.msg = sb.toString();
		} else {
			info = datasourceService.insertSelective(vo);
		}
		
		return info;
	}
	
	// 修改
	@PostMapping("/update")
	public ResponseInfo update(@Validated @RequestBody DatasourceconfigVO vo, BindingResult result){
		ResponseInfo info = null;
		log.debug("修改的内容为: " + vo);
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				log.error("数据源修改接口：" + error.getDefaultMessage());
				sb.append(error.getDefaultMessage() + ",");
			}
			info = new ResponseInfo();
			info.code = ErrorCodeEnum.CALL_VALIDATE_ERROR.getCode();
			info.msg = sb.toString();
		} else {
			info = datasourceService.updateSelective(vo);
		}
		
		return info;
	}
	
	// 查看
	@GetMapping("/get")
	public ResponseInfo selectByPrimaryKey(Integer id) {
		log.debug("查看的ID为: " + id);
		return datasourceService.selectByPrimaryKey(id);
	}
}
