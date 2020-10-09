package com.spring.course.dto;

import javax.validation.constraints.NotNull;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.domain.User;
import com.spring.course.domain.enums.RequestState;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RequestStageSavedto {

	private String description;

	@NotNull(message = "State required")
	private RequestState state;

	@NotNull(message = "Request required")
	private Request request;

	@NotNull(message = "Owner required")
	private User owner;

	public RequestStage tranformToRequestStage() {
		RequestStage stage = new RequestStage(null, description, null, state, request, owner);
		return stage;
	}
}