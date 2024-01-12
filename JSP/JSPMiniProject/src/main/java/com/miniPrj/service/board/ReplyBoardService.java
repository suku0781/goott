package com.miniPrj.service.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.service.BoardService;
import com.miniPrj.vo.Board;

public class ReplyBoardService implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, NamingException, SQLException {
		BoardFactory bf = BoardFactory.getInstance();
		
		String writter = request.getParameter("writter");
		String content = request.getParameter("content");
		int ref = Integer.parseInt(request.getParameter("ref"));
		int step = Integer.parseInt(request.getParameter("step"));
		int refOrder = Integer.parseInt(request.getParameter("refOrder"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		BoardDAO dao = BoardCRUD.getInstance();
		Board board = new Board(boardNo, writter, null, null, content, -1, -1, ref, step, refOrder, null);
		
		try {
			if(dao.insertReplyTransaction(board)) {
				bf.setRedirect(true);
				bf.setWhereToGo("viewBoard.bo?no="+boardNo+"&page=boardDetail");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return bf;
	}

}
