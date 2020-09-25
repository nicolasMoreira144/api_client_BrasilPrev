package com.spring.course.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.domain.RequestStage;
import com.spring.course.service.RequestStageService;

@RestController
@RequestMapping(value = "requests-stages")
public class RequestStageResource {
	
	@Autowired
	private RequestStageService stageService;
	
	public ResponseEntity<RequestStage> save (RequestStage requestStage){
		RequestStage createdRequestStage = stageService.save(requestStage);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequestStage);
	}


	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById (@PathVariable(name = "id")Long id){
		RequestStage stage = stageService.getById(id);
		return ResponseEntity.ok(stage);
	}

}
