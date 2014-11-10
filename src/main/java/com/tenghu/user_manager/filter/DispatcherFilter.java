package com.tenghu.user_manager.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.jmx.snmp.ThreadContext;
import com.tenghu.user_manager.util.StringUtil;

public class DispatcherFilter implements Filter{

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//获取HttpServletRequest对象
		HttpServletRequest httpRequest=(HttpServletRequest) request;
		//获取HttpServletResponse
		HttpServletResponse httpResponse=(HttpServletResponse) response;
		//获取HttpSession对象
		HttpSession httpSession=httpRequest.getSession();
		httpSession.setAttribute("ctx", StringUtil.getContextPath(httpRequest));
		//将HttpSession放入上下文线程中
		ThreadContext.push("http_session", httpSession);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
