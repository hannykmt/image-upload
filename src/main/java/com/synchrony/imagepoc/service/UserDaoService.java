package com.synchrony.imagepoc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synchrony.imagepoc.controller.User;
import com.synchrony.imagepoc.exception.UserNotFoundException;
import com.synchrony.imagepoc.repository.UserRepository;

import jakarta.transaction.Transactional;

/**
 * The Class UserDaoService.
 */
@Service
@Transactional
public class UserDaoService {

	/** The user repo. */
	@Autowired
	UserRepository userRepo;

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
	public List<User> getAllUsers() {
		List<User> Users = userRepo.findAll();
		return Users;
	}

	/**
	 * Gets the user by id.
	 *
	 * @param id the id
	 * @return the user by id
	 */
	public User getUserById(int id) {
		return userRepo.findById(id);
	}

	/**
	 * Save user.
	 *
	 * @param User the user
	 * @return the user
	 */
	public User saveUser(User User) {
		return (User)userRepo.save(User);
	}

	/**
	 * Delete user.
	 *
	 * @param id the id
	 */
	public void deleteUser(int id) {
		try {
			userRepo.deleteById(id);
		} catch (Exception e) {
			throw new UserNotFoundException("id: "+id);
		}
	}
}
