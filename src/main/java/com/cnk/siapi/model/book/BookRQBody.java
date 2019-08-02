package com.cnk.siapi.model.book;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlPath;


public class BookRQBody 
{
	@XmlPath("OTA_TourActivityBookRQWrapper/SupplierID/text()")
	private String supplierID;
	@XmlPath("OTA_TourActivityBookRQWrapper/Sequence/text()")
	private String sequence = "1";
	@XmlTransient
	private String accessCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/POS/Source/@ISOCurrency")
	private String isoCurrency;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/@BirthDate")
	private String contactBirthDate;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/PersonName/GivenName/text()")
	private String contactFirstName;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/PersonName/MiddleName/text()")
	private String contactMiddleName;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/PersonName/Surname/text()")
	private String contactSurname;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/PersonName/NameTitle/text()")
	private String contactTitle;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/Telephone/@CountryAccessCode")
	private String contactTelCountryCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/Telephone/@PhoneNumber")
	private String contactPhoneNumber;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/Address/BldgRoom/text()")
	private String contactAddressBldgRoom;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/Address/AddressLine/text()")
	private String contactAddressLine;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/Address/CountryName/@Code")
	private String contactAddressCountryCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/ContactDetail/Email/text()")
	private String contactEmail;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/@SupplierProductCode")
	private String supplierProductCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/@SupplierBrandCode")
	private String supplierBrandCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/@Name")
	private String activityName;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Shipping_Details")
	private List<BookRQShipping> shippingDetails;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/CancellationPolicy")
	private List<BookRQCancelPolicy> cancellationPolicy;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/name/text()")
	private String name;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/ID/text()")
	private String id;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/Reference/text()")
	private String reference;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Supplier_Details/RateKey/text()")
	private String rate;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Availability_Status/text()")
	private String availabilityStatus;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/TourLanguage/@Code")
	private String tourLanguageCode;	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/TourLanguage/@LanguageListCode")
	private String tourLanguageListCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/TimeSlots/TimeSlot/@code")
	private String timeSlotCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/TimeSlots/TimeSlot/@startTime")
	private String startTime;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/POS/Agent_Name/text()")
	private String agentName;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/POS/Email/text()")
	private String agentEmail;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/POS/Phone/text()")
	private String agentPhone;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/POS/Country/text()")
	private String agentCountry;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/POS/Source/text()")
	private String agentSource;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Answers/Answer")
	private List<BookRQBodyAnswers> answers;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/BasicInfo/TPA_Extensions/Activity_TPA/Addons/")
	private List<BookRQAddons> addons;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/ParticipantInfo/")
	private List<BookRQBodyParticipant> participantInfo;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Schedule/@Start")
	private String scheduledStartDate;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Schedule/@End")
	private String scheduledEndDate;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Location/Address/CountryName/@Code")
	private String countryCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Location/Region/@RegionCode")
	private String regionCode;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/PickupDropoff/@DateTime")
	private String pickUpDateTime;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/PickupDropoff/@LocationName")
	private String pickUpLocation;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/PickupDropoff/@OtherInfo")
	private String pickUpOtherInfo;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Pricing/Summary/@Amount")
	private String pricingSummaryAmount;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Pricing/Summary/@CurrencyCode")
	private String pricingCurrencyCode;	
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Pricing/Summary/TaxAmounts/TaxAmount/@Total")
	private String taxAmount;
	@XmlPath("OTA_TourActivityBookRQWrapper/OTA_TourActivityBookRQ/BookingInfo/Pricing/ParticipantCategory")
	private List<BookRQParticipantCategory> participantCategory;
	
	
	public BookRQBody() {
		super();
	}
	
	public BookRQBody(String supplierID, String sequence, String accessCode, String isoCurrency,
			String contactBirthDate, String contactFirstName, String contactMiddleName, String contactSurname,
			String contactTitle, String contactTelCountryCode, String contactPhoneNumber, String contactAddressBldgRoom,
			String contactAddressLine, String contactAddressCountryCode, String contactEmail,
			String supplierProductCode, String supplierBrandCode, String activityName,
			List<BookRQShipping> shippingDetails, List<BookRQCancelPolicy> cancellationPolicy, String name, String id,
			String reference, String rate, String tourLanguageCode, String tourLanguageListCode, String timeSlotCode,
			String startTime, String agentName, String agentEmail, String agentPhone, String agentCountry,
			String agentSource, List<BookRQBodyAnswers> answers, List<BookRQBodyParticipant> participantInfo,
			String scheduledStartDate, String scheduledEndDate, String countryCode, String regionCode,
			String pickUpDateTime, String pickUpLocation, String pickUpOtherInfo, String pricingSummaryAmount,
			String pricingCurrencyCode, String taxAmount, List<BookRQParticipantCategory> participantCategory) {
		super();
		this.supplierID = supplierID;
		this.sequence = sequence;
		this.accessCode = accessCode;
		this.isoCurrency = isoCurrency;
		this.contactBirthDate = contactBirthDate;
		this.contactFirstName = contactFirstName;
		this.contactMiddleName = contactMiddleName;
		this.contactSurname = contactSurname;
		this.contactTitle = contactTitle;
		this.contactTelCountryCode = contactTelCountryCode;
		this.contactPhoneNumber = contactPhoneNumber;
		this.contactAddressBldgRoom = contactAddressBldgRoom;
		this.contactAddressLine = contactAddressLine;
		this.contactAddressCountryCode = contactAddressCountryCode;
		this.contactEmail = contactEmail;
		this.supplierProductCode = supplierProductCode;
		this.supplierBrandCode = supplierBrandCode;
		this.activityName = activityName;
		this.shippingDetails = shippingDetails;
		this.cancellationPolicy = cancellationPolicy;
		this.name = name;
		this.id = id;
		this.reference = reference;
		this.rate = rate;
		this.tourLanguageCode = tourLanguageCode;
		this.tourLanguageListCode = tourLanguageListCode;
		this.timeSlotCode = timeSlotCode;
		this.startTime = startTime;
		this.agentName = agentName;
		this.agentEmail = agentEmail;
		this.agentPhone = agentPhone;
		this.agentCountry = agentCountry;
		this.agentSource = agentSource;
		this.answers = answers;
		this.participantInfo = participantInfo;
		this.scheduledStartDate = scheduledStartDate;
		this.scheduledEndDate = scheduledEndDate;
		this.countryCode = countryCode;
		this.regionCode = regionCode;
		this.pickUpDateTime = pickUpDateTime;
		this.pickUpLocation = pickUpLocation;
		this.pickUpOtherInfo = pickUpOtherInfo;
		this.pricingSummaryAmount = pricingSummaryAmount;
		this.pricingCurrencyCode = pricingCurrencyCode;
		this.taxAmount = taxAmount;
		this.participantCategory = participantCategory;
	}
	public String getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	/*public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}*/
	public String getIsoCurrency() {
		return isoCurrency;
	}
	public void setIsoCurrency(String isoCurrency) {
		this.isoCurrency = isoCurrency;
	}
	public String getContactBirthDate() {
		return contactBirthDate;
	}
	public void setContactBirthDate(String contactBirthDate) {
		this.contactBirthDate = contactBirthDate;
	}
	public String getContactFirstName() {
		return contactFirstName;
	}
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	public String getContactMiddleName() {
		return contactMiddleName;
	}
	public void setContactMiddleName(String contactMiddleName) {
		this.contactMiddleName = contactMiddleName;
	}
	public String getContactSurname() {
		return contactSurname;
	}
	public void setContactSurname(String contactSurname) {
		this.contactSurname = contactSurname;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public String getContactTelCountryCode() {
		return contactTelCountryCode;
	}
	public void setContactTelCountryCode(String contactTelCountryCode) {
		this.contactTelCountryCode = contactTelCountryCode;
	}
	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}
	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}
	public String getContactAddressBldgRoom() {
		return contactAddressBldgRoom;
	}
	public void setContactAddressBldgRoom(String contactAddressBldgRoom) {
		this.contactAddressBldgRoom = contactAddressBldgRoom;
	}
	public String getContactAddressLine() {
		return contactAddressLine;
	}
	public void setContactAddressLine(String contactAddressLine) {
		this.contactAddressLine = contactAddressLine;
	}
	public String getContactAddressCountryCode() {
		return contactAddressCountryCode;
	}
	public void setContactAddressCountryCode(String contactAddressCountryCode) {
		this.contactAddressCountryCode = contactAddressCountryCode;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getSupplierProductCode() {
		return supplierProductCode;
	}
	public void setSupplierProductCode(String supplierProductCode) {
		this.supplierProductCode = supplierProductCode;
	}
	public String getSupplierBrandCode() {
		return supplierBrandCode;
	}
	public void setSupplierBrandCode(String supplierBrandCode) {
		this.supplierBrandCode = supplierBrandCode;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public List<BookRQShipping> getShippingDetails() {
		return shippingDetails;
	}
	public void setShippingDetails(List<BookRQShipping> shippingDetails) {
		this.shippingDetails = shippingDetails;
	}
	public List<BookRQCancelPolicy> getCancellationPolicy() {
		return cancellationPolicy;
	}
	public void setCancellationPolicy(List<BookRQCancelPolicy> cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
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
	public String getTimeSlotCode() {
		return timeSlotCode;
	}
	public void setTimeSlotCode(String timeSlotCode) {
		this.timeSlotCode = timeSlotCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentEmail() {
		return agentEmail;
	}
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public String getAgentCountry() {
		return agentCountry;
	}
	public void setAgentCountry(String agentCountry) {
		this.agentCountry = agentCountry;
	}
	public String getAgentSource() {
		return agentSource;
	}
	public void setAgentSource(String agentSource) {
		this.agentSource = agentSource;
	}
	public List<BookRQAddons> getAddons() {
		return addons;
	}
	public void setAddons(List<BookRQAddons> addons) {
		this.addons = addons;
	}
	public List<BookRQBodyAnswers> getAnswers() {
		return answers;
	}
	public void setAnswers(List<BookRQBodyAnswers> answers) {
		this.answers = answers;
	}
	public List<BookRQBodyParticipant> getParticipantInfo() {
		return participantInfo;
	}
	public void setParticipantInfo(List<BookRQBodyParticipant> participantInfo) {
		this.participantInfo = participantInfo;
	}
	public String getScheduledStartDate() {
		return scheduledStartDate;
	}
	public void setScheduledStartDate(String scheduledStartDate) {
		this.scheduledStartDate = scheduledStartDate;
	}
	public String getScheduledEndDate() {
		return scheduledEndDate;
	}
	public void setScheduledEndDate(String scheduledEndDate) {
		this.scheduledEndDate = scheduledEndDate;
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
	public String getPickUpDateTime() {
		return pickUpDateTime;
	}
	public void setPickUpDateTime(String pickUpDateTime) {
		this.pickUpDateTime = pickUpDateTime;
	}
	public String getPickUpLocation() {
		return pickUpLocation;
	}
	public void setPickUpLocation(String pickUpLocation) {
		this.pickUpLocation = pickUpLocation;
	}
	public String getPickUpOtherInfo() {
		return pickUpOtherInfo;
	}
	public void setPickUpOtherInfo(String pickUpOtherInfo) {
		this.pickUpOtherInfo = pickUpOtherInfo;
	}
	public String getPricingSummaryAmount() {
		return pricingSummaryAmount;
	}
	public void setPricingSummaryAmount(String pricingSummaryAmount) {
		this.pricingSummaryAmount = pricingSummaryAmount;
	}
	public String getPricingCurrencyCode() {
		return pricingCurrencyCode;
	}
	public void setPricingCurrencyCode(String pricingCurrencyCode) {
		this.pricingCurrencyCode = pricingCurrencyCode;
	}
	public String getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(String taxAmount) {
		this.taxAmount = taxAmount;
	}
	public List<BookRQParticipantCategory> getParticipantCategory() {
		return participantCategory;
	}
	public void setParticipantCategory(List<BookRQParticipantCategory> participantCategory) {
		this.participantCategory = participantCategory;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getAvailabilityStatus() {
		return availabilityStatus;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

}