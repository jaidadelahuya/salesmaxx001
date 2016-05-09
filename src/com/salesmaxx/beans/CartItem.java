package com.salesmaxx.beans;

import java.io.Serializable;

import com.google.appengine.api.datastore.Key;
import com.salesmaxx.util.Util;

public class CartItem implements Serializable {

	public enum ItemType {
		WORKSHOP,EVENT,GIFT, RESCHEDULE;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 2109981545465098898L;
	private String imageUrl,name, date, location,workshopCode,formattedPrice;
	private long id;
	private int qty;
	private ItemType itemType;
	private double price;
	private String total;
	
	
	@Override
	public String toString() {
		return "CartItem [imageUrl=" + imageUrl + ", name=" + name + ", date="
				+ date + ", location=" + location + ", workshopCode="
				+ workshopCode + ", id=" + id + ", qty=" + qty + ", itemType="
				+ itemType + ", price=" + price + "]";
	}
	
	

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getFormattedPrice() {
		return formattedPrice;
	}



	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}



	public String getWorkshopCode() {
		return workshopCode;
	}

	

	public ItemType getItemType() {
		return itemType;
	}



	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}



	public void setWorkshopCode(String workshopCode) {
		this.workshopCode = workshopCode;
	}



	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		CartItem other = (CartItem) obj;
		if (id != other.id)
			return false;
		return true;
	}



	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		this.formattedPrice = Util.formatPrice(price);
		this.total = Util.formatPrice(price*qty);
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
		this.total = Util.formatPrice(price*qty);
	}
	
	

}
