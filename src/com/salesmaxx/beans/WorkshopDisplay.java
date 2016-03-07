package com.salesmaxx.beans;

import java.io.Serializable;

public class WorkshopDisplay implements Serializable,
		Comparable<WorkshopDisplay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3199148180415960736L;
	private String imageUrl, name, description, id;
	private double rating;
	private int priority, noReviews;

	public int getNoReviews() {
		return noReviews;
	}

	public void setNoReviews(int noReviews) {
		this.noReviews = noReviews;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	

	@Override
	public String toString() {
		return "WorkshopDisplay [imageUrl=" + imageUrl + ", name=" + name
				+ ", description=" + description + ", id=" + id + ", rating="
				+ rating + ", priority=" + priority + ", noReviews="
				+ noReviews + "]";
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
		WorkshopDisplay other = (WorkshopDisplay) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(WorkshopDisplay o) {
		if (this.priority > o.getPriority()) {
			return 1;
		} else if (this.priority < o.getPriority()) {
			return -1;
		} else {
			return 0;
		}
	}
}
