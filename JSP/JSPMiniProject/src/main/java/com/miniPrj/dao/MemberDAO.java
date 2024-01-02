package com.miniPrj.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.miniPrj.vo.Member;

public interface MemberDAO {
	// 아이디가 중복되는지 검사
	Member duplicateUserId(String userId) throws NamingException, SQLException;
}
