package com.miniproject.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.Board;

@Repository // DAO단임을 알림.
public class BoardDAOImpl implements BoardDAO {
	@Inject
	private SqlSession ses; // session템플릿 객체 주입
	
	private static String ns = "com.miniproject.mappers.boardMapper";
	
	@Override
	public List<Board> selectAllBoard() {
		String q = ns + ".getAllList";
		
		return ses.selectList(q);	
	}

}
