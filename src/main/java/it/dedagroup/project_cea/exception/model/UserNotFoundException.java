package it.dedagroup.project_cea.exception.model;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
