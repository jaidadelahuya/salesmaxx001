package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;

public class DiscussionPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492209904290475547L;
	
	private String category;
	private List<SingleDiscussionPageBean> beans;
	@Override
	public String toString() {
		return "DiscussionPageBean [category=" + category + ", beans=" + beans
				+ "]";
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<SingleDiscussionPageBean> getBeans() {
		return beans;
	}
	public void setBeans(List<SingleDiscussionPageBean> beans) {
		this.beans = beans;
	}
	
	

}
