package com.spring.course.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.spring.course.domain.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "client")
public class Client implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 75, nullable = false)
	private String name;
	
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@Column(length = 100, nullable = false)
	private String address;
	
	@Column(length = 75, nullable = false, unique = true)
	private String email;
	
	@Column(length = 20, nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Getter(onMethod = @__({@JsonIgnore})) 
	@Setter(onMethod = @__({@JsonProperty})) // ignorando a desereliazação da senha para não exibir
	@Column(length = 100, nullable = false)
	private String password;
}
