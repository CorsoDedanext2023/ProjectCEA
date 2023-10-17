package it.dedagroup.project_cea.exception.model;

import lombok.Getter;


public class NotValidDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	@Getter
	private Object oggetto;
	public NotValidDataException() {
		super();
	}

	public NotValidDataException(String message, Object oggetto) {
		super(message);
		this.oggetto = oggetto;
	}

	public NotValidDataException(String message) {
		super(message);
	}
	
}
