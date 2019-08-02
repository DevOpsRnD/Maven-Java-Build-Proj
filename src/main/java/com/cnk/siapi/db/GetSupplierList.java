package com.cnk.siapi.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.requestheader.CredentialHeaderDAO;
import com.cnk.siapi.model.requestheader.OperationUrlDAO;
import com.cnk.siapi.model.requestheader.SupplierHeaderDAO;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.google.gson.Gson;

@Service
public class GetSupplierList 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	Gson gson = new Gson();
	
	public List<SupplierHeaderDAO> getSupplierList(){
		
		List<SupplierHeaderDAO> supplierList = new ArrayList<SupplierHeaderDAO>();
		
		//1
	    List<CredentialHeaderDAO> credentialListAcampora = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListAcampora = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOAcampora = new SupplierHeaderDAO("ACAMPORA","1");
	    
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
        
	    SupplierHeaderDAO supplierHeaderDAOGta = new SupplierHeaderDAO("GTA","2");
        
	    CredentialHeaderDAO credentialHeaderDAOGta = new CredentialHeaderDAO("emailaddress","true","XML.DS@GTAINDIA.COM");
	    CredentialHeaderDAO credentialHeaderDAOGta1 = new CredentialHeaderDAO("password","true","PASS");
	    CredentialHeaderDAO credentialHeaderDAOGta2 = new CredentialHeaderDAO("client","true","2268");
	    credentialListGta.add(credentialHeaderDAOGta);
	    credentialListGta.add(credentialHeaderDAOGta1);
	    credentialListGta.add(credentialHeaderDAOGta2);
	    supplierHeaderDAOGta.setCredentials(credentialListGta);
	    
	    OperationUrlDAO operationUrlDAOGta = new OperationUrlDAO("all","https://interface.demo.gta-travel.com/gtaapi/RequestListenerServlet");
	    operationUrlListGta.add(operationUrlDAOGta);
	    supplierHeaderDAOGta.setOperdao(operationUrlListGta);
	    
	    //3
	    List<CredentialHeaderDAO> credentialListBemyguest = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListBemyguest = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOBemyguest = new SupplierHeaderDAO("BEMYGUEST","3");
	    
	    CredentialHeaderDAO credentialHeaderDAOBemyguest = new CredentialHeaderDAO("X-AUTHORIZATION","false","04e04c14f2767a60b6cbef8802cb3c18c250e015");
	    credentialListBemyguest.add(credentialHeaderDAOBemyguest);
        supplierHeaderDAOBemyguest.setCredentials(credentialListBemyguest);
        
        OperationUrlDAO operationUrlDAOBemyguest = new OperationUrlDAO("all","https://apidemo.bemyguest.com.sg/v1");
        operationUrlListBemyguest.add(operationUrlDAOBemyguest);
        supplierHeaderDAOBemyguest.setOperdao(operationUrlListBemyguest);
	    
        //4
        List<CredentialHeaderDAO> credentialListWhl = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListWhl = new ArrayList<OperationUrlDAO>();
        
        SupplierHeaderDAO supplierHeaderDAOWhl = new SupplierHeaderDAO("WHL","4");
        
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
        
	    SupplierHeaderDAO supplierHeaderDAOHotelbeds = new SupplierHeaderDAO("HOTELBEDS","5");
	    
	    CredentialHeaderDAO credentialHeaderDAOHotelbeds = new CredentialHeaderDAO("API-KEY","true","vqjD9+PIpGHCZtpFmDwapws3tSArG1tSBf7Z9RwkG9k=");
	    CredentialHeaderDAO credentialHeaderDAOHotelbeds1 = new CredentialHeaderDAO("SECRET","true","qn/MetCb4TK2UxUVsPNMqg==");
	    
	    credentialListHotelbeds.add(credentialHeaderDAOHotelbeds);
	    credentialListHotelbeds.add(credentialHeaderDAOHotelbeds1);
	    supplierHeaderDAOHotelbeds.setCredentials(credentialListHotelbeds);
	    
	    OperationUrlDAO operationUrlDAOHotelbeds = new OperationUrlDAO("all","https://api.test.hotelbeds.com/activity-api/3.0");
	    operationUrlListHotelbeds.add(operationUrlDAOHotelbeds);
	    supplierHeaderDAOHotelbeds.setOperdao(operationUrlListHotelbeds);
	    
	    //6
		List<CredentialHeaderDAO> credentialListGiftxoxo = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListGiftxoxo = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOGiftxoxo = new SupplierHeaderDAO("GIFTXOXO","6");
	    
	    CredentialHeaderDAO credentialHeaderDAOGiftxoxo = new CredentialHeaderDAO("KEY","false","d3f9f9b1cba34263aa565c10bed9ebf7Q294YW5kS2luZ3NMaW1pdGVkMTUwMDYxNjQxOTM1NXgwWDBkQHlDb3hhbmRLaW5ncw==");
	    
        credentialListGiftxoxo.add(credentialHeaderDAOGiftxoxo);
        supplierHeaderDAOGiftxoxo.setCredentials(credentialListGiftxoxo);
        
        OperationUrlDAO operationUrlDAOGiftxoxo = new OperationUrlDAO("all","http://35.162.160.37:8081");
        operationUrlListGiftxoxo.add(operationUrlDAOGiftxoxo);
        supplierHeaderDAOGiftxoxo.setOperdao(operationUrlListGiftxoxo);
        
        //7
		List<CredentialHeaderDAO> credentialListTourico = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListTourico = new ArrayList<OperationUrlDAO>();
        
        SupplierHeaderDAO supplierHeaderDAOTourico = new SupplierHeaderDAO("TOURICO","7");
	    
        
	    CredentialHeaderDAO credentialHeaderDAOTourico = new CredentialHeaderDAO("LOGINNAME","true","zA2c65+0ytk=");
	    CredentialHeaderDAO credentialHeaderDAOTourico1 = new CredentialHeaderDAO("PASSWORD","true","ZmdG3v7wOOM=");
	    
	    credentialListTourico.add(credentialHeaderDAOTourico);
	    credentialListTourico.add(credentialHeaderDAOTourico1);
	    supplierHeaderDAOTourico.setCredentials(credentialListTourico);
	    
	    OperationUrlDAO operationUrlDAOTourico = new OperationUrlDAO("search","http://demo-activityws.touricoholidays.com/ActivityBookFlow.svc/bas");
	    operationUrlListTourico.add(operationUrlDAOTourico);
	    supplierHeaderDAOTourico.setOperdao(operationUrlListTourico);
	    
	    //8
	    List<CredentialHeaderDAO> credentialListGetYourGuide = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListGetYourGuide = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOGetYourGuide = new SupplierHeaderDAO("GETYOURGUIDE","8");
	    
	    CredentialHeaderDAO credentialHeaderDAOGetYourGuide = new CredentialHeaderDAO("X-ACCESS-TOKEN","false","SRfNOQylBBKyFmCaJDVVuIcDdIqwu4cATHETZOHPKmT3aZIP");
	    credentialListGetYourGuide.add(credentialHeaderDAOGetYourGuide);
        supplierHeaderDAOGetYourGuide.setCredentials(credentialListGetYourGuide);
        
        OperationUrlDAO operationUrlDAOGetYourGuide = new OperationUrlDAO("all","https://api-getyourguide-com.partner.gygtest.com/1/tours");
        operationUrlListGetYourGuide.add(operationUrlDAOGetYourGuide);
        supplierHeaderDAOGetYourGuide.setOperdao(operationUrlListGetYourGuide);
	    
        //9
        List<CredentialHeaderDAO> credentialListSportsEvent = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListSportsEvent = new ArrayList<OperationUrlDAO>();
        
        SupplierHeaderDAO supplierHeaderDAOSportsEvent = new SupplierHeaderDAO("SPORTSEVENTS365","9");
        
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent = new CredentialHeaderDAO("User","true","nI7CviiNE2c=");
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent1 = new CredentialHeaderDAO("PASSWORD","true","ccq6S+g9zGtejXpKQxPoQA==");
	    CredentialHeaderDAO credentialHeaderDAOSportsEvent2 = new CredentialHeaderDAO("sitekey","true","zMWaRM2pDBPKqSz6jHeDMd9kLMrQ0wZDpZdSGHHueHsOBv31Bww6Dw==");
	    
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent);
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent1);
	    credentialListSportsEvent.add(credentialHeaderDAOSportsEvent2);
	    supplierHeaderDAOSportsEvent.setCredentials(credentialListSportsEvent);
	    
	    OperationUrlDAO operationUrlDAOSportsEvent = new OperationUrlDAO("search","https://api.sportsevents365.com/search/");
	    operationUrlListSportsEvent.add(operationUrlDAOSportsEvent);
	    supplierHeaderDAOSportsEvent.setOperdao(operationUrlListSportsEvent);
	    
	    //10
	    List<CredentialHeaderDAO> credentialListTraveller = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListTraveller = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOTraveller = new SupplierHeaderDAO("THETRAVELLER","10");
	    
	    CredentialHeaderDAO credentialHeaderDAOTraveller = new CredentialHeaderDAO("username","true","8ce0YNgj6RMGWlKX7NXzRg==");
	    CredentialHeaderDAO credentialHeaderDAOTraveller1 = new CredentialHeaderDAO("password","true","8ce0YNgj6RMGWlKX7NXzRg==");
	    credentialListTraveller.add(credentialHeaderDAOTraveller);
	    credentialListTraveller.add(credentialHeaderDAOTraveller1);
	    supplierHeaderDAOTraveller.setCredentials(credentialListTraveller);
	    
	    OperationUrlDAO operationUrlDAOTraveller = new OperationUrlDAO("all","http://119.81.52.166/XML/OUT/V2/UAT/index.aspx");
	    operationUrlListTraveller.add(operationUrlDAOTraveller);
	    supplierHeaderDAOTraveller.setOperdao(operationUrlListTraveller);
	    
	    //11
	    List<CredentialHeaderDAO> credentialListViator = new ArrayList<CredentialHeaderDAO>();
        List<OperationUrlDAO> operationUrlListViator = new ArrayList<OperationUrlDAO>();
        
	    SupplierHeaderDAO supplierHeaderDAOViator = new SupplierHeaderDAO("VIATOR","11");
	    
	    CredentialHeaderDAO credentialHeaderDAOViator = new CredentialHeaderDAO("apiKey","true","N5UlQB5xCtPqkDw8tiHkrJseTgs5pJGL");
	    
        credentialListViator.add(credentialHeaderDAOViator);
        supplierHeaderDAOViator.setCredentials(credentialListViator);
        
        OperationUrlDAO operationUrlDAOViator = new OperationUrlDAO("all","http://prelive.viatorapi.viator.com");
        operationUrlListViator.add(operationUrlDAOViator);
        supplierHeaderDAOViator.setOperdao(operationUrlListViator);
        
	    supplierList.add(supplierHeaderDAOAcampora);
        supplierList.add(supplierHeaderDAOGta);
        supplierList.add(supplierHeaderDAOBemyguest);
        supplierList.add(supplierHeaderDAOWhl);
        supplierList.add(supplierHeaderDAOHotelbeds);
        supplierList.add(supplierHeaderDAOGiftxoxo);
        supplierList.add(supplierHeaderDAOTourico);
        supplierList.add(supplierHeaderDAOGetYourGuide);
        supplierList.add(supplierHeaderDAOSportsEvent);
        supplierList.add(supplierHeaderDAOTraveller);
        supplierList.add(supplierHeaderDAOViator);
		
		return supplierList;
	}
	
	public List<SupplierHeaderDAO> getSupplierListFromDB(Set<String> supplierName,String collectionPartName){
		
		List<SupplierHeaderDAO> supplierList = new ArrayList<SupplierHeaderDAO>();
		
		try {
			
			String collectionName = collectionPartName + "supplierlist";
			MongoOperations mongoOperations = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
			List<SupplierCredentials> supplierDataList = mongoOperations.findAll(SupplierCredentials.class , collectionName);

			for(SupplierCredentials supplierCredentials : supplierDataList)	{
				SupplierHeaderDAO supplierDao= gson.fromJson(supplierCredentials.getSupplierHeaderDAO().toString() , SupplierHeaderDAO.class);
				for(String supplier : supplierName)
				{
					if(supplierDao.getSupplierId().equalsIgnoreCase(supplier))
					{
						supplierList.add(supplierDao);
						logger.info(supplierDao.getSupplierId());
					}
				}
			}
			return supplierList;
		} catch(Exception e)
		{
			logger.error("Exception occur",e);
			return null;
		}
	}

	public SupplierCredentials getSupplierFromDB(String searchSupplierBy, String value,String collectionName)  
	{
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
		Query query = new Query();
		query.addCriteria(Criteria.where(searchSupplierBy).is(value));
		
		SupplierCredentials supplierData = (SupplierCredentials) mongoInstance.findOne(query, SupplierCredentials.class, collectionName);
		
		if(supplierData != null) {
			logger.info("Supplier details found : "+supplierData.toString());
			return supplierData;
		}
		else {
//			logger.info("Supplier Credentials present or Invalid " + searchSupplierBy.substring(0, 7) + " " + searchSupplierBy.substring(7));
			return null;
		}
	}
	
	public String getSupplierCodeFromDB(String searchSupplierBy, String value,String collectionName)  
	{
		MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME));
		Query query = new Query();
		query.addCriteria(Criteria.where(searchSupplierBy).is(value));
		
		SupplierCredentials supplierDetails =  mongoInstance.findOne(query, SupplierCredentials.class, collectionName);
		
		String supplierCode = supplierDetails.getSupplierCode();
		if(supplierCode != null) {
			logger.info("Supplier Code found : "+supplierCode.toString());
			return supplierCode;
		}
		else {
			logger.info("Supplier Code not Found");
			return null;
		}
	}
}
