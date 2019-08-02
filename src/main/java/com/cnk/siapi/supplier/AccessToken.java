package com.cnk.siapi.supplier;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accesstoken")
public class AccessToken
{
	String accessToken;
	String supplierCode;
	String username;
	Date tokenExpireTime;
	
	public String getAccessToken() {
		return accessToken;
	}
	
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getSupplierCode() {
		return supplierCode;
	}
	
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	public Date getTokenExpireTime() {
		return tokenExpireTime;
	}

	public void setTokenExpireTime(Date tokenExpireTime) {
		this.tokenExpireTime = tokenExpireTime;
	}

	@Override
	public String toString() {
		return "AccessToken [accessToken=" + accessToken + ", supplierCode=" + supplierCode + ", username=" + username
				+ ", tokenExpireTime=" + tokenExpireTime + "]";
	}
	
}
