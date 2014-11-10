<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="${ctx }/resources/js/splite_page.js"></script>
<!-- 当前页 -->
<input type="hidden" name="pageNo" id="_pageNo" value="${pageBean.pageNo }"/>
<!-- 每页显示的行数 -->
<input type="hidden" name="pageSize" id="_pageSize" value="${pageBean.pageSize }"/>
<!-- 总记录数 -->
<input type="hidden" name="totalNum" id="_totalNum" value="${pageBean.totalNum }"/>
<!-- 总页数 -->
<input type="hidden" name="totalPage" id="_totalPage" value="${pageBean.totalPage }"/>

<!-- 排序字段 -->
<input type="hidden" name="orderByColumn" id="_orderByColumn" value="${pageBean.orderByColumn }"/>
<!-- 排序方式 -->
<input type="hidden" name="orderMode" id="_orderMode" value="${pageBean.orderMode }"/>
<!-- 排序图标 -->
<c:set var="icon">
	<c:choose>
		<c:when test="${pageBean.orderMode=='ASC' }">
			<img src="${ctx }/resources/images/up.png"/>
		</c:when>
		<c:when test="${pageBean.orderMode=='DESC' }">
			<img src="${ctx }/resources/images/down.png"/>
		</c:when>
	</c:choose>
</c:set>
<script type="text/javascript">
$(document).ready(function(){
	var splitParam=getSplitPageParam();//获取分页参数
	//输出分页
	splitPageOut(splitParam[0],splitParam[1],splitParam[2],splitParam[3],splitParam[4],splitParam[5],splitParam[6]);
});
</script>