package com.knet51.ccweb.jpa.entities.projects;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
@Entity
public class Projects extends AbstractEntity {
	
	private String projectName;
	private String industry;//hang ye
	private String progress;//jin du
	private String companyName; 
	private String empNumber; // yuan gong ren shu
	private String location;
	private String boss;
	private String phone;
	private Long totalMoney;// zong jin e
	private Long currentMoney; // xian you jin e
	
	@Lob
	private String content; // xiang mu jian jie
	private String logoPath; // logo picture's save path
	private Date date;
	
	private Integer status; // pass:1; waite:0;
	private Integer maxInvestNum;
	private Integer currentInvestNum;
	private Integer minMoney;
	
	@Version
	private Integer version; 
	
	
	@ManyToOne
	private User user;
	
	
	
	public Integer getCurrentInvestNum() {
		return currentInvestNum;
	}

	public void setCurrentInvestNum(Integer currentInvestNum) {
		this.currentInvestNum = currentInvestNum;
	}

	private Integer complete ;// complete:1; none:0;
	public Integer getMaxInvestNum() {
		return maxInvestNum;
	}

	public void setMaxInvestNum(Integer maxInvestNum) {
		this.maxInvestNum = maxInvestNum;
	}

	public Integer getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(Integer minMoney) {
		this.minMoney = minMoney;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
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



	public Long getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Long getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(Long currentMoney) {
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

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public Integer getComplete() {
		return complete;
	}

	public void setComplete(Integer complete) {
		this.complete = complete;
	}
	
	
	
	 
}
