package com.miniPrj.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.miniPrj.etc.UploadedFile;
import com.miniPrj.vo.Board;

public interface BoardDAO {

	// ��ü �Խ��� �� ���
	List<Board> selectAllBoard() throws NamingException, SQLException;

	// �Խ��� �� ����(���ε� ������ �ִ� ���)
	int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf) throws NamingException, SQLException;
	
	// �Խ��� �� ����(���ε� ������ ���� ���)
	int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException;

	// ���ε�� ���� ������ uploadedFile���̺� insert
	int insertUploadedFileInfo(UploadedFile uf, Connection con) throws NamingException, SQLException;
	
}
