package it.dedagroup.project_cea.exception.model;

public class ScanNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Object request;

    public ScanNotFoundException(Object o, String msg) {
        super(msg);
        request = o;
    }

    public ScanNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ScanNotFoundException(String message) {
        super(message);
    }
}
