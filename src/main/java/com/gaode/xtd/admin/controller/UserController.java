package com.gaode.xtd.admin.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gaode.xtd.admin.domain.query.UserParam;
import com.gaode.xtd.admin.service.UserService;
import com.gaode.xtd.common.enums.ErrorCodeEnum;
import com.gaode.xtd.common.exception.CommonException;
import com.gaode.xtd.common.exception.DaoException;
import com.gaode.xtd.common.exception.ServiceException;
import com.gaode.xtd.common.info.ResponseInfo;

/**
 * 用户处理的控制器
 * @author andyc
 * 2018-2-26
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {
	// 日志记录器
	private Logger log = Logger.getLogger(getClass());
	// 注入用户处理的Service
	@Autowired
	private UserService userService;
	
	// 数据过滤查询
	//@RequestMapping(value="/search")
	@ResponseBody
	public ResponseInfo searchList(UserParam param) {
		ResponseInfo info = null;
		try {
			info = userService.queryList(param);
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
