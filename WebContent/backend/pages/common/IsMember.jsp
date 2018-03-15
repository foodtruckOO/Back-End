<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${empty sessionScope.dto }">
	<script>
		alert("로그인 후 이용하세요");
		location.replace("<c:url value='/backend/pages/Login.jsp'/>");
	</script>
</c:if>
<c:if test="${sessionScope.dto.grade == '3' }">
	<script>
		alert("권한이 부여되지 않았습니다. 마스터 관리자에게 문의하세요");
		location.replace("<c:url value='/backend/pages/Login.jsp'/>");
	</script>
</c:if>