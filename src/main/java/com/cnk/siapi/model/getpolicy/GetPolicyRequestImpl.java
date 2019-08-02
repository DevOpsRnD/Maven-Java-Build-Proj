package com.cnk.siapi.model.getpolicy;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.requestheader.RequestHeaderDAO;
import com.cnk.siapi.model.requestheader.SupplierHeaderDAO;
import com.cnk.siapi.model.storerequst.GetPolicyRequestResponse;
import com.cnk.siapi.response.CommonGetPolicyObjectFactory;
import com.cnk.siapi.response.CommonGetPolicyRS;
import com.cnk.siapi.response.CommonGetPolicyRS.ResponseBody.OTATourActivityCancellationPoliciesRSWrapper.OTATourActivityCancellationPoliciesRS.CancellationPolicies;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.google.gson.Gson;

@Service
public class GetPolicyRequestImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
//	File file;
	String supplierCode;
	String responseData;
	Gson gson = new Gson();
	String supplierProductCodeNew;
	
	public Object createGetpolicyRequest(GetPolicyRQBody getPolicyRequestData, HttpServletRequest request) throws JSONException
	{
		String toCurrency = getPolicyRequestData.getRequestCurrency();
		JAXBContext jc;
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
			userId = "testuser";
		}
		
		try 
		{
			while(responseJson.length()>0)
				responseJson.remove((String) responseJson.keys().next());
			
			GetPolicyRequest getPolicyRQ = new GetPolicyRequest();
			RequestHeaderDAO requestHeader = new RequestHeaderDAO();
			
			requestHeader.setUserId(userId);
			requestHeader.setSessionId(getPolicyRequestData.getAccessCode());
			requestHeader.setTransactionId(getPolicyRequestData.getAccessCode());
			
			String collectionName = "getpolicy" + Constants.SUPPLIER_LIST;
			supplierCode = getPolicyRequestData.getSupplierId().toString();
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
			getPolicyRequestData.setSupplierId(supplierHeaderDAO.getSupplierId());
			
			requestHeader.setSupplierDao(supplierList);
			getPolicyRQ.setRequestHeader(requestHeader);
			
			 // Change the Received supplier product code and update it to correct product code
	        supplierProductCodeNew = getPolicyRequestData.getSupplierProductCode();
	        supplierProductCodeNew =  supplierProductCodeNew.substring(9);
	        supplierProductCodeNew =  getPolicyRequestData.getSupplierProductCode().substring(getPolicyRequestData.getCityCode().replace("-", "").length());
	        getPolicyRequestData.setSupplierProductCode(supplierProductCodeNew);
			getPolicyRQ.setGetPolicyBody(getPolicyRequestData);
	        
			jc = JAXBContext.newInstance(GetPolicyRequest.class);
			Marshaller marshaller;
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			StringWriter sw = new StringWriter();
			marshaller.marshal(getPolicyRQ, sw);
			String xmlRequest = sw.toString();
			logger.info(xmlRequest);
			
			ClientConfig config = new ClientConfig();
			config.property(ClientProperties.READ_TIMEOUT, 1000000);
			config.property(ClientProperties.CONNECT_TIMEOUT, 1000000);
			Client client = ClientBuilder.newClient(config);
			String getpolicyUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETPOLICY_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
			WebTarget target = client.target(getpolicyUrl);
			Response integrationResponse = (Response) target.request().accept(MediaType.APPLICATION_XML).post(Entity.xml(xmlRequest));
			
			if(integrationResponse.getStatus() == HttpStatus.OK.value()) 
			{
				InputStream inputStream = (InputStream) integrationResponse.getEntity();
				responseData = IOUtils.toString( inputStream , "UTF-8");
				logger.info("Integration Response Data : "+responseData);
				
				/*String fileName = "Getpolicy_" + RandomKeyConfig.generateRandom() + ".xml";
				file = new File(fileName);
				FileWriter fileWriter = new FileWriter(file);
				fileWriter.write(responseData);
				fileWriter.flush();
				fileWriter.close();*/
				
				JAXBContext jaxbContext = JAXBContext.newInstance(CommonGetPolicyRS.class, CommonGetPolicyObjectFactory.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				CommonGetPolicyRS getPolicyRS = (CommonGetPolicyRS) jaxbUnmarshaller.unmarshal(IOUtils.toInputStream(responseData));
				
				
				String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
				MongoOperations mongoInstance = loadClassesAuto.mongoConnection.getInstance(databaseName);
				
				try {
					
					getPolicyRS.getResponseBody().getOTATourActivityCancellationPoliciesRSWrapper().setAccessCode(getPolicyRequestData.getAccessCode());
					getPolicyRS.getResponseBody().getOTATourActivityCancellationPoliciesRSWrapper().setSupplierID(supplierCode);
					getPolicyRS.getResponseBody().setCnkStatus(Constants.TRUE);
					
					/*Changes for GetPolicies */
					RestTemplate template = new RestTemplate();
					
					if(getPolicyRS.getResponseBody().getOTATourActivityCancellationPoliciesRSWrapper().getSupplierID().equals("CNKHTOS-5") || 
							getPolicyRS.getResponseBody().getOTATourActivityCancellationPoliciesRSWrapper().getSupplierID().equals("CNKSTAG-2"))
					{
					
						List<CommonGetPolicyRS.ResponseBody.OTATourActivityCancellationPoliciesRSWrapper.OTATourActivityCancellationPoliciesRS.CancellationPolicies>
										cancellationPolicy = getPolicyRS.getResponseBody().getOTATourActivityCancellationPoliciesRSWrapper().getOTATourActivityCancellationPoliciesRS()
											.getCancellationPolicies();
						
						java.util.Iterator<CancellationPolicies> itr = cancellationPolicy.iterator();
						
						while(itr.hasNext())
						{
							CommonGetPolicyRS.ResponseBody.OTATourActivityCancellationPoliciesRSWrapper.OTATourActivityCancellationPoliciesRS.CancellationPolicies cancellation = itr.next();
							
							String fromDate = cancellation.getFromValue();
							String toDate = cancellation.getToValue();
							String unit = cancellation.getUnit();
							String chargeType = cancellation.getChargeType();
							String ckisText = null;
							
							if(unit.equals("Date") && chargeType.equals("Amount"))
							{
								if(!fromDate.equals("NA"))
								{
									if(!toDate.equals("NA"))
									{
										String fromCurrency = cancellation.getCurrencyCode();
										// Call for ROE to get latest conversion rates
										String roeUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETROEURL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
										ResponseEntity<String> response = template.getForEntity( roeUrl + "?FromCurrency=" + fromCurrency + "&" + "ToCurrency=" + toCurrency, String.class);
										
										JSONObject roeResponse = XML.toJSONObject(response.getBody());
										Double roeValue = roeResponse.getJSONObject("result").getJSONObject("Data").getDouble("entry");
										System.out.println(roeValue);
										
										Double convertedRate = roeValue * Double.parseDouble(cancellation.getRate());
										cancellation.setConvertedRate(convertedRate.toString());
										cancellation.setRequestedCurrencyCode(toCurrency);
										
										ckisText = "For Cancellations Prior to " + fromDate + " ,cancellation charges will be nill. " + "From " + fromDate + " To " + toDate +  " the cancellation charges are " + cancellation.getRate() + " " + cancellation.getCurrencyCode() + "/ " + convertedRate + " " + toCurrency;
//										ckisText = "For Cancellations Prior to " + fromDate + " ,cancellation charges will be nill. " + "From " + fromDate + " the cancellation charges are " + cancellation.getRate() + " " +  cancellation.getCurrencyCode();
									}
									else
										ckisText = "For Cancellations Prior to " + fromDate + " ,cancellation charges will be nill. " + "From " + fromDate + " the cancellation charges are " + cancellation.getRate() + " " +  cancellation.getCurrencyCode();
//										ckisText = "For Cancellations Prior to " + fromDate + " ,cancellation charges will be nill. " + "From " + fromDate + " To " + toDate +  " the cancellation charges are " + cancellation.getRate() + " " + cancellation.getCurrencyCode();
								}
								else {
									ckisText = "Cancellation charges will be nill till " + toDate + ".";
								}
							}
							cancellation.setCkisText(ckisText);
						}
						
					}
					
				} catch (NullPointerException npe) {
					logger.error("SI response not contain OTATourActivityCancellationPoliciesRSWrapper tag",npe);
					getPolicyRS.getResponseBody().setCnkStatus(Constants.FALSE);
				}
				
				//Save the Response in Database
				GetPolicyRequestResponse getpolicyReqResp = new GetPolicyRequestResponse();
				getpolicyReqResp.setAccessToken(getPolicyRequestData.getAccessCode());
				getpolicyReqResp.setOriginalResponse((org.json.simple.JSONObject)new JSONParser().parse(XML.toJSONObject(responseData).toString()));
				getpolicyReqResp.setGetpolicyRequest(getPolicyRQ);
				getpolicyReqResp.setGetpolicyResponse(getPolicyRS);
				getpolicyReqResp.setCreationBy(userId);
				getpolicyReqResp.setCreationDate(new Date());
				getpolicyReqResp.setIp(request.getLocalAddr());
				getpolicyReqResp.setSupplierId(supplierHeaderDAO.getSupplierId());
				mongoInstance.insert(getpolicyReqResp);
				
//				file.delete();
				logger.info("Getpolicy request success");
				
				if(reqDataType.equals("application/xml"))
				{
					jc = JAXBContext.newInstance(CommonGetPolicyRS.class);
					marshaller = jc.createMarshaller();
					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					StringWriter sw1 = new StringWriter();
					marshaller.marshal(getPolicyRS, sw1);
					xmlRequest = sw1.toString();
					return xmlRequest;
				}
				else
					return getPolicyRS;
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
			}catch (IOException e) {
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