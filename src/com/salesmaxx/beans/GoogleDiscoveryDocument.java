package com.salesmaxx.beans;

import java.io.Serializable;

public class GoogleDiscoveryDocument implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5582833303128856544L;
	
	private String authorizationEndpoint,tokenEndpoint,userinfoEndpoint;

	@Override
	public String toString() {
		return "GoogleDiscoveryDocument [authorizationEndpoint="
				+ authorizationEndpoint + ", tokenEndpoint=" + tokenEndpoint
				+ ", userinfoEndpoint=" + userinfoEndpoint + "]";
	}

	public String getAuthorizationEndpoint() {
		return authorizationEndpoint;
	}

	public void setAuthorizationEndpoint(String authorizationEndpoint) {
		this.authorizationEndpoint = authorizationEndpoint;
	}

	public String getTokenEndpoint() {
		return tokenEndpoint;
	}

	public void setTokenEndpoint(String tokenEndpoint) {
		this.tokenEndpoint = tokenEndpoint;
	}

	public String getUserinfoEndpoint() {
		return userinfoEndpoint;
	}

	public void setUserinfoEndpoint(String userinfoEndpoint) {
		this.userinfoEndpoint = userinfoEndpoint;
	}
	
	

}
