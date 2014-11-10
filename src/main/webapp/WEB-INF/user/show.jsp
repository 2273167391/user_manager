<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/login/login_register.css"/>
<title>用户信息</title>
<style type="text/css">
	#readd_main label{width:120px;}
</style>
</head>
<body>
<div id="readd_main">
	<p style="margin:0px;">用户信息</p>
	<form action="" method="post">
		<label>用户名：</label>${user.userName }<br/>
		<label>真实姓名：</label>${user.trueName }<br/>
		<label>性别：</label><c:choose><c:when test="${user.sex==1 }">男</c:when><c:otherwise>女</c:otherwise></c:choose><br/>
		<label>出生日期：</label>${user.birthday }<br/>
		<label>电话号码：</label>${user.phone }<br/>
		<label>电子邮箱：</label>${user.email }<br/>
		<label>创建时间：</label><df:dateFormat value="${user.createTime }"/><br/>
		<label>最后登录时间：</label><df:dateFormat value="${user.loginTime }"/><br/>
		<input type="button" value="返回" onclick="javascript:history.go(-1)" class="btn"/>
	</form>
</div>
</body>
</html>