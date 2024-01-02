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
<form action="sessionLogin3.do" method="post">
	<div>아이디 : <input type="text" name="id"></div>
	<div>비밀번호 : <input type="text" name="pw"></div>
	<div>
		<input type="submit">
		<input type="reset">
	</div>
</form>
</body>
</html>