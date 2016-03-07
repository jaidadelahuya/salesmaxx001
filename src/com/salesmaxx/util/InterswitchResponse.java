package com.salesmaxx.util;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InterswitchResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5055206430178761635L;

	private String amount, cardNumber, merchantReference, paymentReference,
			retrievalRferenceNumber, leadBankCbnCode, leadBankName,
			splitAccounts, transactionDate, responseCode, responseDescriptor,txnRef;

	

	@Override
	public String toString() {
		return "InterswitchResponse [amount=" + amount + ", cardNumber="
				+ cardNumber + ", merchantReference=" + merchantReference
				+ ", paymentReference=" + paymentReference
				+ ", retrievalRferenceNumber=" + retrievalRferenceNumber
				+ ", leadBankCbnCode=" + leadBankCbnCode + ", leadBankName="
				+ leadBankName + ", splitAccounts=" + splitAccounts
				+ ", transactionDate=" + transactionDate + ", responseCode="
				+ responseCode + ", responseDescriptor=" + responseDescriptor
				+ ", txnRef=" + txnRef + "]";
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
		InterswitchResponse other = (InterswitchResponse) obj;
		if (txnRef == null) {
			if (other.txnRef != null)
				return false;
		} else if (!txnRef.equals(other.txnRef))
			return false;
		return true;
	}



	public String getTxnRef() {
		return txnRef;
	}



	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}



	public String getAmount() {
		return amount;
	}

	@XmlElement
	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	@XmlElement
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getMerchantReference() {
		return merchantReference;
	}

	@XmlElement
	public void setMerchantReference(String merchantReference) {
		this.merchantReference = merchantReference;
	}

	public String getPaymentReference() {
		return paymentReference;
	}
	@XmlElement
	public void setPaymentReference(String paymentReference) {
		this.paymentReference = paymentReference;
	}

	public String getRetrievalRferenceNumber() {
		return retrievalRferenceNumber;
	}
	@XmlElement
	public void setRetrievalRferenceNumber(String retrievalRferenceNumber) {
		this.retrievalRferenceNumber = retrievalRferenceNumber;
	}

	public String getLeadBankCbnCode() {
		return leadBankCbnCode;
	}
	@XmlElement
	public void setLeadBankCbnCode(String leadBankCbnCode) {
		this.leadBankCbnCode = leadBankCbnCode;
	}

	public String getLeadBankName() {
		return leadBankName;
	}
	@XmlElement
	public void setLeadBankName(String leadBankName) {
		this.leadBankName = leadBankName;
	}

	public String getSplitAccounts() {
		return splitAccounts;
	}
	@XmlElement
	public void setSplitAccounts(String splitAccounts) {
		this.splitAccounts = splitAccounts;
	}

	public String getTransactionDate() {
		return transactionDate;
	}
	@XmlElement
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getResponseCode() {
		return responseCode;
	}
	@XmlElement
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseDescriptor() {
		return responseDescriptor;
	}
	@XmlElement
	public void setResponseDescriptor(String responseDescriptor) {
		this.responseDescriptor = responseDescriptor;
	}
	

}
