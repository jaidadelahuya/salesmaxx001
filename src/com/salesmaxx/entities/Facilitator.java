package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Text;

@Entity
public class Facilitator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8443529320191819155L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String firstName, lastName;
	private BlobKey picture;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private Text profile;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<String> education;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<String> certification;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<String> workExperience;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<String> recentDevelopment;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private List<String> specialization;
	
	


	@Override
	public String toString() {
		return "Facilitator [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", picture=" + picture
				+ ", profile=" + profile + ", education=" + education
				+ ", certification=" + certification + ", workExperience="
				+ workExperience + ", recentDevelopment=" + recentDevelopment
				+ ", specialization=" + specialization + "]";
	}

	

	public Text getProfile() {
		return profile;
	}



	public void setProfile(Text profile) {
		this.profile = profile;
	}



	public BlobKey getPicture() {
		return picture;
	}


	public void setPicture(BlobKey picture) {
		this.picture = picture;
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


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<String> getEducation() {
		return education;
	}
	public void setEducation(List<String> education) {
		this.education = education;
	}
	public List<String> getCertification() {
		return certification;
	}
	public void setCertification(List<String> certification) {
		this.certification = certification;
	}
	public List<String> getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(List<String> workExperience) {
		this.workExperience = workExperience;
	}
	public List<String> getRecentDevelopment() {
		return recentDevelopment;
	}
	public void setRecentDevelopment(List<String> recentDevelopment) {
		this.recentDevelopment = recentDevelopment;
	}
	public List<String> getSpecialization() {
		return specialization;
	}
	public void setSpecialization(List<String> specialization) {
		this.specialization = specialization;
	}
	
	
}
