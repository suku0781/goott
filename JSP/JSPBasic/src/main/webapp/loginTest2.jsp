<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./sessionLogin.do" method="post">
		<div>아이디 : <input type="text" name="userId"></div>
		<div>비밀번호 : <input type="password" name="userPw"></div>
		<div>
			<input type="submit" value="로그인">
			<input type="reset" value="취소">
		</div>
	</form>
</body>
</html>