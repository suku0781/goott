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

	private MemberCRUD() {
	}

	public static MemberCRUD getInstance() {
		if (instance == null)
			instance = new MemberCRUD();
		return instance;
	}

	@Override
	public Member duplicateUserId(String userId) throws NamingException, SQLException {
		Member result = null;
		Connection con = DBConnection.getInstance().dbConnect();

		if (con != null) {
			String query = "select * from member where userId = ?";

			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userId);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = new Member(rs.getString("userId"), rs.getString("userPw"), rs.getString("userEmail"),
						rs.getDate("registDate"), rs.getInt("userImg"), rs.getInt("userPoint"));

			}

			DBConnection.dbClose(rs, pstmt, con);
		}
		
//		System.out.println("resultTEST"+result);

		return result;
	}

	@Override
	public Member duplicateUserEmail(String userEmail) throws NamingException, SQLException {
		Member result = null;
		Connection con = DBConnection.getInstance().dbConnect();
		if (con != null) {
			String query = "select * from member where userEmail = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, userEmail);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result = new Member(rs.getString("userId"), rs.getString("userPw"), rs.getString("userEmail"),
						rs.getDate("registDate"), rs.getInt("userImg"), rs.getInt("userPoint"));
			}

			DBConnection.dbClose(rs, pstmt, con);

		}
		return result;
	}

	@Override
	public int registerMemberWithFile(UploadedFile uf, Member member, String why, int howMuch)
			throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		int no = -1;
		int insertCnt = -1;
		int logCnt = -1;
		int result = -1;

		con.setAutoCommit(false);

//		1. 업로드된 파일이 있다면 파일의 정보를 uploadedFile테이블에 insert 한다. 
		no = insertUploadedFileInfo(uf, con);
//		2. 회원가입(순수한 회원의 데이터를 저장하고 1.에서 저장된 no(PK)를 userImg에 저장)
		if (no != -1) {
			member.setUserImg(no);
			insertCnt = insertMember(member, con); // 1이면 insert성공
		}
//		3. 회원가입 포인트 100점 부여.
		if (insertCnt == 1) {
			logCnt = insertPointLog(why, howMuch, member.getUserId(), con);

		}
//		4. pointLog테이블에 회원가입 포인트 로그를 남겨야한다. 
		if (no != -1 && insertCnt != -1 && logCnt != -1) {
			con.commit();
			result = 0; // 트랜잭션 성공했을때 0 반환
		} else {
			con.rollback();
		}

		con.close();

		return result;
	}

	@Override
	public int registerMember(Member member, String why, int howMuch) throws NamingException, SQLException {
		 Connection con = DBConnection.getInstance().dbConnect();
	      con.setAutoCommit(false);
	      int result = -1;
	      
	      if (con != null) {
//	      업로드 된 파일이 없는 경우
//	      (1) 회원 가입(순수한 회원의 데이터를 저장 + userImg에 default(1)이 저장) + 회원가입 (100포인트) 포함
	         member.setUserPoint(howMuch);
	         int insertCnt = insertMember(member, con, false);

//	      (2) 회원 가입이 완료된 경우 => pointlog 테이블에 회원 가입 포인트 로그를 남겨야 함
	          int logCnt = -1;
	          
	          if (insertCnt == 1) {
	             logCnt = insertPointLog(why, howMuch, member.getUserId(), con);
	          }
	          
	          if (insertCnt == 1 && logCnt == 1) {
	             con.commit();
	             result = 0;
	          } else {
	             con.rollback();
	          }
	          
	          con.setAutoCommit(true);
	          con.close();
	     }
	       
       return result;
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
	public int insertMember(Member member, Connection con, boolean userImg) throws NamingException, SQLException {
//		오버로딩
//		2. 회원가입(순수한 회원의 데이터를 저장하고 1.에서 저장된 no(PK)를 userImg에 저장)
//		3. 회원가입 포인트 100점 부여.
		int result = -1;
		String query = "insert into member (userId, userPw, userEmail, userPoint) values (?, sha1(md5(?)), ?, ?)";
  
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, member.getUserId());
		ps.setString(2, member.getUserPw());
		ps.setString(3, member.getUserEmail());
		ps.setInt(4, member.getUserPoint());
  
		result = ps.executeUpdate();
  
		ps.close();
  
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

		while (rs.next()) {
			result = rs.getInt("no");
		}

		return result;
	}

	@Override
	public Member loginMember(String userId, String userPw) throws NamingException, SQLException {
		Member loginMember = null;
		Connection con = DBConnection.getInstance().dbConnect();
		
		String query = "select * from member m inner join uploadedfile u on m.userImg = u.no where userId = ? and userPw = sha1(md5(?))";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, userId);
		pstmt.setString(2, userPw);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			loginMember = new Member( 	rs.getString("userId"), 
										rs.getString("userPw"), 
										rs.getString("userEmail"), 
										rs.getDate("registDate"), 
										rs.getInt("userImg"),
										rs.getInt("userPoint"),
										rs.getString("isAdmin"),
										rs.getString("newFileName") );
		}
		
		DBConnection.dbClose(rs, pstmt, con);
		
		return loginMember;
	}

	@Override
	public int addPointToMember(String userId, String why, int howMuch) throws NamingException, SQLException  {
		// 멤버 포인트 업데이트
		int result = -1;
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		String query = "update member set userPoint = userPoint + ? where userId = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, howMuch);
		pstmt.setString(2, userId);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		if(result == 1) {
			// pointLog에 기록 남기기
			int afterPointLog = insertPointLog(why, howMuch, userId, con);
			if(afterPointLog == 1) {
				con.commit();
				result = 0;
			} else {
				con.rollback();
			}
		} else {
			con.rollback();
		}
		con.setAutoCommit(true);
		
		con.close();
		return result;
	}

}
