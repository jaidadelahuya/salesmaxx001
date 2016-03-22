package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.SocketTimeoutException;
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
import com.salesmaxx.beans.FacebookAccessTokenResponse;
import com.salesmaxx.beans.SocialUser;
import com.salesmaxx.util.Util;

public class FacebookCallback extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6419930231827426621L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		String errorReason = req.getParameter("error_reason");
		String error = req.getParameter("error");
		String errorDesc = req.getParameter("error_description");

		if (Util.notNull(error)) {
			// send to error page
		} else {
			String clientId = getServletContext().getInitParameter(
					"FacebookClientId");
			String clientSecret = getServletContext().getInitParameter(
					"FacebookClientSecret");
			String redirect = resp
					.encodeRedirectURL("http://localhost:8888/sm/open/facebook/callback");
			HttpSession session = req.getSession();
			Object o = null;
			String sstate = null;
			synchronized (session) {
				o = session.getAttribute("facebookState");
			}
			if (o != null) {
				sstate = (String) o;
				if (sstate.equals(state)) {

					URL url = new URL(
							"https://graph.facebook.com/v2.5/oauth/access_token?"
									+ "client_id=" + clientId
									+ "&redirect_uri=" + redirect
									+ "&client_secret=" + clientSecret
									+ "&code=" + code);
					HTTPRequest r = new HTTPRequest(url);
					URLFetchService urlfetch = URLFetchServiceFactory
					
							.getURLFetchService();
					HTTPResponse response = null;
					try {
						response = urlfetch.fetch(r);
					} catch (SocketTimeoutException ste) {
						resp.getWriter().write("Your connection timed out. Try again.");
						return;
					}
					
					FacebookAccessTokenResponse fatr = Util
							.toFacebookAccessTokenResponse(new String(response
									.getContent()));
					synchronized (session) {
						session.setAttribute("fatr", fatr);
					}

					url = new URL(
							"https://graph.facebook.com/v2.5/me?access_token="
									+ fatr.getAccessToken()
									+ "&fields=first_name,last_name,verified,gender,picture,email");
					r = new HTTPRequest(url);
					urlfetch = URLFetchServiceFactory.getURLFetchService();
					response = urlfetch.fetch(r);
					// resp.getWriter().write(new
					// String(response.getContent()));
					SocialUser su = Util.toFaceBookSocialUser(new String(
							response.getContent()));
					// resp.getWriter().write(su.toString());
					RequestDispatcher rd = req
							.getRequestDispatcher("/sm/open/social-media-login");
					synchronized (session) {
						session.setAttribute("socialUser", su);
					}

					rd.forward(req, resp);

				} else {
					resp.sendError(401, "You cannot have access");
				}
			}
		}
	}

}
