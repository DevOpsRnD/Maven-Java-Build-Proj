package com.cnk.siapi.supplier;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotBlank;
import org.json.JSONObject;

public class SupplierCredentials {

	@NotBlank
	@Column(unique = true)
	String supplierCode;
	String supplierName;
	
	JSONObject supplierHeaderDAO;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public JSONObject getSupplierHeaderDAO() {
		return supplierHeaderDAO;
	}

	public void setSupplierHeaderDAO(JSONObject supplierHeaderDAO) {
		this.supplierHeaderDAO = supplierHeaderDAO;
	}

	@Override
	public String toString() {
		return "SupplierCredentials [supplierCode=" + supplierCode + ", supplierName=" + supplierName
				+ ", supplierHeaderDAO=" + supplierHeaderDAO + "]";
	}
	
}
