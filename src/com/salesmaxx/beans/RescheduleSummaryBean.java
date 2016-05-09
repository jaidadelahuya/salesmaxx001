package com.salesmaxx.beans;

import java.io.Serializable;

public class RescheduleSummaryBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3166073846644541612L;
	
	private ScheduleWorkshopDisplay oldWorkshop, newWorkshop;
	private long noDelegates;
	private double costPerDelegates;
	public ScheduleWorkshopDisplay getOldWorkshop() {
		return oldWorkshop;
	}
	public void setOldWorkshop(ScheduleWorkshopDisplay oldWorkshop) {
		this.oldWorkshop = oldWorkshop;
	}
	public ScheduleWorkshopDisplay getNewWorkshop() {
		return newWorkshop;
	}
	public void setNewWorkshop(ScheduleWorkshopDisplay newWorkshop) {
		this.newWorkshop = newWorkshop;
	}
	public long getNoDelegates() {
		return noDelegates;
	}
	public void setNoDelegates(long noDelegates) {
		this.noDelegates = noDelegates;
	}
	public double getCostPerDelegates() {
		return costPerDelegates;
	}
	public void setCostPerDelegates(double costPerDelegates) {
		this.costPerDelegates = costPerDelegates;
	}
	@Override
	public String toString() {
		return "RescheduleSummaryBean [oldWorkshop=" + oldWorkshop
				+ ", newWorkshop=" + newWorkshop + ", noDelegates="
				+ noDelegates + ", costPerDelegates=" + costPerDelegates + "]";
	}
	
	

}
