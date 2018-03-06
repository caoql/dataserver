package com.gaode.xtd.common.info;

/**
 * 返回数据的通用格式
 * @author andyc
 * 2018-2-26
 */
public class ResponseInfo {
	// 状态码，0表示成功，其他表示失败
	public Integer code = 0;
	// 提示消息
	public String msg = "操作成功";
	// 返回的具体数据
	public Object data;
	// 特殊的返回值,如存储过程
	public Object out;
}
