<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.tenghu.com/dateFormat" prefix="df" %>
<c:if test="${empty login_user }">
	<script type="text/javascript">alert("你没有登录或登录超时");location.href='${ctx}'</script>
</c:if>