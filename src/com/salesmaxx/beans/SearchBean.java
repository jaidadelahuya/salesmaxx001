package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Set;

public class SearchBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8100309401625687100L;
	
	private String date;
	private Set<String> location,industry,experience,jobRole,type,format;
	private int pageStart, pageEnd,pagination,currentDisplay;
	
	
	
	public int getCurrentDisplay() {
		return currentDisplay;
	}
	public void setCurrentDisplay(int currentDisplay) {
		this.currentDisplay = currentDisplay;
	}
	public int getPagination() {
		return pagination;
	}
	public void setPagination(int pagination) {
		this.pagination = pagination;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	

	public Set<String> getLocation() {
		return location;
	}
	public void setLocation(Set<String> location) {
		this.location = location;
	}
	public Set<String> getIndustry() {
		return industry;
	}
	public void setIndustry(Set<String> industry) {
		this.industry = industry;
	}
	public Set<String> getExperience() {
		return experience;
	}
	public void setExperience(Set<String> experience) {
		this.experience = experience;
	}
	public Set<String> getJobRole() {
		return jobRole;
	}
	public void setJobRole(Set<String> jobRole) {
		this.jobRole = jobRole;
	}
	public Set<String> getType() {
		return type;
	}
	public void setType(Set<String> type) {
		this.type = type;
	}
	public Set<String> getFormat() {
		return format;
	}
	public void setFormat(Set<String> format) {
		this.format = format;
	}
	@Override
	public String toString() {
		return "SearchBean [date=" + date + ", location=" + location
				+ ", industry=" + industry + ", experience=" + experience
				+ ", jobRole=" + jobRole + ", type=" + type + ", format="
				+ format + ", pageStart=" + pageStart + ", pageEnd=" + pageEnd
				+ ", pagination=" + pagination + ", currentDisplay="
				+ currentDisplay + "]";
	}
	
	

}
