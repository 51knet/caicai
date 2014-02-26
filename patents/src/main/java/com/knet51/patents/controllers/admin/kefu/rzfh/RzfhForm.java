package com.knet51.patents.controllers.admin.kefu.rzfh;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


public class RzfhForm {
	@NotEmpty
	@Size(min=1, max=200)
	private String name;
	@NotEmpty
	private String content;
 
	@NotEmpty
	@Size(min=1, max=200)
	private String webUrl;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public RzfhForm() {
		super();
	}
	
}
