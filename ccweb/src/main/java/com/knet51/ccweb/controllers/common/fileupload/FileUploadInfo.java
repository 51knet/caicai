package com.knet51.ccweb.controllers.common.fileupload;

public class FileUploadInfo {

	private int error; // 1 for error, 0 for good
	private String message;
	private String url;
	
	// factory
	public static FileUploadInfo createSucceedFileUploadInfo(String url) {
		return new FileUploadInfo(url);
	}
	public static FileUploadInfo createFailedFileUploadInfo(String message) {
		return new FileUploadInfo(1, message);
	}
	private FileUploadInfo (String url) {
		this.error = 0;
		this.url = url;
	}
	
	private FileUploadInfo() {
	}

	private FileUploadInfo(int error, String message) {
		this.error = error;
		this.message = message;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
