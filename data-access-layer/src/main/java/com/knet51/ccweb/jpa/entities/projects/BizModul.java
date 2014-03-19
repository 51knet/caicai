package com.knet51.ccweb.jpa.entities.projects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class BizModul extends AbstractEntity {
	
	@Lob
	private String targetUser;
	@Lob
	private String targetReq;
	@Lob
	private String modulIntro;
	@Lob
	private String profitModul;
	@Lob
	private String competitorIntro;
	@Lob
	private String coreValueIntro;
	
	@OneToOne
	private Projects projects;

	public BizModul(){
		
	}
	
	public BizModul(Projects projects){
		this.projects = projects;
	}
	
	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public String getTargetReq() {
		return targetReq;
	}

	public void setTargetReq(String targetReq) {
		this.targetReq = targetReq;
	}

	public String getModulIntro() {
		return modulIntro;
	}

	public void setModulIntro(String modulIntro) {
		this.modulIntro = modulIntro;
	}

	public String getProfitModul() {
		return profitModul;
	}

	public void setProfitModul(String profitModul) {
		this.profitModul = profitModul;
	}

	public String getCompetitorIntro() {
		return competitorIntro;
	}

	public void setCompetitorIntro(String competitorIntro) {
		this.competitorIntro = competitorIntro;
	}

	public String getCoreValueIntro() {
		return coreValueIntro;
	}

	public void setCoreValueIntro(String coreValueIntro) {
		this.coreValueIntro = coreValueIntro;
	}

	public Projects getProjects() {
		return projects;
	}

	public void setProjects(Projects projects) {
		this.projects = projects;
	}

}
