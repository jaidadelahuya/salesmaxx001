package com.salesmaxx.entities;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

public class PurchaseHistory implements Serializable, Comparable<PurchaseHistory>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5797568935189979260L;
	
	@Id
	private Key id;

	private String txnRef;

	private double total;

	private String status;

	private Date purchaseDate;

	private String purchaseType;
	
	private List<Key> items;

	
	
	
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}
	
	

	public void setTotal(double total) {
		this.total = total;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public List<Key> getItems() {
		return items;
	}

	public void setItems(List<Key> items) {
		this.items = items;
	}

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTxnRef() {
		return txnRef;
	}

	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}

	public double getTotal() {
		return total;
	}

	

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((txnRef == null) ? 0 : txnRef.hashCode());
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
		PurchaseHistory other = (PurchaseHistory) obj;
		if (txnRef == null) {
			if (other.txnRef != null)
				return false;
		} else if (!txnRef.equals(other.txnRef))
			return false;
		return true;
	}

	@Override
	public int compareTo(PurchaseHistory o) {
		return o.getPurchaseDate().compareTo(purchaseDate);
	}

}
