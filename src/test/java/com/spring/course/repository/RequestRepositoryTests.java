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
import com.spring.course.domain.User;
import com.spring.course.domain.enums.RequestState;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class RequestRepositoryTests {

	@Autowired
	private RequestRepository requestRepository;

	@Test
	public void AsaveTest() {
		User owner = new User();
		owner.setId(4L);

		Request request = new Request(null, "Novo laptop HP", "Pretendo obter um laptop HP", new Date(),
				RequestState.OPEN, owner, null);

		Request createdRequest = requestRepository.save(request);

		assertThat(createdRequest.getId()).isEqualTo(13L);
	}

	@Test
	public void updateTest() {
		User owner = new User();
		owner.setId(4L);

		Request request = new Request(13L, "Novo laptop HP, de RAM 16GB", "Pretendo obter um laptop HP", null,
				RequestState.OPEN, owner, null);

		Request updatedRequest = requestRepository.save(request);

		assertThat(updatedRequest.getDescription()).isEqualTo("Pretendo obter um laptop HP");

	}

	@Test
	public void getByIdTest() {
		Optional<Request> result = requestRepository.findById(13L);
		Request request = result.get();

		assertThat(request.getSubject()).isEqualTo("Novo laptop HP");
	}

	@Test
	public void listTest() {
		List<Request> request = requestRepository.findAll();
		assertThat(request.size()).isEqualTo(1);
	}

	@Test
	public void listByOwnerId() {
		List<Request> request = requestRepository.findAllByOwnerId(4L);
		assertThat(request.size()).isEqualTo(1);
	}

	@Test
	public void updateStatusTest() {
		int affectedRows = requestRepository.updateStatus(13L, RequestState.IN_PROGRESS);
		assertThat(affectedRows).isEqualTo(1);
	}
}
