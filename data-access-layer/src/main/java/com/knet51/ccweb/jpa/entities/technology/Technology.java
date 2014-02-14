package com.knet51.ccweb.jpa.entities.technology;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class Technology extends AbstractEntity {
	private String techName;// xiang mu ming cheng
	private String techField;//suo shu ling yu
	private String techType;//xiang mu lei xing:"863"
	
	private String maturity;// cheng shu du
	private String progress;// jin zhan, jin du
	
	private String applyArea;// ying yong ling yu
	private String cooperation;// he zuo fang shi
	private String demand; //dui qi ye yao qiu
	
	@Lob
	private String achievement;// zhuan li cheng guo
	@Lob
	private String contents;// jian jie
	@Lob
	private String advantage;// you shi
	
	private String department;// fa ming dan wei
	private String inventer;// yong you zhe
	private String phone;
	
	private Date date;
	private Integer status;
	private Integer focus;

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

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Technology() {
		super();
	}

	public Integer getFocus() {
		return focus;
	}

	public void setFocus(Integer focus) {
		this.focus = focus;
	}
	
	
	
	
}
