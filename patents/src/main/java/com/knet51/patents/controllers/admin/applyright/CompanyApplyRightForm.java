package com.knet51.patents.controllers.admin.applyright;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class CompanyApplyRightForm {
	@NotEmpty
	@Size(min=1,max=200)
	private String boss;
	
	@NotEmpty
	@Size(min=7,max=12)
	private String bossPhone;
	
	@NotEmpty
	@Size(min=17, max=20)
	private String bossId;
	private String comContent;
	
	@NotEmpty
	private String comName;
	
	@NotEmpty
	private String comApplypermit;

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getBossPhone() {
		return bossPhone;
	}

	public void setBossPhone(String bossPhone) {
		this.bossPhone = bossPhone;
	}

	public String getBossId() {
		return bossId;
	}

	public void setBossId(String bossId) {
		this.bossId = bossId;
	}

	public String getComContent() {
		return comContent;
	}

	public void setComContent(String comContent) {
		this.comContent = comContent;
	}

	public String getComApplypermit() {
		return comApplypermit;
	}

	public void setComApplypermit(String comApplypermit) {
		this.comApplypermit = comApplypermit;
	}

	public CompanyApplyRightForm() {
		super();
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	
}
