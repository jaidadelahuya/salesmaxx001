package com.salesmaxx.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CommentBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9177434461929644775L;
	private String body;
	private String ownerName, ownerImage;
	private int likes;
	private Date time;
	private List<CommentBean> comments;
	private String webkey;
	private int upVotes, downVotes;
	


	


	public int getUpVotes() {
		return upVotes;
	}


	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}


	public int getDownVotes() {
		return downVotes;
	}


	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}


	public String getWebkey() {
		return webkey;
	}


	public void setWebkey(String webkey) {
		this.webkey = webkey;
	}


	@Override
	public String toString() {
		return "CommentBean [body=" + body + ", ownerName=" + ownerName
				+ ", ownerImage=" + ownerImage + ", likes=" + likes + ", time="
				+ time + ", comments=" + comments + ", webkey=" + webkey + "]";
	}
	
	
	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	public List<CommentBean> getComments() {
		return comments;
	}


	public void setComments(List<CommentBean> comments) {
		this.comments = comments;
	}


	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getOwnerImage() {
		return ownerImage;
	}
	public void setOwnerImage(String ownerImage) {
		this.ownerImage = ownerImage;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	
}
