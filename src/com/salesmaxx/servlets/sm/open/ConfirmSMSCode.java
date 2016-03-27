package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;

public class ConfirmSMSCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1199520325341980557L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");
		HttpSession session = req.getSession();
		Object o = null;
		synchronized (session) {
			o = session.getAttribute("smsCode");
		}
		if(o != null) {
			String c1 = (String) o;
			if(c1.equals(code.trim())) {
				User u = null;
				synchronized (session) {
					Object o1 = session.getAttribute("user");
					if(o1 != null) {
						 u = (User) o1;
						 u.setPhoneVerified(true);
					}
					session.setAttribute("codeVerified", true);
					session.removeAttribute("wrongSMSCode");
					session.setAttribute("user", u);
				}
				if(u!=null) {
					u.setAuthenticated(true);
					new UserController().edit(u);
				}
				resp.sendRedirect(resp.encodeRedirectURL("/sm/closed/profile/user-profile"));
			}else {
				synchronized (session) {
					session.removeAttribute("codeVerified");
					session.setAttribute("wrongSMSCode", true);
				}
				resp.sendRedirect(resp.encodeRedirectURL("/sm/open/sms-verification-page"));
			}
		}
	}

}
