package com.tenghu.user_manager.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tenghu.user_manager.dao.IUsersDao;
import com.tenghu.user_manager.model.Users;
import com.tenghu.user_manager.page.model.PageBean;
import com.tenghu.user_manager.util.SecurityPwdUtil;
import com.tenghu.user_manager.util.StringUtil;

@Repository
public class UsersDaoImpl extends BaseDao<Users> implements IUsersDao {

	@SuppressWarnings("unchecked")
	public List<Users> queryAllList() {
		// 查询语句
		String sql = "from Users";
		// 获取Session对象
		Session session = sessionFactory.getCurrentSession();
		List list=session.createCriteria(Users.class).setProjection(Projections.projectionList().add(Projections.count("id"))).list();
		System.out.println(list.get(0));
		// 创建Query对象
		Query query = session.createQuery(sql);
		
		return query.list();
	}
	
	public PageBean<Users> queryAllList(PageBean<Users> pageBean){
		//创建StringBuffer
		StringBuffer hql=new StringBuffer("from Users where 1=1 ");
		//创建参数集合
		List<Object> paramsList=new ArrayList<Object>();
		//获取查询参数集合
		Map<String, Object> paramters=pageBean.getParamters();
		if(paramters.size()>0){
			//判断用户名是否为空
			if(paramters.containsKey("userName")){
				if(!StringUtil.isNUll(paramters.get("userName").toString())){
					paramsList.add("%"+paramters.get("userName")+"%");
					hql.append("and userName like ? ");
				}
			}
			
			//判断性别
			if(paramters.containsKey("trueName")){
				if(!StringUtil.isNUll(paramters.get("trueName").toString())){
					paramsList.add("%"+paramters.get("trueName")+"%");
					hql.append("and trueName like ? ");
				}
			}
			
			//判断电话号码
			if(paramters.containsKey("phone")){
				if(!StringUtil.isNUll(paramters.get("phone").toString())){
					paramsList.add("%"+paramters.get("phone")+"%");
					hql.append("and phone like ?");
				}
			}
			
			//判断电子邮箱
			if(paramters.containsKey("email")){
				if(!StringUtil.isNUll(paramters.get("email").toString())){
					paramsList.add("%"+paramters.get("email")+"%");
					hql.append("and email like ? ");
				}
			}
		}
		
		//判断排序字段
		if(!StringUtil.isNUll(pageBean.getOrderByColumn())){
			hql.append("order by ").append(pageBean.getOrderByColumn()).append(" ").append(pageBean.getOrderMode());
		}else{
			hql.append("order by createTime desc");
		}
		return findPage(pageBean, hql.toString(), paramsList);
	}

	@SuppressWarnings("unchecked")
	public Users queryUsersByName(String userName) {
		// 查询语句
		String sql = "from Users where username=:username";
		// 获取Session
		Session session = sessionFactory.getCurrentSession();
		// 创建Query对象
		Query query = session.createQuery(sql);
		// 设置参数
		query.setString("username", userName);
		List<Users> usersList = query.list();
		if (usersList.size() > 0)
			return usersList.get(0);
		return null;
	}

	public Users queryUsersById(int id) {
		// 查询语句
		String sql = "from Users where id=:id";
		// 获取Session
		Session session = sessionFactory.getCurrentSession();
		// 创建Query对象
		Query query = session.createQuery(sql);
		// 设置参数
		query.setInteger("id", id);
		List<Users> usersList = query.list();
		if (usersList.size() > 0)
			return usersList.get(0);
		return null;
	}

	public void deleteUsersById(int id) {
		//获取Session
		Session session=sessionFactory.getCurrentSession();
		//查询用户
		Users user=(Users) session.load(Users.class, id);
		//删除用户
		session.delete(user);
	}

	public void addUser(Users user) throws Exception {
		//获取Session对象
		Session session=sessionFactory.getCurrentSession();
		//获取密码盐
		String salt=SecurityPwdUtil.generateSalt();
		//密码加密
		String password=SecurityPwdUtil.getEncryptedPassword(user.getPassword(), salt);
		user.setSalt(salt);
		user.setPassword(password);
		//设置添加时间
		user.setCreateTime(System.currentTimeMillis());
		session.save(user);//添加用户
	}

	public void updateUser(Users user) throws Exception {
		//获取Session对象
		Session session=sessionFactory.getCurrentSession();
		//修改用户
		session.merge(user);
	}

}
