<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 기본 생성자를 이용해서 id속성값의 이름으로 Bean(객체)를 생성한다.  -->
	<jsp:useBean id="member1" class="com.jsp.dto.MemberDTO"></jsp:useBean>
	
<!-- 	useBean으로 생성된 Bean객체에 속성값을 설정(Setter호출) -->
	<jsp:setProperty property="id" name="member1" value="shk1234" />
	<jsp:setProperty property="pw" name="member1" value="1234" />
	<jsp:setProperty property="email" name="member1" value="suku0781@naver.com" />
	<% out.print(member1); %>
	<% out.print(member1.toString()); %>
	
<!-- 	useBean으로 생성된 Bean객체에 속성값을 얻어오기(getter 호출) -->
	<div>아이디 : <jsp:getProperty property="id" name="member1" /></div>
	<div>이메일 : <jsp:getProperty property="email" name="member1" /></div>
</body>
</html>