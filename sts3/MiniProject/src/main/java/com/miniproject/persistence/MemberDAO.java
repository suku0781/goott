package com.miniproject.persistence;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;

public interface MemberDAO {

	String getDate();

	// member테이블에 userPoijnt를 update
	int updateUserPoint(String why, String writter) throws Exception;

	// 로그인하려는 유저 확인
	Member login(Login tmpMember) throws Exception;

}
