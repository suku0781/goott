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
		<h1>boardDetail.jsp</h1>
<%-- 		<div>${boardList }</div> --%>
		<c:choose>
			<c:when test="${boardList != null }">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="board" items="${boardList }">
						<tr>
							<td>${board.no }</td>
							<td>${board.title }</td>
							<td>${board.writter }</td>
							<td>${board.postDate }</td>
							<td>${board.readCount }</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				게시글 없음.
			</c:otherwise>
		</c:choose>
	</div>
	<button type="button" class="btn btn-primary" onclick="location.href='writeBoard.jsp'">글쓰기</button>
	<jsp:include page="../footer.jsp"></jsp:include>	
</body>
</html>