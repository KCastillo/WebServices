package com.casey.gae.sociallogin;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.google.appengine.labs.repackaged.org.json.JSONException;

public class Facebook {
	// Facebook
	private static final String CLIENT_ID = "286582231376507";
	private static final String REDIRECT_URI = "http://oauthsociallogin.appspot.com/CallBackServlet";
	private static final String CLIENT_SECRET = "dab0997b071b4b16881f048acee7b08d";

	private static final String FACEBOOK_AUTH_URI = "https://www.facebook.com/dialog/oauth";
	private static final String FACEBOOK_AUTHENTICATE = "https://graph.facebook.com/oauth/access_token";
	private static final String FACEBOOK_ACCESS_URL = "https://graph.facebook.com/";
	
	public static String getClientId() {
		return CLIENT_ID;
	}

	public static String getRedirectUri() {
		return REDIRECT_URI;
	}

	public static String getClientSecret() {
		return CLIENT_SECRET;
	}
	
    public String getLoginRedirectURL() {
    	return  FACEBOOK_AUTH_URI + "?" + 
			    "client_id=" + CLIENT_ID + "&" + 
			    "redirect_uri=" + REDIRECT_URI + "&" +
			    "scope=email";
    }

    public String getAuthURL(String authCode) {
    	return  FACEBOOK_AUTHENTICATE + "?" + 
    			"client_id=" + CLIENT_ID + "&" + 
			    "redirect_uri=" + REDIRECT_URI + "&" + 
			    "client_secret=" + CLIENT_SECRET + "&" +
			    "code=" + authCode;
    }
    
    public String getGraphAccessURL(String accessToken){
    	return FACEBOOK_ACCESS_URL + "me" + "?" + "access_token=" + accessToken;    	
    }

    public FacebookToken readAccessToken(String oAuthCode) throws ProtocolException, IOException, Exception{
    	FacebookToken facebookToken;
    	// App Authentication
		String authURL = this.getAuthURL(oAuthCode);
		URL url = new URL(authURL);
		String fbResponse = this.getResponseFor(url);
		//ParseString
    	facebookToken = this.parseFacebookToken(fbResponse);
   	
		return facebookToken;
	}	

    private FacebookToken parseFacebookToken(String result){

        FacebookToken facebookToken = new FacebookToken();
        
        String[] pairs = result.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length != 2) {
                throw new RuntimeException("Unexpected auth response");
            } else {
                if (kv[0].equals("access_token")) {
                	facebookToken.setAccessToken(kv[1]);
                }
                if (kv[0].equals("expires")) {
                	facebookToken.setExpires(Integer.valueOf(kv[1]));
                }
            }
        }
        return facebookToken;
    }
    
    public FacebookUser getFacebookUser(FacebookToken token) throws IOException, JSONException{
    	String authURL = getGraphAccessURL(token.getAccessToken());
		URL url = new URL(authURL);
		String response = this.getResponseFor(url);
		FacebookUser facebookUser = new FacebookUser();
		facebookUser.load(response);
		return facebookUser;
    }
    
    private String getResponseFor(URL url) throws IOException{
    	
    	// Setup connection with Facebook
		HttpURLConnection con = null;
    	
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
		
		// Read accessToken
		byte[] buffer = new byte[10];
    	String fbResponse = "";
		int bytesRead;
    				
		InputStream is = con.getInputStream();
		while ((bytesRead = is.read(buffer)) != -1){
			fbResponse += new String(buffer,0,bytesRead);
		}
		con.disconnect();
		
		return fbResponse;
	}
}
