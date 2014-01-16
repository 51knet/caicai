package com.knet51.ccweb.jpa.entities.technology;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class Technology extends AbstractEntity {
	private String techName;
	private String techField;
	private String department;// fa ming dan wei
	private String inventer;// yong you zhe
	private String phone;
	private String techType;//xiang mu lei xing "863"
	@Lob
	private String content;// jian jie
	private String advantage;// you shi
	private String maturity;// cheng shu du
	private String progress;// jin zhan, jin du
	@Lob
	private String achievement;// zhuan li cheng guo
	private String applyArea;// ying yong ling yu
	private String cooperation;// he zuo fang shi
	private String demand;
	private Integer status;
	@ManyToOne
	private User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
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

	public Technology() {
		super();
	}
	
	
	
	
}
