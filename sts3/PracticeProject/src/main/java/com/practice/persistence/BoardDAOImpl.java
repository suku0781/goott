package com.practice.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.practice.domain.Board;

@Repository
public class BoardDAOImpl implements BoardDAO {
	private String ns = "com.practiceproject.mappers.boardMapper";
	
	@Inject
	private SqlSession ses;

	@Override
	public List getAllBoard() {
		String q = ns + ".selectAllBoardList";
		
		return ses.selectList(q);
	}

}
