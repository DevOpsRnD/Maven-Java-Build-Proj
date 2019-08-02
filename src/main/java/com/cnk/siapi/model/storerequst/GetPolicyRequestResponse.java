package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.model.getpolicy.GetPolicyRequest;
import com.cnk.siapi.response.CommonGetPolicyRS;

@Document(collection = "getpolicyrequestresponse")
public class GetPolicyRequestResponse {

	String accessToken;
	GetPolicyRequest getpolicyRequest;
	CommonGetPolicyRS getpolicyResponse;
	JSONObject originalResponse;
	Date creationDate;
	String creationBy;
	String ip;
	String supplierId;

	public GetPolicyRequestResponse() {
		super();
	}


	public GetPolicyRequestResponse(String accessToken, GetPolicyRequest getpolicyRequest,
			CommonGetPolicyRS getpolicyResponse, JSONObject originalResponse, Date creationDate, String creationBy,
			String ip, String supplierId) {
		super();
		this.accessToken = accessToken;
		this.getpolicyRequest = getpolicyRequest;
		this.getpolicyResponse = getpolicyResponse;
		this.originalResponse = originalResponse;
		this.creationDate = creationDate;
		this.creationBy = creationBy;
		this.ip = ip;
		this.supplierId = supplierId;
	}


	public GetPolicyRequest getGetpolicyRequest() {
		return getpolicyRequest;
	}


	public void setGetpolicyRequest(GetPolicyRequest getpolicyRequest) {
		this.getpolicyRequest = getpolicyRequest;
	}


	public CommonGetPolicyRS getGetpolicyResponse() {
		return getpolicyResponse;
	}


	public void setGetpolicyResponse(CommonGetPolicyRS getpolicyResponse) {
		this.getpolicyResponse = getpolicyResponse;
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

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
}