package com.cnk.siapi.model.master;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class StaticActivities 
{
	private int pageSize;
	private int pageNo;
	private String requestingSupplierCode;
	private List<String> cityCodes;
	private List<String> filterBySuppliers;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getRequestingSupplierCode() {
		return requestingSupplierCode;
	}
	public void setRequestingSupplierCode(String requestingSupplierCode) {
		this.requestingSupplierCode = requestingSupplierCode;
	}
	public List<String> getCityCodes() {
		return cityCodes;
	}
	public void setCityCodes(List<String> cityCodes) {
		this.cityCodes = cityCodes;
	}
	public List<String> getFilterBySuppliers() {
		return filterBySuppliers;
	}
	public void setFilterBySuppliers(List<String> filterBySuppliers) {
		this.filterBySuppliers = filterBySuppliers;
	}
	
	
}
