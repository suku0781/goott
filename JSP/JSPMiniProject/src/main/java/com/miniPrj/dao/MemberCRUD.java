package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Member;

public class MemberCRUD implements MemberDAO {
	private static MemberCRUD instance = null;
	
	private MemberCRUD() {}
	
	public static MemberCRUD getInstance() {
		if(instance == null) instance = new MemberCRUD();
		return instance;
	}
	
	@Override
	public Member duplicateUserId(String userId) throws NamingException, SQLException {
		Member result = null;
		Connection con = DBConnection.getInstance().dbConnect();
		
		if(con != null) {
			String query = "select * from member where userId = ?";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = new Member(rs.getString("userId")
						, rs.getString("userPw")
						, rs.getString("userEmail")
						, rs.getDate("registDate")
						, rs.getInt("userImg")
						, rs.getInt("userPoint"));
						
			}
			
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return result;
	}
	
	@Override
	public Member duplicateUserEmail(String userEmail) throws NamingException, SQLException {
		Member result = null;
		Connection con = DBConnection.getInstance().dbConnect();
		if(con != null) {
			String query = "select * from member where userEmail = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				result = new Member(rs.getString("userId")
						, rs.getString("userPw")
						, rs.getString("userEmail")
						, rs.getDate("registDate")
						, rs.getInt("userImg")
						, rs.getInt("userPoint"));
			}
			
			DBConnection.dbClose(rs, pstmt, con);
			
		}
		return result;
	}

	@Override
	public int registerMemberWithFile(UploadedFile uf, Member member, String why, int howMuch) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		int no = -1;
		int insertCnt = -1;
		int logCnt = -1;
		int result = -1;
		
		con.setAutoCommit(false);
		
//		1. 업로드된 파일이 있다면 파일의 정보를 uploadedFile테이블에 insert 한다. 
		no = insertUploadedFileInfo(uf, con);
//		2. 회원가입(순수한 회원의 데이터를 저장하고 1.에서 저장된 no(PK)를 userImg에 저장)
		if(no != -1) {
			member.setUserImg(no);
			insertCnt = insertMember(member, con); // 1이면 insert성공
		}
//		3. 회원가입 포인트 100점 부여.
		if(insertCnt == 1) {
			logCnt = insertPointLog(why, howMuch, member.getUserId(), con);
			
		}
//		4. pointLog테이블에 회원가입 포인트 로그를 남겨야한다. 
		if(no != -1 && insertCnt != -1 && logCnt != -1) {
			con.commit();
			result = 0; // 트랜잭션 성공했을때 0 반환
		} else {
			con.rollback();
		}
		
		con.close();
		
		return result;
	}

	@Override
	public int registerMemberWithFile(Member member, String why, int howMuch) throws NamingException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertUploadedFileInfo(UploadedFile uf, Connection con) throws NamingException, SQLException {
		int result = -1;
		String query = "insert into uploadedFile(originalFileName, ext, newFileName, size) values(?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, uf.getOriginalFileName());
		pstmt.setString(2, uf.getExt());
		pstmt.setString(3, uf.getNewFileName());
		pstmt.setLong(4, uf.getSize());
		
		pstmt.executeUpdate(); 
		result = getUploadedFileNo(uf, con); // 현재 업로드된 파일의 저장번호(no)
		
		pstmt.close();
//		con.close();
		return result;
	}

	@Override
	public int insertMember(Member member, Connection con) throws NamingException, SQLException {
//		2. 회원가입(순수한 회원의 데이터를 저장하고 1.에서 저장된 no(PK)를 userImg에 저장)
//		3. 회원가입 포인트 100점 부여.
		int result = -1;
		String query = "insert into member(userId, userPw, userEmail, userImg, userPoint) values(?, sha1(md5(?)), ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, member.getUserId());
		pstmt.setString(2, member.getUserPw());
		pstmt.setString(3, member.getUserEmail());
		pstmt.setInt(4, member.getUserImg());
		pstmt.setInt(5, member.getUserPoint());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
//		con.close();
		
		return result;
	}

	@Override
	public int insertPointLog(String why, int howMuch, String userId, Connection con) throws NamingException, SQLException {
		int result = -1;
		String query = "insert into pointLog(why, howMuch, who) values(?, ?, ?);";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, why);
		pstmt.setInt(2, howMuch);
		pstmt.setString(3, userId);
		result = pstmt.executeUpdate();
		
		pstmt.close();
//		con.close();
		
		return result;
	}
	
	// 업로드된 파일의 저장번호(no)를 반환하는 함수	
	private int getUploadedFileNo(UploadedFile uf, Connection con) throws SQLException {
		int result = -1;
		String query = "select no from uploadedFile where newFileName = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, uf.getNewFileName());
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = rs.getInt("no");
		}
		
		return result;
	}



}
