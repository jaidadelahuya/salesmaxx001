package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
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
import com.salesmaxx.beans.LinkedInAccessTokenResponse;
import com.salesmaxx.beans.SocialUser;
import com.salesmaxx.util.Util;

public class LinkedInCallback extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7148967060202889830L;
	private static final Logger log = Logger.getLogger(LinkedInCallback.class
			.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		String error = req.getParameter("error");
		String errorDesc = req.getParameter("error_description");

		String clientId = getServletContext().getInitParameter(
				"LinkedInClientId");
		String clientSecret = getServletContext().getInitParameter(
				"LinkedInClientSecret");

		if (Util.notNull(error)) {
			resp.getWriter().write(
					"<h2>Request was not completed</h2><p>" + errorDesc
							+ "</p>");
		} else {
			HttpSession session = req.getSession();

			String sstate = null;
			Object o = null;

			synchronized (session) {
				o = session.getAttribute("linkedInState");
			}

			if (o != null) {
				sstate = (String) o;

				if (state.equals(sstate)) {
					String redirect = resp
							.encodeRedirectURL("http://www.salesmaxx.com/sm/open/linkedin/callback");
					String payload = "grant_type=authorization_code&code="
							+ code + "&redirect_uri=" + redirect
							+ "&client_id=" + clientId + "&client_secret="
							+ clientSecret;
					URL url = new URL(
							"https://www.linkedin.com/uas/oauth2/accessToken");
					HTTPRequest r = new HTTPRequest(url, HTTPMethod.POST);
					r.addHeader(new HTTPHeader("content-type",
							"application/x-www-form-urlencoded"));
					r.setPayload(payload.getBytes());
					URLFetchService urlfetch = URLFetchServiceFactory
							.getURLFetchService();
					HTTPResponse response = null;
					try {
						response = urlfetch.fetch(r);
					} catch (SocketTimeoutException ste) {
						resp.getWriter().write(
								"Your connection timed out. Try again");
						return;
					}

					LinkedInAccessTokenResponse latr = Util
							.toLinkedInAccessToken(new String(response
									.getContent()));
					synchronized (session) {
						session.setAttribute("latr", latr);
					}
					url = new URL(
							"https://api.linkedin.com/v1/people/~:(id,firstName,headline,lastName,picture-url,email-address)?format=json");
					r = new HTTPRequest(url);
					r.addHeader(new HTTPHeader("Connection", "Keep-Alive"));
					r.addHeader(new HTTPHeader("Authorization", "Bearer "
							+ latr.getAccessToken()));
					urlfetch = URLFetchServiceFactory.getURLFetchService();
					try {
						response = urlfetch.fetch(r);
					} catch (SocketTimeoutException ste) {
						resp.getWriter().write(
								"Your connection timed out. Try again");
						return;
					}

					SocialUser su = Util.toLinkedInSocialUser(new String(
							response.getContent()));
					// resp.getWriter().write(su.toString());
					RequestDispatcher rd = req
							.getRequestDispatcher("/sm/open/social-media-login");
					synchronized (session) {
						session.setAttribute("socialUser", su);
					}

					rd.forward(req, resp);

				} else {
					resp.sendError(401, "You cannot get access");
				}
			}
		}

	}

}
