package com.cnk.siapi.dbscript;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.cnk.siapi.common.Constants;
import com.mongodb.MongoClient;

public class InsertUrls 
{
	
/*	public static void main(String[] args)
	{
		new InsertUrls().insertUrl();
	}*/
	
	@SuppressWarnings("unchecked")
	public void insertUrl() {
		String databaseIP = "10.21.32.93";
		int databasePort = 27017;
		String databaseName = "siapi";
		
		MongoOperations mongoOperation = new MongoTemplate(new MongoClient(databaseIP, databasePort) , databaseName);
	
		JSONArray jsonArray = new JSONArray();
		
		JSONObject SI_SEARCH_URL = new JSONObject();
		SI_SEARCH_URL.put("urlName", Constants.SEARCH_URL);
		SI_SEARCH_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/GetAvailabilityAndPrice");
		SI_SEARCH_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/GetAvailabilityAndPrice");
		jsonArray.add(SI_SEARCH_URL);
		
		JSONObject SI_BOOK_URL = new JSONObject();
		SI_BOOK_URL.put("urlName", Constants.BOOK_URL);
		SI_BOOK_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/CreateBooking");
		SI_BOOK_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/CreateBooking");
		jsonArray.add(SI_BOOK_URL);
		
		JSONObject SI_CANCEL_URL = new JSONObject();
		SI_CANCEL_URL.put("urlName", Constants.CANCEL_URL);
		SI_CANCEL_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/CancelBooking");
		SI_CANCEL_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/CancelBooking");
		jsonArray.add(SI_CANCEL_URL);
		
		JSONObject SI_GETDETAILS_URL = new JSONObject();
		SI_GETDETAILS_URL.put("urlName", Constants.GETDETAILS_URL);
		SI_GETDETAILS_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/GetDetails");
		SI_GETDETAILS_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/GetDetails");
		jsonArray.add(SI_GETDETAILS_URL);
		
		JSONObject SI_GETPOLICY_URL = new JSONObject();
		SI_GETPOLICY_URL.put("urlName", Constants.GETPOLICY_URL);
		SI_GETPOLICY_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/Getpolicies");
		SI_GETPOLICY_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/Getpolicies");
		jsonArray.add(SI_GETPOLICY_URL);
		
		JSONObject SI_REPRICE_URL = new JSONObject();
		SI_REPRICE_URL.put("urlName", Constants.REPRICE_URL);
		SI_REPRICE_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/RePricing");
		SI_REPRICE_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/RePricing");
		jsonArray.add(SI_REPRICE_URL);
		
		JSONObject SI_RETRIEVE_URL = new JSONObject();
		SI_RETRIEVE_URL.put("urlName", Constants.RETRIEVE_URL);
		SI_RETRIEVE_URL.put("devvalue", "http://10.25.6.150:8002/SightCommonInterface/RetrieveBooking");
		SI_RETRIEVE_URL.put("prodvalue", "http://10.25.6.150:8002/SightCommonInterface/RetrieveBooking");
		jsonArray.add(SI_RETRIEVE_URL);
		
		/***********************************************************************************/
		
//		old ip : 10.12.3.127
		
		JSONObject SEARCHCITY_BYCODE_URL = new JSONObject();
		SEARCHCITY_BYCODE_URL.put("urlName", Constants.SEARCHCITY_BYCODE_URL);
		SEARCHCITY_BYCODE_URL.put("devvalue", "http://172.23.217.22/Mapping/System/City/CityCode/");
		SEARCHCITY_BYCODE_URL.put("prodvalue", "http://172.23.217.22/Mapping/System/City/CityCode/");
		jsonArray.add(SEARCHCITY_BYCODE_URL);
		
		JSONObject GETACTIVITY_URL = new JSONObject();
		GETACTIVITY_URL.put("urlName", Constants.GETACTIVITY_URL);
		GETACTIVITY_URL.put("devvalue", "http://172.23.217.22/ActivityMapping/Get/ByCities");
		GETACTIVITY_URL.put("prodvalue", "http://172.23.217.22/ActivityMapping/Get/ByCities");
		jsonArray.add(GETACTIVITY_URL);
		
		JSONObject GETPRODUCTSTATICURL = new JSONObject();
		GETPRODUCTSTATICURL.put("urlName", Constants.GETPRODUCTSTATICURL);
		GETPRODUCTSTATICURL.put("devvalue", "http://172.23.217.22/ActivityMapping/Get/Details");
		GETPRODUCTSTATICURL.put("prodvalue", "http://172.23.217.22/ActivityMapping/Get/Details");
		jsonArray.add(GETPRODUCTSTATICURL);
		
		JSONObject GETCITYMASTER_URL = new JSONObject();
		GETCITYMASTER_URL.put("urlName", Constants.GETCITYMASTER_URL);
		GETCITYMASTER_URL.put("devvalue", "http://172.23.217.22/Masters/Get/Cities");
		GETCITYMASTER_URL.put("prodvalue", "http://172.23.217.22/Masters/Get/Cities");
		jsonArray.add(GETCITYMASTER_URL);
		
		JSONObject GETCOUNTRYMASTER_URL = new JSONObject();
		GETCOUNTRYMASTER_URL.put("urlName", Constants.GETCOUNTRYMASTER_URL);
		GETCOUNTRYMASTER_URL.put("devvalue", "http://172.23.217.22/Masters/Get/Countries");
		GETCOUNTRYMASTER_URL.put("prodvalue", "http://172.23.217.22/Masters/Get/Countries");
		jsonArray.add(GETCOUNTRYMASTER_URL);
		
		JSONObject GETCITY_URL = new JSONObject();
		GETCITY_URL.put("urlName", Constants.GETCITY_URL);
		GETCITY_URL.put("devvalue", "http://172.23.217.22/Masters/Get/Cities/CountryCode/");
		GETCITY_URL.put("prodvalue", "http://172.23.217.22/Masters/Get/Cities/CountryCode/");
		jsonArray.add(GETCITY_URL);
		
		JSONObject GETCOUNTRY_URL = new JSONObject();
		GETCOUNTRY_URL.put("urlName", Constants.GETCOUNTRY_URL);
		GETCOUNTRY_URL.put("devvalue", "http://172.23.217.22/Masters/Get/Countries/CountryCode/");
		GETCOUNTRY_URL.put("prodvalue", "http://172.23.217.22/Masters/Get/Countries/CountryCode/");
		jsonArray.add(GETCOUNTRY_URL);
		
		JSONObject GETCITYNAME_URL = new JSONObject();
		GETCITYNAME_URL.put("urlName", Constants.GETCITYNAME_URL);
		GETCITYNAME_URL.put("devvalue", "http://172.23.217.22/Mapping/System/City/");
		GETCITYNAME_URL.put("prodvalue", "http://172.23.217.22/Mapping/System/City/");
		jsonArray.add(GETCITYNAME_URL);
		
		JSONObject GETROE_URL = new JSONObject();
		GETROE_URL.put("urlName", Constants.GETROEURL);
		GETROE_URL.put("devvalue", "http://esb.coxandkings.com/api/v1/roe.json");
		GETROE_URL.put("prodvalue", "http://esb.coxandkings.com/api/v1/roe.json");
		jsonArray.add(GETROE_URL);

		for(Object object : jsonArray)
		{
			 JSONObject json = (JSONObject) object;
			 
			 Query query = new Query();
			 query.addCriteria(Criteria.where("urlName").is(json.get("urlName")));
			 
			 if( mongoOperation.findOne(query, JSONObject.class, "urllist") == null)
			 {
				 mongoOperation.insert(json, "urllist");
				 System.out.println("Inserted Data : "+json);
			 }
				 
		}
		
		
		/*Query query = new Query();
		query.addCriteria(Criteria.where("urlName").is("SI_GETPOLICY_URL"));
		
		System.out.println(mongoOperation.findOne(query, JSONObject.class, "urllist").get("devvalue"));*/
		
	}

}
