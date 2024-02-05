package com.miniproject.service.member;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;
import com.miniproject.domain.Session;

public interface MemberService {
	boolean duplChkUserId();

	Member login(Login tmpMember) throws Exception;

	// member테이블에 유저 정보를 저장
	boolean remember(Session session) throws Exception;
	
	// 자동 로그인 유저인지 확인
	Member checkAutoLoginUser(String cookieValue) throws Exception; 
}
