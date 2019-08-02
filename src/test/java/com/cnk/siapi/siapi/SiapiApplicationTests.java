package com.cnk.siapi.siapi;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cnk.siapi.SiapiApplication;
import com.cnk.siapi.model.book.BookRQBody;
import com.cnk.siapi.model.book.BookRQBodyParticipant;
import com.cnk.siapi.model.book.BookRQParticipantCategory;
import com.cnk.siapi.model.cancel.CancelRQBody;
import com.cnk.siapi.model.cancel.CancelRQConfirmationInfo;
import com.cnk.siapi.model.getdetails.GetDetailsRQBody;
import com.cnk.siapi.model.getdetails.GetdetailsRQParticipant;
import com.cnk.siapi.model.getpolicy.GetPolicyRQBody;
import com.cnk.siapi.model.getpolicy.GetPolicyRQParticipant;
import com.cnk.siapi.model.master.StaticActivities;
import com.cnk.siapi.model.reprice.RepriceRQBody;
import com.cnk.siapi.model.reprice.RepriceRQParticipant;
import com.cnk.siapi.model.search.SearchRQBody;
import com.cnk.siapi.model.search.SearchRQParticipant;
import com.cnk.siapi.siapi.utils.ModelDescription;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SiapiApplication.class)
@WebAppConfiguration
public class SiapiApplicationTests 
{
	
	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(documentationConfiguration(restDocumentation)
                )
                .build();
    }


	
   @Test
    public void countries() throws Exception {    	
    	  mockMvc.perform(RestDocumentationRequestBuilders.get("/sightseeingmaster/getcountry?countryCode=SL")
                  .accept(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.status().isOk())
                  .andDo(MockMvcRestDocumentation.document("countries",
                          PayloadDocumentation.responseFields(PayloadDocumentation.fieldWithPath("[]")
                                  .description("Get the list of all Countries with Country Code")).andWithPrefix("[]", ModelDescription.getCountries())));
    }
    
    @Test
    public void cities() throws Exception {    	
    	  mockMvc.perform(RestDocumentationRequestBuilders.get("/sightseeingmaster/getcities?countryCode=SL")
                  .accept(MediaType.APPLICATION_JSON))
                  .andExpect(MockMvcResultMatchers.status().isOk())
                  .andDo(MockMvcRestDocumentation.document("cities",
                          PayloadDocumentation.responseFields(PayloadDocumentation.fieldWithPath("[]")
                                  .description("Get the list of all Cities")).andWithPrefix("[]", ModelDescription.getCities())));
    }
    
    @Test
    public void staticData() throws Exception
    {
    	List<String> cityList = new ArrayList<String>();
    	cityList.add("IND-NWDL000");
    	StaticActivities staticData = new StaticActivities();
    	staticData.setPageNo(1);
    	staticData.setPageSize(1);
    	staticData.setCityCodes(cityList);
    	
    	objectMapper = new ObjectMapper();
    	String content = objectMapper.writeValueAsString(staticData);
    	

		mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeingmaster/getstaticactivity")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("staticdata"));
//                		,PayloadDocumentation.responseFields(ModelDescription.getStaticActivities())));
		 
    }
    
    @Test
    public void searchRequest() throws Exception
    {
    	SearchRQBody searchBody = new SearchRQBody();
     
        searchBody.setCountryCode("UK");
        searchBody.setRegionCode("UK-LNDN008");
        searchBody.setStartDate("2018-06-20");
        searchBody.setEndDate("2018-06-20");
        searchBody.setPriceCurrency("EUR");
        SearchRQParticipant participant = new SearchRQParticipant();
        participant.setAge(35);
        participant.setQualifierInfo("Adult");
        participant.setQuantity(1);
        
        SearchRQParticipant participant2 = new SearchRQParticipant();
        participant2.setAge(05);
        participant2.setQualifierInfo("Child");
        participant2.setQuantity(1);
        
        List<SearchRQParticipant> participantL = new ArrayList<SearchRQParticipant>();
        participantL.add(participant);
        participantL.add(participant2);
        
        searchBody.setParticipantCount(participantL);
	        
    	objectMapper = new ObjectMapper();
    	String content =  objectMapper.writeValueAsString(searchBody);
    	
    	mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeing/search")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("search",PayloadDocumentation.requestFields(ModelDescription.getSearchInformation())));
    		
    }
    
    @Test
    public void getDetailsRequests() throws Exception
    {
    	GetDetailsRQBody getDetails = new GetDetailsRQBody();
    	
    	getDetails.setAccessCode("CKIS601U37NYW0660W");
    	getDetails.setCountryCode("UK");
    	getDetails.setEndDate("2018-06-20");
    	getDetails.setName("HARD ROCK CAFE - MENU GOLD");
    	GetdetailsRQParticipant participant = new GetdetailsRQParticipant();
        participant.setAge(35);
        participant.setQualifierInfo("Adult");
        participant.setQuantity(1);
        
        GetdetailsRQParticipant participant2 = new GetdetailsRQParticipant();
        participant2.setAge(05);
        participant2.setQualifierInfo("Child");
        participant2.setQuantity(1);
        
        List<GetdetailsRQParticipant> participantL = new ArrayList<GetdetailsRQParticipant>();
        participantL.add(participant);
        participantL.add(participant2);
        
        getDetails.setParticipantCount(participantL);
        
        getDetails.setRegionCode("UK-LNDN008");
        getDetails.setStartDate("2018-06-20");
        getDetails.setSupplierId("CNKWSLH-4");
        getDetails.setSupplierProductCode("UKLNDN0081972");
        getDetails.setTotalCost("");
        getDetails.setPricingCurrency("EUR");
        getDetails.setShippingSupplierID("");
        getDetails.setOptionName("");
        getDetails.setShippingSupplierDetails("");
        getDetails.setServiceFee("");
        getDetails.setShippingCurrency("");
        getDetails.setId("1972");
        getDetails.setSupplierReference("LOC.1972-101");
        getDetails.setSupplierRateKey("LOC.1972-101");
        
        objectMapper = new ObjectMapper();
    	String content =  objectMapper.writeValueAsString(getDetails);
    	
    	mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeing/getdetails")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("getdetails",PayloadDocumentation.requestFields(ModelDescription.getDetailsRequest())));
    		
        
    }
    
    @Test
    public void repriceRequest() throws Exception
    {
    	RepriceRQBody repriceBody = new RepriceRQBody();
    	
    	repriceBody.setAccessCode("CKIS601U37NYW0660W");
    	repriceBody.setCountryCode("UK");
    	repriceBody.setEndDate("2018-05-27");
    	repriceBody.setID("1972");
    	repriceBody.setName("HARD ROCK CAFE - MENU GOLD");
    	repriceBody.setOptionName("");
    	
    	RepriceRQParticipant participant = new RepriceRQParticipant();
        participant.setAge("35");
        participant.setQualifierInfo("Adult");
        participant.setQuantity("1");
        
        RepriceRQParticipant participant2 = new RepriceRQParticipant();
        participant2.setAge("05");
        participant2.setQualifierInfo("Child");
        participant2.setQuantity("1");
        
        List<RepriceRQParticipant> participantL = new ArrayList<RepriceRQParticipant>();
        participantL.add(participant);
        participantL.add(participant2);
        
        repriceBody.setParticipantCount(participantL);

        repriceBody.setPricingCurrency("EUR");
        repriceBody.setRateKey("LOC.1972-101");
        repriceBody.setReference("LOC.1972-101");
        repriceBody.setRegionCode("UK-LNDN008");
        repriceBody.setServiceFee("");
        repriceBody.setShippingCurrency("");
        repriceBody.setShippingSupplierDetails("");
        repriceBody.setShippingSupplierID("1972");
        repriceBody.setStartDate("2018-05-25");
        repriceBody.setSupplierId("CNKWSLH-4");
        repriceBody.setSupplierProductCode("UKLNDN0081972");
        repriceBody.setTimeSlotCode("6884");
        repriceBody.setTotalCost("");
        repriceBody.setTourLanguageCode("en");
        repriceBody.setTourLanguageListCode("");
        
        
        objectMapper = new ObjectMapper();
    	String content =  objectMapper.writeValueAsString(repriceBody);
    	
    	mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeing/reprice")
    			.header("Authorization", "Basic YWRtaW46Y25rYWRtaW4xMjM=")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcRestDocumentation.document("reprice",PayloadDocumentation.requestFields(ModelDescription.repriceRequest()))); 		
    }
   
    @Test
    public void bookRequest() throws Exception
    {
    	BookRQBody bookRQ = new BookRQBody();
    	
    	bookRQ.setAccessCode("CKIS601U37NYW0660W");
    	bookRQ.setActivityName("HARD ROCK CAFE - MENU GOLD");    	

    	bookRQ.setAgentCountry("");
    	bookRQ.setAgentEmail("");
    	bookRQ.setAgentName("");
    	bookRQ.setAgentPhone("");
    	bookRQ.setAgentSource("");
    	bookRQ.setContactAddressBldgRoom("Test");
    	bookRQ.setContactAddressLine("TestCnK");
    	bookRQ.setContactBirthDate("1992-05-06");
    	bookRQ.setContactEmail("testds@test");
    	bookRQ.setContactFirstName("DS");
    	bookRQ.setContactMiddleName("S");
    	bookRQ.setContactSurname("S");
    	bookRQ.setContactTelCountryCode("+91");
    	bookRQ.setContactPhoneNumber("9874521454");
    	bookRQ.setContactAddressCountryCode("IN");
    	bookRQ.setContactTitle("Mr.");
    	bookRQ.setCountryCode("UK");
    	bookRQ.setId("");
    	bookRQ.setName("");
    	bookRQ.setIsoCurrency("EUR");
    	
    	BookRQParticipantCategory category = new BookRQParticipantCategory();
    	category.setPriceAmount(89.27);
    	category.setQualifierInfo("Adult");
    	
    	List<BookRQParticipantCategory> categoryL = new ArrayList<>();
    	categoryL.add(category);
    	
    	bookRQ.setParticipantCategory(categoryL);
    	
    	BookRQBodyParticipant participant = new BookRQBodyParticipant();
    	participant.setParticipantAge(25);
    	participant.setParticipantCategoryId("1");
    	participant.setParticipantGivenName("Ds");
    	participant.setParticipantMiddleName("S");
    	participant.setParticipantSurname("S");
    	participant.setParticipantTitle("Mr.");
    	participant.setParticipantPhoneCountryCode("+91");
    	participant.setParticipantTelephone("957415214");
    	participant.setParticipantQuantity(1);
    	participant.setParticipantQualifierInfo("Adult");
    	
    	List<BookRQBodyParticipant> participantL = new ArrayList<>();
    	participantL.add(participant);
    	
    	bookRQ.setParticipantInfo(participantL);
    	bookRQ.setPickUpDateTime("2018-05-25T00:00:00");
    	bookRQ.setPickUpLocation("");
    	bookRQ.setPickUpOtherInfo("");
    	bookRQ.setPricingCurrencyCode("EUR");
    	bookRQ.setPricingSummaryAmount("178.54");
    	bookRQ.setReference("LOC.1972-101");
    	bookRQ.setRate("LOC.1972-101");
    	bookRQ.setRegionCode("UK-LNDN008");
    	bookRQ.setScheduledStartDate("2018-06-20");
    	bookRQ.setScheduledEndDate("2018-06-20");
    	bookRQ.setStartTime("20:00:00");
    	bookRQ.setSupplierBrandCode("REST");
    	bookRQ.setSupplierID("CNKWSLH-4");
    	bookRQ.setSupplierProductCode("UKLNDN0081972");
    	bookRQ.setTimeSlotCode("6884");
    	bookRQ.setTourLanguageCode("");
    	bookRQ.setTourLanguageListCode("");
    	
    	objectMapper = new ObjectMapper();
     	String content =  objectMapper.writeValueAsString(bookRQ);
     	
     	mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeing/book")
                 .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(content))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andDo(MockMvcRestDocumentation.document("book", PayloadDocumentation.requestFields(ModelDescription.bookRequest()))); 		
     	}
    
    @Test
    public void getPolicyRequest() throws Exception
    {
    	GetPolicyRQBody getPolicy = new GetPolicyRQBody();
    	
    	getPolicy.setAccessCode("CKIS601U37NYW0660W");
    	getPolicy.setSupplierProductCode("UKLNDN0081972");
    	getPolicy.setCountryCode("UK");
    	getPolicy.setCityCode("UK-LNDN008");
    	getPolicy.setCurrencyCode("");
    	getPolicy.setFromDate("2018-06-20");
    	getPolicy.setEndDate("2018-06-20");
    	getPolicy.setSupplierId("CNKWSLH-4");
    	getPolicy.setReferenceCode("LOC.1972-101");
 
    	GetPolicyRQParticipant policyParticipant = new GetPolicyRQParticipant();
    	
    	policyParticipant.setAge("25");
    	policyParticipant.setQuantity("1");
    	policyParticipant.setQualifierInfo("Adult");
    	
    	List<GetPolicyRQParticipant> participantL = new ArrayList<GetPolicyRQParticipant>();
    	getPolicy.setParticipantCount(participantL);
    	
    	objectMapper = new ObjectMapper();
     	String content =  objectMapper.writeValueAsString(getPolicy);
     	
     	mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeing/getpolicy")
                 .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(content))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andDo(MockMvcRestDocumentation.document("getpolicy", PayloadDocumentation.requestFields(ModelDescription.getPolicyRequest()))); 
 
    	
    }
    
    @Test
    public void getCancelRequest() throws Exception
    {
    	CancelRQBody cancelBody = new CancelRQBody();
    	
    	cancelBody.setAccessCode("CKIS601U37NYW0660W");
    	cancelBody.setISOCurrency("");
    	cancelBody.setSupplierId("CNKWSLH-4");
    	
    	CancelRQConfirmationInfo cancelConfirmation = new CancelRQConfirmationInfo();
    	cancelConfirmation.setConfirmationId("CKIS601U37NYW0660W-1");
    	cancelConfirmation.setConfirmationType("14");
    	cancelConfirmation.setConfirmationInstance("NA");
    	
    	List<CancelRQConfirmationInfo> cancelL = new ArrayList<CancelRQConfirmationInfo>();
    	cancelL.add(cancelConfirmation);
    	
    	cancelBody.setCancelConfirmationInfo(cancelL);
    	
    	objectMapper = new ObjectMapper();
     	String content =  objectMapper.writeValueAsString(cancelBody);
     	
     	mockMvc.perform(RestDocumentationRequestBuilders.post("/sightseeing/cancel")
                 .accept(MediaType.APPLICATION_JSON)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(content))
                 .andExpect(MockMvcResultMatchers.status().isOk())
                 .andDo(MockMvcRestDocumentation.document("cancel", PayloadDocumentation.requestFields(ModelDescription.cancelRequest()))); 
    }
    
	@Test
	public void contextLoads() {
	}

}
