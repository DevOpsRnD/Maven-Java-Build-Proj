package com.cnk.siapi.common;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnk.siapi.config.ApplicationProperties;
import com.cnk.siapi.controller.VoucherController;
import com.cnk.siapi.db.CommonOperations;
import com.cnk.siapi.db.GetSupplierList;
import com.cnk.siapi.db.MongoConnection;
import com.cnk.siapi.db.MongoDBOperation;
import com.cnk.siapi.model.book.BookRequestImpl;
import com.cnk.siapi.model.cancel.CancelRequestImpl;
import com.cnk.siapi.model.getdetails.GetDetailsRequestImpl;
import com.cnk.siapi.model.getpolicy.GetPolicyRequestImpl;
import com.cnk.siapi.model.master.StaticActivityImpl;
import com.cnk.siapi.model.reprice.RepriceRequestImpl;
import com.cnk.siapi.model.retrieve.RetrieveRequestImpl;
import com.cnk.siapi.model.search.SearchRequestImpl;
import com.cnk.siapi.showxml.RequestResponseXML;
import com.cnk.siapi.supplier.SupplierOperations;

@Service
public class LoadClassesAuto {

	@Autowired
	public SupplierOperations supplierOperations;
	
	@Autowired
	public SearchRequestImpl searchRequest;
	
	@Autowired
	public BookRequestImpl bookRequest;
	
	@Autowired
	public GetDetailsRequestImpl getDetails;
	
	@Autowired
	public GetPolicyRequestImpl getPolicy;
	
	@Autowired
	public CancelRequestImpl cancelBook;
	
	@Autowired
	public RepriceRequestImpl reprice;
	
	@Autowired
	public RetrieveRequestImpl retrieve;
	
	@Autowired
	public StaticActivityImpl activityImpl; 
	
	@Autowired
	public ApplicationProperties applicationProperties;
	
	@Autowired
	public GetSupplierList getSupplierList; 
	
	@Autowired
	public MongoDBOperation mongoDBOperation;
	
	@Autowired
	public MongoConnection mongoConnection;
	
	@Autowired
	public CommonOperations commonOperations; 
	
	@Autowired
	public RequestResponseXML requestResponseXML;
	
	@Autowired
	public VoucherController voucherController;
	
	public static JSONObject responseJson;
}
