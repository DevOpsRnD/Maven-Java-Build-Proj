package com.cnk.siapi.model.book;

import java.util.List;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQShipping 
{
	@XmlPath("ID/text()")
	private String shippingId;
	@XmlPath("OptionName/text()")
	private String optionName;
	@XmlPath("Details/text()")
	private String details;
	@XmlPath("ServiceFee/text()")
	private String serviceFee;
	@XmlPath("/ShippingAreas/RateList")
	private List<BookRQRates> rateList;
	@XmlPath("TotalCost/text()")
	private String totalCost;
	@XmlPath("Currency/text()")
	private String currency;
	
	
	
	public BookRQShipping() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookRQShipping(String shippingId, String optionName, String shippingDetails, String shippingServiceFee,
			List<BookRQRates> rateList, String totalCost, String currency) {
		super();
		this.shippingId = shippingId;
		this.optionName = optionName;
		this.details = shippingDetails;
		this.serviceFee = shippingServiceFee;
		this.rateList = rateList;
		this.totalCost = totalCost;
		this.currency = currency;
	}
	public String getShippingId() {
		return shippingId;
	}
	public void setShippingId(String shippingId) {
		this.shippingId = shippingId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String shippingDetails) {
		this.details = shippingDetails;
	}
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String shippingServiceFee) {
		this.serviceFee = shippingServiceFee;
	}
	public List<BookRQRates> getRateList() {
		return rateList;
	}
	public void setRateList(List<BookRQRates> rateList) {
		this.rateList = rateList;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	

}
