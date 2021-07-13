package com.mumSchud.springbootmum.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	BaseUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/courses").hasRole("ADMIN")
			.antMatchers("/blocks").hasRole("ADMIN")
			.antMatchers("/students").hasAnyRole("ADMIN", "STUDENT")
			.antMatchers("/faculties").hasRole("ADMIN")
			.antMatchers("/entries").hasRole("ADMIN")
			.antMatchers("/sections").hasRole("ADMIN")
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/student").hasRole("STUDENT")
			.antMatchers("/**").permitAll()
			.and()
			.formLogin()
			.and()
			.logout()
			.logoutSuccessUrl("/");
		http.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
