<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function registerValid() {
		console.log("test");    
  	}
</script>
<body>
<form action="./ValidPractice" style="border:1px solid #ccc" method="post">
    <div class="container">
      <h1>회원가입</h1>
      <p>아래의 항목을 기입해 주세요.</p>
      <hr>
  
      <label for="userId"><b>아이디</b></label>
      <input type="text" placeholder="Input Id" name="userId" id="userId">
	  <br>
      <label for="pwd"><b>비밀번호</b></label>
      <input type="password" placeholder="Input Password" name="pwd" id="pwd">
  	  <br>
      <label for="psw-repeat"><b>비밀번호 확인</b></label>
      <input type="password" placeholder="Repeat Password" name="psw-repeat" id="pwdRepeat">
  	  <br>
      <label for="email"><b>Email</b></label>
      <input type="text" placeholder="Input Email" name="email" id="email">
	  <br>
	  <label for="birthday"><b>생년월일</b></label>
      <input type="date" placeholder="Input Birthday" name="birthday" id="birthday">
	  <br>
      <label for="phoneNumber"><b>전화번호</b></label>
      <input type="text" placeholder="Input phoneNumber" name="phoneNumber" id="mobile">
      <br>
      <fieldset>
        <legend>성별</legend>
        <div class="form-check">
          <label class="form-check-label" for="male">남성</label>
          <input type="radio" class="form-check-input" id="male" name="gender" value="male">
        </div>
        <div class="form-check">
          <label class="form-check-label" for="female">여성</label>
          <input type="radio" class="form-check-input" id="female" name="gender" value="female">
        </div>
      </fieldset>

      <fieldset>
        <legend>취미</legend>
        <div class="form-check">
          <label class="form-check-label" for="check1">코딩</label>
          <input type="checkbox" class="form-check-input" value="coding" name="hobby" id="check1" value="something" checked>
        </div>
        <div class="form-check">
          <label class="form-check-label" for="check2">알고리즘 문제풀기</label>
          <input type="checkbox" class="form-check-input" value="studyAlgorithm" name="hobby" id="check2" value="something">
        </div>
        <div class="form-check">
          <label class="form-check-label" for="check3">자료구조 공부</label>
          <input type="checkbox" class="form-check-input" value="studyDataStructure" name="hobby" id="check3" >
        </div>
        <div class="form-check">
          <label class="form-check-label" for="check4">개발언어 공부</label>
          <input type="checkbox" class="form-check-input" value="studyLanguage" name="hobby" id="check4" >
        </div>
        <div class="form-check">
          <label class="form-check-label" for="check5">컴퓨터구조 공부</label>
          <input type="checkbox" class="form-check-input" value="studyComputerStructure" name="hobby" id="check5" >
        </div>
      </fieldset>

      <label for="job" class="form-label"><b>직업</b></label>
      <select class="form-select" id="job" name="job" multiple="multiple">
        <option>---직업선택---</option>
        <option>웹개발자</option>
        <option>앱개발자</option>
        <option>데이터베이스관리자</option>
        <option>퍼블리셔</option>
        <option>디자이너</option>
      </select>
      
      <div class="mb-3 mt-3">
        <label for="comment"><b>메모</b></label>
        <textarea id="memo" name="memo" class="form-control" cols="10" rows="10"></textarea>
      </div>

      <label>
        <input type="checkbox" name="agree" id="agree" style="margin-bottom:15px"> 이용약관 동의
      </label>


      <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>
  
      <div class="clearfix">
        <button type="reset" class="cancelbtn">Cancel</button>
        <button type="submit" class="signupbtn" onclick="console.log(registerValid())">Sign Up</button>
      </div>
    </div>
  </form>
</body>
</html>