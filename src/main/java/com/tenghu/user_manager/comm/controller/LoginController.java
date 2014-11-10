package com.tenghu.user_manager.comm.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.service.IUsersService;
import com.tenghu.user_manager.util.JSONMessageUtil;
import com.tenghu.user_manager.util.SecurityPwdUtil;

/**
 * 登录控制器
 * @author Arvin_Li
 *
 */
@Controller
public class LoginController extends CommController{
	
	@Autowired
	private IUsersService usersService;
	
	@RequestMapping(value={"/login","/"})
	public String login(){
		return "login";
	}
	
	//@RequestMapping(value="/login.do",method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam(value="username") String userName,@RequestParam(value="password") String password,HttpSession httpSession){
		//查询用户
		Users user=usersService.queryUsersByName(userName);
		if(null==user)
			return JSONMessageUtil.getErrorJSON("用户名和密码错误");
		else{
			try {
				if(!SecurityPwdUtil.authenticate(password, user.getPassword(), user.getSalt())){
					return JSONMessageUtil.getErrorJSON("用户名和密码错误");
				}else{
					httpSession.setAttribute("login_user", user);
					//修改登录时间
					user.setLoginTime(System.currentTimeMillis());
					usersService.updateUser(user);
					return JSONMessageUtil.getSuccessJSON("登录成功");
				}
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				e.printStackTrace();
			}
		}
		return JSONMessageUtil.getErrorJSON("系统异常");
	}
	
	@RequestMapping(value="/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession httpSession){
		httpSession.removeAttribute("login_user");
		return "login";
	}
}
