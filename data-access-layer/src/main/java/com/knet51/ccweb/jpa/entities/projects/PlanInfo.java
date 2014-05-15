package com.knet51.ccweb.jpa.entities.projects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class PlanInfo extends AbstractEntity {
	
	@Lob
	private String context;
	
	@OneToOne
	private Projects project;

	public PlanInfo(){
		
	}
	
	public PlanInfo(Projects project){
		this.project = project;
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Projects getProject() {
		return project;
	}

	public void setProject(Projects project) {
		this.project = project;
	}

}
