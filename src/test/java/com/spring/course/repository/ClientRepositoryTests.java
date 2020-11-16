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

import com.spring.course.domain.Client;
import com.spring.course.domain.enums.Role;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
@SpringBootTest
public class ClientRepositoryTests {

	@Autowired
	private ClientRepository clientRepository;
	
	@Test // 'A' é proposital para esse metodo ser executado primeiro que todos isso é uma tecnica  
	public void AsaveTest() {
		Client client = new Client(null, "test", "12345678910", "rua direita", "test@gmail.com", Role.ADMINISTRATOR, "12345678");
		Client createdClient = clientRepository.save(client);

		assertThat(createdClient.getId()).isEqualTo(4L);
	}

	@Test
	public void updateTest() {
		Client client = new Client(null, "test", "12345678910", "rua direita", "test@gmail.com", Role.ADMINISTRATOR, "12345678");
		Client updatedClient = clientRepository.save(client);

		assertThat(updatedClient.getName()).isEqualTo("test");

	}

	@Test
	public void getByIdTets() {
		Optional<Client> result = clientRepository.findById(4L);
		Client client = result.get();

		assertThat(client.getPassword()).isEqualTo("123");
	}

	@Test
	public void listTest() {
		List<Client> users = clientRepository.findAll();
		assertThat(users.size()).isEqualTo(1);// size not change
	}

	@Test
	public void loginTest() {
		Optional<Client> result = clientRepository.login("nicolassouza144@gmail.com", "123");
		Client loggedUser = result.get();
	
		assertThat(loggedUser.getId()).isEqualTo(4);
	}

	@Test
	public void updateRoleTest() {
		int affectedRows = clientRepository.updateRole(5L, Role.ADMINISTRATOR);
		assertThat(affectedRows).isEqualTo(1);
	}
}
