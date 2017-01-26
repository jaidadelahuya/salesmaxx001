package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.persistence.controllers.CancelWorkshopController;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.util.Util;

public class ClearCanceledWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6361909242792682715L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String webKey = req.getParameter("web-key");
		HttpSession session = req.getSession();
		if(Util.notNull(webKey)) {
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("canceledWorkshopPage");
			}
			
			if(o!=null) {
				CanceledWorkshopPageBean cwpb = (CanceledWorkshopPageBean) o;
				 List<CancelWorkshopBean> l =  cwpb.getPendingWorkshops();
				 Iterator<CancelWorkshopBean> it = l.iterator();
				 for(CancelWorkshopBean cwb = it.next(); it.hasNext();) {
					 if(cwb.getWebKey().equals(webKey)) {
						 Key key = KeyFactory.stringToKey(webKey);
						 CancelWorkshopController c = new CancelWorkshopController();
						 CanceledWorkshop cw = c.findByKey(key);
						 cw.setCleared(true);
						 EMF.getDs().put(Util.canceledWorkshopToEntity(cw));
						 it.remove();
						 List<CancelWorkshopBean> list =  cwpb.getClearedWorkshops();
						 list.add(0, cwb);
						 cwpb.setClearedWorkhops(list);
						 cwpb.setPendingWorkshops(l);
						 break;
					 }
				 }
				 
				 synchronized (session) {
					session.setAttribute("canceledWorkshopPage", cwpb);
				}
				 
				resp.sendRedirect("/sm-admin/workshops/canceled?status=cleared");
				 
			}
		}
	}

}
