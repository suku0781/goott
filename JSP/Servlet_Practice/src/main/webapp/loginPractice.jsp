<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
</head>
<body>
<form action="./LoginPractice" method="post" name="prcForm">
	아이디 : <input type="text" name="userId">
	비밀번호 : <input type="password" name="userPw">
	<input type="submit">
	<%=request.getParameter("status")%>
</form>
</body>
</html>