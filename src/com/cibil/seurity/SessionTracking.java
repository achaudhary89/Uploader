package com.cibil.seurity;

import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

/**
 * @author arunbal.srinivasan
 *
 */
public class SessionTracking {

	private static Hashtable data;
	private static SessionTracking _tracker;

	public SessionTracking() {
		data = new Hashtable();
	}

	public static SessionTracking getInstance() {
		if (_tracker == null) {
			_tracker = new SessionTracking();
		}
		return _tracker;
	}

	public static void addUser(String username, HttpServletRequest request) {
		getInstance();
		if (data != null) {
			data.put(username, request.getSession().getId());
		}
	}

	public static void deleteUser(String username) {
		if (data != null) {
			data.remove(username);
		}
	}

	public static boolean isValidUser(String username,
			HttpServletRequest request) {
		boolean result = false;
		if (data.containsKey(username)) {
			if (data.get(username).equals(request.getSession().getId())) {
				return true;
			}
		}
		return result;
	}

}