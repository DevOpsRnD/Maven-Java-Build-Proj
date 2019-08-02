package com.cnk.siapi.model.getpolicy;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;

public class GetPolicyRequest {

	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";
	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;
	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private GetPolicyRQBody getPolicyBody;

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

	public GetPolicyRQBody getGetPolicyBody() {
		return getPolicyBody;
	}

	public void setGetPolicyBody(GetPolicyRQBody getPolicyBody) {
		this.getPolicyBody = getPolicyBody;
	}

}
