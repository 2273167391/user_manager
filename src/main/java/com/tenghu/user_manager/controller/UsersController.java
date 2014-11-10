package com.tenghu.user_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenghu.user_manager.comm.controller.CommController;
import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.page.model.PageBean;
import com.tenghu.user_manager.service.IUsersService;
import com.tenghu.user_manager.util.JSONMessageUtil;

@Controller
@RequestMapping(value="user")
public class UsersController extends CommController{
	@Autowired
	private IUsersService usersService;
	
	@RequestMapping(value="/list")
	public String list(PageBean<Users> pageBean,Model model){
		pageBean=usersService.queryAllList(null==pageBean?new PageBean<Users>():pageBean);
		model.addAttribute("pageBean", pageBean);
		return "/user/list";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		//获取用户
		Users user=usersService.queryUsersById(id);
		model.addAttribute("user", user);
		return "/user/show";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(){
		return "/user/add";
	}
	
	@RequestMapping(value="/check",method=RequestMethod.POST)
	@ResponseBody
	public String checkUserName(@RequestParam String userName){
		Users user=usersService.queryUsersByName(userName);
		if(null==user){
			return JSONMessageUtil.getSuccessJSON("恭喜，该用户名可以使用！");
		}else{
			return JSONMessageUtil.getErrorJSON("抱歉，该用户名已被使用！");
		}
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public String add(@ModelAttribute("user") Users user){
		return usersService.addUser(user);
	}
	
	/**
	 * 修改用户
	 * @return
	 */
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		//获取修改用户信息
		Users user=usersService.queryUsersById(id);
		model.addAttribute("user",user);
		return "/user/update";
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/{id}/update",method=RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute("user") Users user){
		System.out.println(user.getId()+"\t"+user.getUserName());
		return usersService.updateUser(user);
	}
	
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(@PathVariable int id){
		return usersService.deleteUserById(id);
	}
}
