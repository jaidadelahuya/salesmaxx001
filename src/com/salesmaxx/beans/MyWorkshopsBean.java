package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

import com.salesmaxx.entities.WorkShop;

public class MyWorkshopsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4700720823662231826L;
	
	private List<ScheduleWorkshopDisplay> enrolled,wish,completed;

	public List<ScheduleWorkshopDisplay> getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(List<ScheduleWorkshopDisplay> enrolled) {
		this.enrolled = enrolled;
	}

	public List<ScheduleWorkshopDisplay> getWish() {
		return wish;
	}

	public void setWish(List<ScheduleWorkshopDisplay> wish) {
		this.wish = wish;
	}

	public List<ScheduleWorkshopDisplay> getCompleted() {
		return completed;
	}

	public void setCompleted(List<ScheduleWorkshopDisplay> completed) {
		this.completed = completed;
	}

	@Override
	public String toString() {
		return "MyWorkshopsBean [enrolled=" + enrolled + ", wish=" + wish
				+ ", completed=" + completed + "]";
	}
	
	

}
