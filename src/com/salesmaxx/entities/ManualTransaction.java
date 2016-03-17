package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;

public class ManualTransaction implements Serializable {

	/**
	 * 
	 */
	public enum TransactionType {
		CHEQUE,ELECTRONIC
	}
	private static final long serialVersionUID = -4544318862178480429L;
	
	private Key id;
	private Key ownerKey;
	private Date issueDate;
	private String txnRef;
	private List<EmbeddedEntity> items;
	private String status;
	private String transactionType;

	@Override
	public String toString() {
		return "ManualTransaction [ownerKey=" + ownerKey + ", issueDate="
				+ issueDate + ", txnRef=" + txnRef + ", items=" + items
				+ ", status=" + status + "]";
	}
	
	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Key getOwnerKey() {
		return ownerKey;
	}
	public void setOwnerKey(Key ownerKey) {
		this.ownerKey = ownerKey;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public List<EmbeddedEntity> getItems() {
		return items;
	}
	public void setItems(List<EmbeddedEntity> items) {
		this.items = items;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
