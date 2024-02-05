package com.miniproject.persistence;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;
import com.miniproject.domain.Session;

public interface MemberDAO {

	String getDate();

	// member테이블에 userPoijnt를 update
	int updateUserPoint(String why, String writter) throws Exception;

	// 로그인하려는 유저 확인
	Member login(Login tmpMember) throws Exception;

	// 자동 로그인 정보를 저장
	int updateSession(Session session) throws Exception;

	// 자동로그인 멤버 확인
	Member selectAutoLoginUser(String sessinoKey) throws Exception;
}
