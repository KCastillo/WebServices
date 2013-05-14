package com.casey.gae.sociallogin;

public class FacebookToken {
	
	private String accessToken;
	private int expires;

	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public int getExpires() {
		return expires;
	}
	public void setExpires(int expires) {
		this.expires = expires;
	}
}
	
