package com.gaode.xtd.common.exception;

import com.gaode.xtd.common.enums.ErrorCodeEnum;

/**
 * Dao层抛出的异常
 * @author andyc
 * @since 2018-2-26
 */
public class DaoException extends RuntimeException {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 8376281035177970222L;
	
	public static final int COMMON_ERROR_CODE = 7000;
	public static final String COMMON_ERROR_MSG = "Dao层的未知错误";
    
    private Integer code;
    private String msg;
    
    public DaoException(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }
    
    public DaoException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    
    public DaoException(String msg) {
        this.code = CommonException.COMMON_ERROR_CODE;
        this.msg = msg;
    }
    
    public DaoException() {
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
