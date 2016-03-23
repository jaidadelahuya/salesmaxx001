package com.salesmaxx.servlets.sm.open;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.salesmaxx.beans.SocialUser;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class SocialMediaLogin extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 934479990285012354L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SocialUser su = null;
		HttpSession session = req.getSession();

		synchronized (session) {
			Object o = session.getAttribute("socialUser");
			if (o != null) {
				su = (SocialUser) o;
			} else {
				resp.getWriter().write(
						"<h2>You cannot get access</h2><p>Login properly</p>");
				return;
			}
		}

		User u = null;
		UserController uc = new UserController();

		if (su.getEmail() != null) {
			u = uc.findUserByUsername(su.getEmail().toLowerCase().trim());
		}

		if (u == null) {// user with username does not exists
			u = uc.findUserByOpenId(su);
			if (u == null) {
		
				u = Util.userFromSocialUser(su);
				u = uc.createUserFromSocial(u,su);
				u.setPictureUrl(su.getPictureUrl());
				Util.logUserIn(u, req, resp, false);
			} else {
				if (u.getEmails() != null && su.getEmail() != null) {
					if(!u.getEmails().contains(su.getEmail())) {
						u.getEmails().add(su.getEmail());
					}
					
				}
				switch (su.getNetwork()) {
				case LINKEDIN:
					u.setLinkedInId(su.getId());

					break;
				case TWITTER:
					u.setTwitterId(su.getId());
					break;
				case GOOGLE:
					u.setGoogleId(su.getId());
					break;
				case FACEBOOK:
					u.setFacebookId(su.getId());
					break;
				}
				uc.edit(u);
				u.setPictureUrl(su.getPictureUrl());
				Util.logUserIn(u, req, resp, false);
			}
		} else {// user with username exists
			if (u.getEmails() != null && su.getEmail() != null) {
				u.getEmails().add(su.getEmail());
			}
			switch (su.getNetwork()) {
			case LINKEDIN:
				u.setLinkedInId(su.getId());

				break;
			case TWITTER:
				u.setTwitterId(su.getId());
				break;
			case GOOGLE:
				u.setGoogleId(su.getId());
				break;
			case FACEBOOK:
				u.setFacebookId(su.getId());
				break;
			}
			uc.edit(u);
			u.setPictureUrl(su.getPictureUrl());
			Util.logUserIn(u, req, resp, false);
		}

	}

}
