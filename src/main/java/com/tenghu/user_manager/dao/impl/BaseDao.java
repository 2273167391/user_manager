package com.tenghu.user_manager.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tenghu.user_manager.page.model.PageBean;

public class BaseDao<T> {
	@Autowired
	protected SessionFactory sessionFactory;
	
	/**
	 * 分页查询
	 * @param pageBean 分页实体
	 * @param hql 查询语句
	 * @param paramsList 查询参数
	 * @return
	 */
	public PageBean<T> findPage(PageBean<T> pageBean,String hql,List<Object> paramsList){
		//获取Session
		Session session=sessionFactory.getCurrentSession();
		//创建Query对象
		Query query=session.createQuery(hql);
		//判断查询参数是否为空 
		if(paramsList.size()>0){
			for(int i=0;i<paramsList.size();i++){
				query.setParameter(i, paramsList.get(i));
			}
		}
		//获取总记录数
		int totalNum=query.list().size();
		//设置开始行
		query.setFirstResult((pageBean.getPageNo()-1)*pageBean.getPageSize());
		//设置最大结果
		query.setMaxResults(pageBean.getPageSize());
		//获取查询结果
		List<T> resultList=query.list();
		//设置分页参数
		pageBean.setTotalNum(totalNum);
		pageBean.setResultData(resultList);
		return pageBean;
	}
}
