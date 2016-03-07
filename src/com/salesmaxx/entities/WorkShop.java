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
public class WorkShop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1577317115112477768L;

	@Id
	private Key id;

	
	private long noEnrolled;
	@Basic
	
	private List<String> facilitators;
	private Date startDate;
	private Date endDate;
	private boolean forSale;
	private String workshopType;
	private Key location;
	private BlobKey flyer;
	private String venue;
	
	

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public BlobKey getFlyer() {
		return flyer;
	}

	public void setFlyer(BlobKey flyer) {
		this.flyer = flyer;
	}

	public Key getLocation() {
		return location;
	}

	public void setLocation(Key location) {
		this.location = location;
	}


	public long getNoEnrolled() {
		return noEnrolled;
	}

	public void setNoEnrolled(long noEnrolled) {
		this.noEnrolled = noEnrolled;
	}

	public List<String> getFacilitators() {
		return facilitators;
	}

	public void setFacilitators(List<String> facilitators) {
		this.facilitators = facilitators;
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

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}

	public String getWorkshopType() {
		return workshopType;
	}

	public void setWorkshopType(String workshopType) {
		this.workshopType = workshopType;
	}

	
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "WorkShop [id=" + id + ", noEnrolled=" + noEnrolled
				+ ", facilitators=" + facilitators + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", forSale=" + forSale
				+ ", workshopType=" + workshopType + ", location=" + location
				+ ", flyer=" + flyer + "]";
	}

	

}
