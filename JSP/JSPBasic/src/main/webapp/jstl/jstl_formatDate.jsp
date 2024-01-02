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
	<div><fmt:formatNumber value="123456.78"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="123456.78" groupingUsed="false"></fmt:formatNumber></div>
	
	<div><fmt:formatNumber value="0.574" type="percent"></fmt:formatNumber></div>
	
<!-- 	통화형식 지정 -->
	<div><fmt:formatNumber value="123456" type="currency"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="123456" type="currency" currencySymbol="$"></fmt:formatNumber></div>
	
	<hr>
	
<!-- 	#은 채워야 할 자리에 비해서 데이터가 모자랄 경우 아무것도 표시하지 않고 공백으로 표시 -->
	<div><fmt:formatNumber value="123456.78654" pattern="#,###.#"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="123456.78654" pattern="#,###.#"></fmt:formatNumber></div>
	<div><fmt:formatNumber value="123456.78654" pattern="#,###.#"></fmt:formatNumber></div>
	
	<hr>
<!-- 	0은 빈자리 만큼 0으로 채움. -->
	<div><fmt:formatNumber value="123.45678" pattern="###,###"></fmt:formatNumber></div>
	
</body>
</html>