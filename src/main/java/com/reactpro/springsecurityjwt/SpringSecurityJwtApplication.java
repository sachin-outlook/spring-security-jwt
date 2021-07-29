package com.reactpro.springsecurityjwt;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.reactpro.springsecurityjwt.domains.JwtRole;
import com.reactpro.springsecurityjwt.domains.JwtUser;
import com.reactpro.springsecurityjwt.repos.JwtRoleRepository;
import com.reactpro.springsecurityjwt.repos.JwtUserRepository;

@SpringBootApplication
public class SpringSecurityJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
	}

	
	
	
	
	@Autowired
	private JwtUserRepository jwtUserRepository;
	
	@Autowired
	private JwtRoleRepository jwtRoleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@Bean
	public CommandLineRunner getRunner() {
		 return  (v ) -> {
			
			 //Create Roles 
			 List<JwtRole> roles = Arrays.asList(
					 new JwtRole("ROLE_USER"),
					 new JwtRole("ROLE_ADMIN"),
					 new JwtRole("ROLE_MANAGER")
					 );
			 
			 jwtRoleRepository.saveAllAndFlush(roles);
			 
			 jwtRoleRepository
			 	.findByName("ROLE_USER").ifPresent(System.out::println);
			 
			 
			 //JwtUser user1 =  JwtUser.builder().name("sachin").password("sachin").build();
			 JwtUser user1 = new JwtUser();
			 user1.setName("sachin alok");
			 user1.setPassword(  bCryptPasswordEncoder.encode("sachin") );
			 user1.setUsername("sachin");
			 
			 jwtUserRepository.saveAndFlush(user1);
			 
			 
			 jwtRoleRepository.findByName("ROLE_USER").ifPresent(role -> user1.getRoles().add(role));
			 jwtRoleRepository.findByName("ROLE_ADMIN").ifPresent(role -> user1.getRoles().add(role));
			 
			 jwtUserRepository.save(user1);
			 
			 
			 JwtUser user2 = new JwtUser();
			 user2.setName("alok");
			 user2.setPassword( bCryptPasswordEncoder.encode("alok123") );
			 user2.setUsername("alok");
			 
			 jwtUserRepository.saveAndFlush(user2);
			 
			 
			 jwtRoleRepository.findByName("ROLE_USER").ifPresent(role -> user2.getRoles().add(role));
			 //jwtRoleRepository.findByName("ROLE_ADMIN").ifPresent(role -> user1.getRoles().add(role));
			 
			 jwtUserRepository.save(user2);
			 
			 
			 
			 
			 
		};
	}
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
	


}
