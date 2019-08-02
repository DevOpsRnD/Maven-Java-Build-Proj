package com.cnk.siapi.siapi.utils;

import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;

public class ModelDescription 
{

	 private ModelDescription(){}
	 
	 public static FieldDescriptor[] getCountries()
	 {
		 return new FieldDescriptor[]
				 {
						 PayloadDocumentation.fieldWithPath("countryName").description("Name of the Country"),
						 PayloadDocumentation.fieldWithPath("countryCode").description("Country Code in the System")
				 };
	 }
	 
	 public static FieldDescriptor[] getCities()
	 {
		 return new FieldDescriptor[]
				 {
						 PayloadDocumentation.fieldWithPath("cityName").description("City Name"),
						 PayloadDocumentation.fieldWithPath("cityCode").description("City Code"),
						 PayloadDocumentation.fieldWithPath("stateName").description("State Name"),
						 PayloadDocumentation.fieldWithPath("stateCode").description("State Code"),
						 PayloadDocumentation.fieldWithPath("countryName").description("Country Name"),
						 PayloadDocumentation.fieldWithPath("countryCode").description("Country Code")
				 };
	 }
	 public static FieldDescriptor[] getStaticActivities()
	 {
		 return new FieldDescriptor[]
				 {		
						 PayloadDocumentation.fieldWithPath("totalNumberOfActivities").description("Total Number of Activites Returned"),
						 PayloadDocumentation.fieldWithPath("pageSize").description("Total Page Size"),
						 PayloadDocumentation.fieldWithPath("currentPage").description("Current Page Number"),
						 PayloadDocumentation.fieldWithPath("totalPage").description("Number of Total Pages"),
						 PayloadDocumentation.fieldWithPath("activities[]").description("Activity content details"),
						 PayloadDocumentation.fieldWithPath("activities[].activityCode").description("Activity code of the Activity as shared by the Supplier"),
						 PayloadDocumentation.fieldWithPath("activities[].supplierCompanyCode").description("Company Code of the Supplier"),
						 PayloadDocumentation.fieldWithPath("activities[].supplierProductCode").description("Activity Product Code"),
						 PayloadDocumentation.fieldWithPath("activities[].category").description("Activity Category(s)"),
						 PayloadDocumentation.fieldWithPath("activities[].type").description("Type of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].subType").description("SubType of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].recommended").description("Is Activity Recommended"),
						 PayloadDocumentation.fieldWithPath("activities[].name").description("Name of the Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].description").description("Long Description of the Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].daysOfTheWeek").description("Days in which Activity can be taken."),
						 PayloadDocumentation.fieldWithPath("activities[].daysOfTheWeek[].supplierStartTime").description("Activity Start time suggested by supplier"),
						 PayloadDocumentation.fieldWithPath("activities[].daysOfTheWeek[].startTime").description("Actual Start Time of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].daysOfTheWeek[].supplierDuration").description("Duration of Activity suggested by supplier"),
						 PayloadDocumentation.fieldWithPath("activities[].daysOfTheWeek[].operatingFromDate").description("Date at which Activity has started"),
						 PayloadDocumentation.fieldWithPath("activities[].daysOfTheWeek[].operatingToDate").description("Date at which Activity will end"),
						 PayloadDocumentation.fieldWithPath("activities[].overview").description("Information About Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].countryName").description("Country Name of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].countryCode").description("Country Code of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].cityName").description("City Name of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].cityCode").description("City Code of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].activityMedia").description("Static Images of Activity"),
						 PayloadDocumentation.fieldWithPath("activities[].productOptions[]").description("Option Code of Activity in System"),
						 PayloadDocumentation.fieldWithPath("activities[].simliarProducts[]").description("Similar Type of Activities in the system")
						 	};
	 }
	 
	 public static FieldDescriptor[] getSearchInformation()
	 {
		 return new FieldDescriptor[]
				 {
						 PayloadDocumentation.fieldWithPath("priceCurrency").description("Preferred Currency for Transaction"),
						 PayloadDocumentation.fieldWithPath("nationality").description("Users Nationality. Used to return Amount as per the nationality. This is optional field"),
						 PayloadDocumentation.fieldWithPath("supplierCode").description("To be left blank"),
						 PayloadDocumentation.fieldWithPath("countryCode").description("Country Code for the Activity"),
						 PayloadDocumentation.fieldWithPath("regionCode").description("City Code/ Region Code for the Country"),
						 PayloadDocumentation.fieldWithPath("startDate").description("Start Date for the Activities to search ('YYYY-MM-DD')"),
						 PayloadDocumentation.fieldWithPath("endDate").description("End Date of the Activities ('YYYY-MM-DD')"),
						 PayloadDocumentation.fieldWithPath("participantCount").description("Information About people for Activities"),
						 PayloadDocumentation.fieldWithPath("participantCount[].quantity").description("Number of Paricipant. Value is Always 1"),
						 PayloadDocumentation.fieldWithPath("participantCount[].age").description("Age of Participant"),
						 PayloadDocumentation.fieldWithPath("participantCount[].qualifierInfo").description("Type of Participant. Adult or Child")
				 };
	 }
	 
	 public static FieldDescriptor[] getDetailsRequest()
	 {
		 return new FieldDescriptor[]
				 {
						 PayloadDocumentation.fieldWithPath("accessCode").description("It is return in Search Response and it a mandatory Field in Input"),
						 PayloadDocumentation.fieldWithPath("pricingCurrency").description("Currency Code of the Activity. Returned in Response of some Search Request. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("countryCode").description("Country Code of the country of Activity (Returned a Response of Search Request"),
						 PayloadDocumentation.fieldWithPath("endDate").description("End Date Of Activity. Returned in Response of Search Request ('YYYY-MM-DD')"),
						 PayloadDocumentation.fieldWithPath("id").description("Id of The Activity. Returned in Response of some Search Request. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("name").description("Name of the Activity on the Supplier Side.  If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("optionName").description("This is generally Passed a Blank."),
						 PayloadDocumentation.fieldWithPath("participantCount").description("Information About people for Activities"),
						 PayloadDocumentation.fieldWithPath("participantCount[].quantity").description("Number of Paricipant. Value is 1 if there is one participant of same age"),
						 PayloadDocumentation.fieldWithPath("participantCount[].age").description("Age of Participant"),
						 PayloadDocumentation.fieldWithPath("participantCount[].qualifierInfo").description("Type of Participant. Adult or Child"),
						 PayloadDocumentation.fieldWithPath("rateList").description("Rate List of the Activity. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("regionCode").description("Region Code/ City Code of the Activity of the City. Returned in Search Response"),
						 PayloadDocumentation.fieldWithPath("serviceFee").description("Service Fee of the Activity. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("shippingCurrency").description("Shipping Currency of the Activity. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("shippingSupplierDetails").description("Shipping Details of the Supplier Activity. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("shippingSupplierID").description("Shipping Supplier ID. If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("startDate").description("Start Date of the Activity. It is returned in the Response of the Search Request ('YYYY-MM-DD')"),
						 PayloadDocumentation.fieldWithPath("supplierId").description("Internal ID used for Reference Purpose. It is returned in the Response of the Search Request."),
						 PayloadDocumentation.fieldWithPath("supplierProductCode").description("Unique Activity Product Code used to Identify Activity in Supplier End. It is returned in the Response of the Search Request."),
						 PayloadDocumentation.fieldWithPath("supplierRateKey").description("Unique Identifier sent by Supplier for Identification. If data in Blank in Search Response, then pass Blank\""),
						 PayloadDocumentation.fieldWithPath("supplierReference").description("Unique Identifier sent by Supplier for Identification. If data in Blank in Search Response, then pass Blank\""),
						 PayloadDocumentation.fieldWithPath("totalCost").description("Total Activity Cost Including All Participants. It is returned in the Search Response of the Search Request."),
						 PayloadDocumentation.fieldWithPath("nationality").description("Users Nationality. Used to return Amount as per the nationality. This is optional field"),
						 PayloadDocumentation.fieldWithPath("supplierBrandCode").description("Unique code which needs to be passed for the supplier to identify the activity. Can get the data from GetDetails Response"),
						 PayloadDocumentation.fieldWithPath("supplierDetailName").description("Name of the Activity on the Supplier Side.  If data in Blank in Search Response, then pass Blank"),
						 PayloadDocumentation.fieldWithPath("tourLanguageCode").description("Tour Language of the Activity. It is returned in the GetDetails Response of the GetDetails Request."),
				 };
	 }

	public static FieldDescriptor[] repriceRequest() 
	{
		return new FieldDescriptor[]
				{
						PayloadDocumentation.fieldWithPath("accessCode").description("It is return in GetDetails Response and it a mandatory Field in Input"),
						PayloadDocumentation.fieldWithPath("countryCode").description("Country Code of the country of Activity (Returned a Response of GetDetails Request)"),
						PayloadDocumentation.fieldWithPath("endDate").description("End Date Of Activity. Returned in Response of GetDetails Request ('YYYY-MM-DD')"),
						PayloadDocumentation.fieldWithPath("id").description("Id of The Activity. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("name").description("Name of the Activity on the Supplier Side.  If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("optionName").description("This is generally Passed a Blank."),
						PayloadDocumentation.fieldWithPath("pricingCurrency").description("Currency Code of the Activity. Returned in Response of some GetDetails Request. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("participantCount").description("Information About people for Activities"),
						PayloadDocumentation.fieldWithPath("participantCount[].quantity").description("Number of Paricipant. Value is 1 if there is one participant of same age"),
						PayloadDocumentation.fieldWithPath("participantCount[].age").description("Age of Participant"),
						PayloadDocumentation.fieldWithPath("participantCount[].qualifierInfo").description("Type of Participant. Adult or Child"),
						PayloadDocumentation.fieldWithPath("rateKey").description("Unique Key used for the Activity Identification in Supplier End.If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("rateList").description("Rate List of the Activity. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("reference").description("Unique Key used for the Activity Identification in Supplier End.If data in Blank in GetDetails Response, then pass Blank"), 
						PayloadDocumentation.fieldWithPath("regionCode").description("Region Code/ City Code of the Activity of the City. Returned in GetDetails Response"),
						PayloadDocumentation.fieldWithPath("serviceFee").description("Service Fee of the Activity. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("shippingCurrency").description("Shipping Currency of the Activity. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("shippingSupplierDetails").description("Shipping Details of the Supplier Activity. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("shippingSupplierID").description("Shipping Supplier ID. If data in Blank in GetDetails Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("startDate").description("Start Date of the Activity. It is returned in the Response of the GetDetails Request ('YYYY-MM-DD')"),
						PayloadDocumentation.fieldWithPath("supplierId").description("Internal ID used for Reference Purpose. It is returned in the Response of the GetDetails Request."),
						PayloadDocumentation.fieldWithPath("supplierProductCode").description("Unique Activity Product Code used to Identify Activity in Supplier End. It is returned in the Response of the GetDetails Request."),
						PayloadDocumentation.fieldWithPath("timeSlotCode").description("Particular TimeSlot for the Activity. It is returned in the Response of the GetDetails Request."), 
						PayloadDocumentation.fieldWithPath("totalCost").description("Total Activity Cost Including All Participants. It is returned in the GetDetails Response of the GetDetails Request."),
						PayloadDocumentation.fieldWithPath("tourLanguageCode").description("Tour Language of the Activity. It is returned in the GetDetails Response of the GetDetails Request."),
						PayloadDocumentation.fieldWithPath("tourLanguageListCode").description("Tour Language Code of the Activity. It is returned in the GetDetails Response of the GetDetails Request."),
						PayloadDocumentation.fieldWithPath("supplierBrandCode").description("Unique code which needs to be passed for the supplier to identify the activity. Can get the data from GetDetails Response"),
						PayloadDocumentation.fieldWithPath("nationality").description("Optional field which can be passed to get the Amount based on the nationality")
				};
	}

	public static FieldDescriptor[] bookRequest()
	{
		// TODO Auto-generated method stub
		return new FieldDescriptor[]
				{
					PayloadDocumentation.fieldWithPath("accessCode").description("Unique Identification code for Operation. The value needs to be taken as input from Reprice Response"),
					PayloadDocumentation.fieldWithPath("activityName").description("Name of the Activity. The value needs to be taken from 'Name' in Basic Info section of Reprice Response"),
					PayloadDocumentation.fieldWithPath("agentCountry").description("Leave it as Blank"),
					PayloadDocumentation.fieldWithPath("agentEmail").description("Leave it as Blank"),
					PayloadDocumentation.fieldWithPath("agentName").description("Leave it as Blank"),
					PayloadDocumentation.fieldWithPath("agentPhone").description("Leave it as Blank"),
					PayloadDocumentation.fieldWithPath("agentSource").description("Leave it as Blank"),
					PayloadDocumentation.fieldWithPath("answers").description("Some of the Suppliers ask some information from user as Questions. Check the 'questions' tab in Reprice Response for Questions. These needs to be entered by User while Booking"),
					PayloadDocumentation.fieldWithPath("contactAddressBldgRoom").description("Address of the Contact"),
					PayloadDocumentation.fieldWithPath("contactAddressLine").description("Address of the Contact"),
					PayloadDocumentation.fieldWithPath("contactAddressCountryCode").description("Country Code of the Contact"),
					PayloadDocumentation.fieldWithPath("contactBirthDate").description("BirthDate of the Contact"),
					PayloadDocumentation.fieldWithPath("contactEmail").description("Email of the Contact"),
					PayloadDocumentation.fieldWithPath("contactTitle").description("Salutation of the Contact. Mr. or Mrs."),
					PayloadDocumentation.fieldWithPath("contactFirstName").description("FirstName of the Contact"),
					PayloadDocumentation.fieldWithPath("contactMiddleName").description("MiddleName of the Contact"),
					PayloadDocumentation.fieldWithPath("contactSurname").description("Surname of the Contact"),
					PayloadDocumentation.fieldWithPath("contactTelCountryCode").description("Phone Country Code of the Contact. E.g. +91 for India"),
					PayloadDocumentation.fieldWithPath("contactPhoneNumber").description("Phone Number of Contact."),
					PayloadDocumentation.fieldWithPath("countryCode").description("Country Code of Activity"),
					PayloadDocumentation.fieldWithPath("id").description("Unique ID for Activity. Should be taken from Supplier Details Section of Reprice Response. If value is blank, leave blank"),
					PayloadDocumentation.fieldWithPath("isoCurrency").description("Currency for the Transaction. Should be taken from currencyCode from Reprice Response. If value is blank, leave blank"),
					PayloadDocumentation.fieldWithPath("name").description("Unique Name for Activity at Supplier End. Should be taken from Supplier Details Section of Reprice Response. If value is blank, leave blank"),
					PayloadDocumentation.fieldWithPath("participantCategory[].priceAmount").description("Participant Wise Amount for the Activity. Should be taken from participantCategory Section of Reprice Response."),
					PayloadDocumentation.fieldWithPath("participantCategory[].qualifierInfo").description("Adult or Child. Should be taken from participantCategory Section of Reprice Response."),
					PayloadDocumentation.fieldWithPath("participantInfo").description("If there are more than one participant, repeat this block"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantAge").description("Age of the Participant."),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantCategoryId").description("Default 1"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantGivenName").description("First Name of the Participant"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantMiddleName").description("Middle Name of the Participant"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantSurname").description("Surname of the Participant"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantTitle").description("Mr. or Mrs."),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantQualifierInfo").description("Adult or Child. Can be taken from Response of GetDetails or Reprice"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantQuantity").description("Total Number of Participant in the given Information"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantPhoneCountryCode").description("Country Code of the Telephone Number. E.g. +91 for India"),
					PayloadDocumentation.fieldWithPath("participantInfo[].participantTelephone").description("Telephone Number of Participant"),
					PayloadDocumentation.fieldWithPath("pickUpDateTime").description("Should be taken from Reprice Response. If blank, leave it as blank"),
					PayloadDocumentation.fieldWithPath("pickUpLocation").description("Should be taken from Reprice Response. If blank, leave it as blank"),
					PayloadDocumentation.fieldWithPath("pickUpOtherInfo").description("Should be taken from Reprice Response. If blank, leave it as blank"),
					PayloadDocumentation.fieldWithPath("pricingCurrencyCode").description("Currency Code of the Amount which is given. Should be taken from pricing block in Reprice Response."),
					PayloadDocumentation.fieldWithPath("pricingSummaryAmount").description("Total Amount of the Activity. Should be taken from pricing block in Reprice Response."),
					PayloadDocumentation.fieldWithPath("rate").description("Unique Identifier at Supplier End. Should be taken from RateKey in supplierDetails block in Reprice Response."),
					PayloadDocumentation.fieldWithPath("reference").description("Unique Identifier at Supplier End. Should be taken from reference in supplierDetails block in Reprice Response."),
					PayloadDocumentation.fieldWithPath("regionCode").description("Region Code for the Activity. Can be taken from Reprice Response."),
					PayloadDocumentation.fieldWithPath("scheduledEndDate").description("End Date of the Activity. Should be taken from Reprice Response."),
					PayloadDocumentation.fieldWithPath("scheduledStartDate").description("Start Date of the Activity. Should be taken from Reprice Response."),
					PayloadDocumentation.fieldWithPath("shippingDetails").description("Shipping Details for the Bookings. If it is present in Reprice Response, then enter or leave it blank"),
					PayloadDocumentation.fieldWithPath("startTime").description("Activity Start Time. If it is present in Reprice Response, then enter or leave it blank"),
					PayloadDocumentation.fieldWithPath("supplierBrandCode").description("Unique Activity Code. If it is present in Reprice Response, then enter or leave it blank"),
					PayloadDocumentation.fieldWithPath("supplierID").description("Unique ID for Supplier. SupplierID field in Reprice Response"),
					PayloadDocumentation.fieldWithPath("supplierProductCode").description("Unique Product Code for Activity in Supplier End. Should be taken from Reprice Response"),
					PayloadDocumentation.fieldWithPath("taxAmount").description("Tax for the Activity. Should be taken from Reprice Response"),
					PayloadDocumentation.fieldWithPath("timeSlotCode").description("Unique Timeslot for the Activity. Should be taken from Reprice Response"),
					PayloadDocumentation.fieldWithPath("tourLanguageCode").description("Language code for the Activity. Should be taken from Reprice Response"),
					PayloadDocumentation.fieldWithPath("tourLanguageListCode").description("Unique Language code for the Activity. Should be taken from Reprice Response"),
					PayloadDocumentation.fieldWithPath("cancellationPolicy").description("Cancellation Policy from the supplier. Should be taken from Reprice Response"),
					PayloadDocumentation.fieldWithPath("addons").description("Addon information selected by the client"),
					PayloadDocumentation.fieldWithPath("sequence").description("To be left blank")
				};
	}
	
	public static FieldDescriptor[] getPolicyRequest()
	{
		// TODO Auto-generated method stub
		return new FieldDescriptor[]
				{
						PayloadDocumentation.fieldWithPath("accessCode").description("Unique Identification code for Operation. The value needs to be taken as input from Previous Response"),
						PayloadDocumentation.fieldWithPath("countryCode").description("Country Code of the country of Activity (Returned a Response of Previous Request)"),
						PayloadDocumentation.fieldWithPath("endDate").description("End Date Of Activity. Returned in Response of Previous Request"),
						PayloadDocumentation.fieldWithPath("fromDate").description("End Date Of Activity. Returned in Response of Previous Request"),
						PayloadDocumentation.fieldWithPath("participantCount").description("If there are more than one participant, repeat this block"),
						/*PayloadDocumentation.fieldWithPath("participantCount[].quantity").description("Number of Paricipant. Value is 1 if there is one participant of same age"),
						PayloadDocumentation.fieldWithPath("participantCount[].age").description("Age of Participant"),
						PayloadDocumentation.fieldWithPath("participantCount[].qualifierInfo").description("Type of Participant. Adult or Child"),
					*/	PayloadDocumentation.fieldWithPath("referenceCode").description("Unique Key used for the Activity Identification in Supplier End.If data in Blank in GetDetails Response, then pass Blank"), 
						PayloadDocumentation.fieldWithPath("cityCode").description("Region Code/ City Code of the Activity of the City. Returned in GetDetails Response"),
						PayloadDocumentation.fieldWithPath("supplierId").description("Unique Supplier ID to indentify the supplier. Returned in Response of Previous Request"),
						PayloadDocumentation.fieldWithPath("sequence").description("Should Always be left blank"),
						PayloadDocumentation.fieldWithPath("supplierProductCode").description("Unique Activity Product Code used to Identify Activity in Supplier End. It is returned in the Response of the Previous Request."),
						PayloadDocumentation.fieldWithPath("currencyCode").description("Currency Code of the Activity. Returned in Response of previous Request. If data is Blank in Previous Response, then pass Blank"),
				};
	}

	public static FieldDescriptor[] cancelRequest() {
		// TODO Auto-generated method stub
		return new FieldDescriptor[]
				{
						PayloadDocumentation.fieldWithPath("accessCode").description("Unique Identification code for Operation. The value needs to be taken as input from Previous Response"),
						PayloadDocumentation.fieldWithPath("isocurrency").description("Currency Code of the Activity. Returned in Response of previous Request. If data is Blank in Previous Response, then pass Blank"),
						PayloadDocumentation.fieldWithPath("supplierId").description("Unique Supplier ID to indentify the supplier. Returned in Response of Previous Request"),
						PayloadDocumentation.fieldWithPath("cancelConfirmationInfo").description("If multiple confirmation information is present, repeat this tag"),
						PayloadDocumentation.fieldWithPath("cancelConfirmationInfo[].confirmationType").description("Confirmation Type of Confirmation. 14 is Actual confirmation id, 17 is used for Reprice. We can get the values from booking response."),
						PayloadDocumentation.fieldWithPath("cancelConfirmationInfo[].confirmationId").description("Confirmation id to be cancelled. We can get the values from booking response."),
						PayloadDocumentation.fieldWithPath("cancelConfirmationInfo[].confirmationInstance").description("Confirmation Instance information. We can get the values from booking response."),
						
				};
	}
}
