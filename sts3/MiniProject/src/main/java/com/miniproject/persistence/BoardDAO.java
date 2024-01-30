package com.miniproject.persistence;

import java.util.List;

import com.miniproject.domain.Board;
import com.miniproject.domain.ReadCountProcess;
import com.miniproject.domain.SearchCriteria;
import com.miniproject.domain.UploadedFile;
import com.miniproject.etc.PagingInfo;

public interface BoardDAO {
	// 전체 게시글 조회 
	List<Board> selectAllBoard(PagingInfo pi) throws Exception;

	// 게시글 저장
	int insertNewBoard(Board newBoard) throws Exception;

	// boardNo 조회
	int selectBoardNo();

	// 파일 정보 저장
	int insertUploadedFile(UploadedFile file, int boardNo) throws Exception;

	// 해당 아이피 주소와 글번호 같은 것들이 있는지 체크
	ReadCountProcess selectReadCountProcess(int no, String ipAddr) throws Exception;

	// 현지시간과 저장된 시간 차이 조회
	int getHourDiffReadTime(int no, String ipAddr) throws Exception;

	// readCountProcess 테이블에서 시간을 현재시간으로 설정
	int updateReadCountProcess(ReadCountProcess readCountProcess) throws Exception;

	// 조회수 1 증가
	int updateReadCount(int no) throws Exception;

	// readCountProcess 테이블에 insert
	int insertReadCountProcess(ReadCountProcess readCountProcess) throws Exception;

	// no 로 게시글 조회
	Board selectBoardByNo(int no) throws Exception;

	// no번 글에 업로드한 파일 조회
	List<UploadedFile> selectUploadedFile(int no) throws Exception;

	// 전체게시글 수 조회
	int getTotalPostCnt() throws Exception;

	// 검색어 갯수 조회
	int getBoardCntWithSearch(SearchCriteria sc) throws Exception;
	
	//
	List<Board> selectAllBoard(PagingInfo pi, SearchCriteria sc) throws Exception;

}
