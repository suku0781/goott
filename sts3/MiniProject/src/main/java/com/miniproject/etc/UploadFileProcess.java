package com.miniproject.etc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.miniproject.domain.UploadedFile;

public class UploadFileProcess {
	

	/**
	 * @MethodName : fileUpload
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 파일업로드 처리 
	 * @returnType : UploadedFile
	 * @param originalFilename
	 * @param size
	 * @param contentType
	 * @param data
	 * @param realPath
	 * @return uf
	 * @throws IOException
	 * @note : 
	 */
	public static UploadedFile fileUpload(String originalFilename, long size, String contentType, byte[] data, String realPath) throws IOException {
//		1. 시스템의 현재 날짜를 얻어와서 해당 날짜 년 월 일 폴더를 생성하고 파일명을 uuid나 시퀀스로 중복되지않게 처리한다. 
		
		// path를 만드는 작업(년 월 일 디렉토리 생성)
		String completePath = realPath + makeCalculatePath(realPath); // 물리적 경로 + \년 + \월 + \일
		
		UploadedFile uf = new UploadedFile();  
		
		if(size > 0) {
			uf.setNewFileName( getNewFileName(originalFilename, realPath, completePath) );
			uf.setOriginalFileName(originalFilename);
			uf.setSize(size);
			uf.setExt(originalFilename.substring(uf.getOriginalFileName().lastIndexOf(".")));

			FileCopyUtils.copy(data,  new File(realPath + uf.getNewFileName())); // 원본 이미지 저장
			
			if(ImgMimeType.contentTypeIsImage(contentType)) {
				// 파일이 이미지인 경우 썸네일 생성(scale down) 후 저장
				System.out.println("이미지가 업로드됨.");
				
				makeThumbnailImage(uf, completePath, realPath);
				
				
			} else {
				System.out.println("파일이 업로드됨.");
			}
			
		}
		return uf;
	}
	
	/**
	 * @MethodName : makeThumbnailImage
	 * @author : shk
	 * @date  : 2024. 1. 23. 
	 * @description : 
	 * @returnType : void
	 * @param uf
	 * @param completePath
	 * @param realPath
	 * @throws IOException 
	 * @note : 저장된 원본 파일을 읽어서 스케일 다운하여 썸네일을 만든다.
	 */
	private static void makeThumbnailImage(UploadedFile uf, String completePath, String realPath) throws IOException {
		System.out.println("썸네일 이미지 안들 원본 파일 경로 : "+realPath + uf.getNewFileName());
		
		BufferedImage originImg = ImageIO.read(new File(realPath + uf.getNewFileName())); // 원본 파일 읽기
		
//		Scalr.resize(originImg, Scalr.Method.AUTOMATIC, Mode.FIT_TO_HEIGHT, 50);
		BufferedImage thumbnailImage = Scalr.resize(originImg, Mode.FIT_TO_HEIGHT, 50);
		
		// 썸네일 저장
		String thumbnailImageName = "thumb_" + uf.getOriginalFileName();
		String ext = uf.getOriginalFileName().substring(uf.getOriginalFileName().lastIndexOf(".") + 1);
		File saveTarget = new File(completePath + File.separator + thumbnailImageName);
		if( ImageIO.write(thumbnailImage, ext,  saveTarget) ) { // 썸네일 저장되었다면
			uf.setThumbFileName(completePath.substring(realPath.length()) + File.separator + thumbnailImageName);
		} else {
			System.out.println("썸네일 이미지 저장 실패.");
		}
	}

	/**
	 * @MethodName : getNewFileName
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 새로운 파일 명을 생성
	 * @returnType : String
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
	 * @returnType : String
	 * @param realPath
	 * @return
	 */
	public static String makeCalculatePath(String realPath) {
		Calendar cal = Calendar.getInstance(); // 현재날짜 얻어오기
		
		String year = File.separator + (cal.get(Calendar.YEAR));// \2024
		String month = year + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1); // \2024\01
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE)); // \2024\01\22
		
		makeDirectory(realPath, year, month, date); // 경로가 있는지 없는지 검사하여 없다면 생성
		
		return date;
	}
	
	/**
	 * @MethodName : makeDirectory
	 * @author : shk
	 * @date  : 2024. 1. 22. 
	 * @description : 경로가 있는지 없는지 검사하여 없다면 생성
	 * @returnType : void
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

	/**
	 * @MethodName : fileDelete
	 * @author : shk
	 * @date  : 2024. 1. 23. 
	 * @description : fileList에서 removeFile과 thumbnail Image 삭제
	 * @returnType : void
	 * @param ufList
	 * @param removeFile
	 * @param realPath
	 * @note : 
	 */
	public static void fileDelete(List<UploadedFile> ufList, String removeFile, String realPath) {
		for(UploadedFile uf : ufList) {
			if(removeFile.equals(uf.getOriginalFileName())) { // 지워야할 파일을 찾으면
				File file = new File(realPath + uf.getNewFileName());
				
				if(file.exists()) {
					file.delete(); // original file 삭제
				}
				if(uf.getThumbFileName() != null) {
					File thumbFile = new File(realPath + uf.getThumbFileName());
					if(thumbFile.exists()) {
						thumbFile.delete(); // thumbnail file 삭제
					}	
				}
			} 
		}
	}
	
	/**
	 * @MethodName : fileDelete
	 * @author : shk
	 * @date  : 2024. 1. 23. 
	 * @description : 
	 * @returnType : void
	 * @param ufList
	 * @param realPath
	 * @note : 
	 */
	public static void fileDelete(List<UploadedFile> ufList, String realPath) {
		for(UploadedFile uf : ufList) {
			File file = new File(realPath + uf.getNewFileName());
			
			if(file.exists()) {
				file.delete(); // original file 삭제
			}
			if(uf.getThumbFileName() != null) {
				File thumbFile = new File(realPath + uf.getThumbFileName());
				if(thumbFile.exists()) {
					thumbFile.delete(); // thumbnail file 삭제
				}	
			}
		}
	}
	
}
