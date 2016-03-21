package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.util.Util;

public class LinkedInSignIn extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4122105247930992768L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String clientId = getServletContext().getInitParameter(
				"LinkedInClientId");
		String clientSecret = getServletContext().getInitParameter(
				"LinkedInClientSecret");
		String state = Util.generateRandomCode(1000000, 9999999);
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("linkedInState", state);
		}
		String redirect = resp
				.encodeRedirectURL("http://www.salesmaxx.com/sm/open/linkedin/callback");

		URL url = new URL(
				"https://www.linkedin.com/uas/oauth2/authorization?response_type=code&"
						+ "client_id=" + clientId + "&redirect_uri=" + redirect
						+ "&state=" + state);
		resp.sendRedirect(url.toString());
		

	}
}
