package com.reactpro.springsecurityjwt.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.reactpro.springsecurityjwt.domains.JwtUser;
import com.reactpro.springsecurityjwt.repos.JwtUserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private JwtUserRepository jwtUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("Fetching the user with {}", username);
		Optional<JwtUser> user = jwtUserRepository.findByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		log.debug( "User found {  }", user );
		JwtUser userDb =  user.get();
		
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		userDb.getRoles().forEach(val -> {
			authorities.add( new SimpleGrantedAuthority(val.getName()));
		});
		
		return new org.springframework.security.core.userdetails.User(userDb.getUsername(),
				userDb.getPassword(), authorities);
				
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		username = "sachin"; 
//		String password="alok";
//		boolean enabled = true, accountNonExpired=true, credentialsNonExpired=true, accountNonLocked=true;
//		List<? extends GrantedAuthority> authorities =  
//				Arrays
////				.asList("ROLE_USER", "ROLE_ADMIN").stream()
//				.asList("ROLE_ADMIN").stream()
//				.map(SimpleGrantedAuthority::new)
//				.collect(Collectors.toList());
//		User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
//		return user;
//	}
	
	
	

}
