package com.surgeri.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author amitb
 *
 */
@Controller
public class WebController {
	
	@GetMapping("/")
	public String showLogin(HttpServletRequest request){
		return "login";
	}
	
	@GetMapping("/getDashboard")
	public String showDashboard(HttpServletRequest request){
		return "dashboard";
	}

}
