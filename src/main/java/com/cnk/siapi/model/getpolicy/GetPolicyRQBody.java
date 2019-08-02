package com.cnk.siapi.model.getpolicy;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlPath;


public class GetPolicyRQBody {

	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/SupplierID/text()")
	private String supplierId;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/Sequence/text()")
	private String sequence = "1";
	
	@XmlTransient
	private String accessCode;
	
	@XmlTransient
	private String requestCurrency;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/CountryCode/text()")
	private String countryCode;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/CityCode/text()")
	private String cityCode;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/ParticipantCount")
	private List<GetPolicyRQParticipant> participantCount;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/FromDate/text()")
	private String fromDate;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/EndDate/text()")
	private String endDate;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/CurrencyCode/text()")
	private String currencyCode;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/SupplierProductCode/text()")
	private String supplierProductCode;
	
	@XmlPath("/OTA_TourActivityCancellationPoliciesRQWrapper/OTA_TourActivityCancellationPoliciesRQ/CancellationPolicies/ReferenceCode/text()")
	private String referenceCode;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<GetPolicyRQParticipant> getParticipantCount() {
		return participantCount;
	}

	public void setParticipantCount(List<GetPolicyRQParticipant> participantCount) {
		this.participantCount = participantCount;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}

	public String getRequestCurrency() {
		return requestCurrency;
	}

	public void setRequestCurrency(String requestCurrency) {
		this.requestCurrency = requestCurrency;
	}
	
	
}
