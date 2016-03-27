package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.util.Util;
import com.twilio.sdk.TwilioRestException;

public class SendSMS extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922578884581640891L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String to = req.getParameter("phone");
		String smsCode = Util.generateRandomCode(111111, 999999);
		System.out.println(smsCode);
		String msg = "To verify your phone number with SalesMaxx. Enter this Code "+smsCode;
		HttpSession session = req.getSession();
		Object o = null;
		try {
			Util.sendSMS(to,msg);
			synchronized (session) {
				o = session.getAttribute("user");
				if(o !=null) {
					User u = (User) o;
					u.setPrimaryPhone(to);
					session.setAttribute("user",u);
				}
				session.removeAttribute("signUp");
				session.setAttribute("smsCode",smsCode);
			}
			resp.sendRedirect(resp.encodeRedirectURL("/sm/open/sms-verification-page"));
		} catch (TwilioRestException e) {
			synchronized (session) {
				session.setAttribute("twilioExeption", true);
				e.printStackTrace();
			}
			resp.getWriter().write("we could not send ans SMS to your number");
		}
	}

}
