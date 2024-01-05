package com.miniPrj.service.member;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JPopupMenu.Separator;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.UploadContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.miniPrj.controller.MemberFactory;
import com.miniPrj.dao.MemberCRUD;
import com.miniPrj.dao.MemberDAO;
import com.miniPrj.etc.UploadedFile;
import com.miniPrj.service.MemberService;
import com.miniPrj.vo.Member;

public class RegisterMemberService implements MemberService {
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // �ϳ��� ���� ���� ���� ������ (5MB)
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // �ִ� ���� ������(10MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15; // �ִ� request������(15MB)

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���������� ���� ������ �ϴ°�.
		
		System.out.println("��� ��� �ؾ��Ѵ�.");
		
		// ���ϰ� �Բ� �����͸� �޾Ҵٸ� request.getParameter()�� �����͸� �����ϸ� �ȵȴ�.
		System.out.println(req.getParameter("userId"));
		
		// ���� ���ε��� ���丮 ����
		String uploadDir = "\\memberImg";
		
		// ���� ������ ����� ������ ���
		String realPath = req.getSession().getServletContext().getRealPath(uploadDir);
		System.out.println(realPath);
		
		// ���� ��ü ����� 
		File saveFileDir = new File(realPath);
		
		String userId = "";
		String userPw = "";
		String userEmail = "";
		String userImg = "";
		String encoding = "utf-8";
		
		// ������ ����� ������ ���, ������ ��l ȯ�漳�� ������ �������ִ� ��ü
		DiskFileItemFactory factory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);
		
//		DiskFileItemFactory factory2 = new DiskFileItemFactory(); // �⺻������
//		factory2.setSizeThreshold(MEMORY_THRESHOLD);
//		factory2.setRepository(saveFileDir);
		
		// ������ request�� �Ѱ����� �Ű������� ���ؼ� ������ ���ε�ó���� ��ü
		ServletFileUpload sfu = new ServletFileUpload(factory);
		UploadedFile uf = null;
		try {
			List<FileItem> lst = sfu.parseRequest(req);
			
			// fileItem �Ӽ����� 
			// 1. name���� null�� �ƴϸ� ����(name���� �����̸�(Ȯ���� ����))
			// 2. isFormField�� ���� true�̸� ������ �ƴ� ������
			//    isFormField�� ���� false�̸� ����
			// 3. fieldName���� ������ �������� input�±��� name�Ӽ� ��
			
			for(FileItem item : lst) {
				System.out.println(item.toString());
				
				if(item.isFormField()) {
					if(item.getFieldName().equals("userId")) {
						userId = item.getString(encoding);
					} else if(item.getFieldName().equals("userPw")) {
						userPw = item.getString(encoding);
					} else if(item.getFieldName().equals("userEmail")) {
						userEmail = item.getString(encoding);
					} 
					
					
				} else if (item.isFormField() == false && item.getName() != "") { // ���ε�� ������ ���
					// �����̸� �ߺ�����
					// 1. �ߺ������ʴ� �� �̸����� ���ϸ��� ���� :
					// user_uuid
					uf = getNewFileName(item, realPath, userId);
					System.out.println(uf.toString());
					
					// 2. ���ϸ�(������ȣ).Ȯ����
					// ����
					uf = makeNewFileNameWithNumbering(item, realPath);
					
					// ���� �ϵ��ũ�� ����
					File fileToSave = new File(realPath + File.separator + uf.getNewFileName());
					try {
						item.write(fileToSave); // ���� �ϵ��ũ�� ����
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		} catch (FileUploadException e) {
			// ���� ���ε�ɶ��� ����
			e.printStackTrace();
		}
		
		// ȸ������ ����
		MemberDAO mDao = MemberCRUD.getInstance();
		int result = -1;
		try {
			if(uf != null) { // ���ε��� ������ �ִ� ���
				uf.setNewFileName("memberImg/"+uf.getNewFileName());
				result = mDao.registerMemberWithFile(uf, new Member(userId, userPw, userEmail, null, -1, -1), "ȸ������", 100);
				System.out.print("���ε�������� �ִ� ���");		
			} else { // ���ε��� ������ ���� ���
				result = mDao.registerMember(new Member(userId, userPw, userEmail, null, -1, -1), "ȸ������", 100);
				System.out.print("���ε�������� ���� ���");		
			}
		} catch (NamingException | SQLException e) {
			// DB�� ������ �� ������ ����
			e.printStackTrace();
			
			if (uf != null) {
				// ���ε�� ������ �ִٸ� �����ؾ� ��.
				System.out.println("������ �̹���: " + uf.getNewFileName());
				// memberImg/aaaa_c6645aaf-131b-4f83-b527-4e97a2dab0dc.png
				// realPath =
				// D:\lecture\jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMiniProject\memberImg

				String without = uf.getNewFileName().substring("memberImg/".length());
//	             System.out.println(without);

				File deleteFile = new File(realPath + File.separator + without);
				deleteFile.delete(); // ���� ����

			}
		}
		System.out.println((result == 0) ? "ȸ������ ����!" : "ȸ������ ����!");
		
		
		return null;
	}
	
	// �����̸� �ߺ� ����
	private UploadedFile getNewFileName(FileItem item, String realPath, String userId) {
		// userId_UUID�� �� �����̸� ����.
		String uuid = UUID.randomUUID().toString();
		String originalFileName = item.getName(); // ���ε�� ���� ���� �̸�
		String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // .PNG
		
//		System.out.println("uuid: " + uuid + ", originalFileName: " + originalFileName + ", ext: " + ext);
		
		String newFileName = "memberImg/";
		
		if(item.getSize() > 0) { // ���� ������ ����Ǵ� ���
			newFileName += userId + "_" + uuid + ext;
		}
//		System.out.println("newFileName : "+ newFileName);
		
		UploadedFile uf = new UploadedFile(originalFileName, ext, newFileName, item.getSize());
		
		return uf;
	}
	
	
	
	   private UploadedFile makeNewFileNameWithNumbering(FileItem item, String realPath) {
		      // ex) ���ϸ�(��ȣ).Ȯ���� -> ������ �̸��� �����
		      int cnt = 0;
		      String tmpFileName = item.getName(); // ���ε�� �����̸�
		      String newFileName = ""; // ���� ����Ǵ� �����ϸ�
		      String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".")); // .png
		      
		      while(duplicateFileName(tmpFileName, realPath)) { // ������ �ߺ��Ǹ�
		         // �������̸� �����
		         cnt++;
		         tmpFileName = makeNewFileName(tmpFileName, cnt);
		      }
		      
		      newFileName = tmpFileName;
		      
		      UploadedFile uf = new UploadedFile(item.getName(), ext, newFileName, item.getSize());
		      
		      return uf;
		   }

		   private String makeNewFileName(String tmpFileName, int cnt) {
		      //ex) ���ϸ�(��ȣ).Ȯ����
		      // rock.png -> rock + (1) + .png 
		      // rock(1).png -> rock(2).png
		      
		      String newFileName = "";
		      String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".")); // .png
		      String oldFileNameWithoutExt = tmpFileName.substring(0, tmpFileName.lastIndexOf(".")); 
		      
		      int openPos = oldFileNameWithoutExt.indexOf("(");
		      
		      if (openPos == -1) { // "(" �� ���ٸ� -> ó�� �ߺ�
		         newFileName = oldFileNameWithoutExt + "(" + cnt + ")" + "_cp" + ext;
		      } else {
		         newFileName = oldFileNameWithoutExt.substring(0, openPos) + "(" + cnt + ")" + "_cp" + ext;
		      }
		      
		      return newFileName;
		         
		   }

		   private boolean duplicateFileName(String tmpFileName, String realPath) {
		      boolean result = false;
		      File tmpFileNamePath = new File(realPath);
		      File[] files = tmpFileNamePath.listFiles(); // ���ϸ���Ʈ
		      
//		      System.out.println(Arrays.toString(files));
		      
//		      for (File f : files) {
//		         if (f.getName().equals(tmpFileName)) {
//		            System.out.println(tmpFileName + "�� �ߺ��˴ϴ�..");
//		            result = true;
//		         }
//		      }
		      
		      File tmpFile = new File(realPath + File.separator + tmpFileName);
		      if (tmpFile.exists()) {
		         System.out.println(tmpFileName + "�� �ߺ��˴ϴ�..");
		         result = true;
		      }
		      
		      return result;
		   }

	
}
