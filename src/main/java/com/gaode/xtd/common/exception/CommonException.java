package com.gaode.xtd.common.exception;

import com.gaode.xtd.common.enums.ErrorCodeEnum;


/**
 * 通用异常
 * @author andyc
 * @since 2018-2-26
 */
public class CommonException extends RuntimeException {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -1180059982502682421L;
	
	public static final int COMMON_ERROR_CODE = 6000;
	public static final String COMMON_ERROR_MSG = "通用的未知错误";
    
    private Integer code;
    private String msg;
    
    public CommonException(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }
    
    public CommonException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public CommonException(String msg) {
        this.code = CommonException.COMMON_ERROR_CODE;
        this.msg = msg;
    }
    
    public CommonException() {
        this.code = CommonException.COMMON_ERROR_CODE;
        this.msg = CommonException.COMMON_ERROR_MSG;
    }

	public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

