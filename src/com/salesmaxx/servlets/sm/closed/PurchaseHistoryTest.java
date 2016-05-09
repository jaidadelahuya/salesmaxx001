package com.salesmaxx.servlets.sm.closed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.salesmaxx.beans.CartItem;
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

public class PurchaseHistoryTest extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4840485583939142622L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = null;
		HttpSession session = req.getSession();
		Object o = null;
		Cart c = null;
		synchronized (session) {
			user = (User) session.getAttribute("user");
			o = session.getAttribute("cart");
		}
		if (o != null) {
			c = (Cart) o;
		}
		InterswitchResponse ir = new InterswitchResponse();
		ir.setAmount(String.valueOf(c.getSubTotal()));
		ir.setResponseCode("00");
		ir.setResponseDescriptor("approved");
		Date d = new Date();
		ir.setTransactionDate(d.toString());
		ir.setTxnRef((String) session.getAttribute("txnRef"));

		synchronized (session) {
			session.setAttribute("interswitch", ir);
		}

		if (ir.getResponseCode().equalsIgnoreCase("00")) {

			Long id = user.getGeneralInfoId();
			UserGeneralInfoController cont = new UserGeneralInfoController();
			UserGeneralInfo ugi = cont.findUserGeneralInfo(user, id);
			ugi = addWorkshopToEnrolledWokshops(c, ugi, user);
			List<PurchaseHistory> phs = getPurchaseHistory(c, ugi, ir);
			UserGeneralInfoController c1 = new UserGeneralInfoController();
			c1.edit(ugi, user.getRegId(), phs);

			String body = ClosedUtil.getEmailBody(session);
			try {
				Util.sendEmail(Util.SERVICE_ACCOUNT, user.getUsername(),
						"Your Transaction Details", body);

			} catch (AddressException e) {
				resp.getWriter().write("address exception");
				return;
			} catch (MessagingException e) {

				resp.getWriter().write(e.getClass().getSimpleName());
				resp.getWriter().write(e.getCause().getLocalizedMessage());
				return;
			}

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
			//enrolled = ugi.getEnrolledWorkshops();
			if (enrolled == null) {
				enrolled = new HashSet<Key>();
			}
			enrolled.addAll(workshopKeys);

		}

		//ugi.setEnrolledWorkshops(enrolled);
		return ugi;

	}

}
