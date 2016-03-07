package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.servlets.sm.closed.ClosedUtil;

public class TestFetch extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = (String) req.getAttribute("txnRef");
		String amount = (String) req.getAttribute("amount");
		URL url = new URL(
				"https://stageserv.interswitchng.com/test_paydirect/api/v1/gettransaction.json?productid="
						+ ClosedUtil.INTERSWITCH_PRODUCT_ID
						+ "&transactionreference="
						+ txnRef
						+ "&amount="
						+ amount);
		HTTPRequest r = new HTTPRequest(url);
		r.addHeader(new HTTPHeader("Hash", ClosedUtil
				.getInterswitchHash(txnRef)));
		URLFetchService urlfetch = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = null;
		response = urlfetch.fetch(r);
		String respString = new String(response.getContent());
		req.setAttribute("txnRef", txnRef);
		req.setAttribute("interswitchResponse", respString);
		req.getRequestDispatcher("/interswicth-response").forward(req, resp);
	}
}
