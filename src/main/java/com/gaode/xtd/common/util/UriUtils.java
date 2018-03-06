package com.gaode.xtd.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * Uri相关操作帮助类
 * 
 * @author andyc 2018-2-26
 */
public final class UriUtils {
	// 日志记录器
	private static Logger log = Logger.getLogger(UriUtils.class);

	/**
	 * 解析URL传递的参数
	 * 
	 * @param query
	 * @return 封装成map的查询条件
	 */
	public static final Map<String, Object> analyzeQuery(String query) {
		if (StringUtils.isBlank(query)) {
			return null;
		}
		Map<String, Object> map = null;
		try {
			query = URLDecoder.decode(query, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("get编码转换错误", e);
			e.printStackTrace();
		}
		String[] array = query.split("&");
		if (array != null) {
			int len = array.length;
			map = new HashMap<String, Object>();
			for (int i = 1; i < len; i++) {// 把第一个operName=xxx去掉
				String s = array[i];
				if (StringUtils.isNotBlank(s)) {
					String[] keyValue = s.split("=");
					if (keyValue != null && keyValue.length ==2) {
						map.put(keyValue[0], keyValue[1]);
					}
				}
			}
		}
		return map;
	}
	
	/**
	 * 把请求参数封装成map,不管是GET还是POST
	 * @param request
	 * @return
	 */
	public static final Map<String, Object> analyzeQuery(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String[]> params = request.getParameterMap();
		if (params != null) {
			for (String key : params.keySet()) {
	            String[] values = params.get(key);
	            for (int i = 0; i < values.length; i++) {
	                String value = values[i];
	                // 后面的会覆盖掉前面的,去除operName字段
	                if (!"operName".equals(key)) {
	                	result.put(key, value);
	                }
	            }
	        }
		}
		return result;
	}
}
