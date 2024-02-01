package com.miniproject.service.member;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;

public interface MemberService {
	boolean duplChkUserId();

	Member login(Login tmpMember) throws Exception;
}
