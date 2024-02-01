package com.miniproject.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;

@Repository // DAO단임을 알림.
public class MemberDAOImpl implements MemberDAO {
	private static String ns = "com.miniproject.mappers.memberMapper";

	@Inject
	private SqlSession ses;

	@Override
	public String getDate() {
		String q = ns + ".curDate";

		return ses.selectOne(q);
	}

	@Override
	public int updateUserPoint(String why, String writter) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();

		param.put("why", why);
		param.put("userId", writter);

		return ses.update(ns + ".updateUserPoint", param);
	}

	@Override
	public Member login(Login tmpMember) throws Exception {
		return ses.selectOne(ns + ".login", tmpMember);
	}
}
