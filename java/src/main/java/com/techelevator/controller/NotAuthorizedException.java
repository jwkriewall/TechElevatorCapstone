package com.techelevator.controller;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unauthorized")
public class NotAuthorizedException extends RuntimeException {

	private static final long serialVersionUID = -8930929336114625210L;
	
	public NotAuthorizedException(String message) {
        super(message);
    }

}
