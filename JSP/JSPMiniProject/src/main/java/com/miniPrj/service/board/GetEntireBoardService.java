package com.miniPrj.service.board;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.service.BoardService;
import com.miniPrj.vo.Board;

public class GetEntireBoardService implements BoardService {
	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("게시판 글 전체 목록 가져오기.");
		
		BoardDAO dao = BoardCRUD.getInstance();
		try {
			List<Board> lst = dao.selectAllBoard();
			System.out.println("lst : "+lst);
			
			if(lst.size() == 0) {
				request.setAttribute("boardList", null);
			} else {
				request.setAttribute("boardList", lst);
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("listAll.jsp").forward(request, response);		
		
		return null;
	}



}
