package com.salesmaxx.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.util.Util;

@Entity
public class WorkshopTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9169170389231121428L;
	
	@Id
	private Key workshopId;
	
	private String workshopName;
	
	private String priority;
	
	private String gender;
	
	@Basic
	private List<String> skillLevel;
	
	private String catalogueLink;
	
	private String formattedPrice;
	
	
	private double price;
	
	private BlobKey workshopImage;
	
	private long noEnrolled;
	@Basic
	
	private Text shortDescription;
	@Basic
	
	private List<Key> downloadables;
	@Basic
	
	private List<Key> relatedWorkshops;
	@Basic
	
	private List<Long> reviews;
	@Basic
	
	private List<Long> assessmentQuestions;
	
	private double salesmaxxCredits;
	@Basic
	
	private List<String> industries;
	@Basic
	
	private List<String> professions;
	
	private List<String> learningOutcomes;
	
	private String format;
	
	private List<String> audiences;
	
	private List<String> courseContent;
	@Transient
	private String webSafeKey;
	private List<Key> schedules;
	private String imageUrl;
	
	
	
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getCatalogueLink() {
		return catalogueLink;
	}
	public void setCatalogueLink(String catalogueLink) {
		this.catalogueLink = catalogueLink;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((workshopId == null) ? 0 : workshopId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WorkshopTemplate other = (WorkshopTemplate) obj;
		if (workshopId == null) {
			if (other.workshopId != null)
				return false;
		} else if (!workshopId.equals(other.workshopId))
			return false;
		return true;
	}
	public List<Key> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<Key> schedules) {
		this.schedules = schedules;
	}
	public String getWebSafeKey() {
		return webSafeKey;
	}
	public void setWebSafeKey(String webSafeKey) {
		this.webSafeKey = webSafeKey;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public BlobKey getWorkshopImage() {
		return workshopImage;
	}
	public void setWorkshopImage(BlobKey workshopImage) {
		this.workshopImage = workshopImage;
		this.imageUrl = Util.getImageUrl(workshopImage);
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getWorkshopName() {
		return workshopName;
	}
	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}
	public WorkshopTemplate(String workshopId) {
		this.workshopId = KeyFactory.createKey(WorkshopTemplate.class.getSimpleName(), workshopId);
		this.webSafeKey = KeyFactory.keyToString(this.workshopId);
	}
	public Key getWorkshopId() {
		return workshopId;
	}
	public void setWorkshopId(Key workshopId) {
		this.workshopId = workshopId;
	}
	public long getNoEnrolled() {
		return noEnrolled;
	}
	public void setNoEnrolled(long noEnrolled) {
		this.noEnrolled = noEnrolled;
	}
	public Text getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(Text shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		this.formattedPrice = new DecimalFormat("#,###.00").format(price);
	}
	public List<Key> getDownloadables() {
		return downloadables;
	}
	public void setDownloadables(List<Key> downloadables) {
		this.downloadables = downloadables;
	}
	public List<String> getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(List<String> skillLevel) {
		this.skillLevel = skillLevel;
	}
	
	public List<Key> getRelatedWorkshops() {
		return relatedWorkshops;
	}
	public void setRelatedWorkshops(List<Key> relatedWorkshops) {
		this.relatedWorkshops = relatedWorkshops;
	}
	public List<Long> getReviews() {
		return reviews;
	}
	public void setReviews(List<Long> reviews) {
		this.reviews = reviews;
	}
	public List<Long> getAssessmentQuestions() {
		return assessmentQuestions;
	}
	public void setAssessmentQuestions(List<Long> assessmentQuestions) {
		this.assessmentQuestions = assessmentQuestions;
	}
	public double getSalesmaxxCredits() {
		return salesmaxxCredits;
	}
	public void setSalesmaxxCredits(double salesmaxxCredits) {
		this.salesmaxxCredits = salesmaxxCredits;
	}
	public List<String> getIndustries() {
		return industries;
	}
	public void setIndustries(List<String> industries) {
		this.industries = industries;
	}
	public List<String> getProfessions() {
		return professions;
	}
	public void setProfessions(List<String> professions) {
		this.professions = professions;
	}
	
	
	public List<String> getLearningOutcomes() {
		return learningOutcomes;
	}
	public void setLearningOutcomes(List<String> learningOutcomes) {
		this.learningOutcomes = learningOutcomes;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public List<String> getAudiences() {
		return audiences;
	}
	public void setAudiences(List<String> audiences) {
		this.audiences = audiences;
	}
	public List<String> getCourseContent() {
		return courseContent;
	}
	public void setCourseContent(List<String> courseContent) {
		this.courseContent = courseContent;
	}
	@Override
	public String toString() {
		return "WorkshopTemplate [workshopId=" + workshopId + ", workshopName="
				+ workshopName + ", priority=" + priority + ", gender="
				+ gender + ", skillLevel=" + skillLevel + ", price=" + price
				+ ", workshopImage=" + workshopImage + ", noEnrolled="
				+ noEnrolled + ", shortDescription=" + shortDescription
				+ ", downloadables=" + downloadables + ", relatedWorkshops="
				+ relatedWorkshops + ", reviews=" + reviews
				+ ", assessmentQuestions=" + assessmentQuestions
				+ ", salesmaxxCredits=" + salesmaxxCredits + ", industries="
				+ industries + ", professions=" + professions
				+ ", learningOutcomes=" + learningOutcomes + ", format="
				+ format + ", audiences=" + audiences + ", courseContent="
				+ courseContent + ", webSafeKey=" + webSafeKey + ", schedules="
				+ schedules + "]";
	}
	
	

}
