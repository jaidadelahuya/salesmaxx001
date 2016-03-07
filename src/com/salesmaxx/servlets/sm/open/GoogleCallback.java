package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.beans.GoogleDiscoveryDocument;

public class GoogleCallback extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7375513128114482438L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String state = req.getParameter("state");
		String code = req.getParameter("code");

		String clientId = getServletContext()
				.getInitParameter("GoogleClientId");
		String clientSecret = getServletContext().getInitParameter(
				"GoogleClientSecret");
		resp.getWriter().write("called");

	HttpSession session = req.getSession();
		Object o = null;
		Object o1 = null;
		GoogleDiscoveryDocument gdd = null;
		String sstate = null;
		synchronized (session) {
			o = session.getAttribute("googleState");
			o1 = session.getAttribute("gdd");
		}

		if (o != null && o1 != null) {
			sstate = (String) o;
			gdd = (GoogleDiscoveryDocument) o1;
		}

		if (state.trim().equals(sstate.trim())) {
			String redirect = resp
					.encodeRedirectURL("https://salesmaxx001.appspot.com/sm/open/google/callback");
			URL url = new URL(gdd.getTokenEndpoint().trim());
			HTTPRequest r = new HTTPRequest(url, HTTPMethod.POST);
			r.setHeader(new HTTPHeader("Content-Type",
					"application/x-www-form-urlencoded"));
			String payload = "code=" + code + "&client_id=" + clientId
					+ "&client_secret=" + clientSecret + "&redirect_uri="
					+ redirect+"&grant_type=authorization_code";
			r.setPayload(payload.getBytes());
			URLFetchService urlfetch = URLFetchServiceFactory.getURLFetchService();
			HTTPResponse response = urlfetch.fetch(r);
			resp.getWriter().write(new String(response.getContent()));

		} else {
			resp.sendError(401, "You are not authorized");
		}
	}
}
