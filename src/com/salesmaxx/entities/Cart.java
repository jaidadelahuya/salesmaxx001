package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.CartItem;
import com.salesmaxx.util.Util;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7231115048560053796L;

	private Key cartKey;
	private List<EmbeddedEntity> items;
	private Set<CartItem> cartItems;
	private int subTotal;
	private int noOfItems;
	private String formattedsubTotal;
	
	
	
	public String getFormattedsubTotal() {
		return formattedsubTotal;
	}

	public void setFormattedsubTotal(String formattedsubTotal) {
		this.formattedsubTotal = formattedsubTotal;
	}

	public int getNoOfItems() {
		return noOfItems;
	}

	public void setNoOfItems(int noOfItems) {
		this.noOfItems = noOfItems;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
		
	}

	public Key getCartKey() {
		return cartKey;
	}

	public void setCartKey(Key userId) {
		this.cartKey = userId;
	}

	
	@Override
	public String toString() {
		return "Cart [cartKey=" + cartKey + ", items=" + items + ", cartItems="
				+ cartItems + "]";
	}

	public List<EmbeddedEntity> getItems() {
		return items;
	}

	public void setItems(List<EmbeddedEntity> items) {
		
		this.cartItems = Util.getCartItems(items);
		this.noOfItems = cartItems.size();
		this.subTotal = (int)Util.getSubTotal(this.cartItems);
		this.formattedsubTotal = Util.formatPrice(subTotal);
		this.items = items;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartKey == null) ? 0 : cartKey.hashCode());
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
		Cart other = (Cart) obj;
		if (cartKey == null) {
			if (other.cartKey != null)
				return false;
		} else if (!cartKey.equals(other.cartKey))
			return false;
		return true;
	}
	
}
