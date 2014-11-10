//添加自定义电话号码验证
$.validator.addMethod("checkPhone",function(value,element){
	var regPhone=/^(1[3-9](\d{9}))|(0(\d{2,3})-(\d{6,10}))$/;
	return regPhone.test(value);
},"请输入正确的电话号码格式");

//添加自定义日期格式验证
$.validator.addMethod("checkDate",function(value,element){
	var regDate=/^(\d{4}[-|/](\d{1,2})[-|/](\d{1,2}))$/;
	return regDate.test(value);
},"请输入正确的日期格式");