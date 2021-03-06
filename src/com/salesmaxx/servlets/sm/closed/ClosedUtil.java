package com.salesmaxx.servlets.sm.closed;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.salesmaxx.beans.CartItem;
import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.beans.MyWorkshopsBean;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.entities.ProductPaidFor;
import com.salesmaxx.entities.PurchaseableItem;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.persistence.controllers.PurchaseableItemController;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.util.InterswitchResponse;
import com.salesmaxx.util.Util;

public class ClosedUtil {

	private static final String INTERSWITCH_MAC = "B6123AC90C87147A852F97AD992F63FB53B33A07269183A6935AB61B9B7E867DB7EDA6F4F5EF2A9488DE77AAFD56BEA65AEA08F7FF0EB42CDB7E609ECB9D8493";

	public static final String INTERSWITCH_PRODUCT_ID = "6112";

	private static final String INTERSWITCH_PAY_ITEM_ID = "101";

	public static String getTransactionRef(User u) {
		// TODO Auto-generated method stub
		Date d = new Date();
		Long l = d.getTime();
		String code = u.getFirstName().substring(0, 2) + l
				+ u.getLastName().substring(0, 2);
		return code.toUpperCase();
	}

	public static String getInterswitchHash(String txnRef, String amount,
			String redirectUrl) {
		String str = txnRef + INTERSWITCH_PRODUCT_ID + INTERSWITCH_PAY_ITEM_ID
				+ amount + redirectUrl + INTERSWITCH_MAC;

		
		return Util.toSHA512(str).toUpperCase();
	}

	public static String getInterswitchHash(String txnRef) {
		String str = INTERSWITCH_PRODUCT_ID + txnRef + INTERSWITCH_MAC;

		return Util.toSHA512(str).toUpperCase();
	}

	public static String getEmailBody(HttpSession session) {
		Cart c = (Cart) session.getAttribute("cart");
		Set<CartItem> ci = c.getCartItems();
		InterswitchResponse ir = (InterswitchResponse) session
				.getAttribute("interswitch");
		User u = (User) session.getAttribute("user");
		String pre = "<html><link rel='stylesheet' href='https://salesmaxx001.appspot.com/style/bootstrap.min.css'>"
				+ "<head><style type='text/css'>.col-centered {float: none;margin: 0 auto;overflow: auto;}</style></head>"
				+ "<body><div class='container'><div class='row'><div class='col-sm-12' style='text-align: center;'>"
				+ "<img alt='SalesMaxx' class='img img-responsive'src='https://salesmaxx001.appspot.com/images/salesmaxx-logo.jpg' />"
				+ "</div></div><div class='row'><div class='col-sm-12'><strong>Hello "
				+ u.getFirstName()
				+ ",</strong></div></div>"
				+ "<div class='row'><div class='col-sm-12' style='text-align: center;'>"
				+ "<h3 class='text-info'>Your Have Completed Your Transaction Successfully</h3>"
				+ "<h4 style='color: color: #777'>Thank you for choosing <strong style='color: #a94442'>SalesMaxx</strong></h4></div></div>"
				+ "<div class='row'><div class='col-xs-12 col-sm-10 col-md-8 alert alert-info col-centered'><div class='col-sm-12'>"
				+ "<strong>TRANSACTION REFERENCE: </strong> <strong style='color: #a94442'>"
				+ ir.getTxnRef() + "</strong></div>";

		String end = "</div></div><div class='row'><div class='col-sm-12'>"
				+ "<p>Follow us on <a>Twitter</a></p>"
				+ "<p>Become a fan on <a>FaceBook</a></p>"
				+ "<p>Connect with us on <a>LinkedIn</a></p>"
				+ "<p>Hang out with us on <a>Google Plus</a></p></div>"
				+ "</div><div class='row'><div class='col-sm-12'>"
				+ "<p>Regards,</p><p>SalesMaxx Team.</p></div>"
				+ "</div></div></body></html>";

		for (CartItem s : ci) {
			pre += "<div class='col-sm-12'><strong>WORKSHOP NAME: </strong>"
					+ s.getName()
					+ "</div>"
					+ "<div class='col-sm-6'><strong>Workshop Code: </strong>"
					+ s.getWorkshopCode()
					+ "</div>"
					+ "<div class='col-sm-6'><strong>Workshop Date: </strong>"
					+ s.getDate()
					+ "</div>"
					+ "<div class='col-sm-6'><strong>Time: </strong>9am - 5pm</div>"
					+ "<div class='col-sm-6'><strong>Location: </strong>"
					+ s.getLocation()
					+ "</div>"
					+ "<div class='col-sm-6'><strong>Credit Hours: </strong>8hrs</div>"
					+ "<div class='col-sm-6'><strong>No. of Delegates: </strong>"
					+ s.getQty() + "</div>";

		}
		pre += end;

		return pre;
	}

	public static List<ProductPaidFor> toProductsPaidFor(Set<CartItem> cartItems) {
		List<ProductPaidFor> ppfs = new ArrayList<>();

		for (CartItem ci : cartItems) {
			ProductPaidFor ppf = new ProductPaidFor();
			ppf.setId(ci.getWorkshopCode());
			ppf.setQty(ci.getQty());
			ppfs.add(ppf);
		}
		return ppfs;
	}

	public static MyWorkshopsBean createWorkshopBean(
			List<Key> enrolledWorkshops,
			List<Key> completedWorkshops, Key cart) {
		MyWorkshopsBean mwb = new MyWorkshopsBean();
		PurchaseableItemController pc = new PurchaseableItemController();
		if (enrolledWorkshops != null) {
			List<PurchaseableItem> ew = pc.findAll(enrolledWorkshops);
			List<Key> keys = new ArrayList<>();
			for (PurchaseableItem pi : ew) {
				Key k = pi.getItemKey();
				if(!keys.contains(k)) {
					keys.add(k);
				}
				
			}
			Map<Key, Long> map = new HashMap<>();
			for (PurchaseableItem pi : ew) {
				Key k = pi.getItemKey();
				long qty = pi.getQty();
				for(Key kk : keys) {
					if(kk.equals(k)) {
						Set<Key> kkk = map.keySet();
						if(kkk.contains(kk)) {
							map.put(kk, map.get(kk)+qty);
						}else {
							map.put(kk, qty);
						}
					}
				}
			}
			List<ScheduleWorkshopDisplay> list = Util.toScheduleWorkshopDisplay(map);
			mwb.setEnrolled(list);
		}

		if (completedWorkshops != null) {
			List<PurchaseableItem> ew = pc.findAll(enrolledWorkshops);
			List<Key> keys = new ArrayList<>();
			for (PurchaseableItem pi : ew) {
				keys.add(pi.getItemKey());
			}
			mwb.setCompleted(Util.toScheduleWorkshopDisplay(Util
					.getScheduledWorkshops(keys)));
		}

		if (cart != null) {
			CartController cont = new CartController();
			Cart c = cont.findCart(cart);
			List<EmbeddedEntity> list = c.getItems();
			List<WorkShop> temps = new ArrayList<>();
			for (EmbeddedEntity ee : list) {
				WorkShop w = Util.getWorkshopSchedule(String.valueOf(ee
						.getProperty("workshopID")));
				temps.add(w);
			}
			mwb.setWish(Util.toScheduleWorkshopDisplay(temps));
		}
		return mwb;
	}

	public static ChequeInvoice manualTransactionToChequeInvoice(
			ManualTransaction mt) {
		ChequeInvoice cq = new ChequeInvoice();
		SimpleDateFormat sm = new SimpleDateFormat("E, dd MMM yyyy");
		cq.setDate(sm.format(mt.getIssueDate()));
		Calendar date = Calendar.getInstance();
		date.setTime(mt.getIssueDate());
		date.add(Calendar.DAY_OF_MONTH, 5);
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			date.add(Calendar.DAY_OF_MONTH, 2);
		} else if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			date.add(Calendar.DAY_OF_MONTH, 1);
		}
		cq.setDueDate(sm.format(date.getTime()));
		User u = new UserController().findUser(mt.getOwnerKey());
		if (u != null) {
			cq.setName(u.getFirstName() + " " + u.getLastName());
			if (u.getUsername() != null) {
				cq.setEmail(u.getUsername());
			} else {
				if (u.getEmails() != null && u.getEmails().isEmpty()) {
					for (String s : u.getEmails()) {
						cq.setEmail(s);
						break;
					}
				}
			}

		}

		cq.setItems(Util.getCartItems(mt.getItems()));
		double subTotal = 0;
		for (CartItem ci : cq.getItems()) {
			subTotal += (ci.getPrice() * ci.getQty());
		}
		cq.setSubTotal(Util.formatPrice(subTotal));
		cq.setTxnRef(mt.getTxnRef());

		return cq;
	}
}
