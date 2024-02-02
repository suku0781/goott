package com.miniproject.service.member;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;
import com.miniproject.domain.Session;

public interface MemberService {
	boolean duplChkUserId();

	Member login(Login tmpMember) throws Exception;

	// member테이블에 유저 정보를 저장
	boolean remember(Session session) throws Exception;
}
