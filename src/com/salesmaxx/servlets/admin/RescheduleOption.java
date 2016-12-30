package com.salesmaxx.servlets.admin;

import java.io.Serializable;
import java.util.List;

import com.salesmaxx.beans.ScheduleWorkshopDisplay;

public class RescheduleOption implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1429934061863633208L;
	private String qty,txnRef,workshop, oid;
	private List<ScheduleWorkshopDisplay> schedules;
	
	
	public String getWorkshop() {
		return workshop;
	}
	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getTxnRef() {
		return txnRef;
	}
	public void setTxnRef(String txnRef) {
		this.txnRef = txnRef;
	}
	public List<ScheduleWorkshopDisplay> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleWorkshopDisplay> schedules) {
		this.schedules = schedules;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	
	

}
