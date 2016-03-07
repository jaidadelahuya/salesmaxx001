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
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.util.Util;

public class ScheduleWorkshoped extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -786300834884350330L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String workshopName = req.getParameter("workshop-name");
		String venue = req.getParameter("venue");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String startDate = req.getParameter("start-date");
		String endDate = req.getParameter("end-date");
		String format = req.getParameter("format");
		String type = req.getParameter("type");
		String[] facilitators = req.getParameterValues("facilitators");

		boolean ok = Util.notNull(workshopName, venue, street, city, state,
				country, startDate, format, type);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to fill up the form completely.");
			return;
		} else {
			ok = Util.notNullArray(facilitators);
			if (!ok) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"You have to select at least one Facilitator");
				return;
			} else {
				BlobstoreService bss = BlobstoreServiceFactory
						.getBlobstoreService();
				Map<String, List<BlobKey>> map = bss.getUploads(req);
				List<BlobKey> keys = map.get("image");
				BlobKey key = null;
				if (keys != null && !keys.isEmpty()) {
					WorkShop wk = new WorkShop();
					System.out.print(startDate);
				}
			}
		}

	}

}
