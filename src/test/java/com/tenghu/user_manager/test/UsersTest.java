package com.tenghu.user_manager.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.page.model.PageBean;
import com.tenghu.user_manager.service.IUsersService;

public class UsersTest {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		IUsersService usersService=context.getBean(IUsersService.class);
		//System.out.println(usersService.queryAllUsers().size());
		PageBean<Users> pageBean=new PageBean<Users>();
		pageBean.setPageNo(1);
		pageBean.setPageSize(5);
		pageBean=usersService.queryAllList(pageBean);
		List<Users> userList=pageBean.getResultData();
		System.out.println(userList.size());
		System.out.println("总页数："+pageBean.getTotalPage());
		for (Users users : userList) {
			System.out.println(users.getUserName()+"\t"+users.getTrueName());
		}
	}
}
