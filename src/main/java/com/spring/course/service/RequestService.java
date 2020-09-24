package com.spring.course.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.spring.course.domain.Request;
import com.spring.course.domain.enums.RequestState;
import com.spring.course.repository.RequestRepository;

@Service
public class RequestService {

	private RequestRepository requestRepository;

	public Request save(Request request) {
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		Request createdRequest = requestRepository.save(request);

		return createdRequest;
	}

	public Request update(Request request) {
		Request updatedRequest = requestRepository.save(request);
		return updatedRequest;

	}

	public Request findById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		return result.get();
	}

	public List<Request> listAll() {
		List<Request> requests = requestRepository.findAll();
		return requests;
	}


	public List<Request> listAllByOwnerId(Long id) {
		List<Request> requests = requestRepository.findAllByOwnerId(id);
		return requests;
	}

}
