package com.cnk.siapi.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.master.StaticActivities;
import com.cnk.siapi.productstatic.response.ProductActivityRS;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value= "sightseeingmaster")
@Api(tags = "Activities", description = "Master Data for Activities Implementation")
public class ActivityMasterImpl 
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	
	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@RequestMapping(value = "/getstaticactivity", method= RequestMethod.POST,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML}, consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get Static Activities")
	public Object getSupplierActivities(@RequestBody StaticActivities activitiesRequest) throws JSONException
	{
		
		Object op = loadClassesAuto.activityImpl.getActivites(activitiesRequest); 
		return op; 
	}
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	@RequestMapping(value = "/getproductinfo", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get Product Wise Activity Info")
	public Object getActivityDetails(@RequestParam String supplierCode , @RequestParam String supplierProductCode)
	{
		int statusCode = 200;
		Object responseObject = new Object();
		Object activityInfo = loadClassesAuto.activityImpl.getActivityInfo(supplierCode, supplierProductCode); 
		
		if(activityInfo instanceof ProductActivityRS) {
			responseObject = (ProductActivityRS) activityInfo;
			logger.info(Constants.SUCCESS);
		}
		else
		{
			responseObject = activityInfo;
			statusCode = 400;
		}
		
		return new ResponseEntity(activityInfo, HttpStatus.valueOf(statusCode));

	}
	
	@RequestMapping(value = "/getcitymaster", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get all Cities and City Codes")
	public Object getCities(HttpServletRequest request )
	{
		return loadClassesAuto.activityImpl.getCityMaster(request); 
	}
	
	@RequestMapping(value = "/getcities", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get all Cities and City Codes")
	public Object getCities(HttpServletRequest request, @RequestParam String countryCode)
	{
		return loadClassesAuto.activityImpl.getCities(request, countryCode); 
	}
	
	@RequestMapping(value = "/getcountrymaster", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get all Countries and Country Codes")
	public Object getCountries(HttpServletRequest request )
	{
		return loadClassesAuto.activityImpl.getCountryMaster(request); 
	}
	
	@RequestMapping(value = "/getcountry", method= RequestMethod.GET,produces = {MediaType.APPLICATION_JSON, 
            MediaType.APPLICATION_XML})
	@ApiOperation(value = "Get all Countries and Country Codes")
	public Object getCountry(HttpServletRequest request, @RequestParam String countryCode )
	{
		return loadClassesAuto.activityImpl.getCountry(request, countryCode); 
	}
}
