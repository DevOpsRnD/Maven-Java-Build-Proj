package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.model.getdetails.GetDetailsRequest;
import com.cnk.siapi.response.CommonGetDetailsRS;

@Document(collection = "getdetailsrequestresponse")
public class GetDetailsRequestResponse {

	String accessToken;
	GetDetailsRequest getdetailsRequest;
	CommonGetDetailsRS getdetailsResponse;
	JSONObject originalResponse;
	Date creationDate;
	String creationBy;
	String ip;
	String supplierId;

	public GetDetailsRequestResponse() {
		super();
	}

	

	public GetDetailsRequestResponse(String accessToken, GetDetailsRequest getdetailsRequest,
			CommonGetDetailsRS getdetailsResponse, JSONObject originalResponse, Date creationDate, String creationBy,
			String ip, String supplierId) {
		super();
		this.accessToken = accessToken;
		this.getdetailsRequest = getdetailsRequest;
		this.getdetailsResponse = getdetailsResponse;
		this.originalResponse = originalResponse;
		this.creationDate = creationDate;
		this.creationBy = creationBy;
		this.ip = ip;
		this.supplierId = supplierId;
	}



	public GetDetailsRequest getGetdetailsRequest() {
		return getdetailsRequest;
	}



	public void setGetdetailsRequest(GetDetailsRequest getdetailsRequest) {
		this.getdetailsRequest = getdetailsRequest;
	}



	public CommonGetDetailsRS getGetdetailsResponse() {
		return getdetailsResponse;
	}



	public void setGetdetailsResponse(CommonGetDetailsRS getdetailsResponse) {
		this.getdetailsResponse = getdetailsResponse;
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