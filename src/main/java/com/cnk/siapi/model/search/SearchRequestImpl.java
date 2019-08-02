package com.cnk.siapi.model.search;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.config.RandomKeyConfig;
import com.cnk.siapi.model.requestheader.RequestHeaderDAO;
import com.cnk.siapi.model.requestheader.SupplierHeaderDAO;
import com.cnk.siapi.model.storerequst.SearchRequestResponse;
import com.cnk.siapi.response.MultiSearchObjectFactory;
import com.cnk.siapi.response.MultiSearchRS;
import com.cnk.siapi.response.MultiSearchRS.ResponseBody.OTATourActivityAvailRSWrapper;
import com.cnk.siapi.response.MultiSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo;
import com.cnk.siapi.response.MultiSearchRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory;
import com.cnk.siapi.supplier.AccessToken;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.google.gson.Gson;

@Service

public class SearchRequestImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
//	File file;
	String xmlRequest;
	String responseData;
	Gson gson = new Gson();
	List<SupplierHeaderDAO> supplierList;
	SearchRequestResponse searchReqResp = new SearchRequestResponse();

	public Object createSearchRequest(SearchRQBody searchRequestData, HttpServletRequest request) throws JSONException
	{
		JAXBContext jc;
		JSONObject responseJson = new JSONObject();
		Set<String> supplierName = new HashSet<String>();
		String dataRequest = request.getHeader("Accept");
		String userId;
		try
		{
			userId = request.getUserPrincipal().getName();
		}
		catch(NullPointerException e)
		{
			userId = "testUser";
		}
		
		try {
			
			String operation = "search";
			String regionCode = searchRequestData.getRegionCode();
			String SessionTransactionId = "CKIS" + RandomKeyConfig.generateRandom();
	
//			Will connect to Matt's system and get the City Code from Country Code
			RestTemplate template = new RestTemplate();
		
			ResponseEntity<String> responseEntity = template
					.getForEntity(loadClassesAuto.mongoDBOperation.getURL(Constants.SEARCHCITY_BYCODE_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT) ) + regionCode, String.class);
//					.getForEntity(loadClassesAuto.applicationProperties.getProperty(Constants.SEARCHCITY_BYCODE_URL) + regionCode, String.class);
//					.getForEntity("http://preprod-lb-wapi-632830701.ap-south-1.elb.amazonaws.com/Mapping/System/City/CityCode/" + regionCode, String.class);
			
			String response = responseEntity.getBody();
			JSONArray supplierCodeArray = new JSONArray(response);

			for (int i = 0; i < supplierCodeArray.length(); i++) {
				JSONObject jsonObj = supplierCodeArray.getJSONObject(i);
				String supplierCode = jsonObj.getString("supplierCode");
				supplierName.add(supplierCode);
			}
			System.out.println(searchRequestData.getSupplierCode());

			if(searchRequestData.getSupplierCode() == null || searchRequestData.getSupplierCode().equals(""))
			{
				supplierList = loadClassesAuto.getSupplierList.getSupplierListFromDB(supplierName, operation);
				logger.info("Supplier list match with third party list : " + supplierList);
				
				if (supplierList == null || supplierList.size() == 0 ) {
					responseJson.put(Constants.CODE, Constants.NOT_FOUND);
					responseJson.put(Constants.STATUS, Constants.FAILED);
					responseJson.put(Constants.DESCRIPTION, "Supplier not found");
					logger.info("Supplier not found");
					return responseJson;
				}
			}
			else
			{
				System.out.println(searchRequestData.getSupplierCode());
				String collectionName = operation + "supplierlist";
				SupplierCredentials supplierDetails = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYCODE, searchRequestData.getSupplierCode(), collectionName);
				logger.info("Supplier list match with third party list : " + supplierDetails);
				
				SupplierHeaderDAO supplierHeaderDAO = gson.fromJson( supplierDetails.getSupplierHeaderDAO().toString() , SupplierHeaderDAO.class );
				supplierList = new ArrayList<SupplierHeaderDAO>();
				supplierList.add(supplierHeaderDAO);
			    
			}
			
			if (supplierList == null || supplierList.size() == 0 ) {
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, Constants.FAILED);
				responseJson.put(Constants.DESCRIPTION, "Supplier not found");
				logger.info("Supplier not found");
				return responseJson;
//				supplierList = loadClassesAuto.getSupplierList.getSupplierList();
			}
			SearchRequest searchRQ = new SearchRequest();
			RequestHeaderDAO requestHeader = new RequestHeaderDAO();
	
			SearchRQBody searchBody = new SearchRQBody();
			searchBody.setPriceCurrency("");
	
			// Multisearch
			requestHeader.setUserId(userId);
			requestHeader.setSessionId(SessionTransactionId);
			requestHeader.setTransactionId(SessionTransactionId);
	
			requestHeader.setSupplierDao(supplierList);
			searchRQ.setRequestHeader(requestHeader);
			searchRQ.setSearchBody(searchRequestData);

			jc = JAXBContext.newInstance(SearchRequest.class);
			Marshaller marshaller;
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			StringWriter sw = new StringWriter();
			marshaller.marshal(searchRQ, sw);
			xmlRequest = sw.toString();
			logger.info(xmlRequest);

			ClientConfig config = new ClientConfig();	
			
			config.property(ClientProperties.READ_TIMEOUT, 100000);
			config.property(ClientProperties.CONNECT_TIMEOUT, 100000);
			Client client = ClientBuilder.newClient(config);
			String searchUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.SEARCH_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
			WebTarget target = client.target(searchUrl).property("http.connection.timeout", 0)
					.property("http.receive.timeout", 0);
			Response integrationResponse = (Response) target.request().accept(MediaType.APPLICATION_XML).post(Entity.xml(xmlRequest));

			if (integrationResponse.getStatus() == HttpStatus.OK.value()) 
			{
				InputStream inputStream = (InputStream) integrationResponse.getEntity();
				responseData = IOUtils.toString( inputStream , "UTF-8");
				logger.info("Integration Response Data : "+responseData);
				
				/*String fileName = "Search_" + RandomKeyConfig.generateRandom() + ".xml";
				file = new File(fileName);
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(responseData);
				fileWriter.flush();
				fileWriter.close();*/
				
				JAXBContext jaxbContext = JAXBContext.newInstance(MultiSearchRS.class, MultiSearchObjectFactory.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				MultiSearchRS multiSearchRS = (MultiSearchRS) jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(responseData));
				
				String integrationStatus = null;
				
				try { 
					integrationStatus = multiSearchRS.getResponseBody().getOTATourActivityAvailRSWrapper().get(0).getOTATourActivityAvailRS().getSuccess();
					multiSearchRS.getResponseBody().setCnkStatus(Constants.TRUE);
					
				} catch (NullPointerException npe) {
					logger.info("SI response not contain success tag");
					multiSearchRS.getResponseBody().setCnkStatus(Constants.FALSE);
				}
				
				logger.info("Integration status : "+integrationStatus);
				
				if (multiSearchRS.getResponseBody().getOTATourActivityAvailRSWrapper().size() > 1 ||
						(integrationStatus != null && (integrationStatus.equalsIgnoreCase(Constants.TRUE) || integrationStatus.equalsIgnoreCase(Constants.SUCCESS))) )
				{
					logger.info("Manipulate search response");
					MultiSearchRS.ResponseBody responseBody = multiSearchRS.getResponseBody();
					
					List<OTATourActivityAvailRSWrapper> listOTA = multiSearchRS.getResponseBody().getOTATourActivityAvailRSWrapper();
					List<OTATourActivityAvailRSWrapper> listOTANew = new ArrayList<>();
					Iterator<OTATourActivityAvailRSWrapper> itr = listOTA.iterator();
					String supplierId = null;
					String accessCode = null;
					while (itr.hasNext())
					{
						OTATourActivityAvailRSWrapper wrapper = new OTATourActivityAvailRSWrapper();
						wrapper = itr.next();
						
						try
						{
							supplierId = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYNAME,wrapper.getSupplierID(), "searchsupplierlist").getSupplierCode();
							accessCode = "CKIS" + RandomKeyConfig.generateRandom();
							
							wrapper.setSupplierID(supplierId);
							wrapper.setAccessCode(accessCode);
							
							logger.info("In Supplier Change");
						}
						catch(NullPointerException e)
						{
							responseJson.put(Constants.CODE, integrationResponse.getStatus());
							responseJson.put(Constants.STATUS, Constants.FAILED);
							responseJson.put(Constants.DESCRIPTION, "SupplierId is blank");
							logger.error(responseJson);
//							return responseJson;
						}
						
						// Change the Product Code and Merge it with Region Code
						List<TourActivityInfo> productCodeL = wrapper.getOTATourActivityAvailRS().getTourActivityInfo();
						Iterator<TourActivityInfo> productItr = productCodeL.iterator();
						
						while(productItr.hasNext())
						{
							TourActivityInfo productWrapper = new TourActivityInfo();
							productWrapper = productItr.next();
							String regionString = productWrapper.getLocation().getRegion().getRegionCode();
							regionString = regionString.replace("-", "");
							String productCodeNew = regionString + productWrapper.getBasicInfo().getSupplierProductCode();
							productWrapper.getBasicInfo().setSupplierProductCode(productCodeNew);
							
							
							if(productWrapper.getPricing().getAvailable().equals("true"))
							{
								Float markup = null;
								if(productWrapper.getPricing().getSummary() != null) 
								{
									if(!productWrapper.getPricing().getSummary().getAmount().toString().equalsIgnoreCase("NA")) {
										// Setting the Summary Amount
										Float summaryAmount =  Float.parseFloat(productWrapper.getPricing().getSummary().getAmount());
										markup = (float) (summaryAmount  * 1.13);
										productWrapper.getPricing().getSummary().setAmount(String.valueOf(markup));
									}
								}
								
								// Setting the Per Pax Amount
								
								List<ParticipantCategory> pricingL = productWrapper.getPricing().getParticipantCategory();
								Iterator<ParticipantCategory> pricintItr = pricingL.iterator();
								
								while(pricintItr.hasNext())
								{
									ParticipantCategory participantAmount = new ParticipantCategory();
									participantAmount = pricintItr.next();
									Float amount = participantAmount.getPrice().getAmount();
									markup = (float) (amount  * 1.13);
									participantAmount.getPrice().setAmount(markup);
								}
							}
						}
						if(wrapper.getOTATourActivityAvailRS().getTourActivityInfo().isEmpty())
						{
							logger.info("Error from supplier " + wrapper.getSupplierID());
						}
						else
						{
							listOTANew.add(wrapper);
						}
						long expMillis = System.currentTimeMillis() + 86_400_000;
						Date tokenExpiryTime = new Date(expMillis);
//						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
//						String tokenExpiryTime = simpleDateFormat.format(date);
						
						AccessToken accessToken =  new AccessToken();
						accessToken.setAccessToken(accessCode);
						accessToken.setSupplierCode(supplierId);
						accessToken.setUsername(supplierId);
						accessToken.setTokenExpireTime(tokenExpiryTime);
						loadClassesAuto.supplierOperations.saveAccessToken(accessToken);
						
						//Save the Response in Database
						searchReqResp.setAccessToken(accessCode);
						searchReqResp.setOriginalResponse((org.json.simple.JSONObject)new JSONParser().parse(XML.toJSONObject(responseData).toString()));
						searchReqResp.setSearchRequest((org.json.simple.JSONObject)new JSONParser().parse(XML.toJSONObject(xmlRequest).toString()));
						searchReqResp.setSearchResponse((org.json.simple.JSONObject)new JSONParser().parse(gson.toJson(multiSearchRS)));
						searchReqResp.setRegionCode(searchRequestData.getRegionCode());
						searchReqResp.setCountryCode(searchRequestData.getCountryCode());
						searchReqResp.setStartDate(searchRequestData.getStartDate());
						searchReqResp.setEndDate(searchRequestData.getEndDate());
						searchReqResp.setCreationBy(userId);
						searchReqResp.setCreationDate(new Date());
						searchReqResp.setIp(request.getLocalAddr());
//						mongoInstance.insert(searchReqResp);
					}
					responseBody.setOtaTourActivityAvailRSWrapper(listOTANew);
					multiSearchRS.setResponseBody(responseBody);
					
//					file.delete();
					logger.info("Search request success");
					
				} else {
					
					String oldSupplierId = multiSearchRS.getResponseBody().getOTATourActivityAvailRSWrapper().get(0).getSupplierID();
					String supplierId = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYNAME,oldSupplierId, "searchsupplierlist").getSupplierCode();
					multiSearchRS.getResponseBody().getOTATourActivityAvailRSWrapper().get(0).setSupplierID(supplierId);
					multiSearchRS.getResponseBody().setCnkStatus(Constants.FALSE);
					logger.info("Search request failed as integration status is false or blank");
				}
				
				if(dataRequest.equals("application/xml"))
				{
					jc = JAXBContext.newInstance(MultiSearchRS.class);
					marshaller = jc.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					StringWriter sw1 = new StringWriter();
					marshaller.marshal(multiSearchRS, sw1);
					xmlRequest = sw1.toString();
					return xmlRequest;
				}
				else
					return multiSearchRS;
			} 
			else {

				logger.info("Integration Response : "+integrationResponse);
				responseJson.put(Constants.CODE, integrationResponse.getStatus());
				responseJson.put(Constants.STATUS, Constants.FAILED);
				responseJson.put(Constants.DESCRIPTION, HttpStatus.valueOf(integrationResponse.getStatus()).name());
				return responseJson;
			}
			
		} catch (JAXBException e) {
        	logger.error("Exception occur while pasing Json data",e);
		} catch (SocketTimeoutException ste) { 
			responseJson.put(Constants.STATUS, Constants.FAILED);
			responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
			responseJson.put(Constants.DESCRIPTION, "Unable to connect the destination");
		} catch (ConnectException ste) { 
			responseJson.put(Constants.STATUS, Constants.FAILED);
			responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
			responseJson.put(Constants.DESCRIPTION, "Unable to connect the destination");
		} catch (IOException e) {
			logger.error("IOException occur",e);
		} catch (Exception e) {
			responseJson.put(Constants.STATUS, Constants.FAILED);
			responseJson.put(Constants.CODE, HttpStatus.BAD_REQUEST);
			responseJson.put(Constants.DESCRIPTION, "Something went wrong");
			logger.error("Exception occur",e);
		} finally {
			/*if(file != null && file.exists())
				file.delete();*/
		}
		return responseJson;
	}

}