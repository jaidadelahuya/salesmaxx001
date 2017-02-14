package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.persistence.controllers.EMF;

public class Vote implements Serializable {

	public enum type {UPVOTE,DOWNVOTE}
	private static final long serialVersionUID = -4412298186631842414L;
	
	private String type;
	private Key voter, id;
	private Date date;
	
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Vote [type=" + type + ", voter=" + voter + "]";
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Key getVoter() {
		return voter;
	}
	public void setVoter(Key voter) {
		this.voter = voter;
	}	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	
	
	public Vote() {
		id = EMF.getDs().allocateIds(Vote.class.getSimpleName(), 1).getStart();
	}
	
	public Vote(Entity e) {
		id = e.getKey();
		type = (String) e.getProperty("type");
		voter = (Key) e.getProperty("voter");
	}
	
	public Entity toEntity() {
		Entity e = new Entity(id);
		e.setIndexedProperty("voter", voter);
		e.setIndexedProperty("type", type);
		return e;
	}

}
