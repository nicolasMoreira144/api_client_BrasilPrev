package com.spring.course.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.spring.course.domain.User;
import com.spring.course.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	
	@Test // 'A' é proposital para esse metodo ser executado primeiro que todos isso é uma tecnica  
	public void AsaveTest() {
		User user = new User(null, "Nicolas", "nicolassouza144@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User createdUser = userRepository.save(user);

		assertThat(createdUser.getId()).isEqualTo(4L);
	}

	@Test
	public void updateTest() {
		User user = new User(4L, "Nicolas Souza", "nicolassouza144@gmail.com", "123", Role.ADMINISTRATOR, null, null);
		User updatedUser = userRepository.save(user);

		assertThat(updatedUser.getName()).isEqualTo("Nicolas Souza");

	}

	@Test
	public void getByIdTets() {
		Optional<User> result = userRepository.findById(4L);
		User user = result.get();

		assertThat(user.getPassword()).isEqualTo("123");
	}

	@Test
	public void listTest() {
		List<User> users = userRepository.findAll();
		assertThat(users.size()).isEqualTo(1);// size not change
	}

	@Test
	public void loginTest() {
		Optional<User> result = userRepository.login("nicolassouza144@gmail.com", "123");
		User loggedUser = result.get();
	
		assertThat(loggedUser.getId()).isEqualTo(4);
	}

}
