package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class SearchDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3165208116795318773L;
	
	private String name, description, experience, type, workshopCode;
	private Key id;
	private List<String> location,month;
	@Override
	public String toString() {
		return "SearchDocument [name=" + name + ", description=" + description
				+ ", experience=" + experience + ", type=" + type
				+ ", workshopCode=" + workshopCode + ", id=" + id
				+ ", location=" + location + ", month=" + month + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWorkshopCode() {
		return workshopCode;
	}
	public void setWorkshopCode(String workshopCode) {
		this.workshopCode = workshopCode;
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public List<String> getLocation() {
		return location;
	}
	public void setLocation(List<String> location) {
		this.location = location;
	}
	public List<String> getMonth() {
		return month;
	}
	public void setMonth(List<String> month) {
		this.month = month;
	}

	

}
