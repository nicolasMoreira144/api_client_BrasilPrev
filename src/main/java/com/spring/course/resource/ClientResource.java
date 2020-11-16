package com.spring.course.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.course.domain.Client;
import com.spring.course.dto.ClientSavedto;
import com.spring.course.dto.ClientUpdateRoledto;
import com.spring.course.dto.ClientUpdatedto;
import com.spring.course.dto.UserLoginResponsedto;
import com.spring.course.dto.UserLogindto;
import com.spring.course.model.PageModel;
import com.spring.course.model.PageRequestModel;
import com.spring.course.security.JwtManager;
import com.spring.course.service.ClientService;

@RestController
@RequestMapping(value = "clients")
public class ClientResource {
	
	@Autowired
	private ClientService clientService;

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtManager jwtManager; 
	
	//@Secured({"ROLE_ADMINISTRATOR"})
	@PostMapping
	public ResponseEntity<Client> save(@RequestBody @Valid ClientSavedto clientdto) {
		Client clientToSave = clientdto.transformToClient();
		Client createdClient = clientService.save(clientToSave);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<Client> update(@PathVariable(name = "id") Long id, @RequestBody @Valid ClientUpdatedto clientdto) {
		Client client = clientdto.transformToClient();
		client.setId(id);
		Client updatedClient = clientService.update(client);
		return ResponseEntity.ok(updatedClient);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getById(@PathVariable(name = "id") Long id) {
		Client client = clientService.getById(id);
		return ResponseEntity.ok(client);
	}

	@GetMapping
	public ResponseEntity<PageModel<Client>> listAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Client> pm = clientService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}

	@PostMapping("/login")
	public ResponseEntity<UserLoginResponsedto> login(@RequestBody @Valid UserLogindto user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
		Authentication auth = authManager.authenticate(token);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		org.springframework.security.core.userdetails.User userSpring =
				(org.springframework.security.core.userdetails.User) auth.getPrincipal();
		
		String email = userSpring.getUsername();
		List<String> roles = userSpring.getAuthorities()
										.stream()
										.map(authority -> authority.getAuthority())
										.collect(Collectors.toList());
		
		
		
		return ResponseEntity.ok(jwtManager.createToken(email, roles));
		
	}

	@Secured({"ROLE_ADMINISTRATOR"})
	@PatchMapping("/role/{id}")
	public ResponseEntity<?> updateRole(@PathVariable(name = "id") Long id, @RequestBody @Valid ClientUpdateRoledto clientdto){
		Client client = new Client();
		client.setId(id);
		client.setRole(clientdto.getRole());
		
		clientService.updateRole(client);
		
		return ResponseEntity.ok().build();
	
	}
}
