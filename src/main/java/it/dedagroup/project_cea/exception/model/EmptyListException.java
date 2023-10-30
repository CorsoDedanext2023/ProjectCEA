package it.dedagroup.project_cea.exception.model;

import lombok.Data;
import lombok.Getter;

@Getter
public class EmptyListException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private Object request;

    public EmptyListException(Object request, String msg) {
        super(msg);
        this.request = request;
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyListException(String message) {
        super(message);
    }
}
