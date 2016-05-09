package com.salesmaxx.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.salesmaxx.persistence.controllers.EMF;

public class BankAccountDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6621534332323499565L;

	private String bankName, accNumber, accName;
	private Key userKey, id;
	
	

	@Override
	public String toString() {
		return "BankAccountDetails [bankName=" + bankName + ", accNumber="
				+ accNumber + ", accName=" + accName + ", userKey=" + userKey
				+ "]";
	}

	
	public BankAccountDetails() {
		this.id = EMF.getDs().allocateIds(BankAccountDetails.class.getSimpleName(), 1).getStart();
	}


	public Key getId() {
		return id;
	}


	public void setId(Key id) {
		this.id = id;
	}


	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public Key getUserKey() {
		return userKey;
	}

	public void setUserKey(Key userKey) {
		this.userKey = userKey;
	}

}
