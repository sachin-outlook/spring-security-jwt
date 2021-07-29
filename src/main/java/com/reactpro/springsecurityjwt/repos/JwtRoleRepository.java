package com.reactpro.springsecurityjwt.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reactpro.springsecurityjwt.domains.JwtRole;

import lombok.extern.slf4j.Slf4j;

@Repository

public interface JwtRoleRepository 
					extends  JpaRepository<JwtRole, Long>{
	
	 Optional<JwtRole> findByName(String name);

}
