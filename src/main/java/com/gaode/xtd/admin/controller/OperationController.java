package com.gaode.xtd.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gaode.xtd.admin.domain.query.OperationParam;
import com.gaode.xtd.admin.service.OperationService;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.exception.CommonException;
import com.gaode.xtd.common.exception.DaoException;
import com.gaode.xtd.common.exception.ServiceException;
import com.gaode.xtd.common.info.ResponseInfo;
import com.gaode.xtd.common.util.UriUtils;

/**
 * 操作控制器
 * @author andyc 
 * 2018-2-26
 */
@RestController
@RequestMapping("/admin/operation")
public class OperationController {
	// 日志记录器
	private Logger log = Logger.getLogger(getClass());
	// 注入操作处理的Service
	@Autowired
	private OperationService operationService;

	// 数据过滤查询
	@RequestMapping("/search")
	public ResponseInfo searchList(OperationParam param, HttpServletRequest request) {
		ResponseInfo info = null;
		try {
			// 组装一下查询参数，为后面拼接sql做准备
			Map<String, Object> queryMap = UriUtils.analyzeQuery(request.getQueryString());
			param.setQuery(queryMap);
			info = operationService.queryList(param);
		} catch (ServiceException e) {
			log.error(e.getMessage(), e);
			info = new ResponseInfo();
			info.code = e.getCode();
			info.msg = e.getMsg();
		} catch (DaoException e) {
			log.error(e.getMessage(), e);
			info = new ResponseInfo();
			info.code = e.getCode();
			info.msg = e.getMsg();
		} catch (CommonException e) {
			log.error(e.getMessage(), e);
			info = new ResponseInfo();
			info.code = e.getCode();
			info.msg = e.getMsg();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			info = new ResponseInfo();
			info.code = ErrorCodeEnum.CALL_ERROR.getCode();
			info.msg = ErrorCodeEnum.CALL_ERROR.getMsg();
		}

		return info;
	}
}
