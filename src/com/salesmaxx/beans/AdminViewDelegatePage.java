package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AdminViewDelegatePage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2377533457192740270L;
	private String workshopName, workshopCode, state;
	private Date date;
	List<AdminDelegateView> delegates;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public List<AdminDelegateView> getDelegates() {
		return delegates;
	}
	public void setDelegates(List<AdminDelegateView> delegates) {
		this.delegates = delegates;
	}
	@Override
	public String toString() {
		return "AdminViewDelegatePage [workshopName=" + workshopName
				+ ", workshopCode=" + workshopCode + ", state=" + state
				+ ", date=" + date + ", delegates=" + delegates + "]";
	}
	
	

}
