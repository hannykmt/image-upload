package com.synchrony.imagepoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synchrony.imagepoc.controller.User;

/**
 * The Interface UserRepository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<User> findAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	User findById(int id);
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @return the int
	 */
	int deleteById(int id);
}