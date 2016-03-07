package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.LoyaltyOrganisation;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.AddressController;
import com.salesmaxx.persistence.controllers.LoyaltyOrganisationController;
import com.salesmaxx.util.Util;

public class SaveLoyaltyOrganization extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4664891708011443630L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("organization-name");
		String website = req.getParameter("website");
		String street = req.getParameter("street");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String country = req.getParameter("country");
		String phones = req.getParameter("phone");
		String desc = req.getParameter("description");

		boolean ok = Util.notNull(name, street, city, state, country, phones,
				desc);
		if (!ok) {
			resp.sendError(
					HttpServletResponse.SC_EXPECTATION_FAILED,
					"Fill all fields in the form.");
		} else {
			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("image");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);

				ServingUrlOptions suo = ServingUrlOptions.Builder
						.withBlobKey(key);
				ImagesService is = ImagesServiceFactory.getImagesService();
				String url = is.getServingUrl(suo);

				LoyaltyOrganisation lo = new LoyaltyOrganisation(name);
				lo.setDescription(new Text(desc));
				lo.setImage(url);
				lo.setWebsite(website);
				String[] pns = phones.split(",");
				List<String> list = new ArrayList<>();
				for(String s: pns) {
					list.add(s);
				}
				
				Address ad = Util.createAddress(street, city, state, country);
				lo.setAddress(ad.getId());
				
				AddressController cnt = new AddressController();
				LoyaltyOrganisationController cont = new LoyaltyOrganisationController();
				try {
					cnt.create(ad);
					cont.create(lo);

				} catch (RollbackFailureException e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"We could not save the Loyalty Organization at this time. Please try again later.");
					e.printStackTrace();
				} catch (Exception e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"We could not save the Loyalty Organization at this time. Please try again later.");
					e.printStackTrace();
				}

			}

		}

	}
}
