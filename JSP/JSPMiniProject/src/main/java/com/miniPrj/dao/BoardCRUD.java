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
					 rs.getString("isDelete") ) );
		}
		DBConnection.dbClose(rs, pstmt, con);

		return lst;
	}
	
	// 게시판 글 작성 : 파일 입로드 한 경우
	@Override
	public int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf) throws NamingException, SQLException {
		// 1. 게시글 정보를 board테이르에 insert
		// 2. uploadedFile테이블에 업로드 파일정보를 insert(board 테이블의 no값을 boardNo값으로 추가)
		// 3. 게시물 작성에 대한 포인트 부여
		// 4. pointLog에 기록
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		int saveFileYn = -1;
		boolean writePointYn = false;
		int fileUploadYn = -1;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo > -1) {
			System.err.println("step1");
			
			result = insertBoard(tmpBoard, con, lastNo); // 1
			if(result == 1) {
				System.err.println("step2");
				saveFileYn = insertUploadedFileInfo(uf, con, lastNo); // 2
				if(saveFileYn > -1) {
					System.err.println("step3");
					writePointYn = MemberCRUD.getInstance().addPointToMember(tmpBoard.getWritter(), "게시물작성", 2, con);	
					if(writePointYn) {
						System.err.println("finish!");
						con.commit();
						
					} else {
						con.rollback();	
					}
				} else {
					con.rollback();
				}
			} else {
				con.rollback();
			}
		} else {
			con.rollback();
		}
		
		con.close();
		return result;
	}

	// 게시판 글 작성 : 파일 입로드하지 않았을 경우
	@Override
	public int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException {
		// 1. 게시글 정보를 board테이르에 insert
		// 2. 게시물 작성에 대한 포인트 부여
		// 3. pointLog에 기록
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		boolean writePointYn = false;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo > -1) {
			result = insertBoard(tmpBoard, con, lastNo);
			if(result == 1) {
				System.err.println("step2");
				System.out.println(setAutoIncrement(lastNo, con) > -1 ? "auto-increment 가"+lastNo+"로 재설정됨." : "");
				writePointYn = MemberCRUD.getInstance().addPointToMember(tmpBoard.getWritter(), "게시물작성", 2, con);	
				if(writePointYn) {
					System.err.println("finish!");
					con.commit();
					
				} else {
					conRollback(con, lastNo);
				}
				con.commit();
			} else {
				conRollback(con, lastNo);
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
		
		String query = "insert into uploadedFile(originalFileName, ext, newFileName, size, boardNo, base64String) values(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setString(1, uf.getOriginalFileName());
		pstmt.setString(2, uf.getExt());
		pstmt.setString(3, uf.getNewFileName());
		pstmt.setLong(4, uf.getSize());
		pstmt.setInt(5, lastNo);
		pstmt.setString(6, uf.getBase64String());

		result = pstmt.executeUpdate();

		pstmt.close();
		
		return result;
	}
	
	@Override
	public Board selectBoard(int no) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		Board board = null;
		UploadedFile uf = null;
		if(con != null) {
			String query = "select * from board b inner join uploadedFile u on b.no = u.boardNo where b.no = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				board =  new Board(  	rs.getInt("no"),
									 	rs.getString("writter"), 
									 	rs.getString("title"), 
									 	rs.getTimestamp("postDate"), 
									 	rs.getString("content"),
									 	rs.getInt("readCount"),
									 	rs.getInt("likeCount"),
									 	rs.getInt("ref"),
									 	rs.getInt("step"),
									 	rs.getInt("reforder"),
									 	rs.getString("isDelete") );
				uf = new UploadedFile(  rs.getString("originalFileName"),
										rs.getString("ext"), 
										rs.getString("newFileName"), 
										rs.getLong("size"), 
										rs.getInt("boardNo") , 
										rs.getString("base64String") );
			}
			
			DBConnection.dbClose(rs, pstmt, con);;
		}
		System.out.println("board : " + board.toString());
		
		return board;
	}
	
	// 마지막 boardNo+1 가져오기
	public int getLastBoardNo(Connection con) throws SQLException {
		int result = -1;
		String query = "select max(no)+1 as nextRef from board";
		PreparedStatement pstmt = con.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = rs.getInt("nextRef");
		}
		
		pstmt.close();
		rs.close();
		
		return result;
	}
	
	// 게시글 insert함수
	public int insertBoard(Board tmpBoard, Connection con, int lastNo) throws SQLException {
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
	
	// board 테이블 auto_increment설정
	public int setAutoIncrement(int no, Connection con) throws SQLException {
		int result = -1;
		String query = "alter table board auto_increment = ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setInt(1,  no);
		result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
	}
	
	public void conRollback(Connection con, int lastNo) throws SQLException {
		con.rollback();	
		System.out.println("con.rollback() 됨.");
		System.out.println(setAutoIncrement(lastNo, con) > -1 ? "auto-increment 가"+lastNo+"로 재설정됨." : "");
	}

	@Override
	public boolean selectReadCountProcess(String userIp, int no) throws NamingException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int selectHourDiff(String userIp, int no) throws NamingException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void readCountProcessWithReadCntInc(String userIp, int no, String string)
			throws NamingException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
