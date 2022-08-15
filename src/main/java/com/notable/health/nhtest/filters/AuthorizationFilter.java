package com.notable.health.nhtest.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AuthorizationFilter implements Filter {

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Read the headers
		// What do we do if they're not present? return 401 unauthenticated!
		
		String userId = ((HttpServletRequest)request).getHeader("X-User-Id");
		String userRole = ((HttpServletRequest)request).getHeader("X-User-Role");
		
		if(userId == null || userId.isBlank()) {
			// return 401
		}
		if(userRole == null || userRole.isBlank()) {
			// return 401
		}
		
		// Admin!!!
		if(userRole.equalsIgnoreCase("admin")) {
			chain.doFilter(request, response);
		}
		else if(userRole.equalsIgnoreCase("doctor")) {
			// check if the doctor is accessing their own appointments
			
			
			
		}
		else {
			// 400 bad request, unacceptable role!
		}
		
				
		chain.doFilter(request, response);
	}

}
