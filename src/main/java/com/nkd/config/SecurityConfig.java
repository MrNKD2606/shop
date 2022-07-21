package com.nkd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nkd.service.impl.CustomerUserDetailsService;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	CustomerUserDetailsService customerUserDetailsService;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/web/**", "/web1/**", "/admin1/**").permitAll()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.anyRequest().authenticated()
			.and().exceptionHandling().accessDeniedPage("/403")
			.and().formLogin().loginPage("/login").permitAll()
			.defaultSuccessUrl("/default")
			.failureUrl("/login?sucess=false")
			.loginProcessingUrl("/j_spring_security_check")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/web");
				
			
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
	}
}
