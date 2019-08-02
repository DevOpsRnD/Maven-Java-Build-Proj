package com.cnk.siapi.supplier;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.common.MyResponseErrorHandler;
import com.cnk.siapi.model.book.BookingDetails;
import com.cnk.siapi.model.storerequst.BookRequestResponse;
import com.cnk.siapi.model.storerequst.GetPolicyRequestResponse;
import com.cnk.siapi.response.CommonGetPolicyRS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

@Service
public class SupplierOperations 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	Gson gson = new Gson();
	
	public Integer saveSupplierCredentials(SupplierCredentials supplierCredentials,String collectionName)
	{
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		SupplierCredentials supplierDetails = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYCODE, supplierCredentials.getSupplierCode(), collectionName);
		
		if(supplierDetails == null)	
		{
			mongoInstance.insert(supplierCredentials , collectionName);
			logger.info("Supplier credentials saved successgully");
			return 0;
		} else {
			logger.info("Supplier credentials is already present");
			return 1;
		}
	}
	
	@SuppressWarnings("deprecation")
	public JSONObject isTokenValid(String token, Date currentTime, String operation) throws JSONException
	{
		JSONObject jsonResponse = new JSONObject();
		AccessToken isTokenPresent =  loadClassesAuto.mongoDBOperation.findByAccessToken(token);
		
		if( isTokenPresent != null ) {
			if(operation.equalsIgnoreCase(Constants.VALID)) {
				Date tokenExpireTime = isTokenPresent.getTokenExpireTime();
				if( ( tokenExpireTime.getDate() >= currentTime.getDate() ) && ( tokenExpireTime.getTime() >= currentTime.getTime() )) 
				{
					jsonResponse.put(Constants.STATUS, true);
					jsonResponse.put(Constants.CODE, Constants.OK);
					jsonResponse.put(Constants.DESCRIPTION, "Valid Token");
					jsonResponse.put(Constants.RESPONSEDATA, isTokenPresent);
					logger.info("Valid Token");
				}else
				{
					jsonResponse.put(Constants.STATUS, false);
					jsonResponse.put(Constants.CODE, Constants.UNAUTHORIZED);
					jsonResponse.put(Constants.DESCRIPTION, "Token is already expired");
					logger.info("Token is already expired");
				}
			} else
			{
				jsonResponse.put(Constants.STATUS, true);
				jsonResponse.put(Constants.CODE, Constants.OK);
				jsonResponse.put(Constants.DESCRIPTION, "Token found");
			}
		} else 
		{
			jsonResponse.put(Constants.STATUS, false);
			jsonResponse.put(Constants.CODE, Constants.UNAUTHORIZED);
			jsonResponse.put(Constants.DESCRIPTION, "Invalid Token");
			logger.info("Invalid Token");
		}
			
		return jsonResponse;
	}
	
	public void refreshToken(String token)
	{
		long expMillis = System.currentTimeMillis() + 3_600_000;
		Date tokenExpiryTime = new Date(expMillis);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("accessToken").is(token));
		
		Update update = new Update();
		update.set("tokenExpireTime", tokenExpiryTime);
		
		loadClassesAuto.mongoConnection.getInstance(
				loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME)).findAndModify(query, update, AccessToken.class);
	}
	
	public boolean saveAccessToken(AccessToken accessToken)
	{
		try {
			AccessToken isTokenPresent = loadClassesAuto.mongoDBOperation.findByAccessToken(accessToken.getAccessToken());
			
			if(isTokenPresent == null ) {
				loadClassesAuto.mongoConnection.getInstance(
						loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME)).insert(accessToken);
				logger.info("Token saved successfully");
				return true;
			} else {
				logger.info("Token already present");
				return false;
			}
		}
		catch (Exception e) {
			logger.error("Failed to save Access Token", e);
			return false;
		}
	}
	
	public JSONObject getBookingData(String username,int limit,int offset) throws JSONException
	{
		boolean isAdmin = false;
		long totalCount = 0;
		Query query = new Query();
		JSONObject response = new JSONObject();
		List<BookingDetails> bookingDataList = null;
		
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
		isAdmin = getUserRole(username,mongoInstance);
		
		if(!isAdmin) 
			query.addCriteria(Criteria.where("username").is(username));
		totalCount =   mongoInstance.count(query, BookingDetails.class);
		
		query.skip(offset);
		query.limit(limit);
		bookingDataList = mongoInstance.find(query, BookingDetails.class);
			
		if(bookingDataList.size() > 0) {
			response.put(Constants.STATUS, Constants.SUCCESS);
			response.put(Constants.RESPONSEDATA, bookingDataList);
			response.put("totalcount", totalCount);
		} else {
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Booking details for supplier " + username + " not found");
		}
		
		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject updateBooking(String bookId,String userId) throws RestClientException, URISyntaxException, JSONException, ParseException 
	{
		
		logger.info("Book id : " +bookId );
		JSONObject response = new JSONObject();
		Update update = new Update();
		Query query = new Query();
 		boolean isAdmin = false;
		
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
		
		query.addCriteria(Criteria.where("bookingId").is(bookId));
		BookingDetails bookingDetails = mongoInstance.findOne(query, BookingDetails.class);
		
		if(bookingDetails != null) 
		{
			logger.info("access token : "+bookingDetails.getAccessToken());
			logger.info(bookingDetails);
			isAdmin = getUserRole(userId, mongoInstance);
			if(bookingDetails.getUsername().equalsIgnoreCase(userId) || isAdmin)
			{
				String accessCode = bookingDetails.getAccessToken();
				String currency = "";
				String supplierId = bookingDetails.getSupplierId();
				
				JSONArray confirmationArray = new JSONArray(bookingDetails.getOtherBookInfo());
				JSONArray uniqueIdDetailsArray = new JSONArray();
				
				for(int i=0;i<confirmationArray.length();i++) 
				{
					JSONObject uniqueIdDetailsJson = new JSONObject();
					uniqueIdDetailsJson.put("instance", confirmationArray.getJSONObject(i).get("instance"));
					uniqueIdDetailsJson.put("uniqueId", confirmationArray.getJSONObject(i).get("id"));
					uniqueIdDetailsJson.put("uniqueIdType", confirmationArray.getJSONObject(i).get("type"));
				
					uniqueIdDetailsArray.put(uniqueIdDetailsJson);
				}
				
				JSONObject requestJson = new JSONObject();
				requestJson.put("accessCode", accessCode);
				requestJson.put("isocurrency", currency);
				requestJson.put("sequence", "1");
				requestJson.put("supplierId", supplierId);
				requestJson.put("uniqueIdDetails", uniqueIdDetailsArray);
				
				String URL = loadClassesAuto.applicationProperties.getProperty(Constants.INTERNAL_RETRIEVE_URL);
				
				String plainCreds = "tejesh" + ":" + "tejesh@123"; 
				byte[] plainCredsBytes = plainCreds.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				RestTemplate template = new RestTemplate();
				template.setErrorHandler(new MyResponseErrorHandler());
				
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
				headers.add("Authorization", "Basic " + base64Creds);
				
				logger.info("Input request for manage book in admin panel : "+requestJson);
				
				HttpEntity<String> request = new HttpEntity(requestJson.toString(),headers);
				ResponseEntity<String> integrationResponse = template.exchange(new URI(URL), HttpMethod.POST, request, String.class);
	
				JSONObject responseJson =new JSONObject(integrationResponse.getBody());
				logger.info("responseJson : "+responseJson);
				String integrationStatus = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityResRetrieveRSIntWrapper").get("otatourActivityResRetrieveRS").toString().equalsIgnoreCase(Constants.NULL) ? 
						Constants.FALSE : responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityResRetrieveRSIntWrapper").getJSONObject("otatourActivityResRetrieveRS").getString("success");
				
				if(integrationStatus.equalsIgnoreCase(Constants.TRUE)) 
				{
					//Update Booking Status of specified Book id
					
					JSONArray updatedConfirmationArray = new JSONArray();
					updatedConfirmationArray = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityResRetrieveRSIntWrapper").getJSONObject("otatourActivityResRetrieveRS")
							.getJSONObject("detail").getJSONArray("confirmation");
					
					for(int i=0;i<updatedConfirmationArray.length();i++) 
					{
						if(updatedConfirmationArray.getJSONObject(i).getInt("type") == 14) {
							update.set("bookingStatus", updatedConfirmationArray.getJSONObject(i).getString("idcontext"));
						}
					}
					
					mongoInstance.findAndModify(query, update, BookingDetails.class);
					BookingDetails newBookingDetails = mongoInstance.findOne(query, BookingDetails.class);
					
					response.put(Constants.STATUS, Constants.SUCCESS);
					response.put("refresheddata", new JSONParser().parse(new Gson().toJson(newBookingDetails)));
				}
				else
				{
					JSONObject errorJson = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityResRetrieveRSIntWrapper").getJSONObject("errorList").getJSONObject("error");
					response.put(Constants.STATUS, Constants.FAILED);
					response.put(Constants.CODE, errorJson.getString("errorCode"));
					response.put(Constants.DESCRIPTION, errorJson.getString("errorMsg"));
				}
			}
			else {
				response.put(Constants.STATUS, Constants.FAILED);
				response.put(Constants.DESCRIPTION, "Access Denied. Insufficient rights to update details");
			}
		} 
		else {
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Invalid book id. Please provide proper book id");
		}
		
		return response;
	}
	
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	public ResponseEntity getPolicies(String bookId, String userId, HttpServletRequest request) throws RestClientException, URISyntaxException, JSONException, JsonProcessingException
	{
		int statusCode = 200;
		JSONObject response = new JSONObject();
		
		Query query = new Query();
		
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
		
		query.addCriteria(Criteria.where("bookingId").is(bookId));
		Document bookingDetails = mongoInstance.findOne(query, Document.class, "bookingdetails");
		logger.info("Booking Details " + bookingDetails);
		if(bookingDetails != null)
		{
			String accessCode;
			accessCode = bookingDetails.getString("accessToken");
			logger.info("Booking Access Token: " + accessCode);
			if(accessCode != null)			
			{
				Query getPolicyQuery = new Query();
				getPolicyQuery.addCriteria(Criteria.where("accessToken").is(accessCode));
				GetPolicyRequestResponse getPolicyDetails = mongoInstance.findOne(getPolicyQuery, GetPolicyRequestResponse.class);
				logger.info("Get Policy Response : " + getPolicyDetails);
				
				if(getPolicyDetails != null)
				{
					CommonGetPolicyRS getPolicyResponse = getPolicyDetails.getGetpolicyResponse();
					return new ResponseEntity(getPolicyResponse, HttpStatus.valueOf(statusCode));
				}
			}	
		}
		return null;		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject cancelBooking(String bookId,String userId) throws RestClientException, URISyntaxException, JSONException, ParseException 
	{
		JSONObject response = new JSONObject();
		Update update = new Update();
		Query query = new Query();
		boolean isAdmin = false;
		
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));

		query.addCriteria(Criteria.where("bookingId").is(bookId));
		BookingDetails bookingDetails = mongoInstance.findOne(query, BookingDetails.class);
		
		if(bookingDetails != null) 
		{
			isAdmin = getUserRole(userId, mongoInstance);
			if(bookingDetails.getUsername().equalsIgnoreCase(userId) || isAdmin)
			{
				String accessCode = bookingDetails.getAccessToken();
				String currency = "";
				String supplierId = bookingDetails.getSupplierId();
				
				JSONArray confirmationArray = new JSONArray(bookingDetails.getOtherBookInfo());
				JSONArray cancelConfirmationInfoArray = new JSONArray();
				
				for(int i=0;i<confirmationArray.length();i++) 
				{
					JSONObject cancelConfirmationInfoJson = new JSONObject();
					cancelConfirmationInfoJson.put("confirmationInstance", confirmationArray.getJSONObject(i).get("instance"));
					cancelConfirmationInfoJson.put("confirmationId", confirmationArray.getJSONObject(i).get("id"));
					cancelConfirmationInfoJson.put("confirmationType", confirmationArray.getJSONObject(i).get("type"));
				
					cancelConfirmationInfoArray.put(cancelConfirmationInfoJson);
				}
				
				JSONObject requestJson = new JSONObject();
				requestJson.put("accessCode", accessCode);
				requestJson.put("isocurrency", currency);
				requestJson.put("sequence", "1");
				requestJson.put("supplierId", supplierId);
				requestJson.put("cancelConfirmationInfo", cancelConfirmationInfoArray);
				
				String URL = loadClassesAuto.applicationProperties.getProperty(Constants.INTERNAL_CANCEL_URL);
				
				String plainCreds = Constants.USERNAME + ":" + Constants.PASSWORD; 
				byte[] plainCredsBytes = plainCreds.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				RestTemplate template = new RestTemplate();
				template.setErrorHandler(new MyResponseErrorHandler());
				
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
				headers.add("Authorization", "Basic " + base64Creds);
				
				HttpEntity<String> request = new HttpEntity(requestJson.toString(),headers);
				ResponseEntity<String> integrationResponse = template.exchange(new URI(URL), HttpMethod.POST, request, String.class);
	
				JSONObject responseJson =new JSONObject(integrationResponse.getBody());
				logger.info("responseJson : "+responseJson);
				
				if(!responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").get("otatourActivityCancelRS").toString().equals(Constants.NULL)) 
				{
					logger.info(responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").get("otatourActivityCancelRS"));
					String integrationStatus = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").getJSONObject("otatourActivityCancelRS").getString("success");
					
					if(integrationStatus.equalsIgnoreCase(Constants.TRUE)) 
					{
						//Update status of specified booking as Cancelled using provided Book id
						
						JSONObject cancelConfirmationJson = new JSONObject();
						cancelConfirmationJson = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").getJSONObject("otatourActivityCancelRS")
								.getJSONObject("reservation").getJSONObject("cancelConfirmation").getJSONObject("uniqueID");
					
						update.set("bookingStatus", cancelConfirmationJson.getString("idcontext"));
						
						mongoInstance.findAndModify(query, update, BookingDetails.class);
						bookingDetails = mongoInstance.findOne(query, BookingDetails.class);
						
						response.put(Constants.STATUS, Constants.SUCCESS);
						response.put(Constants.DESCRIPTION, "Booking cancel successfully");
						response.put("refresheddata", new JSONParser().parse(new Gson().toJson(bookingDetails)));
					}
					else
					{
						if(!responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").getJSONObject("otatourActivityCancelRS").get("errors").toString().equals(Constants.NULL)) 
						{
							JSONObject errorJson = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").getJSONObject("otatourActivityCancelRS").getJSONObject("errors");
							String errorMsg = errorJson.getJSONObject("error").getString("shortText");
							if(errorMsg.equalsIgnoreCase("Booking is already cancelled. You cannot cancel this booking again: "+bookingDetails.getSupplierBookingId())) 
							{
								update.set("bookingStatus", "Cancelled");
								mongoInstance.findAndModify(query, update, BookingDetails.class);
								bookingDetails = mongoInstance.findOne(query, BookingDetails.class);
								
								org.json.simple.JSONObject adminManipulateApiResponse = loadClassesAuto.commonOperations.manipulateBookingInAdminPanel(bookingDetails, "cancel");
								logger.info(adminManipulateApiResponse);
								
								response.put(Constants.STATUS, Constants.SUCCESS);
								response.put(Constants.DESCRIPTION, "Booking is already cancelled");
								response.put("refresheddata", new JSONParser().parse(new Gson().toJson(bookingDetails)));
							} else { 
								response.put(Constants.STATUS, Constants.FAILED);
								response.put(Constants.DESCRIPTION, errorMsg);
							}
						} 
						else { 
							response.put(Constants.STATUS, Constants.FAILED);
							response.put(Constants.DESCRIPTION, "Cancellation failed.Blank error response");
						}
					}
				}
				else
				{
					JSONObject errorJson = responseJson.getJSONObject("responseBody").getJSONObject("otatourActivityCancelRSWrapper").getJSONObject("errorList").getJSONObject("error");
					String errorMsg = errorJson.getString("errorMsg");
					if(errorMsg.equalsIgnoreCase("Unable to perform cancellation on ResID: " + bookingDetails.getSupplierBookingId() + " because it was already cancelled.")) 
					{
						update.set("bookingStatus", "Cancelled");
						mongoInstance.findAndModify(query, update, BookingDetails.class);
						bookingDetails = mongoInstance.findOne(query, BookingDetails.class);
						
						org.json.simple.JSONObject adminManipulateApiResponse = loadClassesAuto.commonOperations.manipulateBookingInAdminPanel(bookingDetails, "cancel");
						logger.info(adminManipulateApiResponse);
						
						response.put(Constants.STATUS, Constants.SUCCESS);
						response.put(Constants.DESCRIPTION, "Booking is already cancelled");
						response.put("refresheddata", new JSONParser().parse(new Gson().toJson(bookingDetails)));
					} else { 
						response.put(Constants.STATUS, Constants.FAILED);
						response.put(Constants.DESCRIPTION, errorMsg);
						response.put(Constants.CODE, errorJson.getString("errorCode"));
					}
				}
			}
			else {
				response.put(Constants.STATUS, Constants.FAILED);
				response.put(Constants.DESCRIPTION, "Access Denied. Insufficient rights to cancel booking");
			}
		} else {
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Invalid book id. Please provide proper book id");
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public boolean getUserRole(String username,MongoOperations mongoInstance)
	{
		boolean isAdmin = false;
		Query loginQuery = new Query();
		loginQuery.addCriteria(Criteria.where("username").is(username));
		
		Document userLoginDetails = mongoInstance.findOne(loginQuery, Document.class, "apilogindetails");
		if (userLoginDetails != null) 
		{
			List<String> authorities = (List<String>) userLoginDetails.get("authorities");
		
			for(String role : authorities) {
				if(role.equalsIgnoreCase("ADMIN"))
					isAdmin = true;
			}
		}
		return isAdmin;
	}
	
	public Object getUserCode(String userId)
	{
		Query userQuery = new Query();
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
		userQuery.addCriteria(Criteria.where("username").is(userId));
		Document userDetails = mongoInstance.findOne(userQuery, Document.class, "apilogindetails");
		if(userDetails != null)
		{
			String userRs;
			try
			{
				userRs = (String) userDetails.get("userCode");
				if(userRs != null)
					return userRs;
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return null;	
	}
	
	
	public BookingDetails getBookingInfo(String bookingId)
	{
		Query query = new Query();
		BookingDetails bookingDetails = new BookingDetails();
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		query.addCriteria(Criteria.where("bookingId").is(bookingId));
		bookingDetails = (BookingDetails) mongoInstance.findOne(query, BookingDetails.class);
		
		if(! bookingDetails.getBookingId().isEmpty())
		{
			return bookingDetails;
		}
		
		else
			return null;
	}
	
	public Object getBookingResponse(String bookingId)
	{
		Query bookQuery = new Query();
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		bookQuery.addCriteria(Criteria.where("bookId").is(bookingId));
		BookRequestResponse bookingDetails = mongoInstance.findOne(bookQuery, BookRequestResponse.class, "bookrequestresponse");
		System.out.println();
		if (bookingDetails != null) 
		{
			return bookingDetails;
		}
		else
			return null;
	}
	
	public Object getBookingData(String bookingId)
	{
		Query bookQuery = new Query();
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		bookQuery.addCriteria(Criteria.where("bookId").is(bookingId));
		Document bookingDetails = mongoInstance.findOne(bookQuery, Document.class, "bookrequestresponse");
		System.out.println();
		if (bookingDetails != null) 
		{
			JSONObject bookRs;
			try {
				bookRs = new JSONObject(new Gson().toJson(bookingDetails.get("originalResponse")));
				if(bookRs != null)			
				return bookRs;
			} catch (JSONException e) {
				e.printStackTrace();
			}		
		}
		else
			return null;
		return null;	
	}
	
	@SuppressWarnings("unused")
	public JSONObject getBookingRequest(String bookingId) throws ParseException  
	{
		Query bookQuery = new Query();
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		bookQuery.addCriteria(Criteria.where("bookId").is(bookingId));
		Document bookingDetails = mongoInstance.findOne(bookQuery, Document.class, "bookrequestresponse");
		if (bookingDetails != null) 
		{
			JSONObject bookRq;
			try {
				bookRq = new JSONObject(new Gson().toJson(bookingDetails.get("bookRequest")));
			
			if(bookRq != null)			
				return bookRq;
			else
				return null;
				}	
				catch (JSONException e) {
					e.printStackTrace();
		}
	}
		return null;
	}
	
	public JSONObject getRepriceResponse(String accessCode)
	{
		
		Query repriceQuery = new Query();
		
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		repriceQuery.addCriteria(Criteria.where("accessToken").is(accessCode));
		
		Document repriceDetails = mongoInstance.findOne(repriceQuery, Document.class, "repricerequestresponse");
		System.out.println(repriceDetails);
		if(repriceDetails != null)
		{
			JSONObject repriceRs;
			try
			{
				repriceRs = new JSONObject(new Gson().toJson(repriceDetails.get("repriceResponse")));
				
				if(repriceRs != null)			
					return repriceRs;
			}	
			catch (JSONException e) {
				e.printStackTrace();
			}	
		}
		return null;
	}
	
	public boolean updateBooking(UpdateBooking bookingData)
	{
		try {
			String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
			MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
			mongoInstance.insert(bookingData);
			logger.info("Booking status updated");
			return true;
		}catch (Exception e) {
			logger.error("failed to update booking",e);
			return false;
		}
	}
}	
