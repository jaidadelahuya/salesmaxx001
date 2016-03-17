package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

public class UserGeneralInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3379934035699585206L;

	private long id;

	private Date dateOfBirth;
	private Key notification;
	private List<Key> purchaseHistory;
	private Set<Key> completedWorkshops;
	private Set<Key> enrolledWorkshops;
	private Set<Long> wishListEvent;
	private Set<Long> attendedEvents;
	private Set<Long> enrolledEvents;
	private Key address;
	private List<String> phones;
	private String website;
	private List<EmbeddedEntity> socialMedia;
	private Key cart;
	private boolean cancelWorkshop;
	private List<Long> SIG;
	private Set<BlobKey> certificate;
	private List<Key> salesmaxxHistoryCredits;
	private Text biography;
	private List<Key> pendingOrder;
	private List<Key> completedManualOrder;
	
	
	public List<Key> getPendingOrder() {
		return pendingOrder;
	}

	public void setPendingOrder(List<Key> pendingOrder) {
		this.pendingOrder = pendingOrder;
	}

	public List<Key> getCompletedManualOrder() {
		return completedManualOrder;
	}

	public void setCompletedManualOrder(List<Key> completedManualOrder) {
		this.completedManualOrder = completedManualOrder;
	}

	public Text getBiography() {
		return biography;
	}

	public void setBiography(Text biography) {
		this.biography = biography;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Key> getSalesmaxxHistoryCredits() {
		return salesmaxxHistoryCredits;
	}

	public void setSalesmaxxHistoryCredits(List<Key> salesmaxxHistoryCredits) {
		this.salesmaxxHistoryCredits = salesmaxxHistoryCredits;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Key getNotification() {
		return notification;
	}

	public void setNotification(Key notification) {
		this.notification = notification;
	}

	public List<Key> getPurchaseHistory() {
		return purchaseHistory;
	}

	public void setPurchaseHistory(List<Key> purchaseHistory) {
		this.purchaseHistory = purchaseHistory;
	}

	public Set<Key> getCompletedWorkshops() {
		return completedWorkshops;
	}

	public void setCompletedWorkshops(Set<Key> completedWorkshops) {
		this.completedWorkshops = completedWorkshops;
	}

	public Set<Key> getEnrolledWorkshops() {
		return enrolledWorkshops;
	}

	public void setEnrolledWorkshops(Set<Key> enrolledWorkshops) {
		this.enrolledWorkshops = enrolledWorkshops;
	}

	public Set<Long> getWishListEvent() {
		return wishListEvent;
	}

	public void setWishListEvent(Set<Long> wishListEvent) {
		this.wishListEvent = wishListEvent;
	}

	public Set<Long> getAttendedEvents() {
		return attendedEvents;
	}

	public void setAttendedEvents(Set<Long> attendedEvents) {
		this.attendedEvents = attendedEvents;
	}

	public Set<Long> getEnrolledEvents() {
		return enrolledEvents;
	}

	public void setEnrolledEvents(Set<Long> enrolledEvents) {
		this.enrolledEvents = enrolledEvents;
	}

	public Key getAddress() {
		return address;
	}

	public void setAddress(Key address) {
		this.address = address;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public List<EmbeddedEntity> getSocialMedia() {
		return socialMedia;
	}

	public void setSocialMedia(List<EmbeddedEntity> socialMedia) {
		this.socialMedia = socialMedia;
	}

	public Key getCart() {
		return cart;
	}

	public void setCart(Key cart) {
		this.cart = cart;
	}

	public boolean isCancelWorkshop() {
		return cancelWorkshop;
	}

	public void setCancelWorkshop(boolean cancelWorkshop) {
		this.cancelWorkshop = cancelWorkshop;
	}

	public List<Long> getSIG() {
		return SIG;
	}

	public void setSIG(List<Long> sIG) {
		SIG = sIG;
	}

	public Set<BlobKey> getCertificate() {
		return certificate;
	}

	public void setCertificate(Set<BlobKey> certificate) {
		this.certificate = certificate;
	}

	@Override
	public String toString() {
		return "UserGeneralInfo [id=" + id + ", dateOfBirth=" + dateOfBirth
				+ ", notification=" + notification + ", purchaseHistory="
				+ purchaseHistory
				+ ", completedWorkshops=" + completedWorkshops
				+ ", enrolledWorkshops=" + enrolledWorkshops
				+ ", wishListEvent=" + wishListEvent + ", attendedEvents="
				+ attendedEvents + ", enrolledEvents=" + enrolledEvents
				+ ", address=" + address + ", phones=" + phones + ", website="
				+ website + ", socialMedia=" + socialMedia + ", cart=" + cart
				+ ", cancelWorkshop=" + cancelWorkshop + ", SIG=" + SIG
				+ ", certificate=" + certificate + ", salesmaxxHistoryCredits="
				+ salesmaxxHistoryCredits + "]";
	}

}
