package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class PurchaseHistoryBean implements Serializable, Comparable<PurchaseHistoryBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -417762141277212901L;
	
	
	private String txnRef, webKey;
	List<ScheduleWorkshopDisplay> list;
	private Date date;
	private double unitPrice, total;
	
	
	
	public String getWebKey() {
		return webKey;
	}
	public void setWebKey(String webKey) {
		this.webKey = webKey;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	
	public List<ScheduleWorkshopDisplay> getList() {
		return list;
	}
	public void setList(List<ScheduleWorkshopDisplay> list) {
		this.list = list;
	}
	
	@Override
	public int compareTo(PurchaseHistoryBean o) {
		
		return this.date.compareTo(o.getDate());
	}
	
	

}
