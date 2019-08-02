package com.cnk.siapi.model.cancel;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;

public class CancelRequest {

	
	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";
	
	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;
	
	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private CancelRQBody cancelBookBody;
	
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
	public CancelRQBody getCancelBookBody() {
		return cancelBookBody;
	}
	public void setCancelBookBody(CancelRQBody cancelBookBody) {
		this.cancelBookBody = cancelBookBody;
	}
}
