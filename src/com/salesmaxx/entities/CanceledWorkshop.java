package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.persistence.controllers.EMF;

public class CanceledWorkshop implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309456050906196779L;
	
	private Key id;
	private Key userid;
	private Date cancelDate;
	private String workshopName, workshopDate, workshopLocation, imageUrl, formattedCanceledDate;
	private Text reason;
	private long noOfDelegates, edNoOfDelegates;
	private double amount;
	private String workshopId;
	private boolean cleared;
	private boolean reschedule;
	
	
	public boolean isReschedule() {
		return reschedule;
	}
	public void setReschedule(boolean reschedule) {
		this.reschedule = reschedule;
	}
	public boolean isCleared() {
		return cleared;
	}
	public void setCleared(boolean cleared) {
		this.cleared = cleared;
	}
	public String getWorkshopId() {
		return workshopId;
	}
	public void setWorkshopId(String workshopId) {
		this.workshopId = workshopId;
	}
	public String getFormattedCanceledDate() {
		return formattedCanceledDate;
	}
	public void setFormattedCanceledDate(String formattedCanceledDate) {
		this.formattedCanceledDate = formattedCanceledDate;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public long getEdNoOfDelegates() {
		return edNoOfDelegates;
	}
	public void setEdNoOfDelegates(long edNoOfDelegates) {
		this.edNoOfDelegates = edNoOfDelegates;
	}
	public Key getUserid() {
		return userid;
	}
	public void setUserid(Key userid) {
		this.userid = userid;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getWorkshopName() {
		return workshopName;
	}
	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}
	public String getWorkshopDate() {
		return workshopDate;
	}
	public void setWorkshopDate(String workshopDate) {
		this.workshopDate = workshopDate;
	}
	public String getWorkshopLocation() {
		return workshopLocation;
	}
	public void setWorkshopLocation(String workshopLocation) {
		this.workshopLocation = workshopLocation;
	}
	public Text getReason() {
		return reason;
	}
	public void setReason(Text reason) {
		this.reason = reason;
	}
	public long getNoOfDelegates() {
		return noOfDelegates;
	}
	public void setNoOfDelegates(long noOfDelegates) {
		this.noOfDelegates = noOfDelegates;
	}
	public CanceledWorkshop(Key userid) {
		
		this.userid = userid;
		this.id =  EMF.getDs().allocateIds(CanceledWorkshop.class.getSimpleName(), 1).getStart();
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CanceledWorkshop [id=" + id + ", userid=" + userid
				+ ", cancelDate=" + cancelDate + ", workshopName="
				+ workshopName + ", workshopDate=" + workshopDate
				+ ", workshopLocation=" + workshopLocation + ", reason="
				+ reason + ", noOfDelegates=" + noOfDelegates + ", amount="
				+ amount + "]";
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	

}
