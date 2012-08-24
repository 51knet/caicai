package com.knet51.ccweb.jpa.entities;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.util.Assert;


/**
 * A User.
 * 
 * @author 
 */
@Entity
@Table(name="Usr")
public class User extends AbstractEntity {

	@Column(unique = true)
	private EmailAddress emailAddress;

	//@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	//@JoinColumn(name = "customer_id")
	//private Set<Address> addresses = new HashSet<Address>();
	
	private String name;
	private String password;
	private Boolean gender;
	private Integer role;
	private Date register_date;
	private Date last_login_date;
	private Integer level;
	private String cell_phone;
	private String fix_phone;
	  
	/**
	 * Creates a new {@link User} from the given name and password.
	 * 
	 * @param name must not be {@literal null} or empty.
	 * @param password must not be {@literal null} or empty.
	 */
	public User(String name, String password) {

		Assert.hasText(name);
		Assert.hasText(password);

		this.name = name;
		this.password = password;
	}

	protected User() {

	}

//	/**
//	 * Adds the given {@link Address} to the {@link User}.
//	 * 
//	 * @param address must not be {@literal null}.
//	 */
//	public void add(Address address) {
//
//		Assert.notNull(address);
//		this.addresses.add(address);
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Date getRegister_date() {
		return register_date;
	}

	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}

	public Date getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(Date last_login_date) {
		this.last_login_date = last_login_date;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getCell_phone() {
		return cell_phone;
	}

	public void setCell_phone(String cell_phone) {
		this.cell_phone = cell_phone;
	}

	public String getFix_phone() {
		return fix_phone;
	}

	public void setFix_phone(String fix_phone) {
		this.fix_phone = fix_phone;
	}
//
//	public void setAddresses(Set<Address> addresses) {
//		this.addresses = addresses;
//	}

	/**
	 * Returns the {@link EmailAddress} of the {@link User}.
	 * 
	 * @return
	 */
	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the {@link User}'s {@link EmailAddress}.
	 * 
	 * @param emailAddress must not be {@literal null}.
	 */
	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}

//	/**
//	 * Return the {@link User}'s addresses.
//	 * 
//	 * @return
//	 */
//	public Set<Address> getAddresses() {
//		return Collections.unmodifiableSet(addresses);
//	}
}
