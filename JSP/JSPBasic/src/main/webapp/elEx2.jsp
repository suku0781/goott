<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL로 사용가능한 데이터</title>
	<div> \${ 5+2 } : ${ 5+2 }</div> <%-- EL앞에 \를 붙이면 EL이 동작하는것을 막는다. --%>
	<div> \${ 5/2 } : ${ 5/2 }</div>
<%-- 	<div> \${ 5 div 2 } : ${ 5 div 2 }</div> 빨간줄이 가지만 되긴한다. --%>
	<div> \${ 5 mod 2 } : ${ 5 mod 2 }</div>
	<div> \${ 5 % 2 } : ${ 5 % 2 }</div>
	<div> \${ 5 > 2 } : ${ 5 > 2 }</div>
<%-- 	<div> \${ 5 gt 2 } : ${ 5 gt 2 }</div< --%>
	<div> \${ 5 lt 2 } : ${ 5 lt 2 }</div>
	<div> \${ 5 le 2 } : ${ 5 le 2 }</div>
	<div> \${ 5 gt 2 and 3 lt 4 } : ${ 5 gt 2 and 3 lt 4 }</div>
	<div> \${ 5 gt 45 ? true : false } : ${ 5 gt 45 ? true : false }</div>
	<% String input = null; %>
	<div> \${ empty input } : ${ empty input }</div>
 
</head>
<body>

</body>
</html>