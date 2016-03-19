package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class ChequePaymentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 847849267192674529L;
	private String category;
	private String cursor;
	private int noOfEntries;
	private List<ManualPaymentBean> mpbs;
	private int currentPage;
	private int totalNumberOfEntries;
	
	

	@Override
	public String toString() {
		return "ChequePaymentBean [category=" + category + ", cursor=" + cursor
				+ ", noOfEntries=" + noOfEntries + ", mpbs=" + mpbs
				+ ", currentPage=" + currentPage + ", totalNumberOfEntries="
				+ totalNumberOfEntries + "]";
	}
	

	public int getTotalNumberOfEntries() {
		return totalNumberOfEntries;
	}

	public void setTotalNumberOfEntries(int totalNumberOfEntries) {
		this.totalNumberOfEntries = totalNumberOfEntries;
	}

	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public int getNoOfEntries() {
		return noOfEntries;
	}
	public void setNoOfEntries(int noOfEntries) {
		this.noOfEntries = noOfEntries;
	}
	public List<ManualPaymentBean> getMpbs() {
		return mpbs;
	}
	public void setMpbs(List<ManualPaymentBean> mpbs) {
		this.mpbs = mpbs;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	

}
