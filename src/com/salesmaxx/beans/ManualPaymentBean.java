package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class ManualPaymentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8917238226627460636L;
	private String txnRef;
	private String issueDate;
	private String overdueDate;
	private String customerName;
	private String totalAmount;
	private List<PendingWorkshopBean> pwbs;
	private String uid;
	
	@Override
	public String toString() {
		return "ManualPaymentBean [txnRef=" + txnRef + ", issueDate="
				+ issueDate + ", overdueDate=" + overdueDate
				+ ", customerName=" + customerName + ", totalAmount="
				+ totalAmount + ", pwbs=" + pwbs + "]";
	}
	
	
	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getOverdueDate() {
		return overdueDate;
	}
	public void setOverdueDate(String overdueDate) {
		this.overdueDate = overdueDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<PendingWorkshopBean> getPwbs() {
		return pwbs;
	}
	public void setPwbs(List<PendingWorkshopBean> pwbs) {
		this.pwbs = pwbs;
	}
	
	

}
