package com.knet51.patents.controllers.admin.technology;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TechnologyForm {
	@NotEmpty
	private String techName;
	@NotEmpty
	private String techField;
	
	private String department;// fa ming dan wei
	@NotEmpty
	private String inventer;// yong you zhe
	@NotEmpty
	@Size(min=7,max=11)
	private String phone;
	private String techType;//xiang mu lei xing "863"
	
	private String content;// jian jie
	private String advantage;// you shi
	
	private String maturity;// cheng shu du
	private String progress;// jin zhan, jin du
	@NotEmpty
	private String achievement;// zhuan li cheng guo
	@NotEmpty
	private String applyArea;// ying yong ling yu
	@NotEmpty
	private String cooperation;// he zuo fang shi
	private String demand;
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}
	public String getTechField() {
		return techField;
	}
	public void setTechField(String techField) {
		this.techField = techField;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getInventer() {
		return inventer;
	}
	public void setInventer(String inventer) {
		this.inventer = inventer;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTechType() {
		return techType;
	}
	public void setTechType(String techType) {
		this.techType = techType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getMaturity() {
		return maturity;
	}
	public void setMaturity(String maturity) {
		this.maturity = maturity;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getApplyArea() {
		return applyArea;
	}
	public void setApplyArea(String applyArea) {
		this.applyArea = applyArea;
	}
	public String getCooperation() {
		return cooperation;
	}
	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
	}
	protected TechnologyForm() {
		super();
	}
	
	
}
