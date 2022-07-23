package com.notable.health.nhtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.notable.health.nhtest.data.entities.User;
import com.notable.health.nhtest.data.repositories.UserRepository;

@Controller()
@RequestMapping("")
public class RootController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	@ResponseBody
    public String index() {
		
		User testUser = new User();
		testUser.setName("Hank");
		testUser.setEmail("icon5585@gmail.com");
		
		User updatedTestUser = userRepository.save(testUser);
		
		System.out.println(updatedTestUser);
		
        return "Root Index Page!";
    }
	
}
