package com.tenghu.user_manager.service;

import java.util.List;

import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.page.model.PageBean;

public interface IUsersService {
	public List<Users> queryAllUsers();
	public PageBean<Users> queryAllList(PageBean<Users> pageBean);
	public Users queryUsersByName(String userName);
	public Users queryUsersById(int id);
	public String deleteUserById(int id);
	public String addUser(Users user);
	public String updateUser(Users user);
}
