package com.graphene.web.jpa.entity.resume;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.graphene.web.jpa.entity.AbstractEntity;

@Entity
@Table(name="work_experience")
public class WorkExp extends AbstractEntity {
	private String company;
	private String department;
	private String position;
	private String startTime;
	private String endTime;
	@Lob
	private String workDesc;
	private Long teacherid;
	
	private String forbidden;
	
	
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getTeacherid() {
		return teacherid;
	}
	public void setTeacherid(Long teacherid) {
		this.teacherid = teacherid;
	}
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	public WorkExp() {
		super();
	}
	
	
}
