package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class VerifyCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3240924846526436484L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String uCode = req.getParameter("code");
		HttpSession session = req.getSession();
		Object o = session.getAttribute("recoveryCode");
		String rCode = null;
		if(uCode != null) {
			uCode = uCode.trim();
		}
		if(o!=null) {
			rCode = (String) o;
			if(rCode.equalsIgnoreCase(uCode)) {
				synchronized (session) {
					session.removeAttribute("recoveryCode");
					session.removeAttribute("codeSent");
				}
				String url = resp.encodeURL("/x-password");
				resp.getWriter().write(url);
			} else {
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"The confirmation code you entered in not valid");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"Sorry we cannot process your request");
		}
		
		
	}
}
