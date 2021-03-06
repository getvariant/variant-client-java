package com.variant.client.servlet.mock;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

/**
 * HttpServeltRequest partial mock adds state and methods to get to it.
 */
public abstract class HttpServletRequestMock implements HttpServletRequest {

	private HashMap<String, Object> attributes = null;
	
	@Override public void setAttribute(String key, Object value) {
		if (attributes == null) attributes = new HashMap<String, Object>();
		attributes.put(key, value);
	}
	
	@Override public Object getAttribute(String key) {
		return attributes == null ? null : attributes.get(key);
	}

}
