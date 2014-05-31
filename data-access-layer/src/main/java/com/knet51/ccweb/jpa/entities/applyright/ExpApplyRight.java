package com.knet51.ccweb.jpa.entities.applyright;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
@Entity
public class ExpApplyRight extends AbstractEntity {
	private String expname;
	private String expgender;
	private String expcollege;
	private String expschool;
	private String expresearch;
	private String exptitle;
	private int status;
	private Date date;
	
	@ManyToOne
	private User user;

	public String getExpname() {
		return expname;
	}

	public void setExpname(String expname) {
		this.expname = expname;
	}

	public String getExpgender() {
		return expgender;
	}

	public void setExpgender(String expgender) {
		this.expgender = expgender;
	}

	public String getExpcollege() {
		return expcollege;
	}

	public void setExpcollege(String expcollege) {
		this.expcollege = expcollege;
	}

	public String getExpschool() {
		return expschool;
	}

	public void setExpschool(String expschool) {
		this.expschool = expschool;
	}

	public String getExpresearch() {
		return expresearch;
	}

	public void setExpresearch(String expresearch) {
		this.expresearch = expresearch;
	}

	public String getExptitle() {
		return exptitle;
	}

	public void setExptitle(String exptitle) {
		this.exptitle = exptitle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ExpApplyRight() {
		super();
	}

}
