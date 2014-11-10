/**
 * 获取分页参数
 */
function getSplitPageParam(){
	var pageNo=$("#_pageNo").val();//当前页
	var pageSize=$("#_pageSize").val();//每页显示的行数
	var totalNum=$("#_totalNum").val();//总记录数
	var totalPage=$("#_totalPage").val();//总页数
	
	pageNo=parseInt(pageNo,10);
	pageSize=parseInt(pageSize,10);
	totalNum=parseInt(totalNum,10);
	totalPage=parseInt(totalPage,10);
	
	var orderByColumn=$("#_orderByColumn").val();//排序字段
	var orderMode=$("#_orderMode").val();//排序模式
	
	if(""==orderMode||null==orderMode){
		orderMode="ASC";
	}
	return [pageNo,pageSize,totalNum,totalPage,orderByColumn,orderMode];
}

/**
 * 分页输出
 */
function splitPageOut(pageNo,pageSize,totalNum,totalPage,orderByColumn,orderMode){
	var splitPageStr="";
	if(pageNo>1&&pageNo<=totalPage){
		splitPageStr+="<a href='javascript:splitPage("+1+")'>首页</a>";
		splitPageStr+="<a href='javascript:splitPage("+(pageNo-1)+")'>上一页</a>"
	}
	for(var i=1;i<=totalPage;i++){
		if(i==2&&pageNo-3>1){
			splitPageStr+="<a href='javascript:void(0)'>...</a>";
			i=pageNo-3;
		}else if(i==pageNo+3&&pageNo+3<totalPage){
			splitPageStr+="<a href='javascript:void(0)'>...</a>";
			i=totalPage-1;
		}else{
			if(i==pageNo){
				splitPageStr+="<a href='javascript:void(0)' style='background:#E0E0E0;color:#000000'>"+pageNo+"</a>";
			}else{
				splitPageStr+="<a href='javascript:splitPage("+i+")'>"+i+"</a>";
			}
		}
	}
	
	if(pageNo<totalPage&&pageNo>=1){
		splitPageStr+="<a href='javascript:splitPage("+(pageNo+1)+")'>下一页</a>";
		splitPageStr+="<a href='javascript:splitPage("+totalPage+")'>末页</a>"
	}
	splitPageStr+="<label>共"+totalNum+"条记录</label>";
	splitPageStr+="<label>当前页："+pageNo+"/"+totalPage+"</label>";
	splitPageStr+=selectPageSize(pageSize);
	splitPageStr+="<label>跳转到：<input type='text' class='pageNumber' value='"+pageNo+"' size='4'/><input type='button' value='跳转' onclick='jumpPage();' class='jumpBtn'/><label>";
	splitPageStr+="<input type='hidden' id='pageNoId' name='pageNo' value='"+pageNo+"'/>";
	splitPageStr+="<input type='hidden' id='orderByColumnId' name='orderByColumn' value='"+orderByColumn+"'/>";
	splitPageStr+="<input type='hidden' id='orderModeId' name='orderMode' value='"+orderMode+"'/>";
	
	$("#splitPageCon").html(splitPageStr);
}

/**
 * 跳转
 */
function jumpPage(){
	var totalPage=$("#_totalPage").val();//总页数
	var pageNum=$(".pageNumber").val();
	if(isNaN(pageNum)){
		alert("请输入正确的数字");
		return;
	}else if(parseInt(pageNum)>parseInt(totalPage)){
		$("#pageNoId").val(totalPage);
	}else{
		$("#pageNoId").val(pageNum);
	}
	ajaxForm("splitForm");
}

/**
 * 排序函数
 * @param columnName
 */
function orderByFun(columnName){
	var orderByColumnId=$("#orderByColumnId");
	var orderByColumnVal=orderByColumnId.val();
	var orderModeId=$("#orderModeId");
	var orderModeVal=orderModeId.val();
	if(columnName==orderByColumnVal){
		if(orderModeVal==""){
			orderModeId.val("ASC")
		}else if(orderModeVal=="ASC"){
			orderModeId.val("DESC")
		}if(orderModeVal=="DESC"){
			orderModeId.val("ASC")
		}
	}else{
		orderByColumnId.val(columnName);
		orderModeId.val("ASC")
	}
	$("#pageNoId").val("1");//当前页为1
	ajaxForm("splitForm");
}


/**
 * 分页
 * @param num
 */
function splitPage(toPage){
	$("#pageNoId").val(toPage);
	ajaxForm("splitForm");
}

/**
 * 判断分页显示行
 * @param pageSize
 * @returns {String}
 */
function selectPageSize(pageSize){
	var selectSize="<label>每页显示：<select onblur='searchAndSelectSize()' name='pageSize' class='selectPage'>";
	if(pageSize==10){
		selectSize+="<option value='10' selected='selected'>10</option><option value='30'>30</option><option value='50'>50</option>";
	}else if(pageSize==30){
		selectSize+="<option value='10'>10</option><option value='30' selected='selected'>30</option><option value='50'>50</option>";
	}else if(pageSize==50){
		selectSize+="<option value='10'>10</option><option value='30'>30</option><option value='50' selected='selected'>50</option>";
	}
	selectSize+="</select></label>";
	return selectSize;
}

/**
 * 搜索与分页选择
 */
function searchAndSelectSize(){
	$("#pageNoId").val("1");//当前页为1
	ajaxForm("splitForm");
}

/**
 * 异步提交
 */
function ajaxForm(formID){
	$.ajax({
		url:$("#"+formID).attr('action'),
		data:$("#"+formID).serialize(),
		type:'POST',
		dataType:'html',
		success:function(data){
			$("#content").html(data);
		}
	});
}