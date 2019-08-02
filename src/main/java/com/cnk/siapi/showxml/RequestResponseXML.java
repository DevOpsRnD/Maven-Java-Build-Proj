package com.cnk.siapi.showxml;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.book.BookRequest;
import com.cnk.siapi.model.cancel.CancelRequest;
import com.cnk.siapi.model.getdetails.GetDetailsRequest;
import com.cnk.siapi.model.getpolicy.GetPolicyRequest;
import com.cnk.siapi.model.reprice.RepriceRequest;
import com.cnk.siapi.model.retrieve.RetrieveRequest;
import com.cnk.siapi.model.storerequst.BookRequestResponse;
import com.cnk.siapi.model.storerequst.CancelRequestResponse;
import com.cnk.siapi.model.storerequst.GetDetailsRequestResponse;
import com.cnk.siapi.model.storerequst.GetPolicyRequestResponse;
import com.cnk.siapi.model.storerequst.RepriceRequestResponse;
import com.cnk.siapi.model.storerequst.RetrieveRequestResponse;
import com.cnk.siapi.response.CommonBookRS;
import com.cnk.siapi.response.CommonCancelRS;
import com.cnk.siapi.response.CommonGetDetailsRS;
import com.cnk.siapi.response.CommonGetPolicyRS;
import com.cnk.siapi.response.CommonRepriceRS;
import com.cnk.siapi.response.CommonRetrieveRS;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RequestResponseXML {
	
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	JAXBContext jc;
	Marshaller marshaller;
	
	@SuppressWarnings("unchecked")
	public Object getBookingXML(String bookingId,String operation,String byIdOrAccessCode) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException{
		BookRequestResponse bookRequestResponse=null;
		org.json.simple.JSONObject  responseJson = new org.json.simple.JSONObject ();
		if(byIdOrAccessCode.equalsIgnoreCase("accessCode")){
			bookRequestResponse=(BookRequestResponse) loadClassesAuto.mongoDBOperation.findByAccessCode(bookingId,"booking");
		}else if(byIdOrAccessCode.equalsIgnoreCase("bookingId")){
			bookRequestResponse=loadClassesAuto.mongoDBOperation.findByBookingId(bookingId);
		}
		logger.info("bookRequestResponse"+bookRequestResponse);
		if(bookRequestResponse!=null && bookRequestResponse.getBookId()!=null){
			if(operation.equalsIgnoreCase("request")){
				
				ObjectMapper mapper = new ObjectMapper();
				
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				BookRequest  request = bookRequestResponse.getBookRequest();
				logger.info("Request json class " + request);
				
				jc = JAXBContext.newInstance(BookRequest.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(request, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else if(operation.equalsIgnoreCase("response")){
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				logger.info(bookRequestResponse.getBookResponse());
				CommonBookRS bookResponse = bookRequestResponse.getBookResponse();
				jc = JAXBContext.newInstance(CommonBookRS.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(bookResponse, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else{
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, false);
				responseJson.put("data", "");
				responseJson.put(Constants.DESCRIPTION, "Operation name is invalid");
				logger.info("Operation name is invalid");
				return responseJson;
			}
		}else{
			responseJson.put(Constants.CODE, Constants.NOT_FOUND);
			responseJson.put(Constants.STATUS, false);
			responseJson.put("data", "");
			responseJson.put(Constants.DESCRIPTION, "invalid booking id or accesscode");
			logger.info("invalid booking id or accesscode");
			return responseJson;
		}
		
	}
	
	@SuppressWarnings({"unchecked"})
	public Object getDetailsXML(String bookingId,String operation,String byIdOrAccessCode) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException{
		GetDetailsRequestResponse getdetailsrequestresponse=null;
		org.json.simple.JSONObject  responseJson = new org.json.simple.JSONObject ();
		BookRequestResponse bookRequestResponse=null;
		String accessCode="";
		
		if(byIdOrAccessCode.equalsIgnoreCase("bookingId")){
			bookRequestResponse=loadClassesAuto.mongoDBOperation.findByBookingId(bookingId);
			accessCode=bookRequestResponse.getAccessToken();
		}else{
			accessCode=bookingId;
		}
		getdetailsrequestresponse=(GetDetailsRequestResponse) loadClassesAuto.mongoDBOperation.findByAccessCode(accessCode,"getdetails");
		
		logger.info("getdetailsrequestresponse xml"+getdetailsrequestresponse);
		
		if(getdetailsrequestresponse!=null && getdetailsrequestresponse.getAccessToken()!=null){
			if(operation.equalsIgnoreCase("request")){
				
				ObjectMapper mapper = new ObjectMapper();
				
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				GetDetailsRequest  request = getdetailsrequestresponse.getGetdetailsRequest();
				logger.info("Request json class " + request);
				
				jc = JAXBContext.newInstance(GetDetailsRequest.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(request, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else if(operation.equalsIgnoreCase("response")){
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				logger.info(getdetailsrequestresponse.getGetdetailsResponse());
				CommonGetDetailsRS response = getdetailsrequestresponse.getGetdetailsResponse();
				jc = JAXBContext.newInstance(CommonGetDetailsRS.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(response, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else{
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, false);
				responseJson.put("data", "");
				responseJson.put(Constants.DESCRIPTION, "Operation name is invalid");
				logger.info("Operation name is invalid");
				return responseJson;
			}
		}else{
			responseJson.put(Constants.CODE, Constants.NOT_FOUND);
			responseJson.put(Constants.STATUS, false);
			responseJson.put("data", "");
			responseJson.put(Constants.DESCRIPTION, "invalid booking id or accesscode");
			logger.info("invalid booking id or accesscode");
			return responseJson;
		}
		
	}
	
	@SuppressWarnings({"unchecked"})
	public Object getPolicyXML(String bookingId,String operation,String byIdOrAccessCode) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException{
		GetPolicyRequestResponse getpolicyrequestresponse=null;
		org.json.simple.JSONObject  responseJson = new org.json.simple.JSONObject ();
		BookRequestResponse bookRequestResponse=null;
		String accessCode="";
		
		if(byIdOrAccessCode.equalsIgnoreCase("bookingId")){
			bookRequestResponse=loadClassesAuto.mongoDBOperation.findByBookingId(bookingId);
			accessCode=bookRequestResponse.getAccessToken();
		}else{
			accessCode=bookingId;
		}
		logger.info("byIdOrAccessCode"+bookingId);
		getpolicyrequestresponse=(GetPolicyRequestResponse) loadClassesAuto.mongoDBOperation.findByAccessCode(accessCode,"policy");
		
		logger.info("getdetailsrequestresponse xml"+getpolicyrequestresponse);
		
		if(getpolicyrequestresponse!=null && getpolicyrequestresponse.getAccessToken()!=null){
			if(operation.equalsIgnoreCase("request")){
				
				ObjectMapper mapper = new ObjectMapper();
				
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				GetPolicyRequest  request = getpolicyrequestresponse.getGetpolicyRequest();
				logger.info("Request json class " + request);
				
				jc = JAXBContext.newInstance(GetPolicyRequest.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(request, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else if(operation.equalsIgnoreCase("response")){
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				logger.info(getpolicyrequestresponse.getGetpolicyResponse());
				CommonGetPolicyRS response = getpolicyrequestresponse.getGetpolicyResponse();
				jc = JAXBContext.newInstance(CommonGetPolicyRS.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(response, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else{
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, false);
				responseJson.put("data", "");
				responseJson.put(Constants.DESCRIPTION, "Operation name is invalid");
				logger.info("Operation name is invalid");
				return responseJson;
			}
		}else{
			responseJson.put(Constants.CODE, Constants.NOT_FOUND);
			responseJson.put(Constants.STATUS, false);
			responseJson.put("data", "");
			responseJson.put(Constants.DESCRIPTION, "invalid booking id or accesscode");
			logger.info("invalid booking id or accesscode");
			return responseJson;
		}
		
	}
	
	@SuppressWarnings({"unchecked"})
	public Object getRepriceXML(String bookingId,String operation,String byIdOrAccessCode) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException{
		RepriceRequestResponse getrepriceequestresponse=null;
		org.json.simple.JSONObject  responseJson = new org.json.simple.JSONObject ();
		BookRequestResponse bookRequestResponse=null;
		String accessCode="";
		
		if(byIdOrAccessCode.equalsIgnoreCase("bookingId")){
			bookRequestResponse=loadClassesAuto.mongoDBOperation.findByBookingId(bookingId);
			accessCode=bookRequestResponse.getAccessToken();
		}else{
			accessCode=bookingId;
		}
		logger.info("byIdOrAccessCode"+bookingId);
		getrepriceequestresponse=(RepriceRequestResponse) loadClassesAuto.mongoDBOperation.findByAccessCode(accessCode,"reprice");
		
		logger.info("getrepriceequestresponse xml"+getrepriceequestresponse);
		
		if(getrepriceequestresponse!=null && getrepriceequestresponse.getAccessToken()!=null){
			if(operation.equalsIgnoreCase("request")){
				
				ObjectMapper mapper = new ObjectMapper();
				
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				RepriceRequest  request = getrepriceequestresponse.getRepriceRequest();
				logger.info("Request json class " + request);
				
				jc = JAXBContext.newInstance(RepriceRequest.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(request, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else if(operation.equalsIgnoreCase("response")){
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				logger.info(getrepriceequestresponse.getRepriceResponse());
				CommonRepriceRS response = getrepriceequestresponse.getRepriceResponse();
				jc = JAXBContext.newInstance(CommonRepriceRS.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(response, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else{
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, false);
				responseJson.put("data", "");
				responseJson.put(Constants.DESCRIPTION, "Operation name is invalid");
				logger.info("Operation name is invalid");
				return responseJson;
			}
		}else{
			responseJson.put(Constants.CODE, Constants.NOT_FOUND);
			responseJson.put(Constants.STATUS, false);
			responseJson.put("data", "");
			responseJson.put(Constants.DESCRIPTION, "invalid booking id or accesscode");
			logger.info("invalid booking id or accesscode");
			return responseJson;
		}
		
	}
	
	@SuppressWarnings({"unchecked"})
	public Object getRetrieveXML(String bookingId,String operation,String byIdOrAccessCode) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException{
		RetrieveRequestResponse getretrieverequestresponse=null;
		org.json.simple.JSONObject  responseJson = new org.json.simple.JSONObject ();
		BookRequestResponse bookRequestResponse=null;
		String accessCode="";
		
		if(byIdOrAccessCode.equalsIgnoreCase("bookingId")){
			bookRequestResponse=loadClassesAuto.mongoDBOperation.findByBookingId(bookingId);
			accessCode=bookRequestResponse.getAccessToken();
		}else{
			accessCode=bookingId;
		}
		logger.info("byIdOrAccessCode"+bookingId);
		getretrieverequestresponse=(RetrieveRequestResponse) loadClassesAuto.mongoDBOperation.findByAccessCode(accessCode,"retrieve");
		
		logger.info("getrepriceequestresponse xml"+getretrieverequestresponse);
		
		if(getretrieverequestresponse!=null && getretrieverequestresponse.getAccessToken()!=null){
			if(operation.equalsIgnoreCase("request")){
				
				ObjectMapper mapper = new ObjectMapper();
				
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				RetrieveRequest  request = getretrieverequestresponse.getRetrieveRequest();
				logger.info("Request json class " + request);
				
				jc = JAXBContext.newInstance(RetrieveRequest.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(request, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else if(operation.equalsIgnoreCase("response")){
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				logger.info(getretrieverequestresponse.getRetrieveResponse());
				CommonRetrieveRS response = getretrieverequestresponse.getRetrieveResponse();
				jc = JAXBContext.newInstance(CommonRetrieveRS.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(response, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else{
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, false);
				responseJson.put("data", "");
				responseJson.put(Constants.DESCRIPTION, "Operation name is invalid");
				logger.info("Operation name is invalid");
				return responseJson;
			}
		}else{
			responseJson.put(Constants.CODE, Constants.NOT_FOUND);
			responseJson.put(Constants.STATUS, false);
			responseJson.put("data", "");
			responseJson.put(Constants.DESCRIPTION, "invalid booking id or accesscode");
			logger.info("invalid booking id or accesscode");
			return responseJson;
		}
		
	}
	
	@SuppressWarnings({"unchecked"})
	public Object getCancelXML(String bookingId,String operation,String byIdOrAccessCode) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException{
		CancelRequestResponse getcancelrequestresponse=null;
		org.json.simple.JSONObject  responseJson = new org.json.simple.JSONObject ();
		BookRequestResponse bookRequestResponse=null;
		String accessCode="";
		
		if(byIdOrAccessCode.equalsIgnoreCase("bookingId")){
			bookRequestResponse=loadClassesAuto.mongoDBOperation.findByBookingId(bookingId);
			accessCode=bookRequestResponse.getAccessToken();
		}else{
			accessCode=bookingId;
		}
		logger.info("byIdOrAccessCode"+bookingId);
		getcancelrequestresponse=(CancelRequestResponse) loadClassesAuto.mongoDBOperation.findByAccessCode(accessCode,"cancel");
		
		logger.info("getrepriceequestresponse xml"+getcancelrequestresponse);
		
		if(getcancelrequestresponse!=null && getcancelrequestresponse.getAccessToken()!=null){
			if(operation.equalsIgnoreCase("request")){
				
				ObjectMapper mapper = new ObjectMapper();
				
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				CancelRequest  request = getcancelrequestresponse.getCancelRequest();
				logger.info("Request json class " + request);
				
				jc = JAXBContext.newInstance(CancelRequest.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(request, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else if(operation.equalsIgnoreCase("response")){
				
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

				logger.info(getcancelrequestresponse.getCancelResponse());
				CommonCancelRS response = getcancelrequestresponse.getCancelResponse();
				jc = JAXBContext.newInstance(CommonCancelRS.class);
				marshaller = jc.createMarshaller();
				marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				StringWriter sw1 = new StringWriter();
				marshaller.marshal(response, sw1);
				String xmlRequest = XML.toString(sw1);
				logger.info("Book Response"+xmlRequest);
				responseJson.put(Constants.CODE, Constants.SUCCESS);
				responseJson.put(Constants.STATUS, true);
				responseJson.put("data", xmlRequest);
				return responseJson;
				
			}else{
				responseJson.put(Constants.CODE, Constants.NOT_FOUND);
				responseJson.put(Constants.STATUS, false);
				responseJson.put("data", "");
				responseJson.put(Constants.DESCRIPTION, "Operation name is invalid");
				logger.info("Operation name is invalid");
				return responseJson;
			}
		}else{
			responseJson.put(Constants.CODE, Constants.NOT_FOUND);
			responseJson.put(Constants.STATUS, false);
			responseJson.put("data", "");
			responseJson.put(Constants.DESCRIPTION, "invalid booking id or accesscode");
			logger.info("invalid booking id or accesscode");
			return responseJson;
		}
		
	}
	
	
}


