package com.cnk.siapi.model.getdetails;

import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class GetDetailsRQBody 
{
	@XmlPath("/OTA_TourActivityAvailRQWrapper/SupplierID/text()")
	private String supplierId;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/Sequence/text()")
	private String sequence = "1";
	
	@XmlTransient
	private String accessCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/ProcessingInformation/@PricingCurrency")
	private String pricingCurrency;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/@SupplierProductCode")
	private String supplierProductCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/@SupplierBrandCode")
	private String supplierBrandCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/@Name")
	private String name;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/TourLanguage/@Code")
	private String tourLanguageCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/TourLanguage/@LanguageListCode")
	private String tourLanguageListCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/ID/text()")
	private String shippingSupplierID;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/OptionName/text()")
	private String optionName;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/Details/text()")
	private String shippingSupplierDetails;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/ServiceFee/text()")
	private String serviceFee;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/ShippingAreas/RateList")
	@XmlElementWrapper(name = "RateList")
	private List<GetDetailsRQRateList> rateList;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/TotalCost/text()")
	private String totalCost;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/Currency/text()")
	private String shippingCurrency;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/ID/text()")
	private String id;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/name/text()")
	private String supplierDetailName;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/Reference/text()")
	private String supplierReference;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/RateKey/text()")
	private String supplierRateKey;

	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Nationality/text()")
	private String nationality;
	
	@XmlPath("//OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Location/Address/CountryName/@Code")
	private String countryCode;
	
	@XmlPath("//OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Location/Region/@RegionCode")
	private String regionCode;
	
	@XmlPath("//OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Schedule/@StartPeriod")
	private String startDate;
	
	@XmlPath("//OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Schedule/@EndPeriod")
	private String endDate;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/ParticipantCount/")
	private List<GetdetailsRQParticipant> participantCount;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

/*	public String getSequence() {
		return sequence;
	}*/

	/*public void setSequence(String sequence) {
		this.sequence = sequence;
	}*/

	
	public String getPricingCurrency() {
		return pricingCurrency;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}
	
	public String getTourLanguageCode() {
		return tourLanguageCode;
	}

	public void setTourLanguageCode(String tourLanguageCode) {
		this.tourLanguageCode = tourLanguageCode;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String basicName) {
		this.name = basicName;
	}

	public String getShippingSupplierID() {
		return shippingSupplierID;
	}

	public void setShippingSupplierID(String shippingSupplierID) {
		this.shippingSupplierID = shippingSupplierID;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getShippingSupplierDetails() {
		return shippingSupplierDetails;
	}

	public void setShippingSupplierDetails(String shippingSupplierDetails) {
		this.shippingSupplierDetails = shippingSupplierDetails;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public List<GetDetailsRQRateList> getRateList() {
		return rateList;
	}

	public void setRateList(List<GetDetailsRQRateList> rateList) {
		this.rateList = rateList;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	public String getShippingCurrency() {
		return shippingCurrency;
	}

	public void setShippingCurrency(String shippingCurrency) {
		this.shippingCurrency = shippingCurrency;
	}

	public String getId() {
		return id;
	}

	public void setId(String supplierDetailsID) {
		this.id = supplierDetailsID;
	}

	public String getSupplierReference() {
		return supplierReference;
	}

	public void setSupplierReference(String supplierReference) {
		this.supplierReference = supplierReference;
	}

	public String getSupplierRateKey() {
		return supplierRateKey;
	}

	public void setSupplierRateKey(String supplierRateKey) {
		this.supplierRateKey = supplierRateKey;
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

	public List<GetdetailsRQParticipant> getParticipantCount() {
		return participantCount;
	}

	public void setParticipantCount(List<GetdetailsRQParticipant> participantCount) {
		this.participantCount = participantCount;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getSupplierBrandCode() {
		return supplierBrandCode;
	}

	public void setSupplierBrandCode(String supplierBrandCode) {
		this.supplierBrandCode = supplierBrandCode;
	}

	public String getSupplierDetailName() {
		return supplierDetailName;
	}

	public void setSupplierDetailName(String supplierDetailName) {
		this.supplierDetailName = supplierDetailName;
	}
	
}
