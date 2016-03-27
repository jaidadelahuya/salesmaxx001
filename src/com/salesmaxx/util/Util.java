package com.salesmaxx.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.salesmaxx.beans.CartItem;
import com.salesmaxx.beans.CategoryDisplay;
import com.salesmaxx.beans.ChequeInvoice;
import com.salesmaxx.beans.ChequePaymentBean;
import com.salesmaxx.beans.CoachingPost;
import com.salesmaxx.beans.CommentBean;
import com.salesmaxx.beans.DiscussionPageBean;
import com.salesmaxx.beans.FacebookAccessTokenResponse;
import com.salesmaxx.beans.GoogleDiscoveryDocument;
import com.salesmaxx.beans.LinkedInAccessTokenResponse;
import com.salesmaxx.beans.ManualPaymentBean;
import com.salesmaxx.beans.OtherCategoriesDisplay;
import com.salesmaxx.beans.PendingWorkshopBean;
import com.salesmaxx.beans.ProfileBean;
import com.salesmaxx.beans.PurchaseHistoryBean;
import com.salesmaxx.beans.ScheduleWorkshopDisplay;
import com.salesmaxx.beans.SearchBean;
import com.salesmaxx.beans.SignUp;
import com.salesmaxx.beans.SingleDiscussionPageBean;
import com.salesmaxx.beans.SocialUser;
import com.salesmaxx.beans.SocialUser.SocialNetwork;
import com.salesmaxx.beans.WorkshopDisplay;
import com.salesmaxx.beans.WorkshopSideBarItem;
import com.salesmaxx.entities.Address;
import com.salesmaxx.entities.Alumnus;
import com.salesmaxx.entities.Article;
import com.salesmaxx.entities.Cart;
import com.salesmaxx.entities.Category;
import com.salesmaxx.entities.Comment;
import com.salesmaxx.entities.ContactForm;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.Event;
import com.salesmaxx.entities.Facilitator;
import com.salesmaxx.entities.Industry;
import com.salesmaxx.entities.InterswitchTransactionLog;
import com.salesmaxx.entities.JobRole;
import com.salesmaxx.entities.MailingList;
import com.salesmaxx.entities.ManualTransaction;
import com.salesmaxx.entities.ProductPaidFor;
import com.salesmaxx.entities.PurchaseHistory;
import com.salesmaxx.entities.PurchaseableItem;
import com.salesmaxx.entities.Review;
import com.salesmaxx.entities.SalesMarketingTemplate;
import com.salesmaxx.entities.SalesMarketingTemplateCategory;
import com.salesmaxx.entities.SalesTemplateFormat;
import com.salesmaxx.entities.SalesmaxxCreditHistory;
import com.salesmaxx.entities.SkillLevel;
import com.salesmaxx.entities.Tag;
import com.salesmaxx.entities.Testimonial;
import com.salesmaxx.entities.User;
import com.salesmaxx.entities.UserGeneralInfo;
import com.salesmaxx.entities.UserRole;
import com.salesmaxx.entities.Video;
import com.salesmaxx.entities.WhitePaper;
import com.salesmaxx.entities.WorkShop;
import com.salesmaxx.entities.WorkshopTemplate;
import com.salesmaxx.persistence.controllers.AddressController;
import com.salesmaxx.persistence.controllers.CartController;
import com.salesmaxx.persistence.controllers.CategoryController;
import com.salesmaxx.persistence.controllers.CommentController;
import com.salesmaxx.persistence.controllers.DiscussionController;
import com.salesmaxx.persistence.controllers.EMF;
import com.salesmaxx.persistence.controllers.IndustryController;
import com.salesmaxx.persistence.controllers.JobRoleController;
import com.salesmaxx.persistence.controllers.ManualTransactionController;
import com.salesmaxx.persistence.controllers.PurchaseHistoryController;
import com.salesmaxx.persistence.controllers.PurchaseableItemController;
import com.salesmaxx.persistence.controllers.ReviewController;
import com.salesmaxx.persistence.controllers.TestimonialController;
import com.salesmaxx.persistence.controllers.UserController;
import com.salesmaxx.persistence.controllers.UserGeneralInfoController;
import com.salesmaxx.persistence.controllers.WorkshopController;
import com.salesmaxx.persistence.controllers.WorkshopTemplateController;
import com.salesmaxx.util.json.JSONObject;
import com.salesmaxx.util.json.JSONTokener;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;

public class Util {

	public static final long totalNumberOfSeats = 25;
	public static final String SERVICE_ACCOUNT = "profiliant.salesmaxx@gmail.com";
	public static final MemcacheService WORKSHOP_CACHE = MemcacheServiceFactory
			.getMemcacheService("workshops");
	public static final MemcacheService USER_CACHE = MemcacheServiceFactory
			.getMemcacheService("users");
	public static final MemcacheService DISCUSSION_CACHE = MemcacheServiceFactory
			.getMemcacheService("discussions");
	public static final MemcacheService TestimonialCache = MemcacheServiceFactory
			.getMemcacheService("latest-testimonials");
	public static final double NEW_ACCOUNT_CREDITS = 150;

	public static final String TWILIO_TOKEN = "9c53ae718c376fd272ef424d1dc3032b";
	public static final String TWILIO_SID = "AC0c9c2afac2a4b9ea3880e6a9d5fdff3c";

	public static String generateRandomCode(int minVal, int maxVal) {
		Random ran = new Random();
		return new Integer(minVal + ran.nextInt(maxVal)).toString();
	}

	public static void sendSMS(String to, String msg)
			throws TwilioRestException {
		TwilioRestClient client = new TwilioRestClient(TWILIO_SID, TWILIO_TOKEN);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("Body", msg));
		params.add(new BasicNameValuePair("To", "+2347051212230"));
		params.add(new BasicNameValuePair("From", "+14696082006"));

		MessageFactory messageFactory = client.getAccount().getMessageFactory();
		com.twilio.sdk.resource.instance.Message message = null;

		message = messageFactory.create(params);

		System.out.println(message.getSid());
	}

	@SuppressWarnings("unchecked")
	public static User toUser(Entity e) {
		User user = new User();
		user.setPassword((String) e.getProperty("password"));
		user.setAlumnus((Boolean) e.getProperty("alumnus"));
		user.setGender((String) e.getProperty("gender"));
		user.setGeneralInfoId((Long) e.getProperty("generalInfoId"));
		user.setHeadline((String) e.getProperty("headline"));
		user.setFirstName((String) e.getProperty("firstName"));
		user.setLastName((String) e.getProperty("lastName"));
		user.setPicture((BlobKey) e.getProperty("picture"));
		user.setRegId((Key) e.getKey());
		user.setCart((Key) e.getProperty("cart"));
		user.setLinkedInId((String) e.getProperty("linkedInId"));
		user.setFacebookId((String) e.getProperty("facebookId"));
		user.setGoogleId((String) e.getProperty("twitterId"));
		user.setTwitterId((String) e.getProperty("googleId"));
		List<String> ems = (List<String>) e.getProperty("emails");
		user.setPrimaryPhone((String) e.getProperty("primaryPhone"));
		user.setPhoneVerified((Boolean) e.getProperty("phoneVerified"));
		Set<String> emails = new HashSet<>();
		if (ems != null) {
			emails.addAll(ems);
		}
		user.setEmails(emails);
		String role = (String) e.getProperty("role");
		UserRole[] roles = UserRole.values();
		for (UserRole r : roles) {
			if (r.name().equalsIgnoreCase(role)) {
				user.setRole(r);
				break;
			}
		}
		user.setSalesmaxxCredit((Double) e.getProperty("salesmaxxCredit"));
		user.setUsername((String) e.getProperty("username"));
		user.setUsers((List<User>) e.getProperty("users"));
		user.setCart((Key) e.getProperty("cart"));
		return user;
	}

	public static boolean notNull(String... args) {
		for (String s : args) {
			if (s == null || s.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public static boolean sendEmail(String from, String to, String title,
			String body) throws AddressException, MessagingException {

		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(Util.SERVICE_ACCOUNT,
					"SalesMaxx Admin", "UTF-8"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(title);
			msg.setText(body);
			msg.setContent(body, "text/html");
			Transport.send(msg);

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();

		}
		return true;

	}

	public static Entity UserToEntity(User user) {
		Entity ent = new Entity(user.getRegId());
		ent.setIndexedProperty("username", user.getUsername());
		ent.setIndexedProperty("password", user.getPassword());
		ent.setIndexedProperty("firstName", user.getFirstName());
		ent.setIndexedProperty("alumnus", user.isAlumnus());
		ent.setIndexedProperty("lastName", user.getLastName());
		ent.setUnindexedProperty("users", user.getUsers());
		ent.setUnindexedProperty("salesmaxxCredit", user.getSalesmaxxCredit());
		ent.setUnindexedProperty("headline", user.getHeadline());
		ent.setUnindexedProperty("picture", user.getPicture());
		ent.setIndexedProperty("linkedInId", user.getLinkedInId());
		ent.setIndexedProperty("facebookId", user.getFacebookId());
		ent.setIndexedProperty("googleId", user.getGoogleId());
		ent.setIndexedProperty("twitterId", user.getTwitterId());
		ent.setIndexedProperty("emails", user.getEmails());
		ent.setIndexedProperty("primaryPhone", user.getPrimaryPhone());
		ent.setUnindexedProperty("phoneVerified", user.isPhoneVerified());

		if (user.getRole() != null) {
			ent.setUnindexedProperty("role", user.getRole().name());
		}
		ent.setUnindexedProperty("gender", user.getGender());
		ent.setUnindexedProperty("generalInfoId", user.getGeneralInfoId());
		ent.setUnindexedProperty("cart", user.getCart());
		return ent;
	}

	public static String getImageUrl(BlobKey key) {
		if (key == null) {
			return "/images/unknown-user.jpg";
		} else {
			ServingUrlOptions suo = ServingUrlOptions.Builder.withBlobKey(key);
			ImagesService is = ImagesServiceFactory.getImagesService();
			return is.getServingUrl(suo);
		}

	}

	public static Entity UserGeneralInfoToEntity(
			UserGeneralInfo userGeneralInfo, Key userKey) {
		Entity ent = new Entity(UserGeneralInfo.class.getSimpleName(),
				userGeneralInfo.getId(), userKey);
		ent.setIndexedProperty("dateOfBirth", userGeneralInfo.getDateOfBirth());
		ent.setUnindexedProperty("notification",
				userGeneralInfo.getNotification());
		ent.setUnindexedProperty("purchaseHistory",
				userGeneralInfo.getPurchaseHistory());
		ent.setUnindexedProperty("completedWorkshops",
				userGeneralInfo.getCompletedWorkshops());
		ent.setUnindexedProperty("enrolledWorkshops",
				userGeneralInfo.getEnrolledWorkshops());
		ent.setUnindexedProperty("wishListEvent",
				userGeneralInfo.getWishListEvent());
		ent.setUnindexedProperty("attendedEvents",
				userGeneralInfo.getAttendedEvents());
		ent.setUnindexedProperty("enrolledEvents",
				userGeneralInfo.getEnrolledEvents());
		ent.setUnindexedProperty("address", userGeneralInfo.getAddress());
		ent.setUnindexedProperty("phones", userGeneralInfo.getPhones());
		ent.setUnindexedProperty("website", userGeneralInfo.getWebsite());
		ent.setUnindexedProperty("socialMedia",
				userGeneralInfo.getSocialMedia());
		ent.setUnindexedProperty("cancelWorkshop",
				userGeneralInfo.isCancelWorkshop());
		ent.setUnindexedProperty("SIG", userGeneralInfo.getSIG());
		ent.setUnindexedProperty("certificate",
				userGeneralInfo.getCertificate());
		ent.setUnindexedProperty("salesmaxxHistoryCredits",
				userGeneralInfo.getSalesmaxxHistoryCredits());
		ent.setUnindexedProperty("biography", userGeneralInfo.getBiography());
		ent.setUnindexedProperty("pendingOrder",
				userGeneralInfo.getPendingOrder());
		ent.setUnindexedProperty("completedManualOrder",
				userGeneralInfo.getCompletedManualOrder());
		return ent;
	}

	public static Date WebtoDate(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static Date WebtoDate1(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return formatter.parse(date);
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static List<String> stringToList(String str, String regex) {
		String[] arr = str.split(regex);
		return Arrays.asList(arr);
	}

	public static Address createAddress(String street, String city,
			String state, String country) {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		KeyRange range = ds.allocateIds(Address.class.getSimpleName(), 1);
		Key ky = range.getStart();
		Address ad = new Address(ky, street, city, state, country);
		return ad;
	}

	public static Entity eventToEntity(Event event) {
		Entity ent = new Entity(event.getEventId());
		ent.setIndexedProperty("startDate", event.getStartDate());
		ent.setIndexedProperty("endDate", event.getEndDate());
		ent.setIndexedProperty("venue", event.getVenue());
		ent.setIndexedProperty("location", event.getLocation());
		ent.setUnindexedProperty("template", event.getTemplate());
		ent.setUnindexedProperty("noEnrolled", event.getNoEnrolled());
		ent.setUnindexedProperty("flyer", event.getFlyer());
		ent.setIndexedProperty("time", event.getTime());

		return ent;
	}

	public static Entity addressToEntity(Address address, Key key) {
		Entity ent = null;
		if (address.getId() != null) {
			ent = new Entity("Address", address.getId().getId(), key);
		} else {
			KeyRange range = EMF.getDs().allocateIds("Address", 1);
			Key k = range.getStart();
			ent = new Entity("Address", k.getId(), key);
		}
		ent.setIndexedProperty("state", address.getState());
		ent.setIndexedProperty("country", address.getCountry());
		ent.setUnindexedProperty("city", address.getCity());
		ent.setUnindexedProperty("street", address.getStreet());

		return ent;
	}

	public static boolean notNullArray(String[] arr) {
		if (arr != null && arr.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static Entity WorkshopTemplateToEntity(
			WorkshopTemplate workshopTemplate) {
		Entity ent = new Entity(workshopTemplate.getWorkshopId());
		ent.setIndexedProperty("workshopName",
				workshopTemplate.getWorkshopName());
		ent.setIndexedProperty("priority", workshopTemplate.getPriority());
		ent.setIndexedProperty("gender", workshopTemplate.getGender());
		ent.setIndexedProperty("skillLevel", workshopTemplate.getSkillLevel());
		ent.setUnindexedProperty("price", workshopTemplate.getPrice());
		ent.setUnindexedProperty("workshopImage",
				workshopTemplate.getWorkshopImage());
		ent.setUnindexedProperty("noEnrolled", workshopTemplate.getNoEnrolled());
		ent.setUnindexedProperty("shortDescription",
				workshopTemplate.getShortDescription());
		ent.setUnindexedProperty("downloadables",
				workshopTemplate.getDownloadables());
		ent.setUnindexedProperty("relatedWorkshops",
				workshopTemplate.getRelatedWorkshops());
		ent.setUnindexedProperty("reviews", workshopTemplate.getReviews());
		ent.setUnindexedProperty("assessmentQuestions",
				workshopTemplate.getAssessmentQuestions());
		ent.setUnindexedProperty("salesmaxxCredits",
				workshopTemplate.getSalesmaxxCredits());
		ent.setUnindexedProperty("industries", workshopTemplate.getIndustries());
		ent.setUnindexedProperty("professions",
				workshopTemplate.getProfessions());
		ent.setUnindexedProperty("learningOutcomes",
				workshopTemplate.getLearningOutcomes());
		ent.setUnindexedProperty("format", workshopTemplate.getFormat());
		ent.setUnindexedProperty("audiences", workshopTemplate.getAudiences());
		ent.setUnindexedProperty("courseContent",
				workshopTemplate.getCourseContent());
		ent.setUnindexedProperty("catalogueLink",
				workshopTemplate.getCatalogueLink());
		ent.setUnindexedProperty("schedules", workshopTemplate.getSchedules());
		return ent;
	}

	public static Entity ArticleToEntity(Article article) {
		Entity ent = new Entity("Article", article.getId());
		ent.setUnindexedProperty("name", article.getName());
		ent.setUnindexedProperty("link", article.getLink());
		return ent;
	}

	public static Entity VideoToEntity(Video video) {
		Entity ent = new Entity("Video", video.getId());
		ent.setUnindexedProperty("name", video.getName());
		ent.setUnindexedProperty("link", video.getLink());
		return ent;
	}

	public static Entity WhitePaperToEntity(WhitePaper whitePaper) {
		Entity ent = new Entity("WhitePaper", whitePaper.getId());
		ent.setUnindexedProperty("name", whitePaper.getName());
		ent.setUnindexedProperty("link", whitePaper.getLink());
		return ent;
	}

	public static String getWorkshopId(String wsn) {

		if (wsn != null) {
			if (wsn.equalsIgnoreCase("Coaching Sales People For Performance")) {
				return "SM-CSP-202";
			} else if (wsn.equalsIgnoreCase("Consultative Selling")) {
				return "RM-CS-201";
			} else if (wsn
					.equalsIgnoreCase("Developing A Winning Sales Presentation")) {
				return "PS-DWS-101";
			} else if (wsn
					.equalsIgnoreCase("Developing & Implementing Winning Strategic Account Plans")) {
				return "TEAM-DIW-202";
			} else if (wsn
					.equalsIgnoreCase("Interviewing, Hiring & Onboarding Top Sales Talent")) {
				return "SM-IHT-203";
			} else if (wsn.equalsIgnoreCase("Indirect Channel Sales Bootcamp")) {
				return "CDS-ICS-101";
			} else if (wsn.equalsIgnoreCase("Sales Compensation Bootcamp")) {
				return "SM-SCB-101";
			} else if (wsn.equalsIgnoreCase("Sales Promotion Bootcamp")) {
				return "CDS-SPB-102";
			} else if (wsn
					.equalsIgnoreCase("Negotiating Beyond Price & Tactical Approaches")) {
				return "NG-NPT-201";
			} else if (wsn.equalsIgnoreCase("Presenting To Senior Executives")) {
				return "PS-PSE-201";
			} else if (wsn.equalsIgnoreCase("Professional Selling Bootcamp")) {
				return "PSS-PSB-101";
			} else if (wsn.equalsIgnoreCase("Prospecting Bootcamp")) {
				return "PSS-PB-104";
			} else if (wsn.equalsIgnoreCase("Relationship Marketing")) {
				return "RM-RM-101";
			} else if (wsn.equalsIgnoreCase("Retail Selling")) {
				return "CDS-RS-103";
			} else if (wsn.equalsIgnoreCase("Sales-Call Management")) {
				return "PSS-SCM-103";
			} else if (wsn.equalsIgnoreCase("Sales Pipeline Management")) {
				return "SM-SPM-201";
			} else if (wsn
					.equalsIgnoreCase("Sales Pipeline Management Bootcamp")) {
				return "PSS-SPM-102";
			} else if (wsn.equalsIgnoreCase("Sales Opportunity Management")) {
				return "TEAM-SOM-203";
			} else if (wsn.equalsIgnoreCase("Selling to Senior Executives")) {
				return "RM-SSE-201";
			} else if (wsn.equalsIgnoreCase("Situational Sales Negotiation")) {
				return "NG-SSN-101";
			} else if (wsn.equalsIgnoreCase("Solution Selling Workshop")) {
				return "TTS-SSW-201";
			} else if (wsn.equalsIgnoreCase("Strategic Account Management")) {
				return "TEAM-SAM-201";
			} else if (wsn
					.equalsIgnoreCase("Technology Sales Training Workshop")) {
				return "TTS-TST-102";
			} else if (wsn.equalsIgnoreCase("The Telesales Bootcamp")) {
				return "TTS-TB-101";
			} else if (wsn.equalsIgnoreCase("Trust Selling")) {
				return "RM-TS-101";
			}
		} else {
			return null;
		}
		return null;
	}

	public static String getCatalogueLink(String name) {
		name = name.toLowerCase().trim();
		String[] bkn = name.split(" ");
		String link = "";
		for (String s : bkn) {
			link += s;
		}
		link = "/catalogue/" + link + ".pdf";
		return link;
	}

	public static Entity facilitatorToEntity(Facilitator facilitator) {
		Entity ent = new Entity(Facilitator.class.getSimpleName());
		ent.setIndexedProperty("firstName", facilitator.getFirstName());
		ent.setIndexedProperty("lastName", facilitator.getLastName());
		ent.setUnindexedProperty("education", facilitator.getEducation());
		ent.setUnindexedProperty("certification",
				facilitator.getCertification());
		ent.setUnindexedProperty("picture", facilitator.getPicture());
		ent.setUnindexedProperty("recentDevelopment",
				facilitator.getRecentDevelopment());
		ent.setUnindexedProperty("specialization",
				facilitator.getSpecialization());
		ent.setUnindexedProperty("workExperience",
				facilitator.getWorkExperience());
		ent.setUnindexedProperty("profile", facilitator.getProfile());
		return ent;
	}

	@SuppressWarnings("unchecked")
	public static WorkshopTemplate entityToWorkshopTemplate(Entity result) {
		WorkshopTemplate wst = new WorkshopTemplate(result.getKey().getName());
		wst.setAssessmentQuestions((List<Long>) result
				.getProperty("assessmentQuestions"));
		wst.setAudiences((List<String>) result.getProperty("audiences"));
		wst.setCourseContent((List<String>) result.getProperty("courseContent"));
		wst.setDownloadables((List<Key>) result.getProperty("downloadables"));
		wst.setFormat((String) result.getProperty("format"));
		wst.setGender((String) result.getProperty("gender"));
		wst.setIndustries((List<String>) result.getProperty("industries"));
		wst.setLearningOutcomes((List<String>) result
				.getProperty("learningOutcomes"));
		wst.setNoEnrolled((Long) result.getProperty("noEnrolled"));
		wst.setPrice((double) result.getProperty("price"));
		wst.setPriority((String) result.getProperty("priority"));
		wst.setProfessions((List<String>) result.getProperty("professions"));
		wst.setRelatedWorkshops((List<Key>) result
				.getProperty("relatedWorkshops"));
		wst.setReviews((List<Long>) result.getProperty("reviews"));
		wst.setSalesmaxxCredits((double) result.getProperty("salesmaxxCredits"));
		wst.setShortDescription((Text) result.getProperty("shortDescription"));
		wst.setSkillLevel((List<String>) result.getProperty("skillLevel"));
		wst.setWorkshopImage((BlobKey) result.getProperty("workshopImage"));
		wst.setWorkshopName((String) result.getProperty("workshopName"));
		wst.setCatalogueLink((String) result.getProperty("catalogueLink"));
		wst.setSchedules((List<Key>) result.getProperty("schedules"));
		return wst;
	}

	public static Set<CategoryDisplay> getCategoryDisplays(
			List<WorkshopTemplate> workshops) {

		Set<CategoryDisplay> cds = new HashSet<CategoryDisplay>();
		List<Category> categories = getCategoriesFromCache();
		Collections.shuffle(categories);
		for (Category c : categories) {
			CategoryDisplay cd = new CategoryDisplay();
			cd.setCatalogueLink(c.getCatalogueLink());
			cd.setCategoryId(c.getCategoryId());
			cd.setCategoryName(c.getName().getName());
			cd.setImageUrl(Util.getImageUrl(c.getImage()));
			List<WorkshopTemplate> list = new ArrayList<WorkshopTemplate>();
			for (String s : c.getWorkshops()) {
				WorkshopTemplate wt = getWorkshopTemplateById(s, workshops);
				if (wt != null) {
					list.add(wt);
				}
			}
			List<WorkshopDisplay> wds = toWorkshopDisplay(list);
			cd.setWorkshops(wds);
			cds.add(cd);
		}

		return cds;

	}

	@SuppressWarnings("unchecked")
	public static DiscussionPageBean getDiscussionFromCache(String category) {

		String cat = category.replace(" ", "");
		Object o = DISCUSSION_CACHE.get(cat);
		DiscussionPageBean dpb = new DiscussionPageBean();
		List<SingleDiscussionPageBean> beans = null;
		if (o == null) {
			DiscussionController cont = new DiscussionController();
			List<Discussion> discussion = cont
					.findDiscussionsByCategory(category);
			beans = Util.discussionToSDPB(discussion);
			dpb.setCategory(category);
			dpb.setBeans(beans);
			DISCUSSION_CACHE.put(cat, dpb);
		} else {
			dpb = (DiscussionPageBean) o;
		}

		return dpb;
	}

	@SuppressWarnings("unchecked")
	public static List<Testimonial> getTestimonialFromCache(String category) {

		Object o = TestimonialCache.get("latest-testimonials");
		List<Testimonial> li = null;
		if (o == null) {
			TestimonialController cont = new TestimonialController();
			li = cont.findTestimonialByLatestDate(5);
			DISCUSSION_CACHE.put("latest-testimonials", li);
		} else {
			li = (List<Testimonial>) o;
		}

		return li;
	}

	@SuppressWarnings("unchecked")
	private static List<Category> getCategoriesFromCache() {
		List<Category> categories = null;
		Object o = WORKSHOP_CACHE.get("categories");
		if (o == null) {
			CategoryController cont = new CategoryController();
			categories = cont.getAllCategories();
			WORKSHOP_CACHE.put("categories", categories);
		} else {
			categories = (List<Category>) o;
		}
		return categories;
	}

	public static List<OtherCategoriesDisplay> getOtherCategories(
			String categoryName) {

		List<OtherCategoriesDisplay> ocds = new ArrayList<OtherCategoriesDisplay>();
		List<Category> cats = getCategoriesFromCache();
		for (Category c : cats) {
			if (!(c.getName().getName().equalsIgnoreCase(categoryName))) {
				OtherCategoriesDisplay ocd = new OtherCategoriesDisplay();
				ocd.setId(c.getCategoryId());
				ocd.setImage(Util.getImageUrl(c.getImage()));
				ocd.setName(c.getName().getName());
				ocd.setNoOfWorkshops(String.valueOf(c.getWorkshops().size()));
				ocds.add(ocd);
			}
		}
		Collections.shuffle(ocds);
		return ocds;
	}

	public static List<WorkshopDisplay> toWorkshopDisplay(
			List<WorkshopTemplate> list) {
		List<WorkshopDisplay> wsds = new ArrayList<WorkshopDisplay>();

		for (WorkshopTemplate wt : list) {
			WorkshopDisplay wd = new WorkshopDisplay();
			wd.setDescription(wt.getShortDescription().getValue());
			wd.setId(wt.getWorkshopId().getName());
			wd.setImageUrl(Util.getImageUrl(wt.getWorkshopImage()));
			wd.setName(wt.getWorkshopName());
			List<Review> reviews = getReviews(wt.getReviews());
			if (reviews == null) {
				wd.setNoReviews(0);
			} else {
				wd.setNoReviews(reviews.size());
			}
			wd.setRating(getAvgRating(reviews));
			wd.setPriority(getPriority(wt.getPriority()));
			wsds.add(wd);
		}
		Collections.sort(wsds);
		return wsds;
	}

	private static int getPriority(String priority) {
		if (priority != null) {
			if (priority.equalsIgnoreCase("first five")) {
				return 5;
			} else if (priority.equalsIgnoreCase("second five")) {
				return 4;
			} else if (priority.equalsIgnoreCase("third five")) {
				return 3;
			} else if (priority.equalsIgnoreCase("other")) {
				return 2;
			} else {
				return 1;
			}
		} else {
			return 0;
		}
	}

	public static double getAvgRating(List<Review> reviews) {
		if (reviews == null || reviews.size() == 0) {
			return 0;
		} else {
			int rating = 0;
			for (Review r : reviews) {
				rating += r.getNoOfStars();
			}
			return rating / reviews.size();
		}

	}

	public static List<Review> getReviews(List<Long> reviews) {
		if (reviews == null) {
			return null;
		} else {
			List<Review> rws = new ArrayList<>();
			for (Long l : reviews) {
				ReviewController con = new ReviewController();
				Review r = con.findReview(l);
				if (r != null) {
					rws.add(r);
				}
			}
			return rws;
		}

	}

	private static WorkshopTemplate getWorkshopTemplateById(String s,
			List<WorkshopTemplate> workshops) {
		for (WorkshopTemplate wt : workshops) {
			if (wt.getWorkshopId().getName().equalsIgnoreCase(s)) {
				return wt;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static List<WorkshopTemplate> getWorkshopTemplateFromCache() {
		List<WorkshopTemplate> workshops = null;
		Object o = WORKSHOP_CACHE.get("workshops");
		if (o == null) {
			WorkshopTemplateController cont = new WorkshopTemplateController();
			workshops = cont.getAllWorkshops();
			WORKSHOP_CACHE.put("workshops", workshops);
		} else {
			workshops = (List<WorkshopTemplate>) o;
			if (workshops.isEmpty()) {
				WorkshopTemplateController cont = new WorkshopTemplateController();
				workshops = cont.getAllWorkshops();
				WORKSHOP_CACHE.put("workshops", workshops);
			}
		}
		return workshops;
	}

	public static WorkshopTemplate getWorkshopFromList(String id,
			List<WorkshopTemplate> workshops) {
		for (WorkshopTemplate w : workshops) {
			if (w.getWorkshopId().getName().equalsIgnoreCase(id)) {
				return w;
			}
		}
		return null;
	}

	public static List<WorkShop> getScheduledWorkshops(List<Key> list) {
		List<WorkShop> workshops = new ArrayList<>();
		List<WorkShop> allSchedules = getAllWorkshopSchedulesFromCache();

		if (list != null) {
			for (Key k : list) {
				for (WorkShop w : allSchedules) {
					if (w.getId().equals(k)) {
						workshops.add(w);
					}
				}
			}
		}
		return workshops;
	}

	public static List<WorkShop> getAllWorkshopSchedulesFromCache() {
		List<WorkShop> workshops = null;
		Object o = WORKSHOP_CACHE.get("schedules");
		if (o == null) {
			WorkshopController cont = new WorkshopController();
			workshops = cont.getAllWorkshops();
			WORKSHOP_CACHE.put("schedules", workshops);
		} else {
			workshops = (List<WorkShop>) o;
		}
		return workshops;
	}

	public static Category getWorkshopCategory(String id) {
		List<Category> categories = getCategoriesFromCache();
		for (Category c : categories) {
			for (String s : c.getWorkshops()) {
				if (s.equalsIgnoreCase(id)) {
					return c;
				}
			}
		}
		return null;
	}

	public static Facilitator entityToFacilitator(Entity result) {
		Facilitator fac = new Facilitator();
		fac.setCertification((List<String>) result.getProperty("certification"));
		fac.setEducation((List<String>) result.getProperty("education"));
		fac.setFirstName((String) result.getProperty("firstName"));
		fac.setId(result.getKey().getId());
		fac.setLastName((String) result.getProperty("lastName"));
		fac.setPicture((BlobKey) result.getProperty("picture"));
		fac.setProfile((Text) result.getProperty("profile"));
		fac.setRecentDevelopment((List<String>) result
				.getProperty("recentDevelopment"));
		fac.setSpecialization((List<String>) result
				.getProperty("specialization"));
		fac.setWorkExperience((List<String>) result
				.getProperty("workExperience"));
		return fac;
	}

	public static void initWorkshopLayout(HttpSession session,
			HttpServletResponse resp) {
		MemcacheService cache = Util.WORKSHOP_CACHE;
		List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();

		// process for industries
		Object o = null;
		List<Industry> industries = null;
		o = cache.get("industries");
		if (o == null) {
			IndustryController c1 = new IndustryController();
			industries = c1.getAllIndustries();
			cache.put("industries", industries);
		} else {
			industries = (List<Industry>) o;
			if (industries.size() == 0) {
				IndustryController c1 = new IndustryController();
				industries = c1.getAllIndustries();
				cache.put("industries", industries);
			}
		}

		List<WorkshopSideBarItem> industryItems = new ArrayList<WorkshopSideBarItem>();
		for (Industry ind : industries) {
			int counter = 0;
			for (int i = 0; i < workshops.size(); i++) {
				WorkshopTemplate wt = workshops.get(i);

				for (String str : wt.getIndustries()) {
					if (str.equalsIgnoreCase(ind.getName().getName())) {
						counter++;
						break;
					}
				}

			}
			String url = resp
					.encodeRedirectURL("&#47sm&#47open&#47get-industry-workshops?industry="
							+ ind.getName().getName().toLowerCase());
			WorkshopSideBarItem wsi = new WorkshopSideBarItem(ind.getName()
					.getName(), url, counter);
			industryItems.add(wsi);
		}

		// process for job roles
		List<JobRole> jobRoles = null;
		o = cache.get("jobRoles");
		if (o == null) {
			JobRoleController c2 = new JobRoleController();
			jobRoles = c2.getAllJobRoles();
			cache.put("jobRoles", jobRoles);
		} else {
			jobRoles = (List<JobRole>) o;
		}

		List<WorkshopSideBarItem> jobRoleItems = new ArrayList<WorkshopSideBarItem>();
		for (JobRole jr : jobRoles) {
			int counter = 0;
			for (int i = 0; i < workshops.size(); i++) {
				WorkshopTemplate wt = workshops.get(i);
				for (String str : wt.getProfessions()) {
					if (str.equalsIgnoreCase(jr.getName().getName())) {
						counter++;
						break;
					}
				}
			}
			String url = resp
					.encodeRedirectURL("&#47sm&#47open&#47get-job-role-workshops?job-role="
							+ jr.getName().getName().toLowerCase());
			WorkshopSideBarItem wsi = new WorkshopSideBarItem(jr.getName()
					.getName(), url, counter);
			jobRoleItems.add(wsi);
		}

		// Process for experience
		SkillLevel[] exps = SkillLevel.values();
		List<WorkshopSideBarItem> experienceItems = new ArrayList<WorkshopSideBarItem>();
		for (SkillLevel ex : exps) {
			int counter = 0;
			for (int i = 0; i < workshops.size(); i++) {
				WorkshopTemplate wt = workshops.get(i);
				for (String str : wt.getSkillLevel()) {
					if (str.equalsIgnoreCase(ex.name())) {
						counter++;
						break;
					}
				}
			}
			String url = resp
					.encodeRedirectURL("&#47sm&#47open&#47get-workshops-by-experience?experience="
							+ ex.name().toLowerCase());
			WorkshopSideBarItem wsi = new WorkshopSideBarItem(ex.name(), url,
					counter);
			experienceItems.add(wsi);
		}

		synchronized (session) {
			session.setAttribute("industryItems", industryItems);
			session.setAttribute("jobRoleItems", jobRoleItems);
			session.setAttribute("experienceItems", experienceItems);
		}

	}

	public static Entity workshopToEntity(WorkShop workShop, Key key) {

		Entity ent = new Entity(WorkShop.class.getSimpleName(), workShop
				.getId().getId(), key);
		ent.setUnindexedProperty("facilitators", workShop.getFacilitators());
		ent.setIndexedProperty("startDate", workShop.getStartDate());
		ent.setIndexedProperty("endDate", workShop.getEndDate());
		ent.setUnindexedProperty("forSale", workShop.isForSale());
		ent.setUnindexedProperty("flyer", workShop.getFlyer());
		ent.setUnindexedProperty("location", workShop.getLocation());
		ent.setUnindexedProperty("noEnrolled", workShop.getNoEnrolled());
		ent.setUnindexedProperty("workshopType", workShop.getWorkshopType());
		ent.setUnindexedProperty("venue", workShop.getVenue());
		ent.setUnindexedProperty("students", workShop.getStudents());

		return ent;
	}

	public static List<WorkshopTemplate> getWorkshopByIndustry(String industry) {
		List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (WorkshopTemplate wt : workshops) {
			for (String s : wt.getIndustries()) {
				if (s.equalsIgnoreCase(industry.trim())) {
					temps.add(wt);
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByIndustry(
			String... industries) {
		List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (String industry : industries) {
			for (WorkshopTemplate wt : workshops) {
				for (String s : wt.getIndustries()) {
					if (s.equalsIgnoreCase(industry.trim())) {
						temps.add(wt);
					}
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByIndustry(String industry,
			List<WorkshopTemplate> workshops) {
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (WorkshopTemplate wt : workshops) {
			for (String s : wt.getIndustries()) {
				if (s.equalsIgnoreCase(industry.trim())) {
					temps.add(wt);
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByExperience(
			String experience) {
		List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (WorkshopTemplate wt : workshops) {
			for (String s : wt.getSkillLevel()) {
				if (s.equalsIgnoreCase(experience.trim())) {
					temps.add(wt);
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByExperience(
			String experience, List<WorkshopTemplate> workshops) {
		if (workshops == null) {
			workshops = getWorkshopTemplateFromCache();
		}
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (WorkshopTemplate wt : workshops) {
			for (String s : wt.getSkillLevel()) {
				if (s.equalsIgnoreCase(experience.trim())) {
					temps.add(wt);
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByExperience(
			List<WorkshopTemplate> workshops, String... experiences) {
		if (workshops == null) {
			workshops = getWorkshopTemplateFromCache();
		}
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (String experience : experiences) {
			for (WorkshopTemplate wt : workshops) {
				for (String s : wt.getSkillLevel()) {
					if (s.equalsIgnoreCase(experience.trim())) {
						temps.add(wt);
					}
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByJobRole(String role) {
		List<WorkshopTemplate> workshops = Util.getWorkshopTemplateFromCache();
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (WorkshopTemplate wt : workshops) {
			for (String s : wt.getProfessions()) {
				if (s.equalsIgnoreCase(role.trim())) {
					temps.add(wt);
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByJobRole(String role,
			List<WorkshopTemplate> workshops) {
		if (workshops == null) {
			workshops = getWorkshopTemplateFromCache();
		}
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (WorkshopTemplate wt : workshops) {
			for (String s : wt.getProfessions()) {
				if (s.equalsIgnoreCase(role.trim())) {
					temps.add(wt);
				}
			}
		}
		return temps;
	}

	public static List<WorkshopTemplate> getWorkshopByJobRole(
			List<WorkshopTemplate> workshops, String... roles) {
		if (workshops == null) {
			workshops = getWorkshopTemplateFromCache();
		}
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (String role : roles) {
			for (WorkshopTemplate wt : workshops) {
				for (String s : wt.getProfessions()) {
					if (s.equalsIgnoreCase(role.trim())) {
						temps.add(wt);
					}
				}
			}
		}
		return temps;
	}

	@SuppressWarnings("unchecked")
	public static WorkShop entityToWorkshop(Entity result) {
		WorkShop ws = new WorkShop();
		ws.setEndDate((Date) result.getProperty("endDate"));
		ws.setFacilitators((List<String>) result.getProperty("facilitators"));
		ws.setFlyer((BlobKey) result.getProperty("flyer"));
		ws.setForSale((boolean) result.getProperty("forSale"));
		ws.setId(result.getKey());
		ws.setLocation((Key) result.getProperty("location"));
		ws.setNoEnrolled((Long) result.getProperty("noEnrolled"));
		ws.setStartDate((Date) result.getProperty("startDate"));
		ws.setWorkshopType((String) result.getProperty("workshopType"));
		ws.setVenue((String) result.getProperty("venue"));
		ws.setStudents((List<Key>) result.getProperty("students"));
		return ws;
	}

	public static List<ScheduleWorkshopDisplay> toScheduleWorkshopDisplay(
			List<WorkShop> ws) {
		List<ScheduleWorkshopDisplay> list = new ArrayList<>();

		for (WorkShop w : ws) {
			ScheduleWorkshopDisplay s = toScheduleWorkshopDisplay(w);
			list.add(s);
		}
		return list;
	}

	public static List<ScheduleWorkshopDisplay> toScheduleWorkshopDisplay(
			List<WorkShop> ws, List<PurchaseableItem> pis) {
		List<ScheduleWorkshopDisplay> list = new ArrayList<>();

		for (WorkShop w : ws) {
			PurchaseableItem pi = null;
			for (PurchaseableItem p : pis) {
				if (w.getId().equals(p.getItemKey())) {
					pi = p;
				}
			}
			ScheduleWorkshopDisplay s = toScheduleWorkshopDisplay(w, pi);
			list.add(s);
		}
		return list;
	}

	public static ScheduleWorkshopDisplay toScheduleWorkshopDisplay(WorkShop w,
			PurchaseableItem p) {

		ScheduleWorkshopDisplay s = new ScheduleWorkshopDisplay();
		s.setQty(p.getQty());
		AddressController c = new AddressController();
		Address a = c.findAddress(w.getLocation());
		s.setId(String.valueOf(w.getId().getId()));
		s.setLocation(a);
		s.setFormattedTotalPrice(new DecimalFormat("#,###.00").format(p
				.getUnitPrice() * p.getQty()));
		s.setFormattedPrice(new DecimalFormat("#,###.00").format(p
				.getUnitPrice()));
		WorkshopTemplate wst = getWorkshopTemplateFromScheduleId(
				getWorkshopTemplateFromCache(),
				String.valueOf(w.getId().getId()));
		if (wst != null) {
			s.setImageUrl(wst.getImageUrl());
			s.setDescription(wst.getShortDescription().getValue());
			s.setWorkshopCode(wst.getWorkshopId().getName());
		}
		// s.setFlyer(Util.getImageUrl(w.getFlyer()));
		s.setFormat(w.getWorkshopType());
		s.setType((w.isForSale()) ? "FREE" : "PAID");
		s.setVenue(w.getVenue());
		s.setName(getWorkshopTemplateName(w.getId()));
		SimpleDateFormat sm = new SimpleDateFormat("E, dd MMM yyyy");
		s.setStartDate(sm.format(w.getStartDate()));
		return s;
	}

	public static ScheduleWorkshopDisplay toScheduleWorkshopDisplay(WorkShop w) {

		ScheduleWorkshopDisplay s = new ScheduleWorkshopDisplay();
		AddressController c = new AddressController();
		Address a = c.findAddress(w.getLocation());
		s.setId(String.valueOf(w.getId().getId()));
		s.setLocation(a);
		s.setSeatsLeft(25 - w.getNoEnrolled());
		WorkshopTemplate wst = getWorkshopTemplateFromScheduleId(
				getWorkshopTemplateFromCache(),
				String.valueOf(w.getId().getId()));
		if (wst != null) {
			s.setCatalogueLink(wst.getCatalogueLink());
			s.setImageUrl(wst.getImageUrl());
			s.setDescription(wst.getShortDescription().getValue());
			s.setWorkshopCode(wst.getWorkshopId().getName());
		}
		// s.setFlyer(Util.getImageUrl(w.getFlyer()));
		s.setFormat(w.getWorkshopType());
		s.setType((w.isForSale()) ? "FREE" : "PAID");
		s.setVenue(w.getVenue());
		s.setName(getWorkshopTemplateName(w.getId()));
		SimpleDateFormat sm = new SimpleDateFormat("E, dd MMM yyyy");
		s.setStartDate(sm.format(w.getStartDate()));
		return s;
	}

	private static String getWorkshopTemplateName(Key id) {
		List<WorkshopTemplate> list = getWorkshopTemplateFromCache();
		for (WorkshopTemplate wt : list) {
			if (wt.getSchedules() != null) {
				for (Key k : wt.getSchedules()) {
					if (k.equals(id)) {
						return wt.getWorkshopName();
					}
				}
			}
		}
		return null;
	}

	public static List<WorkShop> findWorkshopByLocation(String location,
			List<WorkShop> workshops) {
		List<WorkShop> list = new ArrayList<>();

		for (WorkShop w : workshops) {
			Address a = new AddressController().findAddress(w.getLocation());
			if (a.getState().equalsIgnoreCase(location)) {
				list.add(w);
			}
		}
		return list;
	}

	public static List<WorkShop> findWorkshopByLocation(
			List<WorkShop> workshops, String... locations) {
		List<WorkShop> list = new ArrayList<>();
		for (String location : locations) {
			for (WorkShop w : workshops) {
				Address a = new AddressController()
						.findAddress(w.getLocation());
				if (a.getState().equalsIgnoreCase(location)) {
					list.add(w);
				}
			}
		}
		return list;
	}

	public static WorkshopTemplate getWorkshopTemplateFromScheduleId(
			List<WorkshopTemplate> workshops, String id) {
		for (WorkshopTemplate w : workshops) {
			if (w.getSchedules() != null) {
				for (Key k : w.getSchedules()) {
					if (k.getId() == Long.parseLong(id)) {
						return w;
					}
				}
			}

		}
		return null;
	}

	public static void yearOnly(String year, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		boolean ok2 = Util.notNull(year);
		if (ok2) {
			List<WorkShop> workshops = Util.getAllWorkshopSchedulesFromCache();
			List<WorkShop> temps = new ArrayList<>();

			for (WorkShop w : workshops) {

				Calendar c = new GregorianCalendar();
				c.setTime(w.getStartDate());

				int yr = c.get(Calendar.YEAR);
				int y = Integer.parseInt(year);

				if (y == yr) {
					temps.add(w);
				}
			}

			List<ScheduleWorkshopDisplay> swds = Util
					.toScheduleWorkshopDisplay(temps);
			HttpSession session = req.getSession();
			Util.initWorkshopLayout(session, resp);

			synchronized (session) {
				session.setAttribute("workshopSchedules", swds);
				session.setAttribute("Date", year);
				session.setAttribute("searchFor", year);
			}

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
			rd.include(req, resp);
		}

	}

	public static void both(String month, String year, HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		boolean ok1 = Util.notNull(month);
		boolean ok2 = Util.notNull(year);

		if (ok1 && ok2) {
			List<WorkShop> workshops = Util.getAllWorkshopSchedulesFromCache();
			List<WorkShop> temps = new ArrayList<>();
			for (WorkShop w : workshops) {

				Calendar c = new GregorianCalendar();
				c.setTime(w.getStartDate());
				int mm = c.get(Calendar.MONTH);
				int yr = c.get(Calendar.YEAR);

				int m = Integer.parseInt(month);
				int y = Integer.parseInt(year);

				if (m == mm && y == yr) {
					temps.add(w);
				}
			}

			List<ScheduleWorkshopDisplay> swds = Util
					.toScheduleWorkshopDisplay(temps);
			HttpSession session = req.getSession();
			Util.initWorkshopLayout(session, resp);

			synchronized (session) {
				session.setAttribute("workshopSchedules", swds);
				session.setAttribute("Date", month + ", " + year);
				session.setAttribute(
						"searchFor",
						new DateFormatSymbols().getMonths()[Integer
								.parseInt(month)] + ", " + year);
			}

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
			rd.include(req, resp);
		}

	}

	public static WorkShop getWorkshopSchedule(String id) {
		List<WorkShop> ws = getAllWorkshopSchedulesFromCache();

		for (WorkShop w : ws) {
			if (w.getId().getId() == Long.parseLong(id)) {
				return w;
			}
		}
		return null;
	}

	public static boolean usernameExist(String lowerCase) {
		List<User> users = Util.getUsersFromCache();
		return false;
	}

	private static List<User> getUsersFromCache() {
		List<User> users = null;
		Object o = USER_CACHE.get("users");
		if (o == null) {
			UserController cont = new UserController();
			users = null;
			WORKSHOP_CACHE.put("users", users);
		} else {
			users = (List<User>) o;
		}
		return users;
	}

	public static String toSHA256(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

		} catch (NoSuchAlgorithmException e) { // TODO Auto-generated catch
												// block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String toSHA512(String str) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());

			byte byteData[] = md.digest();

			// convert the byte to hex format method 1

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
						.substring(1));
			}

		} catch (NoSuchAlgorithmException e) { // TODO Auto-generated catch
												// block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static User signUpToUser(SignUp su) {
		User u = new User(su.getFirstName(), su.getLastName());
		u.setUsername(su.getUsername());
		u.setPassword(su.getPassword());
		u.setPrimaryPhone(su.getPhone());
		return u;
	}

	public static String newRegCode(String firstName2, String lastName2) {
		String code = Util.generateRandomCode(100000, 999999);
		code = firstName2.substring(0, 2) + code + lastName2.substring(0, 2);
		return code.toUpperCase();
	}

	public static SignUp toSignUpUser(User user) {
		SignUp su = new SignUp();
		su.setFirstName(user.getFirstName());
		su.setLastName(user.getLastName());
		su.setPassword(user.getPassword());
		su.setUsername(user.getUsername());
		su.setPhone(user.getPrimaryPhone());
		return su;
	}

	public static void sendConfirmationCodeEmail(String email, String body)
			throws AddressException, MessagingException {
		String title = "Your SalesMaxx Confirmation Code";
		String to = email.toLowerCase();
		Util.sendEmail(Util.SERVICE_ACCOUNT, to, title, body);
		// Util.sendEmailNotification( to, title, body);
	}

	public static String getConfirmationCodeEmailBody(String code, String name) {
		if (name == null) {
			name = "";
		}
		return "<body><div style='width: 40%; margin: 0 auto'>"
				+ "<img alt='SalesMaxx' src='http://www.salesmaxx.com/images/salesmaxx-logo.jpg'/>"
				+ "</div><div><h4 style='padding-bottom: 3%;'>Hello "
				+ name
				+ ",</h4>"
				+ "<h3 style='color:#d9534f'>SalesMaxx Confirmation Code</h3><p>Your new account is almost ready.</p>"
				+ "<p>Before you can login you have to confirm your email by entering this code: <strong style='color:#d9534f'>"
				+ code + "</strong></p>"
				+ "<p>Regards,</p><p>SalesMaxx Team</p></div></body>";
	}

	public static String getPasswordRecoveryEmailBody(String code, String name) {
		if (name == null) {
			name = "";
		}
		return "<body><div style='width: 40%; margin: 0 auto'>"
				+ "<img alt='SalesMaxx' src='http://www.salesmaxx.com/images/salesmaxx-logo.jpg'/>"
				+ "</div><div><h4 style='padding-bottom: 3%;'>Hello "
				+ name
				+ ",</h4>"
				+ "<h3 style='color:#d9534f'>SalesMaxx Confirmation Code</h3><p>Your have requested to change your password.</p>"
				+ "<p>Before you can continue, you have to confirm your email by entering this code: <strong style='color:#d9534f'>"
				+ code + "</strong></p>"
				+ "<p>Regards,</p><p>SalesMaxx Team</p></div></body>";
	}

	public static List<WorkShop> getAllScheduleWorkshopFromTemplate(
			List<WorkshopTemplate> temps) {
		List<WorkShop> list = null;
		if (temps == null) {
			list = getAllWorkshopSchedulesFromCache();
			return list;
		} else {
			list = new ArrayList<>();
			for (WorkshopTemplate wt : temps) {
				List<Key> keys = wt.getSchedules();
				list.addAll(Util.getScheduledWorkshops(keys));
			}
		}
		return list;
	}

	public static List<WorkShop> getFreeWorkshops(List<WorkShop> list) {
		List<WorkShop> l = new ArrayList<>();

		for (WorkShop w : list) {
			if (!w.isForSale()) {
				l.add(w);
			}
		}
		return l;
	}

	public static List<WorkShop> getPaidWorkshops(List<WorkShop> list) {
		List<WorkShop> l = new ArrayList<>();

		for (WorkShop w : list) {
			if (w.isForSale()) {
				l.add(w);
			}
		}
		return l;
	}

	public static List<WorkShop> getInstructorLedWorkshops(List<WorkShop> list) {
		List<WorkShop> l = new ArrayList<>();

		for (WorkShop w : list) {
			if (w.getWorkshopType().equalsIgnoreCase("Instructor Led")) {
				l.add(w);
			}
		}
		return l;
	}

	public static List<WorkShop> getOnlineWorkshops(List<WorkShop> list) {
		List<WorkShop> l = new ArrayList<>();

		for (WorkShop w : list) {
			if (w.getWorkshopType().equalsIgnoreCase("On-Demand")) {
				l.add(w);
			}
		}
		return l;
	}

	public static Set<CartItem> getCartItems(List<EmbeddedEntity> items) {
		Set<CartItem> ci = new HashSet<CartItem>();
		if (items != null) {
			for (EmbeddedEntity ee : items) {
				WorkShop w = Util.getWorkshopSchedule(String.valueOf(ee
						.getProperty("workshopID")));
				if (w != null) {
					CartItem c = toCartItem(w,
							Integer.parseInt((String) ee.getProperty("qty")));
					ci.add(c);
				}

			}
		} else {
			return null;
		}

		return ci;
	}

	public static CartItem toCartItem(WorkShop w, int qty) {
		List<WorkshopTemplate> temps = Util.getWorkshopTemplateFromCache();
		WorkshopTemplate wt = Util.getWorkshopTemplateFromScheduleId(temps,
				String.valueOf(w.getId().getId()));
		CartItem c = new CartItem();
		c.setDate(new SimpleDateFormat("dd-MM-yyyy").format(w.getStartDate()));
		c.setId(w.getId().getId());

		c.setImageUrl(wt.getImageUrl());
		c.setItemType(CartItem.ItemType.WORKSHOP);
		Address a = Util.toScheduleWorkshopDisplay(w).getLocation();
		c.setLocation(a.getState() + "," + a.getCountry());
		c.setName(wt.getWorkshopName());
		c.setPrice(wt.getPrice());
		c.setQty(qty);
		c.setWorkshopCode(wt.getWorkshopId().getName());

		// c.setName();
		return c;
	}

	@SuppressWarnings("unchecked")
	public static Cart toCart(Entity e) {

		Cart c = new Cart();
		c.setCartKey(e.getKey());
		Object o = e.getProperty("items");
		System.out.println(o);
		if (o == null) {
			c.setItems(new ArrayList<EmbeddedEntity>());
		} else {
			c.setItems((List<EmbeddedEntity>) o);
		}

		return c;
	}

	public static Entity cartToEntity(Cart c) {
		Entity e = null;
		if (c.getCartKey() == null) {
			e = new Entity("cart");
		} else {
			e = new Entity(c.getCartKey());
		}
		e.setUnindexedProperty("items", c.getItems());
		return e;
	}

	public static double getSubTotal(Set<CartItem> cartItems) {
		double d = 0;
		for (CartItem ci : cartItems) {
			d += (ci.getPrice() * ci.getQty());
		}
		return d;
	}

	public static String formatPrice(double price) {
		String pattern = "###,###.##";
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		String format = decimalFormat.format(price);
		return format;
	}

	public static InterswitchResponse parseInterswitchResponse(
			String respString, String txnRef) {
		respString = respString.replace("{", "").replace("}", "")
				.replace("\"", "");
		String[] str = respString.split(",");
		InterswitchResponse ir = new InterswitchResponse();
		for (String s : str) {
			String[] ss = s.split(":");
			if (ss[0].equalsIgnoreCase("amount")) {
				if (ss.length > 1) {
					ir.setAmount(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("CardNumber")) {
				if (ss.length > 1) {
					ir.setCardNumber(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("MerchantReference")) {
				if (ss.length > 1) {
					ir.setMerchantReference(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("PaymentReference")) {
				if (ss.length > 1) {
					ir.setPaymentReference(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("RetrievalReferenceNumber")) {
				if (ss.length > 1) {
					ir.setRetrievalRferenceNumber(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("LeadBankCbnCode")) {
				if (ss.length > 1) {
					ir.setLeadBankCbnCode(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("LeadBankName")) {
				if (ss.length > 1) {
					ir.setLeadBankName(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("SplitAccounts")) {
				if (ss.length > 1) {
					ir.setSplitAccounts(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("TransactionDate")) {
				if (ss.length > 1) {
					String[] sss = ss[1].split("T");
					ir.setTransactionDate(sss[0]);
				}

			} else if (ss[0].equalsIgnoreCase("ResponseCode")) {
				if (ss.length > 1) {
					ir.setResponseCode(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("ResponseDescription")) {
				if (ss.length > 1) {
					ir.setResponseDescriptor(ss[1]);
				}

			}

		}
		ir.setTxnRef(txnRef);
		return ir;
	}

	public static Entity productPaidForToEntity(ProductPaidFor pdt, Key k) {

		Entity e = new Entity(k);
		e.setUnindexedProperty("id", pdt.getId());
		e.setUnindexedProperty("qty", pdt.getQty());
		e.setUnindexedProperty("totalPrice", pdt.getTotalPrice());
		return e;
	}

	public static Entity interswitchTransactionLogToEntity(
			InterswitchTransactionLog interswitchTransactionLog) {

		Entity e = new Entity(InterswitchTransactionLog.class.getSimpleName(),
				interswitchTransactionLog.getTnxRef());
		e.setIndexedProperty("customerId",
				interswitchTransactionLog.getCustomerId());
		e.setIndexedProperty("tnxRef", interswitchTransactionLog.getTnxRef());
		e.setIndexedProperty("responseCode",
				interswitchTransactionLog.getResponseCode());
		e.setUnindexedProperty("amount", interswitchTransactionLog.getAmount());
		e.setUnindexedProperty("date", interswitchTransactionLog.getDate());
		e.setUnindexedProperty("productsPaidFor",
				interswitchTransactionLog.getProductsPaidFor());
		e.setUnindexedProperty("responseDescription",
				interswitchTransactionLog.getResponseDescription());
		e.setUnindexedProperty("apprAmount",
				interswitchTransactionLog.getApprAmount());
		return e;
	}

	@SuppressWarnings("unchecked")
	public static InterswitchTransactionLog toInterswitchTransactionLog(Entity e) {
		InterswitchTransactionLog itl = new InterswitchTransactionLog();
		itl.setAmount((String) e.getProperty("amount"));
		itl.setCustomerId((String) e.getProperty("customerId"));
		itl.setDate((String) e.getProperty("date"));
		itl.setApprAmount((String) e.getProperty("apprAmount"));
		Object o = e.getProperty("productsPaidFor");
		if (o != null) {
			List<Key> list = (List<Key>) o;
			Set<Key> set = new HashSet<>();
			set.addAll(list);
			itl.setProductsPaidFor(set);
		}
		itl.setResponseCode((String) e.getProperty("responseCode"));
		itl.setResponseDescription((String) e
				.getProperty("responseDescription"));
		itl.setTnxRef((String) e.getProperty("tnxRef"));
		return itl;
	}

	public static List<WorkshopTemplate> getWorkshopTemplates(
			List<Key> relatedWorkshops) {
		List<WorkshopTemplate> temps = new ArrayList<>();
		for (Key key : relatedWorkshops) {

			for (WorkshopTemplate wt : getWorkshopTemplateFromCache()) {
				System.out.println(key + " and " + wt.getWorkshopId());
				if (key.equals(wt.getWorkshopId())) {

					temps.add(wt);
					break;
				}
			}
		}
		Collections.shuffle(temps);
		return temps;
	}

	public static List<WorkshopTemplate> getCartRelatedWorkshops(
			Set<CartItem> cartItems) {
		Set<WorkshopTemplate> set = new HashSet<WorkshopTemplate>();
		List<WorkshopTemplate> allTemps = getWorkshopTemplateFromCache();
		for (CartItem ci : cartItems) {
			WorkshopTemplate temp = getWorkshopTemplateFromScheduleId(allTemps,
					String.valueOf(ci.getId()));
			set.addAll(getWorkshopTemplates(temp.getRelatedWorkshops()));

		}
		List<WorkshopTemplate> list = new ArrayList<WorkshopTemplate>();
		list.addAll(set);
		Collections.shuffle(list);
		return list;
	}

	public static String createEmailMessage(String name, String email,
			String phone, String msg) {
		String m = "<body><div><table><thead><tr><th>Parameter</th><th>Value</th></tr></thead><tbody><tr>"
				+ "<td><strong>Name</strong></td><td>"
				+ name
				+ "</td>"
				+ "</tr><tr><td><strong>Email</strong></td>"
				+ "<td>"
				+ email
				+ "</td></tr><tr><td><strong>Phone</strong></td>"
				+ "<td>"
				+ phone
				+ "</td></tr><tr><td><strong>Message</strong></td>"
				+ "<td>" + msg + "</td></tr></tbody></table></div></body>";
		return m;
	}

	public static Entity mailingListToEntity(MailingList ml) {
		Key key = KeyFactory.createKey(MailingList.class.getSimpleName(),
				ml.getEmail());
		Entity e = new Entity(key);
		return e;
	}

	public static MailingList entityToMailingList(Entity ent) {
		MailingList ml = new MailingList();
		ml.setEmail(ent.getKey().getName());
		return ml;
	}

	@SuppressWarnings("unchecked")
	public static UserGeneralInfo EntityToUserGeneralInfo(Entity e) {
		UserGeneralInfo ugi = new UserGeneralInfo();
		ugi.setAddress((Key) e.getProperty("address"));
		ugi.setAttendedEvents((Set<Long>) e.getProperty("attendedEvents"));
		if (e.getProperty("cancelWorkshop") == null) {
			ugi.setCancelWorkshop(false);
		} else {
			ugi.setCancelWorkshop((boolean) e.getProperty("cancelWorkshop"));
		}

		ugi.setCertificate((Set<BlobKey>) e.getProperty("certificate"));
		ugi.setCompletedWorkshops((Set<Key>) e
				.getProperty("completedWorkshops"));
		Set<Key> s = new HashSet<>();
		Object o = e.getProperty("enrolledWorkshops");
		if (o != null) {
			List<Key> l = (List<Key>) o;
			s.addAll(l);
		}
		ugi.setEnrolledWorkshops(s);
		ugi.setCompletedManualOrder((List<Key>) e
				.getProperty("completedManualOrder"));
		ugi.setPendingOrder((List<Key>) e.getProperty("pendingOrder"));
		ugi.setDateOfBirth((Date) e.getProperty("dateOfBirth"));
		ugi.setEnrolledEvents((Set<Long>) e.getProperty("enrolledEvents"));
		ugi.setId(e.getKey().getId());
		ugi.setNotification((Key) e.getProperty("notification"));
		ugi.setPhones((List<String>) e.getProperty("phones"));
		ugi.setPurchaseHistory((List<Key>) e.getProperty("purchaseHistory"));
		ugi.setSalesmaxxHistoryCredits((List<Key>) e
				.getProperty("salesmaxxHistoryCredits"));
		ugi.setSIG((List<Long>) e.getProperty("SIG"));
		ugi.setSocialMedia((List<EmbeddedEntity>) e.getProperty("socialMedia"));
		ugi.setWebsite((String) e.getProperty("website"));
		ugi.setWishListEvent((Set<Long>) e.getProperty("wishListEvent"));
		ugi.setBiography((Text) e.getProperty("biography"));
		return ugi;
	}

	public static ProfileBean createProfileBean(User user, UserGeneralInfo ugi) {
		ProfileBean pb = new ProfileBean();
		AddressController c = new AddressController();
		Address a = c.findAddress(ugi.getAddress());
		if (a != null) {
			pb.setAddress(a);
		}
		if (ugi.getDateOfBirth() != null) {
			pb.setDob(new SimpleDateFormat("dd-MMM").format(ugi
					.getDateOfBirth()));
		}
		pb.setPrimaryPhone(user.getPrimaryPhone());
		pb.setEmail(user.getUsername());
		pb.setFirstName(user.getFirstName());
		pb.setLastName(user.getLastName());
		pb.setRegId(user.getRegId().getName());
		pb.setSalesmaxxCredits(String.valueOf(user.getSalesmaxxCredit()));
		pb.setWebsite(ugi.getWebsite());
		pb.setHeadline(user.getHeadline());
		String phones = "";
		
		pb.setPhoneVerified(user.isPhoneVerified());
		pb.setPicture(user.getPictureUrl());
		if (ugi.getBiography() != null) {
			pb.setBiography(ugi.getBiography().getValue());
		}

		return pb;
	}

	public static Address entityToAddress(Entity e) {
		Address a = Util.createAddress((String) e.getProperty("street"),
				(String) e.getProperty("city"),
				(String) e.getProperty("state"),
				(String) e.getProperty("country"));

		return a;
	}

	public static String getPasswordChangedMessage(String firstName) {
		String msg = "<body><div style='width: 40%; margin: 0 auto'>"
				+ "<img alt='SalesMaxx' src='http://salesmax001.appspot.com/images/salesmaxx-logo.jpg'/>"
				+ "</div><div><h4 style='padding-bottom: 3%;'>Hello "
				+ firstName
				+ ",</h4>"
				+ "<h3>Password Successfully Changed</h3><p>Your new SalesMaxx password has been set.</p>"
				+ "<p>If you have any problems logging into salesmaxx.com, contact a site administrator.</p>"
				+ "<p>Regards</p><p>SalesMaxx Team</p></div></body>";
		return msg;
	}

	public static List<Entity> purchaseHistoryToEntity(List<PurchaseHistory> phs) {
		List<Entity> ents = new ArrayList<Entity>();
		Entity e = null;

		for (PurchaseHistory ph : phs) {
			e = new Entity(PurchaseHistory.class.getSimpleName(),
					ph.getTxnRef());
			e.setIndexedProperty("txnRef", ph.getTxnRef());
			e.setProperty("purchaseDate", ph.getPurchaseDate());
			e.setProperty("status", ph.getStatus());
			e.setUnindexedProperty("items", ph.getItems());
			e.setUnindexedProperty("total", ph.getTotal());
			e.setProperty("purchaseType", ph.getPurchaseType());
			ents.add(e);
		}

		return ents;
	}

	@SuppressWarnings("unchecked")
	public static PurchaseHistory entityToPurchaseHistory(Entity e) {
		PurchaseHistory ph = new PurchaseHistory();
		ph.setPurchaseDate((Date) e.getProperty("purchaseDate"));
		ph.setStatus((String) e.getProperty("status"));
		ph.setItems((List<Key>) e.getProperty("items"));
		ph.setTotal((Double) e.getProperty("total"));
		ph.setTxnRef((String) e.getProperty("txnRef"));
		ph.setPurchaseType((String) e.getProperty("purchaseType"));
		return ph;
	}

	public static Entity alunmusToEntity(Alumnus alumnus) {
		Entity e = new Entity(Alumnus.class.getSimpleName());
		e.setProperty("title", alumnus.getTitle());
		e.setProperty("firstName", alumnus.getFirstName());
		e.setProperty("lastName", alumnus.getLastName());
		e.setProperty("email", alumnus.getEmail());
		e.setProperty("altEmail", alumnus.getAltEmail());
		e.setProperty("phone1", alumnus.getPhone1());
		e.setProperty("phone2", alumnus.getPhone2());
		e.setProperty("company", alumnus.getCompany());
		e.setProperty("programsAttended", alumnus.getProgramsAttended());
		e.setProperty("birthDate", alumnus.getBirthDate());

		return e;
	}

	public static List<InterswitchTransactionLog> getListFromCursor(
			Cursor cursor) {
		List<InterswitchTransactionLog> list = new ArrayList<>();
		for (List<Object> obs : cursor.getSublist()) {
			for (Object o : obs) {
				if (o.getClass().getSimpleName()
						.equals("InterswitchTransactionLog")) {
					InterswitchTransactionLog itl = (InterswitchTransactionLog) o;
					list.add(itl);
				}
			}
		}
		return list;
	}

	public static void initWorkshopFinderPagination(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String val = req.getParameter("val");
		Object o = null;
		Object o1 = null;
		synchronized (session) {
			o = session.getAttribute("searchBeanX");
			o1 = session.getAttribute("workshopSchedules");
		}

		if (o != null) {
			SearchBean sb = (SearchBean) o;
			int pageStart = (Integer.parseInt(val) - 1) * 4;
			sb.setPageStart(pageStart);
			int pageEnd = pageStart + 3;

			Set list = (Set) o1;
			if (pageEnd < list.size()) {
				if (pageStart == 0) {
					sb.setPageEnd(3);
				} else {
					sb.setPageEnd(pageEnd);
				}

			} else {
				sb.setPageEnd(list.size() - 1);
			}
			sb.setCurrentDisplay(Integer.parseInt(val));
			synchronized (session) {
				session.setAttribute("searchBeanX", sb);
				session.setAttribute("searchBean", sb);
			}

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/find-workshop-by-prop.jsp");
			rd.include(req, resp);

		}

	}

	public static void initSearchPagePagination(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String val = req.getParameter("val");
		Object o = null;
		Object o1 = null;
		synchronized (session) {
			o = session.getAttribute("sb1");
			o1 = session.getAttribute("searchDocs");
		}

		if (o != null) {
			SearchBean sb = (SearchBean) o;
			int pageStart = (Integer.parseInt(val) - 1) * 5;
			sb.setPageStart(pageStart);
			int pageEnd = pageStart + 4;

			List l = (List) o1;
			Set list = new HashSet<>();
			list.addAll(l);
			if (pageEnd < list.size()) {
				if (pageStart == 0) {
					sb.setPageEnd(4);
				} else {
					sb.setPageEnd(pageEnd);
				}

			} else {
				sb.setPageEnd(list.size() - 1);
			}
			sb.setCurrentDisplay(Integer.parseInt(val));
			synchronized (session) {
				session.setAttribute("sb1", sb);
			}

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/sm/open/search-page.jsp");
			rd.include(req, resp);

		}

	}

	public static void initInterswitchLogPagination(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String val = req.getParameter("val");
		Object o = null;
		Object o1 = null;
		synchronized (session) {
			o = session.getAttribute("sb");
			o1 = session.getAttribute("interswitchTransactionLog");
		}

		if (o != null) {
			SearchBean sb = (SearchBean) o;
			int pageStart = (Integer.parseInt(val) - 1) * 10;
			sb.setPageStart(pageStart);
			int pageEnd = pageStart + 9;

			List l = (List) o1;
			Set list = new HashSet<>();
			list.addAll(l);
			if (pageEnd < list.size()) {
				if (pageStart == 0) {
					sb.setPageEnd(9);
				} else {
					sb.setPageEnd(pageEnd);
				}

			} else {
				sb.setPageEnd(list.size() - 1);
			}
			sb.setCurrentDisplay(Integer.parseInt(val));
			synchronized (session) {
				session.setAttribute("sb", sb);
			}

			RequestDispatcher rd = req
					.getRequestDispatcher("/WEB-INF/interswitch-transaction-log.jsp");
			rd.include(req, resp);

		}

	}

	public static Entity contactFormToEntity(ContactForm cf) {
		Entity e = new Entity("ContactForm");
		e.setUnindexedProperty("name", cf.getName());
		e.setUnindexedProperty("email", cf.getEmail());
		e.setUnindexedProperty("message", cf.getMessage());
		e.setUnindexedProperty("phone", cf.getPhone());
		return e;
	}

	public static ContactForm createContactForm(String name, String email,
			String phone, String msg) {
		ContactForm cf = new ContactForm();
		cf.setEmail(name);
		cf.setEmail(email);
		cf.setPhone(phone);
		cf.setMessage(msg);
		cf.setName(name);
		return cf;
	}

	public static double getPendingSalesMaxxCredits(Set<Key> enrolledWorkshops) {
		double d = 0;
		if (enrolledWorkshops != null) {
			List<Key> keys = new ArrayList<>();
			keys.addAll(enrolledWorkshops);
			List<ScheduleWorkshopDisplay> list = Util
					.toScheduleWorkshopDisplay(Util.getScheduledWorkshops(keys));
			for (ScheduleWorkshopDisplay swd : list) {
				WorkshopTemplate wt = Util.getWorkshopTemplateFromScheduleId(
						Util.getWorkshopTemplateFromCache(), swd.getId());
				d += wt.getSalesmaxxCredits();
			}
		}
		return d;
	}

	public static Entity purchaseableItemToEntity(
			PurchaseableItem purchaseableItem) {
		KeyRange range = EMF.getDs().allocateIds(
				PurchaseableItem.class.getSimpleName(), 1);
		Key key = range.getStart();
		Entity e = new Entity(key);
		e.setUnindexedProperty("itemKey", purchaseableItem.getItemKey());
		e.setUnindexedProperty("unitPrice", purchaseableItem.getUnitPrice());
		e.setUnindexedProperty("qty", purchaseableItem.getQty());
		return e;
	}

	public static List<PurchaseHistoryBean> getPurchaseHistoryBeans(
			List<PurchaseHistory> phs) {
		List<PurchaseHistoryBean> l = new ArrayList<>();
		if (phs != null) {
			for (PurchaseHistory ph : phs) {
				PurchaseHistoryBean phb = getPurchaseHistoryBean(ph);

				l.add(phb);
			}
		}

		return l;
	}

	public static List<PurchaseHistoryBean> manualTransactionToPurchaseHistoryBean(
			List<ManualTransaction> mts) {
		List<PurchaseHistoryBean> phbs = new ArrayList<>();
		if (mts == null) {
			return new ArrayList<PurchaseHistoryBean>();
		}
		for (ManualTransaction mt : mts) {
			PurchaseHistoryBean phb = new PurchaseHistoryBean();
			List<PurchaseableItem> items = new ArrayList<>();
			Set<CartItem> cis = Util.getCartItems(mt.getItems());
			double total = 0;
			if (cis == null) {
				return null;
			}
			for (CartItem ci : cis) {
				PurchaseableItem pi = new PurchaseableItem();
				pi.setItemKey(Util.getWorkshopSchedule(
						String.valueOf(ci.getId())).getId());
				pi.setUnitPrice(ci.getPrice());
				pi.setQty(ci.getQty());
				total += (ci.getPrice() * ci.getQty());
				items.add(pi);
			}
			phb.setFormattedDate(new SimpleDateFormat("dd-MMM-yyyy").format(
					mt.getIssueDate()).toUpperCase());
			phb.setFormattedTotalPrice(new DecimalFormat("#,###.00")
					.format(total));
			phb.setTxnRef(mt.getTxnRef());
			List<Key> kys = new ArrayList<>();
			for (PurchaseableItem p : items) {
				kys.add(p.getItemKey());
			}
			List<WorkShop> wks = Util.getScheduledWorkshops(kys);
			List<ScheduleWorkshopDisplay> swds = Util
					.toScheduleWorkshopDisplay(wks, items);
			phb.setList(swds);
			phbs.add(phb);
		}

		return phbs;
	}

	public static PurchaseHistory manualTransactionToPurchaseHistory(
			ManualTransaction mt) {
		PurchaseHistory ph = new PurchaseHistory();
		ph.setPurchaseDate(mt.getIssueDate());
		ph.setPurchaseType(mt.getTransactionType());
		ph.setStatus(mt.getStatus());
		ph.setTxnRef(mt.getTxnRef());
		List<PurchaseableItem> items = new ArrayList<>();
		Set<CartItem> cis = Util.getCartItems(mt.getItems());
		double total = 0;
		for (CartItem ci : cis) {
			PurchaseableItem pi = new PurchaseableItem();
			pi.setItemKey(Util.getWorkshopSchedule(String.valueOf(ci.getId()))
					.getId());
			pi.setUnitPrice(ci.getPrice());
			pi.setQty(ci.getQty());
			total += (ci.getPrice() * ci.getQty());
			items.add(pi);
		}
		ph.setTotal(total);
		List<Key> keys = new ArrayList<>();
		List<Entity> ents = new ArrayList<>();
		for (PurchaseableItem ppi : items) {
			Entity e = Util.purchaseableItemToEntity(ppi);
			ents.add(e);
			keys.add(e.getKey());
		}
		new PurchaseableItemController().create(ents);
		ph.setItems(keys);
		return ph;
	}

	public static PurchaseHistoryBean getPurchaseHistoryBean(PurchaseHistory ph) {
		if (ph == null) {
			return null;
		}
		PurchaseHistoryBean phb = new PurchaseHistoryBean();
		phb.setFormattedDate(ph.getFormattedDate());
		phb.setFormattedTotalPrice(ph.getFormattedAmount());
		phb.setTxnRef(ph.getTxnRef());
		phb.setFormattedUnitPrice(new DecimalFormat("#,###.00").format(ph
				.getTotal() / ph.getItems().size()));
		phb.setKey(KeyFactory.createKey(PurchaseHistory.class.getSimpleName(),
				ph.getTxnRef()));
		List<Key> keys = ph.getItems();
		PurchaseableItemController c = new PurchaseableItemController();
		List<PurchaseableItem> pis = c.findAll(keys);
		List<Key> kys = new ArrayList<>();
		for (PurchaseableItem p : pis) {
			kys.add(p.getItemKey());
		}
		List<WorkShop> wks = Util.getScheduledWorkshops(kys);
		List<ScheduleWorkshopDisplay> swds = Util.toScheduleWorkshopDisplay(
				wks, pis);
		phb.setList(swds);
		return phb;
	}

	public static List<PurchaseableItem> toPurchaseableItems(
			Map<Key, Entity> map) {
		Set<Key> keys = map.keySet();
		List<PurchaseableItem> l = new ArrayList<>();
		for (Key k : keys) {
			Entity e = map.get(k);
			PurchaseableItem p = Util.toPurchaseableItem(e);
			l.add(p);
		}
		return l;
	}

	private static PurchaseableItem toPurchaseableItem(Entity e) {
		PurchaseableItem p = new PurchaseableItem();
		p.setItemKey((Key) e.getProperty("itemKey"));
		p.setQty((Long) e.getProperty("qty"));
		p.setUnitPrice((Double) e.getProperty("unitPrice"));
		return p;
	}

	public static LinkedInAccessTokenResponse toLinkedInAccessToken(
			String respString) {
		respString = respString.replace("{", "").replace("}", "")
				.replace("\"", "");
		String[] str = respString.split(",");
		LinkedInAccessTokenResponse latr = new LinkedInAccessTokenResponse();
		for (String s : str) {
			String[] ss = s.split(":");
			if (ss[0].equalsIgnoreCase("access_token")) {
				if (ss.length > 1) {
					latr.setAccessToken(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("expires_in")) {
				if (ss.length > 1) {
					latr.setExpires(ss[1]);
				}

			}

		}
		return latr;
	}

	public static FacebookAccessTokenResponse toFacebookAccessTokenResponse(
			String respString) {
		respString = respString.replace("{", "").replace("}", "")
				.replace("\"", "");
		String[] str = respString.split(",");
		FacebookAccessTokenResponse fatr = new FacebookAccessTokenResponse();
		for (String s : str) {
			String[] ss = s.split(":");
			if (ss[0].equalsIgnoreCase("access_token")) {
				if (ss.length > 1) {
					fatr.setAccessToken(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("expires_in")) {
				if (ss.length > 1) {
					fatr.setExpires(ss[1]);
				}

			} else if (ss[0].equalsIgnoreCase("token_type")) {
				if (ss.length > 1) {
					fatr.setTokenType(ss[1]);
				}

			}

		}
		return fatr;
	}

	public static GoogleDiscoveryDocument toGoogleDiscoveryDocument(
			String respString) {
		GoogleDiscoveryDocument gdd = new GoogleDiscoveryDocument();
		respString = respString.replace("{", "").replace("}", "")
				.replace("\"", "");
		String[] str = respString.split(",");
		for (String s : str) {
			String[] ss = s.split(":");
			if (ss[0].trim().equalsIgnoreCase("authorization_endpoint")) {
				if (ss.length > 1) {
					gdd.setAuthorizationEndpoint(ss[1] + ":" + ss[2]);
				}

			} else if (ss[0].trim().equalsIgnoreCase("token_endpoint")) {
				if (ss.length > 1) {
					gdd.setTokenEndpoint(ss[1] + ":" + ss[2]);
				}

			} else if (ss[0].trim().equalsIgnoreCase("userinfo_endpoint")) {
				if (ss.length > 1) {
					gdd.setUserinfoEndpoint(ss[1] + ":" + ss[2]);
				}

			}

		}
		return gdd;
	}

	public static SocialUser toLinkedInSocialUser(String respString) {
		SocialUser su = new SocialUser();
		su.setNetwork(SocialNetwork.LINKEDIN);
		respString = respString.replace("{", "").replace("}", "")
				.replace("\"", "");
		String[] str = respString.split(",");
		for (String s : str) {
			String[] ss = s.split(":");
			if (ss[0].trim().equalsIgnoreCase("emailAddress")) {
				if (ss.length > 1) {
					su.setEmail(ss[1].trim());
				}

			} else if (ss[0].trim().equalsIgnoreCase("firstName")) {
				if (ss.length > 1) {
					su.setFirstName(ss[1]);
				}

			} else if (ss[0].trim().equalsIgnoreCase("headline")) {
				if (ss.length > 1) {
					su.setHeadline(ss[1]);
				}

			} else if (ss[0].trim().equalsIgnoreCase("id")) {
				if (ss.length > 1) {
					su.setId(ss[1].trim());
				}

			} else if (ss[0].trim().equalsIgnoreCase("lastName")) {
				if (ss.length > 1) {
					su.setLastName(ss[1]);
				}

			} else if (ss[0].trim().equalsIgnoreCase("pictureUrl")) {
				if (ss.length > 1) {
					su.setPictureUrl(ss[1] + ":" + ss[2]);
				}

			}

		}
		return su;
	}

	public static User userFromSocialUser(SocialUser su) {
		User u = new User(su.getFirstName().trim(), su.getLastName().trim());
		u.setHeadline(su.getHeadline());
		u.setGender(su.getGender());
		u.setPictureUrl(su.getPictureUrl());
		Set<String> emails = new HashSet<>();
		emails.add(su.getEmail());
		u.setEmails(emails);
		switch (su.getNetwork()) {
		case LINKEDIN:
			u.setLinkedInId(su.getId());
			break;
		case FACEBOOK:
			u.setFacebookId(su.getId());
			break;
		case TWITTER:
			u.setTwitterId(su.getId());
			break;
		case GOOGLE:
			u.setGoogleId(su.getId());
			break;
		}
		return u;
	}

	public static void logUserIn(User user, HttpServletRequest req,
			HttpServletResponse resp, Boolean isAjax) throws IOException,
			ServletException {
		HttpSession session = req.getSession();
		user.setAuthenticated(true);
		CartController con = new CartController();
		Key key = user.getCart();
		Cart cart = null;
		if (key != null) {
			cart = con.findCart(user.getCart());
			Object ob = null;
			synchronized (session) {
				ob = session.getAttribute("cart");
			}

			if (ob != null) {
				Cart c = (Cart) ob;
				List<EmbeddedEntity> li = cart.getItems();
				if (li != null) {
					li.addAll(c.getItems());
					cart.setItems(li);
				} else {
					li = c.getItems();
					if (li != null) {
						cart.setItems(li);
					}
				}

			}

		} else {
			cart = new Cart();

			cart.setItems(new ArrayList<EmbeddedEntity>());

		}

		Object o = null;
		synchronized (session) {
			session.setAttribute("user", user);
			session.setAttribute("user", user);
			session.setAttribute("cart", cart);
			o = session.getAttribute("requestURI");
		}

		if (isAjax) {
			if (o == null) {
				String url = resp.encodeURL("/sm/open/show-all-workshops");
				resp.getWriter().write(url);
			} else {
				String url = (String) o;
				if (!url.equalsIgnoreCase("/sm/open/start-discussion-page")) {
					resp.getWriter().write(url);
				}
			}
		} else {
			if (o == null) {
				resp.sendRedirect(resp.encodeRedirectURL("/index"));
				return;

			} else {
				String url = (String) o;
				url = resp.encodeRedirectURL(url);
				resp.sendRedirect(url);
			}
		}

	}

	public static Entity SocialUserToEntity(SocialUser su) {
		Entity e = new Entity(SocialUser.class.getSimpleName(), su.getId());
		e.setUnindexedProperty("email", su.getEmail());
		e.setUnindexedProperty("firstName", su.getFirstName());
		e.setUnindexedProperty("gender", su.getGender());
		e.setUnindexedProperty("headline", su.getHeadline());
		e.setUnindexedProperty("lastName", su.getLastName());
		e.setUnindexedProperty("network", su.getNetwork().name());
		return e;
	}

	public static SocialUser toFaceBookSocialUser(String respString) {
		SocialUser su = new SocialUser();
		su.setNetwork(SocialNetwork.FACEBOOK);
		JSONTokener jt = new JSONTokener(respString);
		JSONObject jo = new JSONObject(jt);
		if (respString.contains("email")) {
			su.setEmail(jo.getString("email"));
		}
		if (respString.contains("first_name")) {
			su.setFirstName(jo.getString("first_name"));
		}
		if (respString.contains("gender")) {
			su.setGender(jo.getString("gender"));
		}
		if (respString.contains("id")) {
			su.setId(jo.getString("id"));
		}
		if (respString.contains("last_name")) {
			su.setLastName(jo.getString("last_name"));
		}
		su.setPictureUrl(jo.getJSONObject("picture").getJSONObject("data")
				.getString("url"));
		su.setVerified(jo.getBoolean("verified"));
		return su;
	}

	public static List<Entity> TagsToEntities(List<Tag> tags) {
		List<Entity> ents = new ArrayList<>();
		for (Tag t : tags) {
			Entity e = new Entity(Tag.class.getSimpleName(), t.getName());
			e.setUnindexedProperty("description", t.getDescription());
			ents.add(e);
		}
		return ents;
	}

	public static List<Key> getTagsKeys(List<Entity> tgs) {
		List<Key> keys = new ArrayList<>();

		for (Entity e : tgs) {
			keys.add(e.getKey());
		}
		return keys;
	}

	public static Entity discussionToEntity(Discussion discussion) {
		Entity e = null;
		if (discussion != null) {
			if (discussion.getId() != null) {
				e = new Entity(discussion.getId());
			} else {
				e = new Entity(Discussion.class.getSimpleName());
			}
			e.setIndexedProperty("owner", discussion.getOwner());
			e.setIndexedProperty("socialNetwork", discussion.getSocialNetwork());
			e.setIndexedProperty("views", discussion.getView());
			e.setIndexedProperty("votes", discussion.getVotes());
			e.setIndexedProperty("timePosted", discussion.getTimePosted());
			e.setUnindexedProperty("title", discussion.getTitle());
			e.setUnindexedProperty("body", discussion.getBody());
			e.setUnindexedProperty("tags", discussion.getTags());
			e.setUnindexedProperty("emailsToNotify",
					discussion.getEmailsToNotify());
			e.setUnindexedProperty("comments", discussion.getComments());
			e.setProperty("category", discussion.getCategory());
			e.setIndexedProperty("privacy", discussion.getPrivacy());
		}
		return e;
	}

	@SuppressWarnings("unchecked")
	public static Discussion entityToDiscussion(Entity ent) {
		Discussion d = new Discussion();
		d.setId(ent.getKey());
		d.setTitle((String) ent.getProperty("title"));
		d.setSocialNetwork((String) ent.getProperty("socialNetwork"));
		d.setView((Long) ent.getProperty("views"));
		d.setPrivacy((String) ent.getProperty("privacy"));
		d.setBody((Text) ent.getProperty("body"));
		d.setCategory((String) ent.getProperty("category"));
		d.setComments((List<Key>) ent.getProperty("comments"));
		d.setEmailsToNotify((List<String>) ent.getProperty("emailsToNotify"));
		d.setOwner((Key) ent.getProperty("owner"));
		d.setTimePosted((Date) ent.getProperty("timePosted"));
		d.setVotes((Long) ent.getProperty("votes"));

		return d;
	}

	public static Discussion coachingPostToDiscussion(CoachingPost cp, User u) {
		Discussion d = new Discussion();
		d.setBody(new Text(cp.getBody()));
		d.setCategory(cp.getCategory());
		d.setOwner(u.getRegId());
		d.setTimePosted(new Date());
		List<String> emails = new ArrayList<>();
		if ((cp.getNotify() != null && cp.getNotify().equalsIgnoreCase("true"))
				| cp.getPrivacy().equalsIgnoreCase("private")) {
			emails.add(u.getUsername());
			d.setEmailsToNotify(emails);
		} else {
			d.setEmailsToNotify(emails);
		}
		d.setPrivacy(cp.getPrivacy());
		d.setTitle(cp.getTitle());
		d.setView(0);
		d.setVotes(0);
		d.setComments(new ArrayList<Key>());
		List<Key> tags = null;

		return d;
	}

	public static SingleDiscussionPageBean discussionToSDPB(Discussion d, User u) {
		SingleDiscussionPageBean spdb = new SingleDiscussionPageBean();
		spdb.setBody(d.getBody().getValue());
		spdb.setCategory(d.getCategory());
		// spdb.setComments(comments);
		spdb.setOwnerImage(u.getPictureUrl());
		// spdb.setTags(tags);
		spdb.setTime(d.getTimePosted().toString());
		spdb.setTopic(d.getTitle());
		spdb.setViews(d.getView());
		spdb.setVotes(d.getVotes());
		spdb.setOwnerName(u.getFirstName() + " " + u.getLastName());
		if (d.getId() != null) {
			spdb.setWebkey(KeyFactory.keyToString(d.getId()));
		}

		return spdb;
	}

	private static List<CommentBean> commentsKeyToCommentsBean(
			List<Key> comments) {
		List<Comment> mp = new CommentController().findComments(comments);
		List<CommentBean> l = Util.toCommentBean(mp);
		return l;
	}

	private static List<CommentBean> toCommentBean(List<Comment> mp) {
		List<CommentBean> l = new ArrayList<CommentBean>();
		List<Key> userKeys = new ArrayList<Key>();
		for (Comment c : mp) {
			userKeys.add(c.getOwner());
		}
		Map<Key, User> map = new UserController().findUsers(userKeys);
		for (Comment c : mp) {
			CommentBean cb = new CommentBean();
			cb.setBody(c.getBody().getValue());
			cb.setComments(Util.commentsKeyToCommentsBean(c.getComments()));
			if (c.getLikers() == null) {
				cb.setLikes(0);
			} else {
				cb.setLikes(c.getLikers().size());
			}
			User owner = map.get(c.getOwner());
			cb.setOwnerImage(owner.getPictureUrl());
			cb.setOwnerName(owner.getFirstName() + " " + owner.getLastName());
			cb.setTime(c.getTime());
			cb.setWebkey(KeyFactory.keyToString(c.getId()));
			l.add(cb);
		}
		return l;
	}

	public static List<SingleDiscussionPageBean> discussionToSDPB(
			List<Discussion> dis) {
		List<SingleDiscussionPageBean> list = new ArrayList<>();
		List<Key> keys = new ArrayList<Key>();
		for (Discussion d : dis) {
			if (!keys.contains(d.getOwner())) {
				keys.add(d.getOwner());
			}
		}

		Map<Key, User> map = new UserController().findUsers(keys);
		for (Discussion d : dis) {
			User u = map.get(d.getOwner());
			SingleDiscussionPageBean spdb = new SingleDiscussionPageBean();
			spdb.setWebkey(KeyFactory.keyToString(d.getId()));
			spdb.setBody(d.getBody().getValue());
			spdb.setCategory(d.getCategory());
			spdb.setComments(Util.commentsKeyToCommentsBean(d.getComments()));
			spdb.setOwnerImage(u.getPictureUrl());
			// spdb.setTags(tags);
			spdb.setTime(d.getTimePosted().toString());
			spdb.setTopic(d.getTitle());
			spdb.setViews(d.getView());
			spdb.setVotes(d.getVotes());
			spdb.setOwnerName(u.getFirstName() + " " + u.getLastName());
			list.add(spdb);
		}

		return list;
	}

	public static CommentBean createCommentBean(Comment comment, User u,
			boolean newComment) {
		CommentBean cb = new CommentBean();
		cb.setBody(comment.getBody().getValue());
		cb.setLikes(comment.getLikers().size());
		cb.setOwnerImage(u.getPictureUrl());
		cb.setOwnerName(u.getFirstName() + " " + u.getLastName());
		cb.setTime(comment.getTime());
		cb.setWebkey(KeyFactory.keyToString(comment.getId()));
		if (newComment) {
			cb.setComments(new ArrayList<CommentBean>());
		}
		// do set comments
		return cb;
	}

	public static Entity commentToEntity(Comment c) {

		Entity e = null;
		if (c.getId() != null) {
			e = new Entity(c.getId());
		} else {
			e = new Entity(Comment.class.getSimpleName());
		}
		e.setIndexedProperty("time", c.getTime());
		e.setIndexedProperty("owner", c.getOwner());
		e.setUnindexedProperty("body", c.getBody());
		e.setUnindexedProperty("comments", c.getComments());
		e.setUnindexedProperty("likers", c.getLikers());
		return e;
	}

	public static Comment entityToComment(Entity e) {
		Comment c = new Comment();
		c.setBody((Text) e.getProperty("body"));
		c.setComments((List<Key>) e.getProperty("comments"));
		c.setId(e.getKey());
		c.setLikers((List<Key>) e.getProperty("likers"));
		c.setOwner((Key) e.getProperty("owner"));
		c.setTime((Date) e.getProperty("time"));
		return c;
	}

	public static void updateDiscussionInCache(SingleDiscussionPageBean sdpb) {
		Object o = Util.DISCUSSION_CACHE.get(sdpb.getCategory()
				.replace(" ", ""));
		if (o != null) {
			DiscussionPageBean dpb = (DiscussionPageBean) o;
			List<SingleDiscussionPageBean> l = dpb.getBeans();

			for (SingleDiscussionPageBean x : l) {
				if (x.getWebkey().equals(sdpb.getWebkey())) {
					l.set(l.indexOf(x), sdpb);
				}
			}

			dpb.setBeans(l);
			Util.DISCUSSION_CACHE.put(sdpb.getCategory().replace(" ", ""), dpb);

		}

	}

	public static Entity testimonialToEntity(Testimonial t) {

		Entity e = null;
		if (t.getId() != null) {
			e = new Entity(t.getId());
		} else {
			e = new Entity(Testimonial.class.getSimpleName());
		}

		e.setIndexedProperty("date", t.getDate());
		e.setUnindexedProperty("statement", t.getStatement());
		e.setUnindexedProperty("writer", t.getWriter());
		e.setUnindexedProperty("writerName", t.getWriterName());

		return e;
	}

	public static Testimonial entityToTestimonial(Entity e) {
		Testimonial t = new Testimonial();
		t.setDate((Date) e.getProperty("date"));
		t.setStatement((Text) e.getProperty("statement"));
		t.setWriter((Key) e.getProperty("writer"));
		t.setWriterName((String) e.getProperty("writerName"));
		t.setId(e.getKey());
		return t;
	}

	@SuppressWarnings("unchecked")
	public static List<SingleDiscussionPageBean> getHotestDiscussion() {

		Object o = DISCUSSION_CACHE.get("hottest");
		if (o == null) {

			DiscussionPageBean o1 = Util
					.getDiscussionFromCache("Interview Coaching");
			DiscussionPageBean o2 = Util
					.getDiscussionFromCache("Excecutive Coaching");
			DiscussionPageBean o3 = Util
					.getDiscussionFromCache("Sales Performance Coaching");

			List<SingleDiscussionPageBean> dis1 = o1.getBeans();
			List<SingleDiscussionPageBean> dis2 = o2.getBeans();
			List<SingleDiscussionPageBean> dis3 = o3.getBeans();
			List<SingleDiscussionPageBean> dis4 = new ArrayList<>();
			dis4.addAll(dis1);
			dis4.addAll(dis2);
			dis4.addAll(dis3);
			Collections.sort(dis4, new Comparator<SingleDiscussionPageBean>() {
				@Override
				public int compare(SingleDiscussionPageBean d1,
						SingleDiscussionPageBean d2) {

					return new Long(d1.getViews()).compareTo(new Long(d2
							.getViews()));
				}
			});

			int x = 0;
			if (dis4.size() > 9) {
				x = 9;
			} else {
				x = dis4.size();
			}
			dis4 = dis4.subList(0, x);
			dis1.clear();
			dis1.addAll(dis4);
			DISCUSSION_CACHE.put("hotest", dis1,
					Expiration.byDeltaSeconds(302400));
			return new ArrayList<>(dis1);
		} else {
			List<SingleDiscussionPageBean> dis = (List<SingleDiscussionPageBean>) o;
			return dis;
		}

	}

	public static void sendEmailNotification(List<String> emailsToNotify,
			String title, String body) {
		if (emailsToNotify == null) {
			return;
		} else {
			Queue q = QueueFactory.getDefaultQueue();
			for (String email : emailsToNotify) {
				q.add(TaskOptions.Builder
						.withUrl("/sm/open/send-notification-email")
						.param("email", email).param("body", body)
						.param("title", title));
			}
		}

	}

	public static void sendEmailNotification(String email, String title,
			String body) {
		Queue q = QueueFactory.getDefaultQueue();

		q.add(TaskOptions.Builder.withUrl("/sm/open/send-notification-email")
				.param("email", email).param("body", body)
				.param("title", title));

	}

	public static Entity SalesMarketingTemplateToEntity(
			SalesMarketingTemplate smt) {

		Entity e = null;
		if (smt.getId() == null) {
			e = new Entity(SalesMarketingTemplate.class.getSimpleName());
		} else {
			e = new Entity(smt.getId());
		}

		e.setProperty("name", smt.getName());
		e.setProperty("format", smt.getFormat().name());
		e.setUnindexedProperty("price", smt.getPrice());
		e.setProperty("category", smt.getCategory());
		e.setUnindexedProperty("imageKey", smt.getImageKey());
		return e;
	}

	public static SalesMarketingTemplate entityToSalesMarketingTemplate(Entity e) {
		SalesMarketingTemplate smt = new SalesMarketingTemplate();
		smt.setCategory((Key) e.getProperty("category"));
		smt.setId(e.getKey());
		smt.setName((String) e.getProperty("name"));
		smt.setPrice((Double) e.getProperty("price"));
		smt.setImageKey((BlobKey) e.getProperty("imageKey"));
		String format = (String) e.getProperty("format");
		if (format.equalsIgnoreCase(SalesTemplateFormat.EXCEL.name())) {
			smt.setFormat(SalesTemplateFormat.EXCEL);
		} else if (format.equalsIgnoreCase(SalesTemplateFormat.MS_WORD.name())) {
			smt.setFormat(SalesTemplateFormat.MS_WORD);
		} else if (format.equalsIgnoreCase(SalesTemplateFormat.PDF.name())) {
			smt.setFormat(SalesTemplateFormat.PDF);
		} else if (format.equalsIgnoreCase(SalesTemplateFormat.POWER_POINT
				.name())) {
			smt.setFormat(SalesTemplateFormat.POWER_POINT);
		}

		return smt;
	}

	public static Entity salesMarketingTemplateCategoryToEntity(
			SalesMarketingTemplateCategory smtc) {
		Entity e = null;
		if (smtc.getId() == null) {
			e = new Entity(SalesMarketingTemplateCategory.class.getSimpleName());
		} else {
			e = new Entity(smtc.getId());
		}
		e.setUnindexedProperty("templates", smtc.getTemplates());
		return e;
	}

	public static SalesMarketingTemplateCategory entityToSalesMarketingTemplateCategory(
			Entity e) {
		SalesMarketingTemplateCategory smtc = new SalesMarketingTemplateCategory();
		smtc.setId(e.getKey());
		smtc.setTemplates((List<Key>) e.getProperty("templates"));
		return smtc;
	}

	public static Entity ManualTransactionToEntity(ManualTransaction mt) {
		Entity e = null;
		if (mt.getId() == null) {
			e = new Entity(ManualTransaction.class.getSimpleName());
		} else {
			e = new Entity(mt.getId());
		}
		e.setIndexedProperty("ownerKey", mt.getOwnerKey());
		e.setIndexedProperty("txnRef", mt.getTxnRef());
		e.setIndexedProperty("transactionType", mt.getTransactionType());
		e.setUnindexedProperty("items", mt.getItems());
		e.setProperty("issueDate", mt.getIssueDate());
		e.setProperty("status", mt.getStatus());
		return e;
	}

	public static ManualTransaction entityToManualTransaction(Entity e) {
		ManualTransaction mt = new ManualTransaction();
		mt.setId(e.getKey());
		mt.setIssueDate((Date) e.getProperty("issueDate"));
		mt.setItems((List<EmbeddedEntity>) e.getProperty("items"));
		mt.setOwnerKey((Key) e.getProperty("ownerKey"));
		mt.setStatus((String) e.getProperty("status"));
		mt.setTransactionType((String) e.getProperty("transactionType"));
		mt.setTxnRef((String) e.getProperty("txnRef"));
		return mt;
	}

	public static ChequePaymentBean getChequePaymentBean(String category,
			String currentPage, String numberOfEntries, ChequePaymentBean oCpb) {
		ChequePaymentBean cpb = new ChequePaymentBean();
		if (notNull(category) | oCpb == null) {
			if (notNull(category)) {
				cpb.setCategory(category);
			} else {
				cpb.setCategory("pending");
			}

			if (notNull(currentPage)) {
				cpb.setCurrentPage(Integer.parseInt(currentPage));
			} else {
				cpb.setCurrentPage(1);
			}
			if (notNull(numberOfEntries)) {
				cpb.setNoOfEntries(Integer.parseInt(numberOfEntries));
			} else {
				cpb.setNoOfEntries(10);
			}

			ManualTransactionController mtc = new ManualTransactionController();
			cpb = mtc.addManualTransactions(cpb);

		} else {

			if (notNull(currentPage)) {
				oCpb.setCurrentPage(Integer.parseInt(currentPage));
			}
			if (notNull(numberOfEntries)) {
				oCpb.setNoOfEntries(Integer.parseInt(numberOfEntries));
				ManualTransactionController mtc = new ManualTransactionController();
				oCpb = mtc.addManualTransactions(oCpb);
			}
			cpb = oCpb;
		}
		return cpb;
	}

	public static List<ManualPaymentBean> toManualPaymentBean(
			List<ManualTransaction> mts) {
		List<ManualPaymentBean> mpbs = new ArrayList<>();
		for (ManualTransaction mt : mts) {
			ManualPaymentBean mpb = Util.toManualPaymentBean(mt);
			mpbs.add(mpb);
		}
		return mpbs;
	}

	private static ManualPaymentBean toManualPaymentBean(ManualTransaction mt) {
		ManualPaymentBean mpb = new ManualPaymentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		mpb.setIssueDate(sdf.format(mt.getIssueDate()));
		Calendar date = Calendar.getInstance();
		date.setTime(mt.getIssueDate());
		date.add(Calendar.DAY_OF_MONTH, 5);
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			date.add(Calendar.DAY_OF_MONTH, 2);
		} else if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			date.add(Calendar.DAY_OF_MONTH, 1);
		}
		mpb.setOverdueDate(sdf.format(date.getTime()));
		mpb.setTxnRef(mt.getTxnRef());
		User u = new UserController().findUser(mt.getOwnerKey());
		if (u != null) {
			mpb.setCustomerName(u.getFirstName() + " " + u.getLastName());
		}
		DecimalFormat f1 = new DecimalFormat("#,###.00");
		List<PendingWorkshopBean> items = new ArrayList<>();
		Set<CartItem> cis = Util.getCartItems(mt.getItems());
		double total = 0;
		if (cis != null) {
			for (CartItem ci : cis) {
				PendingWorkshopBean pwb = new PendingWorkshopBean();
				WorkShop w = Util
						.getWorkshopSchedule(String.valueOf(ci.getId()));
				pwb.setDate(sdf.format(w.getStartDate()));

				ScheduleWorkshopDisplay swd = Util.toScheduleWorkshopDisplay(w);
				pwb.setScheduleID(swd.getId());
				pwb.setLocation(swd.getVenue());
				WorkshopTemplate wt = getWorkshopTemplateFromScheduleId(
						Util.getWorkshopTemplateFromCache(),
						String.valueOf(w.getId().getId()));
				pwb.setWorkshopCode(wt.getWorkshopId().getName());
				pwb.setWorkshopName(wt.getWorkshopName());
				pwb.setUnitPrice(f1.format(ci.getPrice()));
				pwb.setQty(String.valueOf(ci.getQty()));
				pwb.setTotalPrice(f1.format(ci.getPrice() * ci.getQty()));
				total += (ci.getPrice() * ci.getQty());
				pwb.setUserWebSafeKey(KeyFactory.keyToString(u.getRegId()));
				items.add(pwb);
			}
			mpb.setTotalAmount(f1.format(total));
			mpb.setPwbs(items);
		}
		return mpb;
	}

	public static List<EmbeddedEntity> getUpdatedItems(Cart c, String id,
			String qty, HttpServletRequest req) {
		HttpSession session = req.getSession();
		List<EmbeddedEntity> items = null;
		if (Util.notNull(id, qty)) {
			items = c.getItems();
			for (EmbeddedEntity ee : items) {

				if (((String) ee.getProperty("workshopID")).equals(id)) {
					WorkShop w = Util.getWorkshopSchedule(id);
					long seatsLeft = 25 - w.getNoEnrolled();
					if (seatsLeft >= Long.valueOf(qty)) {
						ee.setProperty("qty", qty);
						synchronized (session) {
							session.removeAttribute("cannotAddWorkshops");
							session.removeAttribute("notAddedWorkshop");
						}
					} else {
						ScheduleWorkshopDisplay swd = Util
								.toScheduleWorkshopDisplay(w);
						ee.setProperty("qty", String.valueOf(seatsLeft));
						synchronized (session) {
							session.setAttribute("cannotAddWorkshops", true);
							session.setAttribute("notAddedWorkshop", swd);
						}

					}

					break;
				}
			}
		} else if (Util.notNull(id)) {
			items = c.getItems();
			for (EmbeddedEntity ee : items) {
				if (((String) ee.getProperty("workshopID")).equals(id)) {
					items.remove(ee);
					synchronized (session) {
						session.removeAttribute("cannotAddWorkshops");
						session.removeAttribute("notAddedWorkshop");
					}
					break;
				}
			}
		} else {
			items = new ArrayList<EmbeddedEntity>();
		}
		synchronized (session) {
			session.removeAttribute("existingItem");
			session.removeAttribute("itemExist");
		}
		return items;
	}

	public static boolean clearManualPayment(String txnRef, WorkShop w) {
		ManualTransactionController mtc = new ManualTransactionController();
		List<ManualTransaction> mtss = mtc.findByTxnRef(txnRef,
				ChequeInvoice.InvoiceStatus.PENDING);
		ManualTransaction mt = null;
		if (mtss.size() == 1) {
			mt = mtss.get(0);
		} else {
			return false;
		}
		List<EmbeddedEntity> list = mt.getItems();
		EmbeddedEntity emb = null;
		for (EmbeddedEntity ee : list) {
			if (((String) ee.getProperty("workshopID")).equals(String.valueOf(w
					.getId().getId()))) {
				list.remove(ee);
				emb = ee;
				break;
			}
		}
		if (list.isEmpty()) {
			mt.setStatus(ChequeInvoice.InvoiceStatus.CLEARED.name());
			list.add(emb);
			mt.setItems(list);
			List<ManualTransaction> l1 = mtc.findByTxnRef(mt.getTxnRef(),
					ChequeInvoice.InvoiceStatus.CLEARED);
			if (l1 == null || l1.isEmpty()) {
				mtc.create(mt);
				addPurchaseHistory(mt);
			} else {
				if (l1.size() == 1) {
					ManualTransaction mt1 = l1.get(0);
					mt1.getItems().addAll(mt.getItems());
					mtc.create(mt1);
					addPurchaseHistory(mt1);
				}
			}

		} else {
			ManualTransaction cmt = new ManualTransaction(mt);
			List<EmbeddedEntity> l = new ArrayList<>();
			l.add(emb);
			cmt.setItems(l);
			cmt.setStatus(ChequeInvoice.InvoiceStatus.CLEARED.name());
			List<ManualTransaction> l1 = mtc.findByTxnRef(cmt.getTxnRef(),
					ChequeInvoice.InvoiceStatus.CLEARED);
			if (l1 == null || l1.isEmpty()) {
				// create using bulk input(already done)
			} else {
				if (l1.size() == 1) {
					ManualTransaction mt1 = l1.get(0);
					mt1.getItems().addAll(cmt.getItems());
					cmt = mt1;
				}
			}
			mt.setItems(list);
			List<ManualTransaction> mts = new ArrayList<>();
			mts.add(cmt);
			mts.add(mt);
			addPurchaseHistory(cmt);
			mtc.create(mts);
		}
		return true;
	}

	private static void addPurchaseHistory(ManualTransaction mt) {

		PurchaseHistory ph = Util.manualTransactionToPurchaseHistory(mt);
		PurchaseHistoryController c = new PurchaseHistoryController();
		PurchaseHistory ph1 = c.findByTransactionRef(ph.getTxnRef());
		if (ph1 != null) {
			ph1.getItems().addAll(ph.getItems());
			ph = ph1;
		}
		User u = new UserController().findUser(mt.getOwnerKey());
		UserGeneralInfoController ugc = new UserGeneralInfoController();
		UserGeneralInfo ugi = ugc.findUserGeneralInfo(u, u.getGeneralInfoId());
		List<PurchaseHistory> phs = new ArrayList<>();
		phs.add(ph);
		ugc.edit(ugi, u.getRegId(), phs);

	}

	private static void updateManualTransaction(ManualTransaction mt,
			UserGeneralInfo ugi) {
		ManualTransactionController mtc = new ManualTransactionController();
		List<ManualTransaction> omt = mtc.findByTxnRef(mt.getTransactionType(),
				ChequeInvoice.InvoiceStatus.PENDING);
		if (omt.size() == 1) {
			ManualTransaction mt1 = omt.get(0);
			if (mt1.getItems().size() == mt.getItems().size()) {
				List<Key> keys = ugi.getPendingOrder();
				for (Key k : keys) {
					if (k == mt.getId()) {
						keys.remove(k);
					}
				}
				// new UserGeneralInfoC
			}
		}

	}

	public static Cart getNewCart() {
		Cart c = new Cart();
		c.setItems(new ArrayList<EmbeddedEntity>());
		return c;
	}

	public static UserGeneralInfo getNewUserGeneralInfo() {
		UserGeneralInfo ugi = new UserGeneralInfo();
		ugi.setCertificate(new HashSet<BlobKey>());
		ugi.setCompletedManualOrder(new ArrayList<Key>());
		ugi.setCompletedWorkshops(new HashSet<Key>());
		// ugi.setEnrolledEvents(new HashSet<Long>());
		ugi.setEnrolledWorkshops(new HashSet<Key>());
		ugi.setPendingOrder(new ArrayList<Key>());
		ugi.setPhones(new ArrayList<String>());
		ugi.setPurchaseHistory(new ArrayList<Key>());
		ugi.setSalesmaxxHistoryCredits(new ArrayList<Key>());
		return ugi;
	}

	public static Entity salesmaxxCreditHistoryToEntity(
			SalesmaxxCreditHistory smch) {
		Entity e = new Entity(smch.getId());
		e.setUnindexedProperty("amountPaid", smch.getAmount());
		e.setUnindexedProperty("creditsRecieved", smch.getCreditRecieved());
		e.setUnindexedProperty("date", smch.getExpiryDate());
		e.setUnindexedProperty("title", smch.getTitle());
		return e;
	}

	public static void sendWebPayPurchaseSuccessSMS(String primaryPhone) throws TwilioRestException {
		String msg = "Your transaction has been successful and your seat has been reserved. Check your email for detailed information.Thank you for choose SalesMaxx";
		
		Util.sendSMS(primaryPhone, msg);
	}

}
