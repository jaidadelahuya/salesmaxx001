package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.salesmaxx.beans.CartItem;
import com.salesmaxx.beans.RescheduleSummaryBean;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.persistence.controllers.CartController;

public class AddRescheduleToCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3335083335204857305L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String mode = req.getParameter("mode");
		HttpSession session = req.getSession();
		
		Object o = null;
		Object o1 = null;
		Object o2 = null;
		synchronized (session) {
			o = session.getAttribute("rescheduleSummaryBean");
			o1 = session.getAttribute("user");
			o2 = session.getAttribute("cart");
		}
		if(o!=null && o1!=null && o2!=null ) {
			RescheduleSummaryBean rsb = (RescheduleSummaryBean) o;
			User u = (User) o1;
			Cart c = (Cart) o2;
			EmbeddedEntity ee = new EmbeddedEntity();
			ee.setUnindexedProperty("workshopID", rsb.getNewWorkshop().getId());
			ee.setUnindexedProperty("qty", rsb.getNoDelegates());
			ee.setUnindexedProperty("itemType", CartItem.ItemType.RESCHEDULE.name());
			ee.setUnindexedProperty("unitPrice", rsb.getCostPerDelegates());
			c.getItems().add(ee);
			new CartController().edit(c);
			synchronized (session) {
				session.setAttribute("cart", c);
			}
		}
		
	}
}
