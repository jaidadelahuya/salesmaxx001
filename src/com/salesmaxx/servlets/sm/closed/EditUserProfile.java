package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class EditUserProfile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -331375840492004654L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String headline = req.getParameter("headline");
		String dob = req.getParameter("dob");
		String phone = req.getParameter("phone");
		String website = req.getParameter("website");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String facebook = req.getParameter("facebook");
		String linkedin = req.getParameter("linkedin");
		String google = req.getParameter("google");
		String tweeter = req.getParameter("tweeter");

		Object o = null;
		Object o1 = null;

		HttpSession session = req.getSession();

		synchronized (session) {
			o = session.getAttribute("user");
			o1 = session.getAttribute("ugi");
		}

		User u = (User) o;
		UserGeneralInfo ugi = (UserGeneralInfo) o1;

		if (Util.notNull(firstName)) {
			u.setFirstName(firstName);
		}

		if (Util.notNull(lastName)) {
			u.setLastName(lastName);
		}

		if (Util.notNull(headline)) {
			u.setHeadline(headline);
		}

		if (Util.notNull(dob)) {
			ugi.setDateOfBirth(Util.WebtoDate(dob));
		}
		
		if (Util.notNull(website)) {
			ugi.setWebsite(website);
		}

		if (Util.notNull(phone)) {
			List<String> phones = ugi.getPhones();
			if (phones == null) {
				phones = new ArrayList<>();
			} else if(!phone.contains(phone)) {
				phones.add(phone);
			}
			ugi.setPhones(phones);

		}

		Address a = new Address(null);
		if (Util.notNull(street)) {
			a.setStreet(street);
		}

		if (Util.notNull(city)) {
			a.setCity(city);
		}

		if (Util.notNull(state)) {
			a.setState(state);

		}

		if (Util.notNull(country)) {
			a.setCountry(country);
		}


		List<EmbeddedEntity> sml = ugi.getSocialMedia();

		if (sml == null) {
			sml = new ArrayList<>();
		}

		if (Util.notNull(facebook)) {
			EmbeddedEntity e = new EmbeddedEntity();
			e.setProperty("type", "facebook");
			e.setProperty("url", facebook);
			sml.add(e);
		}

		if (Util.notNull(linkedin)) {
			EmbeddedEntity e = new EmbeddedEntity();
			e.setProperty("type", "likedin");
			e.setProperty("url", linkedin);
			sml.add(e);
		}

		if (Util.notNull(google)) {
			EmbeddedEntity e = new EmbeddedEntity();
			e.setProperty("type", "google");
			e.setProperty("url", google);
			sml.add(e);
		}

		if (Util.notNull(tweeter)) {
			EmbeddedEntity e = new EmbeddedEntity();
			e.setProperty("type", "tweeter");
			e.setProperty("url", tweeter);
			sml.add(e);
		}
		
		ugi.setSocialMedia(sml);
		
		synchronized (session) {
			session.setAttribute("user", u);
			session.setAttribute("ugi", ugi);
		}
		
		new UserController().edit(u, ugi, a);

	}
}
