package com.miniproject.service.board;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.miniproject.domain.Board;
import com.miniproject.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	BoardDAO dao;
	
	@Override
	public List<Board> getEntireBoard() {
		List<Board> lst = dao.selectAllBoard();
		
		return lst;
	}

}
