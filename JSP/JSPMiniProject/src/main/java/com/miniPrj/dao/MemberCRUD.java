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

//		1. ���ε�� ������ �ִٸ� ������ ������ uploadedFile���̺� insert �Ѵ�. 
		no = insertUploadedFileInfo(uf, con);
//		2. ȸ������(������ ȸ���� �����͸� �����ϰ� 1.���� ����� no(PK)�� userImg�� ����)
		if (no != -1) {
			member.setUserImg(no);
			insertCnt = insertMember(member, con); // 1�̸� insert����
		}
//		3. ȸ������ ����Ʈ 100�� �ο�.
		if (insertCnt == 1) {
			logCnt = insertPointLog(why, howMuch, member.getUserId(), con);

		}
//		4. pointLog���̺� ȸ������ ����Ʈ �α׸� ���ܾ��Ѵ�. 
		if (no != -1 && insertCnt != -1 && logCnt != -1) {
			con.commit();
			result = 0; // Ʈ����� ���������� 0 ��ȯ
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
//	      ���ε� �� ������ ���� ���
//	      (1) ȸ�� ����(������ ȸ���� �����͸� ���� + userImg�� default(1)�� ����) + ȸ������ (100����Ʈ) ����
	         member.setUserPoint(howMuch);
	         int insertCnt = insertMember(member, con, false);

//	      (2) ȸ�� ������ �Ϸ�� ��� => pointlog ���̺� ȸ�� ���� ����Ʈ �α׸� ���ܾ� ��
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
		result = getUploadedFileNo(uf, con); // ���� ���ε�� ������ �����ȣ(no)

		pstmt.close();
//		con.close();
		return result;
	}

	@Override
	public int insertMember(Member member, Connection con) throws NamingException, SQLException {
//		2. ȸ������(������ ȸ���� �����͸� �����ϰ� 1.���� ����� no(PK)�� userImg�� ����)
//		3. ȸ������ ����Ʈ 100�� �ο�.
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
//		�����ε�
//		2. ȸ������(������ ȸ���� �����͸� �����ϰ� 1.���� ����� no(PK)�� userImg�� ����)
//		3. ȸ������ ����Ʈ 100�� �ο�.
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

	// ���ε�� ������ �����ȣ(no)�� ��ȯ�ϴ� �Լ�
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
		// ��� ����Ʈ ������Ʈ
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
			// pointLog�� ��� �����
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
