package com.knet51.ccweb.util.fileUpLoad;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {

	private String hostname = "103.30.5.198";
	private int port = 21;
	private String username = "caicai";
	private String password = "caicai123";
	private FTPClient ftp ;
	private FTPUtil() {

	}

	private static FTPUtil instance = null;

	public static FTPUtil getInstance() {
		if (instance == null) {
			instance = new FTPUtil();
		}
		return instance;
	}

//	 here is an example to upload a file.
	 public static void main(String[] args) {
//		 InputStream input;
//			 try {
//			 input = new FileInputStream("C:/EMC/tmp/11aa##.txt");
//			 boolean flag = FTPUtil.getInstance().uploadFile("测试a1", "测试2.txt",
//			 input);
//			 System.out.println(flag);
//			 } catch (FileNotFoundException e) {
//			 // TODO Auto-generated catch block
//			 e.printStackTrace();
//		 }
		 
		 String path = "ftp://103.30.5.198/resources/attached/3/upload/asd/1122.JPG";
		 String dirPath = path.substring(0, path.lastIndexOf("/"));
		 System.out.println(dirPath);
	 }

	public boolean uploadFile(String path, String fileName, InputStream input) {
		// path is the upload directory path.
		boolean success = false;
		ftp = new FTPClient();
		// ftp.setControlEncoding("UTF-8");
		// FTPClientConfig conf = new FTPClientConfig(FTPClientConfig.SYST_NT);
		// conf.setServerLanguageCode("zh");
		// ftp.configure(conf);
		try {
			int reply;
			ftp.connect(hostname, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.enterLocalPassiveMode();
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			path = new String(path.getBytes("GBK"), "iso-8859-1");		
			if (!ftp.changeWorkingDirectory(path)) {
				ftp.makeDirectory(path);
				ftp.changeWorkingDirectory(path);
			}
			ftp.storeFile(new String(fileName.getBytes("GBK"), "iso-8859-1"),
					input);
			
			//File localFile = new File("ftp://103.30.5.198/resources/attached/3/upload/asd/1122.JPG");
			//System.out.println("the file is ======="+localFile.exists());
			
			input.close();
			ftp.logout();
			success = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {

				}
			}
		}
		return success;
	}

	public String getDownloadFileFullPath(String path) {
		String fullPath = "ftp://" + hostname;
		fullPath += path;
		return fullPath;
	}
	
	public boolean ftpDownLoad(HttpServletResponse response,String savePath,String fileName) throws Exception{
		OutputStream output = null;
		ftp = new FTPClient();
		boolean success = false;
	
		String fullPath = new String(new String(savePath.getBytes(), "iso-8859-1"));
		String dirPath = fullPath.substring(0, fullPath.lastIndexOf("/")); 
		String targerFile = new String(fileName.getBytes(), "iso-8859-1");
		try {
			int reply;
			ftp.connect(hostname, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return false;
			}
			ftp.changeWorkingDirectory(dirPath);
			
			FTPFile[] files =  ftp.listFiles(fullPath);
			for (FTPFile ftpFile : files) {
				if(targerFile.equalsIgnoreCase(ftpFile.getName())){
					response.setContentType("application/x-msdownload;");
					response.setHeader("Content-Disposition", "attachement; filename="+new String(fileName.getBytes("GBK"),"ISO8859-1"));
					response.setHeader("Content-Length", String.valueOf(ftpFile.getSize()));
					System.out.println("------FTPFILESName = "+ftpFile.getName()+"byte----"+ftpFile.getSize());
					output = response.getOutputStream();
					success = ftp.retrieveFile(targerFile, output);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(output != null){
				output.flush();
				output.close();
			}
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {

				}
			}
		}
		return success;
	}

}
