package com.salesmaxx.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class JobRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3279534576573377420L;
	
	
	private Key name;


	@Override
	public String toString() {
		return "JobRole [name=" + name + "]";
	}


	public Key getName() {
		return name;
	}


	public void setName(Key name) {
		this.name = name;
	}


	public JobRole(String name) {
		this.name = KeyFactory.createKey("JobRole", name);
	}
	


}
