package com.surgeri.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.surgeri.model.session.LoginApiRequest;
import com.surgeri.model.session.LoginDetail;
import com.surgeri.service.UserService;
import com.surgeri.util.Constants;

/**
 * @author amitb
 *
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	private static final Logger LOGGER = LogManager.getLogger(UserController.class.getName());
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<String> doLogin(HttpServletRequest request, @RequestBody @Valid LoginApiRequest loginRequest){
		LOGGER.info("Request to login from {}", loginRequest.getUsername());
		LoginDetail loginDetails = userService.login(loginRequest);
		request.getSession().setAttribute(Constants.LOGGEDIN_USER, loginDetails);
		LOGGER.info("Login success for user: {}", loginDetails.getUserName());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> doLogout(HttpServletRequest request){
		if(request.getSession() != null && request.getSession().getAttribute(Constants.LOGGEDIN_USER) != null) {
			request.getSession().removeAttribute(Constants.LOGGEDIN_USER);
		}
		LOGGER.info("Logging out success");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
