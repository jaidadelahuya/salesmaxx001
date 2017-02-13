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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.QueryResultList;
import com.salesmaxx.beans.AdminDelegateView;
import com.salesmaxx.beans.AdminViewDelegatePage;
import com.salesmaxx.beans.AdminWorkshopPageBean;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.beans.WorkshopTemplateBean;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopDelegate;
import com.salesmaxx.util.Util;

public class GetDelegate extends HttpServlet {

	
	private static final long serialVersionUID = -2189529255910620594L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		if(Util.notNull(id)) {
			
			Key key = KeyFactory.createKey(WorkShop.class.getSimpleName(), Long.parseLong(id));
			QueryResultList<Entity> qrl = Util.getDelegatesForWorkshop(key);
			List<WorkshopDelegate> list = new ArrayList<>();
			for(Entity e : qrl) {
				WorkshopDelegate wd = new WorkshopDelegate(e);
				list.add(wd);
			}
			HttpSession session = req.getSession();
			Object o = null;
			synchronized (session) {
				o = session.getAttribute("workshop");
			}
			AdminWorkshopPageBean awpb = (AdminWorkshopPageBean) o;
			List<WorkshopTemplateBean> l = awpb.getWorkshopTemplate();
			ScheduleWorkshopDisplay swd = null;
			AdminViewDelegatePage avdp = new AdminViewDelegatePage();
			for(WorkshopTemplateBean wtb:l) {
				List<ScheduleWorkshopDisplay> schs = wtb.getSchedules();
				for(ScheduleWorkshopDisplay sd : schs) {
					if(id.equals(sd.getId())) {
						avdp.setWorkshopName(wtb.getName());
						avdp.setWorkshopCode(wtb.getCode());
						avdp.setState(sd.getStartDate());
						avdp.setState(sd.getLocation().getState());
						break;
						
					}
				}
			}
			List<AdminDelegateView> view = Util.toAdminDelegateView(list);
			avdp.setDelegates(view);
			synchronized (session) {
				session.setAttribute("delegatesPage", avdp);
			}
			resp.sendRedirect("/sm-admin/view-delegates");
			
		}
		
	}

}
