package com.springbasic.persistence;

import java.util.List;

import com.springbasic.vo.Member;

public interface MemberDAO {
	
	// 현재 시간 가져오는 추상메서드
	String getDate();
	
	// userId로 멤버 정보 가져오기 
	Member selectMemberByUserId(String userId);
	
	// member 정보 추가하기 
	void insertMember(Member member);
	
	// 전체 member정보 가져오기
	List<Member> selectAllMember();
}
