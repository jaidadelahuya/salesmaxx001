package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.salesmaxx.entities.Tag;
import com.salesmaxx.util.Util;

public class SingleDiscussionPageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4674044182364333913L;
	
	private String category;
	private String topic;
	private String body;
	private List<Tag> tags;
	private long views;
	private long votes;
	private long nComments;
	private List<CommentBean> comments;
	private Date time;
	private String ownerImage;
	private String ownerName;
	private String webkey;
	private String snippet;
	private String timePosted;
	
	
	
	public long getnComments() {
		return nComments;
	}
	public void setnComments(long nComments) {
		this.nComments = nComments;
	}
	public String getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(String timePosted) {
		this.timePosted = timePosted;
	}
	public String getSnippet() {
		return snippet;
	}
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
	public String getWebkey() {
		return webkey;
	}
	public void setWebkey(String webkey) {
		this.webkey = webkey;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@Override
	public String toString() {
		return "SingleDiscussionPageBean [category=" + category + ", topic="
				+ topic + ", body=" + body + ", tags=" + tags + ", views="
				+ views + ", votes=" + votes + ", comments=" + comments
				+ ", time=" + time + ", ownerImage=" + ownerImage
				+ ", ownerName=" + ownerName + ", webkey=" + webkey + "]";
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
		if(body !=null && body.length() > 400) {
			snippet = body.substring(0, body.indexOf("</p>"));
		}else {
			snippet = body;
		}
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public long getViews() {
		return views;
	}
	public void setViews(long views) {
		this.views = views;
	}
	public long getVotes() {
		return votes;
	}
	public void setVotes(long votes) {
		this.votes = votes;
	}
	public List<CommentBean> getComments() {
		return comments;
	}
	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
		if(comments!=null) {
			nComments = comments.size();
		}
		
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
		timePosted = Util.getPostedTime(time);
	}
	public String getOwnerImage() {
		return ownerImage;
	}
	public void setOwnerImage(String ownerImage) {
		this.ownerImage = ownerImage;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	

}
