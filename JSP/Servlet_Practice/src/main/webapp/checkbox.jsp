<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
for(let i = 0 ; i < 10 ; i++) {
	const ranNum = (Math.random() * 10) - (i+1);
}
</script>
<body>
<!-- checkbox.jsp → input태그의 type=checkbox로 다중값(5개 정도)  입력받아서 get방식으로 (form태그 이용) paramValues.jsp로 데이터를 전송한 후 paramValues.jsp에서 forEach문을 사용하여 출력하시오.  -->
	<form action="paramValues.jsp">
		<input type="checkbox" name="item" value="1">
		<input type="checkbox" name="item" value="2">
		<input type="checkbox" name="item" value="3">
		<input type="checkbox" name="item" value="4">
		<input type="checkbox" name="item" value="5">
		<input type="checkbox" name="item" value="6">
		<input type="checkbox" name="item" value="7">
		<input type="checkbox" name="item" value="8">
		<input type="checkbox" name="item" value="9">
		<input type="checkbox" name="item" value="10">
		<input type="submit">
	</form>
</body>
</html>