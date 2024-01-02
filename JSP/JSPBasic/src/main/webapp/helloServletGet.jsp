<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">

	function callServlet(){
		location.href="./hGET?name=김수혁";
	}
	
	function callServletByAjax() {
	      $.ajax({
	        url: "./hGET?name=김수혁", 
	        type: 'GET',
	        success : function (data){
	          console.log('success',data);
				document.getElementById("ajaxTest").innerHTML = data;
	        },
	        fail : function (data){
	          console.log('fail',data);
	        },

	      })
	    }
</script>
<body>
	<h1>Hello Servlet_GET 파일을 GET방식으로 호출해보기</h1>
	<div>
		<a href="./hGET?name=shk">a태그로 서블릿 요청</a>

		<form action='./hGET' method="GET">
			<input type="text" name="name" value="RHY">
			<button type="submit">form태그로 GET방식 요청</button>
		</form>
		
		<button onclick="callServlet()">location.href로 서블릿 요청</button>	<hr>	
		<button onclick="callServletByAjax()">Ajax로 서블릿 요청(GET방식)</button>		
		<div id="ajaxTest"></div>
	</div>
</body>
</html>