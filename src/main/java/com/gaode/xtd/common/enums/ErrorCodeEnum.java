package com.gaode.xtd.common.enums;

/**
 * 通用的错误定义
 * @author andyc
 * @since 2018-2-26
 */
public enum ErrorCodeEnum {
	CALL_SUCCESS(0, "操作成功"), 
    CALL_ERROR(9999, "系统异常，请稍后重试...");
    /**
     * 错误码
     */
    private Integer code;
	
    /**
     * 描述信息
     */
    private String msg;
    
    ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
