package com.spring.course.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.spring.course.domain.Client;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ClientUpdatedto {
	
	@NotBlank(message = "Name required")
	private String name;
	
	@Size(min = 11, max = 11, message = "Invalid CPF")
	private String cpf;
	
	@Size(min = 7, max = 99, message = "Password must be between 7 and 99")
	private String address;
	
	@Email(message = "Invalid email address")
	private String email;
	
	@Size(min = 7, max = 99, message = "Password must be between 7 and 99")
	private String password;
	
	public Client transformToClient() {
		Client client = new Client(null, this.name, this.cpf, this.address, this.email, null, this.password);
		return client;
	}

}
