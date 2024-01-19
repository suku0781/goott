package com.springbasic.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbasic.vo.Member;

@Repository // 아래의 클래스가 DAO객체임을 명시
public class MemberDAOImpl implements MemberDAO {
	private static String ns = "com.springbasic.mappers.memberMapper";
	
	@Inject
	private SqlSession ses; // SqlSessionTemplate객체 주입

	// 현재 시간 가져오는 추상메서드
	@Override
	public String getDate() {
		String q = ns + ".curDate";
		return ses.selectOne(q);
	}

	// userId로 멤버 정보 가져오기 
	@Override
	public Member selectMemberByUserId(String userId) {
		String q = ns + ".viewMemberByUserId";
		return ses.selectOne(q,  userId);
	}

	// member 정보 추가하기 
	@Override
	public void insertMember(Member member) {
		String q = ns + ".addMember";
		ses.insert(q, member);
	}

	@Override
	public List<Member> selectAllMember() {
		String q = ns + ".viewAllMember";
		
		return ses.selectList(q);
	}


}
