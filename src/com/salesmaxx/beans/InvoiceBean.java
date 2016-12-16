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
	
	private String txnRef;
	private Date date;
	private double total;
	private List<ScheduleWorkshopDisplay> items;
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public List<ScheduleWorkshopDisplay> getItems() {
		return items;
	}
	public void setItems(List<ScheduleWorkshopDisplay> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "InvoiceBean [txnRef=" + txnRef + ", date=" + date + ", total="
				+ total + ", items=" + items + "]";
	}
	
	
	
	
	
	

}
