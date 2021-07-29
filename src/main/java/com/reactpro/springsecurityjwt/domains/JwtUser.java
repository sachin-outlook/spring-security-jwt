package com.reactpro.springsecurityjwt.domains;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity @Table(name="User")
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class JwtUser {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String username;
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<JwtRole> roles = new ArrayList<>();
	
	

}
