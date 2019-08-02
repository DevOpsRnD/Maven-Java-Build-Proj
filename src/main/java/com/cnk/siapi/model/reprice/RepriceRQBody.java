package com.cnk.siapi.model.reprice;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class RepriceRQBody {

	@XmlPath("/OTA_TourActivityAvailRQWrapper/SupplierID/text()")
	private String supplierId;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/Sequence/text()")
	private String sequence = "1";
	
	@XmlTransient
	@XmlAttribute(required = true)
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
	@XmlElementWrapper(name= "RateList")
	private List<RepriceRQRateList> rateList;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/TotalCost/text()")
	private String totalCost;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details/Currency/text()")
	private String shippingCurrency;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/ID/text()")
	private String id;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/Reference/text()")
	private String reference;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/RateKey/text()")
	private String rateKey;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/TimeSlots/TimeSlot/@code")
	private String timeSlotCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/BasicInfo/TPA_Extensions/Activity_TPA/Nationality/text()")
	private String nationality;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Location/Address/CountryName/@Code")
	private String countryCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Location/Region/@RegionCode")
	private String regionCode;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Schedule/@StartPeriod")
	private String startDate;
	
	@XmlPath("//OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/Schedule/@EndPeriod")
	private String endDate;
	
	@XmlPath("/OTA_TourActivityAvailRQWrapper/OTA_TourActivityAvailRQ/TourActivity/ParticipantCount/")
	private List<RepriceRQParticipant> participantCount;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getPricingCurrency() {
		return pricingCurrency;
	}

	public void setPricingCurrency(String pricingCurrency) {
		this.pricingCurrency = pricingCurrency;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplierProductCode() {
		return supplierProductCode;
	}

	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}
	
	/*public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}*/

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getTourLanguageCode() {
		return tourLanguageCode;
	}

	public void setTourLanguageCode(String tourLanguageCode) {
		this.tourLanguageCode = tourLanguageCode;
	}

	public String getTourLanguageListCode() {
		return tourLanguageListCode;
	}

	public void setTourLanguageListCode(String tourLanguageListCode) {
		this.tourLanguageListCode = tourLanguageListCode;
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

	public List<RepriceRQRateList> getRateList() {
		return rateList;
	}

	public void setRateList(List<RepriceRQRateList> rateList) {
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

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getRateKey() {
		return rateKey;
	}

	public void setRateKey(String rateKey) {
		this.rateKey = rateKey;
	}

	public String getTimeSlotCode() {
		return timeSlotCode;
	}

	public void setTimeSlotCode(String timeSlotCode) {
		this.timeSlotCode = timeSlotCode;
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

	public List<RepriceRQParticipant> getParticipantCount() {
		return participantCount;
	}

	public void setParticipantCount(List<RepriceRQParticipant> participantCount) {
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
	

}

