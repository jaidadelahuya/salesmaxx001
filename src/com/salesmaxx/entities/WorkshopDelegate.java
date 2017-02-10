package com.salesmaxx.entities;

import java.io.Serializable;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.persistence.controllers.EMF;

public class WorkshopDelegate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3326313305370375202L;
	private Key id, ownerKey, workshopKey;
	private String firstName,lastName,email,phone;
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Key getOwnerKey() {
		return ownerKey;
	}
	public void setOwnerKey(Key ownerKey) {
		this.ownerKey = ownerKey;
	}
	public Key getWorkshopKey() {
		return workshopKey;
	}
	public void setWorkshopKey(Key workshopKey) {
		this.workshopKey = workshopKey;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "WorkshopDelegate [id=" + id + ", ownerKey=" + ownerKey
				+ ", workshopKey=" + workshopKey + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", phone="
				+ phone + "]";
	}
	public WorkshopDelegate() {
		this.id = EMF.getDs().allocateIds(WorkshopDelegate.class.getSimpleName(), 1).getStart();
	}
	
	public WorkshopDelegate(Entity e) {
		this.id = e.getKey();
		this.email = (String) e.getProperty("email");
		this.firstName = (String) e.getProperty("firstName");
		this.lastName = (String) e.getProperty("lastName");
		this.ownerKey = (Key) e.getProperty("ownerKey");
		this.phone = (String) e.getProperty("phone");
		this.workshopKey = (Key) e.getProperty("workshopKey");
	}
	
	public Entity toEntity() {
		Entity e = new Entity(id);
		e.setIndexedProperty("email", email);
		e.setIndexedProperty("phone", phone);
		e.setIndexedProperty("workshopKey", workshopKey);
		e.setIndexedProperty("ownerKey",ownerKey);
		e.setUnindexedProperty("firstName", firstName);
		e.setUnindexedProperty("lastName", lastName);
		return e;
	}
	public void persist() {
		Transaction txn = EMF.getDs().beginTransaction();
		EMF.getDs().put(toEntity());
		txn.commitAsync();
		
		
	}
	
	

}
