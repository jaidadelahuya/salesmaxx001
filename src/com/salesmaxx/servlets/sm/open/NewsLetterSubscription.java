package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

public class NewsLetterSubscription extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4221971904889649029L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		
		URL url = new URL(
				"https://campaigns.zoho.com/api/json/listsubscribe");
		HTTPRequest r = new HTTPRequest(url, HTTPMethod.POST);
		String payload = "authtoken=877b093e8098d368e72bf08c622a02ab&scope=CampaignsAPI&listkey=2630b9fdc725b770af6e68b1cda81d4b8060bef678df20a0&resfmt=JSON&contactinfo={Contact Email:"
				+ email+"}";
		r.setPayload(payload.getBytes());
		URLFetchService urlfetch = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = urlfetch.fetch(r);
		System.out.println(new String(response.getContent()));
	}

}
