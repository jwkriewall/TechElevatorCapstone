package com.techelevator.controller;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Organizer Not Found")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -8930929336114625210L;
	
	public ResourceNotFoundException(String message) {
        super(message);
    }

}
