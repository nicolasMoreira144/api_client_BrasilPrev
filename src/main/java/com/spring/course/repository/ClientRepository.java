package com.spring.course.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.course.domain.Client;
import com.spring.course.domain.enums.Role;

public interface ClientRepository extends JpaRepository<Client, Long>{
	
	@Query("SELECT c FROM client c WHERE email = ?1 AND password = ?2")
	public Optional<Client> login(String email, String password);
	
	@Transactional(readOnly = false)
	@Modifying
	@Query("UPDATE client SET role = ?2 WHERE id = ?1")
	public int updateRole(Long id, Role role);


	public Optional<Client> findByEmail(String email);
}
