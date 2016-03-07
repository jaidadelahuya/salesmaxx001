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
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.salesmaxx.entities.GiftItem;
import com.salesmaxx.entities.exception.RollbackFailureException;
import com.salesmaxx.persistence.controllers.GiftItemController;
import com.salesmaxx.util.Util;

public class SaveGift extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8980684866566323797L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter("gift-name");
		String price = req.getParameter("price");
		String qty = req.getParameter("quantity");
		String cat = req.getParameter("category");
		String desc = req.getParameter("description");

		boolean ok = Util.notNull(name, price, qty, cat);
		if (ok) {
			BlobstoreService bss = BlobstoreServiceFactory
					.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("image");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);
				GiftItem gi = null;//new GiftItem();
				gi.setCategory(cat);
				if (Util.notNull(desc)) {
					gi.setDescription(new Text(desc));
				}
				gi.setName(name);
				gi.setPrice(Double.parseDouble(price));
				gi.setQuantity(Integer.parseInt(qty));
				gi.setImage(key);

				GiftItemController cont = new GiftItemController();
				try {
					cont.create(gi);
				} catch (RollbackFailureException e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"We could not save the gift item at this time. Please try again later.");
					e.printStackTrace();
				} catch (Exception e) {
					resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
							"We could not save the gift item at this time. Please try again later.");
					e.printStackTrace();
				}

			}
		} else {
			resp.sendError(HttpServletResponse.SC_EXPECTATION_FAILED,
					"We could not save the gift item at this time. Please try again later.");
		}
	}
}
