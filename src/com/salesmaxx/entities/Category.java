package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.persistence.controllers.CategoryController;
import com.salesmaxx.util.Util;

@Entity
public class Category implements SpecialInterestGroup, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 442654025864062102L;

	@Id
	private Key name;

	private String categoryId;
	
	private String catalogueLink;

	@Basic
	
	private List<String> workshops;

	@Basic
	
	private BlobKey image;
	
	

	public String getCatalogueLink() {
		return catalogueLink;
	}

	public void setCatalogueLink(String catalogueLink) {
		this.catalogueLink = catalogueLink;
	}

	public Category(String name) {
		this.name = KeyFactory.createKey(Category.class.getSimpleName(), name);
		categoryId = newCategoryId(name);
		this.catalogueLink = Util.getCatalogueLink(name);
	}

	private String newCategoryId(String name2) {
		String pre = null;
		if (name2.length() > 3) {
			pre = name2.substring(0, 3);
		} else {
			pre = name2;
		}
		pre = pre + Util.generateRandomCode(100, 999);
		CategoryController cont = new CategoryController();
		if(cont.categoryIdExists(pre)) {
			return newCategoryId(name2);
		} else {
			return pre.toUpperCase();
		}
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Key getName() {
		return name;
	}

	public void setName(Key name) {
		this.name = name;
	}

	public List<String> getWorkshops() {
		return workshops;
	}

	public void setWorkshops(List<String> workshops) {
		this.workshops = workshops;
	}

	public BlobKey getImage() {
		return image;
	}

	public void setImage(BlobKey image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", categoryId=" + categoryId
				+ ", workshops=" + workshops + ", image=" + image + "]";
	}

}
