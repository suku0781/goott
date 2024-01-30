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
	<jsp:include page="header.jsp"></jsp:include>
	
	<div class="container">
		<h1>commonError.jsp</h1>
		<div>${requestScope.errorMessage }</div>
		<c:forEach var="err" items="${requestScope.errorStackTrace }" >
			<div>${err }</div>
		
		</c:forEach>
		<div></div>
	</div>
		
	<jsp:include page="footer.jsp"></jsp:include>	
</body>
</html>