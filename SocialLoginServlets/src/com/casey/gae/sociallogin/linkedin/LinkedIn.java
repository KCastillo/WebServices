package com.casey.gae.sociallogin.linkedin;

public class LinkedIn {
	
	private static final String API_KEY = "u633iw6831t1";
	private static final String SECRET_KEY = "ChWwsCzkX9Gg6jMW";
	private static final String REDIRECT_URI = "http://oauthsociallogin.appspot.com/CallBackServlet2";
	
	private static final String FACEBOOK_AUTH_URI = "https://www.facebook.com/dialog/oauth";
	private static final String FACEBOOK_AUTHENTICATE = "https://graph.facebook.com/oauth/access_token";
	private static final String FACEBOOK_ACCESS_URL = "https://graph.facebook.com/";
	
	public String getApiKey() {
		return API_KEY;
	}


	public String getSecretKey() {
		return SECRET_KEY;
	}
	
	public String getLoginRedirectURL() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCallBackURL() {
		return REDIRECT_URI;
	}

}
