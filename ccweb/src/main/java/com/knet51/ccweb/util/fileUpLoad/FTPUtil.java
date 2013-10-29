package com.knet51.ccweb.util.fileUpLoad;

import java.io.File;
import java.io.FileInputStream;
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
	public static void main(String[] args) {
		//File file = new File("C:/EMC/tmp/testFTP.txt");
		//boolean flag = FTPUtil.getInstance().uploadFile("attach", "testFTP.txt", file);
		//System.out.println(flag);
	}

	public boolean uploadFile(String path, String filename, InputStream input) {
		// path is the upload directory path.
		boolean success = false;
		//FileInputStream input;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			//input = new FileInputStream(file);
			ftp.connect(hostname, port);
			ftp.login(username, password);
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return success;
			}
			ftp.enterLocalPassiveMode();
			ftp.makeDirectory(path);
			ftp.changeWorkingDirectory(path);
			ftp.storeFile(filename, input);
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
