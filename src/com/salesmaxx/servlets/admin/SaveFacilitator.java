package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.entities.Facilitator;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.UserRole;
import com.salesmaxx.persistence.controllers.FacilitatorController;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class SaveFacilitator extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5577569329378726866L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String firstName = req.getParameter("first-name");
		String lastName = req.getParameter("last-name");
		String education = req.getParameter("education");
		String certification = req.getParameter("certification");
		String workExperience = req.getParameter("work-experience");
		String recentDevelopment = req.getParameter("recent-development");
		String specialization = req.getParameter("specialization");
		String profile = req.getParameter("profile");

		boolean ok = Util.notNull(firstName, lastName, education,
				certification, workExperience, recentDevelopment,
				specialization,profile);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"You have to fill all fields in the form.");
		} else {
			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("picture");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);

				Facilitator fac = new Facilitator();
				fac.setProfile(new Text(profile));
				fac.setFirstName(firstName);
				fac.setLastName(lastName);
				fac.setPicture(key);
				fac.setEducation(Util.stringToList(education, ";"));
				fac.setCertification(Util.stringToList(certification, ";"));
				fac.setWorkExperience(Util.stringToList(workExperience, ";"));
				fac.setRecentDevelopment(Util.stringToList(recentDevelopment,
						";"));
				fac.setSpecialization(Util.stringToList(specialization, ";"));
				FacilitatorController cont = new FacilitatorController();
				cont.create(fac);
				resp.getWriter().write(
						"Saved information for " + firstName + " " + lastName);

			}
		}
	}

}
