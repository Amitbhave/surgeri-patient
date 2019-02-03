package com.surgeri.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.surgeri.database.UserDetailsRepository;
import com.surgeri.exception.AuthException;
import com.surgeri.model.ApiFailureResponse;
import com.surgeri.model.session.LoginApiRequest;
import com.surgeri.model.session.LoginDetail;
import com.surgeri.service.UserService;
import com.surgeri.util.Constants;

/**
 * @author amitb
 *
 */
@Service
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
	
	@Autowired
	UserDetailsRepository userDetailRepo;

	@Override
	public LoginDetail login(LoginApiRequest loginApiRequest) {
		LoginDetail loginDetails = userDetailRepo.findByUserNameAndPassword(loginApiRequest.getUsername(), loginApiRequest.getPassword());
		if(loginDetails == null) {
			LOGGER.error("Login error for user: {}", loginApiRequest.getUsername());
			throw new AuthException(new ApiFailureResponse(Constants.UNAUTHORIZED));
		}
		return loginDetails;
	}

}
