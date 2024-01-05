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
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 5; // 하나의 파일 블럭의 버퍼 사이즈 (5MB)
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 최대 파일 사이즈(10MB)
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 15; // 최대 request사이즈(15MB)

	@Override
	public MemberFactory executeService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실질적으로 서블릿 역할을 하는곳.
		
		System.out.println("멤버 등록 해야한다.");
		
		// 파일과 함께 데이터를 받았다면 request.getParameter()로 데이터를 수집하면 안된다.
		System.out.println(req.getParameter("userId"));
		
		// 파일 업로드할 디렉토리 생성
		String uploadDir = "\\memberImg";
		
		// 실제 파일이 저장될 물리적 경로
		String realPath = req.getSession().getServletContext().getRealPath(uploadDir);
		System.out.println(realPath);
		
		// 파일 객체 만들기 
		File saveFileDir = new File(realPath);
		
		String userId = "";
		String userPw = "";
		String userEmail = "";
		String userImg = "";
		String encoding = "utf-8";
		
		// 파일이 저장될 공간의 경로, 사이즈 등릐 환경설정 정보를 가지고있는 객체
		DiskFileItemFactory factory = new DiskFileItemFactory(MEMORY_THRESHOLD, saveFileDir);
		
//		DiskFileItemFactory factory2 = new DiskFileItemFactory(); // 기본생성자
//		factory2.setSizeThreshold(MEMORY_THRESHOLD);
//		factory2.setRepository(saveFileDir);
		
		// 실제로 request로 넘겨져온 매개변수를 통해서 파일을 업로드처리할 객체
		ServletFileUpload sfu = new ServletFileUpload(factory);
		UploadedFile uf = null;
		try {
			List<FileItem> lst = sfu.parseRequest(req);
			
			// fileItem 속성에서 
			// 1. name값이 null이 아니면 파일(name값이 파일이름(확장자 포함))
			// 2. isFormField이 값이 true이면 파일이 아닌 데이터
			//    isFormField이 값이 false이면 파일
			// 3. fieldName값이 보내온 데이터의 input태그의 name속성 값
			
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
					
					
				} else if (item.isFormField() == false && item.getName() != "") { // 업로드된 파일의 경우
					// 파일이름 중복제거
					// 1. 중복되지않는 새 이름으로 파일명을 변경 :
					// user_uuid
					uf = getNewFileName(item, realPath, userId);
					System.out.println(uf.toString());
					
					// 2. 파일명(순서번호).확장자
					// 과제
					uf = makeNewFileNameWithNumbering(item, realPath);
					
					// 파일 하드디스크에 저장
					File fileToSave = new File(realPath + File.separator + uf.getNewFileName());
					try {
						item.write(fileToSave); // 파일 하드디스크에 저장
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		} catch (FileUploadException e) {
			// 파일 업로드될때의 예외
			e.printStackTrace();
		}
		
		// 회원가입 진행
		MemberDAO mDao = MemberCRUD.getInstance();
		int result = -1;
		try {
			if(uf != null) { // 업로드한 파일이 있는 경우
				uf.setNewFileName("memberImg/"+uf.getNewFileName());
				result = mDao.registerMemberWithFile(uf, new Member(userId, userPw, userEmail, null, -1, -1), "회원가입", 100);
				System.out.print("업로드된파일이 있는 경우");		
			} else { // 업로드한 파일이 없는 경우
				result = mDao.registerMember(new Member(userId, userPw, userEmail, null, -1, -1), "회원가입", 100);
				System.out.print("업로드된파일이 없는 경우");		
			}
		} catch (NamingException | SQLException e) {
			// DB에 저장할 때 나오는 예외
			e.printStackTrace();
			
			if (uf != null) {
				// 업로드된 파일이 있다면 삭제해야 함.
				System.out.println("삭제할 이미지: " + uf.getNewFileName());
				// memberImg/aaaa_c6645aaf-131b-4f83-b527-4e97a2dab0dc.png
				// realPath =
				// D:\lecture\jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPMiniProject\memberImg

				String without = uf.getNewFileName().substring("memberImg/".length());
//	             System.out.println(without);

				File deleteFile = new File(realPath + File.separator + without);
				deleteFile.delete(); // 파일 삭제

			}
		}
		System.out.println((result == 0) ? "회원가입 성공!" : "회원가입 실패!");
		
		
		return null;
	}
	
	// 파일이름 중복 제거
	private UploadedFile getNewFileName(FileItem item, String realPath, String userId) {
		// userId_UUID로 새 파일이름 생성.
		String uuid = UUID.randomUUID().toString();
		String originalFileName = item.getName(); // 업로드된 원본 파일 이름
		String ext = originalFileName.substring(originalFileName.lastIndexOf(".")); // .PNG
		
//		System.out.println("uuid: " + uuid + ", originalFileName: " + originalFileName + ", ext: " + ext);
		
		String newFileName = "memberImg/";
		
		if(item.getSize() > 0) { // 실제 파일이 저장되는 경우
			newFileName += userId + "_" + uuid + ext;
		}
//		System.out.println("newFileName : "+ newFileName);
		
		UploadedFile uf = new UploadedFile(originalFileName, ext, newFileName, item.getSize());
		
		return uf;
	}
	
	
	
	   private UploadedFile makeNewFileNameWithNumbering(FileItem item, String realPath) {
		      // ex) 파일명(번호).확장자 -> 새파일 이름을 만들기
		      int cnt = 0;
		      String tmpFileName = item.getName(); // 업로드된 원본이름
		      String newFileName = ""; // 실제 저장되는 새파일명
		      String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".")); // .png
		      
		      while(duplicateFileName(tmpFileName, realPath)) { // 파일이 중복되면
		         // 새파일이름 만들기
		         cnt++;
		         tmpFileName = makeNewFileName(tmpFileName, cnt);
		      }
		      
		      newFileName = tmpFileName;
		      
		      UploadedFile uf = new UploadedFile(item.getName(), ext, newFileName, item.getSize());
		      
		      return uf;
		   }

		   private String makeNewFileName(String tmpFileName, int cnt) {
		      //ex) 파일명(번호).확장자
		      // rock.png -> rock + (1) + .png 
		      // rock(1).png -> rock(2).png
		      
		      String newFileName = "";
		      String ext = tmpFileName.substring(tmpFileName.lastIndexOf(".")); // .png
		      String oldFileNameWithoutExt = tmpFileName.substring(0, tmpFileName.lastIndexOf(".")); 
		      
		      int openPos = oldFileNameWithoutExt.indexOf("(");
		      
		      if (openPos == -1) { // "(" 가 없다면 -> 처음 중복
		         newFileName = oldFileNameWithoutExt + "(" + cnt + ")" + "_cp" + ext;
		      } else {
		         newFileName = oldFileNameWithoutExt.substring(0, openPos) + "(" + cnt + ")" + "_cp" + ext;
		      }
		      
		      return newFileName;
		         
		   }

		   private boolean duplicateFileName(String tmpFileName, String realPath) {
		      boolean result = false;
		      File tmpFileNamePath = new File(realPath);
		      File[] files = tmpFileNamePath.listFiles(); // 파일리스트
		      
//		      System.out.println(Arrays.toString(files));
		      
//		      for (File f : files) {
//		         if (f.getName().equals(tmpFileName)) {
//		            System.out.println(tmpFileName + "이 중복됩니다..");
//		            result = true;
//		         }
//		      }
		      
		      File tmpFile = new File(realPath + File.separator + tmpFileName);
		      if (tmpFile.exists()) {
		         System.out.println(tmpFileName + "이 중복됩니다..");
		         result = true;
		      }
		      
		      return result;
		   }

	
}
