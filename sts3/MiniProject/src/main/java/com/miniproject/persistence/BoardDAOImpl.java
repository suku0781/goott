package com.miniproject.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.Board;
import com.miniproject.domain.UploadedFile;

@Repository // DAO단임을 알림.
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession ses; // session템플릿 객체 주입
	
	private static String ns = "com.miniproject.mappers.boardMapper";
	
	@Override
	public List<Board> selectAllBoard() throws Exception {
		String q = ns + ".getAllList";
		
		return ses.selectList(q);	
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

}
