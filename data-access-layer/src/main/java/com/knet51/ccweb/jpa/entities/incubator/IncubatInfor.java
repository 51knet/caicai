package com.knet51.ccweb.jpa.entities.incubator;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.knet51.ccweb.jpa.entities.User;

@Entity
public class IncubatInfor {
	@Id
	private Long id;
	
	private String financSuppt;
	private String venueSuppt;
	private String revenueSuppt;
	private String accommodatSuppt;
	private String otherSuppt;
	@Lob
	private String incubatInfor;
	private String industry;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="id")
	private User user;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getFinancSuppt() {
		return financSuppt;
	}
	public void setFinancSuppt(String financSuppt) {
		this.financSuppt = financSuppt;
	}
	public String getVenueSuppt() {
		return venueSuppt;
	}
	public void setVenueSuppt(String venueSuppt) {
		this.venueSuppt = venueSuppt;
	}
	public String getRevenueSuppt() {
		return revenueSuppt;
	}
	public void setRevenueSuppt(String revenueSuppt) {
		this.revenueSuppt = revenueSuppt;
	}
	public String getAccommodatSuppt() {
		return accommodatSuppt;
	}
	public void setAccommodatSuppt(String accommodatSuppt) {
		this.accommodatSuppt = accommodatSuppt;
	}
	public String getOtherSuppt() {
		return otherSuppt;
	}
	public void setOtherSuppt(String otherSuppt) {
		this.otherSuppt = otherSuppt;
	}
	public String getIncubatInfor() {
		return incubatInfor;
	}
	public void setIncubatInfor(String incubatInfor) {
		this.incubatInfor = incubatInfor;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public IncubatInfor() {
		super();
	}
	public IncubatInfor(User user) {
		this.user = user;
		setId(user.getId());
	}
	
	
	
	
}
