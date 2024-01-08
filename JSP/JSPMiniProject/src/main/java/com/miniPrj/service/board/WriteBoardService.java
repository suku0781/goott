package com.miniPrj.service.board;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
		Board board = new Board(0, writter, title, null, content, 0, 0, 0, 0, 0, "n");
		int result = -1;
		try {
			if(uf != null) { // ���ε��� ������ �ִ� ���
				uf.setNewFileName("uploads/"+uf.getNewFileName());
				System.out.println("uf.getNewFileName() : "+uf.getNewFileName());
				result = dao.insertBoardWithUploadFileTransaction(board, uf);
				
				System.out.println("���ε� ������ �ִ� ���");
			} else {
				result = dao.insertBoardTransaction(board);
				System.out.println("���ε� ������ ���� ���");
				
				bf.setRedirect(true);
				bf.setWhereToGo("listAll.bo");
			}
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			if(uf != null) {
				// ���ε�� ������ �ִٸ� �����ؾ� ��.
				System.out.println("������ �̹���: " + uf.getNewFileName());
				// memberImg/aaaa_c6645aaf-131b-4f83-b527-4e97a2dab0dc.png
				// realPath =
				// D:\lecture\jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMiniProject\memberImg

				String without = uf.getNewFileName().substring("uploads/".length());
//	             System.out.println(without);

				File deleteFile = new File(realPath + File.separator + without);
				deleteFile.delete(); // ���� ����

			}
		}
		
		
		System.out.println(result == 0 ? "���� ���� ����!" : "���� ���� ����!");
//		
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
		
		System.out.println("breakPoint");
		
		UploadedFile uf = new UploadedFile(originalFileName, ext, newFileName, item.getSize()  );
				
		return uf;
	}

}
