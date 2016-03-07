package com.salesmaxx.entities;

import java.io.Serializable;

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
public class EventTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1213563456612602688L;

	@Id
	private Key eventId;
	
	private String name;
	
	@Basic
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private BlobKey image;
	@Basic
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private Text shortDescription;
	@Transient
	private String imageUrl;
	@Transient
	private String webSafeKey;
	private double salesmaxxCredit;

	public EventTemplate(String eventId) {
		this.eventId = KeyFactory.createKey(EventTemplate.class.getSimpleName(), eventId);
		this.webSafeKey = KeyFactory.keyToString(this.eventId);
	}
	
	
	
	public String getWebSafeKey() {
		return webSafeKey;
	}



	public void setWebSafeKey(String webSafeKey) {
		this.webSafeKey = webSafeKey;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public Key getEventId() {
		return eventId;
	}

	public void setEventId(Key eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BlobKey getImage() {
		return image;
	}

	public void setImage(BlobKey image) {
		this.image = image;
		this.imageUrl = Util.getImageUrl(image);
	}

	public Text getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(Text shortDescription) {
		this.shortDescription = shortDescription;
	}



	public double getSalesmaxxCredit() {
		return salesmaxxCredit;
	}

	public void setSalesmaxxCredit(double salesmaxxCredit) {
		this.salesmaxxCredit = salesmaxxCredit;
	}
	
	

	@Override
	public String toString() {
		return "EventTemplate [eventId=" + eventId + ", name=" + name
				+ ", image=" + image + ", shortDescription=" + shortDescription
				+ ", imageUrl=" + imageUrl + ", webSafeKey=" + webSafeKey
				+ ", salesmaxxCredit=" + salesmaxxCredit + "]";
	}
	
}
