package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

import com.salesmaxx.entities.SalesmaxxCreditHistory;

public class SalesMaxxCreditHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -709158932382467034L;
	
	private List<SalesmaxxCreditHistory> his;
	private double total, pending;

	
	public List<SalesmaxxCreditHistory> getHis() {
		return his;
	}

	public void setHis(List<SalesmaxxCreditHistory> his) {
		this.his = his;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getPending() {
		return pending;
	}

	public void setPending(double pending) {
		this.pending = pending;
	}

	@Override
	public String toString() {
		return "SalesMaxxCreditHistory [his=" + his + ", total=" + total
				+ ", pending=" + pending + "]";
	}
	
	

}
