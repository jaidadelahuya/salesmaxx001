package com.salesmaxx.entities;

import java.io.Serializable;


public class SocialMedia implements Serializable {
	
	private String name,url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "SocialMedia [name=" + name + ", url=" + url + "]";
	}
	
	
}
