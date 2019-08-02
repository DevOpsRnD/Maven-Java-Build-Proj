package com.cnk.siapi.model.search;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.NONE)
public class SearchRQBody 
{
	@XmlPath("/OTA_TourActivityAvailRQ/ProcessingInformation/@PricingCurrency")
	private String priceCurrency;

	@XmlPath("/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Nationality/text()")
	private String nationality;	
	
	@XmlPath("/OTA_TourActivityAvailRQ/TourActivity/Location/Address/CountryName/@Code")
	private String countryCode;
	
	@XmlPath("/OTA_TourActivityAvailRQ/TourActivity/Location/Region/@RegionCode")
	private String regionCode;
	
	private String supplierCode;
	
	@XmlPath("/OTA_TourActivityAvailRQ/TourActivity/Schedule/@StartPeriod")
	private String startDate;
	
	@XmlPath("/OTA_TourActivityAvailRQ/TourActivity/Schedule/@EndPeriod")
	private String endDate;
	
	@XmlPath("/OTA_TourActivityAvailRQ/TourActivity/ParticipantCount/")
	private List<SearchRQParticipant> participantCount;

	public SearchRQBody() {
		super();
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}


	public List<SearchRQParticipant> getParticipantCount() {
		return participantCount;
	}

	public void setParticipantCount(List<SearchRQParticipant> participantCount) {
		this.participantCount = participantCount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getPriceCurrency() {
		return priceCurrency;
	}

	public void setPriceCurrency(String priceCurrency) {
		this.priceCurrency = priceCurrency;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	

}
