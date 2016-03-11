package com.salesmaxx.servlets.features;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interswitch extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnref = req.getParameter("txnref");
		String payRef = req.getParameter("payRef");
		String retRef = req.getParameter("retRef");
		String cardNum = req.getParameter("cardNum");
		String apprAmt = req.getParameter("apprAmt");
		String custID = req.getParameter("cust_id");

		resp.getWriter().write(
				"txnref = " + txnref + "<br/> " + "payref = " + payRef
						+ "<br/>retref = " + retRef + "<br/>cardNum<br/> "
						+ cardNum + "apprAmt= " + apprAmt + " custid ="
						+ custID);
	}
}
