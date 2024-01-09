package com.miniPrj.controller;

import com.miniPrj.service.BoardService;
import com.miniPrj.service.board.GetBoardService;
import com.miniPrj.service.board.GetEntireBoardService;
import com.miniPrj.service.board.WriteBoardService;

public class BoardFactory {
	private boolean isRedirect; // redirect를 할 것인지 
	private String whereToGo; // 어느 view로 이동할 것인지 
	
	private static BoardFactory instance = null;
	
	private BoardFactory() {}
	
	public static BoardFactory getInstance() {
		if(instance == null) instance = new BoardFactory();
		return instance;
	}

	public BoardFactory(boolean isRedirect, String whereToGo) {
		super();
		this.isRedirect = isRedirect;
		this.whereToGo = whereToGo;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}

	public String getWhereToGo() {
		return whereToGo;
	}

	public void setWhereToGo(String whereToGo) {
		this.whereToGo = whereToGo;
	}

	public BoardService getService(String command) {
		BoardService service = null;
		
		System.out.println("board command : " + command);
		
		if(command.equals("/board/listAll.bo")) {
			service = new GetEntireBoardService();
		} else if(command.equals("/board/writeBoard.bo")) {
			service = new WriteBoardService();
		} else if(command.equals("/board/viewBoard.bo")) {
			service = new GetBoardService();
		}
		
		return service;
	}
	
	
	
}
