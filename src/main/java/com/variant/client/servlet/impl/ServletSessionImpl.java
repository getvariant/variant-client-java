package com.variant.client.servlet.impl;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.variant.client.Session;
import com.variant.client.SessionAttributes;
import com.variant.client.StateRequest;
import com.variant.client.TraceEvent;
import com.variant.client.servlet.ServletConnection;
import com.variant.client.servlet.ServletSession;
import com.variant.client.servlet.ServletStateRequest;
import com.variant.client.servlet.ServletVariantException;
import com.variant.share.schema.Schema;
import com.variant.share.schema.State;
import com.variant.share.schema.Variation;
import com.variant.share.schema.Variation.Experience;

/**
 * <p>The implementation of {@link ServletSession}.
 * @author Igor Urisman
 * @since 1.0
 */
public class ServletSessionImpl implements ServletSession {

	private final ServletConnection wrapConnection;
	private final Session bareSession;
	private ServletStateRequest wrapStateRequest;

	/**
	 * 
	 */
	public ServletSessionImpl(ServletConnectionImpl wrapConnection, Session bareSession) {
		if (wrapConnection == null) throw ServletVariantException.internal("Servlet connection cannot be null");
		if (bareSession == null) throw ServletVariantException.internal("Bare session cannot be null");
		this.wrapConnection = wrapConnection;
		this.bareSession = bareSession;
		
	}

	@Override
	public ServletStateRequest targetForState(State state) {
		wrapStateRequest = new ServletStateRequestImpl(this, bareSession.targetForState(state));
		return wrapStateRequest;
	}

	@Override
	public SessionAttributes getAttributes() {
		return bareSession.getAttributes();
	}

	@Override
	public ServletConnection getConnection() {
		return wrapConnection;
	}

	@Override
	public Instant getTimestamp() {
		return bareSession.getTimestamp();
	}

	@Override
	public Set<Variation> getDisqualifiedVariations() {
		return bareSession.getDisqualifiedVariations();
	}

	@Override
	public String getId() {
		return bareSession.getId();
	}

	@Override
	public Optional<StateRequest> getStateRequest() {
		return Optional.ofNullable(wrapStateRequest);
	}

	@Override
	public long getTimeoutMillis() {
		return bareSession.getTimeoutMillis();
	}

	@Override
	public Map<State, Integer> getTraversedStates() {
		return bareSession.getTraversedStates();
	}

	@Override
	public Set<Variation> getTraversedVariations() {
		return bareSession.getTraversedVariations();
	}


	@Override
	public void triggerTraceEvent(TraceEvent event) {
		bareSession.triggerTraceEvent(event);
	}

	@Override
	public Schema getSchema() {
		return bareSession.getSchema();
	}

	@Override
	public Optional<Experience> getLiveExperience(Variation var) {
		return bareSession.getLiveExperience(var);
	}

	@Override
	public Optional<Experience> getLiveExperience(String varName) {
		return bareSession.getLiveExperience(varName);
	}

	@Override
	public Set<Experience> getLiveExperiences() {
		return bareSession.getLiveExperiences();
	}
}
