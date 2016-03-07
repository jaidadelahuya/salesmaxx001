package com.salesmaxx.entities;

import java.io.Serializable;

public class ContactForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5645246429669160966L;
	private String name, email, phone, message;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ContactForm [name=" + name + ", email=" + email + ", phone="
				+ phone + ", message=" + message + "]";
	}
	
	

}
