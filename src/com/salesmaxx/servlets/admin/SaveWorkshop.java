package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.WorkshopTemplateController;
import com.salesmaxx.util.Util;

public class SaveWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5891978971278990743L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String workshopName = req.getParameter("workshop-name");
		String format = req.getParameter("format");
		String gender = req.getParameter("gender");
		String priority = req.getParameter("priority");
		String price = req.getParameter("price");
		String salesmaxxCredits = String.valueOf(Math.ceil(Double
				.parseDouble(price) / 100));
		String[] skillLevel = req.getParameterValues("skill-level");
		String description = req.getParameter("description");
		String[] relatedWorkshops = req.getParameterValues("related-workshops");
		String audiences = req.getParameter("audience");
		String courseContent = req.getParameter("course-content");
		String learningOutcomes = req.getParameter("learning-outcomes");
		String[] industries = req.getParameterValues("industries");
		String[] professions = req.getParameterValues("professions");

		boolean ok = Util.notNullArray(skillLevel);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to select at least one Skill Level");
			return;
		}
		ok = Util.notNullArray(industries);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to select at least one Industry");
			return;
		}

		ok = Util.notNullArray(professions);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to select at least one Profession");
			return;
		}
		ok = Util.notNullArray(relatedWorkshops);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to select at least one Related Workshop");
			return;
		}
		ok = Util.notNull(workshopName, format, gender, priority, price,
				description, audiences, courseContent, learningOutcomes);

		if (ok) {
			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("image");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				String workshopID = Util.getWorkshopId(workshopName.trim());
				key = keys.get(0);
				WorkshopTemplate wst = new WorkshopTemplate(workshopID.trim()
						.toUpperCase());
				wst.setWorkshopName(workshopName.trim().toUpperCase());
				wst.setFormat(format);
				wst.setGender(gender);
				wst.setPriority(priority);
				wst.setPrice(Double.parseDouble(price));
				wst.setSalesmaxxCredits(Double.parseDouble(salesmaxxCredits));
				wst.setSkillLevel(Arrays.asList(skillLevel));
				wst.setShortDescription(new Text(description));
				wst.setAudiences(Util.stringToList(audiences, ";"));
				wst.setCourseContent(Util.stringToList(courseContent, ";"));
				wst.setLearningOutcomes(Util
						.stringToList(learningOutcomes, ";"));
				wst.setWorkshopImage(key);
				wst.setIndustries(Arrays.asList(industries));
				wst.setProfessions(Arrays.asList(professions));
				wst.setCatalogueLink(Util.getCatalogueLink(workshopName));

				// set related workshops
				List<String> rws = Arrays.asList(relatedWorkshops);
				WorkshopTemplateController cont = new WorkshopTemplateController();
				String invalidKey = null;
				Key ky = null;
				WorkshopTemplate wt = null;
				List<Key> wtks = new ArrayList<>();
				for (String s : rws) {
					ky = KeyFactory.createKey(
							WorkshopTemplate.class.getSimpleName(), s.trim());
					wtks.add(ky);
				}

				wst.setRelatedWorkshops(wtks);
				WorkshopTemplateController con = new WorkshopTemplateController();

				con.create(wst);

			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"You have to select a Workshop Image");
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Fill out the form completely");
		}
	}
}
