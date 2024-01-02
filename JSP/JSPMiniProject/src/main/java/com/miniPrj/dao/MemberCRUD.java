package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

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
						, rs.getString("userImg")
						, rs.getInt("userPoint"));
						
		}
			
			DBConnection.dbClose(rs, pstmt, con);
		}
		
		return result;
	}

}
