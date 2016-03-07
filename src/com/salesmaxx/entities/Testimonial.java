package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

public class Testimonial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -321129509311230171L;
	
	private String writerName;
	private Text statement;
	private Date date;
	private Key writer;
	private Key id;
	@Override
	public String toString() {
		return "Testimonial [writerName=" + writerName + ", statement="
				+ statement + ", date=" + date + ", writer=" + writer + ", id="
				+ id + "]";
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public Text getStatement() {
		return statement;
	}
	public void setStatement(Text statement) {
		this.statement = statement;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Key getWriter() {
		return writer;
	}
	public void setWriter(Key writer) {
		this.writer = writer;
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	
	

}
