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
				<div style="display:flex;">
					<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="Input your Email" >
					<button type="button" id="sendCode" class="btn btn-secondary sendCode">Auth</button>
				</div>
				<div class="codeDiv" style="display:none;">
					<input type="text" class="form-control" id="userEmailAuthInp" name="userEmailAuthInp" placeholder="Input Email Authentication Code" >
					<button type="button" class="btn btn-secondary confirmCode">submit</button>
				</div>
			</div>
			
			<div class="mb-3 mt-3">
				<label for="userImg" class="form-label">Image</label> 
				<input type="file" class="form-control" id="userImg" name="userImg" placeholder="Input your Image" >
			</div>

			<div class="form-check mb-3">
				<label for="agree" class="form-check-label">Agree to terms and conditions</label>
				<input class="form-check-input" type="checkbox" id="agree" name="agree" value="">
				
			</div>

			<button type="submit" class="btn btn-primary" onclick="return validation();">Submit</button>
			<button type="reset" class="btn btn-danger">Cancel</button>
		</form>
	</div>
		
	<jsp:include page="../footer.jsp"></jsp:include>	
</body>
<script type="text/javascript">
	let isEmailAuthYn = false;
	function getData(url, type, data, dataType, async){
		let result = false;
		$.ajax({
			url : url,
			type : type || "get",
			data : data || null,
			dataType : dataType || "json",
			async : async || true, 
			success : function(data){
				console.log(data)
				
				if(data.target == "userIdDuplChk"){
					if(data.isDuplicatee == "true"){
						printErrMsg("userId", "Input ID is Unusable. Id is Duplicated", true);
						result = true;
					} else {
						printErrMsg("userId", "Input ID is Usable.", false);
					}
				} else if(data.target == "authCode"){
					console.log("인증코드 발송됨.");
					
					if(data.status == "success"){
						
					} else {
						
					}
				} else if(data.target == "checkAuthCode"){
					console.log("인증코드 확인됨.");
					
					if(data.activation == 'success'){
						$(".codeDiv").hide(2000);
						$("#sendCode").text("Confirmed").attr("disabled", true);
						printErrMsg("userEmail", "Confirmed.", false);
						isEmailAuthYn = true;
					} else {
						
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
		
		// 비밀번호 확인 작성을 마쳤을 때 이벤트
		$("#userPwRepeat").blur(function(){
			validUserPW();
		});
		
		// 이메일 작성을 마쳤을 때 이벤트
		$("#userEmail").blur(function(){
			validUserEmail();
		});
		
		// 이메일 인증버튼 클릭시 이벤트
		$(".sendCode").click(function(){
			if(validUserEmail("sendCode")){
				console.log("sendCodeTest!");
				let userEmail = $("#userEmail").val();
				let obj = {};
				
				obj.userEmail = userEmail;
				
				getData("sendAuthMail.mem", "GET", obj, "json", false);
				
				$(".codeDiv").show();
			}
		});
		
		// 코드확인 버튼 클릭 시 
		$(".confirmCode").click(function(){
			let userEmailAuthInp = $("#userEmailAuthInp").val();
			let obj = {};
			
<%-- 			console.log(<%=(String) request.getSession().getAttribute("authCode")%>) --%>
			obj.userEmailAuthInp = userEmailAuthInp;
			getData("confirmCode.mem", "GET", obj, "json", false);
		});
		
	}) // end of doc

	// 아이디 유효성검사 : 3자 이상 ~ 8자 이하 영문 + 숫자조합 
	function validUserId(){
		let userId = $("#userId").val();
		if(userId.length > 2 && userId.length < 9){
			// 아이디 중복검사
			let obj = {};
			obj.userId = userId;
			
			return getData("dulpUserId.mem", "GET", obj, "json") ? false : true; // 아이디 중복검사 해서 중복이 아니면 true를 리턴하기위해서 반대로 설정함.
		} else {
			printErrMsg("userId", "아이디는 3자 이상 ~ 8자 이하 영문 + 숫자조합으로 입력하십시오.", true); // function printErrMsg(id, msg, isFocus)
			return false;
		}
		
	}
	
	// 비밀번호 유효성검사 : '영문 + 숫자 + 특수문자 조합으로 6~20자리를 사용해야 합니다.
	function validUserPW(){
		let isValid = false;
		let userPw = $("#userPw").val();
		let userPwRepeat = $("#userPwRepeat").val();
		
		if(userPw.length > 5 && userPw.length < 21){
			// 비밀번호 유효성 검사
			let pattern = /^[A-Za-z0-9`~!@#\$%\^&\*\(\)\{\}\[\]\-_=\+\\|;:'"<>,\./\?]{6,20}$/;
			if(pattern.test(userPw)){
				if(userPw == userPwRepeat){
					printErrMsg("userPwRepeat",'비밀번호가 일치합니다.', false);
					isValid = true;
				} else {
					printErrMsg("userPwRepeat",'비밀번호가 일치하지 않습니다.', false);
				}
			} else {
				printErrMsg("userPwRepeat",'비밀번호는 6자 이상 ~ 20자 이하 영문 + 숫자 + 특수문자 조합으로 입력하십시오.', false);
			}
		} else {
			printErrMsg("userPwRepeat",'비밀번호는 6자 이상 ~ 20자 이하 영문 + 숫자 + 특수문자 조합으로 입력하십시오.', false);
		}
		
		return isValid;
	}
	
	function validUserEmail(type){
		let userEmail = $("#userEmail").val();
		
		var pattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if(pattern.test(userEmail)){
			let obj = {};
			obj.userEmail = userEmail;
			
			
			return (type == "sendCode") ? true : getData("dulpUserEmail.mem", "GET", obj, "json");
		} else {
			printErrMsg("userEmail",'올바른 이메일을 입력하십시오.', false);
			return false;
		}
		
	}

	// 회원가입 유효성 검사
	function validation(){
		let isValid = false;
		let isAgree = $("#agree").prop("checked");
		
		if(validUserId() && validUserPW() && isEmailAuthYn){
			if(isAgree){
				printErrMsg("agree",'약관동의 체크해주십시오.', false);
				isValid = true;
			} else {
				isValid = false;
			}
		} else {
			isValid = false;
		}
		
		return isValid;
	}
	
	// 에러메시지 출력
	function printErrMsg(id, msg, isFocus){
		let errMsg = `<div class="errMsg">\${msg}</div>`;
		$(errMsg).insertAfter($(`#\${id}`)).parent();
		if(isFocus) $(`#\${id}`).focus() || $(`.\${id}`).focus();
		
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