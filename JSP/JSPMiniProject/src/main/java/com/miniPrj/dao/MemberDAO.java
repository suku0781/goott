package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Member;
import com.miniPrj.vo.PointLog;

public interface MemberDAO {
	// 아이디가 중복되는지 검사
	Member duplicateUserId(String userId) throws NamingException, SQLException;
	
	// 이메일이 중복되는지 검사
	Member duplicateUserEmail(String userEmail) throws NamingException, SQLException;
	
	// 업로드된 파일이 있는 경우 회원가입 (1)트랜잭션을 토함.
	int registerMemberWithFile(UploadedFile uf, Member member, String why, int howMuch) throws NamingException, SQLException;

	// 업로드된 파일이 없는 경우 회원가입
	int registerMember(Member member, String why, int howMuch) throws NamingException, SQLException;
	// ----- (1)하나의 트랜잭션 -----
	// 업로드된 파일 정보를 uploadedFile테이블에 insert
	int insertUploadedFileInfo(UploadedFile uf, Connection con) throws NamingException, SQLException;
	
	// 회원정보 insert
	int insertMember(Member member, Connection con) throws NamingException, SQLException;
	int insertMember(Member member, Connection con, boolean userImg) throws NamingException, SQLException;
	
	// 회원가입이 완료된 후 pointLog테이블에 회원가입포인트 로그를 남김.
//	int insertPointLog(String why, String howMuch, String userId, Connection con) throws NamingException, SQLException;
	int insertPointLog(String why, int howMuch, String userId, Connection con) throws NamingException, SQLException;
	// ----- (1)하나의 트랜잭션 end-----

	// 로그인
	Member loginMember(String userId, String userPw) throws NamingException, SQLException;

	// member포인트 업데이트
	int addPointToMember(String userId, String why, int howMuch) throws NamingException, SQLException;

	// 해당 아이디 멤버 정보 가져오기
	Member getMemberInfo(String userId) throws SQLException, NamingException;
	
	// 해당 멤버 포인트 기록 가져오기
	List<PointLog> getPointLog(String userId) throws SQLException, NamingException;
}
