package com.salesmaxx.entities;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.salesmaxx.persistence.controllers.EMF;

public class PurchaseableItem {

	private Key id;
	private Key itemKey;//WorkShop Key
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
	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "PurchaseableItem [id=" + id + ", itemKey=" + itemKey
				+ ", unitPrice=" + unitPrice + ", qty=" + qty + "]";
	}
	public PurchaseableItem() {
		KeyRange range = EMF.getDs().allocateIds(
				PurchaseableItem.class.getSimpleName(), 1);
		id = range.getStart();
	}
	
	
	
}
