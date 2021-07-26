package com.reactpro.springsecurityjwt.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		username = "sachin"; 
		String password="alok";
		boolean enabled = true, accountNonExpired=true, credentialsNonExpired=true, accountNonLocked=true;
		List<? extends GrantedAuthority> authorities =  
				Arrays
//				.asList("ROLE_USER", "ROLE_ADMIN").stream()
				.asList("ROLE_ADMIN").stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
		User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}

}
