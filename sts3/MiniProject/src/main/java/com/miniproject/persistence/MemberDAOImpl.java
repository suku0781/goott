package com.miniproject.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static String ns = "com.miniproject.mappers.memberMapper";
	
	@Inject
	private SqlSession ses;
	
		
	@Override
	public String getDate() {
		String q = ns + ".curDate";
		
		return ses.selectOne(q);
	}

}
