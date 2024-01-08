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
	int insertUploadedFileInfo(UploadedFile uf, Connection con) throws NamingException, SQLException;
	
}
