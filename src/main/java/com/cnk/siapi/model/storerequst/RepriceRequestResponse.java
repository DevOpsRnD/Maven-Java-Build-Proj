package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.model.reprice.RepriceRequest;
import com.cnk.siapi.response.CommonRepriceRS;

@Document(collection = "repricerequestresponse")
public class RepriceRequestResponse {

	String accessToken;
	RepriceRequest repriceRequest;
	CommonRepriceRS repriceResponse;
	JSONObject originalResponse;
	Date creationDate;
	String creationBy;
	String ip;
	String supplierId;

	public RepriceRequestResponse() {
		super();
	}


	public RepriceRequestResponse(String accessToken, RepriceRequest repriceRequest, CommonRepriceRS repriceResponse,
			JSONObject originalResponse, Date creationDate, String creationBy, String ip, String supplierId) {
		super();
		this.accessToken = accessToken;
		this.repriceRequest = repriceRequest;
		this.repriceResponse = repriceResponse;
		this.originalResponse = originalResponse;
		this.creationDate = creationDate;
		this.creationBy = creationBy;
		this.ip = ip;
		this.supplierId = supplierId;
	}


	public RepriceRequest getRepriceRequest() {
		return repriceRequest;
	}


	public void setRepriceRequest(RepriceRequest repriceRequest) {
		this.repriceRequest = repriceRequest;
	}


	public CommonRepriceRS getRepriceResponse() {
		return repriceResponse;
	}


	public void setRepriceResponse(CommonRepriceRS repriceResponse) {
		this.repriceResponse = repriceResponse;
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