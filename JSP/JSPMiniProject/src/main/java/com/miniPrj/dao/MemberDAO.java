package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Member;
import com.miniPrj.vo.PointLog;

public interface MemberDAO {
	// ���̵� �ߺ��Ǵ��� �˻�
	Member duplicateUserId(String userId) throws NamingException, SQLException;
	
	// �̸����� �ߺ��Ǵ��� �˻�
	Member duplicateUserEmail(String userEmail) throws NamingException, SQLException;
	
	// ���ε�� ������ �ִ� ��� ȸ������ (1)Ʈ������� ����.
	int registerMemberWithFile(UploadedFile uf, Member member, String why, int howMuch) throws NamingException, SQLException;

	// ���ε�� ������ ���� ��� ȸ������
	int registerMember(Member member, String why, int howMuch) throws NamingException, SQLException;
	// ----- (1)�ϳ��� Ʈ����� -----
	// ���ε�� ���� ������ uploadedFile���̺� insert
	int insertUploadedFileInfo(UploadedFile uf, Connection con) throws NamingException, SQLException;
	
	// ȸ������ insert
	int insertMember(Member member, Connection con) throws NamingException, SQLException;
	int insertMember(Member member, Connection con, boolean userImg) throws NamingException, SQLException;
	
	// ȸ�������� �Ϸ�� �� pointLog���̺� ȸ����������Ʈ �α׸� ����.
//	int insertPointLog(String why, String howMuch, String userId, Connection con) throws NamingException, SQLException;
	int insertPointLog(String why, int howMuch, String userId, Connection con) throws NamingException, SQLException;
	// ----- (1)�ϳ��� Ʈ����� end-----

	// �α���
	Member loginMember(String userId, String userPw) throws NamingException, SQLException;

	// member����Ʈ ������Ʈ
	int addPointToMember(String userId, String why, int howMuch) throws NamingException, SQLException;

	// �ش� ���̵� ��� ���� ��������
	Member getMemberInfo(String userId) throws SQLException, NamingException;
	
	// �ش� ��� ����Ʈ ��� ��������
	List<PointLog> getPointLog(String userId) throws SQLException, NamingException;
}
