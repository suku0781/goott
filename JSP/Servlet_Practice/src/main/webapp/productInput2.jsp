<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form action="./test" method="get">
		<input type="date" id="currentDate" name="date">
		<input type="text" id="currentDate" name="text">
		<input type="submit">
	</form>
</body>
<script>
  document.getElementById('currentDate').value = new Date().toISOString().substring(0, 10);;
</script>
</html>