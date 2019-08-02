package com.cnk.siapi.model.master;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.productstatic.response.ProductActivityRS;
import com.cnk.siapi.staticactivity.response.Activity;
import com.cnk.siapi.staticactivity.response.StaticActivityRS;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Service
public class StaticActivityImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	String requestSupplierCode;
	String requestSupplierId;
	String filterSupplierId;
	
	public Object getActivites(StaticActivities activitiesRequest) throws JSONException
	{
		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		
		/*requestSupplierCode = activitiesRequest.getRequestingSupplierCode();
		System.out.println(requestSupplierCode);
		requestSupplierId = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYNAME,requestSupplierCode, "staticsupplierlist").getSupplierCode();
		activitiesRequest.setRequestingSupplierCode(requestSupplierId);
	*/	
		logger.info("***Static Activites Start***");
		try
		{
			List<String> filterSupplierL =  activitiesRequest.getFilterBySuppliers(); 
			
			Iterator<String> filterItr = filterSupplierL.iterator();
			while(filterItr.hasNext())
			{
				String filterSupplierNo = filterItr.next();
				filterSupplierId = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYCODE,filterSupplierNo, "staticsupplierlist").getSupplierName();
				
			}
			filterSupplierL.remove(0);
			filterSupplierL.add(filterSupplierId);
		}
		catch(NullPointerException ex)
		{
			logger.info("No Filter By Supplier Code Entered");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
        JSONObject json = new JSONObject(new Gson().toJson(activitiesRequest));
	    @SuppressWarnings({ "rawtypes", "unchecked" })
		HttpEntity<String> request = new HttpEntity(json.toString(),headers);
	    
	    String getActivityUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETACTIVITY_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
	    System.out.println(getActivityUrl);
//	    ResponseEntity<String> response = template.postForEntity("http://10.24.2.104:8001/ActivityMapping/Get/ByCities", request , String.class );
//	    ResponseEntity<String> response = template.postForEntity("http://preprod-lb-wapi-632830701.ap-south-1.elb.amazonaws.com/ActivityMapping/Get/ByCities", request , String.class );
	    ResponseEntity<String> response = template.postForEntity( getActivityUrl, request , String.class );
		String responseString = response.getBody();
		
		try {
			StaticActivityRS activityRs = mapper.readValue(responseString, StaticActivityRS.class);
			
			// Change the product code and concat city code with Product Code 
			List<Activity> activityL = activityRs.getActivities();
			Iterator<Activity> itr = activityL.iterator();
			
			while(itr.hasNext())
			{
				Activity activity = new Activity();
				activity = itr.next();
				String regionCodeNew = activity.getCityCode();
				regionCodeNew = regionCodeNew.replace("-", "");
				String productCodeNew = regionCodeNew + activity.getSupplierProductCode();
				System.out.println("------Supplier Product Code: "+ activity.getSupplierProductCode());
				activity.setSupplierProductCode(productCodeNew);
				// Change the SupplierName as well
				String supplierName = activity.getSupplierCompanyCode();
				supplierName = supplierName.toUpperCase();
				String collectionName = "staticsupplierlist";
		        String supplierCode = loadClassesAuto.getSupplierList.getSupplierCodeFromDB(Constants.SEARCH_SUPPLIER_BYNAME, supplierName, collectionName);
				if(supplierCode != null)
					activity.setSupplierCompanyCode(supplierCode);
				
			}
			logger.info("***Static Activites End***");
			return activityRs;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public Object getCityMaster(HttpServletRequest request)
	{
		RestTemplate template = new RestTemplate();
		String dataRequest = request.getHeader("Accept");
		
		String getCityMasterUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETCITYMASTER_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
		
//		ResponseEntity<String> response = template.getForEntity("http://10.24.2.104:8001/Masters/Get/Cities", String.class);
		ResponseEntity<String> response = template.getForEntity(getCityMasterUrl, String.class);
		if(dataRequest.equals("application/xml"))
        {
			JSONObject json;
			try {
				json = new JSONObject(response.getBody());
				String xml = XML.toString(json);
				return xml;
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
        }
        else	
        {
        	return response;
        }
		return null;
	}
	
	public Object getCities(HttpServletRequest request, String countryCode)
	{
		RestTemplate template = new RestTemplate();
		String dataRequest = request.getHeader("Accept");
		
		String getCityUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETCITY_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
		
//		ResponseEntity<String> response = template.getForEntity("http://10.24.2.104:8001/Masters/Get/Cities", String.class);
		ResponseEntity<String> response = template.getForEntity(getCityUrl + countryCode, String.class);
		if(dataRequest.equals("application/xml"))
        {
			JSONObject json;
			try {
				json = new JSONObject(response.getBody());
				String xml = XML.toString(json);
				return xml;
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
        }
        else	
        {
        	return response;
        }
		return null;
	}
	
	public Object getCountryMaster(HttpServletRequest request)
	{
		RestTemplate template = new RestTemplate();
//		String dataRequest = request.getHeader("Accept");
		String getCountryMasterUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETCOUNTRYMASTER_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
		
	//	ResponseEntity<String> response = template.getForEntity("http://10.24.2.104:8001/Masters/Get/Countries", String.class);
		ResponseEntity<String> response = template.getForEntity(getCountryMasterUrl, String.class);
		
		return response;
	}
	
	public Object getCountry(HttpServletRequest request, String countryCode)
	{
		RestTemplate template = new RestTemplate();
//		String dataRequest = request.getHeader("Accept");
		String getCountryUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETCOUNTRY_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
		System.out.println(getCountryUrl + countryCode);
	//	ResponseEntity<String> response = template.getForEntity("http://10.24.2.104:8001/Masters/Get/Countries", String.class);
		ResponseEntity<String> response = template.getForEntity(getCountryUrl + countryCode, String.class);

		return response;
	}

	@SuppressWarnings("unused")
	public Object getActivityInfo(String supplierCode, String supplierProductCode) 
	{
		ObjectMapper mapper = new ObjectMapper();	
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
		String supplierId = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYCODE,supplierCode, "staticsupplierlist").getSupplierName();

		String url = "http://172.23.217.22/Mapping/System/City/CityCode/";
		String productCode = supplierProductCode.substring(0, 3)+"-"+supplierProductCode.substring(3);
		System.out.println(productCode);
		RestTemplate template = new RestTemplate();
		ResponseEntity<String> cityResponse = null;
		String productCodeNew;
		if(supplierProductCode.length()<9)
			return "Enter Valid Product Code";
		try 
		{
			System.out.println("In Try");
			productCodeNew = productCode.substring(0, 10);
			System.out.println(productCodeNew);
			System.out.println(url);
			cityResponse = template.getForEntity(url + productCode , String.class);
			System.out.println("After City Response");
			System.out.println(cityResponse.getBody());
			productCode = productCode.substring(10);
		}
		catch(Exception e)
		{
			System.out.println("In Catch");
			productCodeNew = productCode.substring(0, 11);
			System.out.println(productCodeNew);
			cityResponse = template.getForEntity(url + productCode, String.class); 
			System.out.println(cityResponse.getBody());
			productCode = productCode.substring(11);
		}
		
		if(cityResponse == null)
		{
			return "Invalid Product Code";
		}
		
		String getProductStaticUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETPRODUCTSTATICURL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
		logger.info("* Product Wise Static Request *");
		logger.info(getProductStaticUrl + "/SupplierCode/" + supplierId + "/SupplierProductCode/" + productCode);
		
		ResponseEntity<String> response = template.getForEntity(getProductStaticUrl + "/SupplierCode/" + supplierId + "/SupplierProductCode/" + productCode, String.class);
		String responseString = response.getBody();
		HttpStatus status = response.getStatusCode();
		System.out.println(status);
		if (response.getStatusCode() == HttpStatus.OK) 
		{
			if(!responseString.toString().equalsIgnoreCase("null")) {
			try {
				ProductActivityRS activityRs = mapper.readValue(responseString, ProductActivityRS.class);
				
				// Change the product code and concate city code with Product Code 			

					String regionCodeNew = activityRs.getCityCode();
					regionCodeNew = regionCodeNew.replace("-", "");
					productCodeNew = regionCodeNew + activityRs.getSupplierProductCode();
					
					activityRs.setSupplierProductCode(productCodeNew);
					// Change the SupplierName as well
					String supplierName = activityRs.getSupplierCompanyCode();
					supplierName = supplierName.toUpperCase();
					String collectionName = "staticsupplierlist";
			        supplierCode = loadClassesAuto.getSupplierList.getSupplierCodeFromDB(Constants.SEARCH_SUPPLIER_BYNAME, supplierName, collectionName);
					if(supplierCode != null)
						activityRs.setSupplierCompanyCode(supplierCode);
					
				logger.info("***Static Activites End***");
				return activityRs;
			
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			else
				return "No Data Found";
		}
			return null;
		}
}
