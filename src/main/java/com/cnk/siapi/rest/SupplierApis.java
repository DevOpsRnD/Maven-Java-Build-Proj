package com.cnk.siapi.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.storerequst.BookRequestResponse;
import com.cnk.siapi.response.CommonBookRS;
import com.cnk.siapi.supplier.AccessToken;
import com.cnk.siapi.supplier.SupplierCredentials;
import com.cnk.siapi.supplier.UpdateBooking;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "supplier")
@ApiIgnore
public class SupplierApis {
	
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	Gson gson = new Gson();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/addSupplierCredentials", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}) 
	public ResponseEntity<?> addSupplierCredentials(@RequestBody String data)
	{
		try {
			JSONObject supplierInfo =new JSONObject(data);
			String operation = supplierInfo.get("operation").toString().toLowerCase();
			JSONObject supplierHeaderDAO = new JSONObject(supplierInfo.get("supplierdetails").toString());
			
			String supplierId = supplierHeaderDAO.get("supplierId").toString();
			SupplierCredentials supplierCredentials = new SupplierCredentials();
			supplierCredentials.setSupplierCode(supplierInfo.getString("suppliercode"));
			supplierCredentials.setSupplierName(supplierId);
			supplierCredentials.setSupplierHeaderDAO(supplierHeaderDAO);
			
			int saveResult = loadClassesAuto.supplierOperations.saveSupplierCredentials(supplierCredentials, operation+"supplierlist");
			logger.info("Save supplier credentials response for " + supplierId + " : " + saveResult);
			
			if( saveResult == 0)
				return new ResponseEntity(supplierId + " Supplier credentials saved successgully", HttpStatus.OK);
			else
				return new ResponseEntity(supplierId + "Supplier credentials is already present", HttpStatus.EXPECTATION_FAILED);
			
		} catch (Exception e) {
			logger.error("Supplier credentials save failed", e);
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/saveAccessToken", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}) 
	public ResponseEntity<?> saveAccessToken(@RequestBody String data)
	{
		try {
			JSONObject accessTokenDetails =new JSONObject(data);
			AccessToken accessToken = gson.fromJson(accessTokenDetails.toString(), AccessToken.class);
			
			boolean status = loadClassesAuto.supplierOperations.saveAccessToken(accessToken);
			logger.info("Save access token response : " + status);
			
			if(status) {
				return new ResponseEntity("Token saved successgully", HttpStatus.OK);
			} else {
				return new ResponseEntity("Token already present", HttpStatus.EXPECTATION_FAILED);
			}
		}
		catch (Exception e) {
			logger.error("Failed to save Access Token", e);
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/tokenvalid", method = RequestMethod.GET)
	public ResponseEntity<?> tokenValid(@RequestHeader(value = "token") String token)
	{
		try {
			Date date = new Date();
			JSONObject tokenStatus = loadClassesAuto.supplierOperations.isTokenValid(token, date, Constants.VALID);
			logger.info("Token status : "+tokenStatus);
			
			if((boolean) tokenStatus.get(Constants.STATUS))
				return new ResponseEntity<>(tokenStatus.get(Constants.DESCRIPTION), HttpStatus.OK);
			else
				return new ResponseEntity<>(tokenStatus.get(Constants.DESCRIPTION), HttpStatus.UNAUTHORIZED);
		}catch (Exception e) {
			logger.error("Token status check failed", e);
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getbookingdetails", method = RequestMethod.GET)
	public ResponseEntity<?> getBookingDetails(@RequestHeader(value = "limit") Integer limit,
											   @RequestHeader(value = "offset") Integer offset,HttpServletRequest request)
	{
		org.json.simple.JSONObject response = new org.json.simple.JSONObject();
		try {
			logger.info("limit : " +limit);
			logger.info("offset : " +offset);
			String username = request.getUserPrincipal().getName();
			JSONObject responseData = loadClassesAuto.supplierOperations.getBookingData(username,limit,offset);
			response = (org.json.simple.JSONObject) new JSONParser().parse(responseData.toString());
			logger.info(response);
			
			if(response.get(Constants.STATUS).equals(Constants.SUCCESS))
				return new ResponseEntity<>(response, HttpStatus.OK);
			else 
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			response.put(Constants.STATUS , Constants.FAILED);
			response.put(Constants.DESCRIPTION , "Something went wrong, Exception occur");
			logger.error(response, e);
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updatebookingstatus", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBookingStatus(@RequestParam(value = "bookid") String bookId,
												 HttpServletRequest request)
	{
		org.json.simple.JSONObject response = new org.json.simple.JSONObject();
		try {
			String userId = request.getUserPrincipal().getName();
			JSONObject responseData = loadClassesAuto.supplierOperations.updateBooking(bookId,userId);
			response = (org.json.simple.JSONObject) new JSONParser().parse(responseData.toString());
			logger.info(response);
			
			if(response.get(Constants.STATUS).equals(Constants.SUCCESS))
				return new ResponseEntity<>(response, HttpStatus.OK);
			else 
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			response.put(Constants.STATUS , Constants.FAILED);
			response.put(Constants.DESCRIPTION , "Something went wrong, Exception occur");
			logger.error(response, e);
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cancelbooking", method = RequestMethod.PUT)
	public ResponseEntity<?> cancelBooking(@RequestParam(value = "bookid") String bookId,
										   HttpServletRequest request)
	{
		org.json.simple.JSONObject response = new org.json.simple.JSONObject();
		try {
			String userId = request.getUserPrincipal().getName();
			JSONObject responseData = loadClassesAuto.supplierOperations.cancelBooking(bookId,userId);
			response = (org.json.simple.JSONObject) new JSONParser().parse(responseData.toString());
			logger.info(response);
			
			if(response.get(Constants.STATUS).equals(Constants.SUCCESS))
				return new ResponseEntity<>(response, HttpStatus.OK);
			else 
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			response.put(Constants.STATUS , Constants.FAILED);
			response.put(Constants.DESCRIPTION , "Something went wrong, Exception occur");
			logger.error(response, e);
			return new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@RequestMapping(value = "/getPolicies", method = RequestMethod.GET)
	public ResponseEntity<?> getPolicies(@RequestHeader(value = "bookid") String bookId, HttpServletRequest request)
	{
		String userId = request.getUserPrincipal().getName();
		try {
			try {
				return (loadClassesAuto.supplierOperations.getPolicies(bookId,userId, request));
			} catch (URISyntaxException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch blockO
				e.printStackTrace();
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
			
	}

	@SuppressWarnings({ "rawtypes", "static-access", "unchecked", "unused", "deprecation" })
	@RequestMapping(value = "/voucher", produces = { "application/json" }, method = RequestMethod.GET)
	public @ResponseBody ResponseEntity getVoucher(@QueryParam(value="bookId")String bookId, HttpServletRequest request) throws IOException
	{

	    ResponseEntity respEntity = null;
	    HttpHeaders headers = new HttpHeaders();
	    byte[] reportBytes = null;
	    logger.info("***** Voucher Generation *****");
	    String path = loadClassesAuto.applicationProperties.getProperty(Constants.VOUCHERPATH) + bookId + ".pdf";
	    String userId;
		try
		{
			userId = request.getUserPrincipal().getName();
		}
		catch(NullPointerException e)
		{
			userId = "testUser";
		}
		logger.info("*** " + userId + "Queried for Voucher " + bookId + "***");
		
		BookRequestResponse bookingRequestResponse = loadClassesAuto.mongoDBOperation.findByBookingId(bookId);
		
		if(bookingRequestResponse.getSupplierId().equals("VIATOR"))
		{
			BookRequestResponse bookJson =  (BookRequestResponse) loadClassesAuto.supplierOperations.getBookingResponse(bookId);
			
			Gson gson= new Gson();
			//JSONObject
			CommonBookRS bookResponse = bookJson.getBookResponse();
			logger.info(bookResponse.getResponseBody().getCnkStatus());	
			headers.add("Content-Type", "text/html");
			respEntity = new ResponseEntity(bookResponse.getResponseBody().getOTATourActivityBookRSWrapper().
					getOTATourActivityBookRS().getReservationDetails().getBasicInfo().getTPAExtensions().
					getActivityTPA().getVouchers().getUrl(),headers, HttpStatus.OK);
			return respEntity;
		}
		else
		{
			loadClassesAuto.voucherController.createPdf(path, bookId, userId);
			
			File result=new File(path);

		    if(result.exists()){
		        InputStream inputStream = new FileInputStream(path);
		        String type=result.toURL().openConnection().guessContentTypeFromName(bookId + ".pdf");

		        byte[]out=org.apache.commons.io.IOUtils.toByteArray(inputStream);

		        HttpHeaders responseHeaders = new HttpHeaders();
		        responseHeaders.add("content-disposition", "attachment; filename=" + bookId + ".pdf");
		        responseHeaders.add("Content-Type",type);

		        respEntity = new ResponseEntity(out, responseHeaders,HttpStatus.OK);
		    }else{
		        respEntity = new ResponseEntity ("File Not Found", HttpStatus.OK);
		    }
		    return respEntity;
		}
	       
	}
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	@RequestMapping(value = "/confirmstatus", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON}) 
	public ResponseEntity<?> updateConfirmation(@RequestBody UpdateBooking bookingData)
	{
		org.json.simple.JSONObject response = new org.json.simple.JSONObject();
		logger.info("booking confrmation api" + bookingData.getSuppBookRef());
		try {
			
			Boolean status =loadClassesAuto.supplierOperations.updateBooking(bookingData);
			if(status) {
				response.put("status", true);
				response.put("description", "Booking status updated successgully");
				return new ResponseEntity(response, HttpStatus.OK);
			} else {
				response.put("status", false);
				response.put("description", "failed to update booking status");
				return new ResponseEntity(response, HttpStatus.EXPECTATION_FAILED);
			}
		}
		catch (Exception e) {
			logger.error("Bad input", e);
			return new ResponseEntity<>(e, HttpStatus.EXPECTATION_FAILED);
		}
	}
	
}
