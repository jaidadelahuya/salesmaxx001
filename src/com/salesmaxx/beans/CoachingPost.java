package com.salesmaxx.beans;

import java.io.Serializable;

public class CoachingPost implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3384279548731247755L;
	private String title, body, privacy, tags, category, notify;

	@Override
	public String toString() {
		return "CoachingPost [title=" + title + ", body=" + body + ", privacy="
				+ privacy + ", tags=" + tags + ", category=" + category
				+ ", notify=" + notify + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNotify() {
		return notify;
	}

	public void setNotify(String notify) {
		this.notify = notify;
	}
	
	

}
