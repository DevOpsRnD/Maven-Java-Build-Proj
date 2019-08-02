package com.cnk.siapi.db;

import java.net.URI;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.common.MyResponseErrorHandler;
import com.cnk.siapi.common.ResponseStatusCode;
import com.cnk.siapi.login.ApiLoginDetails;
import com.cnk.siapi.model.book.BookingDetails;

@Service
public class CommonOperations 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public JSONObject manipulateBookingInAdminPanel(BookingDetails bookingDetails, String operation) 
	{
		logger.info("manipulateBookingInAdminPanel");
		String URL = null;
		HttpEntity<String> request = null;
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<JSONObject> integrationResponse = null;

		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new MyResponseErrorHandler());
		JSONObject response = new JSONObject();
		
		try {
			if(loadClassesAuto.responseJson != null)
				loadClassesAuto.responseJson.clear();
			
			/*//Get password for given user from admin panel
			URL = loadClassesAuto.applicationProperties.getProperty(Constants.GET_PRODUCT_CREDENTIALS_URL);
			
			template.setErrorHandler(new MyResponseErrorHandler());
			headers.add("username", bookingDetails.getUsername());
			headers.add("productname", "activity");
			request = new HttpEntity(headers);
			integrationResponse = template.exchange(new URI(URL), HttpMethod.GET, request, JSONObject.class);
			
			if(integrationResponse.getStatusCode().equals(HttpStatus.OK))
				response = integrationResponse.getBody();
			else
				response = loadClassesAuto.responseJson;
			
			if(response.get(Constants.STATUS) != null && response.get(Constants.STATUS).toString().equalsIgnoreCase(Constants.SUCCESS))
			{*/
				String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
				MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
				
				Query query = new Query();
				query.addCriteria(Criteria.where("username").is(bookingDetails.getUsername()));
				
				ApiLoginDetails apiLoginDetails = mongoInstance.findOne(query, ApiLoginDetails.class);
			
				//After getting passsword call manage booking api with proper credenttials
				String productLoginUsername = apiLoginDetails.getUsername();
				String productLoginPassword = apiLoginDetails.getPassword();

//				response.clear();
//				String token = loadClassesAuto.commonOperations.getAdminPanelToken(productLoginUsername, productLoginPassword);
				
				String plainCreds = productLoginUsername + ":" + productLoginPassword;
				byte[] plainCredsBytes = plainCreds.getBytes();
				byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
				String base64Creds = new String(base64CredsBytes);
				
				URL = loadClassesAuto.applicationProperties.getProperty(Constants.ADMIN_MANAGE_BOOK_URL);
				
				JSONObject requestJson = new JSONObject();
				requestJson.put("username", productLoginUsername);
				requestJson.put("bookingId", bookingDetails.getBookingId());
				requestJson.put("productName", "Activity");
				requestJson.put("bookingStatus", bookingDetails.getBookingStatus());
				
				for (ResponseStatusCode responseCode : ResponseStatusCode.values())
				{
					if(responseCode.toString().equalsIgnoreCase(bookingDetails.getBookingStatus())) {
						requestJson.put("bookingStatus", responseCode.name());
						break;
					}
				}
				
				if(operation.equalsIgnoreCase("book")) {
					requestJson.put("supplierId", bookingDetails.getSupplierId());
					requestJson.put("supplierName", bookingDetails.getSupplierName());
					requestJson.put("supplierBookingId", bookingDetails.getSupplierBookingId());
					requestJson.put("bookingDate", bookingDetails.getBookingDate());
				}
				
				logger.info("Input request for manage book in admin panel : "+requestJson);
				
				headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
				headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
				headers.add("Authorization", "Basic "+base64Creds);
				headers.add("operation", operation);
				
				logger.info("URL for manage book : "+URL);
				request = new HttpEntity(requestJson.toString(),headers);
				integrationResponse = template.exchange(new URI(URL), HttpMethod.POST, request, JSONObject.class);
				
				if(integrationResponse.getStatusCode().equals(HttpStatus.OK))
					response = integrationResponse.getBody();
				else
					response = loadClassesAuto.responseJson;
				
				logger.info("Manage book response : "+ response);
//			}
		}
		catch(ResourceAccessException rae)
		{
			logger.error("Exception occur", rae);
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Invalid credentials to access api");
		}
		catch(Exception e)
		{
			logger.error("Exception occur", e);
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Something went wrong. Failed to save booking data in Admin Panel");
		}
		return response;
	}
	
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	public String getAdminPanelToken(String username, String password) 
	{
		String token = null;
		JSONObject response = new JSONObject();
		
		try {
			if(loadClassesAuto.responseJson != null)
				loadClassesAuto.responseJson.clear();
			
			String URL = loadClassesAuto.applicationProperties.getProperty(Constants.ADMIN_LOGIN_URL);
			
			RestTemplate template = new RestTemplate();
			template.setErrorHandler(new MyResponseErrorHandler());
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
			
			MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();     
			body.add("username", username);
			body.add("password", password);
			
			HttpEntity<String> request = new HttpEntity(body,headers);
			ResponseEntity<JSONObject> integrationResponse = template.exchange(new URI(URL), HttpMethod.POST, request, JSONObject.class);
			
			if(integrationResponse.getStatusCode().equals(HttpStatus.OK)) {
				response = integrationResponse.getBody();
				token = response.get("token").toString();
			}
			else
				response = loadClassesAuto.responseJson;
		} 
		catch(ResourceAccessException rae)
		{
			logger.error("Exception occur", rae);
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Invalid credentials to access api");
		}
		catch(Exception e)
		{
			logger.error("Exception occur", e);
			response.put(Constants.STATUS, Constants.FAILED);
			response.put(Constants.DESCRIPTION, "Something went wrong. Failed to retrieve token from Admin Panel for current user");
		}
		
		return token;
	}

}
