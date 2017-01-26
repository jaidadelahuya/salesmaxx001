package com.salesmaxx.servlets.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.QueryResultList;
import com.salesmaxx.entities.CanceledWorkshop;
import com.salesmaxx.persistence.controllers.CancelWorkshopController;
import com.salesmaxx.util.Util;

public class GetCanceledWorkshop extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2597221233081436759L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String status = req.getParameter("status");
		String uid = req.getParameter("uid");

		if (Util.notNull(status)) {
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("canceledWorkshopPage");
			}
			CanceledWorkshopPageBean cwpb = null;
			if (o == null) {
				cwpb = new CanceledWorkshopPageBean();
				
			} else {
				cwpb = (CanceledWorkshopPageBean) o;
			}
			cwpb.setStatus(status);
			QueryResultList<Entity> ents = null;
			List<CancelWorkshopBean> list = null;
			if(status.equalsIgnoreCase("pending")) {
				if(Util.notNull(uid)) {
					ents = getResults(cwpb,null, uid);
				}else {
					ents = getResults(cwpb,cwpb.getPendingCursor());
				}
				
				cwpb.setPendingCursor(ents.getCursor().toWebSafeString());
				list = cwpb.getPendingWorkshops();
				list = addNewRecords(list, ents);
				cwpb.setPendingWorkshops(list);
			}else if (status.equalsIgnoreCase("cleared")) {
				if(Util.notNull(uid)) {
					ents = getResults(cwpb,null,uid);
				}else {
					ents = getResults(cwpb,cwpb.getClearedCursor());
				}
				cwpb.setClearedCursor(ents.getCursor().toWebSafeString());
				list = cwpb.getClearedWorkshops();
				list = addNewRecords(list, ents);
				cwpb.setClearedWorkhops(list);
			}
			
			synchronized (session) {
				session.setAttribute("canceledWorkshopPage", cwpb);
			}
			
			
			resp.sendRedirect("/sm-admin/workshops/canceled");
		}

	}
	
	private QueryResultList<Entity> getResults(CanceledWorkshopPageBean cwpb,String cursor) {
		return getResults(cwpb, cursor,  null);
	}

	private QueryResultList<Entity> getResults(CanceledWorkshopPageBean cwpb,
			String cursor,  String uid) {
		CancelWorkshopController c = new CancelWorkshopController();
		QueryResultList<Entity> ents = c.findByStatus(cwpb.getStatus(),
				cursor, uid);
		return ents;
	}

	private List<CancelWorkshopBean> addNewRecords(List<CancelWorkshopBean> list, QueryResultList<Entity> ents) {
		if(list ==null) {
			list = new ArrayList<>();
		}
		for(Entity e : ents) {
			CanceledWorkshop cw = Util.entityToCancelWorkshop(e);
			CancelWorkshopBean cwb = Util.toCanceledWorkshopBean(cw);
			list.add(cwb);
		}
		return list;
	}

	

}
