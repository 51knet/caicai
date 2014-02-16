package com.knet51.ccweb.jpa.entities.projects;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
@Entity
public class Projects extends AbstractEntity {
	
	private String projectName;
	private String industry;//hang ye
	private String progess;//jin du
	private String companyName; 
	private String empNumber; // yuan gong ren shu
	private String location;
	private String totalMoney;// zong jin e
	private String currentMoney; // xian you jin e
	
	@Lob
	private String content; // xiang mu jian jie
	private String logoPath; // logo picture's save path
	private Date date;
	
	private Integer status; // pass:1; waite:0;
	
	@ManyToOne
	private User user;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProgess() {
		return progess;
	}

	public void setProgess(String progess) {
		this.progess = progess;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(String currentMoney) {
		this.currentMoney = currentMoney;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

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

	public Projects() {
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	 
}
