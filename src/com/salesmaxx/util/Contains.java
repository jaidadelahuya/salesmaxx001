package com.salesmaxx.util;

import java.util.Set;

public class Contains {
	public static boolean contains(Set<String> set, String s) {
		if(set == null) {
			return false;
		} else {
			if(set.contains(s)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public static boolean doit() {
		return true;
	}
}
