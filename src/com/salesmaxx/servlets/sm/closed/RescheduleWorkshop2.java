package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.UserGeneralInfoController;

public class RescheduleWorkshop2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5571237059601379845L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		
		HttpSession session = req.getSession();
		Key oldId = null;
		Object o = null;
		Object o1 = null;
		Object o2 = null;
		
		synchronized (session) {
			o = session.getAttribute("workshopToBeRescheduled");
			o1 = session.getAttribute("user");
			o2 = session.getAttribute("ugi");
		}
		
		if(o != null) {
			oldId = (Key) o;
			if(String.valueOf(oldId.getId()).equals(id)) {
				
			} else {
				User user = (User) o1;
				UserGeneralInfo ugi = null;
				UserGeneralInfoController c = new UserGeneralInfoController();
				if(o2 == null) {
					ugi = c.findUserGeneralInfo(user,user.getGeneralInfoId());
				} else {
					ugi = (UserGeneralInfo) o2;
				}
				Set<Key> keys = ugi.getEnrolledWorkshops();
				
				for(Key k : keys) {
					if(k.equals(oldId)) {
						keys.remove(k);
						break;
					}
				}
				
				Key newKey = KeyFactory.createKey(oldId.getParent(), WorkShop.class.getSimpleName(), Long.parseLong(id));
				keys.add(newKey);
				ugi.setEnrolledWorkshops(keys);
				c.edit(ugi, user.getRegId());
				synchronized (session) {
					session.setAttribute("ügi",ugi);
				}
			}
		}
	}

}
