package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.util.Util;

@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Key regId;

	private String username;

	private String password;
	
	private long generalInfoId;

	private String firstName;
	
	private boolean alumnus;
	
	private String linkedInId,facebookId,twitterId,googleId;
	
	private Set<String> emails;
	
	@Transient
	private boolean authenticated;

	private String lastName;
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String headline;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private BlobKey picture;

	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String gender;

	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private double salesmaxxCredit;

	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private UserRole role;

	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<User> users;// for organisation
	
	@Transient
	private String pictureUrl = "/images/unknown-user.jpg";
	
	private Key cart;
	
	
	
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
		String regCode = newRegCode(firstName,lastName);
		this.regId = KeyFactory.createKey(User.class.getSimpleName(), regCode);
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public User() {
		// TODO Auto-generated constructor stub
	}


	private String newRegCode(String firstName2, String lastName2) {
		String code = Util.generateRandomCode(100000, 999999);
		code = firstName2.substring(0,2)+code+lastName2.substring(0,2);
		return code.toUpperCase();
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
