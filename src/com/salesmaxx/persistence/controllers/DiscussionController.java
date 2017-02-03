package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultList;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.salesmaxx.entities.Comment;
import com.salesmaxx.entities.Discussion;
import com.salesmaxx.entities.Tag;
import com.salesmaxx.util.Util;

public class DiscussionController {

	
	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;
	
	public Discussion create(Discussion discussion, List<Tag> tags) {
		
		if(tags != null) {
			List<Entity> tgs = Util.TagsToEntities(tags);
			List<Key> keys = Util.getTagsKeys(tgs);
			discussion.setTags(keys);
			Entity ents = Util.discussionToEntity(discussion);
			discussion.setId(ents.getKey());
			txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
			ds.put(tgs);
			ds.put(ents);
			txn.commit();
			if(discussion.getPrivacy().equalsIgnoreCase("public")) {
				Util.DISCUSSION_CACHE.clearAll();
			}
			
		} else {
			txn = ds.beginTransaction();
			Entity ents = Util.discussionToEntity(discussion);
			txn.commit();
			
		}
		
		return discussion;
		
	}
	
	public List<Discussion> findDiscussionsByCategory(String category) {
		Query q = new Query(Discussion.class.getSimpleName());
		Filter categoryFilter =
				  new FilterPredicate("category",
				                      FilterOperator.EQUAL,
				                      category);
		Filter privacyFilter =
				  new FilterPredicate("privacy",
				                      FilterOperator.EQUAL,
				                      "Public");
		Filter qFilter =
				  CompositeFilterOperator.and(categoryFilter, privacyFilter);
		q.setFilter(qFilter);
		q.addSort("views", Query.SortDirection.DESCENDING);
		PreparedQuery pq = ds.prepare(q);
		Iterator<Entity> it = pq.asIterator();
		List<Discussion> list = new ArrayList<>();
		
		while(it.hasNext()) {
			Discussion d = Util.entityToDiscussion(it.next());
			list.add(d);
		}
		return list;
	}
	
	public Discussion findDiscussion(Key key) {
		Entity e;
		Discussion d = null;
		try {
			e = ds.get(key);
			d = Util.entityToDiscussion(e);
		} catch (EntityNotFoundException e1) {
			return d;
		}
		
		return d;
	}

	public void edit(Discussion d, Comment comment) {
		Entity e = Util.discussionToEntity(d);
		Entity e1 = Util.commentToEntity(comment);
		txn = ds.beginTransaction(TransactionOptions.Builder.withXG(true));
		ds.put(e);
		ds.put(e1);
		txn.commitAsync();
		Util.DISCUSSION_CACHE.clearAll();
		
	}

	public QueryResultList<Entity> getHottestDiscussion() {
		Query q = new Query(Discussion.class.getSimpleName());
		//Filter filter = new Query.FilterPredicate("privacy", FilterOperator.EQUAL, "Public");
		//q.setFilter(filter);
		q.addSort("nComments", SortDirection.DESCENDING);
		FetchOptions f = FetchOptions.Builder.withLimit(10);
		PreparedQuery pq = ds.prepare(q);
		return pq.asQueryResultList(f);
	}
	
	public QueryResultList<Entity> getNewestDiscussion() {
		Query q = new Query(Discussion.class.getSimpleName());
		//Filter filter = new Query.FilterPredicate("privacy", FilterOperator.EQUAL, "Public");
		//q.setFilter(filter);
		q.addSort("timePosted", SortDirection.DESCENDING);
		FetchOptions f = FetchOptions.Builder.withLimit(5);
		PreparedQuery pq = ds.prepare(q);
		return pq.asQueryResultList(f);
	}

	public  QueryResultList<Entity> getDiscussions(String category, int limit, boolean keysOnly) {
		Query q = new Query(Discussion.class.getSimpleName());
		//Filter filter = new Query.FilterPredicate("category", FilterOperator.EQUAL, category);
		//q.setFilter(filter);
		q.addSort("timePosted", SortDirection.DESCENDING);
		if(keysOnly) {
			q.setKeysOnly();
		}
		FetchOptions f = FetchOptions.Builder.withLimit(limit);
		PreparedQuery pq = ds.prepare(q);
		return pq.asQueryResultList(f);
	}

	
}
