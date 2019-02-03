package com.surgeri.service;

import com.surgeri.model.session.LoginApiRequest;
import com.surgeri.model.session.LoginDetail;

public interface UserService {
	
	LoginDetail login(LoginApiRequest loginApiRequest);

}
