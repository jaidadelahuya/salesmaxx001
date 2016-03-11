package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.salesmaxx.entities.PurchaseableItem;

public class InvoiceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2241308431786167831L;
	
	private String txnRef, total;
	private String date;
	private List<ScheduleWorkshopDisplay> items;
	@Override
	public String toString() {
		return "InvoiceBean [tnxRef=" + txnRef + ", total=" + total + ", date="
				+ date + ", items=" + items + "]";
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String tnxRef) {
		this.txnRef = tnxRef;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public List<ScheduleWorkshopDisplay> getItems() {
		return items;
	}
	public void setItems(List<ScheduleWorkshopDisplay> items) {
		this.items = items;
	}
	
	
	

}
