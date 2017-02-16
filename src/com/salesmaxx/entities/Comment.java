package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

public class Comment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8283822732414233436L;
	
	private Date time;
	private Text body;
	private Key owner;
	List<Key> likers;
	List<Key> comments;
	
	private Key id;
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<Key> getComments() {
		return comments;
	}
	public void setComments(List<Key> comments) {
		this.comments = comments;
	}
	@Override
	public String toString() {
		return "Comment [time=" + time + ", body=" + body + ", owner=" + owner
				+ ", likers=" + likers + ", comments=" + comments + ", id="
				+ id + "]";
	}
	public Text getBody() {
		return body;
	}
	public void setBody(Text body) {
		this.body = body;
	}
	public Key getOwner() {
		return owner;
	}
	public void setOwner(Key owner) {
		this.owner = owner;
	}
	public List<Key> getLikers() {
		return likers;
	}
	public void setLikers(List<Key> likers) {
		this.likers = likers;
	}
	
	

}
