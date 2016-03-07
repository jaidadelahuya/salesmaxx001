package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.persistence.controllers.UserGeneralInfoController;

public class UploadCertificate extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5597531023647928191L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String regId = req.getParameter("reg-id");
		if (regId != null && !regId.isEmpty()) {
			BlobstoreService bss = BlobstoreServiceFactory.getBlobstoreService();
			Map<String, List<BlobKey>> map = bss.getUploads(req);
			List<BlobKey> keys = map.get("certificate");
			BlobKey key = null;
			if (keys != null && !keys.isEmpty()) {
				key = keys.get(0);
				UserController cont = new UserController();
				Key ky = KeyFactory.createKey(User.class.getSimpleName(), regId.toUpperCase().trim());
				User user = cont.findUser(ky);
				if(user != null) {
					long id = user.getGeneralInfoId();
					UserGeneralInfoController con = new UserGeneralInfoController();
					UserGeneralInfo ugi = con.findUserGeneralInfo(user,id);
					if(ugi != null) {
						Set<BlobKey> blbKey = ugi.getCertificate();
						if(blbKey == null) {
							blbKey = new HashSet<BlobKey>();
						} 
						blbKey.add(key);
						//con.edit(ugi,user.getRegId());
					} else {
						resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fatal Error: contact chief admin.");
					}
				} else {
					resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "The Registration Id "+regId+" does not belong to any student.");
				}
			}
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "You have to enter a Student's Registration ID");
		}
		
	}
}
