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
							
					<c:forEach var="board" items="${boardList }" >
						<tr id="board${board.no }" class="board" onclick="location.href='viewBoard?no=${board.no}&amp;page=boardDetail'">
							<td>${board.no }</td>
							<td class="isNewDpTarget">
							<c:if test="${board.step >= 0 }">
								<c:forEach var="i" begin="1" end="${board.step }" varStatus="status">
									<c:if test="${status.last }">
										<img alt="" src="${contextPath }/resources/img/down.png" width="10" style="margin-left:calc(10px * ${i});">
									</c:if>
								</c:forEach>
								${board.title }
							
							</c:if>
							</td>
							<td>${board.writter }</td>
							<td>${board.postDate }</td>
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
	<button type="button" class="btn btn-primary" onclick="location.href='writeBoard'">글쓰기</button>
	<div>
<%-- 	${requestScope.pagingInfo.totalPageCnt%10} --%>
<%-- 	${requestScope.pagingInfo} --%>
<%-- 	${boardList } --%>
	</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${param.pageNo == null || param.pageNo == 1}">
					<li class="page-item disabled"><a class="page-link" href="listAll?pageNo=${param.pageNo - 1}&amp;searchType=${param.searchType}&amp;searchWord=${param.searchWord}" tabindex="-1">Previous</a></li>	
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="listAll?pageNo=${param.pageNo - 1}&amp;searchType=${param.searchType}&amp;searchWord=${param.searchWord}" tabindex="-1">Previous</a></li>	
				</c:otherwise>
			</c:choose>
			<c:forEach var="i"  begin="${requestScope.pagingInfo.startNumOfCurrentPagingBlock }" end="${requestScope.pagingInfo.endNumOfCurrentPagingBlock }" >
				
<%-- 				<c:if test="${requestScope.pagingInfo.totalPageCnt / 10 gt 1 }"> --%>
					<c:choose>
						<c:when test="${requestScope.pagingInfo.pageNo eq i }">
							<li class="page-item active">
						</c:when>
						<c:otherwise>
							<li class="page-item">
						</c:otherwise>
					</c:choose>
						<a class="page-link" href="listAll?pageNo=${i}&amp;searchType=${param.searchType}&amp;searchWord=${param.searchWord}" tabindex="-1">${i}</a>
					</li>
<%-- 				</c:if> --%>
			</c:forEach>
			<c:choose>
				<c:when test="${requestScope.pagingInfo.pageBlockOfCurrentPage == requestScope.pagingInfo.totalPagingBlockCnt && param.pageNo == requestScope.pagingInfo.endNumOfCurrentPagingBlock}">
					<li class="page-item disabled"><a class="page-link" href="listAll?pageNo=${param.pageNo + 1}&amp;searchType=${param.searchType}&amp;searchWord=${param.searchWord}">Next</a></li>	
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="listAll?pageNo=${param.pageNo + 1}&amp;searchType=${param.searchType}&amp;searchWord=${param.searchWord}">Next</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<nav class="navbar navbar-light bg-light">
		<form class="form-inline">
			<select name="searchType">
				<option value="writter" selected>작성자</option>
				<option value="title">제목</option>
				<option value="content">내용</option>
			</select>
			<input class="form-control mr-sm-2" type="search" name="searchWord" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" onclick="return search()">Search</button>
		</form>
	</nav>
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
		
		function search(){
			let type = $("select[name=searchType]").val();
			let value = $("input[name=searchWord]").val();
			let sql = new Array("or", "select", "insert", "update", "delete", "create", "drop","exec", "union", "fetch", "declare", "truncate", "alter");
			
			let expText = /[%=><]/;
			let regExp = "";
			
			if(!value){
				alert("검색어를 입력하십시오.");
				return false;
			}
			if(expText.test(vlaue)){
				alert("특수문자를 사용할 수 없습니다.");
				return false;
			}
			for(let i = 0 ; i < sql.length ; i++){
				regExp = new RegExp(sql[i], "gi");
				if(regExp.test(value) == true){
					alert("특정 문자로 검색할 수 없습니다.");
					return false;
				}
			}
			
			location.href="listAll?searchType=writer&searchWord=김수혁";
		}
	</script>
</body>
</html>