package com.nkd.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

	public static User getPrincipal() {
		User user = (User) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return user;
	}

	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<>();
		//Lấy authorities được put từ CustomUserDetailsService
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		// Put chuỗi String role(getCode) từ authorities
		for (GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());
		}
		return results;
	}
}
