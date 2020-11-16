package com.spring.course.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.course.domain.Client;
import com.spring.course.domain.User;
import com.spring.course.exception.NotFoundException;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.repository.ClientRepository;
import com.spring.course.service.util.HashUtil;

@Service
public class ClientService implements UserDetailsService{
	
	@Autowired
	private ClientRepository clientRepository;

	public Client save(Client client) {

		String hash = HashUtil.getSecureHash(client.getPassword());
		client.setPassword(hash);

		Client createdClient = clientRepository.save(client);
		return createdClient;
	}

	public Client update(Client client) {
		
		String hash = HashUtil.getSecureHash(client.getPassword());
		client.setPassword(hash);

		Client updatedClient = clientRepository.save(client);
		return updatedClient;

	}

	public Client getById(Long id) {
		Optional<Client> result = clientRepository.findById(id);
		
		return result.orElseThrow(() -> new NotFoundException("There are not user with id = " + id));
	}

	public List<Client> listAll() {
		List<Client> clients = clientRepository.findAll();

		return clients;
	}
	
	public PageModel<Client> listAllOnLazyMode(PageRequestModel pr){
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize()); 
		Page<Client> page = clientRepository.findAll(pageable);
		
		PageModel<Client> pm = new PageModel<>((int)page.getTotalElements(), page.getSize(), page.getTotalPages(), page.getContent());
		
		return pm;
	}
	
	public int updateRole(Client client) {
		return clientRepository.updateRole(client.getId(), client.getRole());
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Client> result = clientRepository.findByEmail(username);
		
		if(!result.isPresent()) throw new UsernameNotFoundException("Dosen't exist user with email = " + username);
		
		Client client = result.get();
		
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_" + client.getRole().name()));
		
		org.springframework.security.core.userdetails.User clientSpring = new org.springframework.security.core.userdetails.User(client.getEmail(), client.getPassword(), authorities);
		
		return clientSpring;
	}
}
