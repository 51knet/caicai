package com.knet51.ccweb.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
import com.knet51.ccweb.jpa.entities.teacher.TeacherPatent;
import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;

@Entity
public class Teacher {

	@Id
	private Long id;

	// @OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id")
	private User user;

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private Set<TeacherProject> project = new HashSet<TeacherProject>();

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private Set<TeacherThesis> thesis = new HashSet<TeacherThesis>();

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private Set<TeacherPatent> patent = new HashSet<TeacherPatent>();

	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private Set<TeacherHonor> Honor = new HashSet<TeacherHonor>();
	
	@OneToMany(mappedBy = "teacher", fetch = FetchType.LAZY)
	private Set<TeacherCourse> course = new HashSet<TeacherCourse>();

	private String role;
	private String title;
	private String college;
	private String school;
	private String teaching_subject;
	private String major;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	protected Teacher() {

	}

	public Teacher(User user) {
		this.user = user;
		setId(user.getId());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTeaching_subject() {
		return teaching_subject;
	}

	public void setTeaching_subject(String teaching_subject) {
		this.teaching_subject = teaching_subject;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public Set<TeacherProject> getProject() {
		return project;
	}

	public void setProject(Set<TeacherProject> project) {
		this.project = project;
	}

	public Set<TeacherThesis> getThesis() {
		return thesis;
	}

	public void setThesis(Set<TeacherThesis> thesis) {
		this.thesis = thesis;
	}

	public Set<TeacherPatent> getPatent() {
		return patent;
	}

	public void setPatent(Set<TeacherPatent> patent) {
		this.patent = patent;
	}

	public Set<TeacherHonor> getHonor() {
		return Honor;
	}

	public void setHonor(Set<TeacherHonor> honor) {
		Honor = honor;
	}

	public Set<TeacherCourse> getCourse() {
		return course;
	}

	public void setCourse(Set<TeacherCourse> course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", user=" + user + ", role=" + role
				+ ",title=" + title + " college=" + college + ", school="
				+ school + ", teaching_subject=" + teaching_subject
				+ ", major=" + major + "]";
	}

}