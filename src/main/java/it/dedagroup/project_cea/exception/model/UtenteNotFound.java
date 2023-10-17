package it.dedagroup.project_cea.exception.model;

public class UtenteNotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public UtenteNotFound() {
		super();
	}
	
	public UtenteNotFound(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UtenteNotFound(String message) {
		super(message);
	}
	
}
