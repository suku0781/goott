package com.miniPrj.service.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import com.miniPrj.controller.BoardFactory;
import com.miniPrj.dao.BoardCRUD;
import com.miniPrj.dao.BoardDAO;
import com.miniPrj.etc.UploadedFile;
import com.miniPrj.service.BoardService;
import com.miniPrj.vo.Board;

public class WriteBoardService implements BoardService {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // �ϳ��� ���� ���ۻ�����
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // �ִ� ���� ������
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15; // �ִ� request ������

	@Override
	public BoardFactory doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, MessagingException, NamingException, SQLException {
		System.out.println("�Խñ� �����ϱ�.");
		
		BoardFactory bf = BoardFactory.getInstance();
		
		// ���� ���ε� ���丮 ����.
		String uploadDir = File.separator + "uploads";
		
		// ���� ������ ����Ǵ� ������ ���
		String realPath = request.getSession().getServletContext().getRealPath(uploadDir);
		
		System.out.println("realPath : "+realPath);
		
		// ���� ��ü ����� 
		File saveFileDir = new File(realPath); 
		
		String writter = "";
		String title = "";
		String content = "";
		String upFile = "";
		String encoding = "utf-8";
		
		DiskFileItemFactory factory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);
		
		ServletFileUpload sfu = new ServletFileUpload(factory);
		UploadedFile uf = null;
		
		try {
			List<FileItem> lst = sfu.parseRequest(request);
			
			for(FileItem item : lst) {
				if(item.isFormField()) {
					if(item.getFieldName().equals("writter")) {
						writter = item.getString(encoding);
					}else if(item.getFieldName().equals("title")) {
						title = item.getString(encoding);
					} else if(item.getFieldName().equals("content")) {
						content = item.getString(encoding);
					}
					
				} else if(item.isFormField() == false && item.getName() != "") {
					uf = getNewFileName(item, realPath, writter);
					
					// ���� �ϵ��ũ�� ����
					File fileToSave = new File(realPath + File.separator + uf.getNewFileName());
					System.out.println("fileToSave : "+fileToSave );
					
					try {
						item.write(fileToSave); // ���� �ϵ��ũ ����
						
						uf.setBase64String(changeToBase64(realPath + File.separator + uf.getNewFileName()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// �Խñ� ��� ����
		BoardDAO dao = BoardCRUD.getInstance();
		Board board = new Board(-1, writter, title, null, content, -1, -1, -1, -1, -1, null);
		int result = -1;
		try {
			if(uf != null) { // ���ε��� ������ �ִ� ���
				System.out.println("���ε� ������ �ִ� ���");
				
				uf.setNewFileName(uf.getNewFileName());
				result = dao.insertBoardWithUploadFileTransaction(board, uf);
			} else {
				System.out.println("���ε� ������ ���� ���");
				
				result = dao.insertBoardTransaction(board);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			
			if(uf != null) {
				// ���ε�� ������ �ִٸ� �����ؾ� ��.
				System.out.println("������ �̹���: " + uf.getNewFileName());
				String without = uf.getNewFileName().substring("uploads/".length());
				File deleteFile = new File(realPath + File.separator + without);
				deleteFile.delete(); // ���� ����

			}
		}
		
		
		System.out.println(result > -1 ? "���� ���� ����!" : "���� ���� ����!");
//		
		bf.setRedirect(true);
		bf.setWhereToGo("listAll.bo");
		
		return bf;
	}
	
	
	// ���� �̸� �ߺ� ����
	private UploadedFile getNewFileName(FileItem item, String realPath, String writter) {
		String uuid = UUID.randomUUID().toString();
		String originalFileName = item.getName();
		String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
		
		String newFileName = "";
		
		if(item.getSize() > 0) {
			newFileName += writter + "_" + uuid + ext;
		}
		
		UploadedFile uf = new UploadedFile(originalFileName, ext, newFileName, item.getSize()  );
				
		return uf;
	}
	
	// base64���ڵ� �Լ�
	public String changeToBase64(String uploadedFile) {
		String result = null;
		
		File upFile = new File(uploadedFile);
		
		byte[] file;
		try {
			file = FileUtils.readFileToByteArray(upFile);
			result = Base64.getEncoder().encodeToString(file);
			
			System.out.println("Base64 ���ڵ� ��� : "+result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}

	// base64���ڵ� �Լ�
	public File changeToString(String base64, String realPath, String ext) {
		byte[] result = null;
		byte[] decodeArr = null;
		File file = null;
		
		try {
			decodeArr = Base64.getDecoder().decode(base64);
			file = new File(realPath + file.separator + decodeArr + ext);
			
			FileUtils.writeByteArrayToFile(file, decodeArr);
			
			System.out.println("Base64 ���ڵ� ��� : "+decodeArr + ext);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return file;
	}
}
