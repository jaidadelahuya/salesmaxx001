package com.salesmaxx.beans;

import java.io.Serializable;

import com.salesmaxx.entities.Address;

public class ProfileBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1035370995373726888L;
	private String firstName, lastName, regId, email, dob, website,
			salesmaxxCredits, headline, phones, picture, facebook, linkedin,
			tweeter, google, biography;
	private Address address;

	

	@Override
	public String toString() {
		return "ProfileBean [firstName=" + firstName + ", lastName=" + lastName
				+ ", regId=" + regId + ", email=" + email + ", address="
				+ address + ", dob=" + dob + ", website=" + website
				+ ", salesmaxxCredits=" + salesmaxxCredits + ", headline="
				+ headline + ", phones=" + phones + ", picture=" + picture
				+ ", facebook=" + facebook + ", linkedin=" + linkedin
				+ ", tweeter=" + tweeter + ", google=" + google
				+ ", biography=" + biography + "]";
	}
	
	

	public String getPicture() {
		return picture;
	}



	public void setPicture(String picture) {
		this.picture = picture;
	}



	public String getFacebook() {
		return facebook;
	}



	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}



	public String getLinkedin() {
		return linkedin;
	}



	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}



	public String getTweeter() {
		return tweeter;
	}



	public void setTweeter(String tweeter) {
		this.tweeter = tweeter;
	}



	public String getGoogle() {
		return google;
	}



	public void setGoogle(String google) {
		this.google = google;
	}



	public String getBiography() {
		return biography;
	}



	public void setBiography(String biography) {
		this.biography = biography;
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

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getSalesmaxxCredits() {
		return salesmaxxCredits;
	}

	public void setSalesmaxxCredits(String salesmaxxCredits) {
		this.salesmaxxCredits = salesmaxxCredits;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}

}
