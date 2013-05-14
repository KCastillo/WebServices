package com.casey.gae.sociallogin;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class CallBackServlet extends HttpServlet {
	
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
		
		// Obtain Authorization code
		String oAuthCode = req.getParameter("code");
		if (oAuthCode == null){
			resp.sendRedirect("/");
		}else {
			
			Facebook facebook = new Facebook();
			try {
				FacebookToken token = facebook.readAccessToken(oAuthCode);
				FacebookUser facebookUser = facebook.getFacebookUser(token);
				resp.getWriter().write(this.getUserHTML(facebookUser)); // What will we do with this user info. ?????
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	private String getUserHTML(FacebookUser facebookUser) {
		String html = "";
		html += "First Name: " + facebookUser.getFirstName() + "<br/>";
		html += "Last Name: " + facebookUser.getLastName() +  "<br/>";
		html += "Email: " + facebookUser.getEmail();

		return html;
	}
}
