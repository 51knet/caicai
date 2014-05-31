package com.knet51.patents.controllers.admin.applyright;

import org.hibernate.validator.constraints.NotEmpty;

public class ExpApplyRightForm {
	@NotEmpty
	private String expname;
	@NotEmpty
	private String expgender;
	@NotEmpty
	private String expcollege;
	@NotEmpty
	private String expschool;
	@NotEmpty
	private String expresearch;
	@NotEmpty
	private String exptitle;
	public String getExpname() {
		return expname;
	}
	public void setExpname(String expname) {
		this.expname = expname;
	}
	public String getExpgender() {
		return expgender;
	}
	public void setExpgender(String expgender) {
		this.expgender = expgender;
	}
	public String getExpcollege() {
		return expcollege;
	}
	public void setExpcollege(String expcollege) {
		this.expcollege = expcollege;
	}
	public String getExpschool() {
		return expschool;
	}
	public void setExpschool(String expschool) {
		this.expschool = expschool;
	}
	public String getExpresearch() {
		return expresearch;
	}
	public void setExpresearch(String expresearch) {
		this.expresearch = expresearch;
	}
	public String getExptitle() {
		return exptitle;
	}
	public void setExptitle(String exptitle) {
		this.exptitle = exptitle;
	}
	protected ExpApplyRightForm() {
		super();
	}
	
	
}
