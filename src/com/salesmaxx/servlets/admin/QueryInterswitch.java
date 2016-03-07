package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.persistence.controllers.InterswitchTransactionLogController;
import com.salesmaxx.servlets.sm.closed.ClosedUtil;
import com.salesmaxx.util.Cursor;
import com.salesmaxx.util.InterswitchResponse;
import com.salesmaxx.util.Util;

public class QueryInterswitch extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1510857278540278921L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = req.getParameter("txnref");

		Object o = null;
		HttpSession session = req.getSession();
		synchronized (session) {
			o = session.getAttribute("interswitchTransactionLog");
		}

		InterswitchTransactionLog inter = null;
		if (o != null) {
		
			List<InterswitchTransactionLog> list = (List<InterswitchTransactionLog>) o;
			for (InterswitchTransactionLog itl : list) {
				if (itl.getTnxRef().equalsIgnoreCase(txnRef)) {
					inter = itl;
				}
			}

			URL url = new URL(
					"https://stageserv.interswitchng.com/test_paydirect/api/v1/gettransaction.json?productid="
							+ ClosedUtil.INTERSWITCH_PRODUCT_ID
							+ "&transactionreference="
							+ txnRef
							+ "&amount="
							+ Integer.parseInt(inter.getAmount()) * 100);
			HTTPRequest r = new HTTPRequest(url);
			r.addHeader(new HTTPHeader("Hash", ClosedUtil
					.getInterswitchHash(txnRef)));
			URLFetchService urlfetch = URLFetchServiceFactory
					.getURLFetchService();
			HTTPResponse response = null;
			try {
				response = urlfetch.fetch(r);
			} catch (Exception e) {
				req.setAttribute("interswitchQueryStatus", "error");
				RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/interswitch-transaction-log.jsp");
				rd.include(req, resp);
				return;
			}
			
			String respString = new String(response.getContent());
			InterswitchResponse ir = Util.parseInterswitchResponse(respString,
					txnRef);
			
			if (ir.getResponseCode()
					.equalsIgnoreCase(inter.getResponseCode())) {
				
				req.setAttribute("interswitchQueryStatus", "success");
				req.setAttribute("interswitchQueryStatusMesssage", "The Status of the transaction "+ir.getTxnRef()+" has not changed.");
			} else {
				req.setAttribute("interswitchQueryStatus", "success");
				if(ir.getResponseCode().equalsIgnoreCase("Z25")) {
					req.setAttribute("interswitchQueryStatusMesssage", "The Status of the transaction "+ir.getTxnRef()+" is pending.");
				} else {
					req.setAttribute("interswitchQueryStatusMesssage", "The Status of the transaction "+ir.getTxnRef()+" has changed and has been updated.");
				}
				
				list.remove(inter);
				inter.setResponseCode(ir.getResponseCode());
				inter.setResponseDescription(ir.getResponseDescriptor());
				list.add(inter);
				InterswitchTransactionLogController cont = new InterswitchTransactionLogController();
				cont.edit(inter);
			}
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/interswitch-transaction-log.jsp");
			rd.include(req, resp);
			
		}
	}

}
