<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="../header.jsp"></jsp:include>
	
	<div class="container">
		<h1>register.jsp</h1>
		
		<form action="/registerMember.mem" method="POST">
			<div class="mb-3 mt-3">
				<label for="userId" class="form-label">ID</label> 
				<input type="text" class="form-control" id="userId" name="userId" placeholder="Input your ID" >
			</div>
			<div class="mb-3 mt-3">
				<label for="userPw" class="form-label">PW</label> 
				<input type="password" class="form-control" id="userPw" name=userPw placeholder="Input your PassWord" >
			</div>
			<div class="mb-3 mt-3">
				<label for="userPwRepeat" class="form-label">PW-repeat</label> 
				<input type="password" class="form-control" id="userPwRepeat" placeholder="Input your PassWord Repeat" >
			</div>
			<div class="mb-3 mt-3">
				<label for="userEmail" class="form-label">Email</label> 
				<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="Input your Email" >
				<button type="button" class="btn btn-secondary">Email Authentication</button>
			</div>
			
			<div class="mb-3 mt-3">
				<label for="userImg" class="form-label">Image</label> 
				<input type="file" class="form-control" id="userImg" name="userImg" placeholder="Input your Image" >
			</div>

			<div class="form-check mb-3">
				<label for="agree" class="form-check-label">Agree to terms and conditions</label>
				<input class="form-check-input" type="checkbox" id="agree" name="agree" value="Y">
				
			</div>

			<button type="submit" class="btn btn-primary" onclick="return validation();">Submit</button>
			<button type="reset" class="btn btn-danger">Cancel</button>
		</form>
	</div>
		
	<jsp:include page="../footer.jsp"></jsp:include>	
</body>
<script type="text/javascript">
	function getData(url, type, data, dataType){
		let result = false;
		$.ajax({
			url : url,
			type : type || "get",
			data : data || null,
			dataType : dataType || json,
			success : function(data){
				if(data.target == "userIdDuplChk"){
					if(data.isDuplicatee == "true"){
						printErrMsg("userId", "Input ID is Unusable. Id is Duplicated", true);
					} else {
						printErrMsg("userId", "Input ID is Usable.", false);
						result = true;
					}
				}
			},
			fail: function(data){
				
			}
		})
		return result;
	}

	$(function(){
		// 아이디 작성을 마쳤을 때 이벤트
		$("#userId").blur(function(){
			validUserId();
		});
		
		
	}) // end of doc

	// 아이디 유효성검사 : 3자 이상 ~ 8자 이하 영문 + 숫자조합 
	function validUserId(){
		let isValid = false;
		let userId = $("#userId").val();
		if(userId.length > 2 && userId.length < 9){
			// 아이디 중복검사
			let obj = {};
			obj.userId = userId;
			
			return getData("dulpUserId.mem", "GET", obj, "json")
		} else {
			printErrMsg("userId", "아이디는 3자 이상 ~ 8자 이하 영문 + 숫자조합으로 입력하십시오.", true); // function printErrMsg(id, msg, isFocus)
			return false;
		}
		
	}

	// 회원가입 유효성 검사
	function validation(){
		let isValid = false;
		
		let userIdValid = validUserId();
		
		return isValid;
		
	}
	
	// 에러메시지 출력
	function printErrMsg(id, msg, isFocus){
		let errMsg = `<div class="errMsg">\${msg}</div>`;
		$(errMsg).insertAfter($(`#\${id}`)).parent();
		if(isFocus) $(`#\${id}`).focus();
		
		$('.errMsg').hide(2000);
	}
</script>
<style>
	.errMsg{
		color: red;
		font-size: 14px;
		font-weight: bold;
	}
</style>
</html>