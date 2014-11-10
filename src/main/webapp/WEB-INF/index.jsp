<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<%@ include file="/resources/common/resource.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<style type="text/css">
	#main{width:1200px;margin:20px auto;}
	.title{
		width: 100%;
		clear: none;
		line-height: 35px;
	}
	
	.title label{display: inline-block;}
	.title label:nth-last-child(1){
		float:right;
		margin-right: 10px;
		color:#40B9DB;
		cursor: pointer;
	}
	
	.title label:nth-last-child(1):hover{
		color:#F0AD4E
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url:'user/list',
			dataType:'html',
			success:function(data){
				$("#content").html(data);
			}
		});
	});
	
	//退出
	function logout(){
		if(confirm("确定要退出吗？")){
			location.href="logout";
		}
	}
</script>
</head>
<body>
	
	<div id="main">
		<p class="title"><label>欢迎回来：<a href="user/${login_user.id }">${login_user.userName }</a></label><label onclick="logout();">退出</label></p>
		<div id="content"></div>
	</div>
</body>
</html>