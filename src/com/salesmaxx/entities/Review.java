package com.salesmaxx.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.datastore.Text;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private long userid;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private String title;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private Text comment;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private int noOfStars;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private Date time;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Text getComment() {
		return comment;
	}
	public void setComment(Text comment) {
		this.comment = comment;
	}
	public int getNoOfStars() {
		return noOfStars;
	}
	public void setNoOfStars(int noOfStars) {
		this.noOfStars = noOfStars;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Review [id=" + id + ", userid=" + userid + ", title=" + title
				+ ", comment=" + comment + ", noOfStars=" + noOfStars
				+ ", time=" + time + "]";
	}
	
	

}
