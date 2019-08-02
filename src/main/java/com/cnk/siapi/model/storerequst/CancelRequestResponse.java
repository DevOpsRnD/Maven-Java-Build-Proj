package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.model.cancel.CancelRequest;
import com.cnk.siapi.response.CommonCancelRS;

@Document(collection = "cancelrequestresponse")
public class CancelRequestResponse {

	String accessToken;
	CancelRequest cancelRequest;
	CommonCancelRS cancelResponse;
	JSONObject originalResponse;
	Date creationDate;
	String creationBy;
	String ip;

	public CancelRequestResponse() {
		super();
	}


	public CancelRequestResponse(String accessToken, CancelRequest cancelRequest, CommonCancelRS cancelResponse,
			JSONObject originalResponse, Date creationDate, String creationBy, String ip) {
		super();
		this.accessToken = accessToken;
		this.cancelRequest = cancelRequest;
		this.cancelResponse = cancelResponse;
		this.originalResponse = originalResponse;
		this.creationDate = creationDate;
		this.creationBy = creationBy;
		this.ip = ip;
	}


	public CancelRequest getCancelRequest() {
		return cancelRequest;
	}


	public void setCancelRequest(CancelRequest cancelRequest) {
		this.cancelRequest = cancelRequest;
	}


	public CommonCancelRS getCancelResponse() {
		return cancelResponse;
	}


	public void setCancelResponse(CommonCancelRS cancelResponse) {
		this.cancelResponse = cancelResponse;
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