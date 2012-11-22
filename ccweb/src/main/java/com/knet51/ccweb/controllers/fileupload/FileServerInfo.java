package com.knet51.ccweb.controllers.fileupload;

import java.util.Hashtable;
import java.util.List;

class FileServerInfo {
	private String message;
	private String moveup_dir_path;
	private String current_dir_path;
	private String current_url;
	private int total_count;
	private List<Hashtable> fileList;
	
	public FileServerInfo(String message) {
		super();
		this.message = message;
	}

	public FileServerInfo() {
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMoveup_dir_path() {
		return moveup_dir_path;
	}

	public void setMoveup_dir_path(String moveup_dir_path) {
		this.moveup_dir_path = moveup_dir_path;
	}

	public String getCurrent_dir_path() {
		return current_dir_path;
	}

	public void setCurrent_dir_path(String current_dir_path) {
		this.current_dir_path = current_dir_path;
	}

	public String getCurrent_url() {
		return current_url;
	}

	public void setCurrent_url(String current_url) {
		this.current_url = current_url;
	}

	public int getTotal_count() {
		return total_count;
	}

	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}

	public List<Hashtable> getFileList() {
		return fileList;
	}

	public void setFileList(List<Hashtable> fileList) {
		this.fileList = fileList;
	}

}