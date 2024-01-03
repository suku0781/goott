<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css" >
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/app.js"></script>
</head>
<body>
<form action="join.mem" method="POST">
	<div id="joinFormDiv">
		<input type="text" id="id" name="id" placeholder="id">
		<input type="password" id="pw" name="pw" placeholder="pw">
		<input type="password" id="pwRepeat" name="pwRepeat" placeholder="pw-repeat">
		<input type="text" id="email" name="email" placeholder="email">
		<input type="button" id="getAuthCode" name="getAuthCode" onclick="" value="인증">
		<div id="emailAuthDiv" style="display:none;">
			<input type="text" id="emailCodeInp" name="emailCodeInp" placeholder="emailCodeInp">
			<input type="button" id="emailCodeInp" name="emailCodeInp" value="확인">
				
		</div>
		
		<input type="submit" id="submitBtn">
		<input type="reset" id="resetBtn">
	</div>
</form>
</body>
</html>