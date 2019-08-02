package com.cnk.siapi.dbscript;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.MyResponseErrorHandler;
import com.cnk.siapi.model.requestheader.CredentialHeaderDAO;
import com.cnk.siapi.model.requestheader.OperationUrlDAO;
import com.cnk.siapi.model.requestheader.SupplierHeaderDAO;
import com.google.gson.Gson;

public class InsertSupplier 
{
	/*public static void main(String[] args) {
		
		try {
			List<String> operationList = new ArrayList<>();
			operationList.add("search");
			operationList.add("book");
			operationList.add("cancel");
			operationList.add("retrieve");
			operationList.add("reprice");
			operationList.add("getdetails");
			operationList.add("getpolicy");
			operationList.add("static");

			for(String operation : operationList)
			{
				new InsertSupplier().insertSupplier(operation);
			}
			
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.exit(0);
		}
	}*/

	@SuppressWarnings({ "unchecked", "rawtypes" , "unused"})
	public void insertSupplier(String operation) throws RestClientException, URISyntaxException, JSONException  {
		Gson gson = new Gson();
		String sequence = "1";
//		String URL = "http://10.21.32.93:8086/activity/supplier/addSupplierCredentials";
		String URL = "http://localhost:8080/supplier/addSupplierCredentials";
		
		String plainCreds = "tejesh:tejesh@123";
		byte[] plainCredsBytes = plainCreds.getBytes();
		byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
		String base64Creds = new String(base64CredsBytes);
		
		RestTemplate template = new RestTemplate();
		template.setErrorHandler(new MyResponseErrorHandler());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(org.springframework.http.MediaType.APPLICATION_JSON));
		headers.add("Authorization", "Basic " + base64Creds);
		
		//1
	    List<CredentialHeaderDAO> credentialListAcampora = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListAcampora = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOAcampora = new SupplierHeaderDAO("ACAMPORA", "1");
	    
	    CredentialHeaderDAO credentialHeaderDAOAcampora = new CredentialHeaderDAO("USERNAME","true","7a9bl/qpZmpNjzX1HQ3t2Q==");
	    CredentialHeaderDAO credentialHeaderDAOAcampora1 = new CredentialHeaderDAO("PASSWORD","true","/uiMvq9+h/T2XSpRMDMV5g==");
	    credentialListAcampora.add(credentialHeaderDAOAcampora);
	    credentialListAcampora.add(credentialHeaderDAOAcampora1);
	    supplierHeaderDAOAcampora.setCredentials(credentialListAcampora);
	    
	    OperationUrlDAO operationUrlDAOAcampora = new OperationUrlDAO("all","http://booking.acamporatravel.it:8001/test/b2b/service.asmx");
	    operationUrlListAcampora.add(operationUrlDAOAcampora);
	    supplierHeaderDAOAcampora.setOperdao(operationUrlListAcampora);
	    
	    //2
	    List<CredentialHeaderDAO> credentialListGta = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListGta = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOGta = new SupplierHeaderDAO("GTA", operation.equalsIgnoreCase("search") ? "2" : sequence);
        
	    CredentialHeaderDAO credentialHeaderDAOGta = new CredentialHeaderDAO("emailaddress","true","XML@COXANDKINGS.COM");
	    CredentialHeaderDAO credentialHeaderDAOGta1 = new CredentialHeaderDAO("password","true","PASS");
	    CredentialHeaderDAO credentialHeaderDAOGta2 = new CredentialHeaderDAO("client","true","2316");
	    credentialListGta.add(credentialHeaderDAOGta);
	    credentialListGta.add(credentialHeaderDAOGta1);
	    credentialListGta.add(credentialHeaderDAOGta2);
	    supplierHeaderDAOGta.setCredentials(credentialListGta);
	    
	    OperationUrlDAO operationUrlDAOGta = new OperationUrlDAO("all","https://interface.demo.gta-travel.com/wbsapi/RequestListenerServlet");
	    operationUrlListGta.add(operationUrlDAOGta);
	    supplierHeaderDAOGta.setOperdao(operationUrlListGta);
	    
	    //3
	    List<CredentialHeaderDAO> credentialListBemyguest = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListBemyguest = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOBemyguest = new SupplierHeaderDAO("BEMYGUEST", operation.equalsIgnoreCase("search") ? "3" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOBemyguest = new CredentialHeaderDAO("X-AUTHORIZATION","false","04e04c14f2767a60b6cbef8802cb3c18c250e015");
	    credentialListBemyguest.add(credentialHeaderDAOBemyguest);
        supplierHeaderDAOBemyguest.setCredentials(credentialListBemyguest);
        
        OperationUrlDAO operationUrlDAOBemyguest = new OperationUrlDAO("all","https://api.demo.bemyguest.com.sg/v2");
        operationUrlListBemyguest.add(operationUrlDAOBemyguest);
        supplierHeaderDAOBemyguest.setOperdao(operationUrlListBemyguest);
	    
        //4
        List<CredentialHeaderDAO> credentialListWhl = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListWhl = new ArrayList<OperationUrlDAO>();
        
        SupplierHeaderDAO supplierHeaderDAOWhl = new SupplierHeaderDAO("WHL", operation.equalsIgnoreCase("search") ? "4" : sequence);
        
	    CredentialHeaderDAO credentialHeaderDAOWhl = new CredentialHeaderDAO("actor","true","nI7CviiNE2c=");
	    CredentialHeaderDAO credentialHeaderDAOWhl1 = new CredentialHeaderDAO("user","true","JYGgTw6o99zpX9EFPaWIkw==");
	    CredentialHeaderDAO credentialHeaderDAOWhl2 = new CredentialHeaderDAO("password","true","B56xt8z5AsBUp+FMcl0PvQ==");
	    
	    credentialListWhl.add(credentialHeaderDAOWhl);
	    credentialListWhl.add(credentialHeaderDAOWhl1);
	    credentialListWhl.add(credentialHeaderDAOWhl2);
	    supplierHeaderDAOWhl.setCredentials(credentialListWhl);
	    
	    OperationUrlDAO operationUrlDAOWhl = new OperationUrlDAO("all","http://kalima.worldwidehotelink.com/call.php");
	    operationUrlListWhl.add(operationUrlDAOWhl);
	    supplierHeaderDAOWhl.setOperdao(operationUrlListWhl);	
        
	    //5
	    List<CredentialHeaderDAO> credentialListHotelbeds = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListHotelbeds = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOHotelbeds = new SupplierHeaderDAO("HOTELBEDS", operation.equalsIgnoreCase("search") ?  "1" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOHotelbeds = new CredentialHeaderDAO("API-KEY","true","ag5fhwfj6gezq79cb6n4x3ck");
	    CredentialHeaderDAO credentialHeaderDAOHotelbeds1 = new CredentialHeaderDAO("SECRET","true","BK5HbGjsta");
	    
	    credentialListHotelbeds.add(credentialHeaderDAOHotelbeds);
	    credentialListHotelbeds.add(credentialHeaderDAOHotelbeds1);
	    supplierHeaderDAOHotelbeds.setCredentials(credentialListHotelbeds);
	    
	    OperationUrlDAO operationUrlDAOHotelbeds = new OperationUrlDAO("all","https://api.TEST.hotelbeds.com/activity-api/3.0/");
	    operationUrlListHotelbeds.add(operationUrlDAOHotelbeds);
	    supplierHeaderDAOHotelbeds.setOperdao(operationUrlListHotelbeds);
	    
	    //6
		List<CredentialHeaderDAO> credentialListGiftxoxo = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListGiftxoxo = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOGiftxoxo = new SupplierHeaderDAO("GIFTXOXO", operation.equalsIgnoreCase("search") ? "6" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOGiftxoxo = new CredentialHeaderDAO("KEY","false","fee35f523299474d8c7407a473cfd5bfQ294YW5kS2luZ3NMdGQuMTUxMzc2OTkwMDI3NngwWDBkQHlDb3hhbmRLaW5nc3xFemVlZ28=");
	    
        credentialListGiftxoxo.add(credentialHeaderDAOGiftxoxo);
        supplierHeaderDAOGiftxoxo.setCredentials(credentialListGiftxoxo);
        
        OperationUrlDAO operationUrlDAOGiftxoxo = new OperationUrlDAO("all","https://corp.xoxoday.com");
        operationUrlListGiftxoxo.add(operationUrlDAOGiftxoxo);
        supplierHeaderDAOGiftxoxo.setOperdao(operationUrlListGiftxoxo);
        
        //7
		List<CredentialHeaderDAO> credentialListTourico = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListTourico = new ArrayList<OperationUrlDAO>();
        
        SupplierHeaderDAO supplierHeaderDAOTourico = new SupplierHeaderDAO("TOURICO", operation.equalsIgnoreCase("search") ? "7" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOTourico = new CredentialHeaderDAO("LOGINNAME","true","zA2c65+0ytk=");
	    CredentialHeaderDAO credentialHeaderDAOTourico1 = new CredentialHeaderDAO("PASSWORD","true","ZmdG3v7wOOM=");
	    
	    credentialListTourico.add(credentialHeaderDAOTourico);
	    credentialListTourico.add(credentialHeaderDAOTourico1);
	    supplierHeaderDAOTourico.setCredentials(credentialListTourico);
	    
	    OperationUrlDAO operationUrlDAOTourico = new OperationUrlDAO("all","http://demo-activithghhuyws.touricoholidays.com/ActivityBookFlow.svc/bas");
	    operationUrlListTourico.add(operationUrlDAOTourico);
	    supplierHeaderDAOTourico.setOperdao(operationUrlListTourico);
	    
        //8
        List<CredentialHeaderDAO> credentialListSportsEvent = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListSportsEvent = new ArrayList<OperationUrlDAO>();
        
        SupplierHeaderDAO supplierHeaderDAOSportsEvent = new SupplierHeaderDAO("SPORTSEVENTS365", operation.equalsIgnoreCase("search") ? "8" : sequence);
        
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent = new CredentialHeaderDAO("User","true","nI7CviiNE2c=");
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent1 = new CredentialHeaderDAO("PASSWORD","true","ccq6S+g9zGtejXpKQxPoQA==");
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent2 = new CredentialHeaderDAO("sitekey","true","zMWaRM2pDBPKqSz6jHeDMd9kLMrQ0wZDpZdSGHHueHsOBv31Bww6Dw==");
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent3 = new CredentialHeaderDAO("Source","true","HMUdXdZf4dqI81qBVkZB9KFNrzV4DvIF");
	    
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent);
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent1);
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent2);
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent3);
	    supplierHeaderDAOSportsEvent.setCredentials(credentialListSportsEvent);
	    
	    OperationUrlDAO operationUrlDAOSportsEvent = new OperationUrlDAO("search","https://api.sportsevents365.com/search/");
	    operationUrlListSportsEvent.add(operationUrlDAOSportsEvent);
	    OperationUrlDAO operationUrlDAOSportsEvent1 = new OperationUrlDAO("book","https://api.sportsevents365.com/order/");
	    operationUrlListSportsEvent.add(operationUrlDAOSportsEvent1);
	    supplierHeaderDAOSportsEvent.setOperdao(operationUrlListSportsEvent);
	    
	    //9
	    List<CredentialHeaderDAO> credentialListTraveller = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListTraveller = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOTraveller = new SupplierHeaderDAO("THETRAVELLER", operation.equalsIgnoreCase("search") ? "9" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOTraveller = new CredentialHeaderDAO("username","true","8ce0YNgj6RMGWlKX7NXzRg==");
	    CredentialHeaderDAO credentialHeaderDAOTraveller1 = new CredentialHeaderDAO("password","true","8ce0YNgj6RMGWlKX7NXzRg==");
	    credentialListTraveller.add(credentialHeaderDAOTraveller);
	    credentialListTraveller.add(credentialHeaderDAOTraveller1);
	    supplierHeaderDAOTraveller.setCredentials(credentialListTraveller);
	    
	    OperationUrlDAO operationUrlDAOTraveller = new OperationUrlDAO("all","http://119.81.52.166/XML/OUT/V2/UAT/index.aspx");
	    operationUrlListTraveller.add(operationUrlDAOTraveller);
	    supplierHeaderDAOTraveller.setOperdao(operationUrlListTraveller);
	   
	    //10
	    List<CredentialHeaderDAO> credentialListViator = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListViator = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOViator = new SupplierHeaderDAO("VIATOR", operation.equalsIgnoreCase("search") ? "10" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOViator = new CredentialHeaderDAO("apiKey","true","N5UlQB5xCtPqkDw8tiHkrJseTgs5pJGL");
	    
        credentialListViator.add(credentialHeaderDAOViator);
        supplierHeaderDAOViator.setCredentials(credentialListViator);
        
        OperationUrlDAO operationUrlDAOViator = new OperationUrlDAO("all","https://viatorapi.sandbox.viator.com");
        operationUrlListViator.add(operationUrlDAOViator);
        supplierHeaderDAOViator.setOperdao(operationUrlListViator);
        
        //11
	    List<CredentialHeaderDAO> credentialListCKIS = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListCKIS = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOCKIS = new SupplierHeaderDAO("CKIS", operation.equalsIgnoreCase("search") ? "11" : sequence);
	    
	    CredentialHeaderDAO credentialHeaderDAOCKIS = new CredentialHeaderDAO("client_id","false","1_39kovarbyeg4sss4og4ccwwkgo04g4o0oookc8kwcgs04kw0c8");
	    CredentialHeaderDAO credentialHeaderDAOCKIS1 = new CredentialHeaderDAO("client_secret","false","1a12bk9414n44wk8k4s48gkgckgo8go048cwo0wkwggs000o0k");
	    
        credentialListCKIS.add(credentialHeaderDAOCKIS);
        credentialListCKIS.add(credentialHeaderDAOCKIS1);
        supplierHeaderDAOCKIS.setCredentials(credentialListCKIS);
        
        OperationUrlDAO operationUrlDAOCKIS = new OperationUrlDAO("all","http://ckissvn.coxandkings.com/leopard/esb/web/app_dev.php");
        OperationUrlDAO operationUrlDAOCKIS1 = new OperationUrlDAO("getSession","http://tlm.coxandkings.com");
        /*OperationUrlDAO operationUrlDAOCKIS2 = new OperationUrlDAO("search","http://ckissvn.coxandkings.com");
        OperationUrlDAO operationUrlDAOCKIS3 = new OperationUrlDAO("book","http://tlm.coxandkings.com");
        OperationUrlDAO operationUrlDAOCKIS4 = new OperationUrlDAO("getdetails","http://tlm.coxandkings.com");*/
        operationUrlListCKIS.add(operationUrlDAOCKIS);
        operationUrlListCKIS.add(operationUrlDAOCKIS1);
        /*operationUrlListCKIS.add(operationUrlDAOCKIS2);
        operationUrlListCKIS.add(operationUrlDAOCKIS3);*/
        supplierHeaderDAOCKIS.setOperdao(operationUrlListCKIS);
       
        JSONObject documentAcampora = new JSONObject();
        documentAcampora.put("suppliercode", "CNKSAMC-1");
		documentAcampora.put("supplierName", supplierHeaderDAOAcampora.getSupplierId());
		documentAcampora.put("supplierdetails", gson.toJson(supplierHeaderDAOAcampora));
		documentAcampora.put("operation", operation);
		HttpEntity<String> requestAcampora = new HttpEntity(documentAcampora.toString(),headers);
		ResponseEntity<String> responseAcampora = template.exchange(new URI(URL), HttpMethod.POST, requestAcampora, String.class);
		
		JSONObject documentGta = new JSONObject();
		documentGta.put("suppliercode", "CNKSTAG-2");
		documentGta.put("supplierName", supplierHeaderDAOGta.getSupplierId());
		documentGta.put("supplierdetails", gson.toJson(supplierHeaderDAOGta));
		documentGta.put("operation", operation);
		HttpEntity<String> requestGta = new HttpEntity(documentGta.toString(),headers);
		ResponseEntity<String> responseGta = template.exchange(new URI(URL), HttpMethod.POST, requestGta, String.class);
		
		JSONObject documentBemyguest = new JSONObject();
		documentBemyguest.put("suppliercode", "CNKBSME-3");
		documentBemyguest.put("supplierName", supplierHeaderDAOBemyguest.getSupplierId());
		documentBemyguest.put("supplierdetails", gson.toJson(supplierHeaderDAOBemyguest));
		documentBemyguest.put("operation", operation);
		HttpEntity<String> requestBemyguest = new HttpEntity(documentBemyguest.toString(),headers);
		ResponseEntity<String> responseBemyguest = template.exchange(new URI(URL), HttpMethod.POST, requestBemyguest, String.class);
		
		JSONObject documentWhl = new JSONObject();
		documentWhl.put("suppliercode", "CNKWSLH-4");
		documentWhl.put("supplierName", supplierHeaderDAOWhl.getSupplierId());
		documentWhl.put("supplierdetails", gson.toJson(supplierHeaderDAOWhl));
		documentWhl.put("operation", operation);
		HttpEntity<String> requestWhl = new HttpEntity(documentWhl.toString(),headers);
		ResponseEntity<String> responseWhl = template.exchange(new URI(URL), HttpMethod.POST, requestWhl, String.class);
		
		JSONObject documentHotelbeds = new JSONObject();
		documentHotelbeds.put("suppliercode", "CNKHTOS-5");
		documentHotelbeds.put("supplierName", supplierHeaderDAOHotelbeds.getSupplierId());
		documentHotelbeds.put("supplierdetails", gson.toJson(supplierHeaderDAOHotelbeds));
		documentHotelbeds.put("operation", operation);
		HttpEntity<String> requestHotelbeds = new HttpEntity(documentHotelbeds.toString(),headers);
		ResponseEntity<String> responseHotelbeds = template.exchange(new URI(URL), HttpMethod.POST, requestHotelbeds, String.class);
		
		JSONObject documentGiftxoxo = new JSONObject();
		documentGiftxoxo.put("suppliercode", "CNKGSIF-6");
		documentGiftxoxo.put("supplierName", supplierHeaderDAOGiftxoxo.getSupplierId());
		documentGiftxoxo.put("supplierdetails", gson.toJson(supplierHeaderDAOGiftxoxo));
		documentGiftxoxo.put("operation", operation);
		HttpEntity<String> requestGiftxoxo = new HttpEntity(documentGiftxoxo.toString(),headers);
		ResponseEntity<String> responseGiftxoxo = template.exchange(new URI(URL), HttpMethod.POST, requestGiftxoxo, String.class);
		
		JSONObject documentTourico = new JSONObject();
		documentTourico.put("suppliercode", "CNKTUOS-7");
		documentTourico.put("supplierName", supplierHeaderDAOTourico.getSupplierId());
		documentTourico.put("supplierdetails", gson.toJson(supplierHeaderDAOTourico));
		documentTourico.put("operation", operation);
		HttpEntity<String> requestTourico = new HttpEntity(documentTourico.toString(),headers);
		ResponseEntity<String> responseTourico = template.exchange(new URI(URL), HttpMethod.POST, requestTourico, String.class);
		
		JSONObject documentSportsEvent = new JSONObject();
		documentSportsEvent.put("suppliercode", "CNKSOPOS-9");
		documentSportsEvent.put("supplierName", supplierHeaderDAOSportsEvent.getSupplierId());
		documentSportsEvent.put("supplierdetails", gson.toJson(supplierHeaderDAOSportsEvent));
		documentSportsEvent.put("operation", operation);
		HttpEntity<String> requestSportsEvent = new HttpEntity(documentSportsEvent.toString(),headers);
		ResponseEntity<String> responseSportsEvent = template.exchange(new URI(URL), HttpMethod.POST, requestSportsEvent, String.class);
		
		JSONObject documentTraveller = new JSONObject();
		documentTraveller.put("suppliercode", "CNKTSHE-10");
		documentTraveller.put("supplierName", supplierHeaderDAOTraveller.getSupplierId());
		documentTraveller.put("supplierdetails", gson.toJson(supplierHeaderDAOTraveller));
		documentTraveller.put("operation", operation);
		HttpEntity<String> requestTraveller = new HttpEntity(documentTraveller.toString(),headers);
		ResponseEntity<String> responseTraveller = template.exchange(new URI(URL), HttpMethod.POST, requestTraveller, String.class);
		
		JSONObject documentViator = new JSONObject();
		documentViator.put("suppliercode", "CNKVSIA-11");
		documentViator.put("supplierName", supplierHeaderDAOViator.getSupplierId());
		documentViator.put("supplierdetails", gson.toJson(supplierHeaderDAOViator));
		documentViator.put("operation", operation);
		HttpEntity<String> requestViator = new HttpEntity(documentViator.toString(),headers);
		ResponseEntity<String> responseViator = template.exchange(new URI(URL), HttpMethod.POST, requestViator, String.class);
        
		JSONObject documentCKIS = new JSONObject();
		documentCKIS.put("suppliercode", "CNKSKCI-12");
		documentCKIS.put("supplierName", supplierHeaderDAOCKIS.getSupplierId());
		documentCKIS.put("supplierdetails", gson.toJson(supplierHeaderDAOCKIS));
		documentCKIS.put("operation", operation);
		HttpEntity<String> requestCKIS = new HttpEntity(documentCKIS.toString(),headers);
		ResponseEntity<String> responseCKIS = template.exchange(new URI(URL), HttpMethod.POST, requestCKIS, String.class);
	
	}
	
}
