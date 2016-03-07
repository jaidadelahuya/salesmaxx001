package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.salesmaxx.util.Cursor;

public class CategoryDisplay implements Serializable,
		Comparable<CategoryDisplay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2690446521326105895L;
	private String categoryName, categoryId, catalogueLink, imageUrl;
	private List<WorkshopDisplay> workshops;
	private Cursor cursor;
	
	

	
	public Cursor getCursor() {
		return cursor;
	}

	public void setCursor(Cursor cursor) {
		this.cursor = cursor;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCatalogueLink() {
		return catalogueLink;
	}

	public void setCatalogueLink(String catalogueLink) {
		this.catalogueLink = catalogueLink;
	}

	public List<WorkshopDisplay> getWorkshops() {
		return workshops;
	}

	public void setWorkshops(List<WorkshopDisplay> workshops) {
		this.workshops = workshops;
		List list = new ArrayList();
		list.addAll(workshops);
		this.cursor = new Cursor(list, 4, 0, 0);
	}

	@Override
	public String toString() {
		return "CategoryDisplay [categoryName=" + categoryName
				+ ", categoryId=" + categoryId + ", catalogueLink="
				+ catalogueLink + ", workshops=" + workshops + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
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
		CategoryDisplay other = (CategoryDisplay) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}

	@Override
	public int compareTo(CategoryDisplay o) {
		return this.categoryName.compareTo(o.getCategoryName());
	}

}
