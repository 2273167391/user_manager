package com.tenghu.user_manager.page.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页实体类
 * @author Arvin_Li
 *
 */
@SuppressWarnings("serial")
public class PageBean<T> implements Serializable{

	private final int DEFAULT_PAGE_SIZE=10;//默认当前页
	private final int DEFAULT_PAGE_NO=1;//默认当前页
	
	private int pageSize=DEFAULT_PAGE_SIZE;//每页显示的数量
	private int pageNo=DEFAULT_PAGE_NO;//当前页
	
	private int totalPage;//总页数
	private int totalNum;//总记录数
	
	private List<T> resultData;//查询结果数据
	
	private Map<String, Object> paramters=new HashMap<String, Object>();//查询参数
	
	private String orderByColumn;//排序条件 order by create_time
	private String orderMode;//排序方式 ASC和DESC
	
	//默认构造函数
	public PageBean() {
		super();
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo<=0?1:pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo<0?1:pageNo;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public int getTotalPage() {
		if(getTotalNum()%getPageSize()==0){
			return getTotalNum()/getPageSize();
		}else{
			return getTotalNum()/getPageSize()+1;
		}
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * 获取分页查询后的数据
	 * @return
	 */
	public List<T> getResultData() {
		return resultData;
	}
	public void setResultData(List<T> resultData) {
		this.resultData = resultData;
	}
	/**
	 * 获取查询参数
	 * @return
	 */
	public Map<String, Object> getParamters() {
		return paramters;
	}
	
	public void setParamters(Map<String, Object> paramters) {
		this.paramters = paramters;
	}

	public String getOrderByColumn() {
		return orderByColumn;
	}

	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}

	public String getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(String orderMode) {
		this.orderMode = orderMode;
	}
	
}
