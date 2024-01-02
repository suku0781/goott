<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL로 표현가능한 데이터</title>
</head>
<body>
<div>정수형 : ${10 }</div>
<div>실수형 : ${3.14 }</div>
<div>문자열 : ${"오늘너무춥당"}</div>
<div>논리형 : ${1-2==3}</div>
<div>null : ${null}</div> <%--  null은 아무것도 안찍힌다. --%>


</body>
</html>