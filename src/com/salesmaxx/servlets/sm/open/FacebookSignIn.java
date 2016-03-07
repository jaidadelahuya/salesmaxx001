package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.util.Util;

public class FacebookSignIn extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866449492504128120L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String clientId = getServletContext().getInitParameter(
				"FacebookClientId");
		String clientSecret = getServletContext().getInitParameter(
				"FacebookClientSecret");
		String state = Util.generateRandomCode(1000000, 9999999);
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("facebookState", state);
		}
		String redirect = resp.encodeRedirectURL("https://salesmaxx001.appspot.com/sm/open/facebook/callback");
		String uri = "https://www.facebook.com/dialog/oauth?client_id="
						+ clientId + "&redirect_uri="+redirect+"&state="+state+"&response_type=code&scope=email";
		resp.sendRedirect(uri);
	}

}
