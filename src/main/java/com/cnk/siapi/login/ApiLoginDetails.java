package com.cnk.siapi.login;

import java.util.List;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "apilogindetails")
public class ApiLoginDetails {

	@Column(unique = true)
	String userCode;
	
	String userFullName;
	
	@Column(unique = true)
	String username;
	
	String password;
	String email;
	List<String> authorities;
	
	public ApiLoginDetails() {
		super();
	}

	public ApiLoginDetails(String userCode, String userFullName, String username, String password, String email,
			List<String> authorities) {
		super();
		this.userCode = userCode;
		this.userFullName = userFullName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

}
