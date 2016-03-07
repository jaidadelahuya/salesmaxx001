package com.salesmaxx.entities;

import java.io.Serializable;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;

public class SalesMarketingTemplate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 257781327125450235L;

	private Key id;
	private String name;
	private SalesTemplateFormat format;
	private boolean free;
	private double price;
	private Key category;
	private BlobKey imageKey;
	
	
	@Override
	public String toString() {
		return "SalesMarketingTemplate [id=" + id + ", name=" + name
				+ ", format=" + format + ", free=" + free + ", price=" + price
				+ ", category=" + category + ", imageKey=" + imageKey + "]";
	}
	
	
	public BlobKey getImageKey() {
		return imageKey;
	}


	public void setImageKey(BlobKey imageKey) {
		this.imageKey = imageKey;
	}


	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SalesTemplateFormat getFormat() {
		return format;
	}
	public void setFormat(SalesTemplateFormat format) {
		this.format = format;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Key getCategory() {
		return category;
	}
	public void setCategory(Key category) {
		this.category = category;
	}
	
	
}
