<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- *상품명 : <input type="text"> -->
<!-- *갯수 : <input type="number"> -->
<!-- *가격 : <input type="number"> -->
<h1>input Product Info</h1>
<form action="./inputProd.do" method="GET" name="prodFrom">
	<input type="text" name="prodName" placeholder="input Product Name">
	<input type="number" name="prodCnt" value=0>
	<input type="number" name="prodPrice" value=10000>
	<select name="prodOption">
		<option value="빨강">빨강</option>
		<option value="주황">주황</option>
		<option value="노랑">노랑</option>
		<option value="파랑">파랑</option>
	</select>
	<br>
	<input type="submit">
</form>
</body>
</html>