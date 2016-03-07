package com.salesmaxx.entities;

import com.google.appengine.api.datastore.Key;

public class PurchaseableItem {

	private Key itemKey;
	private double unitPrice;
	private long qty;
	public Key getItemKey() {
		return itemKey;
	}
	public void setItemKey(Key key) {
		this.itemKey = key;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	@Override
	public String toString() {
		return "PurchaseableItem [key=" + itemKey + ", unitPrice=" + unitPrice
				+ ", qty=" + qty + "]";
	}
	
	
	
	
}
