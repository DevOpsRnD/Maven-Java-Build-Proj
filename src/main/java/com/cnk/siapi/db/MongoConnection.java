package com.cnk.siapi.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.mongodb.MongoClient;

@Service
public class MongoConnection {
	
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	private static MongoOperations mongoOperation;
	
	public MongoOperations getInstance(String databaseName)
	{
		String databaseIP = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_IP);
		int databasePort = loadClassesAuto.applicationProperties.getPropertyIntegerValue(Constants.DATABASE_PORT);

		if (mongoOperation == null) {
			mongoOperation = new MongoTemplate(new MongoClient(databaseIP, databasePort) , databaseName);
		}	
		return mongoOperation;
	}
}