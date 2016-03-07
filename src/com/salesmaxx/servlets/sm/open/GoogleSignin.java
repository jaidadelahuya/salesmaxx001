package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.URL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.beans.GoogleDiscoveryDocument;
import com.salesmaxx.util.Util;

public class GoogleSignin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2818277998190072154L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String clientId = getServletContext()
				.getInitParameter("GoogleClientId");
		String redirect = resp
				.encodeRedirectURL("https://salesmaxx001.appspot.com/sm/open/google/callback");
		String state = Util.generateRandomCode(100000000, 999999999);
		HttpSession session = req.getSession();
		synchronized (session) {
			session.setAttribute("googleState", state);
		}

		URL url = new URL(
				"https://accounts.google.com/.well-known/openid-configuration");
		HTTPRequest r = new HTTPRequest(url);
		URLFetchService urlfetch = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = urlfetch.fetch(r);
		GoogleDiscoveryDocument gdd = Util
				.toGoogleDiscoveryDocument(new String(response.getContent()));
		String uri = gdd.getAuthorizationEndpoint().trim() + "?client_id=" + clientId
				+ "&response_type=code&scope=openid%20email&redirect_uri="
				+ redirect + "&state="+state;
		synchronized (session) {
			session.setAttribute("googleState", state);
			session.setAttribute("gdd", gdd);
		}
		resp.sendRedirect(uri);
	}
}
