package com.spring.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.course.domain.Request;
import com.spring.course.domain.RequestStage;
import com.spring.course.domain.User;
import com.spring.course.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestStageRepositoryTests {

	@Autowired
	private RequestStageRepository requestStageRepository;

	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(4L);
		
		Request request = new Request();
		request.setId(13L);
		
		RequestStage stage = new RequestStage(null, "Foi comprando um laptop de marca HP", new Date(), RequestState.CLOSED, request, owner);
		
		RequestStage createdStage = requestStageRepository.save(stage);
	
		assertThat(createdStage.getId()).isEqualTo(3L);
	}
	
	@Test
	public void getByIdTest() {
		Optional<RequestStage> result = requestStageRepository.findById(3L);
		RequestStage stage = result.get();
	
		assertThat(stage.getDescription()).isEqualTo("Foi comprando um laptop de marca HP");
	}

	@Test
	public void listByRequestId() {
		List<RequestStage> stages = requestStageRepository.findAll();
		assertThat(stages.size()).isEqualTo(1L);
	
	}
}
