package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Board;

public class BoardCRUD implements BoardDAO {
	private static BoardCRUD instance = null;
	
	private BoardCRUD() {}
	
	public static BoardCRUD getInstance() {
		if(instance == null) instance = new BoardCRUD();
		return instance;
	}

	@Override
	public List<Board> selectAllBoard() throws NamingException, SQLException{
		List<Board> lst = new ArrayList<>();
		Connection con = DBConnection.getInstance().dbConnect();
		
		String query = "select * from board order by no desc";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			lst.add( new Board( rs.getInt("no"),
					 rs.getString("writter"), 
					 rs.getString("title"), 
					 rs.getTimestamp("postDate"), 
					 rs.getString("content"),
					 rs.getInt("readCount"),
					 rs.getInt("likeCount"),
					 rs.getInt("ref"),
					 rs.getInt("step"),
					 rs.getInt("reforder"),
					 rs.getString("reforder") ) );
		}
		DBConnection.dbClose(rs, pstmt, con);

		return lst;
	}

	// 게시판 글 작성
	@Override
	public int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		int no = -1;
		
		return 0;
	}

	// 파일 입로드하지 않았을 경우
	@Override
	public int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		PreparedStatement pstmt = null;
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo != -1) {
			String query = "insert into board(writter, title, content, ref ) values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, tmpBoard.getWritter());
			pstmt.setString(2, tmpBoard.getTitle());
			pstmt.setString(3, tmpBoard.getContent());
			pstmt.setInt(4, lastNo);
			
			result = pstmt.executeUpdate();
			con.commit();
		} else {
			con.rollback();
		}
		pstmt.close();
		con.close();
		
		return result;
	}
	
	@Override
	public int insertUploadedFileInfo(UploadedFile uf, Connection con) throws NamingException, SQLException {
		int result = -1;
		String query = "insert into uploadedFile(originalFileName, ext, newFileName, size, boardNo) values(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, uf.getOriginalFileName());
		pstmt.setString(2, uf.getExt());
		pstmt.setString(3, uf.getNewFileName());
		pstmt.setLong(4, uf.getSize());
		pstmt.setLong(5, uf.getSize());

		pstmt.executeUpdate();
		result = getUploadedFileNo(uf, con); // 현재 업로드된 파일의 저장번호(no)

		pstmt.close();
//		con.close();
		return result;
	}
	
	public int getLastBoardNo(Connection con) throws SQLException {
		int result = -1;
		String query = "select max(no)+1 from board";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = rs.getInt(1);
		}
		
		pstmt.close();
		rs.close();
		
		return result;
	}

}
