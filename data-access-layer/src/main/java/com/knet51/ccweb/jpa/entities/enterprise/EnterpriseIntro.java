package com.knet51.ccweb.jpa.entities.enterprise;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Enterprise;

@Entity
public class EnterpriseIntro extends AbstractEntity {
	@Expose
	private String name;
	@Expose
	private String reason;
	@Expose
	@Lob
	private String detailDesc;
	@ManyToOne
	private Enterprise enterprise;
	
	private String forbidden;
	
	
	
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public String getDesc() {
		return detailDesc;
	}
	public void setDesc(String desc) {
		this.detailDesc = desc;
	}
	public EnterpriseIntro() {
		super();
	}
	
	
}
