package com.salesmaxx.beans;

import java.io.Serializable;

public class LinkedInAccessTokenResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2341836390558306279L;
	private String accessToken, expires;

	@Override
	public String toString() {
		return "LinkedInAccessTokenResponse [accessToken=" + accessToken
				+ ", expires=" + expires + "]";
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}
	
	
}
