package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class PurchaseHistoryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -417762141277212901L;
	
	private Key key;
	private String formattedDate,txnRef, formattedTotalPrice, formattedUnitPrice;
	List<ScheduleWorkshopDisplay> list;
	public Key getKey() {
		return key;
	}
	public void setKey(Key key) {
		this.key = key;
	}
	public String getFormattedDate() {
		return formattedDate;
	}
	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public String getFormattedTotalPrice() {
		return formattedTotalPrice;
	}
	public void setFormattedTotalPrice(String formattedTotalPrice) {
		this.formattedTotalPrice = formattedTotalPrice;
	}
	public String getFormattedUnitPrice() {
		return formattedUnitPrice;
	}
	public void setFormattedUnitPrice(String formattedUnitPrice) {
		this.formattedUnitPrice = formattedUnitPrice;
	}
	public List<ScheduleWorkshopDisplay> getList() {
		return list;
	}
	public void setList(List<ScheduleWorkshopDisplay> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "PurchaseHistoryBean [key=" + key + ", formattedDate="
				+ formattedDate + ", txnRef=" + txnRef
				+ ", formattedTotalPrice=" + formattedTotalPrice
				+ ", formattedUnitPrice=" + formattedUnitPrice + ", list="
				+ list + "]";
	}
	
	

}
