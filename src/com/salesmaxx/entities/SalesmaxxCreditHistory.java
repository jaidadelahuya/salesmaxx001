package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class SalesmaxxCreditHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 698833063840885576L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private String title;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private double amount;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private double creditRecieved;
	
	private Date ExpiryDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
		return ExpiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		ExpiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "SalesmaxxCreditHistory [id=" + id + ", title=" + title
				+ ", amount=" + amount + ", creditRecieved=" + creditRecieved
				+ ", ExpiryDate=" + ExpiryDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesmaxxCreditHistory other = (SalesmaxxCreditHistory) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
