package com.cnk.siapi.model.retrieve;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;

public class RetrieveRequest {

	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";
	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;
	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private RetrieveRQBody retrieveBody;

	public String getSightSeeing() {
		return sightSeeing;
	}

	public void setSightSeeing(String sightSeeing) {
		this.sightSeeing = sightSeeing;
	}

	public RequestHeaderDAO getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeaderDAO requestHeader) {
		this.requestHeader = requestHeader;
	}

	public RetrieveRQBody getRetrieveBody() {
		return retrieveBody;
	}

	public void setRetrieveBody(RetrieveRQBody retrieveBody) {
		this.retrieveBody = retrieveBody;
	}

	
}
