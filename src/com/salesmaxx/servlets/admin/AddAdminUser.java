package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.HashSet;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserRole;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class AddAdminUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1431093844634615530L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String role = req.getParameter("role");

		UserController cont = new UserController();

		User user = new User(role, username);
		user.setUsername(username.trim().toLowerCase());
		user.setPassword(password.trim());
		user.setHeadline(role);
		UserRole userRole = getUserRole(role);
		user.setRole(userRole);

	
			boolean created = cont.create(user);
			if (created) {
				String title = "You have been added to SalesMaxx Admin";
				String body = "<div><h2 style='background-color: red;color:white'>SalesMaxx</h2><div><p>Hello,</p>"
						+ "<p>You have been added to the SalesMaxx admin team. User the information below to login</p>"
						+ "<p><strong>URL: </strong>https://salesmaxx001.appspot.com/sm-admin or click <a href='https://salesmaxx001.appspot.com/sm-admin'>here<a></p>"
						+ "<p><strong>Username: </strong>"
						+ username
						+ "</p>"
						+ "<p><strong>Password: </strong>"
						+ password
						+ "</p>"
						+ "<p><strong>Role: </strong>"
						+ role
						+ "</p>"
						+ "<p>Regards,</p>";
				try {
					Util.sendEmail("profiliant.salesmaxx@gmail.com", username, title, body);
				} catch (AddressException e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,"");
					e.printStackTrace();
					return;
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(password);
				resp.getWriter().write("User created");
			} else {
				resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED,
						"The Username "+username+" already exists.");
			}
	}

	private UserRole getUserRole(String role) {
		UserRole[] userRoles = UserRole.values();
		UserRole userRole = null;
		for (UserRole ur : userRoles) {
			if (ur.name().equalsIgnoreCase(role)) {
				userRole = ur;
			}
		}
		return userRole;
	}
}
