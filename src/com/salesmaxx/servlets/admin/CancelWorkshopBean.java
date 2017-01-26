package com.salesmaxx.servlets.admin;

import java.io.Serializable;
import java.util.Date;



public class CancelWorkshopBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6858792162141909908L;
	private String webKey,ownerID,reason, workshopName,workshopCode,workshopLocation;
	private Date cancelDate, workshopDate;
	private long NoD;
	@Override
	public String toString() {
		return "CancelWorkshopBean [webKey=" + webKey + ", ownerID=" + ownerID
				+ ", reason=" + reason + ", workshopName=" + workshopName
				+ ", workshopCode=" + workshopCode + ", workshopLocation="
				+ workshopLocation + ", cancelDate=" + cancelDate
				+ ", workshopDate=" + workshopDate + "]";
	}
	
	
	public String getWebKey() {
		return webKey;
	}
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	public String getOwnerID() {
		return ownerID;
	}
	public void setOwnerID(String ownerID) {
		this.ownerID = ownerID;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getWorkshopName() {
		return workshopName;
	}
	public void setWorkshopName(String workshopName) {
		this.workshopName = workshopName;
	}
	public String getWorkshopCode() {
		return workshopCode;
	}
	public void setWorkshopCode(String workshopCode) {
		this.workshopCode = workshopCode;
	}
	public String getWorkshopLocation() {
		return workshopLocation;
	}
	public void setWorkshopLocation(String workshopLocation) {
		this.workshopLocation = workshopLocation;
	}
	public Date getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public Date getWorkshopDate() {
		return workshopDate;
	}
	public void setWorkshopDate(Date workshopDate) {
		this.workshopDate = workshopDate;
	}
	
	public void clear() {}


	public long getNoD() {
		return NoD;
	}


	public void setNoD(long noD) {
		NoD = noD;
	}

}
