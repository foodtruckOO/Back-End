package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class FileUtils {
	public static String getNewFileName(String path, String fileName, String uploaderNo){
		//fileName=원격.txt
		File file= new File(path+File.separator+fileName);
		//파일이 존재할 경우
		String ext = fileName.substring(fileName.lastIndexOf(".")+1).trim();//확장자만 뽑기
		String fileNameExcludeExt =fileName.substring(0,fileName.lastIndexOf("."));//확장자 제외 파트만 뽑기
		String newFileName;//새로 지정할 파일명
		while(true){
			newFileName="";
			if(fileNameExcludeExt.indexOf("_") !=-1){//파일명에 _가 포함됨
				String[] arrFiles=fileNameExcludeExt.split("_");
				String lastName=arrFiles[arrFiles.length-1];
				try{
					int index=Integer.parseInt(lastName);
					for(int i=0; i < arrFiles.length;i++){
						if(i != arrFiles.length-1) newFileName+=arrFiles[i]+"_";
						else newFileName+=String.valueOf(index+1);
					}
					newFileName+="."+ext;
				}
				catch(Exception e){
					newFileName+=fileNameExcludeExt+"_1."+ext;
				}
			}
			else{//_가 없음
				newFileName+=fileNameExcludeExt+"_1."+ext;
			}   
			File f= new File(path+File.separator+newFileName);
			if(f.exists()){     
				fileNameExcludeExt=newFileName.substring(0,newFileName.lastIndexOf("."));
				continue;   
			}
			else break;
		}////////////while
		return newFileName;
	} 

	//파일 업로드 로직
	public static MultipartRequest upload(HttpServletRequest req, String saveDirectory) {
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, saveDirectory, 1024*5000, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mr;
	}
	
	public static void delete(HttpServletRequest req, String directory, String fileName) {
		//req, 폴더, 파일 3개 전달받아서 하는 구조
		String filePath = req.getServletContext().getRealPath(directory)+File.separator+fileName;
		File file = new File(filePath);
		if(file.exists())file.delete();
	}
	
	public static void download(HttpServletRequest request, HttpServletResponse response, String directory, String filename) {
		//파일의 물리적 경로 = metadata 폴더 내에 있음
		String saveDirectory = request.getServletContext().getRealPath(directory);
		//파일크기 얻기 위한 파일객체 생성(File.separator = 운영체제에 따라 디렉토리 구분자가 다르게 설정되어있는데, 이걸 맞춰주는 상수임)
		File file = new File(saveDirectory+File.separator+filename);
		//파일크기 얻기(프로그레스 바를 구현하기 위함...)
		long length = file.length();
		//다운로드창을 보여주기 위한 응답헤더 설정하기 - 컨텐츠 타입
		response.setContentType("binary/octect-stream");
		//프로그레스 바 표시용 응답헤더 설정하기 - 컨텐츠 길이
		response.setContentLength((int)length);
		//응답헤더명 : Content-Disposition
		//응답헤더명에 따른 값 : attachment;filename=파일명 setHeader(응답헤더명, 헤더값)
		//브라우저 종류별 한글파일명이 깨질 수 있기 때문에 브라우저별 인코딩방식을 달리 해야 함
		boolean isIE = request.getHeader("user-agent").toUpperCase().indexOf("MSIE")!=-1 || request.getHeader("user-agent").indexOf("11.0")!=-1;
		try {
			if(isIE){
				filename = URLEncoder.encode(filename, "UTF-8");
			}
			else{
				filename = new String(filename.getBytes("UTF-8"), "8859_1");
			}
			response.setHeader("Content-Disposition", "attacment;filename="+filename);
			/*
			데이터 소스 : 파일	/	노드스트림 : fileInputStream
								필터스트림 : bufferedInputStream
			데이터 목적지 : 브라우저	노드스트림 : response.getOutputStream();
								필터스트림 : BufferedOutputStream
							
		 */
			//스트림 생성
			BufferedInputStream bis;
			bis = new BufferedInputStream(new FileInputStream(file));
			//out.clear();
			//out=PageContext.pushBody();
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			//bis로 읽어 bos로 출력
			int data;
			while((data = bis.read())!=-1){
				bos.write(data);
				bos.flush();
			}
			// 스트림 닫기
			bis.close();
			bos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*	수업간 받은 소스
	public static String getNewFileName(String path, String fileName){
		//fileName=원격.txt
		File file= new File(path+File.separator+fileName);
		if(!file.exists()){//파일이 존재하지 않으면 어떠한 수종조치도 하지 않음
			return fileName;
		}
		else{//파일이 존재할 경우
			String ext = fileName.substring(fileName.lastIndexOf(".")+1).trim();//확장자만 뽑기
			String fileNameExcludeExt =fileName.substring(0,fileName.lastIndexOf("."));//확장자 제외 파트만 뽑기
			String newFileName;//새로 지정할 파일명
			while(true){
				newFileName="";
				if(fileNameExcludeExt.indexOf("_") !=-1){//파일명에 _가 포함됨
					String[] arrFiles=fileNameExcludeExt.split("_");
					String lastName=arrFiles[arrFiles.length-1];
					try{				
						int index=Integer.parseInt(lastName);
						for(int i=0; i < arrFiles.length;i++){
						if(i != arrFiles.length-1) newFileName+=arrFiles[i]+"_";
						else newFileName+=String.valueOf(index+1);
					}
					newFileName+="."+ext;
					}
					catch(Exception e){
					newFileName+=fileNameExcludeExt+"_1."+ext;
					}
				}
				else{//_가 없음
					newFileName+=fileNameExcludeExt+"_1."+ext;
				}   
				File f= new File(path+File.separator+newFileName);
				if(f.exists()){     
					fileNameExcludeExt=newFileName.substring(0,newFileName.lastIndexOf("."));
					continue;   
				}   
				else break;
			}////////////while
		return newFileName;
		}
	} */
	
	
	
	
	
	
}
