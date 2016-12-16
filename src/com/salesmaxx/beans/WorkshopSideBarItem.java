package com.salesmaxx.beans;

import java.io.Serializable;

public class WorkshopSideBarItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8605581831968822270L;
	private String name, href;
	private int freq;
	
	public WorkshopSideBarItem(String name, String href, int freq) {
		super();
		this.name = name.toLowerCase();
		this.href = href;
		this.freq = freq;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	@Override
	public String toString() {
		return "WorkshopSideBarItem [name=" + name + ", href=" + href
				+ ", freq=" + freq + "]";
	}
	
	

}
