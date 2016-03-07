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
import com.salesmaxx.entities.EventTemplate;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.EventTemplateController;
import com.salesmaxx.util.Util;

public class SaveEvent extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2021281080970500726L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String eventName = req.getParameter("event-name");
		String credits = req.getParameter("credits");
		String description = req.getParameter("description");
		String eventId = req.getParameter("event-id");
		
		boolean ok = Util.notNull(eventName,credits,description,eventId);
		
		if(ok) {
			BlobstoreService bss = BlobstoreServiceFactory.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("image");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);
				EventTemplate et = new EventTemplate(eventId);
				et.setImage(key);
				et.setName(eventName.toUpperCase().trim());
				et.setSalesmaxxCredit(Double.parseDouble(credits));
				et.setShortDescription(new Text(description));

				EventTemplateController cont = new EventTemplateController();
				try {
					cont.create(et);
				} catch (RollbackFailureException e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"We could not save the Event at this time. Please try again later.");
					e.printStackTrace();
				} catch (Exception e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"We could not save the Event at this time. Please try again later.");
					e.printStackTrace();
				}

			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "We cannot save the Event Template at this time. Please try again later.");
		}

		
	}
}
