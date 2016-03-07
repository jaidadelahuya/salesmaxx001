package com.salesmaxx.servlets.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.entities.User;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class ChangePassword extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6510516691685020697L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String pass1 = req.getParameter("password1");
		String pass2 = req.getParameter("password2");
		String oldPass =req.getParameter("old-password");
		
		if(pass1 != null && !pass1.isEmpty()) {
			if(pass1.equals(pass2)) {
				HttpSession session = req.getSession();
				Object o = null;
				User user = null;
				synchronized (session) {
					o = session.getAttribute("user");
				}
				if(o != null) {
					user = (User) o;
					if(oldPass != null && !oldPass.trim().isEmpty()) {
						oldPass = oldPass.trim();
						if(user.getPassword().equals(oldPass)) {
							user.setPassword(pass1);
							synchronized (session) {
								session.setAttribute("user", user);
							}
							UserController cont = new UserController();
						
								cont.edit(user);
							
						}else {
							resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "The old password you entered is not correct.");
						}
					} else {
						resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "You have to enter your old password.");
					}
					
				} else {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "We could not change your password, Please try again later");
				}
			} else {
				resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "The passwords do not match");
			}
		}else {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED, "You have to enter a password");
		}
	}
}
