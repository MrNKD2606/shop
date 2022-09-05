package com.nkd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nkd.entity.Account;
import com.nkd.entity.Role;

import com.nkd.repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = userRepository.findOneByUserNameAndStatus(username, 1);

		if (account == null) {
			throw new UsernameNotFoundException("User not found");
		}
//		String role = "ROLE_ADMIN";
//		
//		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//		GrantedAuthority authority = new SimpleGrantedAuthority(role);
//		grantList.add(authority);
//		List<GrantedAuthority> authorities = new ArrayList<>();
//		for(RoleEntity role : userEntity.getRoles()) {
//			authorities.add(new SimpleGrantedAuthority(role.getCode()));
//		}

		// UserDetails userDetails = new User(username, userEntity.getPassword(),
		// grantList);
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : account.getRoles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getCode()));
		}
		UserDetails userDetails = new User(username, account.getPassword(), authorities);

//		List<GrantedAuthority> authorities = new ArrayList<>();
//		GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
//		authorities.add(authority);		
//		UserDetails userDetails = new User(username, userEntity.getPassword(), authorities);
		return userDetails;
	}

}
