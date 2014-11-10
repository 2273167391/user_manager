package com.tenghu.user_manager.dao;
import java.util.List;

import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.page.model.PageBean;
public interface IUsersDao {
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<Users> queryAllList();
	public PageBean<Users> queryAllList(PageBean<Users> pageBean);
	
	/**
	 * 根据用户查询用户
	 * @param userName
	 * @return
	 */
	public Users queryUsersByName(String userName);
	
	public Users queryUsersById(int id);
	public void deleteUsersById(int id);
	public void addUser(Users user) throws Exception;
	public void updateUser(Users user) throws Exception;
}
