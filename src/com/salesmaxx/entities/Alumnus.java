package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Alumnus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1976529618524684855L;
	
	private String title,firstName,lastName,email,altEmail,phone1,phone2,company;
	private List<String> programsAttended;
	private Date birthDate;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAltEmail() {
		return altEmail;
	}
	public void setAltEmail(String altEmail) {
		this.altEmail = altEmail;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public List<String> getProgramsAttended() {
		return programsAttended;
	}
	public void setProgramsAttended(List<String> programsAttended) {
		this.programsAttended = programsAttended;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Override
	public String toString() {
		return "Alumnus [title=" + title + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", emai=" + email + ", altEmail="
				+ altEmail + ", phone1=" + phone1 + ", phone2=" + phone2
				+ ", company=" + company + ", programsAttended="
				+ programsAttended + ", birthDate=" + birthDate + "]";
	}
	
	

}
