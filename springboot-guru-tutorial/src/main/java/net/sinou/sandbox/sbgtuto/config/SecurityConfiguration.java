package net.sinou.sandbox.sbgtuto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//
		// httpSecurity.authorizeRequests().antMatchers("/").permitAll();

		// Warning: dev only: enables the H2 web console
		// See https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
		httpSecurity.authorizeRequests().antMatchers("/").permitAll().and().authorizeRequests()
				.antMatchers("/console/**").permitAll();
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}
}
