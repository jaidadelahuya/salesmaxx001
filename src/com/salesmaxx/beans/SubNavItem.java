package com.salesmaxx.beans;

import java.io.Serializable;

public class SubNavItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8009706124460803529L;
	private String displayName, href;
	@Override
	public String toString() {
		return "SubNavItem [displayName=" + displayName + ", href=" + href
				+ "]";
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public SubNavItem(String displayName, String href) {
		super();
		this.displayName = displayName;
		this.href = href;
	}
	
}
