package com.knet51.diplomat.controllers.admin.incubator;


import org.hibernate.validator.constraints.NotEmpty;

public class IncubatorForm {
	@NotEmpty
	private String financSuppt;
	@NotEmpty
	private String venueSuppt;
	@NotEmpty
	private String revenueSuppt;
	@NotEmpty
	private String accommodatSuppt;
	@NotEmpty
	private String otherSuppt;
	@NotEmpty
	private String incubatInfor;
	@NotEmpty
	private String industry;
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
	protected IncubatorForm() {
		super();
	}
	
}
