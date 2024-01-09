package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Board;

public interface BoardDAO {

	// 전체 게시판 글 목록
	List<Board> selectAllBoard() throws NamingException, SQLException;

	// 게시판 글 저장(업로드 파일이 있는 경우)
	int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf) throws NamingException, SQLException;
	
	// 게시판 글 저장(업로드 파일이 없는 경우)
	int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException;

	// 업로드된 파일 정보를 uploadedFile테이블에 insert
	int insertUploadedFileInfo(UploadedFile uf, Connection con, int lastNo) throws NamingException, SQLException;
	
	// 게시글 가져오기
	Board selectBoard(int no) throws NamingException, SQLException;

	// 조회수 처리
	// readCountProcess테이블에 ip주소와 no(글번호)가 있는지 확인
	boolean selectReadCountProcess(String userIp, int no) throws NamingException, SQLException;

	// 24시간이 지났는지 확인(시간차이)
	int selectHourDiff(String userIp, int no)throws NamingException, SQLException;

	// 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert 하거나 update하는 메서드 
	void readCountProcessWithReadCntInc(String userIp, int no, String string) throws NamingException, SQLException;

}
