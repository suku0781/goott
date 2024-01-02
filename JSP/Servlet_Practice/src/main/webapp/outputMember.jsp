<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 출력</title>
</head>
<body>
	<h1>회원정보</h1>
	<div>아이디 : ${member.id } </div>
	<div>비밀번호 : ${member.pw } </div>
	<div>이메일 : ${member.email } </div>
	<div>나이 : ${member.age } </div>
	<div>휴대폰번호 : ${member.phoneNumber } </div>
	<div>성별 : ${member.gender } </div>
	<div>취미 : ${member.hobbystr } </div>
	<div>직업 : ${member.jobstr } </div>
	<div>메모 : ${member.memo } </div>
<!-- (id, pw, email, birthday, age, phoneNumber, gender, hobbyStr, jobStr, memo) -->
</body>
</html>