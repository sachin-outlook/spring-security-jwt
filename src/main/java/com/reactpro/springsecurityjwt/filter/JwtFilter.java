package com.reactpro.springsecurityjwt.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.reactpro.springsecurityjwt.services.CustomUserDetailsService;
import com.reactpro.springsecurityjwt.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomUserDetailsService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String authString = request.getHeader("Authorization");
		String token = null;
		String userName = null;
		
		if( authString != null && authString.startsWith("Bearer ")){
			token = authString.substring(7);
			userName =  jwtUtil.extractUsername(token);
			
		}
		
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails =  userService.loadUserByUsername(userName);
			if( jwtUtil.validateToken(token, userDetails)){
				UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
