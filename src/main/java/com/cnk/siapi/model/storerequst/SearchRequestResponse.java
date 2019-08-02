package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "searchrequestresponse")
public class SearchRequestResponse {

	String accessToken;
	JSONObject originalResponse;
	JSONObject searchRequest;
	JSONObject searchResponse;
	String regionCode;
	String countryCode;
	String startDate;
	String endDate;
	Date creationDate;
	String creationBy;
	String ip;

	public SearchRequestResponse() {
		super();
	}

	public SearchRequestResponse(String accessToken, JSONObject originalResponse, JSONObject searchRequest,
			JSONObject searchResponse, String regionCode, String countryCode, String startDate, String endDate,
			Date creationDate, String creationBy, String ip) {
		super();
		this.accessToken = accessToken;
		this.originalResponse = originalResponse;
		this.searchRequest = searchRequest;
		this.searchResponse = searchResponse;
		this.regionCode = regionCode;
		this.countryCode = countryCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.creationDate = creationDate;
		this.creationBy = creationBy;
		this.ip = ip;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public JSONObject getOriginalResponse() {
		return originalResponse;
	}

	public void setOriginalResponse(JSONObject originalResponse) {
		this.originalResponse = originalResponse;
	}

	public JSONObject getSearchRequest() {
		return searchRequest;
	}

	public void setSearchRequest(JSONObject searchRequest) {
		this.searchRequest = searchRequest;
	}

	public JSONObject getSearchResponse() {
		return searchResponse;
	}

	public void setSearchResponse(JSONObject searchResponse) {
		this.searchResponse = searchResponse;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreationBy() {
		return creationBy;
	}

	public void setCreationBy(String creationBy) {
		this.creationBy = creationBy;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}