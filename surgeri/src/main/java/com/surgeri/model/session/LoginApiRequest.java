package com.surgeri.model.session;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author amitb
 *
 */
@Data
public class LoginApiRequest {
	
	@NotNull
	private String username;
	@NotNull
	private String password;

}
