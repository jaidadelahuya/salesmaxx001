package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.util.Util;

public class ClearCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4429860870503739093L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String qty = req.getParameter("qty");
		HttpSession session = req.getSession();

		Object o = null;

		synchronized (session) {
			o = session.getAttribute("user");
		}

		if (o != null) {
			User u = (User) o;
			Key ky = u.getCart();
			CartController cont = new CartController();
			Cart c = cont.findCart(ky);
			if (c != null) {
				c.setItems(Util.getUpdatedItems(c, id, qty, req));
				cont.edit(c);
				synchronized (session) {
					session.setAttribute("cart", c);
				}
			}

		} else {
			
			synchronized (session) {
				o = session.getAttribute("cart");
				if(o != null) {
					Cart c = (Cart) o;
					c.setItems(Util.getUpdatedItems(c, id, qty, req));
					session.setAttribute("cart", c);
				}
				
			}
			
		}
	}
	
	
}
