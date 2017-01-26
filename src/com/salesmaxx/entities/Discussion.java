package com.salesmaxx.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.salesmaxx.persistence.controllers.EMF;

public class Discussion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -901893669242034785L;
	
	private String title,socialNetwork;
	private Text body;
	private Key owner;
	private List<Key> tags,comments,view,votes;
	private Date timePosted;
	private List<String> emailsToNotify;
	private  String category;
	private String privacy;
	private Key id;
	private long nComments, nViews, nVotes;
	
	
	
	public long getnComments() {
		return nComments;
	}
	public void setnComments(long nComments) {
		this.nComments = nComments;
	}
	public long getnViews() {
		return nViews;
	}
	public void setnViews(long nViews) {
		this.nViews = nViews;
	}
	public long getnVotes() {
		return nVotes;
	}
	public void setnVotes(long nVotes) {
		this.nVotes = nVotes;
	}
	public Discussion() {
		id = EMF.getDs().allocateIds(Discussion.class.getSimpleName(), 1).getStart();
	}
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSocialNetwork() {
		return socialNetwork;
	}
	public void setSocialNetwork(String socialNetwork) {
		this.socialNetwork = socialNetwork;
	}
	public Text getBody() {
		return body;
	}
	public void setBody(Text body) {
		this.body = body;
	}
	public List<Key> getTags() {
		return tags;
	}
	public void setTags(List<Key> tags) {
		this.tags = tags;
	}
	public List<Key> getComments() {
		return comments;
	}
	public void setComments(List<Key> comments) {
		this.comments = comments;
		if(comments!=null) {
			nComments = comments.size();
		}
		
	}
	public Key getOwner() {
		return owner;
	}
	public void setOwner(Key owner) {
		this.owner = owner;
	}
	public Date getTimePosted() {
		return timePosted;
	}
	public void setTimePosted(Date timePosted) {
		this.timePosted = timePosted;
	}
	public List<String> getEmailsToNotify() {
		return emailsToNotify;
	}
	public void setEmailsToNotify(List<String> emailsToNotify) {
		this.emailsToNotify = emailsToNotify;
	}
	
	public List<Key> getView() {
		return view;
	}
	public void setView(List<Key> view) {
		this.view = view;
		if(view != null) {
			nViews = view.size();
		}
	}
	public List<Key> getVotes() {
		return votes;
	}
	public void setVotes(List<Key> votes) {
		this.votes = votes;
		if(votes != null) {
			nVotes = votes.size();
		}
	}
	@Override
	public String toString() {
		return "Discussion [title=" + title + ", socialNetwork="
				+ socialNetwork + ", body=" + body + ", owner=" + owner
				+ ", tags=" + tags + ", comments=" + comments + ", timePosted="
				+ timePosted + ", emailsToNotify=" + emailsToNotify + ", view="
				+ view + ", votes=" + votes + ", category=" + category
				+ ", privacy=" + privacy + ", id=" + id + "]";
	}
	
	
	
}
