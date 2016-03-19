package com.salesmaxx.beans;

import java.io.Serializable;

public class PendingWorkshopBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 867353991747140726L;
	
	private String workshopName;
	private String workshopCode;
	private String userWebSafeKey;
	private String qty;
	private String unitPrice;
	private String totalPrice;
	private String date;
	private String location;
	
	
	@Override
	public String toString() {
		return "PendingWorkshopBean [workshopName=" + workshopName
				+ ", workshopCode=" + workshopCode + ", userWebSafeKey="
				+ userWebSafeKey + ", qty=" + qty + ", unitPrice=" + unitPrice
				+ ", totalPrice=" + totalPrice + "]";
	}
	
	
	
	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
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
	public String getUserWebSafeKey() {
		return userWebSafeKey;
	}
	public void setUserWebSafeKey(String userWebSafeKey) {
		this.userWebSafeKey = userWebSafeKey;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	
	

}
