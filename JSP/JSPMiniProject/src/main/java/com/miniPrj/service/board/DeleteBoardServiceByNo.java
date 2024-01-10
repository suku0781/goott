package com.miniPrj.service.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.JsonObject;
import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.dao.DBConnection;
import com.miniPrj.service.BoardService;

public class DeleteBoardServiceByNo implements BoardService {

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, NamingException, SQLException {
		System.out.println("게시글 삭제하기");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		BoardDAO dao = BoardCRUD.getInstance();
		int result = dao.deleteBoard(boardNo);
		JSONObject json = new JSONObject();
		PrintWriter out = response.getWriter();
		if(result == 1) {
			json.put("status", "success");
			json.put("target", "deleteBoard");
			
		} else {
			json.put("status", "fail");
		}
		
		out.print(json);
		out.close();
		
		return null;
	}

}
