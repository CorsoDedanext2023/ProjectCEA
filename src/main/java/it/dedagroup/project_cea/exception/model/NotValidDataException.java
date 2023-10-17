package it.dedagroup.project_cea.exception.model;

public class NotValidDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NotValidDataException() {
		super();
	}

	public NotValidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotValidDataException(String message) {
		super(message);
	}
	
}
