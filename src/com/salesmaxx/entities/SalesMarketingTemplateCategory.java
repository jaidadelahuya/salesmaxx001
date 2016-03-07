package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Key;

public class SalesMarketingTemplateCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7407418696212479065L;

	private Key id;
	private List<Key> templates;
	@Override
	public String toString() {
		return "SalesMarketingTemplateCategory [id=" + id + ", templates="
				+ templates + "]";
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public List<Key> getTemplates() {
		return templates;
	}
	public void setTemplates(List<Key> templates) {
		this.templates = templates;
	}
	
	
}
