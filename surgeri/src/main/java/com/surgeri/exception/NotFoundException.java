package com.surgeri.exception;

import com.surgeri.model.ApiFailureResponse;

import lombok.Getter;

/**
 * @author amitb
 *
 */
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7405789993746245778L;

	@Getter
	private ApiFailureResponse apiFailureResponse;

	public NotFoundException(ApiFailureResponse apiFailureResponse) {
		this.apiFailureResponse = apiFailureResponse;
	}

}
