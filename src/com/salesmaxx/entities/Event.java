package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -857098595890480310L;

	@Id
	private Key eventId;
	private Date startDate, endDate;
	private String venue;
	private Key location;
	private String time;
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private Key template;
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private int noEnrolled;
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private BlobKey  flyer;

	@Basic
	private List<String> speakers;

	public Event(Key template, Date startDate, Date endDate,
			String venue, List<String> speakers) {
		this.template = template;
		this.startDate = startDate;
		this.endDate = endDate;
		this.venue = venue;
		this.speakers = speakers;
		
		this.eventId = KeyFactory.createKey(Event.class.getSimpleName(),
				template.getName() + venue
						+ this.startDate.getTime());
	}
	
	

	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}



	public Key getTemplate() {
		return template;
	}

	public void setTemplate(Key template) {
		this.template = template;
	}
	public BlobKey getFlyer() {
		return flyer;
	}

	public void setFlyer(BlobKey flyer) {
		this.flyer = flyer;
	}

	public int getNoEnrolled() {
		return noEnrolled;
	}

	public void setNoEnrolled(int noEnrolled) {
		this.noEnrolled = noEnrolled;
	}

	public String getVenue() {
		return venue;
	}


	public void setVenue(String venue) {
		this.venue = venue;
	}


	public Key getEventId() {
		return eventId;
	}

	public void setEventId(Key eventId) {
		this.eventId = eventId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Key getLocation() {
		return location;
	}

	public void setLocation(Key location) {
		this.location = location;
	}

	public List<String> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<String> speakers) {
		this.speakers = speakers;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", venue=" + venue + ", location="
				+ location + ", time=" + time + ", template=" + template
				+ ", noEnrolled=" + noEnrolled + ", flyer=" + flyer
				+ ", speakers=" + speakers + "]";
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventId == null) ? 0 : eventId.hashCode());
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
		Event other = (Event) obj;
		if (eventId == null) {
			if (other.eventId != null)
				return false;
		} else if (!eventId.equals(other.eventId))
			return false;
		return true;
	}

}
