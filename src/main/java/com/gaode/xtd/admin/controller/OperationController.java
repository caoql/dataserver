package com.gaode.xtd.admin.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.domain.vo.OperationVO;
import com.gaode.xtd.admin.service.OperationService;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.info.ResponseInfo;
import com.gaode.xtd.common.util.UriUtils;

/**
 * 操作控制器
 * @author andyc 
 * 2018-2-26
 * @modify 2018-3-7
 */
@RestController
@RequestMapping("/admin/operation")
public class OperationController {
	// 日志记录器
	private Logger log = Logger.getLogger(getClass());
	
	// 注入操作处理的Service
	@Autowired
	private OperationService operationService;

	// 超级接口-数据过滤查询，支持GET/POST...
	@RequestMapping("/search")
	public ResponseInfo search(@Validated OperationParam param, BindingResult result, HttpServletRequest request) {
		ResponseInfo info = null;
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				log.error("数据服务接口查询：" + error.getDefaultMessage());
				sb.append(error.getDefaultMessage() + ";");
			}
			info = new ResponseInfo();
			info.code = ErrorCodeEnum.CALL_VALIDATE_ERROR.getCode();
			info.msg = sb.toString();
		} else {
			// 组装一下查询参数，为后面拼接sql做准备
			Map<String, Object> queryMap = UriUtils.analyzeQuery(request);
			param.setQuery(queryMap);
			info = operationService.search(param);
		}
		
		return info;
	}
	
	// 数据列表展示
	@PostMapping("/list")
	public ResponseInfo list(OperationParam param) {
		return operationService.list(param);
	}
	
	// 新增
	// @Validated作用就是将pojo内的注解数据校验规则(@NotNull等)生效，如果没有该注解的声明，pojo内有注解数据校验规则也不会生效
	// BindingResult对象用来获取校验失败的信息(@NotNull中的message)，与@Validated注解必须配对使用，一前一后
	@PostMapping("/add")
	public ResponseInfo add(@RequestBody @Validated OperationVO vo, BindingResult result) {
		ResponseInfo info = null;
		log.debug("新增的内容为: " + vo);
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				log.error("数据服务接口新增：" + error.getDefaultMessage());
				sb.append(error.getDefaultMessage() + ",");
			}
			info = new ResponseInfo();
			info.code = ErrorCodeEnum.CALL_VALIDATE_ERROR.getCode();
			info.msg = sb.toString();
		} else {
			info = operationService.insertSelective(vo);
		}
		
		return info;
	}
	
	// 更新
	@PostMapping("/update")
	public ResponseInfo update(@RequestBody @Validated OperationVO vo, BindingResult result) {
		ResponseInfo info = null;
		log.debug("修改的内容为: " + vo);
		if (result.hasErrors()) {
			StringBuffer sb = new StringBuffer();
			List<ObjectError> allErrors = result.getAllErrors();
			for (ObjectError error : allErrors) {
				log.error("数据服务接口修改：" + error.getDefaultMessage());
				sb.append(error.getDefaultMessage() + ",");
			}
			info = new ResponseInfo();
			info.code = ErrorCodeEnum.CALL_VALIDATE_ERROR.getCode();
			info.msg = sb.toString();
		} else {
			info = operationService.updateSelective(vo);
		}
		
		return info;
	}
	
	// 删除
	@DeleteMapping("/delete")
	public ResponseInfo delete(Integer id) {
		log.debug("删除的ID为: " + id);
		return operationService.deleteByPrimaryKey(id);
	}
	
	/**
	 * 查看
	 * @param id
	 * @return
	 */
	@GetMapping("/get")
	public ResponseInfo selectByPrimaryKey(Integer id) {
		log.debug("查看的ID为: " + id);
		return operationService.selectByPrimaryKey(id);
	}
}
