package com.synchrony.imagepoc.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.synchrony.imagepoc.exception.UserNotFoundException;
import com.synchrony.imagepoc.service.UserDaoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class UserResource.
 */
@RestController
@Slf4j
public class UserResource {
	
	private static final Logger logger = LoggerFactory.getLogger(UserResource.class);
	
	/** The user dao service. */
	private UserDaoService userDaoService;
	
	/**
	 * Instantiates a new user resource.
	 *
	 * @param userDaoService the user dao service
	 */
	public UserResource(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}

	/**
	 * Retrive all users.
	 *
	 * @return the list
	 */
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		logger.info("Returning all users");
		return userDaoService.getAllUsers();
	}
	
	/**
	 * Retrive user.
	 *
	 * @param id the id
	 * @return the user
	 */
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id) {
		logger.info("users retriving :{}",id);
		User user = userDaoService.getUserById(id);
		if(user == null) 
			throw new UserNotFoundException("id: "+id);
		return user;
	}
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the response entity
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userDaoService.deleteUser(id);
	}
	
	/**
	 * Update user.
	 *
	 * @param id the id
	 * @param user the user
	 * @return the response entity
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable int id,@Valid @RequestBody User user) {
		User _user = userDaoService.getUserById(id);
		if(_user == null) 
			throw new UserNotFoundException("id: "+id);
		_user.setFirstName(user.getFirstName());
		_user.setLastName(user.getLastName());
		_user.setUserName(user.getUserName());
		_user.setPassword(user.getPassword());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDaoService.saveUser(_user)).toUri();
		return ResponseEntity.created(location).build();
	}

}
