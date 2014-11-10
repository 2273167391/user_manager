/**
 * 用户添加验证
 */
function addCheck(return_url,check_url){
	$("#addForm").validate({
		rules:{
			userName:{
				required:true,
				remote:{
					type:"post",
					url:check_url,
					data:{userName:function(){return $("#userName").val();}},
					dataType:'json',
					dataFilter:function(data,type){
						data=jQuery.parseJSON(data);
						if(data.result==1){return true;}else{return false;}
					}
				}
			},
			password:{required:true,minlength:6},
			confirmPwd:{required:true,equalTo:"#password"},
			trueName:"required",
			phone:{required:true,checkPhone:true},
			email:{required:true,email:true},
			birthday:{required:true,checkDate:true}
		},
		messages:{
			userName:{
				required:"用户名不能为空",
				remote:"用户名已被使用"
			},
			password:{
				required:"密码不能为空",
				minlength:"密码长度不能小于{0}个字符"
			},
			confirmPwd:{
				required:"确认密码不能为空",
				equalTo:"两次密码输入不一致"
			},
			trueName:"真实姓名不能为空",
			phone:{
				required:"电话号码不能为空"
			},
			email:{
				required:"电子邮箱不能为空",
				email:"电子邮箱格式错误"
			},
			birthday:{
				required:"出生日期不能为空"
			}
		},submitHandler:function(form){
			formRequest(form,return_url);
		}
	}); 
}

/**
 * 用户更新验证
 */
function updateCheck(){
	$("#updateForm").validate({
		rules:{
			phone:{checkPhone:true},
			email:{email:true},
			birthday:{checkDate:true}
		},
		messages:{
			email:{
				email:"电子邮箱格式错误"
			},
		},submitHandler:function(form){
			formRequest(form,'../../index');
		}
	}); 
}

/**
 * 表单请求
 * @param form 表单对象
 * @param url 跳转url
 */
function formRequest(form,url){
	$.post($(form).attr('action'),$(form).serialize(),function(data){
		if(data.result==1){
			location.href=url;
		}
		alert(data.message)
	},'json');
}