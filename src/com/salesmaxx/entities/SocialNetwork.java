package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class SocialNetwork implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1139885321388708564L;
	@Id
	private Key userId;
	@Basic
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	List<SocialMedia> socialMedium;
	
	public SocialNetwork(long userId) {
		this.userId = KeyFactory.createKey(SocialNetwork.class.getSimpleName(), String.valueOf(userId));
	}

	public SocialNetwork(Key userId, List<SocialMedia> socialMedium) {
		super();
		this.userId = userId;
		this.socialMedium = socialMedium;
	}

	public Key getUserId() {
		return userId;
	}

	public void setUserId(Key userId) {
		this.userId = userId;
	}

	public List<SocialMedia> getSocialMedium() {
		return socialMedium;
	}

	public void setSocialMedium(List<SocialMedia> socialMedium) {
		this.socialMedium = socialMedium;
	}

	@Override
	public String toString() {
		return "SocialNetworks [userId=" + userId + ", socialMedium="
				+ socialMedium + "]";
	}
	
}
