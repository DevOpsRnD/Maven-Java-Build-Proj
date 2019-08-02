package com.cnk.siapi.model.retrieve;

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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.book.BookingDetails;
import com.cnk.siapi.model.requestheader.RequestHeaderDAO;
import com.cnk.siapi.model.requestheader.SupplierHeaderDAO;
import com.cnk.siapi.model.storerequst.RetrieveRequestResponse;
import com.cnk.siapi.response.CommonRetrieveObjectFactory;
import com.cnk.siapi.response.CommonRetrieveRS;
import com.cnk.siapi.response.CommonRetrieveRS.ResponseBody.OTATourActivityResRetrieveRSIntWrapper.OTATourActivityResRetrieveRS.Detail.Pricing.ParticipantCategory;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.google.gson.Gson;

@Service
public class RetrieveRequestImpl
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
//	File file;
	String supplierCode;
	String responseData;
	Gson gson = new Gson();
	
	public Object createRetrieveRequest(RetrieveRQBody retrieveRequestData, HttpServletRequest request) throws JSONException
	{
		JAXBContext jc;
		JSONObject responseJson = new JSONObject();
		List<SupplierHeaderDAO> supplierList = new ArrayList<SupplierHeaderDAO>();
		String dataRequest = request.getHeader("Accept");
		String userId = request.getUserPrincipal().getName();
		
		try {
			responseJson = loadClassesAuto.supplierOperations.isTokenValid(retrieveRequestData.getAccessCode(),new Date(),Constants.VERIFY);
			
			if( (responseJson.getBoolean(Constants.STATUS)) ||
					(!responseJson.getBoolean(Constants.STATUS) && responseJson.get(Constants.DESCRIPTION).toString().equalsIgnoreCase("Token is already expired"))) 
			{
				while(responseJson.length()>0)
					responseJson.remove((String) responseJson.keys().next());
				
		        RetrieveRequest retrieveRQ = new RetrieveRequest();
		        RequestHeaderDAO requestHeader = new RequestHeaderDAO();
		
		        requestHeader.setUserId(userId);
		        requestHeader.setSessionId(retrieveRequestData.getAccessCode());
		        requestHeader.setTransactionId(retrieveRequestData.getAccessCode());
		
		        String collectionName = "retrieve" + Constants.SUPPLIER_LIST;
		        supplierCode = retrieveRequestData.getSupplierId().toString();
		        SupplierCredentials supplierDetails = loadClassesAuto.getSupplierList.getSupplierFromDB(Constants.SEARCH_SUPPLIER_BYCODE, supplierCode, collectionName);
				
				if(supplierDetails == null)
				{
					responseJson.put(Constants.CODE, Constants.NOT_FOUND);
					responseJson.put(Constants.STATUS, false);
					responseJson.put(Constants.DESCRIPTION, "Invalid Supplier Code");
					return responseJson;
				}
				SupplierHeaderDAO supplierHeaderDAO = gson.fromJson( supplierDetails.getSupplierHeaderDAO().toString() , SupplierHeaderDAO.class );
				supplierList.add(supplierHeaderDAO);
				retrieveRequestData.setSupplierId(supplierHeaderDAO.getSupplierId());
			    
		        requestHeader.setSupplierDao(supplierList);
		        retrieveRQ.setRequestHeader(requestHeader);
		        retrieveRQ.setRetrieveBody(retrieveRequestData);
				
				jc = JAXBContext.newInstance(RetrieveRequest.class);
				Marshaller marshaller;
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		      
		        StringWriter sw = new StringWriter();
		        marshaller.marshal(retrieveRQ, sw);
		        String xmlRequest = sw.toString();
		        logger.info(xmlRequest);
		        	      
		      	ClientConfig config = new ClientConfig();
		      	config.property(ClientProperties.READ_TIMEOUT, 1000000);
				config.property(ClientProperties.CONNECT_TIMEOUT, 1000000);
				Client client = ClientBuilder.newClient(config);
				String retrieveUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.RETRIEVE_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
				WebTarget target = client.target(retrieveUrl);
				Response integrationResponse = (Response) target.request().accept(MediaType.APPLICATION_XML).post(Entity.xml(xmlRequest));
				
				if(integrationResponse.getStatus() == HttpStatus.OK.value()) 
				{
					InputStream inputStream = (InputStream) integrationResponse.getEntity();
					responseData = IOUtils.toString( inputStream , "UTF-8");
					logger.info("Integration Response Data : "+responseData);
					
					/*String fileName = "Retrieve_" + RandomKeyConfig.generateRandom() + ".xml";
					file = new File(fileName);
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(responseData); 
					fileWriter.flush();
					fileWriter.close();*/
					
					JAXBContext jaxbContext = JAXBContext.newInstance(CommonRetrieveRS.class,CommonRetrieveObjectFactory.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					CommonRetrieveRS retrieveRS = (CommonRetrieveRS) jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(responseData));
					
					String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
					MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
					
					String integrationStatus = null;
					
					try { 
						
						integrationStatus = retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().getOTATourActivityResRetrieveRS().getSuccess();
						retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().setAccessCode(retrieveRequestData.getAccessCode());
						retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().setSupplierID(supplierCode);
						retrieveRS.getResponseBody().setCnkStatus(Constants.TRUE);
						
					} catch (NullPointerException npe) {
						logger.info("SI response not contain success tag");
						retrieveRS.getResponseBody().setCnkStatus(Constants.TRUE);
					}
					
					logger.info("Integration status : "+integrationStatus);
					
					if( integrationStatus != null && (integrationStatus.equalsIgnoreCase(Constants.TRUE) || integrationStatus.equalsIgnoreCase(Constants.SUCCESS)) )
					{
						// Update the amount with markup amount
						if(retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().getOTATourActivityResRetrieveRS().getDetail().getPricing().getAvailable().equals("true"))
						{
							Float summaryAmount = retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().getOTATourActivityResRetrieveRS().getDetail().getPricing().getSummary().getAmount();
							float markup = (float) (summaryAmount  * 1.13);
							retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().getOTATourActivityResRetrieveRS().getDetail().getPricing().getSummary().setAmount(markup);
							
							List<ParticipantCategory> participantL = retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().getOTATourActivityResRetrieveRS().getDetail().getPricing().getParticipantCategory();
							Iterator<ParticipantCategory> participantItr = participantL.iterator();
							
							while(participantItr.hasNext())
							{
								ParticipantCategory participantCategory = participantItr.next();
								Float amount = participantCategory.getPrice().getAmount();
								markup = (float) (amount  * 1.13);
								participantCategory.getPrice().setAmount(markup);
							}
						}
						
						for(com.cnk.siapi.response.CommonRetrieveRS.ResponseBody.OTATourActivityResRetrieveRSIntWrapper.OTATourActivityResRetrieveRS.Detail.Confirmation confirmation : 
							retrieveRS.getResponseBody().getOTATourActivityResRetrieveRSIntWrapper().getOTATourActivityResRetrieveRS().getDetail().getConfirmation()) 
						{
							if( confirmation.getType() == 14 ) 
							{
								//Update status of specified booking
								Query query = new Query();
								query.addCriteria(Criteria.where("supplierBookingId").is(confirmation.getID()));
								
								Update update = new Update();
								update.set("bookingStatus", confirmation.getIDContext());
								mongoInstance.findAndModify(query, update, BookingDetails.class);
								BookingDetails bookingDetails = mongoInstance.findOne(query, BookingDetails.class);
								
								org.json.simple.JSONObject adminManipulateApiResponse = loadClassesAuto.commonOperations.manipulateBookingInAdminPanel(bookingDetails, "refresh");
								logger.info(adminManipulateApiResponse);
							}
						}
					}
					else {
						retrieveRS.getResponseBody().setCnkStatus(Constants.FALSE);
						logger.info("Retrieve request failed as integration status is false or blank");
					}
					//Save the Response in Database
					RetrieveRequestResponse retrieveReqResp = new RetrieveRequestResponse();
					retrieveReqResp.setAccessToken(retrieveRequestData.getAccessCode());
					retrieveReqResp.setOriginalResponse((org.json.simple.JSONObject)new JSONParser().parse(XML.toJSONObject(responseData).toString()));
					retrieveReqResp.setRetrieveRequest(retrieveRQ);
					retrieveReqResp.setRetrieveResponse(retrieveRS);
					retrieveReqResp.setCreationBy(userId);
					retrieveReqResp.setCreationDate(new Date());
					retrieveReqResp.setIp(request.getLocalAddr());
					mongoInstance.insert(retrieveReqResp);
					
//					file.delete();
					logger.info("Retrieve request status : " + integrationStatus);
					
					if(dataRequest.equals("application/xml"))	{
						jc = JAXBContext.newInstance(CommonRetrieveRS.class);
						marshaller = jc.createMarshaller();
						marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						StringWriter sw1 = new StringWriter();
						marshaller.marshal(retrieveRS, sw1);
						xmlRequest = sw1.toString();
						return xmlRequest;
					} 
					else
						return retrieveRS;
				} 
				else {
					
					logger.info("Integration Response : "+integrationResponse);
					responseJson.put(Constants.CODE, integrationResponse.getStatus());
					responseJson.put(Constants.STATUS, Constants.FAILED);
					responseJson.put(Constants.DESCRIPTION, HttpStatus.valueOf(integrationResponse.getStatus()).name());
					return responseJson;
				}
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