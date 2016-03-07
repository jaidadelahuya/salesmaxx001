package com.salesmaxx.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

@Entity
public class WhitePaper implements Serializable,Downloadable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8245646316504617253L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private String name;
	
	@Extension(vendorName="datanucleus", key="gae.unindexed", value="true")
	private String link;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "WhitePaper [id=" + id + ", name=" + name + ", link=" + link
				+ "]";
	}
	
	
	
}
