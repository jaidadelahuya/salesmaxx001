package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class WorkshopTemplateBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8818820773430738435L;
	
	private String name,code;
	private int schPassed,schLeft;
	private List <ScheduleWorkshopDisplay> schedules;
	@Override
	public String toString() {
		return "WorkshopTemplateBean [name=" + name + ", code=" + code
				+ ", schPassed=" + schPassed + ", schLeft=" + schLeft
				+ ", schedules=" + schedules + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getSchPassed() {
		return schPassed;
	}
	public void setSchPassed(int schPassed) {
		this.schPassed = schPassed;
	}
	public int getSchLeft() {
		return schLeft;
	}
	public void setSchLeft(int schLeft) {
		this.schLeft = schLeft;
	}
	public List<ScheduleWorkshopDisplay> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleWorkshopDisplay> schedules) {
		this.schedules = schedules;
	}
	
	

}
