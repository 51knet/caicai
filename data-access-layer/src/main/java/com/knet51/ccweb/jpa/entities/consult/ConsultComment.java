package com.knet51.ccweb.jpa.entities.consult;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class ConsultComment extends AbstractEntity {
	@Lob
	private String content;
	private Long score;
	private Date date;
	@ManyToOne
	private ConsultOrder order;
	@ManyToOne
	private User teacher;
	@ManyToOne
	private User consulter;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public User getTeacher() {
		return teacher;
	}
	public void setTeacher(User teacher) {
		this.teacher = teacher;
	}
	public User getConsulter() {
		return consulter;
	}
	public void setConsulter(User consulter) {
		this.consulter = consulter;
	}
	public ConsultOrder getOrder() {
		return order;
	}
	public void setOrder(ConsultOrder order) {
		this.order = order;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
