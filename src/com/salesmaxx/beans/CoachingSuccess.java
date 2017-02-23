package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class CoachingSuccess implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5884262584857781405L;
	
	private String categoryName;
	private List<ScheduleWorkshopDisplay> workshops;
	private List<Book> books;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public List<ScheduleWorkshopDisplay> getWorkshops() {
		return workshops;
	}
	public void setWorkshops(List<ScheduleWorkshopDisplay> workshops) {
		this.workshops = workshops;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "CoachingSuccess [categoryName=" + categoryName + ", workshops="
				+ workshops + ", books=" + books + "]";
	}
	
	

}
