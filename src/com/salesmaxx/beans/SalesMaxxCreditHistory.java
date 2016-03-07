package com.salesmaxx.beans;

import java.io.Serializable;

public class SalesMaxxCreditHistory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -709158932382467034L;
	
	private double total, pending;

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
		return "SalesMaxxCreditHistory [total=" + total + ", pending="
				+ pending + "]";
	}
	
	

}
