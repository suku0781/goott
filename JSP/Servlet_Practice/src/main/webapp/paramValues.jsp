<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- checkbox.jsp → input태그의 type=checkbox로 다중값(5개 정도)  입력받아서 get방식으로 (form태그 이용) paramValues.jsp로 데이터를 전송한 후 paramValues.jsp에서 forEach문을 사용하여 출력하시오.  -->
<%-- 	<c:forEach var="index" items="${param }"></c:forEach> --%>
<div>${paramValues }</div> 
<%-- <c:forEach var="i" items="${paramValues.item }" > --%>
<%-- 	<div>${i }</div> --%>
<%-- </c:forEach> --%>
<c:forEach var="i" items="${paramValues.item }" varStatus="status">
	${i}<c:if test="${!status.last}">,</c:if>
</c:forEach>

</body>
</html>