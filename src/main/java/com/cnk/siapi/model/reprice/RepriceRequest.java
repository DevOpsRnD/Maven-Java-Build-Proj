package com.cnk.siapi.model.reprice;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;

public class RepriceRequest {

	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";

	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;

	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private RepriceRQBody RepriceBody;

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

	public RepriceRQBody getRepriceBody() {
		return RepriceBody;
	}

	public void setRepriceBody(RepriceRQBody repriceBody) {
		RepriceBody = repriceBody;
	}

}