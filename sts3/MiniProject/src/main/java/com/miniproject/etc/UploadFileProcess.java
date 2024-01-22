package com.miniproject.etc;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import com.miniproject.domain.UploadedFile;

public class UploadFileProcess {
	
	/**
	 * @MethodName : fileUpload
	 * @author : shk
	 * @throws IOException 
	 * @date  : 2024. 1. 22. 
	 * @description : 파일업로드 처리 
	 */
	public static void fileUpload(String originalFilename, long size, String contentType, byte[] data, String realPath) throws IOException {
		
//		1. 시스템의 현재 날짜를 얻어와서 해당 날짜 년 월 일 폴더를 생성하고 파일명을 uuid나 시퀀스로 중복되지않게 처리한다. 
		
		// path를 만드는 작업(년 월 일 디렉토리 생성)
		String completePath = realPath + makeCalculatePath(realPath); // 물리적 경로 + \년 + \월 + \일
		
		// 
		UploadedFile uf = new UploadedFile();  
		
		if(size > 0) {
			uf.setNewFileName( getNewFileName(originalFilename, realPath, completePath) );
//			getNewFileName(originalFilename, realPath, completePath);
			uf.setOriginalFileName(originalFilename);
			uf.setSize(size);

//			FileCopyUtils.copy(data,  new File(realPath + uf.getNewFileName())); // 원본 이미지 저장
			System.out.println("uf : " + uf);
			
		}
	}
	
	/**
	 * @MethodName : getNewFileName
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 새로운 파일 명을 생성
	 * @param originalFilename
	 * @param realPath
	 * @param completePath
	 * @return
	 */
	private static String getNewFileName(String originalFilename, String realPath, String completePath) {
		String uuid = UUID.randomUUID().toString();
		String newFileName = uuid + "_" + originalFilename;
		
		System.out.println("realPath : "+realPath);
		System.out.println("completePath : "+completePath);
		System.out.println("테이블에 저장될 업로드 파일 이름 : "+completePath.substring(realPath.length()) + File.separator + newFileName);
				
		return completePath.substring(realPath.length()) + File.separator + newFileName;
	}

	/**
	 * @MethodName : makeCalculatePath
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 년 월 일 디렉토리 생성 
	 * @param realPath
	 * @return
	 */
	public static String makeCalculatePath(String realPath) {
		Calendar cal = Calendar.getInstance(); // 현재날짜 얻어오기
		
		// 
		String year = File.separator + (cal.get(Calendar.YEAR));// \2024
		String month = year + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1); // \2024\01
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2024\01\22
		
		makeDirectory(realPath, year, month, date);
		
		return date;
	}
	
	/**
	 * @MethodName : makeDirectory
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 경로가 있는지 없는지 검사하여 없다면 생성
	 * @param realPath
	 * @param strings
	 */
	public static void makeDirectory(String realPath, String ...strings) {
		if(!new File(realPath + strings[strings.length -1]).exists()) { // date경로가 없다면 생성
			for(String path : strings) {
				File tmp = new File(realPath + path);
				if(!tmp.exists()) {
					tmp.mkdir();
				}
			}
		}
		
		
	}
}
