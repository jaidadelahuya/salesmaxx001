package com.salesmaxx.beans;

import java.io.Serializable;

import com.salesmaxx.util.Util;



public class SignUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 743244297729061083L;

	private String username,password,confirmationCode,firstName,lastName;
	
	

	public SignUp() {
		this.confirmationCode = Util.generateRandomCode(100000, 999999);
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



	@Override
	public String toString() {
		return "SignUp [username=" + username + ", password=" + password
				+ ", confirmationCode=" + confirmationCode + ", firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}



	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Util.toSHA256(password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
