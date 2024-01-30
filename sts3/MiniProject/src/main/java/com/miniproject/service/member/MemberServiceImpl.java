package com.miniproject.service.member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.miniproject.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	@Inject
	MemberDAO dao;

	@Override
	public boolean duplChkUserId() {
//		dao.
		
		return false;
	}
	
	
}
