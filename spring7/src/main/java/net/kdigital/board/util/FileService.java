package net.kdigital.board.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class FileService {
	//1) 서버에 디렉토리가 없으면 디렉토리 생성 
	//2) 원본 파일명을 꺼내서 저장 파일명(랜덤값 or 밀리세컨즈)을 새롭게 작성
	//3) 디렉토리의 파일을 저장하는 작업 수행 
	//4) 저장 파일 명을 반환하여 DB에 저장할 수 있도록 함 
	
	public static String saveFile(MultipartFile uploadFile, String uploadPath) {
		// 파일이 첨부되면, 디렉토리가 있는지 확인 
		// 없으면 생성 , 있으면 그대로 생성 
		if(!uploadFile.isEmpty()) {
			// 
			File path = new File(uploadPath); // 메모리에서 객체만 생성
			if(!path.isDirectory()) {// 디렉토리가 없으면 만들어라
				path.mkdirs();		
			}	
		}
		
		//원본 파일명 추출 
		String originalFileName = uploadFile.getOriginalFilename();
		
		// 랜덤값 발생 , UUID.randomUUID()는 절대 동일값 나올 수 없다 
		String uuid = UUID.randomUUID().toString();
		
		// 원본에서 파일명과 확장자 분리작업 
		String filename; 
		String ext; 
		String savedFileName; 
		
		//확장자가 없다면 -1을 반환
		int position = originalFileName.lastIndexOf("."); // .의 위치를 반환 
		
		//확장자가 없으면 
		if(position == -1) ext ="";
		// 확장자가 있는 경우 
		else 
			ext = "." + originalFileName.substring(position+1);
		
		filename = originalFileName.substring(0,position);
		savedFileName = filename +"_" + uuid + ext;
		
		// 서버의 저장공간에 파일을 저장하기 
		File serverFile = null;
		
		serverFile= new File(uploadPath+"/"+savedFileName);
		
		try {
			uploadFile.transferTo(serverFile); // 하드 디스크에 저장 
		
		} catch (IOException e) {
			savedFileName = null; // 디비에도 저장되면 안되니깐 ????--> 뭔가요,,저장되면 안되니깐 
			e.printStackTrace();
		}
		
		
		
//		bts.jpg
//		bts   jpg 분리 
//		
//		랜덤값: a294aafgk
//		savedfile : bts_a294aafgk.jpg
		return savedFileName; // 저장할 파일명 
	}
	// 저장장치에 저장된 파일을 삭제 (경로 + 파일명)
	public static boolean deleteFile(String fullpath) {
		boolean result = false; // 삭제 여부를 반환 
		File delFile = new File(fullpath);
		
		// 파일 삭제 
		if(delFile.isFile()) {
			result = delFile.delete();
		}
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
