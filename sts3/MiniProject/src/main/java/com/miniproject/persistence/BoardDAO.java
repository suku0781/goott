package com.miniproject.persistence;

import java.util.List;

import com.miniproject.domain.Board;
import com.miniproject.domain.UploadedFile;

public interface BoardDAO {
	// 전체 게시글 조회 
	List<Board> selectAllBoard() throws Exception;

	// 게시글 저장
	int insertNewBoard(Board newBoard) throws Exception;

	// boardNo 조회
	int selectBoardNo();

	// 파일 정보 저장
	int insertUploadedFile(UploadedFile file, int boardNo) throws Exception;

}
