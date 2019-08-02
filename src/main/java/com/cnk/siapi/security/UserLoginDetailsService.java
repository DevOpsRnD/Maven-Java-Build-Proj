package com.cnk.siapi.security;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;


public class UserLoginDetailsService implements UserDetailsService
{
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());

	@SuppressWarnings({ "unchecked" })
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		logger.info(username + " trying to log in");
		
		String databaseIP = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_IP);
		int databasePort = loadClassesAuto.applicationProperties.getPropertyIntegerValue(Constants.DATABASE_PORT);
		String databaseName = loadClassesAuto.applicationProperties.getProperty(Constants.DATABASE_NAME);
		
		MongoClient mongoClient = new MongoClient(databaseIP, databasePort);

		MongoDatabase database = mongoClient.getDatabase(databaseName);
		MongoCollection<Document> collection = database.getCollection("apilogindetails");

		Document document = collection.find(Filters.eq("username", username)).first();

		if (document != null) {

			String name = document.getString("username");
			String password = document.getString("password");
			List<String> authorities = (List<String>) document.get("authorities");

			UserLoginDetails userLoginDetails = new UserLoginDetails(name, password, authorities.toArray(new String[authorities.size()]));
			logger.info("User found");
			mongoClient.close();
			return userLoginDetails;
		}
		logger.info(username + " failed to log in");
		mongoClient.close();
		return null;
	}

}
