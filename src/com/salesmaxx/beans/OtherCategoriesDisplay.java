package com.salesmaxx.beans;

import java.io.Serializable;

public class OtherCategoriesDisplay implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -547119022068642828L;
	
	private String image,id,name,noOfWorkshops;

	@Override
	public String toString() {
		return "OtherCategoriesDisplay [image=" + image + ", id=" + id
				+ ", name=" + name + ", noOfWorkshops=" + noOfWorkshops + "]";
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNoOfWorkshops() {
		return noOfWorkshops;
	}

	public void setNoOfWorkshops(String noOfWorkshops) {
		this.noOfWorkshops = noOfWorkshops;
	}
	
	

}
