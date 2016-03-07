package com.salesmaxx.beans;

import java.io.Serializable;

public class FacebookAccessTokenResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4229192455414878633L;
	
	String accessToken,tokenType,expires;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		return "FacebookAccessTokenResponse [accessToken=" + accessToken
				+ ", tokenType=" + tokenType + ", expires=" + expires + "]";
	}
	
	

}
