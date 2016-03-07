package com.salesmaxx.beans;

import java.io.Serializable;

import com.salesmaxx.entities.Address;

public class ScheduleWorkshopDisplay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5207890116202518776L;
	private String id,startDate,name,format,type,flyer,venue,imageUrl,workshopCode,description;
	private Address location;
	
	
	
	public String getWorkshopCode() {
		return workshopCode;
	}
	public void setWorkshopCode(String workshopCode) {
		this.workshopCode = workshopCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public Address getLocation() {
		return location;
	}
	public void setLocation(Address location) {
		this.location = location;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFlyer() {
		return flyer;
	}
	public void setFlyer(String flyer) {
		this.flyer = flyer;
	}
	@Override
	public String toString() {
		return "ScheduleWorkshopDisplay [id=" + id + ", startDate=" + startDate
				+ ", name=" + name + ", format=" + format + ", type=" + type
				+ ", flyer=" + flyer + ", venue=" + venue + ", imageUrl="
				+ imageUrl + ", workshopCode=" + workshopCode
				+ ", description=" + description + ", location=" + location
				+ "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScheduleWorkshopDisplay other = (ScheduleWorkshopDisplay) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
