package com.bilas.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 

	@Autowired
	private DataSource dataSource;

	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.jdbcAuthentication().dataSource(dataSource)  
			.usersByUsernameQuery("select email as principal, password as credentials, true from user where email = ? ")	//the reason for adding true here is that spring security requires that if this variable is true the user can be authenticated. If it is false, the user can not be authenticated even if user's email and password are correct.
			.authoritiesByUsernameQuery("select user_email as principal, role_name as role from user_roles where user_email=?")
			.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/register", "/", "/about", "/login", "/css/**", "/webjars/**").permitAll()   //the pages that i want to permit everyone to access should be written here
		.antMatchers("/profile").hasAnyRole("USER, ADMIN").
		antMatchers("/users","/addTask").hasAnyRole("ADMIN").
		and().formLogin().loginPage("/login").permitAll().	//the requests like adding tasks, accessing profile page should be authenticated
		defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/login");    
	}
	
	
}
