package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class SearchBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8100309401625687100L;
	
	private String date, nextCursor, prevCursor, queryString;
	private Set<String> location,industry,experience,jobRole,type,format;
	private int pageStart, pageEnd,pagination,currentDisplay;
	private long resultsFound;
	private List<String> previousCursors;
	
	
	
	public List<String> getPreviousCursors() {
		return previousCursors;
	}
	public void setPreviousCursors(List<String> previousCursors) {
		this.previousCursors = previousCursors;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public String getNextCursor() {
		return nextCursor;
	}
	public void setNextCursor(String nextCursor) {
		
		this.nextCursor = nextCursor;
	}
	public String getPrevCursor() {
		return prevCursor;
	}
	public void setPrevCursor(String prevCursor) {
		
		this.prevCursor = prevCursor;
	}
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
	public long getResultsFound() {
		return resultsFound;
	}
	public void setResultsFound(long resultsFound) {
		this.resultsFound = resultsFound;
	}
	
	

}
