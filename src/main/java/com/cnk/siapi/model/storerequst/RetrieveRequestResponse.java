package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.model.retrieve.RetrieveRequest;
import com.cnk.siapi.response.CommonRetrieveRS;

@Document(collection = "retrieverequestresponse")
public class RetrieveRequestResponse {

	String accessToken;
	RetrieveRequest retrieveRequest;
	CommonRetrieveRS retrieveResponse;
	JSONObject originalResponse;
	Date creationDate;
	String creationBy;
	String ip;

	public RetrieveRequestResponse() {
		super();
	}

	public RetrieveRequestResponse(String accessToken, RetrieveRequest retrieveRequest,
			CommonRetrieveRS retrieveResponse, JSONObject originalResponse, Date creationDate, String creationBy,
			String ip) {
		super();
		this.accessToken = accessToken;
		this.retrieveRequest = retrieveRequest;
		this.retrieveResponse = retrieveResponse;
		this.originalResponse = originalResponse;
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


	public RetrieveRequest getRetrieveRequest() {
		return retrieveRequest;
	}


	public void setRetrieveRequest(RetrieveRequest retrieveRequest) {
		this.retrieveRequest = retrieveRequest;
	}


	public CommonRetrieveRS getRetrieveResponse() {
		return retrieveResponse;
	}


	public void setRetrieveResponse(CommonRetrieveRS retrieveResponse) {
		this.retrieveResponse = retrieveResponse;
	}


	public JSONObject getOriginalResponse() {
		return originalResponse;
	}

	public void setOriginalResponse(JSONObject originalResponse) {
		this.originalResponse = originalResponse;
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