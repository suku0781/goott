<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- useBean객체 생성 -->
<jsp:useBean id="member" class="com.jsp.dto.MemberDTO"></jsp:useBean>

<jsp:setProperty property="id" name="member"/>
<jsp:setProperty property="pw" name="member"/>
<jsp:setProperty property="email" name="member"/>
<jsp:setProperty property="birthday" name="member"/>
<jsp:setProperty property="phoneNumber" name="member"/>
<jsp:setProperty property="gender" name="member"/>


<div>아이디 : <jsp:getProperty property="id" name="member"/></div> 
<div>비밀번호 : <jsp:getProperty property="pw" name="member"/></div> 
<div>이메일 : <jsp:getProperty property="email" name="member"/></div> 
<div>생년월일 : <jsp:getProperty property="birthday" name="member"/></div> 
<div>전화번호 : <jsp:getProperty property="phoneNumber" name="member"/></div> 
<div>성별 : <jsp:getProperty property="gender" name="member"/></div> 

</body>
</html>