package com.casey.gae.sociallogin.linkedin;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.linkedinapi.client.oauth.LinkedInAccessToken;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

@SuppressWarnings("serial")
public class CallBackServlet2 extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
		try {
			processRequest(req,resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp){
		try {
			processRequest(req, resp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
		//Get Parameters
		String oauthVerifier = req.getParameter("oauth_verifier");
		
		//Get App Info
		LinkedIn linkedIn = new LinkedIn();
		
		//create the oauth service
		final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().
							createLinkedInOAuthService(linkedIn.getApiKey(),linkedIn.getSecretKey());

		
		//Create the request token 
		LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(linkedIn.getCallBackURL());
		String authUrl = requestToken.getAuthorizationUrl();

		//finally: The access token !!!
		LinkedInAccessToken accessToken = oauthService.getOAuthAccessToken(requestToken, oauthVerifier);
		
		resp.getWriter().write(accessToken.toString());

	}	
}
