package com.casey.gae.sociallogin.linkedin;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.code.linkedinapi.client.oauth.LinkedInOAuthService;
import com.google.code.linkedinapi.client.oauth.LinkedInOAuthServiceFactory;
import com.google.code.linkedinapi.client.oauth.LinkedInRequestToken;

@SuppressWarnings("serial")
public class LinkedInServlet extends HttpServlet {
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		LinkedIn linkedIn = new LinkedIn();
		String consumerKeyValue = linkedIn.getApiKey();
		String consumerSecretValue = linkedIn.getSecretKey();
		
		final LinkedInOAuthService oauthService = LinkedInOAuthServiceFactory.getInstance().
								   createLinkedInOAuthService(consumerKeyValue, consumerSecretValue);

		LinkedInRequestToken requestToken = oauthService.getOAuthRequestToken(linkedIn.getCallBackURL());
		
		String authUrl = requestToken.getAuthorizationUrl();
		
		//session.setAttribute("requestToken", requestToken);
		resp.sendRedirect(authUrl);

		
	}

	@Override
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
}
