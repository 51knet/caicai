package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity

public class AuthenResource  extends AbstractEntity{
	
	private String saveName;
	private String savePath;
	@ManyToOne
	private Authentication authentication;
	
	
	public Authentication getAuthentication() {
		return authentication;
	}
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public AuthenResource() {
		super();
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

}
