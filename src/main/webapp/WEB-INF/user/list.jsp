<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<%@ include file="/resources/common/splitPage.jsp" %>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/splite_page.css"/>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/user/user.css"/>
<script type="text/javascript">
	function deleteUser(urls,id){
		if(confirm("确定要删除该用户吗？")){
			if(id=='${login_user.id}'){
				alert("不能删除当前登录用户");
			}else{
				$.ajax({
					url:urls,
					type:'get',
					dataType:'json',
					success:function(data){
						if(data.result==1)
							location.href='${ctx}/index';
						alert(data.message);
					},error:function(e){
						alert('异步失败');
					}
				});
			}
		}
	}
</script>
<p style="margin-top: 10px;"><a href="user/add">添加用户</a></p>
<form action="user/list" method="POST" id="splitForm">
	<div class="search">
		用户名：<input type='text' name="paramters['userName']" value="${pageBean.paramters['userName'] }"/>
		真实姓名：<input type='text' name="paramters['trueName']" value="${pageBean.paramters['trueName'] }"/>
		电话号码：<input type='text' name="paramters['phone']" value="${pageBean.paramters['phone'] }"/>
		电子邮箱：<input type='text' name="paramters['email']" value="${pageBean.paramters['email'] }"/>
		<input type="button" value="搜索" onclick="searchAndSelectSize()"/>
	</div>
	<table border="0" width="1200" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<th onclick="orderByFun('userName')" width="8%"><c:if test="${pageBean.orderByColumn=='userName' }">${icon }</c:if>用户名</th>
			<th onclick="orderByFun('trueName')" width="8%"><c:if test="${pageBean.orderByColumn=='trueName' }">${icon }</c:if>真实姓名</th>
			<th onclick="orderByFun('sex')" width="4%"><c:if test="${pageBean.orderByColumn=='sex' }">${icon }</c:if>性别</th>
			<th onclick="orderByFun('phone')" width="8%"><c:if test="${pageBean.orderByColumn=='phone' }">${icon }</c:if>电话号码</th>
			<th onclick="orderByFun('createTime')" width="15%"><c:if test="${pageBean.orderByColumn=='createTime' }">${icon }</c:if>创建时间</th>
			<th onclick="orderByFun('loginTime')" width="15%"><c:if test="${pageBean.orderByColumn=='loginTime' }">${icon }</c:if>最后登录时间</th>
			<th width="10%">操作</th>
		</tr>
		<c:forEach items="${pageBean.resultData }" var="user">
			<tr>
				<td>${user.userName }</td>
				<td>${user.trueName }</td>
				<td>
					<c:choose>
						<c:when test="${user.sex==1 }">男</c:when>
						<c:otherwise>女</c:otherwise>
					</c:choose>
				</td>
				<td>${user.phone }</td>
				<td><df:dateFormat value="${user.createTime }"/></td>
				<td>
					<c:if test="${user.loginTime!=0 }">
						<df:dateFormat value="${user.loginTime }"/>
					</c:if>
				</td>
				<td align="center">
					<label onclick="javascript:location.href='user/${user.id}'" class="btn btn_show">查看</label>
					<label onclick="javascript:location.href='user/${user.id }/update'" class="btn btn_update">修改</label>
					<label onclick="javascript:deleteUser('user/${user.id }/delete',${user.id})" class="btn btn_delete">删除</label>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id="splitPageCon"></div>
</form>