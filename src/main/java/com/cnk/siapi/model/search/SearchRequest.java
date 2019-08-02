package com.cnk.siapi.model.search;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;

public class SearchRequest 
{
	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";
	
	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;
	
	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private SearchRQBody searchBody;

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

	public SearchRQBody getSearchBody() {
		return searchBody;
	}

	public void setSearchBody(SearchRQBody searchBody) {
		this.searchBody = searchBody;
	}

}
