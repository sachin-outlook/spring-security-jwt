package com.reactpro.springsecurityjwt.domains;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name="Role") @Data @AllArgsConstructor @NoArgsConstructor
public class JwtRole {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	
	public JwtRole(String name) {
		super();
		this.name = name;
	}
	
	

}
