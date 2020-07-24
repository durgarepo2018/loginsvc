package com.mckesson.inferno.loginsvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SequenceException extends RuntimeException {

	public SequenceException(String message) {
		super(message);
	}
}
