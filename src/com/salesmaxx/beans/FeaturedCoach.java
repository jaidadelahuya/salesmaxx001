package com.salesmaxx.beans;

import java.io.Serializable;

public class FeaturedCoach implements Serializable {

	private static final long serialVersionUID = -1378969764580848429L;
	
	private String webkey,coachName, picture;

	public String getWebkey() {
		return webkey;
	}

	public void setWebkey(String webkey) {
		this.webkey = webkey;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "FeaturedCoach [webkey=" + webkey + ", coachName=" + coachName
				+ ", picture=" + picture + "]";
	}
	
	
}
