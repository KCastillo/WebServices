package com.casey.gae.sociallogin;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class SocialLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp){
			try {
				processRequest(req,resp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Facebook facebook = new Facebook();
		resp.sendRedirect(facebook.getLoginRedirectURL());
		
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
