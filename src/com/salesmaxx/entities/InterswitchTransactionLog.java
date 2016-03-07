package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Key;

@Entity
public class InterswitchTransactionLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1074836026131649291L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String date, tnxRef, amount, responseDescription, responseCode,
			customerId, apprAmount;
	private Set<Key> productsPaidFor;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Key> getProductsPaidFor() {
		return productsPaidFor;
	}

	public void setProductsPaidFor(Set<Key> productsPaidFor) {
		this.productsPaidFor = productsPaidFor;
	}
	
	

	public String getApprAmount() {
		return apprAmount;
	}

	public void setApprAmount(String apprAmount) {
		this.apprAmount = apprAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTnxRef() {
		return tnxRef;
	}

	public void setTnxRef(String tnxRef) {
		this.tnxRef = tnxRef;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "InterswitchTransactionLog [date=" + date + ", tnxRef=" + tnxRef
				+ ", amount=" + amount + ", responseDescription="
				+ responseDescription + ", responseCode=" + responseCode
				+ ", customerId=" + customerId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tnxRef == null) ? 0 : tnxRef.hashCode());
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
		InterswitchTransactionLog other = (InterswitchTransactionLog) obj;
		if (tnxRef == null) {
			if (other.tnxRef != null)
				return false;
		} else if (!tnxRef.equals(other.tnxRef))
			return false;
		return true;
	}

}
