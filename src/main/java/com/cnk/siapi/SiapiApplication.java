package com.cnk.siapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Durgesh.Songire
 *
 */

@SpringBootApplication
@ComponentScan(basePackages ="com.cnk.siapi")
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableAutoConfiguration(exclude = {JndiConnectionFactoryAutoConfiguration.class,DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})

public class SiapiApplication  extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(SiapiApplication.class, args);	
	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(SiapiApplication.class);    
	    }
}
	
