<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지 연습</title>
</head>

<body>
	<form action="./loginPractice2.do" method="post">
		아이디 : <input type="text" name="id">
		비밀번호 : <input type="text" name="pw">
		주민번호 앞7자리 : <input type='number' name="birthAndGender" id="birthAndGender" />
		이메일 : <input type="text" name="email">
		전화번호 : <input type="text" name="phone">
		<input type="submit">
	</form>
</body>
</html>