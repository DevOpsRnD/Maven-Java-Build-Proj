package com.cnk.siapi.db;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.login.ApiLoginDetails;
import com.cnk.siapi.model.storerequst.BookRequestResponse;
import com.cnk.siapi.model.storerequst.CancelRequestResponse;
import com.cnk.siapi.model.storerequst.GetDetailsRequestResponse;
import com.cnk.siapi.model.storerequst.GetPolicyRequestResponse;
import com.cnk.siapi.model.storerequst.RepriceRequestResponse;
import com.cnk.siapi.model.storerequst.RetrieveRequestResponse;
import com.cnk.siapi.supplier.AccessToken;

@Service
public class MongoDBOperation {

	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	public MongoDBOperation() {
	}
	
	public AccessToken findByAccessToken(String token) 
	{
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance =loadClassesAuto.mongoConnection.getInstance(databaseName);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("accessToken").is(token));
		
		AccessToken accessToken = (AccessToken) mongoInstance.findOne(query, AccessToken.class);
		return accessToken;
	}
	
	public String getURL(String urlName , String environment)
	{
		String collectionName = "urllist";
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance =loadClassesAuto.mongoConnection.getInstance(databaseName);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("urlName").is(urlName));
		
		JSONObject urlDetails = mongoInstance.findOne(query, JSONObject.class, collectionName);
		if(urlDetails != null && urlDetails.containsKey("_id"))
			return urlDetails.get(environment+"value").toString();
		else
			return null;
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject saveLoginDetails(ApiLoginDetails apiLoginDetails) 
	{
		JSONObject response = new JSONObject();
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
		
		Query usernameQuery = new Query();
		usernameQuery.addCriteria(Criteria.where("username").is(apiLoginDetails.getUsername()));
		Query usercodeQuery = new Query();
		usercodeQuery.addCriteria(Criteria.where("userCode").is(apiLoginDetails.getUserCode()));
		
		 if( mongoInstance.findOne(usernameQuery, ApiLoginDetails.class) == null) {
			 if( mongoInstance.findOne(usercodeQuery, ApiLoginDetails.class) == null ) 
			 {
				 mongoInstance.insert(apiLoginDetails);
				 response.put(Constants.STATUS, Constants.SUCCESS);
				 response.put(Constants.DESCRIPTION, "Login credentials saved successfully");
			 }
			 else {
				 response.put(Constants.STATUS, Constants.FAILED);
				 response.put(Constants.DESCRIPTION, "Usercode already present");
			 }
		 }
		 else {
			 response.put(Constants.STATUS, Constants.FAILED);
			 response.put(Constants.DESCRIPTION, "Username already present");
		 }
		 return response;
	}
	
	public BookRequestResponse findByBookingId(String bookingId) 
	{
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance =loadClassesAuto.mongoConnection.getInstance(databaseName);
		Query query = new Query();
		query.addCriteria(Criteria.where("bookId").is(bookingId));
		
		BookRequestResponse bookRequestResponse = (BookRequestResponse) mongoInstance.findOne(query, BookRequestResponse.class);
		return bookRequestResponse;
	}
	
	public Object findByAccessCode(String accessCode,String operation) 
	{
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		MongoOperations mongoInstance =loadClassesAuto.mongoConnection.getInstance(databaseName);
		
		Query query = new Query();
		query.addCriteria(Criteria.where("accessToken").is(accessCode));
		if(operation.equalsIgnoreCase("booking")){
			BookRequestResponse bookRequestResponse = (BookRequestResponse) mongoInstance.findOne(query, BookRequestResponse.class);
			return bookRequestResponse;
		}else if(operation.equalsIgnoreCase("getdetails")){
			GetDetailsRequestResponse bookRequestResponse = (GetDetailsRequestResponse) mongoInstance.findOne(query, GetDetailsRequestResponse.class);
			return bookRequestResponse;
		}else if(operation.equalsIgnoreCase("policy")){
			GetPolicyRequestResponse getpolicyrequestresponse = (GetPolicyRequestResponse) mongoInstance.findOne(query, GetPolicyRequestResponse.class);
			return getpolicyrequestresponse;
		}else if(operation.equalsIgnoreCase("reprice")){
			RepriceRequestResponse getrepriceequestresponse = (RepriceRequestResponse) mongoInstance.findOne(query, RepriceRequestResponse.class);
			return getrepriceequestresponse;
		}else if(operation.equalsIgnoreCase("retrieve")){
			RetrieveRequestResponse getretrieverequestresponse = (RetrieveRequestResponse) mongoInstance.findOne(query, RetrieveRequestResponse.class);
			return getretrieverequestresponse;
		}else if(operation.equalsIgnoreCase("cancel")){
			CancelRequestResponse getcancelrequestresponse = (CancelRequestResponse) mongoInstance.findOne(query, CancelRequestResponse.class);
			return getcancelrequestresponse;
		}
		
		return null;
		
	}
	
}