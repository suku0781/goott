package com.miniproject.service.board;

import java.util.List;

import com.miniproject.domain.Board;
import com.miniproject.domain.UploadedFile;

public interface BoardService {

	// 모든 게시글 가져오기
	List<Board> getEntireBoard() throws Exception;

	// 게시글 저장
	void saveNewBoard(Board newBoard, List<UploadedFile> ufList) throws Exception;

}
