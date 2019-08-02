package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQAddon {

	@XmlPath("@inputType")
	private String addonInputType;
	@XmlPath("@code")
	private String addonCode;
	@XmlPath("@name")
	private String addonName;
	@XmlPath("@description")
	private String addonDescription;
	@XmlPath("@pricePerPerson")
	private String addonPricePerPerson;
	@XmlPath("@Quantity")
	private String addonQuantity;
	@XmlPath("@currency")
	private String addonCurrency;
	@XmlPath("@adultPrice")
	private String addonAdultPrice;
	@XmlPath("@childPrice")
	private String addonChildPrice;
	@XmlPath("@minIndividuals")
	private String addonMinIndividuals;
	@XmlPath("@maxIndividuals")
	private String addonMaxIndividuals;
	@XmlPath("@fromDate")
	private String addonFromDate;
	@XmlPath("@toDate")
	private String addonToDate;
	@XmlPath("@addonPrice")
	private String addonPrice;
	@XmlPath("@type")
	private String addonType;

	public BookRQAddon() {
		super();
	}

	public BookRQAddon(String addonInputType, String addonCode, String addonName, String addonDescription,
			String addonPricePerPerson, String addonQuantity, String addonCurrency, String addonAdultPrice,
			String addonChildPrice, String addonMinIndividuals, String addonMaxIndividuals, String addonFromDate,
			String addonToDate, String addonPrice, String addonType) {
		super();
		this.addonInputType = addonInputType;
		this.addonCode = addonCode;
		this.addonName = addonName;
		this.addonDescription = addonDescription;
		this.addonPricePerPerson = addonPricePerPerson;
		this.addonQuantity = addonQuantity;
		this.addonCurrency = addonCurrency;
		this.addonAdultPrice = addonAdultPrice;
		this.addonChildPrice = addonChildPrice;
		this.addonMinIndividuals = addonMinIndividuals;
		this.addonMaxIndividuals = addonMaxIndividuals;
		this.addonFromDate = addonFromDate;
		this.addonToDate = addonToDate;
		this.addonPrice = addonPrice;
		this.addonType = addonType;
	}

	public String getAddonInputType() {
		return addonInputType;
	}

	public void setAddonInputType(String addonInputType) {
		this.addonInputType = addonInputType;
	}

	public String getAddonCode() {
		return addonCode;
	}

	public void setAddonCode(String addonCode) {
		this.addonCode = addonCode;
	}

	public String getAddonName() {
		return addonName;
	}

	public void setAddonName(String addonName) {
		this.addonName = addonName;
	}

	public String getAddonDescription() {
		return addonDescription;
	}

	public void setAddonDescription(String addonDescription) {
		this.addonDescription = addonDescription;
	}

	public String getAddonPricePerPerson() {
		return addonPricePerPerson;
	}

	public void setAddonPricePerPerson(String addonPricePerPerson) {
		this.addonPricePerPerson = addonPricePerPerson;
	}

	public String getAddonQuantity() {
		return addonQuantity;
	}

	public void setAddonQuantity(String addonQuantity) {
		this.addonQuantity = addonQuantity;
	}

	public String getAddonCurrency() {
		return addonCurrency;
	}

	public void setAddonCurrency(String addonCurrency) {
		this.addonCurrency = addonCurrency;
	}

	public String getAddonAdultPrice() {
		return addonAdultPrice;
	}

	public void setAddonAdultPrice(String addonAdultPrice) {
		this.addonAdultPrice = addonAdultPrice;
	}

	public String getAddonChildPrice() {
		return addonChildPrice;
	}

	public void setAddonChildPrice(String addonChildPrice) {
		this.addonChildPrice = addonChildPrice;
	}

	public String getAddonMinIndividuals() {
		return addonMinIndividuals;
	}

	public void setAddonMinIndividuals(String addonMinIndividuals) {
		this.addonMinIndividuals = addonMinIndividuals;
	}

	public String getAddonMaxIndividuals() {
		return addonMaxIndividuals;
	}

	public void setAddonMaxIndividuals(String addonMaxIndividuals) {
		this.addonMaxIndividuals = addonMaxIndividuals;
	}

	public String getAddonFromDate() {
		return addonFromDate;
	}

	public void setAddonFromDate(String addonFromDate) {
		this.addonFromDate = addonFromDate;
	}

	public String getAddonToDate() {
		return addonToDate;
	}

	public void setAddonToDate(String addonToDate) {
		this.addonToDate = addonToDate;
	}

	public String getAddonPrice() {
		return addonPrice;
	}

	public void setAddonPrice(String addonPrice) {
		this.addonPrice = addonPrice;
	}

	public String getAddonType() {
		return addonType;
	}

	public void setAddonType(String addonType) {
		this.addonType = addonType;
	}

}