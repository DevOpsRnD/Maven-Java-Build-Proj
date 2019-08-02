package com.cnk.siapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({ @PropertySource(value = "application.properties", ignoreResourceNotFound = true) })
public class ApplicationProperties {

	 @Autowired
	 private Environment env;
	 
	 public String getProperty(String propName) {
		 return env.getProperty(propName);
	 }
	 
	 public Integer getPropertyIntegerValue(String propName) {
		 return Integer.parseInt(env.getProperty(propName));
	 }
}
