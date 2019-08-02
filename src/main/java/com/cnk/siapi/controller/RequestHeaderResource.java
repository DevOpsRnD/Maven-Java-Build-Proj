package com.cnk.siapi.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


/**
 * 
 * @author durgesh.songire
 *
 */
public class RequestHeaderResource {
	static Map<Integer, HashMap<String, Object>> headerMap;

	public Map<Object, Object> getSupplierDetails(String destination, String CountryCode, String cityCode, String startDate, String endDate, String priceCurrency) {
		System.out.println(destination +" " + CountryCode + " " + cityCode + " " + startDate + " " + endDate + " " + priceCurrency);
		try {
			Map<Object, Object> supplierMap = new HashMap<>();
			ClientConfig clientConfig = new DefaultClientConfig();
			Client client = Client.create(clientConfig);
			WebResource webResource = client.resource(
					"http://ckissvn.coxandkings.com/leopard/esb/web/app_dev.php/api/v1/supplierdetails.json?servicetype=Activities");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed: HTTP Error Code: " + response.getStatus());
			}
			String output = response.getEntity(String.class);
			JSONObject jObject;
			try {
				jObject = new JSONObject(output);

				JSONArray dataObject = jObject.getJSONArray("Data");
				for (int i = 0; i < dataObject.length(); i++) {
					JSONObject jsonObj = dataObject.getJSONObject(i);
					String op = jsonObj.toString();
					headerMap = new HashMap<Integer, HashMap<String, Object>>();
					HashMap<String, Object> mappedVals;
					mappedVals = new ObjectMapper().readValue(op, new TypeReference<HashMap<String, Object>>() {
					});

					headerMap.put(i, mappedVals);
					if(priceCurrency == null)
					{
						priceCurrency = (String) headerMap.get(i).get("Currency");
					}
					if ((headerMap.get(i).get("Destination").equals(destination)) && ((headerMap.get(i).get("Currency").equals(priceCurrency)))) {
						supplierMap.putAll(headerMap.get(i));
						return supplierMap;
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * Commented as we don't need matt's system now
	 */
	
	/*	
	@SuppressWarnings("unused")
	public String getCountryCode(String countryName,String supplierName)
	{
		ClientConfig clientConfig = new DefaultClientConfig();
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource(
				"http://10.24.2.104:8001/Mapping/System/Country/CountryName/"+countryName+"/SupplierName/"+supplierName);
		ClientResponse response = webResource.accept("application/json").type("application/json")
				.get(ClientResponse.class);
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed: HTTP Error Code: " + response.getStatus());
		}
		String output = response.getEntity(String.class);	
		try
		{
	//	JSONObject countryObject = new JSONObject(output);
		
	//	JSONObject countryCode = countryObject.getJSONObject("countryCode");
		JSONArray countryArray = new JSONArray(output);
		 for(int i=0;i<countryArray.length();i++){
			 JSONObject jsonObj = countryArray.getJSONObject(i);
			 String countryCode = jsonObj.getString("countryCode");
			 return countryCode;
	          }
		
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}*/
	
	/*public static void main(String args[])
	{
		RequestHeaderResource reqHdr = new RequestHeaderResource();
		reqHdr.getCountryCode("Australia","tourico");
	}*/
}
