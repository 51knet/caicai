package com.knet51.ccweb.jpa.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * A User.
 * 
 * @author
 */
@Entity
@Table(name = "Usr")
@JsonIgnoreProperties(value={"announcement","receiveMsg","sendMsg"})
public class User extends AbstractEntity {

	@Override
	public String toString() {
		return "User [email=" + email + ", randomUrl=" + randomUrl + ", name="
				+ name + ", password=" + password + ", gender=" + gender
				+ ", role=" + role + ", register_date=" + register_date
				+ ", last_login_date=" + last_login_date + ", level=" + level
				+ ", cell_phone=" + cell_phone + ", fix_phone=" + fix_phone
				+ ", self_url=" + self_url + ", address=" + address + ", fax="
				+ fax + ", qq=" + qq + ", msn=" + msn + ", photo_url="
				+ photo_url + ", id=" + getId() + "]";
	}

	private String email;
	private String randomUrl;
	private String forgotPsw;
	private String name;
	private String password;
	private String gender;
	private String role;
	private Date register_date;
	private Date last_login_date;
	private Integer level;
	private String cell_phone;
	private String fix_phone;
	private String self_url;
	private String address;
	private String fax;
	private String qq;
	private String msn;
	
	
	
	private String photo_url;
	public User(String email, String password,
			String role, Integer level) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.level = level;
	}


	/**
	 * Creates a new {@link User} from the given name and password.
	 * 
	 * @param mail
	 *            must not be {@literal null} or empty.
	 * @param password
	 *            must not be {@literal null} or empty.
	 */
	public User(String mail, String password) {

		this.email = mail;
		this.password = password;
		this.role = "user";
		this.level = 0;
	}


	public  User() {

	}

	// /**
	// * Adds the given {@link Address} to the {@link User}.
	// *
	// * @param address must not be {@literal null}.
	// */
	// public void add(Address address) {
	//
	// Assert.notNull(address);
	// this.addresses.add(address);
	// }
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
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

	public String getRandomUrl() {
		return randomUrl;
	}

	public void setRandomUrl(String randomUrl) {
		this.randomUrl = randomUrl;
	}
	
	public String getForgotPsw() {
		return forgotPsw;
	}


	public void setForgotPsw(String forgotPsw) {
		this.forgotPsw = forgotPsw;
	}

	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	public String getSelf_url() {
		return self_url;
	}

	public void setSelf_url(String self_url) {
		this.self_url = self_url;
	}

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getFax() {
		return fax;
	}


	public void setFax(String fax) {
		this.fax = fax;
	}


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public String getMsn() {
		return msn;
	}


	public void setMsn(String msn) {
		this.msn = msn;
	}

}
