package com.gaode.xtd.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全部接口解决跨域问题
 * @author andyc
 * 2018-3-1
 */
public class SimpleCORSFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;  
		HttpServletResponse response = (HttpServletResponse) res;  
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
