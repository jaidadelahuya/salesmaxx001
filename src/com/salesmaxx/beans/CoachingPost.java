package com.salesmaxx.beans;

import java.io.Serializable;

public class CoachingPost implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3384279548731247755L;
	private String title, body,  tags, category, coachingPost;
	private boolean anonymous, privacy, notify;

	@Override
	public String toString() {
		return "CoachingPost [title=" + title + ", body=" + body + ", privacy="
				+ privacy + ", tags=" + tags + ", category=" + category
				+ ", notify=" + notify + "]";
	}
	
	

	public String getCoachingPost() {
		return coachingPost;
	}



	public void setCoachingPost(String coachingPost) {
		this.coachingPost = coachingPost;
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



	public boolean isAnonymous() {
		return anonymous;
	}



	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}



	public boolean isPrivacy() {
		return privacy;
	}



	public void setPrivacy(boolean privacy) {
		this.privacy = privacy;
	}



	public boolean isNotify() {
		return notify;
	}



	public void setNotify(boolean notify) {
		this.notify = notify;
	}

	
	
	

}
