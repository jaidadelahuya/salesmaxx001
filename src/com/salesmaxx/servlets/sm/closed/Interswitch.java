package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.salesmaxx.beans.CartItem;
import com.salesmaxx.beans.SocialUser;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.entities.ProductPaidFor;
import com.salesmaxx.entities.PurchaseHistory;
import com.salesmaxx.entities.PurchaseableItem;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.persistence.controllers.InterswitchTransactionLogController;
import com.salesmaxx.persistence.controllers.PurchaseableItemController;
import com.salesmaxx.persistence.controllers.UserGeneralInfoController;
import com.salesmaxx.util.InterswitchResponse;
import com.salesmaxx.util.Util;

public class Interswitch extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8612079327020494940L;
	private static final Logger log = Logger.getLogger(Interswitch.class
			.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object o = null;
		Cart c = null;
		synchronized (session) {

			o = session.getAttribute("cart");
		}

		if (o != null) {
			c = (Cart) o;
		}

		// addWorkshopToEnrolledWokshops(c, user);

		if (o != null) {
			c = (Cart) o;
			Set<CartItem> cartItems = c.getCartItems();
			c.setItems(new ArrayList<EmbeddedEntity>());
			session.setAttribute("cart", c);
			CartController cc = new CartController();
			cc.edit(c);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String txnRef = req.getParameter("txnref");
		HttpSession session = req.getSession();

		Object o = null;
		Cart c = null;
		User user = null;

		synchronized (session) {
			user = (User) session.getAttribute("user");
			o = session.getAttribute("cart");
		}
		if (o != null) {
			c = (Cart) o;
		}

		URL url = new URL(
				"https://stageserv.interswitchng.com/test_paydirect/api/v1/gettransaction.json?productid="
						+ ClosedUtil.INTERSWITCH_PRODUCT_ID
						+ "&transactionreference="
						+ txnRef
						+ "&amount="
						+ c.getSubTotal() * 100);
		HTTPRequest r = new HTTPRequest(url);
		r.addHeader(new HTTPHeader("Hash", ClosedUtil
				.getInterswitchHash(txnRef)));
		URLFetchService urlfetch = URLFetchServiceFactory.getURLFetchService();
		HTTPResponse response = null;
		// try {
		response = urlfetch.fetch(r);
		String respString = new String(response.getContent());
		InterswitchResponse ir = Util.parseInterswitchResponse(respString,
				txnRef);

		synchronized (session) {
			session.setAttribute("interswitch", ir);
		}
		String toEmail = null;
		if (ir.getResponseCode().equalsIgnoreCase("00")) {

			Long id = user.getGeneralInfoId();
			UserGeneralInfoController cont = new UserGeneralInfoController();
			UserGeneralInfo ugi = cont.findUserGeneralInfo(user, id);
			ugi = addWorkshopToEnrolledWokshops(c, ugi, user);

			List<PurchaseHistory> phs = getPurchaseHistory(c, ugi, ir);
			UserGeneralInfoController c1 = new UserGeneralInfoController();
			c1.edit(ugi, user.getRegId(), phs);

			String body = ClosedUtil.getEmailBody(session);

			if (user.getUsername() == null) {
				Object ob = null;
				synchronized (session) {
					ob = session.getAttribute("socialUser");
				}
				if (o != null) {
					toEmail = ((SocialUser) ob).getEmail();
					if(toEmail == null) {
						return;
					}
				}

			} else {
				toEmail = user.getUsername();
			}

			Util.sendEmailNotification(toEmail, "Your Transaction Details",
					body);
		}

		List<ProductPaidFor> ppfs = null;
		int subTotal = 0;
		if (o != null) {
			c = (Cart) o;
			Set<CartItem> cartItems = c.getCartItems();
			ppfs = ClosedUtil.toProductsPaidFor(cartItems);
			subTotal = c.getSubTotal();
			if (ir.getResponseCode().equalsIgnoreCase("00")) {
				c.setItems(new ArrayList<EmbeddedEntity>());
				synchronized (session) {
					session.setAttribute("cart", c);
					req.setAttribute("toEmail", toEmail);
				}
				CartController cc = new CartController();
				cc.edit(c);
			}

		}

		InterswitchTransactionLog itl = new InterswitchTransactionLog();
		itl.setApprAmount(ir.getAmount());
		itl.setAmount(String.valueOf(subTotal));
		itl.setCustomerId(user.getRegId().getName());
		itl.setDate(ir.getTransactionDate());
		itl.setResponseCode(ir.getResponseCode());
		itl.setResponseDescription(ir.getResponseDescriptor());
		itl.setTnxRef(ir.getTxnRef());
		itl.setProductsPaidFor(new HashSet<Key>());
		InterswitchTransactionLogController c1 = new InterswitchTransactionLogController();
		c1.create(itl, ppfs);

		RequestDispatcher rd = req
				.getRequestDispatcher("/WEB-INF/sm/closed/interswitch-response.jsp");
		rd.include(req, resp);

		/*
		 * } catch (Exception e) { InterswitchResponse ir = null; synchronized
		 * (session) { session.setAttribute("interswitch", ir);
		 * session.setAttribute("txnRef", txnRef); } RequestDispatcher rd = req
		 * .getRequestDispatcher("/WEB-INF/sm/closed/interswitch-response.jsp");
		 * rd.include(req, resp); }
		 */

		/*
		 * req.setAttribute("txnRef", txnRef); req.setAttribute("amount",
		 * "1000000"); RequestDispatcher rd = req
		 * .getRequestDispatcher("/test-fetch"); rd.forward(req, resp);
		 */

	}

	private List<PurchaseHistory> getPurchaseHistory(Cart c,
			UserGeneralInfo ugi, InterswitchResponse ir) {
		Set<CartItem> cis = c.getCartItems();
		List<PurchaseHistory> phs = new ArrayList<PurchaseHistory>();
		List<PurchaseableItem> items = new ArrayList<>();
		PurchaseHistory ph = new PurchaseHistory();
		ph.setPurchaseDate(new Date());
		ph.setStatus("Pending");
		ph.setTotal(Double.parseDouble(ir.getAmount()));
		ph.setTxnRef(ir.getTxnRef());
		for (CartItem ci : cis) {
			PurchaseableItem pi = new PurchaseableItem();
			pi.setItemKey(Util.getWorkshopSchedule(String.valueOf(ci.getId()))
					.getId());
			pi.setUnitPrice(ci.getPrice());
			pi.setQty(ci.getQty());
			items.add(pi);
		}
		List<Key> keys = new ArrayList<>();
		List<Entity> ents = new ArrayList<>();
		for (PurchaseableItem ppi : items) {
			Entity e = Util.purchaseableItemToEntity(ppi);
			ents.add(e);
			keys.add(e.getKey());
		}
		new PurchaseableItemController().create(ents);
		ph.setItems(keys);
		phs.add(ph);
		return phs;

	}

	private UserGeneralInfo addWorkshopToEnrolledWokshops(Cart c,
			UserGeneralInfo ugi, User user) {

		Set<CartItem> cis = c.getCartItems();
		Set<Key> workshopKeys = new HashSet<>();
		for (CartItem ci : cis) {
			WorkshopTemplate wt = Util.getWorkshopTemplateFromScheduleId(
					Util.getWorkshopTemplateFromCache(),
					String.valueOf(ci.getId()));
			Key key = KeyFactory.createKey(wt.getWorkshopId(), "WorkShop",
					ci.getId());
			workshopKeys.add(key);
		}

		Set<Key> enrolled = null;
		if (ugi != null) {
			enrolled = ugi.getEnrolledWorkshops();
			if (enrolled == null) {
				enrolled = new HashSet<Key>();
			}
			enrolled.addAll(workshopKeys);

		}

		ugi.setEnrolledWorkshops(enrolled);
		return ugi;

	}
}
