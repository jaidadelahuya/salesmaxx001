package com.salesmaxx.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class Industry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6312512366179444875L;
	
	private Key name;
	public Industry(String name) {
		this.name = KeyFactory.createKey("Industry", name);
	}
	@Override
	public String toString() {
		return "Industry [name=" + name + "]";
	}
	public Key getName() {
		return name;
	}
	public void setName(Key name) {
		this.name = name;
	}
	
}
