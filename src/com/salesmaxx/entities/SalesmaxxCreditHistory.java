package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;

import org.datanucleus.ImplementationCreator;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.salesmaxx.persistence.controllers.EMF;



public class SalesmaxxCreditHistory implements Serializable, Comparable<SalesmaxxCreditHistory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 698833063840885576L;

	private Key id;
	
	private String title;
	private double amount;
	private double creditRecieved;
	
	private Date expiryDate;//now used as date

	
	public SalesmaxxCreditHistory() {
		KeyRange range = EMF.getDs().allocateIds(SalesmaxxCreditHistory.class.getSimpleName(), 1);
		id = range.getStart();

	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCreditRecieved() {
		return creditRecieved;
	}

	public void setCreditRecieved(double creditRecieved) {
		this.creditRecieved = creditRecieved;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "SalesmaxxCreditHistory [id=" + id + ", title=" + title
				+ ", amount=" + amount + ", creditRecieved=" + creditRecieved
				+ ", ExpiryDate=" + expiryDate + "]";
	}

	@Override
	public int compareTo(SalesmaxxCreditHistory o) {
		return this.expiryDate.compareTo(o.getExpiryDate());
	}
	
	
}
