package com.miniPrj.dao;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.miniPrj.vo.Member;

public interface MemberDAO {
	// ���̵� �ߺ��Ǵ��� �˻�
	Member duplicateUserId(String userId) throws NamingException, SQLException;
}
