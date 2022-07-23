package com.notable.health.nhtest.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.notable.health.nhtest.data.entities.User;
import com.notable.health.nhtest.data.repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public User getUser(@RequestParam(value = "id") Integer id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with ID %d not found", id));
		}
		
		return user.get();
	}

	@PostMapping
	public User createUser(@ModelAttribute @Valid User user, Errors errors) {
		
		if (errors.hasErrors()) {
			// Provide better error responses providing what was wrong
			System.err.println(errors.getAllErrors().toString());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your request is invalid, please check to make sure it is complete");
		}
		
		return userRepository.save(user);
	}
	
	@DeleteMapping
	public void deleteUser(@RequestParam(value = "id") Integer id) {
		userRepository.deleteById(id);
	}
	
}
