package com.miniproject.service.board;

import java.util.List;
import java.util.Map;

import com.miniproject.domain.Board;
import com.miniproject.domain.UploadedFile;

public interface BoardService {

	// 모든 게시글 가져오기
	List<Board> getEntireBoard() throws Exception;

	// 게시글 저장
	void saveNewBoard(Board newBoard, List<UploadedFile> ufList) throws Exception;

	// no번 게시글 가져오기
	Map getBoardByNo(int no, String ipAddr) throws Exception;

	// 게시글 좋아요
	void setLikeCount(int no, String ipAddr, String userId) throws Exception;

}
