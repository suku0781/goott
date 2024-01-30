package com.miniproject.etc;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class ImgMimeType {
	private static Map<String, String> imgMimeType; // 확장자, MIME 타입
	
	{
		// instance멤버변수 초기화 블럭
		
	}
	
	static {
		// static변수의 초기화 블럭
		imgMimeType = new HashMap<String, String>();
		
		imgMimeType.put("jpg", MediaType.IMAGE_JPEG.toString());
		imgMimeType.put("jpeg", MediaType.IMAGE_JPEG.toString());
		imgMimeType.put("png", MediaType.IMAGE_PNG.toString());
		imgMimeType.put("gif", MediaType.IMAGE_GIF_VALUE);
	}
	
	/**
	 * @MethodName : extIsImage
	 * @author : shk
	 * @date  : 2024. 1. 23. 
	 * @description : extension이 image인지 아닌지 리턴 
	 * @param ext
	 * @return
	 */
	public static boolean extIsImage(String ext) {
		return imgMimeType.containsKey(ext);
	}
	
	/**
	 * @MethodName : contentTypeIsImage
	 * @author : shk
	 * @date  : 2024. 1. 23. 
	 * @description : contentType이 image인지 아닌지 리턴
	 * @param contentType
	 * @return
	 */
	public static boolean contentTypeIsImage(String contentType) {
		return imgMimeType.containsValue(contentType);
	}
	
}
