package com.surgeri.exception;

import com.surgeri.model.ApiFailureResponse;

import lombok.Getter;

/**
 * @author amitb
 *
 */
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = -604848337491333438L;

	@Getter
	private ApiFailureResponse apiFailureResponse;

	public AuthException(ApiFailureResponse apiFailureResponse) {
		this.apiFailureResponse = apiFailureResponse;
	}

}
