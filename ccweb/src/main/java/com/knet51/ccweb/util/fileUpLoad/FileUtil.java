package com.knet51.ccweb.util.fileUpLoad;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileUtil {
	public static String saveFile(InputStream inputStream,String fileName,String realPath) throws Exception{
		String savePath = realPath+"\\"+fileName;
		FileOutputStream outputStream = new FileOutputStream(realPath+"\\"+fileName);
		int count = 0;
		byte[] b = new byte[1024*8];
		while((count=inputStream.read(b))!=-1){
			outputStream.write(b, 0, count);
			outputStream.flush();
		}
		inputStream.close();
		outputStream.close();
		return savePath;
	}
}
