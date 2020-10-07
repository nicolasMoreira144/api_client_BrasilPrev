package com.spring.course.dto;

import javax.validation.constraints.NotNull;

import com.spring.course.domain.enums.Role;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class UserUpdateRoledto {
	
	@NotNull(message = "Role required")
	private Role role;
	
}
