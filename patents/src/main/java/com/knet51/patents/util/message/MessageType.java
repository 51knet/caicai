package com.knet51.patents.util.message;

public enum MessageType {
	alert("alert"), info("alert-info"), error("alert-error"), success("alert-success");

	private String styleName;

	private MessageType(String styleName) {
		this.styleName = styleName;
	}

	public String getStyleName() {
		return styleName;
	}
	
	@Override   
    public String toString() {  
        return  this.styleName;  
    }  
}
