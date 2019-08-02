package com.cnk.siapi.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.savedrequest.NullRequestCache;

/**
 * 
 * @author Durgesh.Songire
 * Spring Basic Secuirty Mechanism
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected final Log logger = LogFactory.getLog(this.getClass());
	
	//Code to validate user with hard code values
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() //
				.anyRequest().authenticated() //
				.and().requestCache().requestCache(new NullRequestCache()) //
				.and().httpBasic() //
				.and().csrf().disable();
	}

	@Autowired
	void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //
				.withUser("user").password("user").authorities("ROLE_USER") //
				.and() //
				.withUser("admin").password("admin").authorities("ROLE_USER", "ROLE_ADMIN");
	}*/
	
	
	//Code to validate user using mysql
	/*@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("inside configAuthentication call");
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username,password, enabled from users where username=?")
			.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

//		http.authorizeRequests()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
//			.and()
//				.formLogin().loginPage("/login").failureUrl("/login?error")
//					.usernameParameter("username").passwordParameter("password")
//			.and()
//				.logout().logoutSuccessUrl("/login?logout")
//			.and()
//				.exceptionHandling().accessDeniedPage("/403")
//			.and()
//				.csrf();
		logger.info("inside configure call");
		http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
		    .and()
		    .httpBasic();
		
	}*/
	
	
	//Code to validate user using mongodb
	@Bean
    public UserDetailsService getUserDetailsService() {
        return  new UserLoginDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    	UserDetailsService userDetailsService = getUserDetailsService();
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/public").permitAll()
                .anyRequest().authenticated()
                .and().requestCache().requestCache(new NullRequestCache())
                .and()
                .httpBasic()
        		.and().csrf().disable();
        
        //Uncomment below code to enable login form while Login
//                .and()
//                .formLogin()
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
    	
    }
}