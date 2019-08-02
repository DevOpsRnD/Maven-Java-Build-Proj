package com.cnk.siapi.model.getdetails;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.config.RandomKeyConfig;
import com.cnk.siapi.model.requestheader.RequestHeaderDAO;
import com.cnk.siapi.model.requestheader.SupplierHeaderDAO;
import com.cnk.siapi.model.storerequst.GetDetailsRequestResponse;
import com.cnk.siapi.response.CommonGetDetailsObjectFactory;
import com.cnk.siapi.response.CommonGetDetailsRS;
import com.cnk.siapi.response.CommonGetDetailsRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo;
import com.cnk.siapi.response.CommonGetDetailsRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.Pricing.ParticipantCategory;
import com.cnk.siapi.response.CommonGetDetailsRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.ProductOptions;
import com.cnk.siapi.response.CommonGetDetailsRS.ResponseBody.OTATourActivityAvailRSWrapper.OTATourActivityAvailRS.TourActivityInfo.ProductOptions.ProductOption;
import com.cnk.siapi.supplier.AccessToken;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.google.gson.Gson;


@Service
public class GetDetailsRequestImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());

//	File file;
	String supplierCode;
	String responseData;
	String supplierProductCodeNew;
	Gson gson = new Gson();
	
	public Object createGetdetailsRequest(GetDetailsRQBody getDetailsRequestData, HttpServletRequest request) throws JSONException
	{
		JAXBContext jc;
		JSONObject responseJson = new JSONObject();
		AccessToken accessToken =  new AccessToken();
		List<SupplierHeaderDAO> supplierList = new ArrayList<SupplierHeaderDAO>();
		String reqDataType = request.getHeader("Accept");
		String userId;
		try
		{
			userId = request.getUserPrincipal().getName();
		}
		catch(NullPointerException ex)
		{
			userId = "testUser";
		}

		try {
			String token = getDetailsRequestData.getAccessCode();
			if(token == null || token.equalsIgnoreCase(""))
			{
				String accessCode = "CKIS" + RandomKeyConfig.generateRandom();
				long expMillis = System.currentTimeMillis() + 86_400_000;
				Date tokenExpiryTime = new Date(expMillis);
				
				accessToken.setAccessToken(accessCode);
				accessToken.setSupplierCode(getDetailsRequestData.getSupplierId());
				accessToken.setUsername(getDetailsRequestData.getSupplierId());
				accessToken.setTokenExpireTime(tokenExpiryTime);
				loadClassesAuto.supplierOperations.saveAccessToken(accessToken);
				getDetailsRequestData.setAccessCode(accessToken.getAccessToken());
			}
			
			responseJson = loadClassesAuto.supplierOperations.isTokenValid(getDetailsRequestData.getAccessCode(),new Date(), Constants.VALID);
			
			if( (responseJson.getBoolean(Constants.STATUS)) ||
					(!responseJson.getBoolean(Constants.STATUS) && responseJson.get(Constants.DESCRIPTION).toString().equalsIgnoreCase("Token is already expired"))) 
			{
				while(responseJson.length()>0)
					responseJson.remove((String) responseJson.keys().next());

				GetDetailsRequest getDetailsRQ = new GetDetailsRequest();
		        RequestHeaderDAO requestHeader = new RequestHeaderDAO();
		
		        requestHeader.setUserId(userId);
		        requestHeader.setSessionId(getDetailsRequestData.getAccessCode());
		        requestHeader.setTransactionId(getDetailsRequestData.getAccessCode());
		        
			    String collectionName = "getdetails" + Constants.SUPPLIER_LIST;
		        supplierCode = getDetailsRequestData.getSupplierId().toString();
		        SupplierCredentials supplierDetails = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYCODE, supplierCode, collectionName);
				
				if(supplierDetails == null)
				{
					responseJson.put(Constants.CODE, Constants.NOT_FOUND);
					responseJson.put(Constants.STATUS, false);
					responseJson.put(Constants.DESCRIPTION, "Invalid Supplier Code");
					logger.info("Invalid Supplier Code");
					return responseJson;
				}
				SupplierHeaderDAO supplierHeaderDAO = gson.fromJson( supplierDetails.getSupplierHeaderDAO().toString() , SupplierHeaderDAO.class );
			    supplierList.add(supplierHeaderDAO);
			    getDetailsRequestData.setSupplierId(supplierHeaderDAO.getSupplierId());
			    
			    requestHeader.setSupplierDao(supplierList);
		        getDetailsRQ.setRequestHeader(requestHeader);
		        
		        // Change the Received supplier product code and update it to correct product code
		        supplierProductCodeNew =  getDetailsRequestData.getSupplierProductCode().substring(getDetailsRequestData.getRegionCode().replace("-", "").length());
		        
		        getDetailsRequestData.setSupplierProductCode(supplierProductCodeNew);
		        getDetailsRQ.setGetDetailsBody(getDetailsRequestData);
	        	
	        	jc = JAXBContext.newInstance(GetDetailsRequest.class);
	        	Marshaller marshaller;
	        	marshaller = jc.createMarshaller();
	        	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	        	
	        	StringWriter sw = new StringWriter();
	        	marshaller.marshal(getDetailsRQ, sw);
	        	String xmlRequest = sw.toString();
	        	logger.info(xmlRequest);
	        	
	        	ClientConfig config = new ClientConfig();
	        	config.property(ClientProperties.READ_TIMEOUT, 1000000);
				config.property(ClientProperties.CONNECT_TIMEOUT, 1000000);
	        	Client client = ClientBuilder.newClient(config);
	        	String getdetailsUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETDETAILS_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
	        	WebTarget target = client.target(getdetailsUrl);
	        	Response integrationResponse = (Response) target.request().accept(MediaType.APPLICATION_XML).post(Entity.xml(xmlRequest));
	        	
	        	if(integrationResponse.getStatus() == HttpStatus.OK.value()) 
				{
	        		InputStream inputStream = (InputStream) integrationResponse.getEntity();
					responseData = IOUtils.toString( inputStream , "UTF-8");
	        		logger.info("Integration Response Data : "+responseData);
					
	        		/*String fileName = "Getdetails_" + RandomKeyConfig.generateRandom() + ".xml";
					file = new File(fileName);
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(responseData);
					fileWriter.flush();
					fileWriter.close();*/
					
					JAXBContext jaxbContext = JAXBContext.newInstance(CommonGetDetailsRS.class, CommonGetDetailsObjectFactory.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					CommonGetDetailsRS getDetailsRS = (CommonGetDetailsRS) jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(responseData));
					
					String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
					MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
					
					String integrationStatus = null;
					
					try { 
						
						integrationStatus = getDetailsRS.getResponseBody().getOTATourActivityAvailRSWrapper().getOTATourActivityAvailRS().getSuccess();
						getDetailsRS.getResponseBody().getOTATourActivityAvailRSWrapper().setAccessCode(getDetailsRequestData.getAccessCode());
						getDetailsRS.getResponseBody().getOTATourActivityAvailRSWrapper().setSupplierID(supplierCode);
						getDetailsRS.getResponseBody().setCnkStatus(Constants.TRUE);
						
					} catch (NullPointerException npe) {
						logger.info("SI response not contain success tag");
						getDetailsRS.getResponseBody().setCnkStatus(Constants.FALSE);
					}
					
					logger.info("Integration status : "+integrationStatus);
					
					if( integrationStatus != null && (integrationStatus.equalsIgnoreCase(Constants.TRUE) || integrationStatus.equalsIgnoreCase(Constants.SUCCESS)) )
					{
						//Change the Supplier Product Code after getting the response
						
						List<TourActivityInfo> tourActivityInfoList = getDetailsRS.getResponseBody().getOTATourActivityAvailRSWrapper().getOTATourActivityAvailRS().getTourActivityInfo();
						Iterator<TourActivityInfo> itr = tourActivityInfoList.iterator();
						
						while(itr.hasNext())
						{
							TourActivityInfo activityInfo = new TourActivityInfo();
							activityInfo = itr.next();
							
							String regionString = activityInfo.getLocation().getRegion().getRegionCode();
							regionString = regionString.replace("-", "");
							String productCodeNew = regionString + activityInfo.getBasicInfo().getSupplierProductCode();
							activityInfo.getBasicInfo().setSupplierProductCode(productCodeNew);
							
							// Update the Amount and add MarkupAmount
							
							if(activityInfo.getPricing().getAvailable().equals("true"))
							{
								Float markup = null;
								if(activityInfo.getPricing().getSummary() != null) 
								{
									if(!activityInfo.getPricing().getSummary().getAmount().toString().equalsIgnoreCase("NA"))
									{
										Float summaryAmount = Float.parseFloat(activityInfo.getPricing().getSummary().getAmount());
										markup = (float) (summaryAmount  * 1.13);
										activityInfo.getPricing().getSummary().setAmount(String.valueOf(markup));
									}
								}					
								// Setting the Per Pax Amount
								
								List<ParticipantCategory> pricingL = activityInfo.getPricing().getParticipantCategory();
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
			
						if(supplierCode.equalsIgnoreCase("CNKVSIA-11") || supplierCode.equalsIgnoreCase("CNKSOPOS-9") || 
								supplierCode.equalsIgnoreCase("CNKBSME-3") || supplierCode.equalsIgnoreCase("CNKGSIF-6")||
								supplierCode.equalsIgnoreCase("CNKHTOS-5"))  
						{
							if(tourActivityInfoList.size() > 1)
							{
								List<ProductOption> productOptionList = new ArrayList<>();
								ProductOptions productOptions = new ProductOptions();
								int tourActivityInfoListSize = tourActivityInfoList.size();
								
								for(int i = 0 ; i < tourActivityInfoListSize;)
								{
									ProductOption productOption = new ProductOption();
									productOption.setBasicInfo(tourActivityInfoList.get(i).getBasicInfo());
									productOption.setDescription(tourActivityInfoList.get(i).getDescription());
									productOption.setExtras(tourActivityInfoList.get(i).getExtras());
									productOption.setLocation(tourActivityInfoList.get(i).getLocation());
									productOption.setPricing(tourActivityInfoList.get(i).getPricing());
									productOption.setSchedule(tourActivityInfoList.get(i).getSchedule());
									
									productOptionList.add(productOption);
									if(i == 0)
										i++;
									else {
										tourActivityInfoList.remove(i);
										if( tourActivityInfoList.size() == 1)
											break;
									}
								}
								productOptions.setProductOption(productOptionList);
								tourActivityInfoList.get(0).setProductOptions(productOptions);
							}
						}
					}
					else {
						getDetailsRS.getResponseBody().setCnkStatus(Constants.FALSE);
						logger.info("Get details request failed as integration status is false or blank");
					}
					// Save the Response in Database
					GetDetailsRequestResponse getdetailsReqResp = new GetDetailsRequestResponse();
					getdetailsReqResp.setAccessToken(getDetailsRequestData.getAccessCode());
					getdetailsReqResp.setOriginalResponse((org.json.simple.JSONObject)new JSONParser().parse(XML.toJSONObject(responseData).toString()));
					getdetailsReqResp.setGetdetailsRequest(getDetailsRQ);
					getdetailsReqResp.setGetdetailsResponse(getDetailsRS);
					getdetailsReqResp.setCreationBy(userId);
					getdetailsReqResp.setCreationDate(new Date());
					getdetailsReqResp.setIp(request.getLocalAddr());
					getdetailsReqResp.setSupplierId(supplierHeaderDAO.getSupplierId());
					mongoInstance.insert(getdetailsReqResp);
					
//					file.delete();
					logger.info("Getdetails request status : " + integrationStatus);
						
					if(reqDataType.equals("application/xml"))
					{
						jc = JAXBContext.newInstance(CommonGetDetailsRS.class);
						marshaller = jc.createMarshaller();
						marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						StringWriter sw1 = new StringWriter();
						marshaller.marshal(getDetailsRS, sw1);
						xmlRequest = sw1.toString();
						return xmlRequest;
					}
					else
						return getDetailsRS;
				} 
	        	else {
					
	        		logger.info("Integration Response : "+integrationResponse);
					responseJson.put(Constants.CODE, integrationResponse.getStatus());
					responseJson.put(Constants.STATUS, Constants.FAILED);
					responseJson.put(Constants.DESCRIPTION, HttpStatus.valueOf(integrationResponse.getStatus()).name());
					return responseJson;
				}
			}else
				return responseJson;
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