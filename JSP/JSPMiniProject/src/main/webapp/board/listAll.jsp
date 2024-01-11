<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<style type="text/css">
.boardList{
	margin: 0 50px;
}
</style>
</head>
<body>
<c:set var="contextPath" value="<%=request.getContextPath() %>"></c:set>
<jsp:include page="../header.jsp"></jsp:include>
	<div class="boardList">
		<h1>listAll.jsp</h1>
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
							<th>좋아요</th>
							<th>ref</th>
							<th>step</th>
							<th>refOrder</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="board" items="${boardList }">
						<tr id="board${board.no }" class="board" onclick="location.href='viewBoard.bo?no=${board.no}&amp;page=boardDetail'">
							<td>${board.no }</td>
							<td class="isNewDpTarget">
							<c:if test="${board.step >= 0 }">
								<c:forEach var="i" begin="1" end="${board.step }">
									<img alt="" src="${contextPath }/img/down.png" width="10">
								</c:forEach>
								${board.title }
							
							</c:if>
							</td>
							<td>${board.writter }</td>
							<td class="isNewDpObj">${board.postDate }</td>
							<td>${board.readCount }</td>
							<td>${board.likeCount }</td>
							<td>${board.ref }</td>
							<td>${board.step }</td>
							<td>${board.refOrder }</td>
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
	<script type="text/javascript">
		$(function(){
			$(".isNewDpTarget").each((idx, item) => {
				if(getHowLong($(item).next().next().text())<5){
					item.innerHTML = `<span class="badge bg-danger">New</span>`+ " " +item.innerHTML
				}
			})
		})
	
		function getHowLong(d){
			if(!d) return;
			let regiDateTime = d.replaceAll("-", "/").substr(0, d.length-2);
			let today = new Date();
			let currentDateTime = new Date(today.getFullYear()+"/"+(today.getMonth() + 1)+"/"+today.getDate()+" "+today.getHours()+":"+today.getMinutes()+":"+today.getSeconds()); // 현재시간
			let targetDateTime = new Date(regiDateTime); // 타겟 시간
			
			return Math.abs(Math.floor((currentDateTime.getTime() - targetDateTime.getTime()) / (60 * 60 * 1000)))
		}
	</script>
</body>
</html>