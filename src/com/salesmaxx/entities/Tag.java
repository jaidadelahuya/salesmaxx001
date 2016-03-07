package com.salesmaxx.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Text;

public class Tag implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8395843038140769174L;
	
	private String name;
	private Text description;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Text getDescription() {
		return description;
	}
	public void setDescription(Text description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Tag [name=" + name + ", description=" + description + "]";
	}
	
	

}
