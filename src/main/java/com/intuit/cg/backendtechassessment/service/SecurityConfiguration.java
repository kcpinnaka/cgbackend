/*package com.intuit.cg.backendtechassessment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.intuit.cg.backendtechassessment.controller.requestmappings.RequestMappings;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(User.withDefaultPasswordEncoder())
        .withUser("user1").password("user1").roles("USER").and()
        .withUser("user2").password("user2").roles("USER").and()
        .withUser("admin").password("admin").roles("ADMIN","USER");
    }
    
	@SuppressWarnings("deprecation")
	@Bean
    @Override
    public UserDetailsService userDetailsService() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(User.withDefaultPasswordEncoder().username("admin").
				password("admin").roles("ADMIN","USER").build());
		users.add(User.withDefaultPasswordEncoder().username("user1").
				password("user1").roles("USER").build());
		users.add(User.withDefaultPasswordEncoder().username("user2").
				password("user2").roles("USER").build());
        return new InMemoryUserDetailsManager(users);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
        .anyRequest().permitAll()
        	.and().httpBasic().and().csrf().disable();
    	http.csrf().disable()
  	  	.authorizeRequests()
        .antMatchers(RequestMappings.CREATE_BID,
        RequestMappings.GET_PROJECT).hasRole("USER")
        .antMatchers(RequestMappings.PROJECTS,RequestMappings.BIDS,
        		RequestMappings.CREATE_PROJECT).hasRole("ADMIN").and().httpBasic();    	
    	// add this line to use H2 web console
        http.headers().frameOptions().disable();
    }
}*/