package com.reactpro.springsecurityjwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactpro.springsecurityjwt.models.JwtRequest;
import com.reactpro.springsecurityjwt.models.JwtResponse;
import com.reactpro.springsecurityjwt.services.CustomUserDetailsService;
import com.reactpro.springsecurityjwt.util.JwtUtil;

@RestController
public class HomeController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService usersService;
	
	@GetMapping("/api/home/welcome")
	public String home(){
		return "Welcome";
	}
	
	@GetMapping("/api/users/welcome")
	public String welcomeUsers(){
		return "Welcome Users";
	}
	
	@GetMapping("/api/admins/welcome")
	public String welcomeAdmins(){
		return "Welcome Admins";
	}
	
	@PostMapping("/authenticate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
		try{
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
					);
		}catch(BadCredentialsException ex){
			System.out.println(ex.getMessage());
			throw new Exception("INVALID_CREDENTIALS", ex);
		}
		
		final UserDetails userDetails = usersService.loadUserByUsername(jwtRequest.getUsername());
		
		final  String token = jwtUtil.generateToken(userDetails);
		
		return new JwtResponse(token);
		
	}
	
}
