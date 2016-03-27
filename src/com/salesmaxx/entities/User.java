package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.util.Util;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 443818419178265061L;

	private Key regId;

	private String username;
	
	private String primaryPhone;

	private String password;
	
	private long generalInfoId;

	private String firstName;
	
	private boolean alumnus;
	
	private String linkedInId,facebookId,twitterId,googleId;
	
	private Set<String> emails;
	
	private boolean authenticated;
	
	private boolean phoneVerified;

	private String lastName;
	private String headline;
	private BlobKey picture;
	private String gender;
	private double salesmaxxCredit;
	private UserRole role;
	private List<User> users;// for organisation
	
	private String pictureUrl = "/images/unknown-user.jpg";
	
	private Key cart;
	
	public String getPrimaryPhone() {
		return primaryPhone;
	}
	
	

	public boolean isPhoneVerified() {
		return phoneVerified;
	}



	public void setPhoneVerified(boolean phoneVerified) {
		this.phoneVerified = phoneVerified;
	}



	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}


	public Set<String> getEmails() {
		return emails;
	}


	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}


	public String getLinkedInId() {
		return linkedInId;
	}


	public void setLinkedInId(String linkedInId) {
		this.linkedInId = linkedInId;
	}


	public String getFacebookId() {
		return facebookId;
	}


	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}


	public String getTwitterId() {
		return twitterId;
	}


	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}


	public String getGoogleId() {
		return googleId;
	}


	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}


	public boolean isAuthenticated() {
		return authenticated;
	}


	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}


	public Key getCart() {
		return cart;
	}


	public void setCart(Key cart) {
		this.cart = cart;
	}


	public User(String firstName, String lastName) {
		String regCode = Util.newRegCode(firstName,lastName);
		this.regId = KeyFactory.createKey(User.class.getSimpleName(), regCode);
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getPictureUrl() {
		if(pictureUrl == null) {
			return "/images/unknown-user.jpg";
		} else {
			return pictureUrl;
		}
	}


	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}


	public long getGeneralInfoId() {
		return generalInfoId;
	}

	public void setGeneralInfoId(long generalInfoId) {
		this.generalInfoId = generalInfoId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Key getRegId() {
		return regId;
	}

	public void setRegId(Key regId) {
		this.regId = regId;
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

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public BlobKey getPicture() {
		return picture;
	}

	public void setPicture(BlobKey picture) {
		this.picture = picture;
		this.pictureUrl = Util.getImageUrl(picture);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getSalesmaxxCredit() {
		return salesmaxxCredit;
	}

	public void setSalesmaxxCredit(double salesmaxxCredit) {
		this.salesmaxxCredit = salesmaxxCredit;
	}

	public boolean isAlumnus() {
		return alumnus;
	}

	public void setAlumnus(boolean alumnus) {
		this.alumnus = alumnus;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
	

	@Override
	public String toString() {
		return "User [regId=" + regId + ", username=" + username
				+ ", password=" + password + ", generalInfoId=" + generalInfoId
				+ ", firstName=" + firstName + ", alumnus=" + alumnus
				+ ", linkedInId=" + linkedInId + ", facebookId=" + facebookId
				+ ", twitterId=" + twitterId + ", googleId=" + googleId
				+ ", emails=" + emails + ", authenticated=" + authenticated
				+ ", lastName=" + lastName + ", headline=" + headline
				+ ", picture=" + picture + ", gender=" + gender
				+ ", salesmaxxCredit=" + salesmaxxCredit + ", role=" + role
				+ ", users=" + users + ", pictureUrl=" + pictureUrl + ", cart="
				+ cart + "]";
	}

	
}
