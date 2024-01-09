<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="boardList">
		<div>${board }</div>
		<div class="container">
		<h1>writeBoard.jsp</h1>
		
		<form action="" method="POST" enctype="multipart/form-data">
			<div class="mb-3 mt-3">
				<label for="writter" class="form-label">작성자</label> 
				<input type="text" class="form-control" id="writter" name="writter" value="${board.writter }" readonly>
			</div>
			<div class="mb-3 mt-3">
				<label for="title" class="form-label">제목</label> 
				<input type="text" class="form-control" id="title" name="title" placeholder="Input Title" value="${board.title }" >
			</div>
			<div class="mb-3 mt-3">
				<label for="content" class="form-label">내용</label> 
				<textarea type="text" class="form-control" id="content" name="content" placeholder="Input Context" rows="10" style="width:100%;">${board.content }</textarea>
			</div>
			<div class="mb-3 mt-3">
				<label for="upFile" class="form-label">첨부파일</label> 
				<input type="file" class="form-control" id="upFile" name="upFile" >
			</div>

			<button type="submit" class="btn btn-primary">글쓰기</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</form>
	</div>
	</div>
	<jsp:include page="../footer.jsp"></jsp:include>	
</body>
</html>