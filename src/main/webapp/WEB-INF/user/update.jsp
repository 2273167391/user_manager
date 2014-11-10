<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<%@ include file="/resources/common/resource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/login/login_register.css"/>
<script type="text/javascript">
$(document).ready(function(){
	updateCheck();
});
</script>
<title>修改用户</title>
</head>
<body>
<div id="readd_main">
<p>修改用户</p>
<form action="update" method="post" id="updateForm">
	<label>用户名：</label><input type="text" name="userName" value="${user.userName }"/><br/>
	<label>真实姓名：</label><input type="text" name="trueName" value="${user.trueName }"/><br/>
	<label>性别：</label>
		<c:choose>
			<c:when test="${user.sex==1 }">
				<input type="radio" name="sex" value="1" checked="checked"/>&nbsp;男&nbsp;&nbsp;<input type="radio" value="0" name="sex"/>&nbsp;女<br/>
			</c:when>
			<c:otherwise>
				<input type="radio" name="sex" value="1"/>&nbsp;男&nbsp;&nbsp;<input type="radio" name="sex" value="0" checked="checked"/>&nbsp;女<br/>
			</c:otherwise>
		</c:choose>
	<label>电话号码：</label><input type="text" name="phone" value="${user.phone }"/><br/>
	<label>电子邮箱：</label><input type="text" name="email" value="${user.email }"/><br/>
	<label>出生日期：</label><input type="text" name="birthday" value="${user.birthday }"/><br/>
	<input type="submit" value="修改" class="btn_info"/>&nbsp;
	<input type="button" value="返回" onclick="javascript:history.go(-1)" class="btn"/>
</form>
</div>
</body>
</html>