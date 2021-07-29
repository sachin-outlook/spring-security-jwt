package com.reactpro.springsecurityjwt.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactpro.springsecurityjwt.domains.JwtUser;

@Repository
public interface JwtUserRepository 
			extends  JpaRepository<JwtUser, Long> {
	
	Optional<JwtUser> findByUsername(String username);

}
