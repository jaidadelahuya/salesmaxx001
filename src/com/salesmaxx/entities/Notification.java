package com.salesmaxx.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6437016302250105108L;

	@Id
	private Key userId;

	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private boolean specialPromotion, newsLetters, askToReview,
			courseRecommendation, noSend;

	public Key getUserId() {
		return userId;
	}

	public void setUserId(Key userId) {
		this.userId = userId;
	}

	public boolean isSpecialPromotion() {
		return specialPromotion;
	}

	public void setSpecialPromotion(boolean specialPromotion) {
		this.specialPromotion = specialPromotion;
	}

	public boolean isNewsLetters() {
		return newsLetters;
	}

	public void setNewsLetters(boolean newsLetters) {
		this.newsLetters = newsLetters;
	}

	public boolean isAskToReview() {
		return askToReview;
	}

	public void setAskToReview(boolean askToReview) {
		this.askToReview = askToReview;
	}

	public boolean isCourseRecommendation() {
		return courseRecommendation;
	}

	public void setCourseRecommendation(boolean courseRecommendation) {
		this.courseRecommendation = courseRecommendation;
	}

	public boolean isNoSend() {
		return noSend;
	}

	public void setNoSend(boolean noSend) {
		this.noSend = noSend;
	}

	public Notification(long userId) {
		this.userId = KeyFactory.createKey(Notification.class.getSimpleName(), String.valueOf(userId));
	}

	public Notification(long userId, boolean specialPromotion,
			boolean newsLetters, boolean askToReview,
			boolean courseRecommendation, boolean noSend) {
		this(userId);
		this.specialPromotion = specialPromotion;
		this.newsLetters = newsLetters;
		this.askToReview = askToReview;
		this.courseRecommendation = courseRecommendation;
		this.noSend = noSend;
	}

	@Override
	public String toString() {
		return "Notification [userId=" + userId + ", specialPromotion="
				+ specialPromotion + ", newsLetters=" + newsLetters
				+ ", askToReview=" + askToReview + ", courseRecommendation="
				+ courseRecommendation + ", noSend=" + noSend + "]";
	}
	
	
	
}
