package com.tenghu.user_manager.comm.controller;

import javax.servlet.http.HttpSession;

import com.sun.jmx.snmp.ThreadContext;
import com.tenghu.user_manager.model.Users;

public class CommController {
	protected final String JSON_MESSAGE="json_message";
	protected final String RETURN_JSON_URL="/common/json_message";
	
	/**
	 * 获取当前登录用户
	 * @param httpSession
	 * @return
	 */
	public Users getCurrentLoginUser(){
		//获取HttpSession
		HttpSession httpSession=(HttpSession) ThreadContext.get("http_session");
		//获取用户对象
		Object obj=httpSession.getAttribute("login_user");
		return (Users)obj;
	}
}
