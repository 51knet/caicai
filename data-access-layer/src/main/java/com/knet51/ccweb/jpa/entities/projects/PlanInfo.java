package com.knet51.ccweb.jpa.entities.projects;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class PlanInfo extends AbstractEntity {
	
	@Lob
	private String context;
	
	@OneToOne
	private Projects projects;

	public PlanInfo(){
		
	}
	
	public PlanInfo(Projects projects){
		this.projects = projects;
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

}
