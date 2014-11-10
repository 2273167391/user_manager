package com.tenghu.user_manager.comm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注册控制器
 * @author Arvin_Li
 *
 */
@Controller
public class RegisterControll {
	
	@RequestMapping(value="/register")
	public String register(){
		return "register";
	}
}
