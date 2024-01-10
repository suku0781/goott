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
	int insertBoardWithUploadFileTransaction(Board tmpBoard, UploadedFile uf, String type) throws NamingException, SQLException;
	
	// �Խ��� �� ����(���ε� ������ ���� ���)
	int insertBoardTransaction(Board tmpBoard) throws NamingException, SQLException;

	// ���ε�� ���� ������ uploadedFile���̺� insert
	int insertUploadedFileInfo(UploadedFile uf, Connection con, int lastNo) throws NamingException, SQLException;
	
	// ��ȸ�� ó��
	// readCountProcess���̺� ip�ּҿ� no(�۹�ȣ)�� �ִ��� Ȯ��
	boolean selectReadCountProcess(String userIp, int no) throws NamingException, SQLException;

	// 24�ð��� �������� Ȯ��(�ð�����)
	int selectHourDiff(String userIp, int no)throws NamingException, SQLException;

	// �������ּҿ� �۹�ȣ�� �����ð��� readCountProcess���̺� insert �ϰų� update�ϴ� �޼��� 
	int readCountProcessWithReadCntInc(String userIp, int no, String type) throws NamingException, SQLException;
	
	// no�� �Խñ� ��������
	Board selectBoardByNo(int no) throws NamingException, SQLException;

	// �Խ��� ÷������ ��������
	UploadedFile getFile(int no) throws NamingException, SQLException;
	
	// �Խñ� �����ϱ�
	int deleteBoard(int no) throws NamingException, SQLException;

	Board editBoard(int no) throws NamingException, SQLException;
}
