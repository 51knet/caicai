package com.knet51.ccweb.jpa.entities.projects;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class TeamInfo extends AbstractEntity {
	
	@Lob
	private String shareholderIntro;
	@Lob
	private String unShareholderIntro;
	
	@OneToOne
	private Projects projects;

	public TeamInfo(){
		
	}
	
	public TeamInfo(Projects projects){
		this.projects = projects;
	}
	
	public String getShareholderIntro() {
		return shareholderIntro;
	}

	public void setShareholderIntro(String shareholderIntro) {
		this.shareholderIntro = shareholderIntro;
	}

	public String getUnShareholderIntro() {
		return unShareholderIntro;
	}

	public void setUnShareholderIntro(String unShareholderIntro) {
		this.unShareholderIntro = unShareholderIntro;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

}
