package com.miniproject.service.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.Board;
import com.miniproject.domain.PointLog;
import com.miniproject.domain.ReadCountProcess;
import com.miniproject.domain.SearchCriteria;
import com.miniproject.domain.UploadedFile;
import com.miniproject.etc.PagingInfo;
import com.miniproject.persistence.BoardDAO;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO dao;
	@Inject
	MemberDAO mDao;
	@Inject
	PointLogDAO plDao;
	
	@Override
	public Map<String, Object> getEntireBoard(int pageNo, SearchCriteria sc) throws Exception {
		PagingInfo pi = getPagingInfo(pageNo, sc);
		List<Board> lst = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(!sc.getSearchWord().equals("")) {
			lst = dao.selectAllBoard(pi, sc);
		} else {
			lst = dao.selectAllBoard(pi);
		}
		
		resultMap.put("boardList", lst);
		resultMap.put("pagingInfo", pi);
		
		return resultMap;
	}


	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveNewBoard(Board newBoard, List<UploadedFile> ufList) throws Exception {

//		새 게시글 저장 트랜잭션
//		1. board테이블에 글을 저장(insert)하고 그 후 파일을 저장해야한다. 
		if(dao.insertNewBoard(newBoard) == 1) { // insert되엇다면
			
			int boardNo = dao.selectBoardNo(); // boardNo최대값
			
//			2. 업로드파일이 있는경우
			if(ufList.size() > 0) { 
//				저장할때 insert된 글의 no값을 얻어와서 uploadedFile테이블에 파일정보를 저장(insert)한다. 
				for(UploadedFile file : ufList) {
					System.out.println("테이블에 저장될 uf : " + file.toString());
					dao.insertUploadedFile(file, boardNo);
				}
				System.out.println("boardNo : " + boardNo);
			}
			
//			3. member테이블에 userPoint를 pointPolicy대로 update한다.
			mDao.updateUserPoint("게시물작성", newBoard.getWritter());
			
//			4. pointLog테이블에 insert한다. 
			plDao.insertPointLog(new PointLog(-1, null, "게시물작성", 2, newBoard.getWritter()));
			
			
		}
		
//		이 중에서 하나라도 실패한다면 모두 롤백해야한다. (all or nothing)
	}

	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public Map getBoardByNo(int no, String ipAddr) throws Exception {
//		해당 아이피 주소와 글 번호가 같은게 있는지 없는지 찾아보기
//		해당 아이피 주소와 글번호 같은 것이 없다면
//		-> 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert
//		-> 해당 글 번호의 readCound를 증가(update)
//		-> 해당 글을 가져옴.(select)
//
//		해당 아이피 주소와 글번호 같은 것이 있다면
//
//		1. 시간이 24시간 지난 경우
//		-> 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 update
//		-> 조회수 1증가(update)
//		-> 해당 글을 가져옴.(select)
//		2. 시간이 24시간 지나지 않은 경우
//		-> 해당 글을 가져옴.(select)
//		
//		해당 아이피 주소와 글번호 같은 것이 없다면
//		-> 해당 글을 가져옴.(select)
//		-----------------------------------------------------------------------------------------
		int readCntResult = -1; 
		Map<String, Object> result = new HashMap<String, Object>();
		
		if(dao.selectReadCountProcess(no, ipAddr) != null) { // 조회한 적이 있을 경우
			System.out.println("시간차이 : "+dao.getHourDiffReadTime(no, ipAddr));
			if(dao.getHourDiffReadTime(no, ipAddr) > 23) { // 시간이 24시간이 지난 경우
//				ip주소와 글번호와 읽은 시간을 readCountProcess 테이블에서 update
				if(dao.updateReadCountProcess(new ReadCountProcess(-1, ipAddr, no, null)) == 1) {
//					 해당 글번호의 readCount 1 증가(update)
					readCntResult = dao.updateReadCount(no);
				}
			} else { // 시간이 24시간이 지나지 않은 경우
				readCntResult = 1;
			}
		} else { // 조회한 적이 없을 경우
			if(dao.insertReadCountProcess(new ReadCountProcess(-1, ipAddr, no, null)) == 1) {
//				 해당 글번호의 readCount 1 증가(update)
				readCntResult = dao.updateReadCount(no);
			}
		}
		
//		해당 글을 가져옴
		if(readCntResult == 1) {
			Board board = dao.selectBoardByNo(no);
			
			// 업로드한 파일정보 가져오기 
			List<UploadedFile> ufLst = dao.selectUploadedFile(no);
			System.out.println("업로드 파일 : "+ufLst);
			result.put("board", board);
			result.put("uploadedFileList", ufLst);
		}
		
		return result;
	}

	private PagingInfo getPagingInfo(int pageNo) throws Exception {
		PagingInfo result = new PagingInfo();
		
		// pageNo세팅
		result.setPageNo(pageNo);
		
		// 전체 게시글 수 세팅
		result.setTotalPostCnt(dao.getTotalPostCnt());
		
		System.out.println("전체 게시글 수 : "+dao.getTotalPostCnt());

		// 총 페이지 수 
		result.setTotalPageCnt(result.getTotalPostCnt(), result.getViewPostCntPerPage());
		
		// 보여주기 시작할 row Index번호 구하기 
		result.setStartRowIndex();
		
		// 전체 페이징 블럭 갯수
		result.setTotalPagingBlockCnt();
		
		// 현재 페이지가 속한 페이징 블럭 번호 
		result.setPageBlockOfCurrentPage();
		
		// 현재 페이징 블럭 페이지 시작 번호
		result.setStartNumOfCurrentPagingBlock();
		
		// 현재 페이징 블럭 페이지 끝 번호
		result.setEndNumOfCurrentPagingBlock();
		
		
		
		return result;
	}
	
	private PagingInfo getPagingInfo(int pageNo, SearchCriteria sc) throws Exception {
		PagingInfo result = new PagingInfo();
		
		// pageNo세팅
		result.setPageNo(pageNo);
		
		// 전체 게시글 수 세팅
		// 검색어가 있을 경우 
		if(!sc.getSearchWord().equals("")) {
			result.setTotalPostCnt(dao.getBoardCntWithSearch(sc));
			System.out.println("검색어 결과 총 갯수 : "+dao.getBoardCntWithSearch(sc));
		} else if(sc.getSearchWord().equals("")) { // 검색어가 없을 경우 
			result.setTotalPostCnt(dao.getTotalPostCnt());
		}
		
		result.setTotalPostCnt(dao.getTotalPostCnt());
		
		System.out.println("전체 게시글 수 : "+dao.getTotalPostCnt());

		// 총 페이지 수 
		result.setTotalPageCnt(result.getTotalPostCnt(), result.getViewPostCntPerPage());
		
		// 보여주기 시작할 row Index번호 구하기 
		result.setStartRowIndex();
		
		// 전체 페이징 블럭 갯수
		result.setTotalPagingBlockCnt();
		
		// 현재 페이지가 속한 페이징 블럭 번호 
		result.setPageBlockOfCurrentPage();
		
		// 현재 페이징 블럭 페이지 시작 번호
		result.setStartNumOfCurrentPagingBlock();
		
		// 현재 페이징 블럭 페이지 끝 번호
		result.setEndNumOfCurrentPagingBlock();
		
		
		
		return result;
	}




}
