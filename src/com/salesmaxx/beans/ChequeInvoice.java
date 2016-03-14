package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class ChequeInvoice implements Serializable {

	/**
	 * @author WebDev
	 *
	 */
	public enum InvoiceStatus {
		PENDING,EXPIRED,CLEARED
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5456884409839717232L;
	
	private String name,email,date,txnRef,subTotal,dueDate;
	private  Set<CartItem> items;
	private InvoiceStatus status;
	@Override
	public String toString() {
		return "ChequeInvoice [name=" + name + ", address="
				+ ", email=" + email + ", phone=" + ", date=" + date
				+ ", txnRef=" + txnRef + ", subTotal=" + subTotal
				+ ", dueDate=" + dueDate + ", items=" + items + "]";
	}
	
	
	public InvoiceStatus getStatus() {
		return status;
	}


	public void setStatus(InvoiceStatus status) {
		this.status = status;
	}


	public String getDueDate() {
		return dueDate;
	}


	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public Set<CartItem> getItems() {
		return items;
	}
	public void setItems(Set<CartItem> items) {
		this.items = items;
	}
	
}
