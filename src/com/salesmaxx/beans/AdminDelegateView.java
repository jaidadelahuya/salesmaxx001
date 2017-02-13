package com.salesmaxx.beans;

import java.io.Serializable;

public class AdminDelegateView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -636061727856826950L;
	
	private String delegateName,email,phone,registrar,organization;

	public String getDelegateName() {
		return delegateName;
	}

	public void setDelegateName(String delegateName) {
		this.delegateName = delegateName;
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

	public String getRegistrar() {
		return registrar;
	}

	public void setRegistrar(String registrar) {
		this.registrar = registrar;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "AdminDelegateView [delegateName=" + delegateName + ", email="
				+ email + ", phone=" + phone + ", registrar=" + registrar
				+ ", organization=" + organization + "]";
	}
	
	

}
