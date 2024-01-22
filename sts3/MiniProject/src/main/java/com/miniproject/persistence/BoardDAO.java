package com.miniproject.persistence;

import java.util.List;

import com.miniproject.domain.Board;

public interface BoardDAO {

	List<Board> selectAllBoard();

}
