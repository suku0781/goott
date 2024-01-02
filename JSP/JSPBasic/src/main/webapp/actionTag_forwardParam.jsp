<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:forward page="actionTag_forwardParam1.jsp"> 
		<jsp:param value="shk" name="userId"/>
		<jsp:param value="27" name="age"/>
		<jsp:param value="홍길동" name="name"/>
	</jsp:forward>
</body>
</html>