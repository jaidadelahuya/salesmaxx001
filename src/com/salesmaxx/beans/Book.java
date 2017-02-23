package com.salesmaxx.beans;

import java.io.Serializable;

public class Book implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -641872450345353297L;
	
	private String name,title,price,url,image;

	@Override
	public String toString() {
		return "Book [name=" + name + ", title=" + title + ", price=" + price
				+ ", url=" + url + "]";
	}
	
	

	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
