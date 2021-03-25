package com.bk.kabow.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bk.kabow.service.user.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
		.antMatchers("/kabow/admin/home/**").access("hasRole('MANAGER') or hasRole('ADMIN')")
		.antMatchers("/kabow/profile/**").hasRole("USER")
		.antMatchers(
				"/kabow/index/**",
				"/kabow/showRegistration/**",
				"/kabow/registration**",
				"/kabow/searchLocation/**",
				"/kabow/information-place/**",
				"/js/**",
				"/css/**",
				"/images/**")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and().formLogin().loginPage("/kabow/loginPage")
		.loginProcessingUrl("/kabow/login")
		.defaultSuccessUrl("/kabow/afterLogin",true)
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/kabow/logout"))
		.deleteCookies("JSESSIONID")
		.deleteCookies("SESSION")
		.logoutSuccessUrl("/kabow/afterLogout")
		.permitAll();
	}
}
