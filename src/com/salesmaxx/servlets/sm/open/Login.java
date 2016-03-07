package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3929467490644758566L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		boolean ok = Util.notNull(username);
		if (!ok) {
			resp.sendError(800,
					"You have to enter your email or Registration ID.");
			return;
		}
		ok = Util.notNull(password);
		if (!ok) {
			resp.sendError(801, "You have to enter your password.");
			return;
		}

		boolean usingEmail = username.contains("@");
		UserController cont = new UserController();
		User user = null;
		password = Util.toSHA256(password.trim());
		if (usingEmail) {
			user = cont.findUserByUsername(username, password);
			if (user == null) {
				resp.sendError(802,
						"The username/password you entered do not match.");
				return;
			} else {
				
				Util.logUserIn(user,req,resp, true);
				

			}

		} else {// using reg-id

			Key key = KeyFactory.createKey(User.class.getSimpleName(), username
					.trim().toUpperCase());
			user = cont.findUser(key);
			if (user == null) {
				resp.sendError(803, "The Registration ID " + username
						+ " does not exist.");
				return;
			} else {
				if (user.getPassword().equals(password.trim())) {
					Util.logUserIn(user, req, resp, true);
				} else {
					resp.sendError(802,
							"The username/password you entered do not match.");
					return;
				}
			}

		}
	}

}
