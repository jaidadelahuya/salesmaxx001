package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DiscussionPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4492209904290475547L;
	
	private String category;
	private List<SingleDiscussionPageBean> beans;
	private Map<String,List<SingleDiscussionPageBean> > otherCategory;
	private String cursor;
	
	@Override
	public String toString() {
		return "DiscussionPageBean [category=" + category + ", beans=" + beans
				+ ", otherCategory=" + otherCategory + "]";
	}
	
	
	public String getCursor() {
		return cursor;
	}


	public void setCursor(String cursor) {
		this.cursor = cursor;
	}


	public Map<String, List<SingleDiscussionPageBean>> getOtherCategory() {
		return otherCategory;
	}


	public void setOtherCategory(
			Map<String, List<SingleDiscussionPageBean>> otherCategory) {
		this.otherCategory = otherCategory;
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
