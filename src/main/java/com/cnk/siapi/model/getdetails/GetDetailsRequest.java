package com.cnk.siapi.model.getdetails;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;

public class GetDetailsRequest 
{
	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";
	
	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;
	
	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private GetDetailsRQBody getDetailsBody;

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

	public GetDetailsRQBody getGetDetailsBody() {
		return getDetailsBody;
	}

	public void setGetDetailsBody(GetDetailsRQBody getDetailsBody) {
		this.getDetailsBody = getDetailsBody;
	}

}
