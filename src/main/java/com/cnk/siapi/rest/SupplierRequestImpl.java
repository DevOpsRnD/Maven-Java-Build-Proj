package com.cnk.siapi.rest;


import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.login.ApiLoginDetails;
import com.cnk.siapi.model.book.BookRQBody;
import com.cnk.siapi.model.cancel.CancelRQBody;
import com.cnk.siapi.model.getdetails.GetDetailsRQBody;
import com.cnk.siapi.model.getpolicy.GetPolicyRQBody;
import com.cnk.siapi.model.reprice.RepriceRQBody;
import com.cnk.siapi.model.retrieve.RetrieveRQBody;
import com.cnk.siapi.model.search.SearchRQBody;
import com.cnk.siapi.response.BookRS;
import com.cnk.siapi.response.CommonBookRS;
import com.cnk.siapi.response.CommonCancelRS;
import com.cnk.siapi.response.CommonGetDetailsRS;
import com.cnk.siapi.response.CommonGetPolicyRS;
import com.cnk.siapi.response.CommonRepriceRS;
import com.cnk.siapi.response.CommonRetrieveRS;
import com.cnk.siapi.response.MultiSearchRS;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "sightseeing")
@Api(tags = "Activities Request", description = "List of Requests for Activities")
public class SupplierRequestImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	
	Object responseObject = null ;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/search", method= RequestMethod.POST,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Search Request", response = MultiSearchRS.class)
	public Object searchRequest(@RequestBody() String searchInfo,HttpServletRequest request, HttpServletResponse response) throws SocketTimeoutException
	{
		int statusCode = 200;
		ObjectMapper mapper = null;
		responseObject = new Object();
		
		try {
			logger.info("**Search Request**");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(searchInfo);
//				searchRequest = new Gson().fromJson(jsonInfo.toString(), SearchRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(searchInfo.toString());
//				searchRequest = new Gson().fromJson(jsonInfo.get("SearchRQBody").toString(), SearchRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				searchRequest = mapper.readValue(searchInfo, SearchRQBody.class);
			}
			
//			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
			
			SearchRQBody searchRequest = mapper.readValue(searchInfo, SearchRQBody.class);
			Object searchResponse = loadClassesAuto.searchRequest.createSearchRequest(searchRequest,request);
			
			if(searchResponse instanceof MultiSearchRS) {
				responseObject = (MultiSearchRS) searchResponse;
				logger.info(Constants.SUCCESS);
			}
			else if(searchResponse instanceof String) 
			{
				if(((String) searchResponse).contains("ResponseBody"))
				{
					responseObject = searchResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = searchResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) searchResponse;
				responseObject =  XML.toString(searchResponse);
			
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("**Search Request End**");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/book", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Booking Request", response = BookRS.class)
	public Object bookRequest(@RequestBody String bookingInfo, HttpServletRequest request)
	{
		int statusCode = 200;
		ObjectMapper mapper = null;
		responseObject = new Object();
		
		try {
			
			logger.info("***Book Request***");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(bookingInfo);
//				bookRequest = new Gson().fromJson(jsonInfo.toString(), BookRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(bookingInfo.toString());
//				bookRequest = new Gson().fromJson(jsonInfo.get("BookRQBody").toString(), BookRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				bookRequest = mapper.readValue(bookingInfo, BookRQBody.class);
			}

//			mapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
			
			BookRQBody bookRequest = mapper.readValue(bookingInfo, BookRQBody.class);
			Object bookResponse = loadClassesAuto.bookRequest.createBookRequest(bookRequest, request);
			
			if(bookResponse instanceof CommonBookRS) {
				responseObject = (CommonBookRS) bookResponse;
				logger.info(Constants.SUCCESS);
			}
			else if(bookResponse instanceof String) {
				if(((String) bookResponse).contains("ResponseBody"))
				{
					responseObject = bookResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = bookResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) bookResponse;
				responseObject =  XML.toString(bookResponse);
			
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("***Book Request End***");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getdetails", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "GetDetails Request", response = CommonGetDetailsRS.class)
	public Object getDetails(@RequestBody String detailInfo, HttpServletRequest request, HttpServletResponse response)
	{
		int statusCode = 200;
		ObjectMapper mapper = null;
		responseObject = new Object();
		
		try {
			logger.info("**Get Details**");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(detailInfo);
//				getdetailsRequest = new Gson().fromJson(jsonInfo.toString(), GetDetailsRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(detailInfo.toString());
//				getdetailsRequest = new Gson().fromJson(jsonInfo.get("GetDetailsRQBody").toString(), GetDetailsRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				getdetailsRequest = mapper.readValue(detailInfo, GetDetailsRQBody.class);
			}
			
			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
			
			GetDetailsRQBody getdetailsRequest = mapper.readValue(detailInfo, GetDetailsRQBody.class);
			Object getdetailsResponse =  loadClassesAuto.getDetails.createGetdetailsRequest(getdetailsRequest, request);
			
			if(getdetailsResponse instanceof CommonGetDetailsRS) {
				responseObject = (CommonGetDetailsRS) getdetailsResponse;
				logger.info(Constants.SUCCESS);
			}
			else if(getdetailsResponse instanceof String) 
			{
				if(((String) getdetailsResponse).contains("ResponseBody"))
				{
					responseObject = getdetailsResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = getdetailsResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) getdetailsResponse;
				responseObject =  XML.toString(getdetailsResponse);
			
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("**Get Details End**");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/getpolicy", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get Policy Details")
	public Object getPolicy(@RequestBody String policyInfo, HttpServletRequest request, HttpServletResponse response)
	{
		int statusCode = 200;
		ObjectMapper mapper = null;
		responseObject = new Object();
		
		try {
			logger.info("**Get Policy**");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(policyInfo);
//				getpolicyRequest = new Gson().fromJson(jsonInfo.toString(), GetPolicyRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(policyInfo.toString());
//				getpolicyRequest = new Gson().fromJson(jsonInfo.get("GetPolicyRQBody").toString(), GetPolicyRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				getpolicyRequest = mapper.readValue(policyInfo, GetPolicyRQBody.class);
			}
			
//			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
			
			GetPolicyRQBody getpolicyRequest = mapper.readValue(policyInfo, GetPolicyRQBody.class);
			Object getpolicyResponse = loadClassesAuto.getPolicy.createGetpolicyRequest(getpolicyRequest, request);
			
			if(getpolicyResponse instanceof CommonGetPolicyRS) {
				responseObject = (CommonGetPolicyRS) getpolicyResponse;
				logger.info(Constants.SUCCESS);
			}
			else if(getpolicyResponse instanceof String) {
				if(((String) getpolicyResponse).contains("ResponseBody"))
				{
					responseObject = getpolicyResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = getpolicyResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) getpolicyResponse;
				responseObject =  XML.toString(getpolicyResponse);
			
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("**Get Policy End**");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/cancel", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Cancel Booking")
	public Object cancelBook(@RequestBody String cancelBookInfo, HttpServletRequest request, HttpServletResponse response)
	{
		int statusCode = 200;
		ObjectMapper mapper = null;
		responseObject = new Object();
		
		try {
			logger.info("**Cancel Book**");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(cancelBookInfo);
//				cancelRequest = new Gson().fromJson(jsonInfo.toString(), CancelRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(cancelBookInfo.toString());
//				cancelRequest = new Gson().fromJson(jsonInfo.get("CancelRQBody").toString(), CancelRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				cancelRequest = mapper.readValue(cancelBookInfo, CancelRQBody.class);
			}
			
//			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));

			CancelRQBody cancelRequest = mapper.readValue(cancelBookInfo, CancelRQBody.class);
			Object cancelResponse = loadClassesAuto.cancelBook.createCancelRequest(cancelRequest, request);
			
			if(cancelResponse instanceof CommonCancelRS) {
				responseObject = (CommonCancelRS) cancelResponse;
//				logger.info(Constants.SUCCESS);
			}
			else if(cancelResponse instanceof String) {
				if(((String) cancelResponse).contains("ResponseBody"))
				{
					responseObject = cancelResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = cancelResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) cancelResponse;
				responseObject =  XML.toString(cancelResponse);
			
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("**Cancel Book End**");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/reprice", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Repricing", response = CommonRepriceRS.class)
	public Object reprice(@RequestBody String repriceInfo, HttpServletRequest request, HttpServletResponse response)
	{
		int statusCode = 200;
		ObjectMapper mapper = null;
		responseObject = new Object();
		
		try {
			logger.info("**Reprice**");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(repriceInfo);
//				repriceRequest = new Gson().fromJson(jsonInfo.toString(), RepriceRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(repriceInfo.toString());
//				repriceRequest = new Gson().fromJson(jsonInfo.get("RepriceRQBody").toString(), RepriceRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				repriceRequest = mapper.readValue(repriceInfo, RepriceRQBody.class);
			}
			
//			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
			
			RepriceRQBody repriceRequest = mapper.readValue(repriceInfo, RepriceRQBody.class);
			Object repirceResponse =  loadClassesAuto.reprice.createRepriceRequest(repriceRequest, request);
			
			if(repirceResponse instanceof CommonRepriceRS) {
				responseObject = (CommonRepriceRS) repirceResponse;
				logger.info(Constants.SUCCESS);
			}
			else if(repirceResponse instanceof String) {
				if(((String) repirceResponse).contains("ResponseBody"))
				{
					responseObject = repirceResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = repirceResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) repirceResponse;
				responseObject =  XML.toString(repirceResponse);
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("**Reprice End**");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/retrieve", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Retrieve Booking")
	public Object retrieve(@RequestBody String retrieveInfo, HttpServletRequest request, HttpServletResponse response)
	{
		int statusCode = 200;
		responseObject = new Object();
		ObjectMapper mapper = null;

		try {
			logger.info("**Retrieve**");
			
			if(request.getContentType().equalsIgnoreCase((("application/json")))) {
//				jsonInfo = new JSONObject(retrieveInfo);
//				retrieveRequest = new Gson().fromJson(jsonInfo.toString(), RetrieveRQBody.class);
				mapper = new ObjectMapper();
				
			} else {
//				jsonInfo = XML.toJSONObject(retrieveInfo.toString());
//				retrieveRequest = new Gson().fromJson(jsonInfo.get("RetrieveRQBody").toString(), RetrieveRQBody.class);
				
				JacksonXmlModule module = new JacksonXmlModule();
				module.setDefaultUseWrapper(false);
				mapper = new XmlMapper(module);
				
//				mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
//				retrieveRequest = mapper.readValue(retrieveInfo, RetrieveRQBody.class);
			}
			
//			mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			mapper.configOverride(String.class).setSetterInfo(JsonSetter.Value.forValueNulls(Nulls.AS_EMPTY));
			
			RetrieveRQBody retrieveRequest = mapper.readValue(retrieveInfo, RetrieveRQBody.class);
			Object retrieveResponse =  loadClassesAuto.retrieve.createRetrieveRequest(retrieveRequest, request);
			
			if(retrieveResponse instanceof CommonRetrieveRS) {
				responseObject = (CommonRetrieveRS) retrieveResponse;
				logger.info(Constants.SUCCESS);
			}
			else if(retrieveResponse instanceof String) {
				if(((String) retrieveResponse).contains("ResponseBody"))
				{
					responseObject = retrieveResponse;
					logger.info(Constants.SUCCESS);
				}
				else
				{
					responseObject = retrieveResponse;
					statusCode = 401;
					logger.info(Constants.FAILED);
				}
			}
			else {
				JSONObject jsonResponse = (JSONObject) retrieveResponse;
				responseObject =  XML.toString(retrieveResponse);
			
				if( jsonResponse.has(Constants.CODE)) 
					statusCode = Integer.parseInt(jsonResponse.get(Constants.CODE).toString());
				else	
					statusCode = 401;
				logger.info(Constants.FAILED);
			}
			logger.info("**Retrieve End**");
		} catch(SocketTimeoutException e) {
			statusCode = 500;
			logger.error("Exception occur",e);
		} catch(Exception e) {
			statusCode = 400;
			logger.error("Exception occur",e);
		}
		return new ResponseEntity(responseObject, HttpStatus.valueOf(statusCode));
	}
	
	@SuppressWarnings("unchecked")
	@CrossOrigin(maxAge = 3600)
	@ApiIgnore
	@RequestMapping(value = "/addlogincredentials", method = RequestMethod.POST)
	public Response addLoginCredentials(@FormParam(value = "username") String username,
												 @FormParam(value = "password") String password,
												 @FormParam(value = "usercode") String usercode,
												 @FormParam(value = "name") String name,
												 @FormParam(value = "email") String email)
	{
		org.json.simple.JSONObject response = new org.json.simple.JSONObject();
		try {
			List<String> roles = new ArrayList<>();
			roles.add("USER");
			
			ApiLoginDetails apiLoginDetails = new ApiLoginDetails();
			apiLoginDetails.setUserCode(usercode);
			apiLoginDetails.setUserFullName(name);
			apiLoginDetails.setUsername(username);
			apiLoginDetails.setPassword(password);
			apiLoginDetails.setEmail(email != null ? email : "");
			apiLoginDetails.setAuthorities(roles);
			
			response = loadClassesAuto.mongoDBOperation.saveLoginDetails(apiLoginDetails);
			logger.info(response);
			
			if(response.get(Constants.STATUS).equals(Constants.SUCCESS))
				return Response.ok(response).build();
			else 
				return Response.status(Response.Status.NOT_FOUND).entity(response).build();
			
		} catch (Exception e) {
			response.put(Constants.STATUS , Constants.FAILED);
			response.put(Constants.DESCRIPTION , "Something went wrong");
			logger.error(response, e);
			return Response.status(Response.Status.EXPECTATION_FAILED).entity(response).build();
		}
	}
	
	
	@SuppressWarnings({ "null", "unchecked" })
	@CrossOrigin(maxAge = 3600)
	@ApiIgnore
	@RequestMapping(value = "/getRequestResponseDetail", method = RequestMethod.GET)
	public Object getFailedBookingRequestResponse(@RequestHeader(value = "accessToken", required=false) String accessToken,@RequestHeader(value = "bookingId", required=false) String bookingId, @RequestHeader(value = "operation") String operation,@RequestHeader(value = "operationType") String operationType) throws JSONException, JAXBException, JsonParseException, JsonMappingException, IOException
	{
		org.json.simple.JSONObject responseJson=null;
		logger.info("Rquest Response XML");
		try{
			if(operation.equalsIgnoreCase("booking")){
				logger.info("**Booking XML**");
				if(accessToken!=null && accessToken!=""){
					return  loadClassesAuto.requestResponseXML.getBookingXML(accessToken,operationType,"accessCode");
				}else if(bookingId!=null && bookingId!=""){
					return  loadClassesAuto.requestResponseXML.getBookingXML(bookingId,operationType,"bookingId");
				}else{
					logger.info("Please Enter alteast one value access code or booking Id");
					responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
		            responseJson.put(Constants.DESCRIPTION, "Please Enter alteast one value access code or booking Id");
		            return responseJson;
				}
			}else if(operation.equalsIgnoreCase("getdetails")){
				logger.info("**Get Details XML**");
				if(accessToken!=null && accessToken!=""){
					return  loadClassesAuto.requestResponseXML.getDetailsXML(accessToken,operationType,"accessCode");
				}else if(bookingId!=null && bookingId!=""){
					return  loadClassesAuto.requestResponseXML.getDetailsXML(bookingId,operationType,"bookingId");
				}else{
					logger.info("Please Enter alteast one value access code or booking Id");
					responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
		            responseJson.put(Constants.DESCRIPTION, "Please Enter alteast one value access code or booking Id");
		            return responseJson;
				}
			}else if(operation.equalsIgnoreCase("policy")){
				logger.info("**Get Policy XML**");
				if(accessToken!=null && accessToken!=""){
					return  loadClassesAuto.requestResponseXML.getPolicyXML(accessToken,operationType,"accessCode");
				}else if(bookingId!=null && bookingId!=""){
					return  loadClassesAuto.requestResponseXML.getPolicyXML(bookingId,operationType,"bookingId");
				}else{
					logger.info("Please Enter alteast one value access code or booking Id");
					responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
		            responseJson.put(Constants.DESCRIPTION, "Please Enter alteast one value access code or booking Id");
		            return responseJson;
				}
			}else if(operation.equalsIgnoreCase("reprice")){
				logger.info("**Get Reprice XML**");
				if(accessToken!=null && accessToken!=""){
					return  loadClassesAuto.requestResponseXML.getRepriceXML(accessToken,operationType,"accessCode");
				}else if(bookingId!=null && bookingId!=""){
					return  loadClassesAuto.requestResponseXML.getRepriceXML(bookingId,operationType,"bookingId");
				}else{
					logger.info("Please Enter alteast one value access code or booking Id");
					responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
		            responseJson.put(Constants.DESCRIPTION, "Please Enter alteast one value access code or booking Id");
		            return responseJson;
				}
			}else if(operation.equalsIgnoreCase("retrieve")){
				logger.info("**Get Retrieve XML**");
				if(accessToken!=null && accessToken!=""){
					return  loadClassesAuto.requestResponseXML.getRetrieveXML(accessToken,operationType,"accessCode");
				}else if(bookingId!=null && bookingId!=""){
					return  loadClassesAuto.requestResponseXML.getRetrieveXML(bookingId,operationType,"bookingId");
				}else{
					logger.info("Please Enter alteast one value access code or booking Id");
					responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
		            responseJson.put(Constants.DESCRIPTION, "Please Enter alteast one value access code or booking Id");
		            return responseJson;
				}
			}else if(operation.equalsIgnoreCase("cancel")){
				logger.info("**Get Cancel XML**");
				if(accessToken!=null && accessToken!=""){
					return  loadClassesAuto.requestResponseXML.getCancelXML(accessToken,operationType,"accessCode");
				}else if(bookingId!=null && bookingId!=""){
					return  loadClassesAuto.requestResponseXML.getCancelXML(bookingId,operationType,"bookingId");
				}else{
					logger.info("Please Enter alteast one value access code or booking Id");
					responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
		            responseJson.put(Constants.DESCRIPTION, "Please Enter alteast one value access code or booking Id");
		            return responseJson;
				}
			}
			
			
			logger.error("operation or operation type not matched");
            responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
            responseJson.put(Constants.STATUS,false);
            responseJson.put("data", "");
            return responseJson;
		}catch (Exception e) {
            logger.error("Exception occured", e);
            responseJson.put(Constants.CODE, Constants.INTERNAL_SERVER_ERROR);
            responseJson.put(Constants.STATUS, e.getMessage());
            responseJson.put("data", "");
            return responseJson;
        }
	}
}
