<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/resource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${ctx }/resources/css/login/login_register.css"/>
<script type="text/javascript">
	$(document).ready(function(){
		$("#loginForm").validate({
			rules:{
				username:"required",
				password:"required"
			},
			messages:{
				username:"用户名不能为空",
				password:"密码不能为空"
			},
			submitHandler:function(form){
				$.ajax({
					url:$("#loginForm").attr("action"),
					data:$("#loginForm").serialize(),
					type:'post',
					dataType:'json',
					success:function(data){
						if(data.result==1){
							location.href='index';
						}
						alert(data.message);
					},error:function(e){
						alert('异步失败');
					}
				});
			}
		});
	});
</script>
<title>登录页面</title>
</head>
<body>
<div id="login_main">
	<p>用户登录</p>
	<form action="login" method="post" id="loginForm">
		<label>用户名：</label><input type="text" name="username"/><br/>
		<label>密码：</label><input type="password" name="password"/><br/>
		<input type="submit" value="登录" class="btn_info"/>
		<input type="button" value="注册" onclick="location.href='register'" class="btn btn_warning"/>
	</form>
</div>
</body>
</html>