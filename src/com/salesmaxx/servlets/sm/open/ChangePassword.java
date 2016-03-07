package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class ChangePassword extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9206446994648391242L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");
		String oldPass = req.getParameter("old-pass");
		
		boolean ok = Util.notNull(oldPass);
		if(!ok) {
			resp.sendError(805, "You have to enter your old password.");
			return;
		}
		
		ok = Util.notNull(pass1);
		if(!ok) {
			resp.sendError(800, "You have to enter a password.");
			return;
		}
		
		ok = Util.notNull(pass2);
		if(!ok) {
			resp.sendError(801, "You have to retype your password.");
			return;
		}
		
		ok = pass1.equals(pass2);
		
		if(!ok) {
			resp.sendError(802, "The passwords do not match.");
			return;
		} else {
			HttpSession session = req.getSession();
			
			Object o = null;
			
			synchronized (session) {
				o = session.getAttribute("user");
			}
			
			if(o != null) {
				User u = (User) o;
				oldPass = Util.toSHA256(oldPass.trim());
				if(u.getPassword().equals(oldPass)) {
					u.setPassword(Util.toSHA256(pass1));
					UserController c = new UserController();
					c.edit(u);
					synchronized (session) {
						session.setAttribute("user", u);
					}
					String body = Util.getPasswordChangedMessage(u.getFirstName());
					try {
						Util.sendEmail(Util.SERVICE_ACCOUNT, u.getUsername(), "Your password has been changed", body);
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					resp.sendError(804, "The old password your entered is not correct.");
					return;
				}
				
			}
		}
	}

}
