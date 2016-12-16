package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.util.Util;

public class SaveScheduleWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1655227479987782074L;

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

		boolean ok = Util.notNull(workshopName, state,
				country, startDate, format, type);
		if (!ok) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
					"You have to fill up the form completely.");
			return;
		} else {

			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("flyer");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);
			}
			WorkShop wk = new WorkShop();
			wk.setFlyer(key);
			wk.setWorkshopType(format);
			wk.setVenue(venue);
			ok = Util.notNull(endDate);
			if (ok) {
				wk.setEndDate(Util.WebtoDate(endDate));
			}
			if(facilitators != null) {
				wk.setFacilitators(Arrays.asList(facilitators));
			}
			if (type.equalsIgnoreCase("paid")) {
				wk.setForSale(true);
			} else {
				wk.setForSale(false);
			}
			Address add = Util.createAddress(street, city, state, country);
			wk.setStartDate(Util.WebtoDate(startDate));
			Calendar c = new GregorianCalendar();
			c.setTime(wk.getStartDate());
			
			wk.setWorkshopType(format);
			//wk.setFlyer(key);

			List<WorkshopTemplate> temps = Util.getWorkshopTemplateFromCache();
			Key ky = KeyFactory.stringToKey(workshopName);
			WorkshopTemplate temp = Util.getWorkshopFromList(ky.getName(),
					temps);
			try {
				Util.addWorkshopToIndex(wk,add,temp);
			} catch (InterruptedException e) {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST,
						"Document has not been added to index.");
				return;
				
			}
			if (temp != null) {
				WorkshopController cont = new WorkshopController();
				cont.create(wk, add, temp);
			}
		}
	}

}
