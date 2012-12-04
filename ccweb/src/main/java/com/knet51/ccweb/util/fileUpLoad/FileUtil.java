package com.knet51.ccweb.util.fileUpLoad;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FileUtil {
	public static String saveFile(InputStream inputStream,String fileOriginalName,String realPath) throws Exception{
		
		String fileName = new Date().getTime()+fileOriginalName;
		//System.out.println(fileName);
		//String savePath = realPath+"\\"+fileName;
		FileOutputStream outputStream = new FileOutputStream(realPath+"\\"+fileName);
		int count = 0;
		byte[] b = new byte[1024*8];
		while((count=inputStream.read(b))!=-1){
			outputStream.write(b, 0, count);
			outputStream.flush();
		}
		inputStream.close();
		outputStream.close();
		return fileName;
	}
	
	public static String getPath(String saveFileName,Long user_id,String type,HttpSession session){
		String realPath = session.getServletContext().getRealPath("/")+saveFileName+"/"+user_id+"/"+type;
		File f = new File(realPath);
		if(!f.exists()) {
			 f.mkdirs();
	    }
		return realPath;
	}
	
	public static String getSavePath(String saveFileName,Long user_id,String type,HttpServletRequest request){
		String savePath = request.getContextPath()+"/"+saveFileName+"/"+user_id+"/"+type;
		return savePath;
	}
	
	public static void downLoad(HttpServletRequest request,HttpServletResponse response,String savePath,String fileName) throws Exception{
		//response.setContentType("text/html;charset=utf-8");
		//request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			Long fileLength = new File(savePath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachement; filename="+new String(fileName.getBytes("GBK"),"ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(savePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] b = new byte[2048];
			int temp = 0;
			while((temp = bis.read(b, 0, b.length))!=-1 ){
				bos.write(b, 0, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bos!=null){
				bos.close();
			}
			if(bis !=null){
				bis.close();
			}
		}
	}
	
}
