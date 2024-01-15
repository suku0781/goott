package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.PagingInfo;
import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Board;
import com.miniPrj.vo.SearchCriteria;

public interface BoardDAO {

	// 전체 게시판 글 목록
	List<Board> selectAllBoard() throws NamingException, SQLException;
	List<Board> selectAllBoard(PagingInfo pi) throws NamingException, SQLException;
	List<Board> selectAllBoard(PagingInfo pi, SearchCriteria sc) throws NamingException, SQLException;
	
	// 게시판 글 저장(업로드 파일이 있는 경우)
	int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf) throws NamingException, SQLException;
	
	// 게시판 글 저장(업로드 파일이 없는 경우)
	int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException;

	// 업로드된 파일 정보를 uploadedFile테이블에 insert
	int insertUploadedFileInfo(UploadedFile uf, Connection con, int lastNo) throws NamingException, SQLException;
	
	// 조회수 처리
	// readCountProcess테이블에 ip주소와 no(글번호)가 있는지 확인
	boolean selectReadCountProcess(String userIp, int no) throws NamingException, SQLException;

	// 24시간이 지났는지 확인(시간차이)
	int selectHourDiff(String userIp, int no)throws NamingException, SQLException;

	// 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert 하거나 update하는 메서드 
	int readCountProcessWithReadCntInc(String userIp, int no, String type) throws NamingException, SQLException;
	
	// no번 게시글 가져오기
	Board selectBoardByNo(int no) throws NamingException, SQLException;

	// 게시판 첨부파일 가져오기
	UploadedFile getFile(int no) throws NamingException, SQLException;
	
	// 게시글 삭제하기
	int deleteBoard(int no) throws NamingException, SQLException;

	Board editBoard(int no) throws NamingException, SQLException;
	
	boolean insertReplyTransaction(Board board) throws NamingException, SQLException;

	List<Board> selectReplyBoard(int no) throws NamingException, SQLException;

	// 페이징 처리
	// 총 게시글 수 가져오기
	int getTotalPostCnt() throws NamingException, SQLException;
	
	// 검색어 처리
	// 게시글 중 검색어가 있을 때 총 게시글 가져오기
	int getTotalPostCnt(SearchCriteria sc) throws NamingException, SQLException;
	
	
}
