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
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		int fileUploadYn = -1;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo > -1) {
			result = setBoard(tmpBoard, con, lastNo);
			if(result == 1) {
				insertUploadedFileInfo(uf, con, lastNo);
				con.commit();
			} else {
				con.rollback();
			}
		} else {
			con.rollback();
		}
		
		con.close();
		return result;
	}

	// 파일 입로드하지 않았을 경우
	@Override
	public int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
//		PreparedStatement pstmt = null;
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo > -1) {
			result = setBoard(tmpBoard, con, lastNo);
			if(result == 1) {
				con.commit();
			} else {
				con.rollback();
			}
		} else {
			con.rollback();
		}
		con.close();
		
		return result;
	}
	
	@Override
	public int insertUploadedFileInfo(UploadedFile uf, Connection con, int lastNo) throws NamingException, SQLException {
		int result = -1;
		
		String query = "insert into uploadedFile(originalFileName, ext, newFileName, size, boardNo) values(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, uf.getOriginalFileName());
		pstmt.setString(2, uf.getExt());
		pstmt.setString(3, uf.getNewFileName());
		pstmt.setLong(4, uf.getSize());
		pstmt.setInt(5, lastNo);

		result = pstmt.executeUpdate();

		pstmt.close();
		
		return result;
	}
	
	// 마지막 no+1 가져오기
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
	
	// 게시글 insert함수
	public int setBoard(Board tmpBoard, Connection con, int lastNo) throws SQLException {
		String query = "insert into board(writter, title, content, ref ) values(?, ?, ?, ?)";
		int result = -1;
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, tmpBoard.getWritter());
		pstmt.setString(2, tmpBoard.getTitle());
		pstmt.setString(3, tmpBoard.getContent());
		pstmt.setInt(4, lastNo);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

}
