package com.miniPrj.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.miniPrj.controller.BoardFactory;

public interface BoardService {
	BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, NamingException, SQLException;
}
