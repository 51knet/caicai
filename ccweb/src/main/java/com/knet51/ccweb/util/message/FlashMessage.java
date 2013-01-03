package com.knet51.ccweb.util.message;

public class FlashMessage {
	private MessageType messageType;
	private String message;
	private String details;
	
	public FlashMessage(MessageType messageType, String message) {
		super();
		this.messageType = messageType;
		this.message = message;
	}
	
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
}
