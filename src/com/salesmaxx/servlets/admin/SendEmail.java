package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class SendEmail extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1207480642795107793L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("username");
		
		UserController cont = new UserController();
		User user = cont.findUserByUsername(email);
		if(user==null) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"An account with this email does not exist.");
		} else {
			String code = Util.generateRandomCode(100000, 999999);
			String from = Util.SERVICE_ACCOUNT;
			String title = "SalesMaxx Password Recovery Code";
			String body = "<h2>Password Recovery Code</h2>"
					+"<p>Your password recovery code is "+code+"</p>";
			System.out.println(code);
			try {
				Util.sendEmail(from, email, title, body);
				HttpSession session = req.getSession();
				synchronized (session) {
					session.setAttribute("user", user);
					session.setAttribute("recoveryCode", code);
					session.setAttribute("codeSent", true);
				}
				String url = resp.encodeURL("/password-recovery");
				resp.getWriter().write(url);
			} catch (AddressException e) {
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"The email "+email+ "is wrongly formatted.");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"We could not send a password recovery email to "+email);
			}
		}
	}
}
