package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Date;
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
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.Event;
import com.salesmaxx.entities.EventTemplate;
import com.salesmaxx.persistence.controllers.EventController;
import com.salesmaxx.persistence.controllers.EventTemplateController;
import com.salesmaxx.util.Util;

public class AddEventToCalendar extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -355041680801700399L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String eventTemplateId = req.getParameter("event-name");
		String venue = req.getParameter("venue");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String startDate = req.getParameter("start-date");
		String endDate = req.getParameter("end-date");
		String speakers = req.getParameter("speakers");
		String time = req.getParameter("time");

		boolean ok = Util.notNull(eventTemplateId, venue, street, city, state,
				country, startDate, endDate, speakers,time);
		if (ok) {
			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("flyer");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);
				Key etKey = KeyFactory.stringToKey(eventTemplateId);
				Date sDate = Util.WebtoDate(startDate);
				Date eDate = Util.WebtoDate(endDate);
				if (sDate != null && eDate != null) {
					List<String> spks = Util.stringToList(speakers, ",");
					Event e = new Event(etKey, sDate, eDate, venue.trim()
							.toUpperCase(), spks);
					Address address = Util.createAddress(street, city, state,
							country);
					e.setLocation(address.getId());
					e.setFlyer(key);
					e.setTime(time);
					EventController eCon = new EventController();
					eCon.create(e, address);
				} else {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"Enter dates in the form MM//DD//YYYY.");
				}

			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"Fill all fields in the form.");
		}
	}

}
