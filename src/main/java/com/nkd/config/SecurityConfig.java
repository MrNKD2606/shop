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
		http.csrf().disable();
		http.authorizeRequests()	
			//.antMatchers(HttpMethod.POST).permitAll()
			//Những trang không yêu cầu login, các đường dẫn tĩnh
			.antMatchers("/web/**", "/web1/**", "/admin1/**", "/images/**", "/api/**").permitAll()
			//Những trang yêu cầu login và được truy cập bởi nhiều role
			.antMatchers("/test").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
			//Những trang chỉ dành cho ADMIN
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			//Mọi request khác đều phải chứng thực(login)
			.anyRequest().authenticated()
			//Khi đã login với vai trò XX
			//Nhưng truy cập vào vai trò YY
			//Ngoại lệ AccessDeniedException sẽ ném ra trang /403
			.and().exceptionHandling().accessDeniedPage("/403")
			.and()
			//Cấu hình login
			//Những request yêu cầu login nhưng chưa login thì sẽ đi đên trang /login
			.formLogin().loginPage("/login").permitAll()
			//Khi login thành công sẽ đến trang /default
			//Tại default với với mỗi quyền sẽ đi đến trang tương ứng
			.defaultSuccessUrl("/default")
			//Khi login thất bại sẽ quay trở lại trang /login
			.failureUrl("/login?sucess=false")
			//Submit URL
			.loginProcessingUrl("/j_spring_security_check")
			//Khi logout thì sẽ trở về trang /web
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/web");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Tìm kiếm user trong Database
		// Set đặt password theo PasswordEncoder
		auth.userDetailsService(customerUserDetailsService).passwordEncoder(passwordEncoder());
	}
}
