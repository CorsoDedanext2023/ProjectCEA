package it.dedagroup.project_cea.exception.model;

public class DatiNonValidiException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DatiNonValidiException() {
		super();
	}

	public DatiNonValidiException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatiNonValidiException(String message) {
		super(message);
	}
	
}
