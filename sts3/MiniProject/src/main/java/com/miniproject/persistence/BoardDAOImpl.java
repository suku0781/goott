package com.miniproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.Board;
import com.miniproject.domain.ReadCountProcess;
import com.miniproject.domain.SearchCriteria;
import com.miniproject.domain.UploadedFile;
import com.miniproject.etc.PagingInfo;

@Repository // DAO단임을 알림.
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession ses; // session템플릿 객체 주입
	
	private static String ns = "com.miniproject.mappers.boardMapper";
	
	@Override
	public List<Board> selectAllBoard(PagingInfo pi) throws Exception {
		return ses.selectList(ns + ".getAllList", pi);	
	}

	@Override
	public int insertNewBoard(Board newBoard) throws Exception {
		return ses.insert(ns + ".insertNewBoard", newBoard);
	}

	@Override
	public int selectBoardNo() {
		return ses.selectOne(ns + ".selectBoardNo");
	}

	@Override
	public int insertUploadedFile(UploadedFile file, int boardNo) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("originalFileName", file.getOriginalFileName());
		param.put("newFileName", file.getNewFileName());
		param.put("size", file.getSize());
		param.put("boardNo", boardNo);
		param.put("thumbFileName", file.getThumbFileName());
		
		return ses.insert(ns + ".insertUploadedFile", param);
	}

	@Override
	public ReadCountProcess selectReadCountProcess(int no, String ipAddr) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("boardNo", no);
		param.put("ipAddr", ipAddr);
		
		return ses.selectOne(ns + ".selectBoardViewCnt", param);
	}

	@Override
	public int getHourDiffReadTime(int no, String ipAddr) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("boardNo", no);
		param.put("ipAddr", ipAddr);
			
		return ses.selectOne(ns + ".getHourDiffReadTime", param);
	}

	@Override
	public int updateReadCountProcess(ReadCountProcess readCountProcess) {
		return ses.update(ns + ".updateReadCountProcess", readCountProcess);
	}

	@Override
	public int updateReadCount(int no) {
		return ses.update(ns + ".updateReadCount", no);
	}

	@Override
	public int insertReadCountProcess(ReadCountProcess readCountProcess) throws Exception {
		return ses.insert(ns + ".insertReadCountProcess", readCountProcess);
	}

	@Override
	public Board selectBoardByNo(int no) throws Exception {
		return ses.selectOne(ns + ".selectBoard", no);
	}

	@Override
	public List<UploadedFile> selectUploadedFile(int no) throws Exception{
		return ses.selectList(ns + ".selectUploadedFile", no);
	}

	@Override
	public int getTotalPostCnt() throws Exception {
		return ses.selectOne(ns + ".selectTotalBoardCnt");
	}

	@Override
	public int getBoardCntWithSearch(SearchCriteria sc) throws Exception{
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("searchType", sc.getSearchType());
		param.put("searchWord", "%" + sc.getSearchWord() + "%");
		
		return ses.selectOne(ns + ".getAllListCntWithSearch", param);
	}

	@Override
	public List<Board> selectAllBoard(PagingInfo pi, SearchCriteria sc) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("searchType", sc.getSearchType());
		param.put("searchWord", "%" + sc.getSearchWord() + "%");
		param.put("startRowIndex", pi.getStartRowIndex());
		param.put("viewPostCntPerPage", pi.getViewPostCntPerPage());
		
		return ses.selectList(ns + ".getAllBoardWithSearch", param);
	}

}
