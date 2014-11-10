package com.tenghu.user_manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tenghu.user_manager.dao.IUsersDao;
import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.page.model.PageBean;
import com.tenghu.user_manager.service.IUsersService;
import com.tenghu.user_manager.util.JSONMessageUtil;
import com.tenghu.user_manager.util.StringUtil;
@Service
public class UsersServiceImpl implements IUsersService {
	
	@Autowired
	private IUsersDao usersDao;

	public List<Users> queryAllUsers() {
		return usersDao.queryAllList();
	}

	public Users queryUsersByName(String userName) {
		return usersDao.queryUsersByName(userName);
	}

	public Users queryUsersById(int id) {
		return usersDao.queryUsersById(id);
	}

	public String deleteUserById(int id) {
		try {
			usersDao.deleteUsersById(id);
			return JSONMessageUtil.getSuccessJSON("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONMessageUtil.getErrorJSON("删除失败！");
	}

	public String addUser(Users user) {
		//查询用户是否存在
		Users oldUser=queryUsersByName(user.getUserName());
		if(null==oldUser){
			try {
				//添加用户
				usersDao.addUser(user);
				return JSONMessageUtil.getSuccessJSON("添加成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JSONMessageUtil.getErrorJSON("添加失败！");
		}else{
			return JSONMessageUtil.getErrorJSON("用户已存在，添加失败！");
		}
	}

	public String updateUser(Users user) {
		//查询用户
		Users oldUser=queryUsersById(user.getId());
		if(null!=oldUser){
			//判断用户名是否为空
			if(!StringUtil.isNUll(user.getUserName())){
				oldUser.setUserName(user.getUserName());
			}
			
			//判断真实姓名是否为空
			if(!StringUtil.isNUll(user.getTrueName())){
				oldUser.setTrueName(user.getTrueName());
			}
			
			//判断电话号码是否为空
			if(!StringUtil.isNUll(user.getPhone())){
				oldUser.setPhone(user.getPhone());
			}
			
			//判断电子邮箱是否为空
			if(!StringUtil.isNUll(user.getEmail())){
				oldUser.setEmail(user.getEmail());
			}
			
			//判断出生日期是否为空
			if(!StringUtil.isNUll(user.getBirthday())){
				oldUser.setBirthday(user.getBirthday());
			}
			
			if(user.getLoginTime()!=0){
				oldUser.setLoginTime(user.getLoginTime());
			}
			oldUser.setSex(user.getSex());
			try {
				//修改用户
				usersDao.updateUser(oldUser);
				return JSONMessageUtil.getSuccessJSON("修改成功！");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return JSONMessageUtil.getErrorJSON("修改失败！");
		}else{
			return JSONMessageUtil.getErrorJSON("用户不存在，修改失败！");
		}
	}

	public PageBean<Users> queryAllList(PageBean<Users> pageBean) {
		return usersDao.queryAllList(pageBean);
	}

}
