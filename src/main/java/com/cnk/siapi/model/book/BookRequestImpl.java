package com.cnk.siapi.model.book;

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
import com.cnk.siapi.model.storerequst.BookRequestResponse;
import com.cnk.siapi.response.CommonBookObjectFactory;
import com.cnk.siapi.response.CommonBookRS;
import com.cnk.siapi.response.CommonBookRS.ResponseBody.OTATourActivityBookRSWrapper.OTATourActivityBookRS.ReservationDetails.Pricing.ParticipantCategory;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.google.gson.Gson;

@Service
public class BookRequestImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
//	File file;
	String supplierCode;
	String responseData;
	String supplierProductCodeNew;
	Gson gson = new Gson();
	
	public Object createBookRequest(BookRQBody bookRequestData, HttpServletRequest request) throws JSONException
	{
		JAXBContext jc;
		Date date = new Date();
		String bookId = null;
		JSONObject responseJson = new JSONObject();
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
			responseJson = loadClassesAuto.supplierOperations.isTokenValid(bookRequestData.getAccessCode(),date,Constants.VALID);
			
			if( (responseJson.getBoolean(Constants.STATUS)) ||
					(!responseJson.getBoolean(Constants.STATUS) && responseJson.get(Constants.DESCRIPTION).toString().equalsIgnoreCase("Token is already expired"))) 
			{
				loadClassesAuto.supplierOperations.refreshToken(bookRequestData.getAccessCode());
				
				while(responseJson.length()>0)
					responseJson.remove((String) responseJson.keys().next());
				
				BookRequest bookRQ = new BookRequest();
		    	RequestHeaderDAO requestHeader = new RequestHeaderDAO();
		    	
		    	if(bookRequestData.getPricingSummaryAmount() != null && !bookRequestData.getPricingSummaryAmount().equalsIgnoreCase(""))
		    	{
			    	// Get the Booking Amount and Remove Markup
			    	Double summaryAmountMarkup = Double.valueOf(bookRequestData.getPricingSummaryAmount());
			    	double summaryAmt = ((summaryAmountMarkup / 1.13));
			    	String summaryAmtRound = String.valueOf(Math.round(summaryAmt * Math.pow(10, 2)) / Math.pow(10, 2));
			    	bookRequestData.setPricingSummaryAmount(summaryAmtRound.substring(0, summaryAmtRound.lastIndexOf(".")));
			    	
			    	List<BookRQParticipantCategory> participantCategory = bookRequestData.getParticipantCategory();
			    	Iterator<BookRQParticipantCategory> itr = participantCategory.iterator();
			    	
			    	while(itr.hasNext())
			    	{
			    		BookRQParticipantCategory participant = new BookRQParticipantCategory();
			    		participant = itr.next();
			    		
			    		if(participant.getPriceAmount() != null) {
				    		Double participantAmtMarkup = participant.getPriceAmount();
				    		double participantAmt = ((participantAmtMarkup / 1.13));
				    		double participantAmtRound =  Math.round(participantAmt * Math.pow(10, 2)) / Math.pow(10, 2);
				    		participant.setPriceAmount(participantAmtRound);
			    		}
			    	}
				}
		    	requestHeader.setUserId(userId);
		        requestHeader.setSessionId(bookRequestData.getAccessCode());
		        requestHeader.setTransactionId(bookRequestData.getAccessCode());
			    
		        String collectionName = "book" + Constants.SUPPLIER_LIST;
		        supplierCode = bookRequestData.getSupplierID().toString();
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
		        bookRequestData.setSupplierID(supplierHeaderDAO.getSupplierId());
			              
		        requestHeader.setSupplierDao(supplierList);
				bookRQ.setRequestHeader(requestHeader);
				
				// Change the Received supplier product code and update it to correct product code
				supplierProductCodeNew =  bookRequestData.getSupplierProductCode().substring(bookRequestData.getRegionCode().replace("-", "").length());
				
				bookRequestData.setSupplierProductCode(supplierProductCodeNew);
				bookRQ.setBookRQBody(bookRequestData);
			
				jc = JAXBContext.newInstance(BookRequest.class);
				Marshaller marshaller;
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				
				StringWriter sw = new StringWriter();
				marshaller.marshal(bookRQ, sw);
				String xmlRequest = sw.toString();
				logger.info(xmlRequest);
				
				ClientConfig config = new ClientConfig();
				config.property(ClientProperties.READ_TIMEOUT, 1000000);
				config.property(ClientProperties.CONNECT_TIMEOUT, 1000000);
				Client client = ClientBuilder.newClient(config);
				String bookUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.BOOK_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
				WebTarget target = client.target(bookUrl);
				Response integrationResponse = (Response) target.request().accept(MediaType.APPLICATION_XML).post(Entity.xml(xmlRequest));
				
				if(integrationResponse.getStatus() == HttpStatus.OK.value()) 
				{
					InputStream inputStream = (InputStream) integrationResponse.getEntity();
					responseData = IOUtils.toString( inputStream , "UTF-8");
					logger.info("Integration Response Data : "+responseData);
					
					/*String fileName = "Book_" + RandomKeyConfig.generateRandom() + ".xml";
					file = new File(fileName);
					FileWriter fileWriter = new FileWriter(file);
					fileWriter.write(responseData);
					fileWriter.flush();
					fileWriter.close();*/
					
					JAXBContext jaxbContext = JAXBContext.newInstance(CommonBookRS.class, CommonBookObjectFactory.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					CommonBookRS bookRS = (CommonBookRS) jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(responseData));
					
					String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
					MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
					
					String integrationStatus = null;
					
					try { 
						
						integrationStatus = bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getSuccess();
						bookRS.getResponseBody().getOTATourActivityBookRSWrapper().setAccessCode(bookRequestData.getAccessCode());
						bookRS.getResponseBody().getOTATourActivityBookRSWrapper().setSupplierID(supplierCode);
						bookRS.getResponseBody().setCnkStatus(Constants.TRUE);
						
					} catch (NullPointerException npe) {
						logger.info("SI response not contain success tag");
						bookRS.getResponseBody().setCnkStatus(Constants.TRUE);
					}
					
					logger.info("Integration status : "+integrationStatus);
					
					if( integrationStatus != null && (integrationStatus.equalsIgnoreCase(Constants.TRUE) || integrationStatus.equalsIgnoreCase(Constants.SUCCESS)) )
					{
						String priceAvaliable = bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getPricing().getAvailable();
								
						// Add the Markup Amount to the Book Response
						if( priceAvaliable != null && priceAvaliable.equals("true"))
						{
							Float markup = null;
							if(bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getPricing().getSummary() != null) 
							{
								if(!bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getPricing().getSummary().getAmount().toString().equalsIgnoreCase("NA"))
								{
									Float summaryAmount = Float.parseFloat(bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getPricing().getSummary().getAmount());
									markup = (float) (summaryAmount  * 1.13);
									bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getPricing().getSummary().setAmount(String.valueOf(markup));;
								}
							}
							
							List<ParticipantCategory> pricingL = bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getPricing().getParticipantCategory();
							Iterator<ParticipantCategory> pricingItr = pricingL.iterator();
							
							while(pricingItr.hasNext())
							{
								ParticipantCategory participantPrice = new ParticipantCategory();
								participantPrice = pricingItr.next();
								Float amount = participantPrice.getPrice().getAmount();
								markup = (float) (amount  * 1.13);
								participantPrice.getPrice().setAmount(markup);
							}
						}
						
						List<com.cnk.siapi.response.CommonBookRS.ResponseBody.OTATourActivityBookRSWrapper.OTATourActivityBookRS.ReservationDetails.Confirmation> oldConfirmationList = bookRS.getResponseBody().getOTATourActivityBookRSWrapper().getOTATourActivityBookRS().getReservationDetails().getConfirmation();
						Iterator<com.cnk.siapi.response.CommonBookRS.ResponseBody.OTATourActivityBookRSWrapper.OTATourActivityBookRS.ReservationDetails.Confirmation> confirmationItr = oldConfirmationList.iterator();
						
						while(confirmationItr.hasNext()) 
						{
							com.cnk.siapi.response.CommonBookRS.ResponseBody.OTATourActivityBookRSWrapper.OTATourActivityBookRS.ReservationDetails.Confirmation confirmation = new com.cnk.siapi.response.CommonBookRS.ResponseBody.OTATourActivityBookRSWrapper.OTATourActivityBookRS.ReservationDetails.Confirmation(); 
							confirmation = confirmationItr.next();
							String bookingId = "CKIS-Book-" + RandomKeyConfig.generateRandom();
							confirmation.setCnkBookId(bookingId);
							
							if( confirmation.getType() == 14 ) {
								bookId = bookingId;
								BookingDetails bookingDetails = new BookingDetails();
								bookingDetails.setBookingId(bookingId);
								bookingDetails.setSupplierBookingId(confirmation.getId());
								bookingDetails.setBookingStatus(confirmation.getIdContext());
								bookingDetails.setBookingType(confirmation.getType());
								bookingDetails.setBookingDate(date);
								bookingDetails.setSupplierId(supplierDetails.getSupplierCode());
								bookingDetails.setSupplierName(supplierDetails.getSupplierName());
								bookingDetails.setUsername(userId);
								bookingDetails.setAccessToken(bookRequestData.getAccessCode());
								bookingDetails.setOtherBookInfo(oldConfirmationList);
								mongoInstance.insert(bookingDetails);
								
								org.json.simple.JSONObject adminManipulateApiResponse = loadClassesAuto.commonOperations.manipulateBookingInAdminPanel(bookingDetails, "book");
								logger.info(adminManipulateApiResponse);
							}
						}
					}
					else {
						bookRS.getResponseBody().setCnkStatus(Constants.FALSE);
						logger.info("Book request failed as integration status is false or blank");
					}
					
					System.out.println("XML to Json : "+XML.toJSONObject(responseData));
					//Save the Response in Database
					BookRequestResponse bookReqResp = new BookRequestResponse();
					bookReqResp.setAccessToken(bookRequestData.getAccessCode());
					bookReqResp.setOriginalResponse((org.json.simple.JSONObject)new JSONParser().parse(XML.toJSONObject(responseData).toString()));
					bookReqResp.setBookRequest(bookRQ);
					bookReqResp.setBookResponse(bookRS);
					bookReqResp.setSupplierProductCode(bookRequestData.getSupplierProductCode());
					bookReqResp.setSupplierBrandCode(bookRequestData.getSupplierBrandCode());
					bookReqResp.setAgentName(userId);
					bookReqResp.setCountryCode(bookRequestData.getCountryCode());
					bookReqResp.setRegionCode(bookRequestData.getAccessCode());
					bookReqResp.setCreationBy(userId);
					bookReqResp.setCreationDate(new Date());
					bookReqResp.setIp(request.getLocalAddr());
					bookReqResp.setBookId(bookId);
					bookReqResp.setSupplierId(supplierHeaderDAO.getSupplierId());
					mongoInstance.insert(bookReqResp);
					
//					file.delete();
					logger.info("Book request status : " + integrationStatus);
					
					if(reqDataType.equals("application/xml"))	{
						jc = JAXBContext.newInstance(CommonBookRS.class);
						marshaller = jc.createMarshaller();
						marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
						StringWriter sw1 = new StringWriter();
						marshaller.marshal(bookRS, sw1);
						xmlRequest = sw1.toString();
						return xmlRequest;
					} 
					else
						return bookRS;
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