package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Extension;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.persistence.controllers.LoyaltyOrganisationController;
import com.salesmaxx.util.Util;

@Entity
public class LoyaltyOrganisation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4080998323496854067L;

	@Id
	private Key name;

	private String organizationId;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private Text description;
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private Key address;
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String website;
	@Basic
	@Extension(vendorName = "datanucleus", key = "gae.unindexed", value = "true")
	private String image;
	private List<String> phones;

	public LoyaltyOrganisation(String name) {
		this.name = KeyFactory.createKey(
				LoyaltyOrganisation.class.getSimpleName(), name);
		this.organizationId = newOrganizationId(name);
	}

	private String newOrganizationId(String name2) {
		String pre = null;
		if (name2.length() > 3) {
			pre = name2.substring(0, 3);
		} else {
			pre = name2;
		}
		pre = pre + Util.generateRandomCode(100, 999);
		LoyaltyOrganisationController cont = new LoyaltyOrganisationController();
		if (cont.organizationIdExists(pre)) {
			return newOrganizationId(name2);
		} else {
			return pre;
		}
	}

	public Key getName() {
		return name;
	}

	public void setName(Key name) {
		this.name = name;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Text getDescription() {
		return description;
	}

	public void setDescription(Text description) {
		this.description = description;
	}

	public Key getAddress() {
		return address;
	}

	public void setAddress(Key address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String url) {
		this.image = url;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	@Override
	public String toString() {
		return "LoyaltyOrganisation [name=" + name + ", organizationId="
				+ organizationId + ", description=" + description
				+ ", address=" + address + ", website=" + website + ", image="
				+ image + ", phones=" + phones + "]";
	}

}
