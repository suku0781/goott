package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.service.board.GetBoardServiceByNo;
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
		
		String query = "select * from board where isDelete = 'n' order by ref desc, refOrder asc";
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
	
	// �Խ��� �� �ۼ� : ���� �Էε� �� ���
	@Override
	public int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf) throws NamingException, SQLException {
		// 1. �Խñ� ������ board���̸��� insert
		// 2. uploadedFile���̺� ���ε� ���������� insert(board ���̺��� no���� boardNo������ �߰�)
		// 3. �Խù� �ۼ��� ���� ����Ʈ �ο�
		// 4. pointLog�� ���
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		int saveFileYn = -1;
		boolean writePointYn = false;
		int fileUploadYn = -1;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo > -1) {
			result = insertBoard(tmpBoard, con, lastNo); // 1
			if(result == 1) {
				saveFileYn = insertUploadedFileInfo(uf, con, lastNo); // 2
				if(saveFileYn > -1) {
					writePointYn = MemberCRUD.getInstance().addPointToMember(tmpBoard.getWritter(), "�Խù��ۼ�", 2, con);	
					if(writePointYn) {
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
		
		con.setAutoCommit(true);
		
		con.close();
		return result;
	}

	// �Խ��� �� �ۼ� : ���� �Էε����� �ʾ��� ���
	@Override
	public int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException {
		// 1. �Խñ� ������ board���̸��� insert
		// 2. �Խù� �ۼ��� ���� ����Ʈ �ο�
		// 3. pointLog�� ���
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		int lastNo = -1;
		int result = -1;
		boolean writePointYn = false;
		
		lastNo = getLastBoardNo(con);
		
		if(lastNo > -1) {
			result = insertBoard(tmpBoard, con, lastNo);
			if(result == 1) {
				writePointYn = MemberCRUD.getInstance().addPointToMember(tmpBoard.getWritter(), "�Խù��ۼ�", 2, con);	
				if(writePointYn) {
					con.commit();
				} else {
					conRollback(con, lastNo);
				}
			} else {
				conRollback(con, lastNo);
			}
		} else {
			con.rollback();
		}
		con.close();
		
		con.setAutoCommit(true);
		
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
	public Board selectBoardByNo(int no) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		Board board = null;
		String query = "select * from board where no = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1,  no);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			board = new Board( rs.getInt("no"),
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
		}
		DBConnection.dbClose(rs, pstmt, con);
		
		return board;
	}
	
	// ������ boardNo+1 ��������
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
	
	// �Խñ� insert�Լ�
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
	
	// board ���̺� auto_increment����
	public int setAutoIncrement(int no, Connection con) throws SQLException {
		int result = -1;
		String query = "alter table board auto_increment = ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		
		pstmt.setInt(1,  no);
		result = pstmt.executeUpdate();
		pstmt.close();
		
		return result;
	}
	
	// con.rollback(), autoIncrement �ʱ�ȭ �Լ� 
	public void conRollback(Connection con, int lastNo) throws SQLException {
		con.rollback();	
		System.out.println("con.rollback() ��.");
		System.out.println(setAutoIncrement(lastNo, con) > -1 ? "auto-increment ��"+lastNo+"�� �缳����." : "");
	}

	// ----------------------��ȸ�� ó��------------------------------
	//�ش� ������ �ּҿ� �� ��ȣ�� ������ �ִ��� ������ �����ϴ� �Լ�
	@Override
	public boolean selectReadCountProcess(String userIp, int no) throws NamingException, SQLException {
		boolean result = false;
		Connection con = DBConnection.getInstance().dbConnect();
		String query = "select * from readcountprocess where boardNo = ? and ipAddr = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, no);
		pstmt.setString(2, userIp);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = true;
		}
		
		DBConnection.dbClose(rs, pstmt, con);
		
		return result;
	}

	// �ð��� 24�ð� �������� Ȯ�� 
	@Override
	public int selectHourDiff(String userIp, int no) throws NamingException, SQLException {
		int result = -1; // �ð� ����
		Connection con = DBConnection.getInstance().dbConnect();
		String query = "SELECT TIMESTAMPDIFF(hour, (select readTime from readcountprocess where ipAddr = ? and boardNo = ?) , now()) AS hourDiff";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, userIp);
		pstmt.setInt(2, no);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			result = rs.getInt("hourDiff");
		}
		
		DBConnection.dbClose(rs, pstmt, con);
		
		return result;
	}

	// �������ּҿ� �۹�ȣ�� �����ð��� readCountProcess���̺� update �Ǵ� insert
	// ��ȸ�� 1����(update)
	@Override
	public int readCountProcessWithReadCntInc(String userIp, int no, String type) throws NamingException, SQLException {
		int result = -1;
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		
		String query = "";
		
		if(type.equals("update")) {
			query = "update readCountProcess set readtime = now() where ipAddr = ? and boardNo = ?";
		} else if(type.equals("insert")) {
			query = "insert into readCountProcess(ipAddr, boardNo) values(?, ?)";
		}
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1, userIp);
		pstmt.setInt(2, no);
		result = pstmt.executeUpdate();
		
		// ��ȸ�� 1����(update)
		if(result == 1) {
			if(updateReadCount(no, con)) {
				result = 0;
				con.commit();
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

	// ��ȸ�� 1 ����
	private boolean updateReadCount(int no, Connection con) throws SQLException {
		boolean result = false;
		
		String query = "update board set readCount = readCount+1 where no = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, no);
		
		if(pstmt.executeUpdate() == 1) {
			result = true;
		}
		
		pstmt.close();
		
		return result;
	}

	// �Խ��� ÷������ ��������
	@Override
	public UploadedFile getFile(int no) throws NamingException, SQLException {
		UploadedFile result = null;
		Connection con = DBConnection.getInstance().dbConnect();
		String query = "select * from uploadedfile where boardNo = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1, no);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			result = new UploadedFile(	rs.getString("originalFileName"),
										rs.getString("ext"),
										rs.getString("newFileName"),
										rs.getInt("size"),
										rs.getInt("BoardNo"),
										rs.getString("base64String"));
		}
		
		DBConnection.dbClose(rs, pstmt, con);
		
		return result;
	}

	// �Խñ� ����
	@Override
	public int deleteBoard(int no) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		int result = -1;
		if(con != null) {
			String query = "update board set isDelete = 'y' where no = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1,  no);
			
			result = pstmt.executeUpdate();
			
			DBConnection.dbClose(pstmt, con);
		}
		
		return result;
	}

	// �Խñ� ����
	@Override
	public Board editBoard(int no) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		if(con != null) {
			String query = "";
			PreparedStatement pstmt = con.prepareStatement(query);
		}
		return null;
	}

	public int updateRefOrder(Board board, Connection con) throws NamingException, SQLException {
		int result = -1;
		
		String query = "update board set reforder = reforder + 1 where ref = ? and reforder > ?";
		
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1,  board.getRef());
		pstmt.setInt(2,  board.getRefOrder());;
		
		result = pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public boolean insertReplyTransaction(Board board) throws NamingException, SQLException {
		boolean result = false;
		
		Connection con = DBConnection.getInstance().dbConnect();
		con.setAutoCommit(false);
		int updateResult = updateRefOrder(board, con);
		
		if(updateResult >= 0) {
			if(insertReply(board, updateResult, con) == 1) {
				if(MemberCRUD.getInstance().addPointToMember(board.getWritter(), "����ۼ�", 1, con)) {
					result = true;
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
		
		con.setAutoCommit(true);
		con.close();
		
		return result;
	}

	private int insertReply(Board board, int updateResult, Connection con) throws SQLException {
		int result = -1;
		String tmpTitle = "("+board.getNo()+"������ "+ (updateResult+1) + "��° ���)" + board.getContent();
		
		String query = "insert into board(writter, content, ref, step, refOrder, title ) values(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1,  board.getWritter());
		pstmt.setString(2,  board.getContent());
		pstmt.setInt(3,  board.getRef());
		pstmt.setInt(4,  board.getStep()+1);
		pstmt.setInt(5,  board.getRefOrder()+1);
		pstmt.setString(6,  tmpTitle);
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	// ��� ��ü ��������
	@Override
	public List<Board> selectReplyBoard(int no) throws NamingException, SQLException {
		Connection con = DBConnection.getInstance().dbConnect();
		List<Board> lst = new ArrayList<>();
		String query = "select * from board where no != ref and ref = ?";
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1,  no);
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
}
