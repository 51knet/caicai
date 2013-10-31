package com.knet51.ccweb.util.fileUpLoad;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {

	private String hostname = "103.30.5.198";
	private int port = 21;
	private String username = "caicai";
	private String password = "caicai123";

	private FTPUtil() {

	}

	private static FTPUtil instance = null;

	public static FTPUtil getInstance() {
		if (instance == null) {
			instance = new FTPUtil();
		}
		return instance;
	}

	// here is an example to upload a file.
	// public static void main(String[] args) {
	// InputStream input;
	// try {
	// input = new FileInputStream("C:/EMC/tmp/11aa##.txt");
	// boolean flag = FTPUtil.getInstance().uploadFile("测试a1", "测试2.txt",
	// input);
	// System.out.println(flag);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public boolean uploadFile(String path, String fileName, InputStream input) {
		// path is the upload directory path.
		boolean success = false;
		FTPClient ftp = new FTPClient();
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
		String fullPath = "ftp://" + hostname + "/";
		fullPath += path;
		return fullPath;
	}

}
