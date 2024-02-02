package com.miniproject.service.board;

import java.util.List;
import java.util.Map;

import com.miniproject.domain.Board;
import com.miniproject.domain.SearchCriteria;
import com.miniproject.domain.UploadedFile;

public interface BoardService {

	// 모든 게시글 가져오기
//	Map<String, Object> getEntireBoard(int pageNo) throws Exception;
	
	// 게시글 조회(pageInfo + 검색어)
	Map<String, Object> getEntireBoard(int pageNo, SearchCriteria sc) throws Exception;
	
	// 게시글 저장
	void saveNewBoard(Board newBoard, List<UploadedFile> ufList) throws Exception;

	// no번 게시글 가져오기
	Map getBoardByNo(int no, String ipAddr) throws Exception;

	// no를 받아서 해당 글의 작성자 정보를 가져오기
	String getBoardWritter(int no) throws Exception;
}
