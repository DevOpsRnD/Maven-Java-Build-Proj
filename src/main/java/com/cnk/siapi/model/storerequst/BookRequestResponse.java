package com.cnk.siapi.model.storerequst;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.model.book.BookRequest;
import com.cnk.siapi.response.CommonBookRS;

@Document(collection = "bookrequestresponse")
public class BookRequestResponse {

	String accessToken;
	BookRequest bookRequest;
	CommonBookRS bookResponse;
	JSONObject originalResponse;
	String supplierProductCode;
	String supplierBrandCode;
	String activityName;
	String agentName;
	String countryCode;
	String regionCode;
	String bookId;
	Date creationDate;
	String creationBy;
	String ip;
	String supplierId;

	public BookRequestResponse() {
		super();
	}



	public BookRequestResponse(String accessToken, BookRequest bookRequest, CommonBookRS bookResponse,
			JSONObject originalResponse, String supplierProductCode, String supplierBrandCode, String activityName,
			String agentName, String countryCode, String regionCode, String bookId, Date creationDate,
			String creationBy, String ip, String supplierId) {
		super();
		this.accessToken = accessToken;
		this.bookRequest = bookRequest;
		this.bookResponse = bookResponse;
		this.originalResponse = originalResponse;
		this.supplierProductCode = supplierProductCode;
		this.supplierBrandCode = supplierBrandCode;
		this.activityName = activityName;
		this.agentName = agentName;
		this.countryCode = countryCode;
		this.regionCode = regionCode;
		this.bookId = bookId;
		this.creationDate = creationDate;
		this.creationBy = creationBy;
		this.ip = ip;
		this.supplierId = supplierId;
	}



	public BookRequest getBookRequest() {
		return bookRequest;
	}



	public void setBookRequest(BookRequest bookRequest) {
		this.bookRequest = bookRequest;
	}



	public CommonBookRS getBookResponse() {
		return bookResponse;
	}



	public void setBookResponse(CommonBookRS bookResponse) {
		this.bookResponse = bookResponse;
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

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public String getSupplierBrandCode() {
		return supplierBrandCode;
	}

	public void setSupplierBrandCode(String supplierBrandCode) {
		this.supplierBrandCode = supplierBrandCode;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
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