package com.salesmaxx.servlets.sm.open;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.salesmaxx.beans.CartItem;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.Util;

public class AddToCart extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5825219699865908505L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		synchronized (session) {
			session.removeAttribute("itemExist");
			session.removeAttribute("existingItem");
		}
		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/open/cart.jsp");
		rd.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String qty = req.getParameter("qty");

		boolean ok = Util.notNull(id, qty);
		HttpSession session = req.getSession();
		synchronized (session) {
			session.removeAttribute("itemExist");
			session.removeAttribute("existingItem");
		}

		if (!ok) {
			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/cart.jsp");
			rd.include(req, resp);
		} else {
			WorkShop w = Util.getWorkshopSchedule(id);
			
			Object o = null;
			Object o1 = null;
			synchronized (session) {
				o = session.getAttribute("user");
				o1 = session.getAttribute("cart");
			}

			EmbeddedEntity ee = new EmbeddedEntity();
			ee.setProperty("workshopID", id);
			ee.setProperty("qty", qty);
			Cart cart = null;
			CartController cont = new CartController();
			boolean exists = false;
			if (o != null) {
				User u = (User) o;

				UserController con = new UserController();
				if (u.getCart() == null) {
					cart = new Cart();
					List<EmbeddedEntity> list = new ArrayList<EmbeddedEntity>();
					list.add(ee);
					cart.setItems(list);

				} else {

					cart = cont.findCart(u.getCart());
					List<EmbeddedEntity> ents = cart.getItems();
					Set<CartItem> items = Util.getCartItems(ents);
					CartItem item = null;
					for (CartItem ci : items) {
						if (ee.getProperty("workshopID").equals(
								String.valueOf(ci.getId()))) {
							exists = true;
							item = ci;
							break;
						}
					}

					if (exists) {

						synchronized (session) {
							session.setAttribute("itemExist", true);
							session.setAttribute("existingItem", item);
						}
					} else {
						ents.add(ee);
						cart.setItems(ents);
					}

				}
				if (!exists) {
					con.edit(u, cart);
				}

			} else {

				if (o1 == null) {
					cart = new Cart();
					List<EmbeddedEntity> l = new ArrayList<EmbeddedEntity>();
					l.add(ee);
					cart.setItems(l);
				} else {
					cart = (Cart) o1;
					List<EmbeddedEntity> ents = cart.getItems();
					Set<CartItem> items = Util.getCartItems(ents);
					CartItem item = null;
					for (CartItem ci : items) {
						if (ee.getProperty("workshopID").equals(
								String.valueOf(ci.getId()))) {
							exists = true;
							item = ci;
							break;
						}
					}
					if (exists) {

						synchronized (session) {
							session.setAttribute("itemExist", true);
							session.setAttribute("existingItem", item);
						}
					} else {
						ents.add(ee);
						cart.setItems(ents);
					}
				}

			}

			List<WorkshopTemplate> relatedWorkshops = Util
					.getCartRelatedWorkshops(cart.getCartItems());

			synchronized (session) {
				session.setAttribute("cart", cart);
				session.setAttribute("cartRelatedWorkshops", relatedWorkshops);
			}
			String url = resp.encodeRedirectURL("/sm/open/cart");
			resp.getWriter().write(url);
		}

	}

}
