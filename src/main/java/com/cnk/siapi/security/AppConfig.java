//Code to validate user using mysql 
/*package com.cnk.siapi.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Import({ WebSecurityConfig.class })
public class AppConfig {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	
		logger.info("inside dataSource call");
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://10.21.32.68:3306/test");
	    driverManagerDataSource.setUsername("CnkAdmin");
	    driverManagerDataSource.setPassword("cnkadmin123");
	    return driverManagerDataSource;
	}
}*/