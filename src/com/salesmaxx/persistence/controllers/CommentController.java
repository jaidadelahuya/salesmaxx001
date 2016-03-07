package com.salesmaxx.persistence.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.salesmaxx.entities.Comment;
import com.salesmaxx.util.Util;

public class CommentController {
	private static DatastoreService ds = EMF.getDs();
	private Transaction txn = null;

	public List<Comment> findComments(List<Key> keys) {
		if (keys == null) {
			return new ArrayList<>();
		} else {
			Map<Key, Entity> map = ds.get(keys);
			List<Comment> m = new ArrayList<>();
			Set<Key> set = map.keySet();
			for (Key k : set) {
				Comment c = Util.entityToComment(map.get(k));
				m.add(c);
			}
			return m;
		}

	}
}
