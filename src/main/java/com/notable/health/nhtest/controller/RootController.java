package com.notable.health.nhtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Root {@link Controller}
 * 
 * @author Hank DeDona
 *
 */
@Controller()
@RequestMapping("")
public class RootController {

	@GetMapping
	@ResponseBody
	public String index() {
		return "Root Index Page!";
	}

}
