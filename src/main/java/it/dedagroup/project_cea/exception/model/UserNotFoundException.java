package it.dedagroup.project_cea.exception.model;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Object request;
	
	public UserNotFoundException(Object o, String msg) {
		super(msg);
		request = o;
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
